package com.narcissu14.spacetech.utils;

import com.narcissu14.spacetech.SpaceTech;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Narcissu14
 */
public class ItemUtils {
    private static final boolean USE_OLD_UNABREAKABLE;

    @SuppressWarnings("deprecation")
	public static ItemStack setUnbreakable(final ItemStack item, boolean unBreakable) {
        ItemStack returnItem = item.clone();
        ItemMeta meta = returnItem.getItemMeta();
        if (USE_OLD_UNABREAKABLE) {
            meta.setUnbreakable(unBreakable);
        } else {
            meta.spigot().setUnbreakable(unBreakable);
        }
        returnItem.setItemMeta(meta);
        return returnItem;
    }

    static {
        String version = SpaceTech.getInstance().getServer().getBukkitVersion().split("-")[0];

        int verNum = 10;
        try {
            verNum = Integer.valueOf(version.split("\\.")[1]);
        } catch (NumberFormatException ignored) {

        }

        USE_OLD_UNABREAKABLE = verNum > 10;
    }
}
