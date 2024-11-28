package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.InterfaceGetState;

public class PlayerBoard implements InterfaceGetState {
    private int points;
    private int houses;
    public PlayerResourcesAndFood playerRF;
    public PlayerFigures playerFig;
    public PlayerCivilisationCards playerCC;
    public PlayerTools playerT;
    public TribeFedStatus tribeFedStatus;
    public PlayerBoard(){
        points = 0;
        houses = 0;
        playerRF = new PlayerResourcesAndFood();
        playerFig = new PlayerFigures();
        playerT = new PlayerTools();
        playerCC = new PlayerCivilisationCards();
        tribeFedStatus = new TribeFedStatus(playerRF,playerFig);
    }
    public void addPoints(int points){
        this.points+=points;
    }
    public void takePenalty(){
        points-=10;
    }
    public void addHouse(){
        houses++;
    }
    public void addEndOfGamePoints(){
        points += playerCC.calculateEndOfGameCivilisationCardPoints(houses,playerT.getTools(),tribeFedStatus.getFields(),playerFig.getTotalFigures());
    }
    @Override
    public String state(){
        String state = playerFig.state()+playerT.state()+tribeFedStatus.state()+playerRF.state()+playerCC.state();
        state += "Number of points: "+points+"\n";
        state += "Number of houses: "+houses+"\n";
        return state;
    }
}
