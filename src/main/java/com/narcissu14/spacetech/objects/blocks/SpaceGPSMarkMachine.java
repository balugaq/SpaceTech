package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.setup.config.STConfig;
import com.narcissu14.spacetech.utils.GeneratorUtils;
import com.narcissu14.spacetech.utils.TitleAPI;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Narcissu14
 * 额外重写内部的GUI，新增一个消耗点数发射GPS标记点的功能按钮
 */
public abstract class SpaceGPSMarkMachine extends AbstractPointsMachine {
    private String id;
    private ItemStack pointsItem;
    private int pointsMax;
    private String pointsName;

    private final static ItemStack LAUNCH_BUTTON = new CustomItem(new ItemStack(Material.FIREWORK_ROCKET), "§c§l发射太空GPS标记点",
            "" , "§7发射后，你可以通过GPS装置传送前往太空标记点", "§7当§d联氨§7充满后才可以发射", "", "§c§l注意: §7前往太空之前，请带好GPS传送装置", "§7否则你将无法从太空§e返回");

    public SpaceGPSMarkMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(category, item, name, recipeType, recipe);
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
    public String getMachineIdentifier() {
        return id;
    }

    @Override
    public ItemStack getPointsItem() {
        return pointsItem;
    }

    @Override
    public String getInventoryTitle() {
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
    public void addExtraMenuHandler(BlockMenu menu, Block b) {
        menu.replaceExistingItem(4, LAUNCH_BUTTON);
        menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler() {
            @Override
            public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
                //发射标记
                if (BlockStorage.getLocationInfo(b.getLocation(), POINTS_KEY).equals(String.valueOf(pointsMax))) {
                    BlockStorage.addBlockInfo(b, POINTS_KEY, "0");
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
                                Slimefun.getGPSNetwork().addWaypoint(p, location);
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
            }
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
    public ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd) {
        return null;
    }
}
