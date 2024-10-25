package net.soluslab.system.commands.troll.fakemessages;

import net.soluslab.system.Main;
import net.soluslab.system.util.ConfigurationManager;
import net.soluslab.system.util.FunctionsLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeJoinMessagesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(ConfigurationManager.get_boolean("fake-join.enabled")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 1) {
                    String fname = args[0];
                    FunctionsLib.BroadcastServer(ConfigurationManager.get_string("prefix") + ConfigurationManager.get_string("player-join.text-start") + fname + ConfigurationManager.get_string("player-join.text-end"));
                } else {
                    p.sendMessage(ConfigurationManager.get_string("prefix") + "Usage: /fakejoin <name>");
                }
            }
        } else {
            Player p = (Player) sender;
            p.sendMessage(ConfigurationManager.get_string("prefix") + ConfigurationManager.get_string("disabled-command"));
        }
        return true;
    }
}
