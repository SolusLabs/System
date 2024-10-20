package net.soluslab.system.commands.staff.freeze;

import net.soluslab.system.Main;
import net.soluslab.system.util.DatabaseManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FreezeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        DatabaseManager databaseManager = Main.databaseManager;
        if (args.length == 0) {
            if (!(s instanceof Player)) {
                s.sendMessage(Main.get_prefix() + "/freeze <player>");
                return false;
            }
            Player p = (Player) s;
            if (databaseManager.getFreezedPlayers().contains(p.getUniqueId().toString())) {
                databaseManager.removeFreezedPlayer(p.getUniqueId());
                s.sendMessage(Main.get_prefix() + "You have §aunfreezed§7 yourself");
                s.sendActionBar(Component.text(""));
                return true;
            } else {
                databaseManager.addFreezedPlayer(p.getUniqueId());
                s.sendMessage(Main.get_prefix() + "You have §afreezed§7 yourself");
                s.sendActionBar(Component.text("§7[§cSystem§7] - §1Freezed§7 - [§cSystem§7]"));
                return true;
            }
        } if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                s.sendMessage(Main.get_prefix() + "That player is §cnot§7 online");
                return false;
            }
            if (databaseManager.getFreezedPlayers().contains(t.getUniqueId().toString())) {
                databaseManager.removeFreezedPlayer(t.getUniqueId());
                if (!(s.getName().equals(t.getName()))) {
                    t.sendMessage(Main.get_prefix() + "You have been §aunfreezed");
                    s.sendMessage(Main.get_prefix() + "You have unfreezed §a" + t.getName());
                    t.sendActionBar(Component.text(""));
                } else {
                    s.sendMessage(Main.get_prefix() + "You have §aunfreezed§7 yourself");
                }
                return true;
            } else {
                databaseManager.addFreezedPlayer(t.getUniqueId());
                if (!(s.getName().equals(t.getName()))) {
                    t.sendMessage(Main.get_prefix() + "You have been §afreezed");
                    t.sendActionBar(Component.text("§7[§cSystem§7] - §1Freezed§7 - [§cSystem§7]"));
                    s.sendMessage(Main.get_prefix() + "You have §afreezed§7 §a" + t.getName());
                } else {
                    s.sendMessage(Main.get_prefix() + "You have §afreezed§7 yourself");
                }
                return true;
            }
        } else {
            s.sendMessage(Main.get_prefix() + "Usage: /freeze <player>");
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
        } else {
            return null;
        }
    }
}
