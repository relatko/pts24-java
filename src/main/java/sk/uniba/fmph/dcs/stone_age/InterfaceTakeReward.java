package sk.uniba.fmph.dcs.stone_age;

public interface InterfaceTakeReward {
    Boolean takeReward(PlayerOrder player,  Effect reward);
    HasAction tryMakeAction(PlayerOrder player);
}
