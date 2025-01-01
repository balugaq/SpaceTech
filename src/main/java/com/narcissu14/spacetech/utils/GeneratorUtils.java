package com.narcissu14.spacetech.utils;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Rotatable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

/**
 * @author Narcissu14
 */
public class GeneratorUtils {
    private static final BlockFace[] BLOCK_FACES = {BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST};

    /**
     * 随机生成列表中的一个特殊头颅方块
     *
     * @param block    生成的方块位置
     * @param itemList 生成的头颅物品列表，从中随机选取生成
     */
    public static void spawnSkullBlock(@NotNull Block block, @NotNull List<SlimefunItemStack> itemList) {
        int num = new Random().nextInt(itemList.size());
        spawnSkullBlock(block, itemList.get(num));
    }

    /**
     * 生成一个特殊的头颅方块
     *
     * @param block 生成的方块位置
     * @param item  生成的头颅物品
     */
    public static void spawnSkullBlock(@NotNull Block block, SlimefunItemStack item) {
        block.setType(Material.PLAYER_HEAD);
        //如果设置了方块材质但类型仍为空气，则当前位置超高
        if (block.getType().equals(Material.AIR)) {
            return;
        }
        Rotatable data = (Rotatable) block.getBlockData();
        data.setRotation(BLOCK_FACES[new Random().nextInt(BLOCK_FACES.length)]);
        try {
            PlayerHead.setSkin(block, PlayerSkin.fromBase64(item.getSkullTexture().get()), true);
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        SlimefunItem sfitem = SlimefunItem.getByItem(item);
        if (sfitem != null) {
            if (StorageCacheUtils.hasBlock(block.getLocation())) {
                return;
            }
            Slimefun.getDatabaseManager().getBlockDataController().createBlock(block.getLocation(), sfitem.getId());
        }
    }

    /**
     * 寻找太空世界中的安全位置
     *
     * @param world 目标世界
     */
    public static Location getSaveLocation(@Nullable World world, int radius, int maxFindTimes) {
        if (world == null) {
            return null;
        }
        Random random = new Random();
        //限制最多查找次数
        for (int i = 0; i < maxFindTimes; i++) {
            int x = random.nextInt(radius << 1) - radius;
            int z = random.nextInt(radius << 1) - radius;
            for (int y = world.getMaxHeight(); y > 30; y--) {
                if (world.getBlockAt(x, y, z).getType().isSolid()) {
                    return new Location(world, x, y + 1, z);
                }
            }
        }
        return null;
    }
}
