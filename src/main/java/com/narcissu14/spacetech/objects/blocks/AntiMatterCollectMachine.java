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
public abstract class AntiMatterCollectMachine extends AbstractPointsMachine {
    private final String id;
    private final ItemStack pointsItem;
    private final int pointsMax;
    private final String pointsName;

    public AntiMatterCollectMachine(@NotNull ItemGroup itemGroup, @NotNull ItemStack item, @NotNull String name, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(itemGroup, item, name, recipeType, recipe);
        id = name;
        this.pointsMax = pointsMax;
        this.pointsItem = pointsItem;
        pointsName = pointName;
    }

    @Override
    public @NotNull String getInventoryTitle() {
        return "§d§l反物质捕获机";
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(10, new ItemStack[]{new ItemStack(Material.BLUE_DYE, 1)}, 32, new ItemStack[]{});
        registerRecipe(128, new ItemStack[]{STItems.EMPTY_EM_UNIT}, -65536, new ItemStack[]{STItems.ANTIMATTER_EM_UNIT}, PointMachineRecipe.PointCheckType.EQUAL, 65536);
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
}
