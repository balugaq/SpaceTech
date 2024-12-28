package com.narcissu14.spacetech.listener;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.event.PlayerSpaceFallEvent;
import com.narcissu14.spacetech.event.SpaceOxygenEvent;
import com.narcissu14.spacetech.generator.populators.PlanetPopulator;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.setup.config.STConfig;
import org.bukkit.*;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Narcissu14
 */
public class SpaceWorldListener implements Listener {

    private HashSet<Material> noPlaceItemSet = new HashSet<>();

    private static final PotionEffect JUMP_EFFECT = new PotionEffect(PotionEffectType.JUMP, 40, 3, false, false);
    //private static final PotionEffect NO_OXYGEN_EFFECT = new PotionEffect(PotionEffectType.WITHER, 100, 0, false, false);
    
    
    // 用后缀搜索物品
    public static Set<Material> filterWithSuffix(String suffix) {
    	return Arrays.stream(Material.values())
        		.filter(m -> m.name().endsWith(suffix) 
        				&& !m.name().startsWith("LEGACY_"))
        		.collect(Collectors.toSet());
    }
    
    public SpaceWorldListener(SpaceTech plugin) {
    	// 末影水晶
        noPlaceItemSet.add(Material.END_CRYSTAL);
        // 火把
        noPlaceItemSet.add(Material.TORCH);
        // 各种树苗
        noPlaceItemSet.addAll(filterWithSuffix("_SAPLING"));
        // 各种树叶
        noPlaceItemSet.addAll(filterWithSuffix("_LEAVES"));
        // 各种盆栽 + 植物
        noPlaceItemSet.add(Material.LARGE_FERN); //大型蕨
        noPlaceItemSet.add(Material.SEAGRASS); //海草
        noPlaceItemSet.add(Material.TALL_SEAGRASS); //高海草
        noPlaceItemSet.add(Material.GRASS); //草
        noPlaceItemSet.add(Material.TALL_GRASS); //高草丛
        noPlaceItemSet.add(Material.LILAC); //丁香
        noPlaceItemSet.add(Material.PEONY); //牡丹
        noPlaceItemSet.add(Material.ROSE_BUSH); //玫瑰丛
        noPlaceItemSet.add(Material.SWEET_BERRY_BUSH); //甜浆果丛
        noPlaceItemSet.add(Material.LILY_OF_THE_VALLEY); //铃兰
        noPlaceItemSet.add(Material.SUNFLOWER); //向日葵
        noPlaceItemSet.add(Material.LILY_PAD); //睡莲
        noPlaceItemSet.add(Material.VINE); //藤蔓
        noPlaceItemSet.add(Material.MUSHROOM_STEM); //蘑菇柄
        noPlaceItemSet.add(Material.FLOWER_POT); //花盆
        noPlaceItemSet.add(Material.FERN); //蕨
        noPlaceItemSet.add(Material.POTTED_FERN); //蕨盆栽
        noPlaceItemSet.add(Material.DEAD_BUSH); //枯萎的灌木
        noPlaceItemSet.add(Material.POTTED_DEAD_BUSH); //枯萎的灌木盆栽
        noPlaceItemSet.add(Material.CACTUS); //仙人掌
        noPlaceItemSet.add(Material.POTTED_CACTUS); //仙人掌盆栽
        noPlaceItemSet.add(Material.BAMBOO); //竹子
        noPlaceItemSet.add(Material.POTTED_BAMBOO); //竹子盆栽
        noPlaceItemSet.add(Material.ALLIUM); //绒球葱
        noPlaceItemSet.add(Material.POTTED_ALLIUM); //绒球葱盆栽
        noPlaceItemSet.add(Material.RED_MUSHROOM); //红色蘑菇
        noPlaceItemSet.add(Material.POTTED_RED_MUSHROOM); //红色蘑菇盆栽
        noPlaceItemSet.add(Material.BROWN_MUSHROOM); //棕色蘑菇
        noPlaceItemSet.add(Material.POTTED_BROWN_MUSHROOM); //棕色蘑菇盆栽
        noPlaceItemSet.add(Material.AZURE_BLUET); //茜草花
        noPlaceItemSet.add(Material.POTTED_AZURE_BLUET); //茜草花盆栽
        noPlaceItemSet.add(Material.POPPY); //虞美人
        noPlaceItemSet.add(Material.POTTED_POPPY); //虞美人盆栽
        noPlaceItemSet.add(Material.OXEYE_DAISY); //滨菊
        noPlaceItemSet.add(Material.POTTED_OXEYE_DAISY); //滨菊盆栽
        noPlaceItemSet.add(Material.POTTED_LILY_OF_THE_VALLEY); //铃兰盆栽
        noPlaceItemSet.add(Material.DANDELION); //蒲公英
        noPlaceItemSet.add(Material.POTTED_DANDELION); //蒲公英盆栽
        noPlaceItemSet.add(Material.CORNFLOWER); //矢车菊
        noPlaceItemSet.add(Material.POTTED_CORNFLOWER); //矢车菊盆栽
        noPlaceItemSet.add(Material.BLUE_ORCHID); //兰花
        noPlaceItemSet.add(Material.POTTED_BLUE_ORCHID); //兰花盆栽
        noPlaceItemSet.add(Material.RED_TULIP); //红色郁金香
        noPlaceItemSet.add(Material.POTTED_RED_TULIP); //红色郁金香盆栽
        noPlaceItemSet.add(Material.ORANGE_TULIP); //橙色郁金香
        noPlaceItemSet.add(Material.POTTED_ORANGE_TULIP); //橙色郁金香盆栽
        noPlaceItemSet.add(Material.WHITE_TULIP); //白色郁金香
        noPlaceItemSet.add(Material.POTTED_WHITE_TULIP); //白色郁金香盆栽
        noPlaceItemSet.add(Material.PINK_TULIP); //粉红色郁金香
        noPlaceItemSet.add(Material.POTTED_PINK_TULIP); //粉色郁金香盆栽
        noPlaceItemSet.add(Material.WITHER_ROSE); //凋零玫瑰
        noPlaceItemSet.add(Material.POTTED_WITHER_ROSE); //凋零玫瑰盆栽
        noPlaceItemSet.add(Material.POTTED_ACACIA_SAPLING); //金合欢树苗盆栽
        noPlaceItemSet.add(Material.POTTED_SPRUCE_SAPLING); //云杉树苗盆栽
        noPlaceItemSet.add(Material.POTTED_JUNGLE_SAPLING); //丛林树苗盆栽
        noPlaceItemSet.add(Material.POTTED_BIRCH_SAPLING); //白桦树苗盆栽
        noPlaceItemSet.add(Material.POTTED_OAK_SAPLING); //橡树树苗盆栽
        noPlaceItemSet.add(Material.POTTED_DARK_OAK_SAPLING); //深色橡树树苗盆栽
        noPlaceItemSet.add(Material.PUMPKIN); //南瓜
        noPlaceItemSet.add(Material.PUMPKIN_SEEDS); //南瓜种子
        noPlaceItemSet.add(Material.CARVED_PUMPKIN); //雕刻过的南瓜
        noPlaceItemSet.add(Material.PUMPKIN_STEM); //南瓜梗
        noPlaceItemSet.add(Material.ATTACHED_PUMPKIN_STEM); //结果的南瓜茎
        noPlaceItemSet.add(Material.MELON); //西瓜
        noPlaceItemSet.add(Material.MELON_SEEDS); //西瓜种子
        noPlaceItemSet.add(Material.MELON_STEM); //西瓜茎
        noPlaceItemSet.add(Material.ATTACHED_MELON_STEM); //结果的西瓜茎
        noPlaceItemSet.add(Material.BEETROOT_SEEDS); //甜菜种子
        noPlaceItemSet.add(Material.BEETROOTS); //甜菜根
        noPlaceItemSet.add(Material.CARROTS); //胡萝卜
        noPlaceItemSet.add(Material.WHEAT); //小麦
        noPlaceItemSet.add(Material.HAY_BLOCK); //干草块
        noPlaceItemSet.add(Material.WHEAT_SEEDS); //小麦种子
        noPlaceItemSet.add(Material.SWEET_BERRIES); //甜浆果
        noPlaceItemSet.add(Material.POTATOES); //马铃薯
        noPlaceItemSet.add(Material.COCOA_BEANS); //可可豆
        noPlaceItemSet.add(Material.NETHER_WART); //地狱疣
        noPlaceItemSet.add(Material.NETHER_WART_BLOCK); //地狱疣块
        noPlaceItemSet.add(Material.KELP); //海带
        noPlaceItemSet.add(Material.KELP_PLANT); //海带植株
        noPlaceItemSet.add(Material.DRIED_KELP); //干海带
        noPlaceItemSet.add(Material.DRIED_KELP_BLOCK); //干海带块
        noPlaceItemSet.add(Material.SEA_PICKLE); //海泡菜
        noPlaceItemSet.add(Material.COCOA); //可可果
        noPlaceItemSet.add(Material.BAMBOO_SAPLING); //竹笋
        // 各种床
        noPlaceItemSet.addAll(filterWithSuffix("_BED"));

        Bukkit.getPluginManager().registerEvents(this, plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!STConfig.spaceWorldList.contains(player.getWorld().getName())) {
                        continue;
                    }
                    player.addPotionEffect(JUMP_EFFECT);
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

    @EventHandler
    public void onEnderDragonSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            if (STConfig.spaceWorldList.contains(event.getEntity().getWorld().getName())) {
                ((EnderDragon) event.getEntity()).setPhase(EnderDragon.Phase.DYING);
            }
        }
    }

    @EventHandler
    public void onWorldGen(WorldInitEvent event) {
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
                //world.setGameRuleValue("falldamage", "false");
                world.setTime(17000);
                break;
            }
        }
    }
    
    /**
     * 宇宙空间中禁止一些物品的放置
     */
    @EventHandler
    public void onPlaceItem(PlayerInteractEvent event) {
        if (!STConfig.spaceWorldList.contains(event.getPlayer().getWorld().getName())) {
            return;
        }
        if (event.getItem() != null) {
            Material mat = event.getItem().getType();
            if (noPlaceItemSet.contains(mat)) {
                event.setCancelled(true);
                return;
            }
        }
    }

    /**
     * 玩家倒出的液体会直接变为对于固体方块
     **/
    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        if (STConfig.spaceWorldList.contains(event.getPlayer().getWorld().getName())) {
            if (isPlaced(event.getBlockClicked().getRelative(event.getBlockFace()), event.getBucket())) {
                event.setItemStack(new ItemStack(Material.BUCKET));
                event.setCancelled(true);
            }
        }
    }

    private boolean isPlaced(Block block, Material material) {
        boolean isPlaced = false;
        switch (material) {
            case WATER_BUCKET:
                block.setType(Material.ICE);
                isPlaced = true;
                break;
            case LAVA_BUCKET:
                block.setType(Material.OBSIDIAN);
                isPlaced = true;
                break;
            default:
                break;
        }
        return isPlaced;
    }

    /**
     * 禁止冰雪融化
     **/
    @EventHandler
    public void onIceMelt(BlockFormEvent event) {
        if (STConfig.spaceWorldList.contains(event.getBlock().getWorld().getName())) {
            event.setCancelled(true);
        }
    }

    /**
     * 禁止发射器倒液体、点火
     **/
    @EventHandler
    public void onDispenseBucket(BlockDispenseEvent event) {
        if (STConfig.spaceWorldList.contains(event.getBlock().getWorld().getName())) {
            switch (event.getItem().getType()) {
                case WATER_BUCKET:
                case LAVA_BUCKET:
                case FLINT_AND_STEEL:
                    event.setCancelled(true);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 宇宙空间中禁用鞘翅飞行
     */
    @EventHandler
    public void onPlayerElytraFly(EntityToggleGlideEvent event) {
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
    public void onPlayerFall(PlayerMoveEvent event) {
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
    public void onPlayerSpaceFall(PlayerSpaceFallEvent event) {
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
    public void onPlayerOxygenCheck(SpaceOxygenEvent event) {
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
