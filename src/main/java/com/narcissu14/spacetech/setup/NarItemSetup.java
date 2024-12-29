package com.narcissu14.spacetech.setup;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.objects.STCategories;
import com.narcissu14.spacetech.utils.SlimefunItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class NarItemSetup {
    public static void setupItems() {
        (new UUMachine(STCategories.QUANTUM_MACHINES, NarItems.UU_MACHINE, "UU_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.PLASTIC_SHEET, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.PLASTIC_SHEET, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS}) {
            public int getUUFull() {
                return 100000;
            }

            public ItemStack @NotNull [] getOutput() {
                return new ItemStack[]{NarItems.UU};
            }

            public @NotNull String getInventoryTitle() {
                return "§d元物质分离机";
            }

            public @NotNull ItemStack getProgressBar() {
                return new ItemStack(Material.FLINT_AND_STEEL);
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
                return 1;
            }

            public @NotNull String getMachineIdentifier() {
                return getId();
            }

            public int getCapacity() {
                return 12800;
            }
        }).register(SpaceTech.getInstance());
        (new SlimefunItem(SlimefunItemGroups.misc, new SlimefunItemStack("SPACETECH_UU", NarItems.UU), new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "uu_item_creator"), NarItems.UU_MACHINE), new ItemStack[]{null, null, null, null, new CustomItemStack(Material.GHAST_TEAR, "§e通过元物质分离机收集"), null, null, null, null
        })).register(SpaceTech.getInstance());
        (new SlimefunItem(SlimefunItemGroups.resources, new SlimefunItemStack("SPACETECH_IRIDIUM", NarItems.IRIDIUM), new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "uu_item_creator"), NarItems.ITEM_CREATOR), new ItemStack[]{null, null, null, null, new CustomItemStack(NarItems.ITEM_CREATOR, "&d物质制造机", "", "§e通过物质制造机制作"), null, null, null, null
        })).register(SpaceTech.getInstance());
        (new CreatorMachine(STCategories.QUANTUM_MACHINES, NarItems.ITEM_CREATOR, "ITEM_CREATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{NarItems.UU, SlimefunItems.CARBONADO, NarItems.UU, SlimefunItems.NETHER_ICE_COOLANT_CELL, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.WITHER_PROOF_GLASS}) {
            public @NotNull String getInventoryTitle() {
                return "&d物质制造机";
            }

            public @NotNull ItemStack getProgressBar() {
                return new ItemStack(Material.FLINT_AND_STEEL);
            }

            public void registerDefaultRecipes() {
                registerRecipe(36, new ItemStack[]{NarItems.UU}, new ItemStack[]{NarItems.IRIDIUM});
                registerRecipe(36, new ItemStack[]{NarItems.UU}, new ItemStack[]{SlimefunItems.NEPTUNIUM});
                registerRecipe(36, new ItemStack[]{NarItems.UU}, new ItemStack[]{SlimefunItems.PLUTONIUM});
            }

            public int getEnergyConsumption() {
                return 768;
            }

            public @NotNull String getMachineIdentifier() {
                return getId();
            }

            public int getCapacity() {
                return 65535;
            }
        }).register(SpaceTech.getInstance());
    }
}