package com.narcissu14.spacetech.listener;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.event.PlayerSpaceFallEvent;
import com.narcissu14.spacetech.event.SpaceOxygenEvent;
import com.narcissu14.spacetech.generator.populators.PlanetPopulator;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.setup.config.STConfig;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;

/**
 * @author Narcissu14
 */
public class SpaceWorldListener implements Listener {

    private static final PotionEffect JUMP_EFFECT = new PotionEffect(PotionEffectType.JUMP, 40, 3, false, false);
    private static final PotionEffect SLOW_FALL_EFFECT = new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 3, false, false);

    public SpaceWorldListener(@NotNull SpaceTech plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!STConfig.spaceWorldList.contains(player.getWorld().getName())) {
                        continue;
                    }
                    player.addPotionEffect(JUMP_EFFECT);
                    player.addPotionEffect(SLOW_FALL_EFFECT);
                }
            }
        }.runTaskTimer(plugin, 0, 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (String worldName : STConfig.spaceWorldList) {
                    World world = Bukkit.getWorld(worldName);
                    if (world != null) {
                        for (Player player : world.getPlayers()) {
                            Bukkit.getPluginManager().callEvent(new SpaceOxygenEvent(player, -1));
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    public static boolean isBannedBlock(Material material) {
        if (material.name().endsWith("_SAPLING")
                || material.name().startsWith("POTTED_")
                || material == Material.AZALEA
                || material == Material.FLOWERING_AZALEA
                || material == Material.BROWN_MUSHROOM
                || material == Material.RED_MUSHROOM
                || material == Material.CRIMSON_FUNGUS
                || material == Material.WARPED_FUNGUS
                || material == materialValueOf("SHORT_GRASS")
                || material == Material.FERN
                || material == Material.DEAD_BUSH
                || material == Material.DANDELION
                || material == Material.POPPY
                || material == Material.BLUE_ORCHID
                || material == Material.ALLIUM
                || material == Material.AZURE_BLUET
                || material == Material.RED_TULIP
                || material == Material.ORANGE_TULIP
                || material == Material.WHITE_TULIP
                || material == Material.PINK_TULIP
                || material == Material.OXEYE_DAISY
                || material == Material.CORNFLOWER
                || material == Material.LILY_OF_THE_VALLEY
                || material == Material.TORCHFLOWER
                || material == Material.WITHER_ROSE
                || material == Material.PINK_PETALS
                || material == Material.SPORE_BLOSSOM
                || material == Material.BAMBOO
                || material == Material.SUGAR_CANE
                || material == Material.CACTUS
                || material == Material.CRIMSON_ROOTS
                || material == Material.WARPED_ROOTS
                || material == Material.NETHER_SPROUTS
                || material == Material.WEEPING_VINES
                || material == Material.TWISTING_VINES
                || material == Material.WEEPING_VINES_PLANT
                || material == Material.TWISTING_VINES_PLANT
                || material == Material.COCOA
                || material == Material.SWEET_BERRY_BUSH
                || material == Material.TORCHFLOWER_CROP
                || material == Material.WHEAT
                || material == Material.MELON_STEM
                || material == Material.PUMPKIN_STEM
                || material == Material.POTATOES
                || material == Material.CARROTS
                || material == Material.BEETROOTS
                || material == Material.KELP
                || material == Material.KELP_PLANT
                || material == Material.SEAGRASS
                || material == Material.LILY_PAD
                || material == materialValueOf("CREAKING_HEART")
                || material == materialValueOf("OPEN_EYEBLOSSOM")
                || material == materialValueOf("CLOSED_EYEBLOSSOM")
                || material == materialValueOf("PALE_HANGING_MOSS")
                || material == materialValueOf("RESIN_CLUMP")
                || material == Material.SEA_PICKLE
                || material == Material.TALL_GRASS
                || material == Material.LARGE_FERN
                || material == Material.TALL_SEAGRASS
                || material == Material.SUNFLOWER
                || material == Material.LILAC
                || material == Material.ROSE_BUSH
                || material == Material.PEONY
                || material == Material.PITCHER_PLANT
                || material.name().endsWith("_CORAL")
                || material.name().endsWith("_CORAL_FAN")
                || material == Material.MUSHROOM_STEM
                || material == Material.TORCH
                || material == Material.REDSTONE_TORCH
                || material == Material.REDSTONE_WALL_TORCH
                || material == Material.WALL_TORCH
                || material == Material.FIRE
                || material == Material.SOUL_FIRE
                || material == Material.SOUL_TORCH
                || material == Material.SOUL_WALL_TORCH
                || material == Material.NETHER_PORTAL
                || material == Material.END_CRYSTAL
                || material.name().endsWith("_BED")
                || material == Material.LANTERN
                || material == Material.SOUL_LANTERN
                || material == Material.JACK_O_LANTERN
                || material == Material.CAMPFIRE
                || material == Material.SOUL_CAMPFIRE
                || material == Material.BEEHIVE
                || material == Material.BEE_NEST) {
            return true;
        }

        return false;
    }

    public static Material materialValueOf(String name) {
        try {
            return Material.valueOf(name);
        } catch (Throwable e) {
            return null;
        }
    }

    @EventHandler
    public void onEnderDragonSpawn(@NotNull CreatureSpawnEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            if (STConfig.spaceWorldList.contains(event.getEntity().getWorld().getName())) {
                ((EnderDragon) event.getEntity()).setPhase(EnderDragon.Phase.DYING);
            }
        }
    }

    @EventHandler
    public void onWorldGen(@NotNull WorldInitEvent event) {
        World world = event.getWorld();
        if (world.getGenerator() == null) {
            return;
        }
        for (BlockPopulator populator : world.getGenerator().getDefaultPopulators(world)) {
            if (populator instanceof PlanetPopulator) {
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setGameRule(GameRule.DO_FIRE_TICK, false);
                world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                // java版本没有 falldamage 这条gamerule, 使用插件手动取消跌落伤害
                world.setGameRuleValue("falldamage", "false");
                world.setTime(17000);
                break;
            }
        }
    }

    /**
     * 宇宙空间中禁止一些物品的放置
     */
    @EventHandler
    public void onPlaceItem(@NotNull PlayerInteractEvent event) {
        if (!STConfig.spaceWorldList.contains(event.getPlayer().getWorld().getName())) {
            return;
        }
        if (event.getItem() != null) {
            Material mat = event.getItem().getType();
            if (isBannedBlock(mat)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * 玩家倒出的液体会直接变为对于固体方块
     **/
    @EventHandler
    public void onBucketEmpty(@NotNull PlayerBucketEmptyEvent event) {
        if (STConfig.spaceWorldList.contains(event.getPlayer().getWorld().getName())) {
            if (isPlaced(event.getBlockClicked().getRelative(event.getBlockFace()), event.getBucket())) {
                event.setItemStack(new ItemStack(Material.BUCKET));
                event.setCancelled(true);
            }
        }
    }

    private boolean isPlaced(@NotNull Block block, @NotNull Material material) {
        boolean isPlaced = false;
        switch (material) {
            case WATER_BUCKET, POWDER_SNOW_BUCKET -> {
                block.setType(Material.ICE);
                isPlaced = true;
            }
            case LAVA_BUCKET -> {
                block.setType(Material.OBSIDIAN);
                isPlaced = true;
            }
            default -> {
            }
        }
        return isPlaced;
    }

    /**
     * 禁止冰雪融化
     **/
    @EventHandler
    public void onIceMelt(@NotNull BlockFormEvent event) {
        if (STConfig.spaceWorldList.contains(event.getBlock().getWorld().getName())) {
            event.setCancelled(true);
        }
    }

    /**
     * 禁止发射器倒液体、点火
     **/
    @EventHandler
    public void onDispenseBucket(@NotNull BlockDispenseEvent event) {
        if (STConfig.spaceWorldList.contains(event.getBlock().getWorld().getName())) {
            switch (event.getItem().getType()) {
                case WATER_BUCKET, POWDER_SNOW_BUCKET, LAVA_BUCKET, FLINT_AND_STEEL -> event.setCancelled(true);
                default -> {
                }
            }
        }
    }

    /**
     * 宇宙空间中禁用鞘翅飞行
     */
    @EventHandler
    public void onPlayerElytraFly(@NotNull EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (!STConfig.spaceWorldList.contains(event.getEntity().getWorld().getName())) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (player.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {
            event.setCancelled(true);
        }
    }

    /**
     * 玩家在太空中坠落的触发监听
     */
    @EventHandler
    public void onPlayerFall(@NotNull PlayerMoveEvent event) {
        if (STConfig.spaceWorldList.contains(event.getFrom().getWorld().getName())) {
            if (!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                return;
            }
            double y = event.getPlayer().getVelocity().getY();
            if (y < 0) {
                Player player = event.getPlayer();
                if (!player.isOnGround()) {
                    //宇宙坠落中事件
                    Bukkit.getPluginManager().callEvent(new PlayerSpaceFallEvent(player, event.getFrom(), event.getTo()));
                }
            }
        }
    }

    /**
     * 玩家在太空中坠落的事件
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerSpaceFall(@NotNull PlayerSpaceFallEvent event) {
        if (!event.isCancelled()) {
            Player player = event.getPlayer();
            Vector vector = new Vector(0, -0.12, 0);
            vector.setX(player.getVelocity().getX());
            vector.setZ(player.getVelocity().getZ());
            player.setVelocity(vector);
            player.setFallDistance(0);
        }
    }

    /**
     * 玩家在太空中玩家氧气监测
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerOxygenCheck(@NotNull SpaceOxygenEvent event) {
        if (!event.isCancelled()) {
            //检测线程已经检查了世界
            ItemStack item = event.getPlayer().getInventory().getHelmet();
            if (STItems.isEquipHasOxygen(item)) {
                //扣除氧气物品的氧气值
                List<String> lores = item.getItemMeta().getLore();
                String newLore = STItems.modifyOxygenValue(item.getItemMeta().getLore(), event.getOxygen(), true);
                lores.set(lores.size() - 2, newLore);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(lores);
                item.setItemMeta(meta);
            } else {
                //给予没有氧气的玩家伤害
                //event.getPlayer().damage(1);
                //TODO 因为服务器上的端有些奇怪问题导致无伤害，暂时用这种方式扣血
                if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                    Player player = event.getPlayer();
                    if (!player.isDead()) {
                        player.playEffect(EntityEffect.HURT);
                        double newHp = player.getHealth() - 1;
                        player.setHealth(newHp < 0 ? 0 : newHp);
                    }
                }
            }
        }
    }
}
