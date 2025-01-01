package com.narcissu14.spacetech.setup;

import com.narcissu14.spacetech.container.PointMachineRecipe;
import com.narcissu14.spacetech.objects.blocks.AbstractPointsMachine;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UUMachine extends AbstractPointsMachine {

    public UUMachine(@NotNull ItemGroup category, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(category, item, recipeType, recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, int uuAmount) {
        registerRecipe(new PointMachineRecipe(seconds, input, uuAmount, new ItemStack[]{}));
    }

    private @NotNull Inventory inject(@NotNull Block b) {
        int size = StorageCacheUtils.getMenu(b.getLocation()).toInventory().getSize();
        Inventory inv = Bukkit.createInventory(null, size);
        for (int i = 0; i < size; i++) {
            inv.setItem(i, new CustomItemStack(Material.COMMAND_BLOCK, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US"));
        }
        for (int slot : getOutputSlots()) {
            inv.setItem(slot, StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot));
        }
        return inv;
    }

    protected boolean fits(@NotNull Block b, ItemStack[] items) {
        return inject(b).addItem(items).isEmpty();
    }

    public @NotNull String getInventoryTitle() {
        return "§d元物质分离机";
    }

    public void registerDefaultRecipes() {
        registerRecipe(5, new ItemStack[]{SlimefunItems.STONE_CHUNK}, 210);
        registerRecipe(5, new ItemStack[]{new ItemStack(Material.BONE)}, 200);
        registerRecipe(4, new ItemStack[]{SlimefunItems.WHEAT_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.CARROT_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.POTATO_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.SEEDS_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.BEETROOT_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.MELON_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.APPLE_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.SWEET_BERRIES_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.KELP_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.COCOA_FERTILIZER}, 250);
        registerRecipe(4, new ItemStack[]{SlimefunItems.SEAGRASS_FERTILIZER}, 250);
        registerRecipe(3, new ItemStack[]{new ItemStack(Material.DIAMOND)}, 500);
        registerRecipe(3, new ItemStack[]{new ItemStack(Material.EMERALD)}, 500);
    }

    public int getEnergyConsumption() {
        return 400;
    }

    public int getLevel() {
        return 3;
    }

    public @NotNull String getMachineIdentifier() {
        return getId();
    }

    @Override
    public ItemStack getPointsItem() {
        return new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
    }

    @Override
    public int getPointsMax() {
        return 100000;
    }

    @Override
    public String getPointsName() {
        return "§5§l反物质:§e ";
    }

    /**
     * 新增额外的GUI按钮、界面设定
     *
     * @param menu
     * @param b
     */
    @Override
    public void addExtraMenuHandler(BlockMenu menu, Block b) {

    }

    /**
     * 检测比对物品是否为可消耗点数充点的物品
     * 如果物品不是可充物品或点数已满，则返回false
     *
     * @param input
     */
    @Override
    public boolean isChargeableItem(ItemStack input) {
        return false;
    }

    /**
     * 获取指定物品的点数
     * WARNING: 为了效率此处没有检测是否为可充点物品
     * 请在每次使用前确保物品为可充点物品
     *
     * @param input
     */
    @Override
    public int getItemPoints(ItemStack input) {
        return 0;
    }

    /**
     * 修改指定物品的点数
     * WARNING: 为了效率此处没有检测是否为可充点物品
     * 请在每次使用前确保物品为可充点物品
     *
     * @param input
     * @param points
     * @param isAdd
     */
    @Override
    public @Nullable ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd) {
        return null;
    }

    public int getCapacity() {
        return 12800;
    }

    @Override
    public int getSpeed() {
        return 1;
    }
}