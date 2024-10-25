package net.soluslab.system.commands.staff.ban;

import net.kyori.adventure.text.Component;
import net.soluslab.system.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import java.util.List;

public class BanCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage(Main.get_prefix() + "Player not found.");
                return false;
            }
            String Reason;
            if (args.length > 1) {
                List<String> argslist = Arrays.stream(args).toList();
                Reason = Main.get_prefix() + String.join(" ", argslist);
            } else {
                Reason = Main.get_prefix() + "You were Banned by an Administrator, no Reason provided.";
            }
            t.kick(Component.text(Reason));
            Bukkit.getBanList(BanList.Type.NAME).addBan(t.getName(), Reason,  null, sender.getName());
            return true;
        } else {
            sender.sendMessage(Main.get_prefix() + "Usage: /ban [player] <reason>");
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
