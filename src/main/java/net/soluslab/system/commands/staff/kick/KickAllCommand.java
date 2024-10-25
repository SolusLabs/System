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

public class KickAllCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            List<String> args2 = Arrays.stream(args).toList();
            args2.removeFirst();
            String Reason = String.join(" ", args2);
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!(p.getName().equals(sender.getName())) && p.hasPermission("system.staff.kick.all.bypass")) {
                    p.kick(Component.text(Reason));
                }
            }
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /kickall <reason>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of("<reason>");
    }
}
