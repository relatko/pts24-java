package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.InterfaceFigureLocation;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.Collection;

public class FigureLocationAdaptor implements InterfaceFigureLocation {
    private final InterfaceFigureLocationInternal flInternal;
    private final Collection<Player> players;

    public FigureLocationAdaptor(InterfaceFigureLocationInternal flInternal, Collection<Player> players) {
        this.flInternal = flInternal;
        this.players = players;
    }

    private Player getPlayerByOrder(PlayerOrder order) {
        for (Player player : players) {
            if(player.getPlayerOrder().equals(order)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean placeFigures(PlayerOrder player, int figureCount){
        Player adaptedPlayer = getPlayerByOrder(player);
        if(adaptedPlayer == null)
            return false;
        return flInternal.placeFigures(adaptedPlayer, figureCount);
    }

    @Override
    public HasAction tryToPlaceFigures(PlayerOrder player, int count){
        Player adaptedPlayer = getPlayerByOrder(player);
        if(adaptedPlayer == null)
            return HasAction.NO_ACTION_POSSIBLE;
        return flInternal.tryToPlaceFigures(adaptedPlayer, count);
    }

    @Override
    public ActionResult makeAction(PlayerOrder player, Collection<Effect> inputResources,
                            Collection<Effect> outputResources){
        Player adaptedPlayer = getPlayerByOrder(player);
        if(adaptedPlayer == null)
            return ActionResult.FAILURE;
        return flInternal.makeAction(adaptedPlayer, inputResources, outputResources);
    }
    @Override
    public boolean skipAction(PlayerOrder player){
        Player adaptedPlayer = getPlayerByOrder(player);
        if(adaptedPlayer == null)
            return false
        return flInternal.skipAction(adaptedPlayer);
    }

    @Override
    public HasAction tryToMakeAction(PlayerOrder player){
        Player adaptedPlayer = getPlayerByOrder(player);
        if(adaptedPlayer == null)
            return HasAction.NO_ACTION_POSSIBLE;
        return flInternal.tryToMakeAction(adaptedPlayer);
    }

    @Override
    public boolean newTurn() //returns true if end of game is implied by
                                //given location
    {
        return flInternal.newTurn();
    }

}
