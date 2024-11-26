package sk.uniba.fmph.dcs.stone_age;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class PlayerTools {
    //TOOLS 0-2 REPRESENTS REGULAR TOOLS, TOOLS 3-5 REPRESENTS ONE-USE TOOLS.
    private int[] tools;
    private boolean[] used;
    public PlayerTools(){
        tools = new int[6];
        used = new boolean[6];
    }
    public void newTurn(){
        for(int i=0;i<6;i++){
            used[i]=false;
        }
    }
    public void addTool(){
        for(int i=1;i<3;i++)
            if(tools[i]<tools[i-1]) {
                tools[i]++;
                return;
            }
        if(tools[0]==4)
            return;
        tools[0]++;
        return;
    }
    public void addSingleUseTool(int strength){
        for(int i=3;i<6;i++){
            if(tools[i]==0) {
                tools[i] = strength;
                return;
            }
        }
    }
    public OptionalInt useTool(int i){
        if(used[i])
            return OptionalInt.empty();
        if(i<3){
            used[i]=true;
            return OptionalInt.of(tools[i]);
        }
        if(tools[i]==0)
            return OptionalInt.empty();
        OptionalInt val = OptionalInt.of(tools[i]);
        for(int j=i;j<5;j++)
            tools[j]=tools[j+1];
        return val;
    }
    public int getTools() {
        int sum = 0;
        for(int i=0;i<6;i++)
            sum+=tools[i];
        return sum;
    }
    public String state(){
        String state = "";
        for(int i=0;i<6;i++){
            if(tools[i]>0){
                String type = (i<3) ? "" : "SingleUse";
                state+=type+"Tool, index: "+i+", strength: "+tools[i]+", used: "+used[i]+'\n';
            }
        }
        return state;
    }

}
