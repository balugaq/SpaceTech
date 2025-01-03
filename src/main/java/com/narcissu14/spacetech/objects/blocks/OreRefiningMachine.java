package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author Narcissu14
 */
public abstract class OreRefiningMachine extends AContainer {
    private final @NotNull String name;

    public OreRefiningMachine(@NotNull ItemGroup itemGroup, @NotNull ItemStack item, @NotNull String name, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(itemGroup, new SlimefunItemStack(name, item), recipeType, recipe);
        this.name = name;
    }

    @Override
    public @NotNull String getInventoryTitle() {
        return "§b电磁炼矿机";
    }

    @Override
    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(20, new ItemStack[]{STItems.COPPER_ORE}, new ItemStack[]{new CustomItemStack(SlimefunItems.COPPER_INGOT, 2)});
        registerRecipe(20, new ItemStack[]{STItems.SILVER_ORE}, new ItemStack[]{new CustomItemStack(SlimefunItems.SILVER_INGOT, 2)});
        registerRecipe(100, new ItemStack[]{STItems.TITANIUM_ORE}, new ItemStack[]{STItems.TITANIUM});
        registerRecipe(100, new ItemStack[]{STItems.NEODYMIUM_ORE}, new ItemStack[]{STItems.NEODYMIUM});
    }

    @Override
    public @NotNull String getMachineIdentifier() {
        return name;
    }

    public abstract int getCapacity();

    public abstract int getEnergyConsumption();

    public abstract int getSpeed();
}
