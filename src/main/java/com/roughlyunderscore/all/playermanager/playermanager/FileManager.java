package com.roughlyunderscore.all.playermanager.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private File file;
    private FileConfiguration config;
    private final String name;

    public FileManager(String name) {
        this.name = name;
    }

    public FileConfiguration create() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin(PlayerManager.instance.getName()).getDataFolder() + "\\Playerdata\\", name + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println(C.c(MessageStrings.COULD_NOT_SAVE_FILE.toString().replace("%name%", name)));
        }
    }

    /*
    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
    */

    public File getFile() {
        return this.file;
    }
}
