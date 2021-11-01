package dev.henrybarreto.survivalsharp.mobs.chain;

import dev.henrybarreto.survivalsharp.mobs.effects.Effect;
import dev.henrybarreto.survivalsharp.mobs.effects.SilverfishEffect;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Zombie;

public class SilverfishChain extends Chain {

    public SilverfishChain(Player player) {
        super(player);
    }

    @Override
    public boolean handleMob(Mob mob) {
        if(mob instanceof Silverfish) {
            Effect silverfishEffect = new SilverfishEffect();
            silverfishEffect.addEffect(this.player);
            return true;
        } else {
            return this.handleNextMob(mob);
        }
    }
}
