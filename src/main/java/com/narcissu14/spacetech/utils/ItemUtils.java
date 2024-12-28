package com.narcissu14.spacetech.utils;

import com.narcissu14.spacetech.SpaceTech;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Narcissu14
 */
public class ItemUtils {
	public static ItemStack setUnbreakable(final ItemStack item, boolean unBreakable) {
        ItemStack returnItem = item.clone();
        ItemMeta meta = returnItem.getItemMeta();
        meta.setUnbreakable(unBreakable);
        returnItem.setItemMeta(meta);
        return returnItem;
    }
}
