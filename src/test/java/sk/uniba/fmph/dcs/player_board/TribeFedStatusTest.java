package sk.uniba.fmph.dcs.player_board;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TribeFedStatusTest {

    @Mock
    private PlayerResourcesAndFood mockPlayerResourcesAndFood;
    @Mock
    private PlayerFigures mockPlayerFigures;

    private TribeFedStatus tribeFedStatusUnderTest;

    @Before
    public void setUp() {
        tribeFedStatusUnderTest = new TribeFedStatus(mockPlayerResourcesAndFood, mockPlayerFigures);
    }

    @Test
    public void testAddField() {
        // Setup
        // Run the test
        tribeFedStatusUnderTest.addField();

        // Verify the results
    }

    @Test
    public void testNewTurn() {
        // Setup
        // Run the test
        tribeFedStatusUnderTest.newTurn();

        // Verify the results
    }

    @Test
    public void testAddFoodForFields() {
        // Setup
        // Run the test
        tribeFedStatusUnderTest.addFoodForFields();

        // Verify the results
        verify(mockPlayerResourcesAndFood).giveResources(List.of(Effect.FOOD));
    }

    @Test
    public void testFeedTribeIfEnoughFood() {
        // Setup
        when(mockPlayerFigures.getTotalFigures()).thenReturn(0);
        when(mockPlayerResourcesAndFood.takeResources(List.of(Effect.FOOD))).thenReturn(false);

        // Run the test
        final boolean result = tribeFedStatusUnderTest.feedTribeIfEnoughFood();

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testFeedTribeIfEnoughFood_PlayerResourcesAndFoodReturnsTrue() {
        // Setup
        when(mockPlayerFigures.getTotalFigures()).thenReturn(0);
        when(mockPlayerResourcesAndFood.takeResources(List.of(Effect.FOOD))).thenReturn(true);

        // Run the test
        final boolean result = tribeFedStatusUnderTest.feedTribeIfEnoughFood();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testFeedTribe() {
        // Setup
        when(mockPlayerFigures.getTotalFigures()).thenReturn(0);
        when(mockPlayerResourcesAndFood.takeResources(List.of(Effect.FOOD))).thenReturn(false);

        // Run the test
        final boolean result = tribeFedStatusUnderTest.feedTribe(List.of(Effect.FOOD));

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testFeedTribe_PlayerResourcesAndFoodReturnsTrue() {
        // Setup
        when(mockPlayerFigures.getTotalFigures()).thenReturn(0);
        when(mockPlayerResourcesAndFood.takeResources(List.of(Effect.FOOD))).thenReturn(true);

        // Run the test
        final boolean result = tribeFedStatusUnderTest.feedTribe(List.of(Effect.FOOD));

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsTribeFed() {
        assertFalse(tribeFedStatusUnderTest.isTribeFed());
    }

    @Test
    public void testSetTribeFed() {
        // Setup
        // Run the test
        final boolean result = tribeFedStatusUnderTest.setTribeFed();

        // Verify the results
        assertTrue(result);
        verify(mockPlayerResourcesAndFood).takeFoodAway();
    }

    @Test
    public void testGetFields() {
        assertEquals(0, tribeFedStatusUnderTest.getFields());
    }

    @Test
    public void testState() {
        assertEquals("Tribe is fed\n", tribeFedStatusUnderTest.state());
    }
}
