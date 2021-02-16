package dev.henrybarreto.hardcoreplus;

import dev.henrybarreto.hardcoreplus.mobs.chain.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HardcorePlusEvents implements Listener {
    public static final double MULTIPLIER_MOB_DAMAGAE = 4.5;
    public static final double MULTIPLIER_MOB_DMG_TAKEN = 2.5;
    public static final int MULTIPLIER_XP_GAIN = 2;
    public static final int MULTIPLIER_FOOD_GAIN = 3;

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setDamage(
                    event.getDamage() * MULTIPLIER_MOB_DAMAGAE
            );
        } else {
            event.setDamage(
                    event.getDamage() / (MULTIPLIER_MOB_DMG_TAKEN)
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
    public void PlayerExpChangeEvent(PlayerExpChangeEvent event) {
        event.setAmount(
                event.getAmount() / MULTIPLIER_XP_GAIN
        );
    }
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        List<ItemStack> drops = event.getDrops();
        drops.clear();
    }
}
