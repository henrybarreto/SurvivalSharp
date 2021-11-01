package dev.henrybarreto.survivalsharp.mobs.effects;

import org.bukkit.potion.PotionEffectType;

public class PhantomEffect extends Effect {
    public PhantomEffect() {
        this.effect = PotionEffectType.BLINDNESS;
        this.time = this.time * 3;
    }
}
