package com.narcissu14.spacetech.objects;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.utils.SkullUtil;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import org.bukkit.NamespacedKey;

/**
 * @author Narcissu14
 */
public class STCategories {
    public static ItemGroup ST_MACHINES = null;

    static {
        try {
            ST_MACHINES = new ItemGroup(new NamespacedKey(SpaceTech.getInstance(), "st_machines"), new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmMzMwZjA4MGEyYTNkMWE4YWRhYjNlNjdmM2VkMzgxMTY1OGIyODczMDVkYjRiNWM0YTYwOTc3NTc1MzUifX19"),
                    "§b太空科技", "", "§7\"We choose to go to the moon.\"", "§7\"Not because they are easy,", "§7but because they are hard.\""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
