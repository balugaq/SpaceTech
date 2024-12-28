package com.narcissu14.spacetech.setup;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.objects.STCategories;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.objects.blocks.*;
import com.narcissu14.spacetech.setup.config.STConfig;
import com.narcissu14.spacetech.utils.ActionBarAPI;
import com.narcissu14.spacetech.utils.TitleAPI;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.events.ItemUseEvent;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.NarItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemInteractionHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;
import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author Narcissu14
 */
public class STItemSetup {
    private static final RecipeType RECIPE_TYPE_UNIT_ITEM_CRAFTER = new RecipeType(STItems.UNIT_ITEM_CRAFTER);

    @SuppressWarnings("deprecation")
    // 虽然带耐久度的构造函数废弃了,但依旧十分好用
	public static void setupItems() {
        new SlimefunItem(Categories.RESOURCES, STItems.COPPER_ORE_NUGGET, "COPPER_ORE_NUGGET", new RecipeType(new CustomItem(Material.IRON_PICKAXE, "§a使用挖掘工具挖取", 0)), new ItemStack[0])
                .register(true);

        new SpaceOre(STCategories.ST_MACHINES, STItems.COPPER_ORE, STItems.COPPER_ORE_NUGGET, "COPPER_ORE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET,
                STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET,
                STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET, STItems.COPPER_ORE_NUGGET
        }, "100").register(true);

        new SlimefunItem(Categories.RESOURCES, STItems.SILVER_ORE_NUGGET, "SILVER_ORE_NUGGET", new RecipeType(new CustomItem(Material.IRON_PICKAXE, "§a使用挖掘工具挖取", 0)), new ItemStack[0])
                .register(true);

