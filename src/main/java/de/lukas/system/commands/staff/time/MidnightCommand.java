package de.lukas.system.commands.staff.time;

import de.lukas.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MidnightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command command, @NotNull String stringr, @NotNull String[] strings) {
        setTimeAllWorlds(18000);
        s.sendMessage(Main.get_prefix() + "The time has been set to §amidnight");
        return true;
    }

    public void setTimeAllWorlds(long time) {
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
    }

}