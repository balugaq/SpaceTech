package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.container.PointMachineRecipe;
import com.narcissu14.spacetech.objects.STItems;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Narcissu14
 */
public abstract class BedrockCrackMachine extends AbstractPointsMachine {
    private String id;
    private ItemStack pointsItem;
    private int pointsMax;
    private String pointsName;

    private final static ItemStack DIG_BUTTON = new CustomItem(new ItemStack(Material.HOPPER), "§a方向: §e下",
            "" , "§7当§e挖掘度§7到达最大后", "§7你可以放入§e空电磁拘束单元", "§7开始对基岩进行爆碎", "§7并收集获得基岩粉末", "", "§c§l注意: §7基岩爆碎机只能爆碎其下方的基岩");

    public BedrockCrackMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(category, item, name, recipeType, recipe);
        id = name;
        this.pointsMax = pointsMax;
        this.pointsItem = pointsItem;
        pointsName = pointName;
    }

    @Override
    public String getInventoryTitle() {
        return "§6§l基岩爆碎机";
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(60, new ItemStack[]{STItems.ANTIMATTER_BEDROCK_DRILL}, 4000, new ItemStack[]{});
        registerRecipe(120, new ItemStack[]{STItems.EMPTY_EM_UNIT}, -10000, new ItemStack[]{STItems.BEDROCK_EM_UNIT}, PointMachineRecipe.PointCheckType.BIG_THAN_OR_EQUAL, 10000);
    }

    @Override
    boolean startTickCheck(Block b, PointMachineRecipe recipe) {
        if (SlimefunManager.isItemSimiliar(recipe.getInput()[0], STItems.EMPTY_EM_UNIT, true)) {
            if (!b.getRelative(BlockFace.DOWN).getType().equals(Material.BEDROCK)) {
                return false;
            }
            //挖基岩
            World world = b.getLocation().getWorld();
            new BukkitRunnable() {
                @Override
                public void run() {
                    b.getRelative(BlockFace.DOWN).setType(Material.AIR);
                }
            }.runTask(SpaceTech.getInstance());
            world.playSound(b.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2f, 0.7f);
            world.spawnParticle(Particle.EXPLOSION_NORMAL, b.getLocation(), 10);
        }
        return true;
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
    public int getPointsMax() {
        return pointsMax;
    }

    @Override
    public String getPointsName() {
        return pointsName;
    }

    @Override
    public void addExtraMenuHandler(BlockMenu menu, Block b) {
        menu.replaceExistingItem(4, DIG_BUTTON);
        menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler() {
            @Override
            public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
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
