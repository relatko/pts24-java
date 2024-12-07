package sk.uniba.fmph.dcs.player_board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerFiguresTest {

    private PlayerFigures playerFiguresUnderTest;

    @Before
    public void setUp() {
        playerFiguresUnderTest = new PlayerFigures();
    }

    @Test
    public void test_all(){
        assertTrue(playerFiguresUnderTest.hasFigures(5));
        assertTrue(playerFiguresUnderTest.takeFigures(3));
        assertFalse(playerFiguresUnderTest.hasFigures(4));
        playerFiguresUnderTest.addNewFigure();
        playerFiguresUnderTest.newTurn();
        assertEquals(6, playerFiguresUnderTest.getTotalFigures());
        assertTrue(playerFiguresUnderTest.takeFigures(6));
        System.out.println(playerFiguresUnderTest.state());
    }

}
