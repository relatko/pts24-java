package sk.uniba.fmph.dcs.player_board;

import org.junit.Before;
import org.junit.Test;

import java.util.OptionalInt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerToolsTest {

    private PlayerTools playerToolsUnderTest;

    @Before
    public void setUp() {
        playerToolsUnderTest = new PlayerTools();
    }

    @Test
    public void testNewTurn() {
        // Setup
        // Run the test
        playerToolsUnderTest.newTurn();

        // Verify the results
    }

    @Test
    public void testAddTool() {
        // Setup
        // Run the test
        playerToolsUnderTest.addTool();

        // Verify the results
    }

    @Test
    public void testAddSingleUseTool() {
        // Setup
        // Run the test
        playerToolsUnderTest.addSingleUseTool(0);

        // Verify the results
    }

    @Test
    public void testUseTool() {
        // Setup
        final OptionalInt expectedResult = OptionalInt.of(0);

        // Run the test
        final OptionalInt result = playerToolsUnderTest.useTool(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHasSufficientTools() {
        assertFalse(playerToolsUnderTest.hasSufficientTools(0));
    }

    @Test
    public void testGetTools() {
        assertEquals(0, playerToolsUnderTest.getTools());
    }

    @Test
    public void testState() {
        assertEquals("result", playerToolsUnderTest.state());
    }
}
