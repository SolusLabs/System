package de.lukas.system.commands.staff.weather;

import de.lukas.system.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ThunderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("This command can only be executed by a player!");
            return false;
        }
        p.getWorld().setThundering(true);
        p.getWorld().setStorm(true);
        p.sendMessage(Main.get_prefix() + "Weather was set to §aThunder§7!");
        return true;
    }
}
