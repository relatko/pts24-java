package sk.uniba.fmph.dcs.stone_age;

import java.util.Collection;

public class CivilisationCard {
    private Collection<ImmediateEffect> immediateEffect;
    private Collection<EndOfGameEffect> endOfGameEffect;

    public CivilisationCard(Collection<ImmediateEffect> immediateEffect, Collection<EndOfGameEffect> endOfGameEffect){
        this.immediateEffect = immediateEffect;
        this.endOfGameEffect = endOfGameEffect;
    }

    public Collection<ImmediateEffect> getImmediateEffect() {
        return immediateEffect;
    }

    public Collection<EndOfGameEffect> getEndOfGameEffect(){
        return endOfGameEffect;
    }
}
