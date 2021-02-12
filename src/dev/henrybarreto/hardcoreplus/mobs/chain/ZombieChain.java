package dev.henrybarreto.hardcoreplus.mobs.chain;

import dev.henrybarreto.hardcoreplus.mobs.effects.ZombieEffect;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class ZombieChain extends Chain {

    public ZombieChain(Player player) {
        super(player);
    }

    @Override
    public boolean handleMob(Mob mob) {
        if(mob instanceof Zombie) {
            ZombieEffect zombieEffect = new ZombieEffect();
            zombieEffect.addEffect(this.player);
            return true;
        } else {
            return this.handleNextMob(mob);
        }
    }
}
