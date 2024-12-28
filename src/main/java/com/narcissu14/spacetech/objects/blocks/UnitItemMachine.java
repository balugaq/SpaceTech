package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author Narcissu14
 */
public abstract class UnitItemMachine extends AContainer {
    private final String name;

    public UnitItemMachine(ItemGroup itemGroup, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, new SlimefunItemStack(name, item), recipeType, recipe);
        this.name = name;
    }

    @Override
    public String getInventoryTitle() {
        return "§9单元物质加工机";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(40, new ItemStack[]{STItems.BEDROCK_EM_UNIT_3}, new ItemStack[]{new ItemStack(Material.BEDROCK), new CustomItemStack(STItems.EMPTY_EM_UNIT, 3)});
        registerRecipe(120, new ItemStack[]{new ItemStack(Material.BEDROCK, 3), new ItemStack(Material.DIAMOND_PICKAXE)},
            new ItemStack[]{STItems.BEDROCK_DIAMOND_PICKAXE});
        registerRecipe(80, new ItemStack[]{new ItemStack(Material.BEDROCK, 2), new ItemStack(Material.DIAMOND_SWORD)},
                new ItemStack[]{STItems.BEDROCK_DIAMOND_SWORD});
        registerRecipe(80, new ItemStack[]{new ItemStack(Material.BEDROCK, 2), new ItemStack(Material.DIAMOND_HOE)},
                new ItemStack[]{STItems.BEDROCK_DIAMOND_HOE});
        registerRecipe(40, new ItemStack[]{new ItemStack(Material.BEDROCK, 1), new ItemStack(Material.DIAMOND_SHOVEL)},
                new ItemStack[]{STItems.BEDROCK_DIAMOND_SPADE});
        registerRecipe(120, new ItemStack[]{new ItemStack(Material.BEDROCK, 3), new ItemStack(Material.DIAMOND_AXE)},
                new ItemStack[]{STItems.BEDROCK_DIAMOND_AXE});
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return name;
    }
}