        new SpaceOre(STCategories.ST_MACHINES, STItems.SILVER_ORE, STItems.SILVER_ORE_NUGGET, "SILVER_ORE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET,
                STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET,
                STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET, STItems.SILVER_ORE_NUGGET
        }, "100").register(true);

        //钛
        new SlimefunItem(Categories.RESOURCES, STItems.TITANIUM_ORE_NUGGET, "TITANIUM_ORE_NUGGET", new RecipeType(new CustomItem(Material.IRON_PICKAXE, "§a使用挖掘工具挖取", 0)), new ItemStack[0])
                .register(true);

        new SpaceOre(STCategories.ST_MACHINES, STItems.TITANIUM_ORE, STItems.TITANIUM_ORE_NUGGET, "TITANIUM_ORE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET,
                STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET,
                STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET, STItems.TITANIUM_ORE_NUGGET
        }, "250").register(true);

        new SlimefunItem(Categories.RESOURCES, STItems.TITANIUM, "TITANIUM", new RecipeType(new CustomItem(Material.IRON_PICKAXE, "§a使用挖掘工具挖取", 0)), new ItemStack[0])
                .register(true);

        new SlimefunItem(Categories.MISC, STItems.TITANIUM_PLATE, "TITANIUM_PLATE", RecipeType.COMPRESSOR, new ItemStack[]{
                STItems.TITANIUM, STItems.TITANIUM, STItems.TITANIUM,
                STItems.TITANIUM, null, STItems.TITANIUM,
                STItems.TITANIUM, STItems.TITANIUM, STItems.TITANIUM
        }).register(true);

        new SlimefunItem(Categories.MISC, STItems.ALUMINIUM_PLATE, "ALUMINIUM_PLATE", RecipeType.COMPRESSOR, new ItemStack[]{
                SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT
        }).register(true);

        //钕
        new SlimefunItem(Categories.RESOURCES, STItems.NEODYMIUM_ORE_NUGGET, "NEODYMIUM_ORE_NUGGET", new RecipeType(new CustomItem(Material.IRON_PICKAXE, "§a使用挖掘工具挖取", 0)), new ItemStack[0])
                .register(true);

        new SpaceOre(STCategories.ST_MACHINES, STItems.NEODYMIUM_ORE, STItems.NEODYMIUM_ORE_NUGGET, "NEODYMIUM_ORE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET,
                STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET,
                STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET, STItems.NEODYMIUM_ORE_NUGGET
        }, "250").register(true);

        new SlimefunItem(Categories.RESOURCES, STItems.NEODYMIUM, "NEODYMIUM", new RecipeType(new CustomItem(Material.IRON_PICKAXE, "§a使用挖掘工具挖取", 0)), new ItemStack[0])
                .register(true);

        //电磁炼矿机-1
        new OreRefiningMachine(STCategories.ST_MACHINES, STItems.ELEC_REFINING_MACHINE_1, "ELEC_REFINING_MACHINE_1", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
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
        }.registerChargeableBlock(true, 512);

        new SlimefunItem(Categories.TOOLS, STItems.STEEL_PICKAXE, "STEEL_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT,
                null, new ItemStack(Material.STICK), null,
                null, new ItemStack(Material.STICK), null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.DAMASCUS_PICKAXE, "DAMASCUS_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
                null, STItems.STEEL_PICKAXE, null,
                null, null, null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.HARDENED_METAL_PICKAXE, "HARDENED_METAL_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT,
                null, STItems.DAMASCUS_PICKAXE, null,
                null, null, null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.REINFORCED_ALLOY_PICKAXE, "REINFORCED_ALLOY_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                null, STItems.HARDENED_METAL_PICKAXE, null,
                null, null, null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.CARBONADO_PICKAXE, "CARBONADO_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.CARBONADO, SlimefunItems.CARBONADO, SlimefunItems.CARBONADO,
                null, STItems.REINFORCED_ALLOY_PICKAXE, null,
                null, null, null
        }).register(true);

        //对原版Slimefun进行适配
        new SlimefunItem(Categories.TECH_MISC, STItems.SUPER_CIRCUIT_BOARD, "SUPER_CIRCUIT_BOARD", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.NEODYMIUM, SlimefunItems.ADVANCED_CIRCUIT_BOARD, STItems.NEODYMIUM,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, STConfig.originalSlimefun ? SlimefunItems.REINFORCED_PLATE : NarItems.IRIDIUM, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                STItems.NEODYMIUM, SlimefunItems.ADVANCED_CIRCUIT_BOARD, STItems.NEODYMIUM
        }).register(true);

        new SlimefunItem(Categories.TECH_MISC, STItems.EMPTY_UNIT, "EMPTY_UNIT", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_PLATE, STItems.ALUMINIUM_PLATE, SlimefunItems.STEEL_PLATE,
                STItems.ALUMINIUM_PLATE, SlimefunItems.HARDENED_GLASS, STItems.ALUMINIUM_PLATE,
                SlimefunItems.STEEL_PLATE, STItems.ALUMINIUM_PLATE, SlimefunItems.STEEL_PLATE
        }).register(true);

        new SlimefunItem(Categories.TECH, STItems.SPACE_HELMET_1, "SPACE_HELMET_1", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_PLATE, STItems.ALUMINIUM_PLATE, SlimefunItems.STEEL_PLATE,
                STItems.ALUMINIUM_PLATE, SlimefunItems.WITHER_PROOF_GLASS, STItems.ALUMINIUM_PLATE,
                SlimefunItems.ZINC_INGOT, STItems.EMPTY_UNIT, SlimefunItems.ZINC_INGOT
        }).register(true);

        new SlimefunItem(Categories.TECH, STItems.SPACE_HELMET_2, "SPACE_HELMET_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.PLASTIC_SHEET, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                STItems.ALUMINIUM_PLATE, STItems.SPACE_HELMET_1, STItems.ALUMINIUM_PLATE,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_ALLOY_INGOT
        }).register(true);

        new SlimefunItem(Categories.TECH, STItems.SPACE_HELMET_3, "SPACE_HELMET_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.PLASTIC_SHEET, STItems.TITANIUM, SlimefunItems.PLASTIC_SHEET,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.SPACE_HELMET_2, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_PLATE, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_PLATE
        }).register(true);

        new SlimefunItem(Categories.TECH, STItems.SPACE_HELMET_4, "SPACE_HELMET_4", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.PLASTIC_SHEET, STItems.SUPER_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                STItems.TITANIUM_PLATE, STItems.SPACE_HELMET_3, STItems.TITANIUM_PLATE,
                SlimefunItems.REINFORCED_PLATE, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_PLATE
        }).register(true);

        new OxygenChargeMachine(STCategories.ST_MACHINES, STItems.OXYGEN_CHARGE_MACHINE, "OXYGEN_CHARGE_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.ALUMINIUM_PLATE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, STItems.ALUMINIUM_PLATE,
                SlimefunItems.STEEL_PLATE, STItems.EMPTY_UNIT, SlimefunItems.STEEL_PLATE,
                SlimefunItems.STEEL_PLATE, SlimefunItems.STEEL_THRUSTER, SlimefunItems.STEEL_PLATE
        }, new ItemStack(Material.CYAN_STAINED_GLASS_PANE, 1), 10000, "§b氧气:§e ")
                .registerChargeableBlock(true, 256);

        new SlimefunItem(Categories.TECH_MISC, STItems.UNIT_HYDRAZINE, "UNIT_HYDRAZINE", new RecipeType(STItems.HYDRAZINE_MACHINE), new ItemStack[]{}).register(true);

        new HydrazineMachine(STCategories.ST_MACHINES, STItems.HYDRAZINE_MACHINE, "HYDRAZINE_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SILVER_INGOT, SlimefunItems.BIO_REACTOR, SlimefunItems.SILVER_INGOT,
                STItems.ALUMINIUM_PLATE, STItems.EMPTY_UNIT, STItems.ALUMINIUM_PLATE,
                SlimefunItems.STEEL_PLATE, SlimefunItems.HEATED_PRESSURE_CHAMBER_2, SlimefunItems.STEEL_PLATE
        }, new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE, 1), 1000, "§5联氨:§e ")
                .registerChargeableBlock(true, 512);

        new SpaceGPSMarkMachine(STCategories.ST_MACHINES, STItems.SPACE_GPS_MARK_MACHINE, "SPACE_GPS_MARK_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SOLAR_GENERATOR_2, SlimefunItems.GPS_CONTROL_PANEL, SlimefunItems.SOLAR_GENERATOR_2,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.EMPTY_UNIT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.STEEL_THRUSTER, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.STEEL_THRUSTER
        }, new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE, 1), 10000, "§5联氨燃料:§e ") {
            @Override
            public int getEnergyConsumption() {
                return 16;
            }
        }.registerChargeableBlock(true, 128);

        new SlimefunItem(Categories.TECH_MISC, STItems.HARD_AXLE, "HARD_AXLE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT
        }).register(true);

        new SlimefunItem(Categories.TECH_MISC, STItems.BEDROCK_DRILL, "BEDROCK_DRILL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_INGOT,
                null, SlimefunItems.STEEL_INGOT, null,
                null, null, null
        }).register(true);

        new BedrockBreakMachine(STCategories.ST_MACHINES, STItems.BEDROCK_BREAK_MACHINE, "BEDROCK_BREAK_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, STItems.HARD_AXLE, SlimefunItems.WITHER_PROOF_OBSIDIAN
        }, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1), 10000, "§a挖掘度: §e") {
            @Override
            public int getEnergyConsumption() {
                return 128;
            }
        }.registerChargeableBlock(true, 1024);

        new SlimefunItem(Categories.TECH, STItems.ITEM_CREATER_CODE_SCANNER, "ITEM_CREATER_CODE_SCANNER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.GOLD_24K, SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.PLASTIC_SHEET, STItems.SUPER_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                SlimefunItems.GOLD_24K, SlimefunItems.REINFORCED_PLATE, SlimefunItems.GOLD_24K
        }).register(true, new ItemInteractionHandler() {
            @Override
            public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack) {
                if (SlimefunManager.isItemSimiliar(itemStack, STItems.ITEM_CREATER_CODE_SCANNER, false)) {
                    if (!player.isSneaking()) {
                        return false;
                    }
                    Block block = itemUseEvent.getClickedBlock();
                    //检查是否为物质制造机
                    if (BlockStorage.check(block, "ITEM_CREATOR")) {
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
                                    String fullCode = Integer.toBinaryString(Integer.valueOf(BlockStorage.getBlockInfo(block, "random-code")));
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
                return false;
            }
        });

        new SlimefunItem(Categories.TECH, STItems.SPACE_ORE_SCANNER, "SPACE_ORE_SCANNER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ELECTRO_MAGNET, STItems.HARD_AXLE, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.PLASTIC_SHEET, STItems.SUPER_CIRCUIT_BOARD, SlimefunItems.PLASTIC_SHEET,
                SlimefunItems.PLASTIC_SHEET, STItems.TITANIUM_PLATE, SlimefunItems.PLASTIC_SHEET
        }).register(true, new ItemInteractionHandler() {
            @Override
            public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack) {
                if (SlimefunManager.isItemSimiliar(itemStack, STItems.SPACE_ORE_SCANNER, false)) {
                    if (!STConfig.spaceWorldList.contains(player.getWorld().getName())) {
                        return false;
                    }
                    if (!player.isSneaking()) {
                        return false;
                    }
                    float charge = ItemEnergy.getStoredEnergy(itemStack);
                    float cost = 2F;
                    if (charge < cost) {
                        return false;
                    }
                    player.getInventory().setItemInMainHand((ItemEnergy.chargeItem(itemStack, -cost)));
                    ActionBarAPI.sendActionBar(player, "§7§l检测中...");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.8f);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Location loc = itemUseEvent.getClickedBlock().getLocation();
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
                                        String id = BlockStorage.checkID(block);
                                        if (id == null) {
                                            continue;
                                        }
                                        if (STItems.SPACE_ORE_LIST.contains(id)) {
                                            blocksMap.put(block.getLocation(), SlimefunItem.getByID(id).getItem().getItemMeta().getDisplayName());
                                        }
                                    }
                                }
                            }
                            if (!blocksMap.isEmpty()) {
                                ActionBarAPI.sendActionBar(player, "§a发现了 §e" + blocksMap.size() + " §a矿物");
                                for (Entry<Location, String> entry : blocksMap.entrySet()) {
                                    int distance = (int) ((Location) entry.getKey()).distance(loc);
                                    player.sendMessage(entry.getValue() + " §7距离: §e" + distance);
                                }
                            } else {
                                ActionBarAPI.sendActionBar(player, "§c很遗憾周围没有检测到矿物");
                            }
                        }
                    }.runTaskLaterAsynchronously(SpaceTech.getInstance(), 120);
                }
                return false;
            }
        });

        new SlimefunItem(Categories.TECH_MISC, STItems.EM_RESTRAINT_DEVICE, "EM_RESTRAINT_DEVICE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, STItems.NEODYMIUM, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.ELECTRO_MAGNET, STItems.EMPTY_UNIT, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.REINFORCED_PLATE, STItems.NEODYMIUM, SlimefunItems.REINFORCED_PLATE
        }).register(true);

        new SlimefunItem(Categories.TECH_MISC, STItems.EMPTY_EM_UNIT, "EMPTY_EM_UNIT", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.EMPTY_UNIT, null,
                STItems.EMPTY_UNIT, STItems.EM_RESTRAINT_DEVICE, STItems.EMPTY_UNIT,
                null, STItems.EMPTY_UNIT, null
        }).register(true);

        new SlimefunItem(Categories.TECH_MISC, STItems.ANTIMATTER_EM_UNIT, "ANTIMATTER_EM_UNIT", new RecipeType(STItems.ANTIMATTER_COLLECT_MACHINE), new ItemStack[]{STItems.EMPTY_EM_UNIT}).register(true);

        new SlimefunItem(Categories.TECH_MISC, STItems.BEDROCK_EM_UNIT, "BEDROCK_EM_UNIT", new RecipeType(STItems.BEDROCK_CRACK_MACHINE), new ItemStack[]{STItems.EMPTY_EM_UNIT}).register(true);

        new SlimefunItem(Categories.TECH_MISC, STItems.BEDROCK_EM_UNIT_3, "BEDROCK_EM_UNIT_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.BEDROCK_EM_UNIT, null,
                new ItemStack(Material.STRING), STItems.BEDROCK_EM_UNIT, new ItemStack(Material.STRING),
                null, STItems.BEDROCK_EM_UNIT, null
        }).register(true);

        new SlimefunItem(STCategories.ST_MACHINES, STItems.PARTICLE_GENERATOR_1, "PARTICLE_GENERATOR_1", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.LEAD_INGOT, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.LEAD_INGOT,
                SlimefunItems.REINFORCED_PLATE, STItems.EM_RESTRAINT_DEVICE, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.POWER_CRYSTAL
        }).register(true, new EnergyTicker() {
            @Override
            public double generateEnergy(Location location, SlimefunItem slimefunItem, Config config) {
                if (STConfig.spaceWorldList.contains(location.getWorld().getName())) {
                    return 32;
                }
                return 0;
            }

            @Override
            public boolean explode(Location location) {
                return false;
            }
        });

        new SlimefunItem(STCategories.ST_MACHINES, STItems.PARTICLE_GENERATOR_2, "PARTICLE_GENERATOR_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM, SlimefunItems.ELECTRO_MAGNET, STItems.TITANIUM,
                STItems.PARTICLE_GENERATOR_1, STItems.EM_RESTRAINT_DEVICE, STItems.PARTICLE_GENERATOR_1,
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.POWER_CRYSTAL
        }).register(true, new EnergyTicker() {
            @Override
            public double generateEnergy(Location location, SlimefunItem slimefunItem, Config config) {
                if (STConfig.spaceWorldList.contains(location.getWorld().getName())) {
                    return 64;
                }
                return 0;
            }

            @Override
            public boolean explode(Location location) {
                return false;
            }
        });

        new SlimefunItem(STCategories.ST_MACHINES, STItems.PARTICLE_GENERATOR_3, "PARTICLE_GENERATOR_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM_PLATE, SlimefunItems.ELECTRO_MAGNET, STItems.TITANIUM_PLATE,
                STItems.PARTICLE_GENERATOR_2, STItems.EM_RESTRAINT_DEVICE, STItems.PARTICLE_GENERATOR_2,
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.POWER_CRYSTAL
        }).register(true, new EnergyTicker() {
            @Override
            public double generateEnergy(Location location, SlimefunItem slimefunItem, Config config) {
                if (STConfig.spaceWorldList.contains(location.getWorld().getName())) {
                    return 128;
                }
                return 0;
            }

            @Override
            public boolean explode(Location location) {
                return false;
            }
        });

        new AntiMatterCollectMachine(STCategories.ST_MACHINES, STItems.ANTIMATTER_COLLECT_MACHINE, "ANTIMATTER_COLLECT_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                STItems.TITANIUM_PLATE, SlimefunItems.WITHER_PROOF_GLASS, STItems.TITANIUM_PLATE,
                STItems.EM_RESTRAINT_DEVICE, STItems.EMPTY_EM_UNIT, STItems.EM_RESTRAINT_DEVICE,
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE
        }, new ItemStack(Material.PURPLE_STAINED_GLASS_PANE, 1), 65536, "§5反物质:§e ") {
            @Override
            public int getEnergyConsumption() {
                return 128;
            }
        }.registerChargeableBlock(true, 1024);

        new SlimefunItem(Categories.TECH_MISC, STItems.ANTIMATTER_BEDROCK_DRILL, "ANTIMATTER_BEDROCK_DRILL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HARDENED_METAL_INGOT, STItems.ANTIMATTER_EM_UNIT, SlimefunItems.HARDENED_METAL_INGOT,
                null, STItems.BEDROCK_DRILL, null,
                null, null, null
        }).register(true);

        new BedrockCrackMachine(STCategories.ST_MACHINES, STItems.BEDROCK_CRACK_MACHINE, "BEDROCK_CRACK_MACHINE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE,
                STItems.TITANIUM_PLATE, STItems.BEDROCK_BREAK_MACHINE, STItems.TITANIUM_PLATE,
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE
        }, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1), 10000, "§a爆碎度: §e") {
            @Override
            public int getEnergyConsumption() {
                return 256;
            }
        }.registerChargeableBlock(true, 2048);

        new UnitItemMachine(STCategories.ST_MACHINES, STItems.UNIT_ITEM_CRAFTER, "UNIT_ITEM_CRAFTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.STEEL_THRUSTER, SlimefunItems.REINFORCED_PLATE,
                STItems.TITANIUM_PLATE, SlimefunItems.HEATED_PRESSURE_CHAMBER_2, STItems.TITANIUM_PLATE,
                SlimefunItems.REINFORCED_PLATE, STItems.TITANIUM_PLATE, SlimefunItems.REINFORCED_PLATE
        }) {
            @Override
            public int getEnergyConsumption() {
                return 128;
            }
        }.registerChargeableBlock(true, 1024);

        new SlimefunItem(Categories.TECH_MISC, STItems.RIG, "RIG", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.HARD_AXLE, null,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
        }).register(true);

        new SlimefunItem(Categories.TECH, STItems.ORE_RIG_REINFORCED, "ORE_RIG_REINFORCED", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
                SlimefunItems.REINFORCED_ALLOY_INGOT, STItems.TITANIUM, SlimefunItems.REINFORCED_ALLOY_INGOT,
                null, STItems.RIG, null,
        }).register(true);

        new SlimefunItem(Categories.TECH, STItems.ORE_RIG_CARBONADO, "ORE_RIG_CARBONADO", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, SlimefunItems.CARBONADO, null,
                SlimefunItems.CARBONADO, STItems.TITANIUM, SlimefunItems.CARBONADO,
                null, STItems.ORE_RIG_REINFORCED, null,
        }).register(true);

        new SlimefunItem(Categories.TECH, STItems.ORE_RIG_TITIANIUM, "ORE_RIG_TITIANIUM", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, STItems.TITANIUM, null,
                STItems.TITANIUM, STItems.TITANIUM, STItems.TITANIUM,
                null, STItems.ORE_RIG_CARBONADO, null,
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.BEDROCK_DIAMOND_AXE, "BEDROCK_DIAMOND_AXE", RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 3), new ItemStack(Material.DIAMOND_AXE), null,
                null, null, null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.BEDROCK_DIAMOND_PICKAXE, "BEDROCK_DIAMOND_PICKAXE", RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 3), new ItemStack(Material.DIAMOND_PICKAXE), null,
                null, null, null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.BEDROCK_DIAMOND_SWORD, "BEDROCK_DIAMOND_SWORD", RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 2), new ItemStack(Material.DIAMOND_SWORD), null,
                null, null, null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.BEDROCK_DIAMOND_HOE, "BEDROCK_DIAMOND_HOE", RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 2), new ItemStack(Material.DIAMOND_HOE), null,
                null, null, null
        }).register(true);

        new SlimefunItem(Categories.TOOLS, STItems.BEDROCK_DIAMOND_SPADE, "BEDROCK_DIAMOND_SPADE", RECIPE_TYPE_UNIT_ITEM_CRAFTER, new ItemStack[]{
                null, null, null,
                new ItemStack(Material.BEDROCK, 1), new ItemStack(Material.DIAMOND_SHOVEL), null,
                null, null, null
        }).register(true);
    }
}
