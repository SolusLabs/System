package de.lukas.system;

import de.lukas.system.commands.BurnCommand;
import de.lukas.system.commands.EnderchestCommand;
import de.lukas.system.commands.MessageCommand;
import de.lukas.system.commands.staff.*;
import de.lukas.system.commands.staff.freeze.FreezeCommand;
import de.lukas.system.commands.staff.freeze.UnfreezeCommand;
import de.lukas.system.commands.staff.heal.HealAllCommand;
import de.lukas.system.commands.staff.heal.HealCommand;
import de.lukas.system.commands.staff.time.*;
import de.lukas.system.commands.staff.weather.RainCommand;
import de.lukas.system.commands.staff.weather.SunCommand;
import de.lukas.system.commands.staff.weather.ThunderCommand;
import de.lukas.system.commands.staff.weather.WeahterCommand;
import de.lukas.system.listener.FreezeListeners;
import de.lukas.system.util.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.management.ObjectName;
import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static String get_prefix() {
        return "§7[§cSystem§7] >> ";
    }

    public static DatabaseManager databaseManager = new DatabaseManager();


    @Override
    public void onEnable() {
        // Plugin startup logic
        databaseManager.connect();
        databaseManager.createFreezedPlayerTable();
        Logger.getLogger("System").info(get_prefix() + "System Plugin Enabled");
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(getCommand("healall")).setExecutor(new HealAllCommand());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand());
        Objects.requireNonNull(getCommand("repair")).setExecutor(new RepairCommand());
        Objects.requireNonNull(getCommand("rename")).setExecutor(new RenameCommand());
        Objects.requireNonNull(getCommand("message")).setExecutor(new MessageCommand());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new InvseeCommand());
        Objects.requireNonNull(getCommand("clearinventory")).setExecutor(new ClearInventoryCommand());
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new EnderchestCommand());
        // Time Controll
        Objects.requireNonNull(getCommand("time")).setExecutor(new TimeCommand());
        Objects.requireNonNull(getCommand("noon")).setExecutor(new NoonCommand());
        Objects.requireNonNull(getCommand("day")).setExecutor(new DayCommand());
        Objects.requireNonNull(getCommand("night")).setExecutor(new NightCommand());
        Objects.requireNonNull(getCommand("midnight")).setExecutor(new MidnightCommand());
        // Freeze
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeCommand());
        Objects.requireNonNull(getCommand("unfreeze")).setExecutor(new UnfreezeCommand());
        // Weather
        Objects.requireNonNull(getCommand("weather")).setExecutor(new WeahterCommand());
        Objects.requireNonNull(getCommand("sun")).setExecutor(new SunCommand());
        Objects.requireNonNull(getCommand("rain")).setExecutor(new RainCommand());
        Objects.requireNonNull(getCommand("thunder")).setExecutor(new ThunderCommand());
        // Fun & Troll
        Objects.requireNonNull(getCommand("burn")).setExecutor(new BurnCommand());
        Bukkit.getPluginManager().registerEvents(new FreezeListeners(), this);

        // Unregister Commands
        UnregisterCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        databaseManager.close();
    }

    public void UnregisterCommands() {
        CommandMap commandMap = getServer().getCommandMap();
        Objects.requireNonNull(commandMap.getCommand("help")).unregister(commandMap);
    }
}
