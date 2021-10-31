package dev.henrybarreto.hardcoreplus.mobs.chain;

import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import dev.henrybarreto.hardcoreplus.mobs.effects.*;

public class SpiderChain extends Chain {

    public SpiderChain(Player player) {
        super(player);
    }

    @Override
    public boolean handleMob(Mob mob) {
        if(mob instanceof Spider) {
            Effect spiderEffect = new SpiderEffect();
            spiderEffect.addEffect(this.player);
            return true;
        } else {
            return this.handleNextMob(mob);
        }
    }
}
