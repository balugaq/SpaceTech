package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Narcissu14
 */
public class OxygenChargeMachine extends AbstractPointsMachine {
    private static String ID;
    private static ItemStack POINTS_ITEM;
    private static int POINTS_MAX;
    private static String POINTS_NAME;

    public OxygenChargeMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(category, item, name, recipeType, recipe);
        ID = name;
        POINTS_MAX = pointsMax;
        POINTS_ITEM = pointsItem;
        POINTS_NAME = pointName;
    }

    @Override
    public String getInventoryTitle() {
        return "§b电力充氧机";
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(10, new ItemStack[]{new ItemStack(Material.WATER_BUCKET, 1)}, 500, new ItemStack[]{new ItemStack(Material.BUCKET)});
        registerRecipe(5, new ItemStack[]{STItems.SPACE_HELMET_1}, -600, 600);
        registerRecipe(10, new ItemStack[]{STItems.SPACE_HELMET_2}, -1200, 1200);
        registerRecipe(20, new ItemStack[]{STItems.SPACE_HELMET_3}, -2400, 2400);
        registerRecipe(40, new ItemStack[]{STItems.SPACE_HELMET_4}, -4000, 4000);
    }

    @Override
    public int getEnergyConsumption() {
        return 32;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return ID;
    }

    @Override
    public ItemStack getPointsItem() {
        return POINTS_ITEM;
    }

    @Override
    public int getPointsMax() {
        return POINTS_MAX;
    }

    @Override
    public String getPointsName() {
        return POINTS_NAME;
    }

    @Override
    public void addExtraMenuHandler(BlockMenu menu, Block b) {

    }

    @Override
    public boolean isChargeableItem(ItemStack input) {
        return STItems.isOxygenEquip(input) && getItemPoints(input) < POINTS_MAX;
    }

    @Override
    public int getItemPoints(ItemStack input) {
        return STItems.getOxygenValue(input.getItemMeta().getLore());
    }

    @Override
    public ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd) {
        ItemStack item = input.clone();
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.set(lores.size() - 2, STItems.modifyOxygenValue(lores, points, true));
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }
}
