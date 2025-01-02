package com.narcissu14.spacetech.setup;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.objects.RechargeableItem;
import com.narcissu14.spacetech.objects.STCategories;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.objects.blocks.AntiMatterCollectMachine;
import com.narcissu14.spacetech.objects.blocks.BedrockBreakMachine;
import com.narcissu14.spacetech.objects.blocks.BedrockCrackMachine;
import com.narcissu14.spacetech.objects.blocks.HydrazineMachine;
import com.narcissu14.spacetech.objects.blocks.OreRefiningMachine;
import com.narcissu14.spacetech.objects.blocks.OxygenChargeMachine;
import com.narcissu14.spacetech.objects.blocks.SpaceGPSMarkMachine;
import com.narcissu14.spacetech.objects.blocks.SpaceOre;
import com.narcissu14.spacetech.objects.blocks.UnitItemMachine;
import com.narcissu14.spacetech.setup.config.STConfig;
import com.narcissu14.spacetech.utils.ActionBarAPI;
import com.narcissu14.spacetech.utils.ItemEnergy;
import com.narcissu14.spacetech.utils.TitleAPI;
import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.SolarGenerator;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Narcissu14
 */
public class STItemSetup {
    private static final RecipeType RECIPE_TYPE_UNIT_ITEM_CRAFTER = new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "unit_item_crafter"), STItems.UNIT_ITEM_CRAFTER);
    private static final RecipeType RECIPE_TYPE_GET_BY_TOOL = new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "get_by_tool"), new CustomItemStack(Material.IRON_PICKAXE, "§a使用挖掘工具挖取"));

    @SuppressWarnings("deprecation")
    // 虽然带耐久度的构造函数废弃了,但依旧十分好用
    public static void setupItems() {
        new SlimefunItem(STCategories.RESOURCES, STItems.COPPER_ORE_NUGGET, RECIPE_TYPE_GET_BY_TOOL, new ItemStack[0])
                .register(SpaceTech.getInstance());
        new SpaceOre(STCategories.MACHINES, STItems.COPPER_ORE, STItems.COPPER_ORE_NUGGET, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET,
                STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET,
                STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET
        }, "100").register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.RESOURCES, STItems.SILVER_ORE_NUGGET, RECIPE_TYPE_GET_BY_TOOL, new ItemStack[0])
                .register(SpaceTech.getInstance());
        new SpaceOre(STCategories.MACHINES, STItems.SILVER_ORE, STItems.SILVER_ORE_NUGGET, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET,
                STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET,
                STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET
        }, "100").register(SpaceTech.getInstance());
        //钛
        new SlimefunItem(STCategories.RESOURCES,  STItems.TITANIUM_ORE_NUGGET, RECIPE_TYPE_GET_BY_TOOL, new ItemStack[0])
                .register(SpaceTech.getInstance());
        new SpaceOre(STCategories.MACHINES, STItems.TITANIUM_ORE, STItems.TITANIUM_ORE_NUGGET,  RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET,
                STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET,
                STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET
        }, "250").register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.RESOURCES, STItems.TITANIUM, new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "ore_refining_machine"), STItems.ELEC_REFINING_MACHINE_1), new ItemStack[0])
                .register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.MISC, STItems.TITANIUM_PLATE, RecipeType.COMPRESSOR, new ItemStack[]{
                STItems.TITANIUM, STItems.TITANIUM, STItems.TITANIUM,
                STItems.TITANIUM, null, STItems.TITANIUM,
                STItems.TITANIUM, STItems.TITANIUM, STItems.TITANIUM
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.MISC, STItems.ALUMINIUM_PLATE, RecipeType.COMPRESSOR, new ItemStack[]{
                SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT
        }).register(SpaceTech.getInstance());
        //钕
        new SlimefunItem(STCategories.RESOURCES, STItems.NEODYMIUM_ORE_NUGGET, RECIPE_TYPE_GET_BY_TOOL, new ItemStack[0])
                .register(SpaceTech.getInstance());
        new SpaceOre(STCategories.MACHINES, STItems.NEODYMIUM_ORE, STItems.NEODYMIUM_ORE_NUGGET, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET,
                STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET,
                STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET
        }, "250").register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.RESOURCES, STItems.NEODYMIUM, new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "ore_refining_machine"), STItems.ELEC_REFINING_MACHINE_1), new ItemStack[0])
                .register(SpaceTech.getInstance());

        new UUMachine(STCategories.MACHINES, STItems.UU_MACHINE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.PLASTIC_SHEET, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.PLASTIC_SHEET, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS})
                .register(SpaceTech.getInstance());
        (new SlimefunItem(STCategories.MISC, STItems.UU, new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "uu_item_creator"), STItems.UU_MACHINE), new ItemStack[]{null, null, null, null, new CustomItemStack(Material.GHAST_TEAR, "§e通过元物质分离机收集"), null, null, null, null
        })).register(SpaceTech.getInstance());
        (new SlimefunItem(STCategories.RESOURCES, STItems.IRIDIUM, new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "uu_item_creator"), STItems.ITEM_CREATOR), new ItemStack[]{null, null, null, null, new CustomItemStack(STItems.ITEM_CREATOR, "&d物质制造机", "", "§e通过物质制造机制作"), null, null, null, null
        })).register(SpaceTech.getInstance());
        (new CreatorMachine(STCategories.MACHINES, STItems.ITEM_CREATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{STItems.UU, SlimefunItems.CARBONADO, STItems.UU, SlimefunItems.NETHER_ICE_COOLANT_CELL, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.WITHER_PROOF_GLASS}) {
            public @NotNull String getInventoryTitle() {
                return "&d物质制造机";
            }

            public @NotNull ItemStack getProgressBar() {
                return new ItemStack(Material.FLINT_AND_STEEL);
            }

            public void registerDefaultRecipes() {
                registerRecipe(36, new ItemStack[]{STItems.UU}, new ItemStack[]{STItems.IRIDIUM});
                registerRecipe(36, new ItemStack[]{STItems.UU}, new ItemStack[]{SlimefunItems.NEPTUNIUM});
                registerRecipe(36, new ItemStack[]{STItems.UU}, new ItemStack[]{SlimefunItems.PLUTONIUM});
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
            @Override
            public int getSpeed() {
                return 1;
            }
        }).register(SpaceTech.getInstance());

        //电磁炼矿机-1
        new OreRefiningMachine(STCategories.MACHINES, STItems.ELEC_REFINING_MACHINE_1, "ELEC_REFINING_MACHINE_1", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.REDSTONE_ALLOY, SlimefunItems.ELECTRIC_SMELTERY_2, SlimefunItems.REDSTONE_ALLOY,
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.HEATING_COIL, SlimefunItems.POWER_CRYSTAL
        }) {
            @Override
            public int getEnergyConsumption() {
                return 64;
            }

            @Override
            public int getSpeed() {
                return 1;
            }

            @Override
            public int getCapacity() {
                return 512;
            }
        }.register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.STEEL_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT,
                null, new ItemStack(Material.STICK), null,
                null, new ItemStack(Material.STICK), null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.DAMASCUS_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
                null, STItems.STEEL_PICKAXE, null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.HARDENED_METAL_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT,
                null, STItems.DAMASCUS_PICKAXE, null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.REINFORCED_ALLOY_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                null, STItems.HARDENED_METAL_PICKAXE, null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.CARBONADO_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, SlimefunItems.CARBONADO,
                null, STItems.REINFORCED_ALLOY_PICKAXE, null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.SUPER_CIRCUIT_BOARD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.NEODYMIUM, SlimefunItems.ADVANCED_CIRCUIT_BOARD, STItems.NEODYMIUM,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, STItems.IRIDIUM, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                STItems.NEODYMIUM, SlimefunItems.ADVANCED_CIRCUIT_BOARD, STItems.NEODYMIUM
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.EMPTY_UNIT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_PLATE, STItems.ALUMINIUM_PLATE, SlimefunItems.STEEL_PLATE,
                STItems.ALUMINIUM_PLATE, SlimefunItems.HARDENED_GLASS, STItems.ALUMINIUM_PLATE,
                SlimefunItems.STEEL_PLATE, STItems.ALUMINIUM_PLATE, SlimefunItems.STEEL_PLATE
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.ARMOR, STItems.SPACE_HELMET_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_PLATE, STItems.ALUMINIUM_PLATE, SlimefunItems.STEEL_PLATE,
                STItems.ALUMINIUM_PLATE, SlimefunItems.WITHER_PROOF_GLASS, STItems.ALUMINIUM_PLATE,
                SlimefunItems.ZINC_INGOT, STItems.EMPTY_UNIT, SlimefunItems.ZINC_INGOT
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.ARMOR, STItems.SPACE_HELMET_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.PLASTIC_SHEET, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                STItems.ALUMINIUM_PLATE, STItems.SPACE_HELMET_1, STItems.ALUMINIUM_PLATE,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_ALLOY_INGOT
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.ARMOR, STItems.SPACE_HELMET_3, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.PLASTIC_SHEET, STItems.TITANIUM, SlimefunItems.PLASTIC_SHEET,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.SPACE_HELMET_2, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_PLATE, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_PLATE
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.ARMOR, STItems.SPACE_HELMET_4, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.PLASTIC_SHEET, STItems.SUPER_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                STItems.TITANIUM_PLATE, STItems.SPACE_HELMET_3, STItems.TITANIUM_PLATE,
                SlimefunItems.REINFORCED_PLATE, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_PLATE
        }).register(SpaceTech.getInstance());
        new OxygenChargeMachine(STCategories.MACHINES, STItems.OXYGEN_CHARGE_MACHINE, "OXYGEN_CHARGE_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.ALUMINIUM_PLATE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, STItems.ALUMINIUM_PLATE,
                SlimefunItems.STEEL_PLATE, STItems.EMPTY_UNIT, SlimefunItems.STEEL_PLATE,
                SlimefunItems.STEEL_PLATE, SlimefunItems.STEEL_THRUSTER, SlimefunItems.STEEL_PLATE
        }, new ItemStack(Material.CYAN_STAINED_GLASS_PANE, 1), 10000, "§b氧气:§e ") {
            @Override
            public int getCapacity() {
                return 256;
            }
        }.register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.UNIT_HYDRAZINE, new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "unit_hydrazine"), STItems.HYDRAZINE_MACHINE), new ItemStack[]{}).register(SpaceTech.getInstance());
        new HydrazineMachine(STCategories.MACHINES, STItems.HYDRAZINE_MACHINE, "HYDRAZINE_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SILVER_INGOT, SlimefunItems.BIO_REACTOR, SlimefunItems.SILVER_INGOT,
                STItems.ALUMINIUM_PLATE, STItems.EMPTY_UNIT, STItems.ALUMINIUM_PLATE,
                SlimefunItems.STEEL_PLATE, SlimefunItems.HEATED_PRESSURE_CHAMBER_2, SlimefunItems.STEEL_PLATE
        }, new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE, 1), 1000, "§5联氨:§e ") {
            @Override
            public int getCapacity() {
                return 512;
            }
        }.register(SpaceTech.getInstance());
        new SpaceGPSMarkMachine(STCategories.MACHINES, STItems.SPACE_GPS_MARK_MACHINE, "SPACE_GPS_MARK_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SOLAR_GENERATOR_2, SlimefunItems.GPS_CONTROL_PANEL, SlimefunItems.SOLAR_GENERATOR_2,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.STEEL_THRUSTER, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.STEEL_THRUSTER
        }, new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE, 1), 10000, "§5联氨燃料:§e ") {
            @Override
            public int getEnergyConsumption() {
                return 16;
            }

            @Override
            public int getCapacity() {
                return 128;
            }

            @Override
            public int getSpeed() {
                return 1;
            }
        }.register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.HARD_AXLE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.BEDROCK_DRILL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT,
                null, SlimefunItems.STEEL_INGOT, null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new BedrockBreakMachine(STCategories.MACHINES, STItems.BEDROCK_BREAK_MACHINE, "BEDROCK_BREAK_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, STItems.HARD_AXLE, SlimefunItems.WITHER_PROOF_OBSIDIAN
        }, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1), 10000, "§a挖掘度: §e") {
            @Override
            public int getEnergyConsumption() {
                return 128;
            }

            @Override
            public int getCapacity() {
                return 1024;
            }

            @Override
            public int getSpeed() {
                return 1;
            }
        }.register(SpaceTech.getInstance());
        RechargeableItem createrCodeScanner = new RechargeableItem(STCategories.ARMOR, STItems.ITEM_CREATER_CODE_SCANNER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.GOLD_24K, SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.PLASTIC_SHEET, STItems.SUPER_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                SlimefunItems.GOLD_24K, SlimefunItems.REINFORCED_PLATE, SlimefunItems.GOLD_24K
        }, 2048);
        createrCodeScanner.addItemHandler((ItemUseHandler) rightClickEvent -> {
            Player player = rightClickEvent.getPlayer();
            ItemStack itemStack = rightClickEvent.getItem();
            if (SlimefunUtils.isItemSimilar(itemStack, STItems.ITEM_CREATER_CODE_SCANNER, false)) {
                if (!player.isSneaking()) {
                    rightClickEvent.cancel();
                    return;
                }
                if (!rightClickEvent.getClickedBlock().isPresent()) {
                    return;
                }
                Block block = rightClickEvent.getClickedBlock().get();
                //检查是否为物质制造机
                SlimefunItem item = StorageCacheUtils.getSfItem(block.getLocation());
                if (item != null && item.getId().equals("ITEM_CREATOR")) {
                    //耗电
                    float charge = ItemEnergy.getStoredEnergy(itemStack);
                    float cost = 2048F;
                    if (charge >= cost) {
                        player.getInventory().setItemInMainHand((ItemEnergy.chargeItem(itemStack, -cost)));
                        player.playSound(player.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, 0.7f, 0.6f);
                        player.spawnParticle(Particle.END_ROD, block.getLocation().add(0, 0.6, 0), 10);
                        ActionBarAPI.sendActionBar(player, "§6§l解译中...需要花费10秒");
                        //检测
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                String fullCode = Integer.toBinaryString(Integer.parseInt(StorageCacheUtils.getData(block.getLocation(), "random-code")));
                                String code = fullCode.substring(0, 2);
                                TitleAPI.sendTitle(player, 1, 2, 1, "§b解译成功", "§9首2位:§e " + code);
                                player.sendMessage("§b当前机器物质编码首2位:§e " + code);
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 0.8f);
                            }
                        }.runTaskLaterAsynchronously(SpaceTech.getInstance(), 200);
                    }
                } else {
                    ActionBarAPI.sendActionBar(player, "§c§l你尝试解译的机器不是物质制造机！");
                }
            }
            rightClickEvent.cancel();
        });
        createrCodeScanner.register(SpaceTech.getInstance());
        RechargeableItem spaceOreScanner = new RechargeableItem(STCategories.ARMOR, STItems.SPACE_ORE_SCANNER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ELECTRO_MAGNET, STItems.HARD_AXLE, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.PLASTIC_SHEET, STItems.SUPER_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                SlimefunItems.PLASTIC_SHEET, STItems.TITANIUM_PLATE, SlimefunItems.PLASTIC_SHEET
        }, 256);
        spaceOreScanner.addItemHandler((ItemUseHandler) rightClickEvent -> {
            Player player = rightClickEvent.getPlayer();
            ItemStack itemStack = rightClickEvent.getItem();
            if (SlimefunUtils.isItemSimilar(itemStack, STItems.SPACE_ORE_SCANNER, false)) {
                if (!STConfig.spaceWorldList.contains(player.getWorld().getName())) {
                    player.sendMessage("§c§l你只能在SpaceWorld才能使用该物品！");
                    rightClickEvent.cancel();
                    return;
                }
                if (!player.isSneaking()) {
                    player.sendMessage("§c§l你必须潜行才可以使用该物品！");
                    rightClickEvent.cancel();
                    return;
                }
                float charge = ItemEnergy.getStoredEnergy(itemStack);
                float cost = 2F;
                if (charge < cost) {
                    rightClickEvent.cancel();
                    return;
                }
                player.getInventory().setItemInMainHand((ItemEnergy.chargeItem(itemStack, -cost)));
                ActionBarAPI.sendActionBar(player, "§7§l检测中...");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.8f);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!rightClickEvent.getClickedBlock().isPresent()) {
                            return;
                        }
                        Location loc = rightClickEvent.getClickedBlock().get().getLocation();
                        HashMap<Location, String> blocksMap = new HashMap<>();
                        //检测半径4格
                        for (int x = -4; x < 5; x++) {
                            for (int y = -4; y < 5; y++) {
                                for (int z = -4; z < 5; z++) {
                                    Block block = loc.clone().add(x, y, z).getBlock();
                                    Material type = block.getType();
                                    if (type != Material.PLAYER_HEAD && type != Material.PLAYER_WALL_HEAD) {
                                        continue;
                                    }
                                    SlimefunItem item = StorageCacheUtils.getSfItem(block.getLocation());
                                    if (item == null) {
                                        continue;
                                    }
                                    String id = item.getId();
                                    if (STItems.SPACE_ORE_LIST.contains(id)) {
                                        blocksMap.put(block.getLocation(), SlimefunItem.getById(id).getItem().getItemMeta().getDisplayName());
                                    }
                                }
                            }
                        }
                        if (!blocksMap.isEmpty()) {
                            ActionBarAPI.sendActionBar(player, "§a发现了 §e" + blocksMap.size() + " §a矿物");
                            for (Entry<Location, String> entry : blocksMap.entrySet()) {
                                int distance = (int) entry.getKey().distance(loc);
                                player.sendMessage(entry.getValue() + " §7距离: §e" + distance);
                            }
                        } else {
                            ActionBarAPI.sendActionBar(player, "§c很遗憾周围没有检测到矿物");
                        }
                    }
                }.runTaskLaterAsynchronously(SpaceTech.getInstance(), 100);
            }
            rightClickEvent.cancel();
        });
        spaceOreScanner.register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.EM_RESTRAINT_DEVICE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, STItems.NEODYMIUM, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.ELECTRO_MAGNET, STItems.EMPTY_UNIT, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.REINFORCED_PLATE, STItems.NEODYMIUM, SlimefunItems.REINFORCED_PLATE
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.EMPTY_EM_UNIT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.EMPTY_UNIT, null,
                STItems.EMPTY_UNIT, STItems.EM_RESTRAINT_DEVICE, STItems.EMPTY_UNIT,
                null, STItems.EMPTY_UNIT, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.ANTIMATTER_EM_UNIT, new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "antimatter_collect_machine"), STItems.ANTIMATTER_COLLECT_MACHINE), new ItemStack[]{STItems.EMPTY_EM_UNIT}).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.BEDROCK_EM_UNIT, new RecipeType(new NamespacedKey(SpaceTech.getInstance(), "bedrock_crack_machine"), STItems.BEDROCK_CRACK_MACHINE), new ItemStack[]{STItems.EMPTY_EM_UNIT}).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.BEDROCK_EM_UNIT_3, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.BEDROCK_EM_UNIT, null,
                new ItemStack(Material.STRING), STItems.BEDROCK_EM_UNIT, new ItemStack(Material.STRING),
                null, STItems.BEDROCK_EM_UNIT, null
        }).register(SpaceTech.getInstance());
        new SolarGenerator(STCategories.MACHINES, 32, 32, STItems.PARTICLE_GENERATOR_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.LEAD_INGOT, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.LEAD_INGOT,
                SlimefunItems.REINFORCED_PLATE, STItems.EM_RESTRAINT_DEVICE, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.POWER_CRYSTAL
        }) {
            @Override
            public int getGeneratedOutput(Location l, SlimefunBlockData data) {
                World world = l.getWorld();
                for (String spaceName : STConfig.spaceWorldList) {
                    if (world.getName().equals(spaceName)) {
                        return getNightEnergy();
                    }
                }

                return 0;
            }
        }.register(SpaceTech.getInstance());
        new SolarGenerator(STCategories.MACHINES, 64, 64, STItems.PARTICLE_GENERATOR_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM, SlimefunItems.ELECTRO_MAGNET, STItems.TITANIUM,
                STItems.PARTICLE_GENERATOR_1, STItems.EM_RESTRAINT_DEVICE, STItems.PARTICLE_GENERATOR_1,
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.POWER_CRYSTAL
        }) {
            @Override
            public int getGeneratedOutput(Location l, SlimefunBlockData data) {
                World world = l.getWorld();
                for (String spaceName : STConfig.spaceWorldList) {
                    if (world.getName().equals(spaceName)) {
                        return getNightEnergy();
                    }
                }

                return 0;
            }
        }.register(SpaceTech.getInstance());
        new SolarGenerator(STCategories.MACHINES, 128, 128, STItems.PARTICLE_GENERATOR_3, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM_PLATE, SlimefunItems.ELECTRO_MAGNET, STItems.TITANIUM_PLATE,
                STItems.PARTICLE_GENERATOR_2, STItems.EM_RESTRAINT_DEVICE, STItems.PARTICLE_GENERATOR_2,
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.POWER_CRYSTAL
        }) {
            @Override
            public int getGeneratedOutput(Location l, SlimefunBlockData data) {
                World world = l.getWorld();
                for (String spaceName : STConfig.spaceWorldList) {
                    if (world.getName().equals(spaceName)) {
                        return getNightEnergy();
                    }
                }

                return 0;
            }
        }.register(SpaceTech.getInstance());
        new AntiMatterCollectMachine(STCategories.MACHINES, STItems.ANTIMATTER_COLLECT_MACHINE, "ANTIMATTER_COLLECT_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM_PLATE, SlimefunItems.WITHER_PROOF_GLASS, STItems.TITANIUM_PLATE,
                STItems.EM_RESTRAINT_DEVICE, STItems.EMPTY_EM_UNIT, STItems.EM_RESTRAINT_DEVICE,
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE
        }, new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1), 65536, "§5反物质:§e ") {
            @Override
            public int getEnergyConsumption() {
                return 128;
            }

            @Override
            public int getCapacity() {
                return 1024;
            }

            @Override
            public int getSpeed() {
                return 1;
            }
        }.register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.ANTIMATTER_BEDROCK_DRILL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HARDENED_METAL_INGOT, STItems.ANTIMATTER_EM_UNIT, SlimefunItems.HARDENED_METAL_INGOT,
                null, STItems.BEDROCK_DRILL, null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new BedrockCrackMachine(STCategories.MACHINES, STItems.BEDROCK_CRACK_MACHINE, "BEDROCK_CRACK_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE,
                STItems.TITANIUM_PLATE, STItems.BEDROCK_BREAK_MACHINE, STItems.TITANIUM_PLATE,
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE
        }, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1), 10000, "§a爆碎度: §e") {
            @Override
            public int getEnergyConsumption() {
                return 256;
            }

            @Override
            public int getCapacity() {
                return 2048;
            }

            @Override
            public int getSpeed() {
                return 1;
            }
        }.register(SpaceTech.getInstance());
        new UnitItemMachine(STCategories.MACHINES, STItems.UNIT_ITEM_CRAFTER, "UNIT_ITEM_CRAFTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.STEEL_THRUSTER, SlimefunItems.REINFORCED_PLATE,
                STItems.TITANIUM_PLATE, SlimefunItems.HEATED_PRESSURE_CHAMBER_2, STItems.TITANIUM_PLATE,
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE
        }) {
            @Override
            public int getEnergyConsumption() {
                return 128;
            }

            @Override
            public int getCapacity() {
                return 1024;
            }
        }.register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TECH_MISC, STItems.RIG, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.HARD_AXLE, null,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
        }).register(SpaceTech.getInstance());
        new RechargeableItem(STCategories.ARMOR, STItems.ORE_RIG_REINFORCED, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.TITANIUM, SlimefunItems.REINFORCED_ALLOY_INGOT,
                null, STItems.RIG, null,
        }, 256) {
            @Override
            public void preRegister() {
                addItemHandler((ToolUseHandler) (event, tool, fortune, drops) -> {
                    SlimefunItem item = SlimefunItem.getByItem(tool);
                    if (item instanceof RechargeableItem rechargeableItem) {
                        if (rechargeableItem.getItemCharge(tool) < 2) {
                            Player player = event.getPlayer();
                            player.sendMessage("§c电量不足！");
                            event.setCancelled(true);
                            return;
                        }
                        rechargeableItem.removeItemCharge(tool, 2);
                    }
                });
            }
        }.register(SpaceTech.getInstance());
        new RechargeableItem(STCategories.ARMOR, STItems.ORE_RIG_CARBONADO, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, SlimefunItems.CARBONADO, null,
                SlimefunItems.CARBONADO, STItems.TITANIUM, SlimefunItems.CARBONADO,
                null, STItems.ORE_RIG_REINFORCED, null,
        }, 512) {
            @Override
            public void preRegister() {
                addItemHandler((ToolUseHandler) (event, tool, fortune, drops) -> {
                    SlimefunItem item = SlimefunItem.getByItem(tool);
                    if (item instanceof RechargeableItem rechargeableItem) {
                        if (rechargeableItem.getItemCharge(tool) < 2) {
                            Player player = event.getPlayer();
                            player.sendMessage("§c电量不足！");
                            event.setCancelled(true);
                            return;
                        }
                        rechargeableItem.removeItemCharge(tool, 2);
                    }
                });
            }
        }.register(SpaceTech.getInstance());
        new RechargeableItem(STCategories.ARMOR, STItems.ORE_RIG_TITANIUM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.TITANIUM, null,
                STItems.TITANIUM, STItems.TITANIUM, STItems.TITANIUM,
                null, STItems.ORE_RIG_CARBONADO, null,
        }, 1024) {
            @Override
            public void preRegister() {
                addItemHandler((ToolUseHandler) (event, tool, fortune, drops) -> {
                    SlimefunItem item = SlimefunItem.getByItem(tool);
                    if (item instanceof RechargeableItem rechargeableItem) {
                        if (rechargeableItem.getItemCharge(tool) < 2) {
                            Player player = event.getPlayer();
                            player.sendMessage("§c电量不足！");
                            event.setCancelled(true);
                            return;
                        }
                        rechargeableItem.removeItemCharge(tool, 2);
                    }
                });
            }
        }.register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.BEDROCK_DIAMOND_AXE, RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 3), new ItemStack(Material.DIAMOND_AXE), null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.BEDROCK_DIAMOND_PICKAXE, RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 3), new ItemStack(Material.DIAMOND_PICKAXE), null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.BEDROCK_DIAMOND_SWORD, RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 2), new ItemStack(Material.DIAMOND_SWORD), null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.BEDROCK_DIAMOND_HOE, RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 2), new ItemStack(Material.DIAMOND_HOE), null,
                null, null, null
        }).register(SpaceTech.getInstance());
        new SlimefunItem(STCategories.TOOL, STItems.BEDROCK_DIAMOND_SPADE, RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 1), new ItemStack(Material.DIAMOND_SHOVEL), null,
                null, null, null
        }).register(SpaceTech.getInstance());
    }
}