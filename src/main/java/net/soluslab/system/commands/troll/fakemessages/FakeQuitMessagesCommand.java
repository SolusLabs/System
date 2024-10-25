package net.soluslab.system.commands.troll.fakemessages;

import net.soluslab.system.Main;
import net.soluslab.system.util.ConfigurationManager;
import net.soluslab.system.util.FunctionsLib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeQuitMessagesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(ConfigurationManager.get_boolean("fake-quit.enabled")) {
            if (sender instanceof Player p) {
                if (args.length == 1) {
                    String fname = args[0];
                    FunctionsLib.BroadcastServer(ConfigurationManager.get_string("prefix") + ConfigurationManager.get_string("player-quit.text-start") + fname + ConfigurationManager.get_string("player-quit.text-end"));
                    return true;
                } else {
                    p.sendMessage(ConfigurationManager.get_string("prefix") + "§cUsage: /fakejoin <name>");
                    return false;
                }
            } else {
                sender.sendMessage(ConfigurationManager.get_string("prefix") + ConfigurationManager.get_string("not-a-player"));
            }
        } else {
            sender.sendMessage(ConfigurationManager.get_string("prefix") + ConfigurationManager.get_string("disabled-command"));
            return false;
        }
        return false;
    }
}
