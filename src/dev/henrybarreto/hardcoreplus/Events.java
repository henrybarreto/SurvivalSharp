package dev.henrybarreto.hardcoreplus;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Events implements Listener {
    public static final int MULT = 4;
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setDamage(
                    event.getDamage() * MULT
            );
        } else {
            event.setDamage(
                    event.getDamage() / (MULT/2)
            );
        }
    }
    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Mob) {
            Player player = (Player) event.getEntity();
            Mob mob = (Mob) event.getDamager();

            if(mob instanceof Zombie) {
                Helper.addEffect(player, PotionEffectType.WEAKNESS, MULT);
            }
            if(mob instanceof Arrow) {
                Helper.addEffect(player, PotionEffectType.CONFUSION, MULT);
            }
            if(mob instanceof Spider) {
                Helper.addEffect(player, PotionEffectType.POISON, MULT);
            }
            if(mob instanceof Phantom) {
                Helper.addEffect(player, PotionEffectType.BLINDNESS, MULT);
            }
        }
    }
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        List<ItemStack> drops = event.getDrops();
        drops.clear();
    }
}
