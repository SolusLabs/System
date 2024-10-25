package net.soluslab.system.commands.staff.broadcast;

import net.soluslab.system.Main;
import net.soluslab.system.util.FunctionsLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            s.sendMessage(Main.get_prefix() + "To use this Command you have to be a player!");
            return false;
        }
        String message = "§7[§cBROADCAST§7] >>" + String.join(" ", args);
        FunctionsLib.BroadcastServer(message);
        return true;
    }
}
