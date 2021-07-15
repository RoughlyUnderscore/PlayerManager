package com.roughlyunderscore.all.playermanager.playermanager.commands;

import com.roughlyunderscore.all.playermanager.playermanager.MessageStrings;
import com.roughlyunderscore.all.playermanager.playermanager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static com.roughlyunderscore.all.playermanager.playermanager.C.c;

public class OPSecretCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(c(MessageStrings.NO_CONSOLE.toString()));
            return false;
        }
        Player p = (Player)sender;
        if (p.hasPermission("pmanager.beop")) {
             p.sendMessage("Arr, matey, ye found a secret easter egg (sort o').");
             p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

             Bukkit.getScheduler().runTaskLater(PlayerManager.instance, () -> {
                 p.sendMessage("I think ye want to know somethin' interesting, do ye?");
                 p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
             }, 40);

            Bukkit.getScheduler().runTaskLater(PlayerManager.instance, () -> {
                p.sendMessage("This here interestin' secret be somethin' ye ne'er 'eard o'.");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }, 40);

            Bukkit.getScheduler().runTaskLater(PlayerManager.instance, () -> {
                p.sendMessage("An' the secret be...");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }, 40);

            Bukkit.getScheduler().runTaskLater(PlayerManager.instance, () -> {
                p.sendMessage("Deez nuts!");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);

            }, 40);
        } else {
            p.sendMessage(c(MessageStrings.NO_PERMISSIONS.toString()));
            return false;
        }
        return false;
    }
}
