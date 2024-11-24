package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.Collection;

public class ToolMakerHutFields {
    private PlayerOrder toolMakerFigures;
    private PlayerOrder hutFigures;
    private PlayerOrder fieldsFigures;

    private final int restriction; // for less than 4 players
    private static final int MIN_PL = 2;
    private static final int MAX_PL = 4;

    public ToolMakerHutFields(int players){
        this.restriction = players;
        if (!(MIN_PL <= players && players <= MAX_PL)){
            throw new IllegalArgumentException("Invalid number of players");
        }
        toolMakerFigures = null;
        hutFigures = null;
        fieldsFigures = null;
    }

    private boolean checkRestriction()
    {
        if(this.restriction == MAX_PL)
            return true;

        int alreadyOccupiedLocations = 0;

        if(toolMakerFigures != null)
            alreadyOccupiedLocations++;
        if(hutFigures != null)
            alreadyOccupiedLocations++;
        if(fieldsFigures != null)
            alreadyOccupiedLocations++;

        if(alreadyOccupiedLocations >= 2)
            return false;
        return true;
    }

    public boolean canPlaceOnToolMaker(Player player){
        if(!checkRestriction())
            return false;
        return toolMakerFigures == null && player.getPlayerBoard().hasFigures(1);
    }
    public boolean placeOnHut(Player figures){
        if(canPlaceOnHut(figures)){
            figures.getPlayerBoard().takeFigures(2);
            hutFigures = figures.getPlayerOrder();
            return true;
        }
        return false;
    }

    public boolean placeOnToolMaker(Player player)
    {
        if(canPlaceOnToolMaker(player)){
            player.getPlayerBoard().takeFigures(1);
            toolMakerFigures = player.getPlayerOrder();
            return true;
        }
        return false;
    }

    public boolean canActionOnToolMaker(Player player){
        return toolMakerFigures.equals(player.getPlayerOrder());
    }

    public boolean actionToolMaker(Player player){
        if (canActionOnToolMaker(player)) {
            Collection<Effect> newTool = new ArrayList<Effect>();
            newTool.add(Effect.TOOL);
            player.getPlayerBoard().giveEffect(newTool);
            toolMakerFigures = null;
            return true;
        }
        return false;
    }

    public boolean actionHut(Player player){
        if (hutFigures.equals(player.getPlayerOrder())) {
            player.getPlayerBoard().giveFigure();
            hutFigures = null;
            return true;
        }
        return false;
    }
    boolean canPlaceOnHut(Player player){
        if(!checkRestriction())
            return false;
        return hutFigures == null  && player.getPlayerBoard().hasFigures(2);
    }
    public boolean placeOnFields(Player player){
        if(canPlaceOnFields(player)){
            player.getPlayerBoard().takeFigures(1);
            fieldsFigures = player.getPlayerOrder();
            return true;
        }
        return false;
    }

    public boolean actionFields(Player player){
        if (fieldsFigures.equals(player.getPlayerOrder())) {
            Collection<Effect> newField = new ArrayList<Effect>();
            newField.add(Effect.FIELD);
            player.getPlayerBoard().giveEffect(newField);
            fieldsFigures = null;
            return true;
        }
        return false;
    }
    public boolean canPlaceOnFields(Player player)
    {
        if(!checkRestriction())
            return false;
        return fieldsFigures == null  && player.getPlayerBoard().hasFigures(1);
    }
    public boolean newTurn(){
        return toolMakerFigures == null && hutFigures == null && fieldsFigures == null;
    }

    public String state(){
        StringBuilder state = new StringBuilder();
        state.append("Player on Tool Maker:");
        state.append(toolMakerFigures == null ? "None" : " " + toolMakerFigures.toString());
        state.append("\n");

        state.append("Player on Fields:");
        state.append(fieldsFigures == null ? "None" : " " + fieldsFigures.toString());
        state.append("\n");

        state.append("Player on Hut:");
        state.append(hutFigures == null ? "None" : " " + hutFigures.toString());
        state.append("\n");

        return state.toString();
    }
}
