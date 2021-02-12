package dev.henrybarreto.hardcoreplus;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        Logger logger= getLogger();
        Server server = getServer();
        PluginManager manager = server.getPluginManager();
        manager.registerEvents(new HardcorePlusEvents(), this);

        logger.info("HardcorePlus is enabled!");
    }

    @Override
    public void onDisable() {
        getLogger()
            .info("HardcorePlus is disabled!");
    }
}
