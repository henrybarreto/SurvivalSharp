package dev.henrybarreto.hardcoreplus;

import dev.henrybarreto.hardcoreplus.mobs.chain.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HardcorePlusEvents implements Listener {
    public static final double MULTIPLIER_MOB_DAMAGE = 3;
    public static final double MULTIPLIER_MOB_DMG_TAKEN = 1.5;
    public static final int MULTIPLIER_XP_GAIN = 2;
    public static final int MULTIPLIER_FOOD_GAIN = 2;

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity()).getPlayer();
            double armorPlayer = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
            event.setDamage(
                    (event.getDamage() - armorPlayer) * MULTIPLIER_MOB_DAMAGE
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
    public void PlayerItemConsumeEvent(PlayerItemConsumeEvent event) {
        event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - MULTIPLIER_FOOD_GAIN);
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
