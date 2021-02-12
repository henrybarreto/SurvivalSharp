package dev.henrybarreto.hardcoreplus;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Helper {
    public static void addEffect(Player player, PotionEffectType potionType, int multiplier) {
        player.
            addPotionEffect(
                new PotionEffect(potionType,
                multiplier * 20,
                1));
    }
}
