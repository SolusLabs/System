package net.soluslab.system.commands;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BurnCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                t.setFireTicks(1000 * 20);
                sender.sendMessage(Main.get_prefix() + "You successfully set " + t.getName() + " on fire!");
                return true;
            } else {
                sender.sendMessage(Main.get_prefix() + "Player not found!");
                return false;
            }
        } else if (args.length == 2) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                t.setFireTicks(Integer.parseInt(args[1]) * 20);
                sender.sendMessage(Main.get_prefix() + "You successfully set " + t.getName() + " on fire!");
                return true;
            } else {
                sender.sendMessage(Main.get_prefix() + "Player not found!");
                return false;
            }

        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /burn [player] <duration>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        } else if (args.length == 2) {
            List<String> list = new ArrayList<>();
            list.add("<duration>");
            return list;
        } else {
            return null;
        }
    }
}
