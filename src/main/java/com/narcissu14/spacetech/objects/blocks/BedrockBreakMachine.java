package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.container.MachineDirection;
import com.narcissu14.spacetech.objects.STItems;
import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Narcissu14
 */
public abstract class BedrockBreakMachine extends AbstractPointsMachine {
    private static String ID;
    private static ItemStack POINTS_ITEM;
    private static int POINTS_MAX;
    private static String POINTS_NAME;

    private final static ItemStack DIG_BUTTON = new CustomItem(new ItemStack(Material.HOPPER), "§c§l点击挖掘基岩",
            "" , "§7当§e挖掘度§7到达最大后可以破穿指定方向的基岩", "§7你可以点击下方的按钮修改破穿的方位", "§7挖掘需要消耗基岩钻头", "", "§c§l注意: §7调整破穿方位会重置挖掘度");
    private final static String DIRECTION_KEY = "break-direction";

    private static ItemStack DIRECTION_BUTTON;

    static {
        try {
            DIRECTION_BUTTON = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZkMWZhYmRmM2UzNDI2NzFiZDlmOTVmNjg3ZmUyNjNmNDM5ZGRjMmYxYzllYThmZjE1YjEzZjFlN2U0OGI5In19fQ=="),
                    "§7当前破穿方向: §e下", "", "§a点击修改§e破穿方向", "§7调整挖掘方位会§c重置§7挖掘度");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BedrockBreakMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(category, item, name, recipeType, recipe);
        ID = name;
        POINTS_MAX = pointsMax;
        POINTS_ITEM = pointsItem;
        POINTS_NAME = pointName;
    }

    @Override
    public String getInventoryTitle() {
        return "&9基岩破穿器";
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(2, new ItemStack[]{STItems.BEDROCK_DRILL}, 1500, new ItemStack[]{});
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
        menu.replaceExistingItem(4, DIG_BUTTON);
        menu.addMenuClickHandler(4, new ChestMenu.MenuClickHandler() {
            @Override
            public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                //判断能挖掘后进行挖掘
                if (Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), POINTS_KEY)) < POINTS_MAX) {
                    player.sendMessage("§c挖掘度不足，现在还无法破穿基岩");
                    return false;
                }
                Block facingBlock = b.getRelative(BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), DIRECTION_KEY)));
                if (!facingBlock.getType().equals(Material.BEDROCK)) {
                    player.sendMessage("§c破穿方向上的方块不是基岩");
                    return false;
                }
                if (CSCoreLib.getLib().getProtectionManager().canBuild(player.getUniqueId(), facingBlock)) {
                    //挖基岩
                    facingBlock.setType(Material.AIR);
                    player.playSound(b.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 2f, 0.7f);
                    player.spawnParticle(Particle.EXPLOSION_NORMAL, b.getLocation(), 10);
                    BlockStorage.addBlockInfo(b, POINTS_KEY, "0", false);
                } else {
                    player.sendMessage("§c你没有权限破坏那个方向上的基岩！");
                }
                return false;
            }
        });
        menu.replaceExistingItem(22, DIRECTION_BUTTON);
        menu.addMenuClickHandler(22, new ChestMenu.MenuClickHandler() {
            @Override
            public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                //点击调整方位
                ItemStack newButton = DIRECTION_BUTTON.clone();
                ItemMeta meta = newButton.getItemMeta();
                switch (MachineDirection.getByName(BlockStorage.getLocationInfo(b.getLocation(), DIRECTION_KEY))) {
                    case DOWN:
                        BlockStorage.addBlockInfo(b, DIRECTION_KEY, MachineDirection.UP.toString(), false);
                        meta.setDisplayName("§7当前破穿方向: §e上");
                        newButton.setItemMeta(meta);
                        break;
                    case UP:
                        BlockStorage.addBlockInfo(b, DIRECTION_KEY, MachineDirection.NORTH.toString(), false);
                        meta.setDisplayName("§7当前破穿方向: §e北");
                        newButton.setItemMeta(meta);
                        break;
                    case NORTH:
                        BlockStorage.addBlockInfo(b, DIRECTION_KEY, MachineDirection.EAST.toString(), false);
                        meta.setDisplayName("§7当前破穿方向: §e东");
                        newButton.setItemMeta(meta);
                        break;
                    case EAST:
                        BlockStorage.addBlockInfo(b, DIRECTION_KEY, MachineDirection.SOUTH.toString(), false);
                        meta.setDisplayName("§7当前破穿方向: §e南");
                        newButton.setItemMeta(meta);
                        break;
                    case SOUTH:
                        BlockStorage.addBlockInfo(b, DIRECTION_KEY, MachineDirection.WEST.toString(), false);
                        meta.setDisplayName("§7当前破穿方向: §e西");
                        newButton.setItemMeta(meta);
                        break;
                    case WEST:
                    default:
                        BlockStorage.addBlockInfo(b, DIRECTION_KEY, MachineDirection.DOWN.toString(), false);
                        //因为是默认下方，所以此处无需再修改名称
                        break;
                }
                BlockStorage.addBlockInfo(b, POINTS_KEY, "0", false);
                menu.replaceExistingItem(22, newButton);
                return false;
            }
        });
    }

    @Override
    public void runOnBlockPlace(Player p, Block b, SlimefunItem item) {
        if (BlockStorage.getLocationInfo(b.getLocation(), DIRECTION_KEY) == null) {
            BlockStorage.addBlockInfo(b, DIRECTION_KEY, MachineDirection.DOWN.toString(), false);
        }
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
    public ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd) {
        return null;
    }
}
