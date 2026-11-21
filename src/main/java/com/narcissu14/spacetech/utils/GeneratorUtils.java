package com.narcissu14.spacetech.utils;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.setup.config.STConfig;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import org.bukkit.Bukkit;
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
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

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
     * 在太空世界中寻找一个安全落脚点。
     * 必须先把区块生成并 populate 完再扫描方块：未生成的区块读出来全是空气，
     * 会把「星球还没生成」误判成「这里没有陆地」，玩家跑图之后成功率暴涨就是这个原因。
     *
     * @param callback 找到时回调坐标，尝试 maxFindTimes 次仍失败时回调 null
     */
    public static void findSafeLocation(@Nullable World world, int radius, int maxFindTimes, @NotNull Consumer<@Nullable Location> callback) {
        if (world == null) {
            callback.accept(null);
            return;
        }
        findSafeLocation(world, radius, maxFindTimes, 1, callback);
    }

    private static void findSafeLocation(@NotNull World world, int radius, int maxFindTimes, int attempt, @NotNull Consumer<@Nullable Location> callback) {
        if (attempt > maxFindTimes) {
            callback.accept(null);
            return;
        }
        Random random = ThreadLocalRandom.current();
        int x = random.nextInt(radius << 1) - radius;
        int z = random.nextInt(radius << 1) - radius;
        world.getChunkAtAsync(x >> 4, z >> 4).whenComplete((chunk, error) ->
                Bukkit.getScheduler().runTask(SpaceTech.getInstance(), () -> {
                    Location location = error == null ? scanColumn(world, x, z) : null;
                    if (STConfig.debugSpaceGps) {
                        SpaceTech.getInstance().getLogger().info("[GPS] 第 " + attempt + "/" + maxFindTimes + " 次选址 ("
                                + x + ", " + z + ") " + (location == null ? "未找到落脚点" : "着陆点 y=" + location.getBlockY()));
                    }
                    if (location != null) {
                        callback.accept(location);
                    } else {
                        findSafeLocation(world, radius, maxFindTimes, attempt + 1, callback);
                    }
                }));
    }

    private static @Nullable Location scanColumn(@NotNull World world, int x, int z) {
        for (int y = world.getMaxHeight() - 1; y > 30; y--) {
            if (world.getBlockAt(x, y, z).getType().isSolid()) {
                return new Location(world, x, y + 1, z);
            }
        }
        return null;
    }
}
