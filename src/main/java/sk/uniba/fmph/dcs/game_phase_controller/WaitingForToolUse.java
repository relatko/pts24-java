package sk.uniba.fmph.dcs.game_phase_controller;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.Location;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;
import sk.uniba.fmph.dcs.stone_age.InterfaceToolUse;

import java.util.Collection;
import java.util.Map;

public final class WaitingForToolUse implements InterfaceGamePhaseState {
    private final Map<PlayerOrder, InterfaceToolUse> interfaceToolUseCollection;

    public WaitingForToolUse(final Map<PlayerOrder, InterfaceToolUse> interfaceToolUseCollection) {
        this.interfaceToolUseCollection = interfaceToolUseCollection;
    }

    @Override
    public ActionResult placeFigures(final PlayerOrder player, final Location location, final int figuresCount) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult makeAction(final PlayerOrder player, final Location location,
            final Collection<Effect> inputResources, final Collection<Effect> outputResources) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult skipAction(final PlayerOrder player, final Location location) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult useTools(final PlayerOrder player, final int toolIndex) {
        if (interfaceToolUseCollection.get(player).useTool(toolIndex)) {
            return ActionResult.ACTION_DONE;
        } else {
            return ActionResult.FAILURE;
        }
    }

    @Override
    public ActionResult noMoreToolsThisThrow(final PlayerOrder player) {
        if (interfaceToolUseCollection.get(player).finishUsingTools()) {
            return ActionResult.ACTION_DONE;
        } else {
            return ActionResult.FAILURE;
        }
    }

    @Override
    public ActionResult feedTribe(final PlayerOrder player, final Collection<Effect> resources) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult doNotFeedThisTurn(final PlayerOrder player) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult makeAllPlayersTakeARewardChoice(final PlayerOrder player, final Effect reward) {
        return ActionResult.FAILURE;
    }

    @Override
    public HasAction tryToMakeAutomaticAction(final PlayerOrder player) {
        if (interfaceToolUseCollection.get(player) == null || !interfaceToolUseCollection.get(player).canUseTools()) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        if (interfaceToolUseCollection.get(player).finishUsingTools()) {
            return HasAction.AUTOMATIC_ACTION_DONE;
        }

        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }
}
