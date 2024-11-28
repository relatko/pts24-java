package sk.uniba.fmph.dcs.player_board;

public class PlayerFigures {
    private int totalFigures;
    private int figures;
    public PlayerFigures(){
        totalFigures = 5;
        figures = 5;
    }
    public void addNewFigure(){
        if(totalFigures<10)
            totalFigures++;
    }
    public boolean hasFigures(int count){
        return figures>=count;
    }
    public boolean takeFigures(int count){
        if (figures>=count){
            figures-=count;
            return true;
        }
        return false;
    }
    public int getTotalFigures(){
        return totalFigures;
    }
    public void newTurn(){
        figures = totalFigures;
    }
    public String state(){
        return "Number of free figures: "+figures+", Total number of figures: "+totalFigures+'\n';
    }
}
