package com.roughlyunderscore.all.playermanager.playermanager.commands;

import com.roughlyunderscore.all.playermanager.playermanager.FileManager;
import com.roughlyunderscore.all.playermanager.playermanager.MessageStrings;
import com.roughlyunderscore.all.playermanager.playermanager.PlayerManager;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static com.roughlyunderscore.all.playermanager.playermanager.C.c;

import java.io.File;

public class LoadFromFileCommand implements CommandExecutor {

    private Object get(String path, String playerUuid) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin(PlayerManager.instance.getName()).getDataFolder() + "\\Playerdata\\", playerUuid + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.get(path);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("pmanager.loadfromfile")) {
            sender.sendMessage(c(MessageStrings.NO_PERMISSIONS.toString()));
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(c(MessageStrings.TOO_LITTLE_ARGS.toString().replace("%usage%", "/loadFromFile <playerName> <fileName (optional)>").replace("%command%", command.getName())));
            return false;
        }
        else if (args.length > 2) {
            sender.sendMessage(c(MessageStrings.TOO_MANY_ARGS.toString().replace("%usage%", "/loadFromFile <playerName> <fileName (optional)>").replace("%command%", command.getName())));
            return false;
        }
        else if (args.length == 1) {
            Player p = Bukkit.getPlayerExact(args[0]);
            if (p == null) {
                sender.sendMessage(c(MessageStrings.NO_SUCH_PLAYER.toString().replace("%player%", args[0])));
                return false;
            }

            FileManager fileManager = new FileManager(p.getUniqueId().toString());
            fileManager.create();
            File f = fileManager.getFile();

            String uuid = p.getUniqueId().toString();

            p.setWhitelisted((Boolean) get("Whitelisted", uuid));
            p.setSwimming((Boolean) get("Swimming", uuid));
            p.setOp((Boolean) get("OP", uuid));
            p.setInvulnerable((Boolean) get("Invulnerable", uuid));
            p.setGlowing((Boolean) get("Glowing", uuid));
            p.setLastDamage((double) get("Last-Damage", uuid));
            p.getInventory().clear();

            try {
                for(int i = 0; i < p.getInventory().getSize(); i++) {
                    if (get("Inventory.N" + i, uuid) != null) {
                        p.getInventory().addItem((ItemStack)get("Inventory.N" + i, uuid));
                    }
                }
            } catch (NullPointerException ignored){}
            try {
                for(int i = 0; i < p.getEnderChest().getSize(); i++) {
                    if (get("EnderChest.N" + i, uuid) != null) {
                        p.getInventory().addItem((ItemStack)get("EnderChest.N" + i, uuid));
                    }
                }
            } catch (NullPointerException ignored){}
            try {
                for (final String k : YamlConfiguration.loadConfiguration(f).getConfigurationSection("Effects").getKeys(false)) {
                    p.addPotionEffect((PotionEffect) YamlConfiguration.loadConfiguration(f).getConfigurationSection("Effects").get(k));
                }
            }catch (NullPointerException ignored){}
            p.setSneaking((Boolean) get("Sneaking", uuid));
            p.setSprinting((Boolean) get("Sprinting", uuid));
            p.setFlying((Boolean) get("Flying", uuid));
            p.setTotalExperience((int) get("Total-Exp", uuid));
            p.setHealth((double) get("Health", uuid));

            p.teleport(new Location(
                    Bukkit.getWorld((String) get("Location.World", uuid)),
                    (double)get("Location.X", uuid),
                    (double)get("Location.Y", uuid),
                    (double)get("Location.Z", uuid),
                    (float)get("Location.Yaw", uuid),
                    (float)get("Location.Pitch", uuid)
            ));

            fileManager.save();
        }
        else {
            Player p = Bukkit.getPlayerExact(args[0]);
            OfflinePlayer loadFrom = Bukkit.getOfflinePlayer(args[1]);
            if (p == null) {
                sender.sendMessage(c(MessageStrings.NO_SUCH_PLAYER.toString().replace("%player%", args[0])));
                return false;
            }
            if (loadFrom == null) {
                sender.sendMessage(c(MessageStrings.NO_SUCH_PLAYER.toString().replace("%player%", args[1])));
                return false;
            }

            FileManager fileManager = new FileManager(loadFrom.getUniqueId().toString());
            fileManager.create();
            File f = fileManager.getFile();

            if (!f.exists()) {
                sender.sendMessage(c(MessageStrings.NO_PLAYER_DATA.toString().replace("%player%", args[1])));
                return false;
            }

            String uuid = loadFrom.getUniqueId().toString();

            Validate.notNull(p, "Player can't be null");
            Validate.notNull(loadFrom, "Player can't be null");

            p.setWhitelisted((Boolean) get("Whitelisted", uuid));
            p.setSwimming((Boolean) get("Swimming", uuid));
            p.setOp((Boolean) get("OP", uuid));
            p.setInvulnerable((Boolean) get("Invulnerable", uuid));
            p.setGlowing((Boolean) get("Glowing", uuid));
            p.setLastDamage((double) get("Last-Damage", uuid));
            p.getInventory().clear();

            try {
                for(int i = 0; i < p.getInventory().getSize(); i++) {
                    if (get("Inventory.N" + i, uuid) != null) {
                        p.getInventory().addItem((ItemStack)get("Inventory.N" + i, uuid));
                    }
                }
            } catch (NullPointerException ignored){}
            try {
                for(int i = 0; i < p.getEnderChest().getSize(); i++) {
                    if (get("EnderChest.N" + i, uuid) != null) {
                        p.getInventory().addItem((ItemStack)get("EnderChest.N" + i, uuid));
                    }
                }
            } catch (NullPointerException ignored){}
            try {
                for (final String k : YamlConfiguration.loadConfiguration(f).getConfigurationSection("Effects").getKeys(false)) {
                    p.addPotionEffect((PotionEffect) YamlConfiguration.loadConfiguration(f).getConfigurationSection("Effects").get(k));
                }
            } catch (NullPointerException ignored){}
            p.setSneaking((Boolean) get("Sneaking", uuid));
            p.setSprinting((Boolean) get("Sprinting", uuid));
            p.setFlying((Boolean) get("Flying", uuid));
            p.setTotalExperience((int) get("Total-Exp", uuid));
            p.setHealth((double) get("Health", uuid));

            p.teleport(new Location(
                    Bukkit.getWorld((String) get("Location.World", uuid)),
                    (double)get("Location.X", uuid),
                    (double)get("Location.Y", uuid),
                    (double)get("Location.Z", uuid),
                    (float)YamlConfiguration.loadConfiguration(f).getDouble("Location.Yaw"),
                    (float)YamlConfiguration.loadConfiguration(f).getDouble("Location.Pitch")
            ));

            fileManager.save();
        }

        return false;
    }
}
