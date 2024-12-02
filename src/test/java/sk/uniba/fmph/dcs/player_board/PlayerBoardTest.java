package sk.uniba.fmph.dcs.player_board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.CannotProceedException;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerBoardTest {

    private PlayerBoard playerBoard;

    @BeforeEach
    void setUp() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        PlayerFigures playerFigures = new PlayerFigures();
        this.playerBoard = new PlayerBoard(playerResourcesAndFood, playerFigures, new PlayerCivilisationCards(),
                new PlayerTools(), new TribeFedStatus(playerFigures, playerResourcesAndFood));
    }

    @Test
    public void testItIsNotPossibleToStartNewTurnWhenTribeIsNotFed() {
        assertThrows(CannotProceedException.class, playerBoard::newTurn);
    }

    @Test
    void testAddPoints() {
        playerBoard.addPoints(10);
        assertEquals("10,0", playerBoard.state());

        playerBoard.addPoints(5);
        assertEquals("15,0", playerBoard.state());
    }

    @Test
    void testAddHouse() {
        playerBoard.addHouse();
        assertEquals("0,1", playerBoard.state());

        playerBoard.addHouse();
        assertEquals("0,2", playerBoard.state());
    }

}
