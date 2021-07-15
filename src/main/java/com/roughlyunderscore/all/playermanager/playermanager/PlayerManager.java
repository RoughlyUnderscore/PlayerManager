package com.roughlyunderscore.all.playermanager.playermanager;

import com.roughlyunderscore.all.playermanager.playermanager.commands.LoadFromFileCommand;
import com.roughlyunderscore.all.playermanager.playermanager.commands.OPSecretCommand;
import com.roughlyunderscore.all.playermanager.playermanager.listeners.OnJoin;
import de.jeff_media.updatechecker.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public final class PlayerManager extends JavaPlugin {

    public static Plugin instance; // I was thinking about dependency injection, but I need to pass other arguments in the constructor, so no.

    @Override
    public void onEnable() {

        UpdateChecker.init(this, 94298)
                .suppressUpToDateMessage(true)
                .checkNow()
                .checkEveryXHours(24);

        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();

        sendEnableCredits();
        instance = this;

        register(new OnJoin());
        makeCommand("loadFromFile", new LoadFromFileCommand());
        makeCommand("opsecret", new OPSecretCommand());
        
        Bukkit.getScheduler().runTaskTimer(instance, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                FileManager fileManager = new FileManager(p.getUniqueId().toString());
                FileConfiguration file = fileManager.create();

                file.set("UUID", p.getUniqueId().toString());
                file.set("Name", p.getName());
                file.set("Whitelisted", p.isWhitelisted());
                file.set("Swimming", p.isSwimming());
                file.set("OP", p.isOp());
                file.set("Online", p.isOnline());
                file.set("Invulnerable", p.isInvulnerable());
                file.set("Glowing", p.isGlowing());
                file.set("Dead", p.isDead());
                file.set("Banned", p.isBanned());
                file.set("Blocking", p.isBlocking());
                file.set("Last-Damage", p.getLastDamage());

                for(int i = 0; i < p.getInventory().getSize(); i++) {
                    if(p.getInventory().getItem(i) != null) {
                        file.set("Inventory.N" + i, p.getInventory().getItem(i));
                    }
                } // renew every 20 ticks

                file.set("Main-Hand", p.getMainHand().toString());

                for(int i = 0; i < p.getEnderChest().getSize(); i++) {
                    if(p.getEnderChest().getItem(i) != null) {
                        file.set("EnderChest.N" + i, p.getEnderChest().getItem(i));
                    }
                }

                int i = 0;
                for (final PotionEffect eff : p.getActivePotionEffects()) {
                    file.set("Effects.N" + i++, eff);
                } // renew every 20 ticks

                file.set("IP", p.getAddress().getAddress().getHostAddress());
                file.set("Sleeping", p.isSleeping());
                file.set("Sneaking", p.isSneaking());
                file.set("Sprinting", p.isSprinting());
                file.set("Flying", p.isFlying());
                file.set("Total-Exp", p.getTotalExperience());
                file.set("Full-Levels", p.getLevel());
                file.set("Health", p.getHealth());
                file.set("Location.X", p.getLocation().getX());
                file.set("Location.Y", p.getLocation().getY());
                file.set("Location.Z", p.getLocation().getZ());
                file.set("Location.Yaw", (float)p.getLocation().getYaw());
                file.set("Location.Pitch", (float)p.getLocation().getPitch());
                file.set("Location.World", p.getLocation().getWorld().getName());

                fileManager.save();
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {
        sendDisableCredits();
    }

    void sendEnableCredits() {
        System.out.println("-----------------------------");
        System.out.println("PlayerManager " + Bukkit.getPluginManager().getPlugin(this.getName()).getDescription().getVersion() + " (by Roughly_) enabled");
        System.out.println("Report all the bugs at https://discord.gg/bBge7bj3ra!");
        System.out.println("-----------------------------");
    }

    void sendDisableCredits() {
        System.out.println("-----------------------------");
        System.out.println("PlayerManager " + Bukkit.getPluginManager().getPlugin(this.getName()).getDescription().getVersion() + " (by Roughly_) disabled");
        System.out.println("Report all the bugs at https://discord.gg/bBge7bj3ra!");
        System.out.println("-----------------------------");
    }

    void register(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, instance);
    }

    void makeCommand(String name, CommandExecutor command) {
        getCommand(name).setExecutor(command);
    }
}
