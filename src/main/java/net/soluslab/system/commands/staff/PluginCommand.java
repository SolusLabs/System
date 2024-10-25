package net.soluslab.system.commands.staff;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import java.util.List;

public class PluginCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> plugin_list = Arrays.stream(Bukkit.getPluginManager().getPlugins()).map(Plugin::getName).toList();
        if (args.length == 0) {
            sender.sendMessage(Main.get_prefix() + "Loaded Plugins: " + Arrays.toString(plugin_list.toString().split(",")).replace("[", "").replace("]", ""));
        } else {
             switch (args[0]) {
                 case "list":
                     sender.sendMessage(Main.get_prefix() + "Loaded Plugins: " + Arrays.toString(plugin_list.toString().split(",")).replace("[", "").replace("]", ""));
                     return true;
                 case "reload":
                     if (!(args.length == 2)) {
                         sender.sendMessage(Main.get_prefix() + "Usage: /plugin reload <plugin_name>");
                         return false;
                     }
                     Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
                     if (plugin == null) {
                         sender.sendMessage(Main.get_prefix() + "Plugin not found: §a" + args[1]);
                         return false;
                     }
                     // Reload Plugin
                     Bukkit.getPluginManager().disablePlugin(plugin);
                     Bukkit.getPluginManager().enablePlugin(plugin);
                     sender.sendMessage(Main.get_prefix() + "Plugin was reloaded.");
                     return true;
                 case "disable":
                     if (!(args.length == 2)) {
                         sender.sendMessage(Main.get_prefix() + "Usage: /plugin disable <plugin_name>");
                         return false;
                     }
                     Plugin plugin1 = Bukkit.getPluginManager().getPlugin(args[1]);
                     if (plugin1 == null) {
                         sender.sendMessage(Main.get_prefix() + "Plugin not found: §a" + args[1]);
                         return false;
                     }
                     Bukkit.getPluginManager().disablePlugin(plugin1);
                     sender.sendMessage(Main.get_prefix() + "Plugin was §aSuccessfully §7disabled.");
                     return true;
                 case "enable":
                     if (!(args.length == 2)) {
                         sender.sendMessage(Main.get_prefix() + "Usage: /plugin enable <plugin_name>");
                         return false;
                     }
                     Plugin plugin2 = Bukkit.getPluginManager().getPlugin(args[1]);
                     if (plugin2 == null) {
                         sender.sendMessage(Main.get_prefix() + "Plugin not found: §a" + args[1]);
                         return false;
                     }
                     Bukkit.getPluginManager().enablePlugin(plugin2);
                     sender.sendMessage(Main.get_prefix() + "Plugin was §aSuccessfully §7enabled.");
                     return true;
                     // /plugin <arg0> <arg1>

             }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("list", "reload", "disable", "enable");
        } else if (args.length == 2) {
            return Arrays.stream(Bukkit.getPluginManager().getPlugins()).map(Plugin::getName).toList();
        }
        return null;
    }
}
