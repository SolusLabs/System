package net.soluslab.system.commands.staff.ban;

import net.soluslab.system.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class UnbanCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length  == 1) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (Bukkit.getBannedPlayers().contains(target)) {
                Bukkit.getBanList(BanList.Type.NAME).pardon(args[0]);
                sender.sendMessage(Main.get_prefix() + "The Player §a" + args[0] + "§7 has been unbanned.");
                return true;
            } else {
                sender.sendMessage(Main.get_prefix() + "The Player §a" + args[0] + "§7 is not banned");
                return false;
            }
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /unbann <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Bukkit.getBannedPlayers().stream().map(OfflinePlayer::getName).toList();
    }
}
