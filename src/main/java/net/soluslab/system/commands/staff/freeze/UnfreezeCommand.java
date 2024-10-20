package net.soluslab.system.commands.staff.freeze;

import net.soluslab.system.Main;
import net.soluslab.system.util.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UnfreezeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        DatabaseManager databaseManager = Main.databaseManager;
        if (args.length == 0) {
            if (!(s instanceof Player)) {
                s.sendMessage(Main.get_prefix() + "/unfreeze <player>");
                return false;
            }
            Player p = (Player) s;
            if (databaseManager.getFreezedPlayers().contains(p.getUniqueId().toString())) {
                databaseManager.removeFreezedPlayer(p.getUniqueId());
                s.sendMessage(Main.get_prefix() + "You have unfreezed yourself");
                return true;
            } else {
                s.sendMessage(Main.get_prefix() + "You are not freezed");
                return true;
            }
        } if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                s.sendMessage(Main.get_prefix() + "That player is not online");
                return false;
            }
            if (databaseManager.getFreezedPlayers().contains(t.getUniqueId().toString())) {
                databaseManager.removeFreezedPlayer(t.getUniqueId());
                t.sendMessage(Main.get_prefix() + "You have been §aunfreezed");
                s.sendMessage(Main.get_prefix() + "You have unfreezed §a" + t.getName());
                return true;
            } else {
                s.sendMessage(Main.get_prefix() + "§a" + t.getName() + " §7is not freezed");
                return true;
            }
        } else {
            s.sendMessage(Main.get_prefix() + "Usage: /unfreeze <player>");
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
        } else {
            return null;
        }
    }
}
