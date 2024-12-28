package com.narcissu14.spacetech.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class MachineHelper {
    public static int getDurability(ItemStack itemStack, int timeLeft, int totalTime) {
        int durability = itemStack.getType().getMaxDurability();
        if (durability == 0) {
            return 0;
        }
        return (int) (durability * (1 - (double) timeLeft / totalTime));
    }
}
