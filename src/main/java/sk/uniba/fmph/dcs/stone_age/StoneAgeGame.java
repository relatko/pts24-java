package sk.uniba.fmph.dcs.stone_age;

public class StoneAgeGame implements InterfaceStoneAgeGame {
    @Override
    public boolean placeFigures(int playerId, Location location, int figuresCount) {
        return false;
    }

    @Override
    public boolean makeAction(int playerId, Location location, Effect[] usedResources, Effect[] desiredResources) {
        return false;
    }

    @Override
    public boolean skipAction(int playerId, Location location) {
        return false;
    }

    @Override
    public boolean useTools(int playerId, int toolIndex) {
        return false;
    }

    @Override
    public boolean noMoreToolsThisThrow(int playerId) {
        return false;
    }

    @Override
    public boolean feedTribe(int playerId, Effect[] resources) {
        return false;
    }

    @Override
    public boolean doNotFeedThisTurn(int playerId) {
        return false;
    }

    @Override
    public boolean makeAllPlayersTakeARewardChoice(int playerId, Effect reward) {
        return false;
    }
}
