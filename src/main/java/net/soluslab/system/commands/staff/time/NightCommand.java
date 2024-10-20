package net.soluslab.system.commands.staff.time;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        setTimeAllWorlds(13000);
        commandSender.sendMessage(Main.get_prefix() + "The time has been set to §anight");
        return true;
    }

    public void setTimeAllWorlds(long time) {
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
    }

}
