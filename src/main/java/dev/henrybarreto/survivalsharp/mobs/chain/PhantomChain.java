package dev.henrybarreto.survivalsharp.mobs.chain;

import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import dev.henrybarreto.survivalsharp.mobs.effects.*;

public class PhantomChain extends Chain {

    public PhantomChain(Player player) {
        super(player);
    }

    @Override
    public boolean handleMob(Mob mob) {
        if(mob instanceof Phantom) {
            Effect phantomEffect = new PhantomEffect();
            phantomEffect.addEffect(this.player);
            return true;
        } else {
            return this.handleNextMob(mob);
        }
    }
}
