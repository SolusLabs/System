package net.soluslab.system.commands.staff.weather;

import net.soluslab.system.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("This command can only be executed by a player!");
            return false;
        }
        p.getWorld().setThundering(false);
        p.getWorld().setStorm(true);
        p.sendMessage(Main.get_prefix() + "Weather was set to §arain§7!");
        return true;
    }
}
