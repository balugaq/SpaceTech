package com.narcissu14.spacetech.utils;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


/**
 * @author j0ach1mmall3
 */
public class TitleAPI {
    public static void sendTitle(@NotNull Player p, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}
