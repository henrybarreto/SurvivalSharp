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
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class SurvivalSharpEvents implements Listener {
    public HashMap<String, Integer> playerDeath;

    public final double MULTIPLIER_MOB_DAMAGE = 4;
    public final int MULTIPLIER_ITEM_DAMAGE = 4;
    public final double MULTIPLIER_MOB_DMG_TAKEN = 2;
    public final int MULTIPLIER_XP_GAIN = 2;

    @EventHandler
    public void PlayerItemDamageEvent(PlayerItemDamageEvent event) {
        Function<Integer, Integer> calculateDamageItem = (Integer damage) -> damage * MULTIPLIER_ITEM_DAMAGE;
        event.setDamage(
            calculateDamageItem.apply(event.getDamage())
        );
    }

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
        Function<Player, Boolean> isBlocking = (Player player) -> ((Player) event.getEntity()).isBlocking();
        if(event.getEntity() instanceof Player player &&
                !isBlocking.apply(player) &&
                event.getDamager() instanceof Mob mob)
        {

            Chain zombieChain  = new ZombieChain(player);
            Chain phantomChain = new PhantomChain(player);
            Chain silverfishChain = new SilverfishChain(player);

            zombieChain.nextMob(phantomChain);
            phantomChain.nextMob(silverfishChain);

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
        Consumer<Player> setDeath = (Player player) -> {
            if(playerDeath.get(player.getName()) != null) {
                playerDeath.put(
                    player.getName(), 
                    playerDeath.get(player.getName()) + 1
                );
            } else {
                playerDeath.put(player.getName(), 1);
            }
        };

        event.getDrops().clear();
        setDeath.accept(event.getEntity());
    }
}
