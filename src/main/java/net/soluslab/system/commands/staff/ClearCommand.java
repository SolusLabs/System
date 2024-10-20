package net.soluslab.system.commands.staff;

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

public class ClearCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "chat":
                    if (args.length == 1) {
                        for (int i = 0; i<100; i++) {
                            Bukkit.broadcastMessage("");
                        }
                        Bukkit.broadcastMessage("§7|-------------------+§cSystem§7+-------------------|");
                        Bukkit.broadcastMessage("§7|     The chat has been cleared by a staff member.   |");
                        Bukkit.broadcastMessage("§7|-------------------+§cSystem§7+-------------------|");
                        return true;
                    } else if (args.length == 2) {
                        switch (args[1].toLowerCase()) {
                            case "*":
                                for (int i = 0; i<100; i++) {
                                    Bukkit.broadcastMessage("");
                                }
                                Bukkit.broadcastMessage("|-------------------+§cSystem§7+-------------------|");
                                Bukkit.broadcastMessage("|   The chat has been cleared by a staff member.   |");
                                Bukkit.broadcastMessage("|-------------------+§cSystem§7+-------------------|");
                                return true;
                            default:
                                Player t = Bukkit.getPlayer(args[1]);
                                if (t == null) {
                                    sender.sendMessage(Main.get_prefix() + "Player does not seem to be online.");
                                    return false;
                                }
                                for (int i = 0; i<100; i++) {
                                    t.sendMessage("");
                                }
                                t.sendMessage("|-------------------+§cSystem§7+-------------------|");
                                t.sendMessage("|   Your chat has been cleared by a staff member.  |");
                                t.sendMessage("|-------------------+§cSystem§7+-------------------|");
                                sender.sendMessage(Main.get_prefix() + "Successfully cleared §a" + t.getName() + "§7's chat.");
                                return true;
                        }
                    } else {
                        sender.sendMessage(Main.get_prefix() + "Usage: /clear chat <player>");
                        return true;
                    }
                case "inventory", "inv":
                    if (args.length == 1) {
                        if (!(sender instanceof Player p)) {
                            sender.sendMessage(Main.get_prefix() + "You must be a player to use this command!");
                            return false;
                        }
                        p.getInventory().clear();
                        p.sendMessage(Main.get_prefix() + "Your inventory has been §acleared§7!");
                        return true;
                    } else if (args.length == 2) {
                        switch (args[1]) {
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
                        sender.sendMessage(Main.get_prefix() + "Usage: /clear <inventory> <player>");
                        return false;
                    }
                default:
                    sender.sendMessage(Main.get_prefix() + "Usage: /clear [chat/inventory] <player>");
                    return false;
            }
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /clear [chat/inventory] <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("chat");
            list.add("inventory");
            return list;
        } else if (args.length == 2) {
            List<String> list = new ArrayList<>();
            list.add("*");
            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        } else {
            return List.of("Use: /clear [chat/inventory] <player>");
        }
    }
}
