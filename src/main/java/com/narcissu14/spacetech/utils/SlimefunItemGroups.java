package com.narcissu14.spacetech.utils;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.NamespacedKey;

import java.util.List;

public class SlimefunItemGroups {
    public static final List<ItemGroup> allGroups = Slimefun.getRegistry().getAllItemGroups();
    public static final ItemGroup resources = findGroup("resources");
    public static final ItemGroup tools = findGroup("tools");
    public static final ItemGroup tech_misc = findGroup("tech_misc");
    public static final ItemGroup misc = findGroup("misc");
    // armor
    public static final ItemGroup armor = findGroup("armor");
    public static ItemGroup findGroup(String key) {
        for (ItemGroup group : allGroups) {
            if (group.getKey().equals(new NamespacedKey(Slimefun.instance(), key))) {
                return group;
            }
        }

        return null;
    }
}
