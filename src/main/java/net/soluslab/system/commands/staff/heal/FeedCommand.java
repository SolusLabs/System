package net.soluslab.system.commands.staff.heal;

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

public class FeedCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player p )) {
                sender.sendMessage(Main.get_prefix() + "Usage: /heal <player>");
                return false;
            }
            p.setFoodLevel(20);
            p.setSaturation(20);
            p.sendMessage(Main.get_prefix() + "You have been §afed§7!");
            return true;
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage(Main.get_prefix() + "Player not found!");
                return false;
            }
            t.setFoodLevel(20);
            t.setSaturation(20);
            t.sendMessage(Main.get_prefix() + "You have been §afed§7!");
            if (t.getName().equals(sender.getName())) {
                sender.sendMessage(Main.get_prefix() + "Successfully fed §a" + t.getName() + "§7!");
            } else {
                sender.sendMessage(Main.get_prefix() + "You were Successfully fed! You could have just used /feed");
            }
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /feed <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            list.add(p.getName());
        }
        return list;
    }
}
