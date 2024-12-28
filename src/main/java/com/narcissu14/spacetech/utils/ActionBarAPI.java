package com.narcissu14.spacetech.utils;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;


/**
 * @author j0ach1mmall3
 */
public class ActionBarAPI {
    public static void sendActionBar(Player p, String msg) {
    	p.spigot().sendMessage(new TextComponent(msg));
    }
}
