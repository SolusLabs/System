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

public class MessageCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 2) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                String message = String.join(" ", args);
                t.sendMessage("§a" + s.getName() + ":§7 " + message);
                return true;
            } else {
                s.sendMessage(Main.get_prefix() + "Player not found");
                return false;
            }
        } else {
            s.sendMessage(Main.get_prefix() + "Usage: /msg <player> <message>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        }
        return null;
    }
}
