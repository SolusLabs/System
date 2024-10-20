package net.soluslab.system.commands.staff;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RepairCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String strings, @NotNull String[] args) {
        if (args.length == 0) {
            if (s instanceof Player) {
                Player p = (Player) s;
                ItemStack item = p.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();

                if (meta instanceof Damageable) {
                    Damageable damageable = (Damageable) meta;
                    damageable.damage(0);
                    item.setItemMeta((ItemMeta) damageable);
                    p.sendMessage(Main.get_prefix() + "The Item has been §arepaired");
                    return true;
                } else {
                    p.sendMessage(Main.get_prefix() + "This Item Can't be §crepaired");
                    return false;
                }
            } else {
                s.sendMessage(Main.get_prefix() + "You need to be a player! Use: /repair <user>");
                return false;
            }
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                ItemStack item = t.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();

                if (meta instanceof Damageable) {
                    Damageable damageable = (Damageable) meta;
                    damageable.damage(0);
                    item.setItemMeta((ItemMeta) damageable);
                    t.sendMessage(Main.get_prefix() + "The item in your hand has been §arepaired");
                    s.sendMessage(Main.get_prefix() + "The item in §a" + t.getName() + "§7's hand has been §arepaired");
                    return true;
                } else {
                    s.sendMessage(Main.get_prefix() + "The item in §a" + t.getName() + "§7's hand can't be §arepaired!");
                    return false;
                }
            } else {
                s.sendMessage(Main.get_prefix() + "The §aplayer§7 you a looking for couldn't be found");
                return false;
            }
        } else {
            s.sendMessage(Main.get_prefix() + "Usage: /repair <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String strings, @NotNull String[] args) {
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
