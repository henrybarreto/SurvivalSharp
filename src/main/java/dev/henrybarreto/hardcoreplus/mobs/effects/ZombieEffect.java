package dev.henrybarreto.hardcoreplus.mobs.effects;

import org.bukkit.potion.PotionEffectType;

public class ZombieEffect extends Effect {
    public ZombieEffect() {
        this.effect = PotionEffectType.WEAKNESS;
        this.time = this.time * 6;
    }
}
