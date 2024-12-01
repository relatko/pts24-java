package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.jupiter.api.Test;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.InterfaceToolUse;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitingForToolUseStateTest {

    private static class ToolUseMock implements InterfaceToolUse {
        private final boolean useToolExpected;
        private final boolean finishUsingToolsExpected;
        private final boolean canUseToolsExpected;

        ToolUseMock(boolean useToolExpected, boolean canUseTools, boolean finishedUsingTools) {
            this.useToolExpected = useToolExpected;
            this.canUseToolsExpected = canUseTools;
            this.finishUsingToolsExpected = finishedUsingTools;
        }

        @Override
        public boolean useTool(int idx) {
            return useToolExpected;
        }

        @Override
        public boolean canUseTools() {
            return canUseToolsExpected;
        }

        @Override
        public boolean finishUsingTools() {
            return finishUsingToolsExpected;
        }
    }

    @Test
    public void testUseTool() {
        HashMap<PlayerOrder, InterfaceToolUse> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);

        map.put(player1, new ToolUseMock(false, false, false));
        map.put(player2, new ToolUseMock(true, true, false));
        WaitingForToolUseState toolUse = new WaitingForToolUseState(map);

        assertEquals(toolUse.useTools(player1, 1), ActionResult.FAILURE);
        assertEquals(toolUse.useTools(player2, 1), ActionResult.ACTION_DONE);
    }

    @Test
    public void testNoMoreToolsThisThrow() {
        HashMap<PlayerOrder, InterfaceToolUse> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);

        map.put(player1, new ToolUseMock(false, false, false)); // finishUsingToolsExpected = false
        map.put(player2, new ToolUseMock(false, false, true)); // finishUsingToolsExpected = true
        WaitingForToolUseState toolUse = new WaitingForToolUseState(map);

        assertEquals(toolUse.noMoreToolsThisThrow(player1), ActionResult.FAILURE);
        assertEquals(toolUse.noMoreToolsThisThrow(player2), ActionResult.ACTION_DONE);
    }

    @Test
    public void testTryToMakeAutomaticAction() {
        HashMap<PlayerOrder, InterfaceToolUse> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);
        PlayerOrder player3 = new PlayerOrder(3, 3);

        map.put(player1, new WaitingForToolUseStateTest.ToolUseMock(false, false, false));
        map.put(player2, new WaitingForToolUseStateTest.ToolUseMock(false, false, true));
        map.put(player3, new WaitingForToolUseStateTest.ToolUseMock(true, true, true));
        WaitingForToolUseState toolUse = new WaitingForToolUseState(map);
        assertEquals(toolUse.tryToMakeAutomaticAction(player1), HasAction.NO_ACTION_POSSIBLE);
        assertEquals(toolUse.tryToMakeAutomaticAction(player2), HasAction.NO_ACTION_POSSIBLE);
        assertEquals(toolUse.tryToMakeAutomaticAction(player3), HasAction.AUTOMATIC_ACTION_DONE);
    }
}
