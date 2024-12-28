package com.narcissu14.spacetech.utils;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import lombok.experimental.UtilityClass;
import org.bukkit.block.Block;

@UtilityClass
public class ChargeableBlock {
    public static boolean isChargeable(Block block) {
        SlimefunItem item = getSfItem(block);
        if (item instanceof EnergyNetComponent) {
            EnergyNetComponent component = (EnergyNetComponent) item;
            return component.isChargeable();
        } else {
            return false;
        }
    }

    public static SlimefunItem getSfItem(Block block) {
        return StorageCacheUtils.getSfItem(block.getLocation());
    }

    public static int getCharge(Block block) {
        SlimefunItem item = getSfItem(block);
        if (item instanceof EnergyNetComponent) {
            EnergyNetComponent component = (EnergyNetComponent) item;
            return component.getCharge(block.getLocation());
        } else {
            return 0;
        }
    }

    public static void setCharge(Block block, int charge) {
        SlimefunItem item = getSfItem(block);
        if (item instanceof EnergyNetComponent) {
            EnergyNetComponent component = (EnergyNetComponent) item;
            component.setCharge(block.getLocation(), charge);
        }
    }

    public static void addCharge(Block block, int charge) {
        if (charge < 0) {
            removeCharge(block, -charge);
            return;
        }

        SlimefunItem item = getSfItem(block);
        if (item instanceof EnergyNetComponent) {
            EnergyNetComponent component = (EnergyNetComponent) item;
            component.addCharge(block.getLocation(), charge);
        }
    }

    public static void removeCharge(Block block, int charge) {
        if (charge < 0) {
            addCharge(block, -charge);
            return;
        }

        SlimefunItem item = getSfItem(block);
        if (item instanceof EnergyNetComponent) {
            EnergyNetComponent component = (EnergyNetComponent) item;
            component.removeCharge(block.getLocation(), charge);
        }
    }
}
