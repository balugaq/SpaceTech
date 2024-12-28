package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author Narcissu14
 */
public abstract class OreRefiningMachine extends AContainer {
    private final String name;

    public OreRefiningMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, name, recipeType, recipe);
        this.name = name;
    }

    @Override
    public String getInventoryTitle() {
        return "§b电磁炼矿机";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(20, new ItemStack[]{STItems.COPPER_ORE}, new ItemStack[]{new CustomItem(SlimefunItems.COPPER_INGOT, 2)});
        registerRecipe(20, new ItemStack[]{STItems.SILVER_ORE}, new ItemStack[]{new CustomItem(SlimefunItems.SILVER_INGOT, 2)});
        registerRecipe(100, new ItemStack[]{STItems.TITANIUM_ORE}, new ItemStack[]{STItems.TITANIUM});
        registerRecipe(100, new ItemStack[]{STItems.NEODYMIUM_ORE}, new ItemStack[]{STItems.NEODYMIUM});
    }

    @Override
    public String getMachineIdentifier() {
        return name;
    }
}
