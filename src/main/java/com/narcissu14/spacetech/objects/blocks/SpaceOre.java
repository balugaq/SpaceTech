package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.utils.ActionBarAPI;
import com.narcissu14.spacetech.utils.ItemEnergy;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Narcissu14
 */
@Getter
public class SpaceOre extends AbstractOre {
    private final @NotNull String breakMsg;
    private final String durMax;

    public SpaceOre(ItemGroup itemGroup, @NotNull SlimefunItemStack item, @NotNull ItemStack oreNugget, RecipeType recipeType, ItemStack[] recipe, String durMax) {
        super(itemGroup, item, recipeType, recipe);

        breakMsg = item.getItemMeta().getDisplayName() + "§7 挖掘度: §e§l%dur% §7挖掘次数:§c§l ";
        this.durMax = durMax;

        addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent blockPlaceEvent) {
                Block block = blockPlaceEvent.getBlockPlaced();
                initBlockInfo(block);
            }
        }, new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent blockBreakEvent, @NotNull ItemStack itemStack, @NotNull List<ItemStack> list) {
                Player player = blockBreakEvent.getPlayer();
                if (player.getGameMode().equals(GameMode.CREATIVE)) {
                    return;
                }
                if (!player.isSneaking()) {
                    ActionBarAPI.sendActionBar(player, "§c请按住§e§l Shift §c进行挖掘");
                    blockBreakEvent.setCancelled(true);
                    return;
                }
                Block block = blockBreakEvent.getBlock();
                //以破坏工具决定挖掘度减少量
                if (StorageCacheUtils.getData(block.getLocation(), DURABILITY) == null) {
                    initBlockInfo(block);
                }
                int dur = Integer.parseInt(StorageCacheUtils.getData(block.getLocation(), DURABILITY)) - getBreakAmount(player);
                int breakTimes = Integer.parseInt(StorageCacheUtils.getData(block.getLocation(), BREAK_TIMES));

                if (dur <= 0 && breakTimes > 0) {
                    //按破坏次数计算应掉落的物资，2次及以内掉落完整矿物，20次以上不掉落
                    int result = 9 - ((breakTimes - 1) >> 1);
                    block.getWorld().dropItemNaturally(block.getLocation(), new CustomItemStack(oreNugget, Math.max(result, 0)));
                    //不掉落本体方块
                    Slimefun.getDatabaseManager().getBlockDataController().removeBlock(block.getLocation());
                    block.setType(Material.AIR);
                } else {
                    String newBreakTimes = String.valueOf(breakTimes + 1);

                    StorageCacheUtils.setData(block.getLocation(), DURABILITY, String.valueOf(dur));
                    StorageCacheUtils.setData(block.getLocation(), BREAK_TIMES, newBreakTimes);
                    //破坏提示
                    ActionBarAPI.sendActionBar(player, breakMsg.replace("%dur%", String.valueOf(dur)) + newBreakTimes);
                }
                //扣除耐久
                if (checkEMiningTool(player)) {
                    damageItem(player);
                }
                blockBreakEvent.setCancelled(true);
            }
        });
    }

    /**
     * 检查是否为钻机
     */
    private boolean checkEMiningTool(@NotNull Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (STItems.isEMiningTools(item)) {
            float charge = ItemEnergy.getStoredEnergy(item);
            float cost = 2F;
            if (charge < cost) {
                return false;
            }
            player.getInventory().setItemInMainHand(ItemEnergy.chargeItem(item, -cost));
            return true;
        }
        return false;
    }

    private void initBlockInfo(Block block) {
        StorageCacheUtils.setData(block.getLocation(), DURABILITY, durMax);
        StorageCacheUtils.setData(block.getLocation(), BREAK_TIMES, "0");
    }
}
