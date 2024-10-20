package net.soluslab.system.commands.staff;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InvseeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage(Main.get_prefix() + "Player not found.");
                return false;
            }
            if (!(sender instanceof Player p)) {
                sender.sendMessage(Main.get_prefix() + "Only players can use this command.");
                return false;
            }
            if (t == p) {
                sender.sendMessage(Main.get_prefix() + "You cannot use this command on Yourself. Just Open your inventory");
                return false;
            }
            final Inventory inv;

            inv = Bukkit.getServer().createInventory(p, 9, t.getName() + "'s Inventory");
            inv.setContents(p.getInventory().getArmorContents());
            p.closeInventory();
            p.openInventory(t.getInventory());
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /invsee <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        }
        return null;
    }
}
