package net.soluslab.system.commands;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderchestCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Main.get_prefix() + "You must be a Player to use this command.");
            return false;
        }
        if (args.length == 0) {
            p.openInventory(p.getEnderChest());
            return true;
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage(Main.get_prefix() + "That player is not online.");
                return false;
            }
            Inventory ec = Bukkit.createInventory(null, t.getInventory().getSize(),  t.getName() + "'s Ender Chest");
            for (ItemStack item : p.getInventory().getContents()) {
                assert item != null;
                ec.addItem(item);
            }
            p.openInventory(ec);
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /ec <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }
}
