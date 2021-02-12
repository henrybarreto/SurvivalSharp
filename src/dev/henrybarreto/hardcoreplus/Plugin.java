package dev.henrybarreto.hardcoreplus;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("HardcorePlus is enabled!");
        getServer()
            .getPluginManager()
            .registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        getLogger()
            .info("HardcorePlus is disabled!");
    }
}
