package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

public interface EvaluateCivilisationCardImmediateEffect {
    Boolean performEffect(Player player, Effect choice);
}
