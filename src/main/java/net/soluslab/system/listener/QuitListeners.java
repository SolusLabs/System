package net.soluslab.system.listener;

import net.soluslab.system.Main;
import net.soluslab.system.util.ConfigurationManager;
import net.soluslab.system.util.FunctionsLib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListeners implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        if (ConfigurationManager.get_boolean("player-quit.enabled")) {
            FunctionsLib.BroadcastServer(ConfigurationManager.get_string("prefix") + ConfigurationManager.get_string("player-quit.text-start") + e.getPlayer().getName() + ConfigurationManager.get_string("player-quit.text-end"));
        }
    }
}
