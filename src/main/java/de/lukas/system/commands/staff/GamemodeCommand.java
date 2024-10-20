package de.lukas.system.commands.staff;

import de.lukas.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GamemodeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String strings, @NotNull String[] args) {
        if (args.length == 1) {
            if (!(s instanceof Player p)) {
                s.sendMessage(Main.get_prefix() + "Usage: /gamemode <0/1/2/3> <user>");
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "0", "survival":
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Main.get_prefix() + "You are now in §asurvival§7 mode.");
                    return true;
                case "1", "creative":
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(Main.get_prefix() + "You are now in §acreative§7 mode.");
                    return true;
                case "2", "adventure":
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(Main.get_prefix() + "You are now in §aadventure§7 mode.");
                    return true;
                case "3", "spectator":
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(Main.get_prefix() + "You are now in §aspectator§7 mode.");
                    return true;
                default:
                    s.sendMessage(Main.get_prefix() + "Usage: /gamemode <0/1/2/3> <user>");
                    return false;
            }

        } else if (args.length == 2) {
            Player t = Bukkit.getPlayer(args[1]);
            if (t == null) {
                s.sendMessage(Main.get_prefix() + "Player not found.");
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "0", "survival":
                    t.setGameMode(GameMode.SURVIVAL);
                    t.sendMessage(Main.get_prefix() + "You are now in §asurvival§7 mode.");
                    s.sendMessage(Main.get_prefix() + "You have set " + t.getName() + "'s gamemode to §asurvival.");
                    return true;
                case "1", "creative":
                    t.setGameMode(GameMode.CREATIVE);
                    t.sendMessage(Main.get_prefix() + "You are now in §acreative§7 mode.");
                    s.sendMessage(Main.get_prefix() + "You have set " + t.getName() + "'s gamemode to §acreative");
                    return true;
                case "2", "adventure":
                    t.setGameMode(GameMode.ADVENTURE);
                    t.sendMessage(Main.get_prefix() + "You are now in §aadventure§7 mode.");
                    s.sendMessage(Main.get_prefix() + "You have set " + t.getName() + "'s gamemode to §aadventure");
                    return true;
                case "3", "spectator":
                    t.setGameMode(GameMode.SPECTATOR);
                    t.sendMessage(Main.get_prefix() + "You are now in §aspectator§7 mode.");
                    s.sendMessage(Main.get_prefix() + "You have set " + t.getName() + "'s gamemode to §aspectator");
                    return true;
                default:
                    s.sendMessage(Main.get_prefix() + "Usage: /gamemode <0/1/2/3> <user>");
                    return false;
            }
        } else {
            s.sendMessage(Main.get_prefix() + "Usage: /gamemode <0/1/2/3> <user>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String strings, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("survival");
            list.add("creative");
            list.add("adventure");
            list.add("spectator");
            return list;
        } else if (args.length == 2) {
            List<String> list = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;

        } else {
            return null;
        }
    }
}
