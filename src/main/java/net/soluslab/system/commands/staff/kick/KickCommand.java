package net.soluslab.system.commands.staff.kick;

import net.kyori.adventure.text.Component;
import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class KickCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage(Main.get_prefix() + "Player not found");
                return false;
            }
            String Reason = null;
            if (args.length == 1) {
                Reason = "You were kicked by an Administrator";
            } else {
                List<String> args2 = Arrays.stream(args).toList();
                args2.removeFirst();
                Reason = String.join(" ", args2);
            }

            t.kick(Component.text(Reason));
            sender.sendMessage(Main.get_prefix() + "Successfully kicked " + t.getName());
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /kick <player>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        } else {
            return List.of("<reason>");
        }
    }
}
