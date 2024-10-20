package net.soluslab.system.commands.staff.weather;

import net.soluslab.system.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WeahterCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Main.get_prefix() + "To run this, you have to be a player!");
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "clear":
                p.getWorld().setThundering(false);
                p.getWorld().setStorm(false);
                p.sendMessage(Main.get_prefix() + "The weather has been set to §aclear§7!");
                return true;
            case "thunder":
                p.getWorld().setThundering(true);
                p.getWorld().setStorm(true);
                p.sendMessage(Main.get_prefix() + "The weather has been set to §athunder§7!");
                return true;
            case "rain":
                p.getWorld().setThundering(false);
                p.getWorld().setStorm(true);
                p.sendMessage(Main.get_prefix() + "The weather has been set to §arain§7!");
                return true;
            default:
                sender.sendMessage(Main.get_prefix() + "Invalid command! Usage: /weather <clear/rain/thunder>");
                return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("clear");
            list.add("rain");
            list.add("thunder");
            return list;
        }
        return null;
    }
}
