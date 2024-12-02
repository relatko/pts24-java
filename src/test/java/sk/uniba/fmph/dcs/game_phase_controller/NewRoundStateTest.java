package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.jupiter.api.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NewRoundStateTest {

    private static class RoundFigureLocationMock implements InterfaceFigureLocation {
        private final boolean newTurnExpected;

        RoundFigureLocationMock(boolean newTurnExpected) {
            this.newTurnExpected = newTurnExpected;
        }

        @Override
        public boolean placeFigures(PlayerOrder player, int figureCount) {
            throw new AssertionError();
        }

        @Override
        public HasAction tryToPlaceFigures(PlayerOrder player, int count) {
            throw new AssertionError();
        }

        @Override
        public ActionResult makeAction(PlayerOrder player, Collection<Effect> inputResources,
                Collection<Effect> outputResources) {
            throw new AssertionError();
        }

        @Override
        public boolean skipAction(PlayerOrder player) {
            throw new AssertionError();
        }

        @Override
        public HasAction tryToMakeAction(PlayerOrder player) {
            throw new AssertionError();
        }

        @Override
        public boolean newTurn() {
            return newTurnExpected;
        }
    }

    private static class NewTurnMock implements InterfaceNewTurn {
        boolean newTurnCalled = false;

        @Override
        public void newTurn() {
            newTurnCalled = true;
        }
    }

    @Test
    public void testTryToMakeAutomaticActionScenarioFF() {
        RoundFigureLocationMock location1 = new RoundFigureLocationMock(false);
        RoundFigureLocationMock location2 = new RoundFigureLocationMock(false);
        NewTurnMock newTurnMock = new NewTurnMock();
        NewRoundState newRoundState = new NewRoundState(new RoundFigureLocationMock[] { location1, location2 },
                newTurnMock);
        assertEquals(HasAction.AUTOMATIC_ACTION_DONE, newRoundState.tryToMakeAutomaticAction(new PlayerOrder(0, 1)));
        assertTrue(newTurnMock.newTurnCalled);
    }
    @Test
    public void testTryToMakeAutomaticActionScenarioTF() {
        RoundFigureLocationMock location1 = new RoundFigureLocationMock(true);
        RoundFigureLocationMock location2 = new RoundFigureLocationMock(false);
        NewTurnMock newTurnMock = new NewTurnMock();
        NewRoundState newRoundState = new NewRoundState(new RoundFigureLocationMock[] { location1, location2 }, newTurnMock);
        newTurnMock.newTurnCalled = false;
        assertEquals(HasAction.NO_ACTION_POSSIBLE, newRoundState.tryToMakeAutomaticAction(new PlayerOrder(0, 1)));
        assertFalse(newTurnMock.newTurnCalled);
    }
    @Test
    public void testTryToMakeAutomaticActionScenarioTT() {
        RoundFigureLocationMock location1 = new RoundFigureLocationMock(true);
        RoundFigureLocationMock location2 = new RoundFigureLocationMock(true);
        NewTurnMock newTurnMock = new NewTurnMock();
        NewRoundState newRoundState = new NewRoundState(new RoundFigureLocationMock[] { location1, location2 },
                newTurnMock);
        assertEquals(HasAction.NO_ACTION_POSSIBLE, newRoundState.tryToMakeAutomaticAction(new PlayerOrder(0, 1)));
        assertFalse(newTurnMock.newTurnCalled);
    }
}