package net.soluslab.system;

import net.soluslab.system.commands.*;
import net.soluslab.system.commands.staff.*;
import net.soluslab.system.commands.staff.ban.BanCommand;
import net.soluslab.system.commands.staff.ban.UnbanCommand;
import net.soluslab.system.commands.staff.broadcast.BroadcastCommand;
import net.soluslab.system.commands.staff.broadcast.BroadcastWorldCommand;
import net.soluslab.system.commands.staff.clear.ClearChatCommand;
import net.soluslab.system.commands.staff.clear.ClearCommand;
import net.soluslab.system.commands.staff.clear.ClearInventoryCommand;
import net.soluslab.system.commands.staff.freeze.FreezeCommand;
import net.soluslab.system.commands.staff.freeze.UnfreezeCommand;
import net.soluslab.system.commands.staff.heal.FeedCommand;
import net.soluslab.system.commands.staff.heal.HealAllCommand;
import net.soluslab.system.commands.staff.heal.HealCommand;
import net.soluslab.system.commands.staff.kick.KickAllCommand;
import net.soluslab.system.commands.staff.kick.KickCommand;
import net.soluslab.system.commands.staff.time.*;
import net.soluslab.system.commands.staff.weather.RainCommand;
import net.soluslab.system.commands.staff.weather.SunCommand;
import net.soluslab.system.commands.staff.weather.ThunderCommand;
import net.soluslab.system.commands.staff.weather.WeahterCommand;
import net.soluslab.system.commands.troll.fakemessages.FakeJoinMessagesCommand;
import net.soluslab.system.commands.troll.fakemessages.FakeQuitMessagesCommand;
import net.soluslab.system.listener.FreezeListeners;
import net.soluslab.system.listener.JoinListeners;
import net.soluslab.system.listener.QuitListeners;
import net.soluslab.system.util.ConfigurationManager;
import net.soluslab.system.util.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static Main instance;
        //return "§c§lSystem §8§l» §r§7";
        //return "§7[§cSystem§7] >> ";
    public static DatabaseManager databaseManager = new DatabaseManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        databaseManager.connect();
        databaseManager.createFreezedPlayerTable();
        instance = this;
        ConfigurationManager.load_static_configuration_file();
        Logger.getLogger("System").info("System Plugin Enabled");
        // Healing
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(getCommand("healall")).setExecutor(new HealAllCommand());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand());
        // Item...
        Objects.requireNonNull(getCommand("repair")).setExecutor(new RepairCommand());
        Objects.requireNonNull(getCommand("rename")).setExecutor(new RenameCommand());
        // Server Management
        Objects.requireNonNull(getCommand("plugin")).setExecutor(new PluginCommand());
        // Player Management
        Objects.requireNonNull(getCommand("kick")).setExecutor(new KickCommand());
        Objects.requireNonNull(getCommand("kickall")).setExecutor(new KickAllCommand());
        Objects.requireNonNull(getCommand("ban")).setExecutor(new BanCommand());
        Objects.requireNonNull(getCommand("unban")).setExecutor(new UnbanCommand());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand());
        Objects.requireNonNull(getCommand("getpos")).setExecutor(new GetPosCommand());
        // Freeze
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeCommand());
        Objects.requireNonNull(getCommand("unfreeze")).setExecutor(new UnfreezeCommand());
        Bukkit.getPluginManager().registerEvents(new FreezeListeners(), this);
        // Broadcast / Chat & Inventory
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new InvseeCommand());
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new EnderchestCommand());
        Objects.requireNonNull(getCommand("clearinventory")).setExecutor(new ClearInventoryCommand());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new ClearChatCommand());
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
        Objects.requireNonNull(getCommand("broadcast")).setExecutor(new BroadcastCommand());
        Objects.requireNonNull(getCommand("wbroadcast")).setExecutor(new BroadcastWorldCommand());
        Objects.requireNonNull(getCommand("message")).setExecutor(new MessageCommand());
        Bukkit.getPluginManager().registerEvents(new JoinListeners(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListeners(), this);
        // Time Control
        Objects.requireNonNull(getCommand("time")).setExecutor(new TimeCommand());
        Objects.requireNonNull(getCommand("noon")).setExecutor(new NoonCommand());
        Objects.requireNonNull(getCommand("day")).setExecutor(new DayCommand());
        Objects.requireNonNull(getCommand("night")).setExecutor(new NightCommand());
        Objects.requireNonNull(getCommand("midnight")).setExecutor(new MidnightCommand());
        // Weather
        Objects.requireNonNull(getCommand("weather")).setExecutor(new WeahterCommand());
        Objects.requireNonNull(getCommand("sun")).setExecutor(new SunCommand());
        Objects.requireNonNull(getCommand("rain")).setExecutor(new RainCommand());
        Objects.requireNonNull(getCommand("thunder")).setExecutor(new ThunderCommand());
        // Fun & Troll
        Objects.requireNonNull(getCommand("burn")).setExecutor(new BurnCommand());
        Objects.requireNonNull(getCommand("fakejoin")).setExecutor(new FakeJoinMessagesCommand());
        Objects.requireNonNull(getCommand("fakequit")).setExecutor(new FakeQuitMessagesCommand());
        // Player Commands
        Objects.requireNonNull(getCommand("workbench")).setExecutor(new WorkbenchCommand());
        Objects.requireNonNull(getCommand("anvil")).setExecutor(new AnvilCommand());

    }

    public static String get_prefix() {
        return ConfigurationManager.get_string("prefix");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        databaseManager.close();
    }

    public static JavaPlugin getPluginInstance() {
        return instance;
    }
}
