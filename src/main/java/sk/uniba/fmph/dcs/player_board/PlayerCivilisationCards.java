package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;

import java.util.*;

public class PlayerCivilisationCards {
    private Map<EndOfGameEffect,Integer> endEffects;
    public PlayerCivilisationCards(){
        endEffects = new HashMap<EndOfGameEffect,Integer>();
        for(EndOfGameEffect effect: EndOfGameEffect.values())
            endEffects.put(effect,0);
    }
    public void addEndOfGameEffects(Collection<EndOfGameEffect> effects){
        for(EndOfGameEffect effect: effects)
            endEffects.put(effect,endEffects.get(effect)+1);
    }
    public int calculateEndOfGameCivilisationCardPoints(int buildings, int tools, int fields, int figures){
        int points = 0;
        //defines how much effects cards are at least in i instances
        int[] number_of_copies = new int[5];
        for(EndOfGameEffect effect: EndOfGameEffect.values()){
            switch (effect){
                case FARMER -> points += endEffects.get(effect)*fields;
                case SHAMAN -> points += endEffects.get(effect)*figures;
                case BUILDER -> points += endEffects.get(effect)*buildings;
                case TOOL_MAKER -> points += endEffects.get(effect)*tools;
                default -> {
                    for (int i=1;i<=endEffects.get(effect);i++)
                        number_of_copies[i]++;
                }
            }
        }
        for(int i=1;i<=4;i++)
            points+=number_of_copies[i]*number_of_copies[i];
        return points;
    }
    public String state(){
        StringBuilder state = new StringBuilder("Number of EndGameEffects:\n");
        for(EndOfGameEffect effect: endEffects.keySet()){
            if(endEffects.get(effect)>0){
                state.append(effect.name()).append(" : ").append(endEffects.get(effect)).append('\n');
            }
        }
        return state.toString();
    }
}
