package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

import java.util.Collection;

public class PlaceOnFieldsAdaptor implements InterfaceFigureLocationInternal{
    private ToolMakerHutFields tmhf;

    public PlaceOnFieldsAdaptor(ToolMakerHutFields tmhf) {
        this.tmhf = tmhf;
    }

    @Override
    public boolean placeFigures(Player player, int figureCount){
        return figureCount == 1 && tmhf.canPlaceOnFields(player);
    }

    @Override
    public HasAction tryToPlaceFigures(Player player, int count){
        if(!this.placeFigures(player, count))
            return HasAction.NO_ACTION_POSSIBLE;
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public ActionResult makeAction(Player player, Collection<Effect> inputResources,
                                   Collection<Effect> outputResources)
    {
        if(tryToMakeAction(player) == HasAction.WAITING_FOR_PLAYER_ACTION && tmhf.actionToolMaker(player))
            return ActionResult.ACTION_DONE;
        return ActionResult.FAILURE;
    }


    @Override
    public HasAction tryToMakeAction(Player player){
        if (tmhf.canActionOnFields(player))
            return HasAction.WAITING_FOR_PLAYER_ACTION;
        return HasAction.NO_ACTION_POSSIBLE;
    }

    @Override
    public boolean skipAction(Player player){
        return tmhf.skipActionOnFields(player);
    }

    @Override
    public boolean newTurn()
    {
        return tmhf.newTurn();
    }
}
