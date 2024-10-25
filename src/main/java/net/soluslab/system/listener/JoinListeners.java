package net.soluslab.system.listener;

import net.soluslab.system.Main;
import net.soluslab.system.util.ConfigurationManager;
import net.soluslab.system.util.FunctionsLib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        if (ConfigurationManager.get_boolean("player-join.enabled")) {
            FunctionsLib.BroadcastServer( ConfigurationManager.get_string("prefix") + ConfigurationManager.get_string("player-join.text-start") + event.getPlayer().getName() + ConfigurationManager.get_string("player-join.text-end"));
        }
    }
}
