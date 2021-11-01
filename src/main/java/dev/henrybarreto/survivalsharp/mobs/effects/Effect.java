package dev.henrybarreto.survivalsharp.mobs.effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class Effect {
    protected PotionEffectType effect;
    protected int time = 200;
    protected int intensity = 1;
    public void addEffect(Player player) {
        player.addPotionEffect(
                new PotionEffect(this.effect, this.time, this.intensity));
    }
}
