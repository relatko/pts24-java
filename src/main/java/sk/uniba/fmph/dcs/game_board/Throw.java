package sk.uniba.fmph.dcs.game_board;

public final class Throw {

    public int[] throwDice (final int dice) {
        int[] results = new int[dice];
        for (int i = 0; i < results.length; i++) {
            int dieFaces = 6;
            results[i] = (int) (Math.random() * dieFaces) + 1;
        }
        return results;
    }
}
