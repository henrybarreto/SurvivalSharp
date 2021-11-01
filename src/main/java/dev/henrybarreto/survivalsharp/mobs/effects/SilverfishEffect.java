package dev.henrybarreto.survivalsharp.mobs.effects;

import org.bukkit.potion.PotionEffectType;

public class SilverfishEffect extends Effect {
    public SilverfishEffect() {
        this.effect = PotionEffectType.SLOW_DIGGING;
        this.time = this.time * 6;
    }
}
