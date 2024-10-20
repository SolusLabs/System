package de.lukas.system.commands.staff.heal;

import de.lukas.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HealCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String strings, @NotNull String[] args) {
        if (args.length == 0) {
            if (!(s instanceof Player)) {
                s.sendMessage(Main.get_prefix() + "Usage: /heal <user>");
                return true;
            }
            Player p = (Player) s;
            p.setHealth(p.getMaxHealth());
            p.setFoodLevel(20);
            p.sendMessage(Main.get_prefix() + "You are now healthy!");
            return true;
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                s.sendMessage(Main.get_prefix() + "Player not found");
                return true;
            }
            t.setHealth(t.getMaxHealth());
            t.setFoodLevel(20);
            t.sendMessage(Main.get_prefix() + "You are now healthy! Thanks to §a" + s.getName());
            s.sendMessage(Main.get_prefix() + "§a" + t.getName() + "§7 has been healed");
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1) {
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
