package sk.uniba.fmph.dcs.player_board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;

public class PlayerBoardFacade implements InterfacePlayerBoardGameBoard,InterfaceNewTurn,InterfaceFeedTribe {
    private PlayerBoard playerBoard;
    public PlayerBoardFacade(){
        this.playerBoard = new PlayerBoard();
    }
    @Override
    public void giveEffect(Collection<Effect> stuff){
        List<Effect> resources = new ArrayList<Effect>();
        for(Effect effect: stuff){
            switch (effect){
                case FIELD -> playerBoard.tribeFedStatus.addField();
                case BUILDING -> playerBoard.addHouse();
                case TOOL -> playerBoard.playerT.addTool();
                case ONE_TIME_TOOL2 -> playerBoard.playerT.addSingleUseTool(2);
                case ONE_TIME_TOOL3 -> playerBoard.playerT.addSingleUseTool(3);
                case ONE_TIME_TOOL4 -> playerBoard.playerT.addSingleUseTool(4);
                default -> resources.add(effect);
            }
        }
        playerBoard.playerRF.giveResources(resources);
    }
    @Override
    public void giveEndOfGameEffect(Collection<EndOfGameEffect> stuff){
        playerBoard.playerCC.addEndOfGameEffects(stuff);
    }
    @Override
    public boolean takeResources(Collection<Effect> stuff){
        return playerBoard.playerRF.takeResources(stuff);
    }
    @Override
    public boolean takeFigures(int count){
        return playerBoard.playerFig.takeFigures(count);
    }
    @Override
    public boolean hasFigures(int count){
        return playerBoard.playerFig.hasFigures(count);
    }
    @Override
    public boolean hasSufficientTools(int goal){
        return playerBoard.playerT.hasSufficientTools(goal);
    }
    @Override
    public OptionalInt useTool(int idx){
        return playerBoard.playerT.useTool(idx);
    }
    @Override
    public void newTurn(){
        playerBoard.playerT.newTurn();
        playerBoard.playerFig.newTurn();
        playerBoard.tribeFedStatus.newTurn();
    }
    @Override
    public boolean feedTribeIfEnoughFood(){
        return playerBoard.tribeFedStatus.feedTribeIfEnoughFood();
    }
    @Override
    public boolean feedTribe(Collection<Effect> resources){
        return playerBoard.tribeFedStatus.feedTribe(resources);
    }
    @Override
    public boolean isTribeFed(){
        return playerBoard.tribeFedStatus.isTribeFed();
    }
    @Override
    public boolean doNotFeedThisTurn(){
        if(playerBoard.tribeFedStatus.setTribeFed()){
            playerBoard.takePenalty();
            return true;
        }
        return false;
    }
    @Override
    public void giveFigure(){
        playerBoard.playerFig.addNewFigure();
    }
}
