package dev.henrybarreto.hardcoreplus;

import dev.henrybarreto.hardcoreplus.mobs.chain.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HardcorePlusEvents implements Listener {
    public static final double MULTIPLIER = 4.5;
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setDamage(
                    event.getDamage() * MULTIPLIER
            );
        } else {
            event.setDamage(
                    event.getDamage() / MULTIPLIER
            );
        }
    }
    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Mob) {
            Player player = (Player) event.getEntity();
            Mob mob = (Mob) event.getDamager();

            Chain zombieChain  = new ZombieChain(player);
            Chain spiderChain  = new SpiderChain(player);
            Chain phantomChain = new PhantomChain(player);

            zombieChain.nextMob(spiderChain);
            spiderChain.nextMob(phantomChain);

            zombieChain.handleMob(mob);
        }
    }
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        List<ItemStack> drops = event.getDrops();
        drops.clear();
    }
}
