package net.soluslab.system.commands.staff;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GetPosCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage("Use: /getpos <player>");
                return true;
            }
            Location loc = p.getLocation();
            String Message = """
                    §7[§cSystem§7] Your Current Location:
                    Current World: %s
                    X: %s
                    Y: %s
                    Z: %s
                    Yaw: %s (Rotation)
                    Pitch: %s
                    """.formatted(p.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getYaw(), loc.getPitch());
            p.sendMessage(Message);
            return true;
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage("Player not found");
                return false;
            }
            Location loc = t.getLocation();
            String Message = """
                    %s §a%s§7's Current Location:
                    Current World: %s
                    X: %s
                    Y: %s
                    Z: %s
                    Yaw: %s (Rotation)
                    Pitch: %s
                    """.formatted(Main.get_prefix(), t.getName(), t.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getYaw(), loc.getPitch());
            sender.sendMessage(Message);
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /getpos <player>");
        }
        return false;
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
