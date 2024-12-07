package sk.uniba.fmph.dcs.player_board;

import org.junit.Before;
import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerResourcesAndFoodTest {

    private PlayerResourcesAndFood playerResourcesAndFoodUnderTest;

    @Before
    public void setUp() {
        playerResourcesAndFoodUnderTest = new PlayerResourcesAndFood();
    }

    @Test
    public void testHasResources() {
        // Setup
        // Run the test
        final boolean result = playerResourcesAndFoodUnderTest.hasResources(List.of(Effect.FOOD));

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testTakeResources() {
        // Setup
        // Run the test
        final boolean result = playerResourcesAndFoodUnderTest.takeResources(List.of(Effect.FOOD));

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testGiveResources() {
        // Setup
        // Run the test
        final boolean result = playerResourcesAndFoodUnderTest.giveResources(List.of(Effect.FOOD));

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testNumberOfResourcesForFinalPoints() {
        // Setup
        // Run the test
        final int result = playerResourcesAndFoodUnderTest.numberOfResourcesForFinalPoints();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testTakeFoodAway() {
        // Setup
        // Run the test
        playerResourcesAndFoodUnderTest.takeFoodAway();

        // Verify the results
    }

    @Test
    public void testState() {
        // Setup
        // Run the test
        final String result = playerResourcesAndFoodUnderTest.state();

        // Verify the results
        assertEquals("result", result);
    }
}
