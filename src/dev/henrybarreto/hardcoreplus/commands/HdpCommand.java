package dev.henrybarreto.hardcoreplus.commands;

import dev.henrybarreto.hardcoreplus.state.ServerState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HdpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        String arg1 = strings[0];
        String arg2 = strings[1];
        ServerState serverState = new ServerState();
        Commands commands = new Commands(arg1, arg2);
        ArrayList<Boolean> listOfCommands =  new ArrayList<Boolean>();
        listOfCommands.add(commands.check("count", "death", (arg01, arg02) -> {
            if(serverState.getDeaths() <= 1) {
                player.sendMessage(Integer.toString(serverState.getDeaths()) + " death in the server");
                return true;
            }
            player.sendMessage(Integer.toString(serverState.getDeaths()) + " deaths in the server");
            return true;
        }));
        listOfCommands.add(commands.check("count", "eggs", (arg01, arg02) -> {
            player.sendMessage("You have 100 egs now!");
            return true;
        }));

        return listOfCommands.stream().reduce(false, (subtotal, result) -> {
            if(result) {
                return true;
            } else {
                return false;
            }
        });
    }
}

interface CommandAction {
    boolean action(String arg1, String arg2);
}

class Commands {
    private String arg1;
    private String arg2;
    public Commands(String arg1, String arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    public boolean check(String arg1, String arg2, CommandAction commandAction) {
        if(this.arg1.equals(arg1) && this.arg2.equals(arg2)) {
            return commandAction.action(this.arg1, this.arg2);
        }
        return false;
    }

}