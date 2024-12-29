package com.narcissu14.spacetech.setup;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChargeableBlock {
    public static final @NotNull Map<String, Integer> max_charges = new HashMap<>();
    public static final @NotNull Set<String> capacitors = new HashSet<>();

    public static boolean isChargeable(@NotNull Block b) {
        return isChargeable(b.getLocation());
    }

    public static boolean isChargeable(@NotNull Location l) {
        if (!StorageCacheUtils.hasBlock(l)) return false;
        return max_charges.containsKey(StorageCacheUtils.getSfItem(l).getId());
    }

    public static int getDefaultCapacity(@NotNull Location l) {
        String id = StorageCacheUtils.getSfItem(l).getId();
        return max_charges.get(id);
    }

    public static int getCharge(@NotNull Block b) {
        return getCharge(b.getLocation());
    }

    public static int getCharge(Location l) {
        String charge = StorageCacheUtils.getData(l, "energy-charge");
        if (charge != null) return Integer.parseInt(charge);
        StorageCacheUtils.setData(l, "energy-charge", "0");
        return 0;
    }

    public static void setCharge(@NotNull Location l, int charge) {
        if (charge < 0) {
            charge = 0;
        } else {
            int capacity = getMaxCharge(l);
            if (charge > capacity) charge = capacity;
        }
        if (charge != getCharge(l)) StorageCacheUtils.setData(l, "energy-charge", String.valueOf(charge));
    }

    public static int addCharge(@NotNull Block b, int charge) {
        return addCharge(b.getLocation(), charge);
    }

    public static int addCharge(@NotNull Location l, int charge) {
        int energy = getCharge(l);
        int space = getMaxCharge(l) - energy;
        int rest = charge;
        if (space > 0 && charge > 0) {
            if (space > charge) {
                setCharge(l, energy + charge);
                rest = 0;
            } else {
                rest = charge - space;
                setCharge(l, getMaxCharge(l));
            }
            if (capacitors.contains(StorageCacheUtils.getSfItem(l).getId())) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (charge < 0 && energy >= -charge) {
            setCharge(l, energy + charge);
            if (capacitors.contains(StorageCacheUtils.getSfItem(l).getId())) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return rest;
    }

    public static int getMaxCharge(@NotNull Location l) {
        SlimefunBlockData cfg = StorageCacheUtils.getBlock(l);
        if (cfg == null) {
            return 0;
        }
        if (!cfg.getAllData().containsKey("id")) {
            Slimefun.getDatabaseManager().getBlockDataController().removeBlock(l);
            return 0;
        }
        if (cfg.getAllData().containsKey("energy-capacity")) {
            return Integer.parseInt(cfg.getAllData().get("energy-capacity"));
        }
        StorageCacheUtils.setData(l, "energy-capacity", String.valueOf(getDefaultCapacity(l)));
        return getDefaultCapacity(l);
    }
}