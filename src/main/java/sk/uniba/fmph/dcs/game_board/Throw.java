package sk.uniba.fmph.dcs.game_board;

public final class Throw {
    public int[] ThrowDice(int dice) {
        int [] results = new int[dice];
        for (int i = 0; i < results.length; i++) {
            results[i] = (int) (Math.random() * 6) + 1;
        }
        return results;
    }
}
