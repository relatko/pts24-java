package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TribeFedStatus {
    private boolean tribeFed;
    private int fields;
    private PlayerFigures playerFigures;
    private PlayerResourcesAndFood playerResourcesAndFood;
    public TribeFedStatus(PlayerResourcesAndFood playerResourcesAndFood, PlayerFigures playerFigures){
        this.playerResourcesAndFood = playerResourcesAndFood;
        this.playerFigures = playerFigures;
        tribeFed = true;
        fields = 0;
    }
    public void addField(){
        if(fields<10)
            fields++;
    }
    public void newTurn(){
        tribeFed = false;
    }
    public void addFoodForFields(){
        List<Effect> addedFood = new ArrayList<Effect>();
        for(int i=0;i<fields;i++)
            addedFood.add(Effect.FOOD);
        playerResourcesAndFood.giveResources(addedFood);
    }
    public boolean feedTribeIfEnoughFood(){
        addFoodForFields();
        List<Effect> neededFood = new ArrayList<Effect>();
        for(int i=0;i<playerFigures.getTotalFigures();i++)
            neededFood.add(Effect.FOOD);
        if(playerResourcesAndFood.takeResources(neededFood)) {
            tribeFed = true;
            return true;
        }
        return false;
    }
    public boolean feedTribe(Collection<Effect> resources){
        if(resources.size()==playerFigures.getTotalFigures()&&playerResourcesAndFood.takeResources(resources)) {
            tribeFed = true;
            return true;
        }
        return false;
    }
    public boolean isTribeFed(){
        return tribeFed;
    }
    public boolean setTribeFed(){
        playerResourcesAndFood.takeFoodAway();
        tribeFed = true;
        return true;
    }
    public int getFields(){
        return fields;
    }
    public String state(){
        if(tribeFed)
            return "Tribe is fed\n";
        else
            return "Tribe is not fed\n";
    }
}
