package com.narcissu14.spacetech.objects;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.utils.SkullUtil;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

/**
 * @author Narcissu14
 */
public class STCategories {
    public static NestedItemGroup SPACE_TECH = null;
    public static ItemGroup MACHINES = null;
    public static ItemGroup RESOURCES = null;
    public static ItemGroup MISC = null;
    public static ItemGroup TECH_MISC = null;
    public static ItemGroup TOOL = null;
    public static ItemGroup ARMOR = null;

    static {
        try {
            SPACE_TECH = new NestedItemGroup(
                    new NamespacedKey(SpaceTech.getInstance(), "space_tech"), new CustomItemStack(Material.DIAMOND_BLOCK,
                    "&b太空科技"));
            MACHINES = new SubItemGroup(new NamespacedKey(SpaceTech.getInstance(), "st_machines"), SPACE_TECH, new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmMzMwZjA4MGEyYTNkMWE4YWRhYjNlNjdmM2VkMzgxMTY1OGIyODczMDVkYjRiNWM0YTYwOTc3NTc1MzUifX19"),
                    "&b太空科技&7-&e机器"));
            RESOURCES = new SubItemGroup(new NamespacedKey(SpaceTech.getInstance(), "resources"), SPACE_TECH, new CustomItemStack(Material.MOSS_BLOCK,
                    "&b太空科技&7-&d资源"));
            MISC = new SubItemGroup(new NamespacedKey(SpaceTech.getInstance(), "misc"), SPACE_TECH, new CustomItemStack(Material.BOOK,
                    "&b太空科技&7-&c其他"));
            TECH_MISC = new SubItemGroup(new NamespacedKey(SpaceTech.getInstance(), "tech_misc"), SPACE_TECH, new CustomItemStack(Material.ANVIL,
                    "&b太空科技&7-&a技术&7-&c其他"));
            TOOL = new SubItemGroup(new NamespacedKey(SpaceTech.getInstance(), "tool"), SPACE_TECH, new CustomItemStack(Material.DIAMOND_PICKAXE,
                    "&b太空科技&7-&a工具"));
            ARMOR = new SubItemGroup(new NamespacedKey(SpaceTech.getInstance(), "armor"), SPACE_TECH, new CustomItemStack(Material.DIAMOND_CHESTPLATE,
                    "&b太空科技&7-&a装甲"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
