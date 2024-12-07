package sk.uniba.fmph.dcs.player_board;

import org.junit.Before;
import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerCivilisationCardsTest {

    private PlayerCivilisationCards playerCivilisationCardsUnderTest;

    @Before
    public void setUp() {
        playerCivilisationCardsUnderTest = new PlayerCivilisationCards();
    }

    @Test
    public void testAddEndOfGameEffects() {
        // Setup
        // Run the test
        playerCivilisationCardsUnderTest.addEndOfGameEffects(List.of(EndOfGameEffect.FARMER));

        // Verify the results
    }

    @Test
    public void testCalculateEndOfGameCivilisationCardPoints() {
        // Setup
        // Run the test
        final int result = playerCivilisationCardsUnderTest.calculateEndOfGameCivilisationCardPoints(0, 0, 0, 0);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testState() {
        // Setup
        // Run the test
        final String result = playerCivilisationCardsUnderTest.state();

        // Verify the results
        assertEquals("result", result);
    }
}
