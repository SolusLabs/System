package de.lukas.system.commands.staff.time;

import de.lukas.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TimeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "set":
                    switch (args[1]) {
                        case "day":
                            setTimeAllWorlds(1000);
                            s.sendMessage(Main.get_prefix() + "The time has been set to §aday");
                            return true;
                        case "night":
                            setTimeAllWorlds(13000);
                            s.sendMessage(Main.get_prefix() + "The time has been set to §anight");
                            return true;
                        case "noon":
                            setTimeAllWorlds(6000);
                            s.sendMessage(Main.get_prefix() + "The time has been set to §anoon");
                            return true;
                        case "midnight":
                            setTimeAllWorlds(18000);
                            s.sendMessage(Main.get_prefix() + "The time has been set to §amidnight");
                            return true;
                        default:
                            try {
                                setTimeAllWorlds(Integer.parseInt(args[1]));
                                s.sendMessage(Main.get_prefix() + "The time has been changed to §a" + args[1]);
                                return true;
                            } catch (Exception e) {
                                s.sendMessage(Main.get_prefix() + "Use: /time set <noon/day/night/midnight>");
                                return false;
                            }
                    }
                case "add":
                    try {
                        addTimeAllWorlds(Integer.parseInt(args[1]));
                        s.sendMessage(Main.get_prefix() + "§a" + args[1] + " has been added to the clock!");
                        return true;
                    } catch (Exception e) {
                        s.sendMessage(Main.get_prefix() + "Use: /time add <int>");
                        return false;
                    }
                case "query":
                    World world = Bukkit.getWorld(args[1]);
                    if (world == null) {
                        s.sendMessage(Main.get_prefix() + "World not found!");
                        return false;
                    } else {
                        s.sendMessage(Main.get_prefix() + "The current time in world §a" + world.getName() + " is §6" + world.getTime());
                        return true;
                    }
                default:
                    s.sendMessage(Main.get_prefix() + "Use: /time <set/add/query>");
                    return true;
            }
        } else {
            s.sendMessage(Main.get_prefix() + "Usage: /time <set/add/query>");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("set");
            list.add("add");
            list.add("query");
            return list;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("set")) {
                List<String> list = new ArrayList<>();
                list.add("noon");
                list.add("day");
                list.add("night");
                list.add("midnight");
                return list;
            }
        }
        return null;
    }

    public void setTimeAllWorlds(long time) {
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
    }

    public void addTimeAllWorlds(long time) {
        for (World world : Bukkit.getWorlds()) {
            long currentTime = world.getTime();
            world.setTime(currentTime + time);
        }
    }
}
