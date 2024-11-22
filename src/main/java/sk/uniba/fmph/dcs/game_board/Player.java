package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public final class Player {
    private final PlayerOrder playerOrder;
    private final InterfacePlayerBoardGameBoard playerBoard;

    public Player(PlayerOrder playerOrder, InterfacePlayerBoardGameBoard playerBoard)
    {
        this.playerOrder = playerOrder;
        this.playerBoard = playerBoard;
    }

    public PlayerOrder getPlayerOrder() {
        return playerOrder;
    }

    public InterfacePlayerBoardGameBoard getPlayerBoard(){
        return playerBoard;
    }

    @Override
    public String toString(){
        return "Player{"+
                "order="+playerOrder+
                ", playerBoard="+playerBoard+
                "}\n";
    }
}
