package net.soluslab.system.util;

import net.soluslab.system.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

public class ConfigurationManager extends Main {

    public static YamlConfiguration load_static_configuration_file () {
        File configFile = new File(Main.getPluginInstance().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Main.getPluginInstance().saveDefaultConfig();
        }
        try {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            return config;
        } catch (Exception e) {
            Main.getPluginInstance().getLogger().severe("Failed to load configuration file");
        }
        return null;
    }

    private static void save_static_configuration_file () {
        try {
            Objects.requireNonNull(load_static_configuration_file()).save(new File(Main.getPluginInstance().getDataFolder(), "config.yml"));
        } catch (Exception e) {
            return;
        }
    }


    // Value Getters
    public static String get_string(String path) {
        return Objects.requireNonNull(load_static_configuration_file()).getString(path);
    }

    public static String get_string(String path, String def) {
        return Objects.requireNonNull(load_static_configuration_file()).getString(path, def);
    }

    public static int get_int(String path) {
        return Objects.requireNonNull(load_static_configuration_file()).getInt(path);
    }

    public static int get_int(String path, int def) {
        return Objects.requireNonNull(load_static_configuration_file()).getInt(path, def);
    }

    public static boolean get_boolean(String path) {
        return Objects.requireNonNull(load_static_configuration_file()).getBoolean(path);
    }

    public static boolean get_boolean(String path, boolean def) {
        return Objects.requireNonNull(load_static_configuration_file()).getBoolean(path, def);
    }

    public static double get_double(String path) {
        return Objects.requireNonNull(load_static_configuration_file()).getDouble(path);
    }

    public static double get_double(String path, double def) {
        return Objects.requireNonNull(load_static_configuration_file()).getDouble(path, def);
    }

    // Value Setters
    public static void set_value(String path, String value) {
        Objects.requireNonNull(load_static_configuration_file()).set(path, value);
        save_static_configuration_file();
    }

}
