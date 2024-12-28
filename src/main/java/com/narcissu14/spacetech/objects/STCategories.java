package com.narcissu14.spacetech.objects;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Objects.Category;

/**
 * @author Narcissu14
 */
public class STCategories {
    public static Category ST_MACHINES = null;

    static {
        try {
            ST_MACHINES = new Category(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmMzMwZjA4MGEyYTNkMWE4YWRhYjNlNjdmM2VkMzgxMTY1OGIyODczMDVkYjRiNWM0YTYwOTc3NTc1MzUifX19"),
                    "§b太空科技", "", "§7\"We choose to go to the moon.\"", "§7\"Not because they are easy,", "§7but because they are hard.\""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
