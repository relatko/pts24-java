package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Before;
import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


class MockInterfaceFeedTribe implements InterfaceFeedTribe{

    List<Boolean> expectedBoolean;
    @Override
    public boolean feedTribeIfEnoughFood() {

        return expectedBoolean.remove(0);
    }

    @Override
    public boolean feedTribe(Effect[] resources) {

        return expectedBoolean.remove(0);
    }

    @Override
    public boolean doNotFeedThisTurn() {

        return expectedBoolean.remove(0);
    }

    @Override
    public boolean isTribeFed() {

        return expectedBoolean.remove(0);
    }
}

public class FeedTribeStateTest {

    private FeedTribeState feedTribeState;
    private MockInterfaceFeedTribe mockInterfaceFeedTribe;
    private PlayerOrder player;
    @Before
    public void setUp() {

        this.player = new PlayerOrder(0,1);
        this.mockInterfaceFeedTribe = new MockInterfaceFeedTribe();

        Map<PlayerOrder, InterfaceFeedTribe> playerBoardFeedTribe = new HashMap<>();
        playerBoardFeedTribe.put(player, mockInterfaceFeedTribe);
        this.feedTribeState = new FeedTribeState(playerBoardFeedTribe);
    }

    private void setExpectedList(List<Boolean> list){

        mockInterfaceFeedTribe.expectedBoolean = new ArrayList<>(list);
    }

    private ActionResult feedTribe(){

        return feedTribeState.feedTribe(player,List.of());
    }
    @Test
    public void testFeedTribe() {

        setExpectedList(List.of(false));

        assertEquals(feedTribe(), ActionResult.FAILURE);

        setExpectedList(List.of(true));

        assertEquals(feedTribe(), ActionResult.ACTION_DONE);
    }

    private ActionResult doNotFeedThisTurn(){

        return feedTribeState.doNotFeedThisTurn(player);

    }
    @Test
    public void testDoNotFeedThisTurn() {

        setExpectedList(List.of(false));

        assertEquals(doNotFeedThisTurn(), ActionResult.FAILURE);

        setExpectedList(List.of(true));

        assertEquals(doNotFeedThisTurn(), ActionResult.ACTION_DONE);
    }

    private HasAction tryToMakeAutomaticAction(){

        return feedTribeState.tryToMakeAutomaticAction(player);

    }

    @Test
    public void testTryToMakeAutomaticAction() {

        setExpectedList(List.of(true, true, true));

        assertEquals(tryToMakeAutomaticAction(), HasAction.NO_ACTION_POSSIBLE);

        setExpectedList(List.of(false, true, true));

        assertEquals(tryToMakeAutomaticAction(), HasAction.AUTOMATIC_ACTION_DONE);

        setExpectedList(List.of(false, false, true));

        assertEquals(tryToMakeAutomaticAction(), HasAction.WAITING_FOR_PLAYER_ACTION);
    }
}