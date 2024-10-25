package net.soluslab.system.commands.staff.clear;

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

public class ClearInventoryCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(Main.get_prefix() + "You must be a player to use this command!");
                return false;
            }
            p.getInventory().clear();
            p.sendMessage(Main.get_prefix() + "Your inventory has been §acleared§7!");
            return true;
        } else if (args.length == 1) {
            switch (args[0]) {
                case "*":
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.getInventory().clear();
                        p.sendMessage(Main.get_prefix() + "Your inventory has been §acleared§7!");
                    }
                    return true;
                default:
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t != null) {
                        t.getInventory().clear();
                        t.sendMessage(Main.get_prefix() + "Your inventory has been§a cleared§7");
                        sender.sendMessage(Main.get_prefix() + "Successfully cleared §a" + t.getName() + "§7's inventory!");
                        return true;
                    } else {
                        sender.sendMessage(Main.get_prefix() + "That player is not online!");
                        return false;
                    }
            }

        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /cinv <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
          if (args.length == 1) {
              List<String> list = new ArrayList<>();
              list.add("*");
              for (Player p : Bukkit.getOnlinePlayers()) {
                  list.add(p.getName());
              }
              return list;
          } else {
              return null;
          }
    }
}
