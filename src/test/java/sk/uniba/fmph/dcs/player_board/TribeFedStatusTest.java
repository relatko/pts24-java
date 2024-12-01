package sk.uniba.fmph.dcs.player_board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import static org.junit.jupiter.api.Assertions.*;

public class TribeFedStatusTest {
    private TribeFedStatus tribeFedStatus;

    @BeforeEach
    void setUp() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        Effect[] baseResources = {Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD,
                Effect.WOOD, Effect.CLAY, Effect.STONE, Effect.GOLD};
        playerResourcesAndFood.giveResources(baseResources);

        this.tribeFedStatus = new TribeFedStatus(new PlayerFigures(), playerResourcesAndFood);
    }

    @Test
    void testInitialState() {
        assertEquals(tribeFedStatus.state(), "Tribe fed: false; Fields count: 0");
    }

    @Test
    void testAddField() {
        tribeFedStatus.addField();
        assertEquals(tribeFedStatus.state(), "Tribe fed: false; Fields count: 1");

        tribeFedStatus.addField();
        tribeFedStatus.addField();
        tribeFedStatus.addField();
        assertEquals(tribeFedStatus.state(), "Tribe fed: false; Fields count: 4");

        for (int i = 0; i < 10; i++) {
            tribeFedStatus.addField();
        }
        assertEquals(tribeFedStatus.state(), "Tribe fed: false; Fields count: 10");
    }

    @Test
    void testNewTurn() {
        tribeFedStatus.setTribeFed();
        assertEquals(tribeFedStatus.state(), "Tribe fed: true; Fields count: 0");

        tribeFedStatus.newTurn();
        assertEquals(tribeFedStatus.state(), "Tribe fed: false; Fields count: 0");
    }

    @Test
    void testFeedTribeIfEnoughFood() {
        assertTrue(tribeFedStatus.feedTribeIfEnoughFood());

        tribeFedStatus.newTurn();
        assertEquals(tribeFedStatus.state(), "Tribe fed: false; Fields count: 0");
        assertFalse(tribeFedStatus.feedTribeIfEnoughFood());
    }

    @Test
    void testFeedTribe() {
        // Not enough gold in resources
        Effect[] resources = {Effect.GOLD, Effect.GOLD, Effect.GOLD, Effect.GOLD, Effect.GOLD};
        assertFalse(tribeFedStatus.feedTribe(resources));
        // Not enough food (and/or resources) to feed the tribe
        resources = new Effect[]{Effect.FOOD, Effect.GOLD};
        assertFalse(tribeFedStatus.feedTribe(resources));
        // Enough food to feed the tribe
        resources = new Effect[]{Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD};
        assertTrue(tribeFedStatus.feedTribe(resources));
    }
}
