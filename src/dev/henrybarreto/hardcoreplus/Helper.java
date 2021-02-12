package dev.henrybarreto.hardcoreplus;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Helper {
    public static void addEffect(Player player, PotionEffectType potionType, double multiplier) {
        player.
            addPotionEffect(
                new PotionEffect(potionType,
                (int) multiplier * 450,
                1));
    }
}
