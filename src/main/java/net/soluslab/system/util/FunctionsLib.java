package net.soluslab.system.util;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class FunctionsLib {
    public static void BroadcastServer(String Message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(Message);
        }
    }

    public static void BroadcastWorld(String Message, World world) {
        for (Player p : world.getPlayers()) {
            p.sendMessage(Message);
        }
    }

}
