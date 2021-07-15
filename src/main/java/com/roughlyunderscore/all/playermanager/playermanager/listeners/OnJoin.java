package com.roughlyunderscore.all.playermanager.playermanager.listeners;

import com.roughlyunderscore.all.playermanager.playermanager.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class OnJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        FileManager fileManager = new FileManager(e.getPlayer().getUniqueId().toString());
        FileConfiguration file = fileManager.create();

        file.set("UUID", e.getPlayer().getUniqueId().toString());
        file.set("Name", e.getPlayer().getName());
        file.set("Whitelisted", e.getPlayer().isWhitelisted());
        file.set("Swimming", e.getPlayer().isSwimming());
        file.set("OP", e.getPlayer().isOp());
        file.set("Online", e.getPlayer().isOnline());
        file.set("Invulnerable", e.getPlayer().isInvulnerable());
        file.set("Glowing", e.getPlayer().isGlowing());
        file.set("Dead", e.getPlayer().isDead());
        file.set("Banned", e.getPlayer().isBanned());
        file.set("Blocking", e.getPlayer().isBlocking());
        file.set("Last-Damage", e.getPlayer().getLastDamage());

        for(int i = 0; i < e.getPlayer().getInventory().getSize(); i++) {
            if(e.getPlayer().getInventory().getItem(i) != null) {
                file.set("Inventory.N" + i, e.getPlayer().getInventory().getItem(i));
            }
        }

        file.set("Main-Hand", e.getPlayer().getMainHand().toString());

        for(int i = 0; i < e.getPlayer().getEnderChest().getSize(); i++) {
            if(e.getPlayer().getEnderChest().getItem(i) != null) {
                file.set("EnderChest.N" + i, e.getPlayer().getEnderChest().getItem(i));
            }
        }

        int i = 0;
        for (final PotionEffect eff : e.getPlayer().getActivePotionEffects()) {
            file.set("Effects.N" + i++, eff);
        }

        file.set("IP", e.getPlayer().getAddress().getAddress().getHostAddress());
        file.set("Sleeping", e.getPlayer().isSleeping());
        file.set("Sneaking", e.getPlayer().isSneaking());
        file.set("Sprinting", e.getPlayer().isSprinting());
        file.set("Flying", e.getPlayer().isFlying());
        file.set("Total-Exp", e.getPlayer().getTotalExperience());
        file.set("Full-Levels", e.getPlayer().getLevel());
        file.set("Health", e.getPlayer().getHealth());
        file.set("Location.X", (double)e.getPlayer().getLocation().getX());
        file.set("Location.Y", (double)e.getPlayer().getLocation().getY());
        file.set("Location.Z", (double)e.getPlayer().getLocation().getZ());
        file.set("Location.Yaw", (float)e.getPlayer().getLocation().getYaw());
        file.set("Location.Pitch", (float)e.getPlayer().getLocation().getPitch());
        file.set("Location.World", e.getPlayer().getLocation().getWorld().getName());

        fileManager.save();

    }
}
