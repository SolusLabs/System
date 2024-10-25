package net.soluslab.system.commands.staff.clear;

import net.soluslab.system.Main;
import net.soluslab.system.util.FunctionsLib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClearChatCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            for (int i = 0; i<100; i++) {
                FunctionsLib.BroadcastServer("");
            }
            FunctionsLib.BroadcastServer("§7|-------------------+§cSystem§7+-------------------|");
            FunctionsLib.BroadcastServer("§7|     The chat has been cleared by a staff member.   |");
            FunctionsLib.BroadcastServer("§7|-------------------+§cSystem§7+-------------------|");
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("*")) {
                for (int i = 0; i < 100; i++) {
                    FunctionsLib.BroadcastServer("");
                }
                FunctionsLib.BroadcastServer("|-------------------+§cSystem§7+-------------------|");
                FunctionsLib.BroadcastServer("|   The chat has been cleared by a staff member.     |");
                FunctionsLib.BroadcastServer("|-------------------+§cSystem§7+-------------------|");
                return true;
            }
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage(Main.get_prefix() + "Player does not seem to be online.");
                return false;
            }
            for (int i = 0; i < 100; i++) {
                t.sendMessage("");
            }
            t.sendMessage("|-------------------+§cSystem§7+-------------------|");
            t.sendMessage("|   Your chat has been cleared by a staff member.    |");
            t.sendMessage("|-------------------+§cSystem§7+-------------------|");
            sender.sendMessage(Main.get_prefix() + "Successfully cleared §a" + t.getName() + "§7's chat.");
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /clear chat <player>");
            return true;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }
}
