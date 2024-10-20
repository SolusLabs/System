package net.soluslab.system.listener;

import net.soluslab.system.Main;
import net.soluslab.system.util.DatabaseManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListeners implements Listener {
    DatabaseManager databaseManager = Main.databaseManager;
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (databaseManager.getFreezedPlayers().contains(event.getPlayer().getUniqueId().toString())) {
            event.setCancelled(true);
            if (event.getPlayer().isFlying()) {
                event.getPlayer().setFlying(true);
            }
            event.getPlayer().sendMessage(Main.get_prefix() + "§aSorry§7, you can't move! You are freezed!");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (databaseManager.getFreezedPlayers().contains(event.getPlayer().getUniqueId().toString())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Main.get_prefix() + "§aSorry§7, you can't break stuff! You are freezed!");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (databaseManager.getFreezedPlayers().contains(event.getPlayer().getUniqueId().toString())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Main.get_prefix() + "§aSorry§7, you can't place stuff! You are freezed!");
        }
    }
}
