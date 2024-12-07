package sk.uniba.fmph.dcs.player_board;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlayerBoardTest {

    @Mock
    private PlayerCivilisationCards mockPlayerCC;
    @Mock
    private PlayerTools mockPlayerT;
    @Mock
    private TribeFedStatus mockTribeFedStatus;
    @Mock
    private PlayerFigures mockPlayerFig;
    @Mock
    private PlayerResourcesAndFood mockPlayerRF;

    private PlayerBoard playerBoardUnderTest;

    @Before
    public void setUp() {
        playerBoardUnderTest = new PlayerBoard(mockPlayerCC, mockPlayerT, mockTribeFedStatus, mockPlayerFig,
                mockPlayerRF);
        playerBoardUnderTest.addPoints(0);
    }

    @Test
    public void testTakePenalty() {
        // Setup
        // Run the test
        playerBoardUnderTest.takePenalty();

        // Verify the results
    }

    @Test
    public void testAddHouse() {
        // Setup
        // Run the test
        playerBoardUnderTest.addHouse();

        // Verify the results
    }

    @Test
    public void testAddEndOfGamePoints() {
        // Setup
        // Run the test
        playerBoardUnderTest.addEndOfGamePoints();

        // Verify the results
    }

    @Test
    public void testState() {
        // Setup
        // Run the test
        final String result = playerBoardUnderTest.state();

        // Verify the results
        assertEquals("result", result);
    }
}
