package dev.henrybarreto.survivalsharp;

import dev.henrybarreto.survivalsharp.mobs.chain.*;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.function.Function;

public class SurvivalSharpEvents implements Listener {
    public static final double MULTIPLIER_MOB_DAMAGE = 3;
    public static final double MULTIPLIER_MOB_DMG_TAKEN = 1.5;
    public static final int MULTIPLIER_XP_GAIN = 2;

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        Function<Double, Double> calculateDamageOnPlayer = (Double damage) -> damage * MULTIPLIER_MOB_DAMAGE;
        Function<Double, Double> calculateDamageOnMob = (Double damage) -> damage / MULTIPLIER_MOB_DMG_TAKEN;
        if (event.getEntity() instanceof Player) {
            event.setDamage(
                    calculateDamageOnPlayer.apply(event.getFinalDamage())
            );
        } else {
            event.setDamage(
                    calculateDamageOnMob.apply(event.getFinalDamage())
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
        Function<Integer, Integer> calculateXPGain = (Integer xp) -> xp / MULTIPLIER_XP_GAIN;
        event.setAmount(
                calculateXPGain.apply(event.getAmount())
        );
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        event.getPlayer().getWorld().playSound(
                event.getPlayer().getLocation(),
                Sound.ENTITY_LIGHTNING_BOLT_THUNDER,
                10,
                1
        );
    }

    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        event.getDrops().clear();
    }
}
