package com.narcissu14.spacetech.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

/**
 * @author Narcissu14
 */
public class ItemUtils {
    public static @NotNull ItemStack setUnbreakable(final @NotNull ItemStack item, boolean unBreakable) {
        ItemStack returnItem = item.clone();
        ItemMeta meta = returnItem.getItemMeta();
        meta.setUnbreakable(unBreakable);
        returnItem.setItemMeta(meta);
        return returnItem;
    }
}
