package dev.henrybarreto.hardcoreplus.mobs.chain;

import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public abstract class Chain {
    public Player player;
    private Chain next;
    public Chain(Player player) {
        this.player = player;
    }
    public Chain nextMob(Chain next) {
        this.next = next;
        return next;
    }

    public abstract boolean handleMob(Mob mob);
    protected boolean handleNextMob(Mob mob) {
        if (next == null) {
            return true;
        }
        return next.handleMob(mob);
    }
}
