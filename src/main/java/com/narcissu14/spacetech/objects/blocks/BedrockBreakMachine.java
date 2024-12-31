package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.container.MachineDirection;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.utils.SkullUtil;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Narcissu14
 */
public abstract class BedrockBreakMachine extends AbstractPointsMachine {
    private final static ItemStack DIG_BUTTON = new CustomItemStack(new ItemStack(Material.HOPPER), "§c§l点击挖掘基岩",
            "", "§7当§e挖掘度§7到达最大后可以破穿指定方向的基岩", "§7你可以点击下方的按钮修改破穿的方位", "§7挖掘需要消耗基岩钻头", "", "§c§l注意: §7调整破穿方位会重置挖掘度");
    private final static String DIRECTION_KEY = "break-direction";
    private static String ID;
    private static ItemStack POINTS_ITEM;
    private static int POINTS_MAX;
    private static String POINTS_NAME;
    private static ItemStack DIRECTION_BUTTON;

    static {
        try {
            DIRECTION_BUTTON = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZkMWZhYmRmM2UzNDI2NzFiZDlmOTVmNjg3ZmUyNjNmNDM5ZGRjMmYxYzllYThmZjE1YjEzZjFlN2U0OGI5In19fQ=="),
                    "§7当前破穿方向: §e下", "", "§a点击修改§e破穿方向", "§7调整挖掘方位会§c重置§7挖掘度");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BedrockBreakMachine(ItemGroup itemGroup, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, ItemStack pointsItem, int pointsMax, String pointName) {
        super(itemGroup, item, name, recipeType, recipe);
        ID = name;
        POINTS_MAX = pointsMax;
        POINTS_ITEM = pointsItem;
        POINTS_NAME = pointName;
    }

    @Override
    public @NotNull String getInventoryTitle() {
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
    public @NotNull String getMachineIdentifier() {
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
    public void addExtraMenuHandler(@NotNull BlockMenu menu, @NotNull Block b) {
        menu.replaceExistingItem(4, DIG_BUTTON);
        menu.addMenuClickHandler(4, (player, i, itemStack, clickAction) -> {
            //判断能挖掘后进行挖掘
            if (Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), POINTS_KEY)) < POINTS_MAX) {
                player.sendMessage("§c挖掘度不足，现在还无法破穿基岩");
                return false;
            }
            Block facingBlock = b.getRelative(BlockFace.valueOf(StorageCacheUtils.getData(b.getLocation(), DIRECTION_KEY)));
            if (!facingBlock.getType().equals(Material.BEDROCK)) {
                player.sendMessage("§c破穿方向上的方块不是基岩");
                return false;
            }
            if (Slimefun.getProtectionManager().hasPermission(player, facingBlock, Interaction.BREAK_BLOCK)) {
                //挖基岩
                facingBlock.setType(Material.AIR);
                player.playSound(b.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 2f, 0.7f);
                player.spawnParticle(Particle.EXPLOSION_NORMAL, b.getLocation(), 10);
                StorageCacheUtils.setData(b.getLocation(), POINTS_KEY, "0");
            } else {
                player.sendMessage("§c你没有权限破坏那个方向上的基岩！");
            }
            return false;
        });
        menu.replaceExistingItem(22, DIRECTION_BUTTON);
        menu.addMenuClickHandler(22, (player, i, itemStack, clickAction) -> {
            //点击调整方位
            ItemStack newButton = DIRECTION_BUTTON.clone();
            ItemMeta meta = newButton.getItemMeta();
            switch (MachineDirection.getByName(StorageCacheUtils.getData(b.getLocation(), DIRECTION_KEY))) {
                case DOWN:
                    StorageCacheUtils.setData(b.getLocation(), DIRECTION_KEY, MachineDirection.UP.toString());
                    meta.setDisplayName("§7当前破穿方向: §e上");
                    newButton.setItemMeta(meta);
                    break;
                case UP:
                    StorageCacheUtils.setData(b.getLocation(), DIRECTION_KEY, MachineDirection.NORTH.toString());
                    meta.setDisplayName("§7当前破穿方向: §e北");
                    newButton.setItemMeta(meta);
                    break;
                case NORTH:
                    StorageCacheUtils.setData(b.getLocation(), DIRECTION_KEY, MachineDirection.EAST.toString());
                    meta.setDisplayName("§7当前破穿方向: §e东");
                    newButton.setItemMeta(meta);
                    break;
                case EAST:
                    StorageCacheUtils.setData(b.getLocation(), DIRECTION_KEY, MachineDirection.SOUTH.toString());
                    meta.setDisplayName("§7当前破穿方向: §e南");
                    newButton.setItemMeta(meta);
                    break;
                case SOUTH:
                    StorageCacheUtils.setData(b.getLocation(), DIRECTION_KEY, MachineDirection.WEST.toString());
                    meta.setDisplayName("§7当前破穿方向: §e西");
                    newButton.setItemMeta(meta);
                    break;
                case WEST:
                default:
                    StorageCacheUtils.setData(b.getLocation(), DIRECTION_KEY, MachineDirection.DOWN.toString());
                    //因为是默认下方，所以此处无需再修改名称
                    break;
            }
            StorageCacheUtils.setData(b.getLocation(), POINTS_KEY, "0");
            menu.replaceExistingItem(22, newButton);
            return false;
        });
    }

    @Override
    public void runOnBlockPlace(Player p, @NotNull Block b) {
        if (StorageCacheUtils.getData(b.getLocation(), DIRECTION_KEY) == null) {
            StorageCacheUtils.setData(b.getLocation(), DIRECTION_KEY, MachineDirection.DOWN.toString());
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
    public @Nullable ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd) {
        return null;
    }
}
