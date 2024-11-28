package sk.uniba.fmph.dcs.stone_age;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerResourcesAndFood {
    private Map<Effect, Integer> resources;
    public PlayerResourcesAndFood(){
        resources = new HashMap<Effect, Integer>();
        for(Effect effect: Effect.values()){
            if(effect.isResourceOrFood())
                resources.put(effect,0);
        }
        resources.put(Effect.FOOD,12);
    }
    public boolean hasResources(Collection<Effect> res){
        Map<Effect, Integer> asked_res = new HashMap<Effect, Integer>();
        for (Effect effect: res){
            if(!effect.isResourceOrFood())
                return false;
            if(asked_res.containsKey(effect))
                asked_res.put(effect,asked_res.get(effect)+1);
            else
                asked_res.put(effect,1);
        }
        for (Effect key: asked_res.keySet())
            if(asked_res.get(key)>resources.get(key))
                return false;
        return true;
    }
    public boolean takeResources(Collection<Effect> res){
        if(!hasResources(res))
            return false;
        for(Effect effect: res)
            resources.put(effect,resources.get(effect)-1);
        return true;
    }
    public boolean giveResources(Collection<Effect> res){
        for (Effect effect: res) {
            if(!effect.isResourceOrFood())
                return false;
            resources.put(effect, resources.get(effect) + 1);
        }
        return true;
    }
    public int numberOfResourcesForFinalPoints(){
        int points = 0;
        for(Effect effect: resources.keySet())
            points+=resources.get(effect)*effect.points();
        return points;
    }
    //in case when we choose 10 points hit
     public void takeFoodAway(){
        resources.put(Effect.FOOD,0);
    }
    public String state(){
        String state = "Number of resources:\n";
        for(Effect effect: resources.keySet()){
            if(resources.get(effect)>0){
                state+=effect.name()+" : "+resources.get(effect)+'\n';
            }
        }
        return state;
    }
}
