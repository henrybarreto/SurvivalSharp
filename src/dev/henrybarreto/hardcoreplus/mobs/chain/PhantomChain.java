package dev.henrybarreto.hardcoreplus.mobs.chain;

import dev.henrybarreto.hardcoreplus.mobs.effects.PhantomEffect;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;

public class PhantomChain extends Chain {

    public PhantomChain(Player player) {
        super(player);
    }

    @Override
    public boolean handleMob(Mob mob) {
        if(mob instanceof Phantom) {
            PhantomEffect phantomEffect = new PhantomEffect();
            phantomEffect.addEffect(this.player);
            return true;
        } else {
            return this.handleNextMob(mob);
        }
    }
}
