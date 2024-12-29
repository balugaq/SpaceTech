package com.narcissu14.spacetech.utils;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlimefunItemGroups {
    public static final List<ItemGroup> allGroups = Slimefun.getRegistry().getAllItemGroups();
    public static final @Nullable ItemGroup resources = findGroup("resources");
    public static final @Nullable ItemGroup tools = findGroup("tools");
    public static final @Nullable ItemGroup tech_misc = findGroup("tech_misc");
    public static final @Nullable ItemGroup misc = findGroup("misc");
    // armor
    public static final @Nullable ItemGroup armor = findGroup("armor");

    public static @Nullable ItemGroup findGroup(@NotNull String key) {
        for (ItemGroup group : allGroups) {
            if (group.getKey().equals(new NamespacedKey(Slimefun.instance(), key))) {
                return group;
            }
        }

        return null;
    }
}
