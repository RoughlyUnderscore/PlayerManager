package com.roughlyunderscore.all.playermanager.playermanager;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public enum MessageStrings {
    COULD_NOT_SAVE_FILE(getMessage("could-not-save-file")),
    NO_PERMISSIONS(getMessage("no-permissions")),
    TOO_MANY_ARGS(getMessage("too-many-arguments")),
    TOO_LITTLE_ARGS(getMessage("too-little-arguments")),
    NO_CONSOLE(getMessage("cant-console")),
    NO_SUCH_PLAYER(getMessage("no-such-player")),
    NO_PLAYER_DATA(getMessage("no-player-data"));

    private static String getMessage(String path) {
        Plugin instance = PlayerManager.instance;

        String lang = instance.getConfig().getString("lang");

        return ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString(lang + "." + path));
    }

    private final String text;

    MessageStrings(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }


}
