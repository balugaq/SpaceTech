package com.narcissu14.spacetech.utils;

import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.Directional;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

/**
 * @author Narcissu14
 */
public class GeneratorUtils {
    private static final BlockFace[] BLOCK_FACES = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

    /**
     * 随机生成列表中的一个特殊头颅方块
     * @param block 生成的方块位置
     * @param itemList 生成的头颅物品列表，从中随机选取生成
     */
    public static void spawnSkullBlock(Block block, List<ItemStack> itemList) {
        int num = new Random().nextInt(itemList.size());
        spawnSkullBlock(block, itemList.get(num));
    }

    /**
     * 生成一个特殊的头颅方块
     * @param block 生成的方块位置
     * @param item 生成的头颅物品
     */
    public static void spawnSkullBlock(Block block, ItemStack item)
    {
        block.setType(Material.PLAYER_HEAD);
        //如果设置了方块材质但类型仍为空气，则当前位置超高
        if (block.getType().equals(Material.AIR)) {
            return;
        }
        Skull s = (Skull) block.getState();
        //1.14 使用BlockData代替Rotation
        Directional data = (Directional) block.getBlockData();
        data.setFacing(BLOCK_FACES[new Random().nextInt(BLOCK_FACES.length)]);
        //意义不明 s.setRawData((byte)1);
        s.update();
        try
        {
            CustomSkull.setSkull(s.getBlock(), CustomSkull.getTexture(item));
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        BlockStorage.store(block, item);
    }

    /**
     * 寻找太空世界中的安全位置
     * @param world 目标世界
     */
    public static Location getSaveLocation(World world, int radius, int maxFindTimes) {
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
