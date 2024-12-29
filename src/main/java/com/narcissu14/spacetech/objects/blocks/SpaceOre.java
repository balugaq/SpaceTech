package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.utils.ActionBarAPI;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChargeUtils;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Narcissu14
 */
@Getter
public class SpaceOre extends AbstractOre {
    private final String breakMsg;
    private final String durMax;

    public SpaceOre(ItemGroup itemGroup, ItemStack item, ItemStack oreNugget, String id, RecipeType recipeType, ItemStack[] recipe, String durMax) {
        super(itemGroup, item, id, recipeType, recipe);

        breakMsg = item.getItemMeta().getDisplayName() + "§7 挖掘度: §e§l%dur% §7挖掘次数:§c§l ";
        this.durMax = durMax;

        addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent blockPlaceEvent) {
                Block block = blockPlaceEvent.getBlockPlaced();
                initBlockInfo(block);
            }
        }, new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Player player = blockBreakEvent.getPlayer();
                if (player.getGameMode().equals(GameMode.CREATIVE)) {
                    return;
                }
                if (!player.isSneaking()) {
                    ActionBarAPI.sendActionBar(player, "§c请按住§e§l shift §c进行挖掘");
                    blockBreakEvent.setCancelled(true);
                    return;
                }
                Block block = blockBreakEvent.getBlock();
                //以破坏工具决定挖掘度减少量
                if (BlockStorage.getLocationInfo(block.getLocation(), DURABILITY) == null) {
                    initBlockInfo(block);
                }
                int dur = Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), DURABILITY)) - getBreakAmount(player);
                int breakTimes = Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), BREAK_TIMES));

                if (dur <= 0 && breakTimes > 0) {
                    //按破坏次数计算应掉落的物资，2次及以内掉落完整矿物，20次以上不掉落
                    int result = 9 - ((breakTimes - 1) >> 1);
                    block.getWorld().dropItemNaturally(block.getLocation(), new CustomItemStack(oreNugget, result > 0 ? result : 0));
                    //不掉落本体方块
                    BlockStorage.retrieve(block);
                    block.setType(Material.AIR);
                } else {
                    String newBreakTimes = String.valueOf(breakTimes + 1);

                    BlockStorage.addBlockInfo(block, DURABILITY, String.valueOf(dur));
                    BlockStorage.addBlockInfo(block, BREAK_TIMES, newBreakTimes);
                    //破坏提示
                    ActionBarAPI.sendActionBar(player, breakMsg.replace("%dur%", String.valueOf(dur)) + newBreakTimes);
                }
                //扣除耐久
                if (checkEMiningTool(player)) {
                    damageItem(player);
                }
                blockBreakEvent.setCancelled(true);
                return;
            }
        });
    }

    /**检查是否为钻机*/
    private boolean checkEMiningTool(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null) {
            return false;
        }
        if (STItems.isEMiningTools(item)) {
            ItemMeta meta = item.getItemMeta();
            float charge = ChargeUtils.getCharge(meta);
            float cost = 2F;
            if (charge < cost) {
                return false;
            }
            ChargeUtils.setCharge(meta, charge - cost, STItems.getToolCapacity(item));
            item.setItemMeta(meta);
            player.getInventory().setItemInMainHand(item);
            return true;
        }
        return false;
    }

    private void initBlockInfo(Block block) {
        BlockStorage.addBlockInfo(block, DURABILITY, durMax);
        BlockStorage.addBlockInfo(block, BREAK_TIMES, "0");
    }
}
