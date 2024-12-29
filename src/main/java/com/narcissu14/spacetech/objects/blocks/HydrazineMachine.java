package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.container.PointMachineRecipe;
import com.narcissu14.spacetech.objects.STItems;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Narcissu14
 */
public class HydrazineMachine extends AbstractPointsMachine {
    private final String id;
    private final ItemStack pointsItem;
    private final int pointsMax;
    private final String pointsName;

    public HydrazineMachine(ItemGroup itemGroup, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(itemGroup, item, name, recipeType, recipe);
        id = name;
        this.pointsMax = pointsMax;
        this.pointsItem = pointsItem;
        pointsName = pointName;
    }

    @Override
    public @NotNull String getInventoryTitle() {
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
    public @NotNull String getMachineIdentifier() {
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
    public @Nullable ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd) {
        return null;
    }

    @Override
    public int getCapacity() {
        return 128;
    }

    @Override
    public int getSpeed() {
        return 1;
    }
}
