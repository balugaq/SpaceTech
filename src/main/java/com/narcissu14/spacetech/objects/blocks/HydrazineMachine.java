package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.container.PointMachineRecipe;
import com.narcissu14.spacetech.objects.STItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * @author Narcissu14
 */
public class HydrazineMachine extends AbstractPointsMachine {
    private String id;
    private ItemStack pointsItem;
    private int pointsMax;
    private String pointsName;

    public HydrazineMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(category, item, name, recipeType, recipe);
        id = name;
        this.pointsMax = pointsMax;
        this.pointsItem = pointsItem;
        pointsName = pointName;
    }

    @Override
    public String getInventoryTitle() {
        return "§5§l联氨合成机";
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(20, new ItemStack[]{new ItemStack(Material.ROTTEN_FLESH)}, 20, new ItemStack[]{});
        registerRecipe(20, new ItemStack[]{new ItemStack(Material.FERMENTED_SPIDER_EYE)}, 60, new ItemStack[]{});
        registerRecipe(100, new ItemStack[]{STItems.EMPTY_UNIT}, -1000, new ItemStack[]{STItems.UNIT_HYDRAZINE}, PointMachineRecipe.PointCheckType.EQUAL, 1000);
    }

    @Override
    public int getEnergyConsumption() {
        return 64;
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
