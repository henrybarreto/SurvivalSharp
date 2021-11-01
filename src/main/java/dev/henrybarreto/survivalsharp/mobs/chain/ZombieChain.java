package dev.henrybarreto.survivalsharp.mobs.chain;

import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import dev.henrybarreto.survivalsharp.mobs.effects.*;

public class ZombieChain extends Chain {

    public ZombieChain(Player player) {
        super(player);
    }

    @Override
    public boolean handleMob(Mob mob) {
        if(mob instanceof Zombie) {
            Effect zombieEffect = new ZombieEffect();
            zombieEffect.addEffect(this.player);
            return true;
        } else {
            return this.handleNextMob(mob);
        }
    }
}
