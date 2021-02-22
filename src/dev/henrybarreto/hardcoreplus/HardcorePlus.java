package dev.henrybarreto.hardcoreplus;

import dev.henrybarreto.hardcoreplus.commands.HdpCommand;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class HardcorePlus extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        Logger logger= getLogger();
        Server server = getServer();
        PluginManager manager = server.getPluginManager();
        PluginCommand commandHdp = getCommand("hdp");

        manager.registerEvents(new HardcorePlusEvents(), this);
        commandHdp.setExecutor(new HdpCommand());

        logger.info("HardcorePlus is enabled!");
    }

    @Override
    public void onDisable() {
        getLogger()
            .info("HardcorePlus is disabled!");
    }
}
