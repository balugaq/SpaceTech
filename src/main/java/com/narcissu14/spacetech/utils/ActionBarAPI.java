package com.narcissu14.spacetech.utils;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


/**
 * @author j0ach1mmall3
 */
public class ActionBarAPI {
    public static void sendActionBar(@NotNull Player p, String msg) {
        p.spigot().sendMessage(new TextComponent(msg));
    }
}
