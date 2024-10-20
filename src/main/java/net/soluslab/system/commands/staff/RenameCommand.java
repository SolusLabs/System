package net.soluslab.system.commands.staff;

import net.soluslab.system.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;


public class RenameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage(Main.get_prefix() + "To use this, you must be a player.");
            return false;
        }
        Player p = (Player) s;
        ItemStack item = p.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        String newName = String.join(" ", args).replace("&", "§");
        meta.setDisplayName(newName);
        item.setItemMeta(meta);
        p.sendMessage(Main.get_prefix() + "The item you are holding has been renamed to " + newName);
        return true;
    }
}
