package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.utils.ActionBarAPI;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Narcissu14
 */
public class SpaceOre extends AbstractOre {
    private final String breakMsg;
    private static String DUR_MAX;

    public SpaceOre(Category category, ItemStack item, ItemStack oreNugget, String id, RecipeType recipeType, ItemStack[] recipe, String durMax) {
        super(category, item, id, recipeType, recipe);

        breakMsg = item.getItemMeta().getDisplayName() + "§7 挖掘度: §e§l%dur% §7挖掘次数:§c§l ";
        DUR_MAX = durMax;

        registerBlockHandler(id, new SlimefunBlockHandler() {
            @Override
            public void onPlace(Player player, Block block, SlimefunItem slimefunItem) {
                initBlockInfo(block, slimefunItem.getItem());
            }

            @Override
            public boolean onBreak(Player player, Block block, SlimefunItem slimefunItem, UnregisterReason unregisterReason) {
                if (player.getGameMode().equals(GameMode.CREATIVE)) {
                    return true;
                }
                if (!player.isSneaking()) {
                    ActionBarAPI.sendActionBar(player, "§c请按住§e§l shift §c进行挖掘");
                    return false;
                }
                //以破坏工具决定挖掘度减少量
                if (BlockStorage.getLocationInfo(block.getLocation(), DURABILITY) == null) {
                    initBlockInfo(block, slimefunItem.getItem());
                }
                int dur = Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), DURABILITY)) - getBreakAmount(player);
                int breakTimes = Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), BREAK_TIMES));

                if (dur <= 0 && breakTimes > 0) {
                    //按破坏次数计算应掉落的物资，2次及以内掉落完整矿物，20次以上不掉落
                    int result = 9 - ((breakTimes - 1) >> 1);
                    block.getWorld().dropItemNaturally(block.getLocation(), new CustomItem(oreNugget, result > 0 ? result : 0));
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
                return false;
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

    private void initBlockInfo(Block block, ItemStack item) {
        BlockStorage.store(block, item);
        BlockStorage.addBlockInfo(block, DURABILITY, DUR_MAX);
        BlockStorage.addBlockInfo(block, BREAK_TIMES, "0");
    }
}
