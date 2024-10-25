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

public class FlyCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String strings, @NotNull String[] args) {
        if (args.length == 0) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (p.isFlying()) {
                    p.setFlying(false);
                    p.sendMessage(Main.get_prefix() + "Returning to the Ground!");
                    return true;
                } else {
                    p.setAllowFlight(true);
                    p.sendMessage(Main.get_prefix() + "You can Fly now!");
                    return true;
                }
            } else {
                s.sendMessage(Main.get_prefix() + "Usage: /fly <user>");
                return false;
            }
        } else if (args.length == 1) {
            if (s instanceof Player) {
                Player t = Bukkit.getPlayer(args[0]);
                if (t == null) {
                    s.sendMessage(Main.get_prefix() + "Player not found");
                    return false;
                }
                if (t.isFlying()) {
                    t.setAllowFlight(false);
                    t.setFlying(false);
                    t.sendMessage(Main.get_prefix() + "Returning to the Ground!");
                    s.sendMessage(Main.get_prefix() + "§a " + t.getName() + " has been bolted to the ground!");
                    return true;
                } else {
                    t.setAllowFlight(true);
                    t.setFlying(true);
                    t.sendMessage(Main.get_prefix() + "You can fly now! Thank §a" + s.getName());
                    s.sendMessage(Main.get_prefix() + "You successfully set " + t.getName() + "flying!");
                    return true;
                }
            }
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
