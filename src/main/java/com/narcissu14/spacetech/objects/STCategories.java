package com.narcissu14.spacetech.objects;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.utils.SkullUtil;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;

/**
 * @author Narcissu14
 */
public class STCategories {
    public static ItemGroup ST_MACHINES = null;
    public static ItemGroup QUANTUM_MACHINES = null;

    static {
        try {
            ST_MACHINES = new ItemGroup(new NamespacedKey(SpaceTech.getInstance(), "st_machines"),
                    new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmMzMwZjA4MGEyYTNkMWE4YWRhYjNlNjdmM2VkMzgxMTY1OGIyODczMDVkYjRiNWM0YTYwOTc3NTc1MzUifX19"),
                            "§b太空科技", "", "§7\"We choose to go to the moon.\"", "§7\"Not because they are easy,", "§7but because they are hard.\""));
            QUANTUM_MACHINES = new ItemGroup(new NamespacedKey(SpaceTech.getInstance(), "quantum_machines"),
                    new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY4NGY0YTZlZDE0Mjg2NWRiMDkzOGU0ODc2NzY4NDlhNTRkNjQzNzhlMmU5ZTdmNzEzYjliMWU5ZDA0MSJ9fX0="),
                            "&b量子科技&7-&e机器"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
