package dev.henrybarreto.hardcoreplus;

import org.bukkit.Difficulty;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class HardcorePlus extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        Logger logger= getLogger();
        Server server = getServer();

        server.getWorlds().forEach((world) -> world.setDifficulty(Difficulty.HARD));
        logger.info("Setting the difficulty to HARD");

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
