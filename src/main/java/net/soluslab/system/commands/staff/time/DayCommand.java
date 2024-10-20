package net.soluslab.system.commands.staff.time;

import net.soluslab.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DayCommand implements CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String strings, @NotNull String[] args) {
        setTimeAllWorlds(1000);
        s.sendMessage(Main.get_prefix() + "The time has been set to §aday");
        return true;
    }

    public void setTimeAllWorlds(long time) {
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
    }
}
