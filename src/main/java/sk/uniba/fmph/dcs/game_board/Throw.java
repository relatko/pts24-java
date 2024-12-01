package sk.uniba.fmph.dcs.game_board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Throw {
    private static final int MAX_DICE_VAL = 6;

    public static Collection<Integer> throw_dices(int dices){
        List<Integer> res = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<dices;i++)
            res.add(random.nextInt(MAX_DICE_VAL)+1);
        return res;
    }
}
