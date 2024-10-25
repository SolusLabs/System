package net.soluslab.system.commands.staff.broadcast;

import net.soluslab.system.Main;
import net.soluslab.system.util.FunctionsLib;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BroadcastWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            s.sendMessage(Main.get_prefix() + "Use: /wbroadcast <message>");
            return false;
        }
        if (!(s instanceof Player p)) {
            s.sendMessage(Main.get_prefix() + "You must be a player to use this command.");
            return false;
        }
        String Message = String.join(" ", args);
        World world = p.getWorld();
        FunctionsLib.BroadcastWorld(Message, world);
        return true;
    }
}
