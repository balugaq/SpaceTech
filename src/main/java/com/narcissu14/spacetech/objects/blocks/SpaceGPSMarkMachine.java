package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.setup.config.STConfig;
import com.narcissu14.spacetech.utils.GeneratorUtils;
import com.narcissu14.spacetech.utils.TitleAPI;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Narcissu14
 * 额外重写内部的GUI，新增一个消耗点数发射GPS标记点的功能按钮
 */
public abstract class SpaceGPSMarkMachine extends AbstractPointsMachine {
    private final static ItemStack LAUNCH_BUTTON = new CustomItemStack(new ItemStack(Material.FIREWORK_ROCKET), "§c§l发射太空GPS标记点",
            "", "§7发射后，你可以通过GPS装置传送前往太空标记点", "§7当§d联氨§7充满后才可以发射", "", "§c§l注意: §7前往太空之前，请带好GPS传送装置", "§7否则你将无法从太空§e返回");
    private String id;
    private ItemStack pointsItem;
    private int pointsMax;
    private String pointsName;

    public SpaceGPSMarkMachine(ItemGroup itemGroup, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(itemGroup, item, name, recipeType, recipe);
        id = name;
        this.pointsMax = pointsMax;
        this.pointsItem = pointsItem;
        pointsName = pointName;
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(10, new ItemStack[]{STItems.UNIT_HYDRAZINE}, 1000, new ItemStack[]{STItems.EMPTY_UNIT});
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public @NotNull String getMachineIdentifier() {
        return id;
    }

    @Override
    public ItemStack getPointsItem() {
        return pointsItem;
    }

    @Override
    public @NotNull String getInventoryTitle() {
        return "&6&l太空GPS发射机";
    }

    @Override
    public int getPointsMax() {
        return pointsMax;
    }

    @Override
    public String getPointsName() {
        return pointsName;
    }

    @Override
    public void addExtraMenuHandler(@NotNull BlockMenu menu, @NotNull Block b) {
        menu.replaceExistingItem(4, LAUNCH_BUTTON);
        menu.addMenuClickHandler(4, (p, s, i, a) -> {
            //发射标记
            if (StorageCacheUtils.getData(b.getLocation(), POINTS_KEY).equals(String.valueOf(pointsMax))) {
                StorageCacheUtils.setData(b.getLocation(), POINTS_KEY, "0");
                p.closeInventory();
                TitleAPI.sendTitle(p, 1, 3, 1, "§6§l发射", "§e等待远方的消息吧！");
                p.playSound(b.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1.5f);
                p.playSound(b.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 2f, 1.5f);
                p.spawnParticle(Particle.EXPLOSION_LARGE, b.getLocation(), 10);
                b.getWorld().spawnEntity(b.getLocation(), EntityType.FIREWORK);
                //找到一个太空世界中的安全位置，延时
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        World world = null;
                        for (World w : Bukkit.getWorlds()) {

                            if (STConfig.spaceWorldList.contains(w.getName())) {
                                world = w;
                                break;
                            }
                        }
                        Location location = GeneratorUtils.getSaveLocation(world, 500, 8);
                        if (location != null) {
                            //添加标记并发送提示
                            TitleAPI.sendTitle(p, 1, 2, 1, "§a§l发射成功", "§e请为寻找到的标记点命名");
                            Slimefun.getGPSNetwork().createWaypoint(p, location);
                        } else {
                            //发送Title消息告知标记失败
                            TitleAPI.sendTitle(p, 1, 2, 1, "", "§c§l很遗憾...本次发射失败了，没有找到安全的着陆点");
                            p.playSound(b.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1.5f);
                        }
                    }
                }.runTaskLaterAsynchronously(SpaceTech.getInstance(), 200);
            } else {
                //联氨量不足
                TitleAPI.sendTitle(p, 1, 2, 1, "", "§c§l当前联氨量不足");
            }
            return false;
        });
    }

    @Override
    public boolean isChargeableItem(ItemStack input) {
        return false;
    }

    @Override
    public int getItemPoints(ItemStack input) {
        return 0;
    }

    @Override
    public @Nullable ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd) {
        return null;
    }
}
