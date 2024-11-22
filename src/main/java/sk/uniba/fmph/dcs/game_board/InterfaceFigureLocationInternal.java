package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

import java.util.Collection;

public interface InterfaceFigureLocationInternal {
    Boolean placeFigures(Player player, Integer figureCount);
    HasAction tryToPlaceFigures(Player player, Integer count);
    ActionResult makeAction(Player player, Collection<Effect> inputResources,
                            Collection<Effect> outputResources);
    Boolean skipAction(Player player);
    HasAction tryToMakeAction(Player player);
    Boolean newTurn(); //returns true if end of game is implied by
                        //given location
}
