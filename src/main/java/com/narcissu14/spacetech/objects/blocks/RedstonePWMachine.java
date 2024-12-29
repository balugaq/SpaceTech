package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.utils.ActionBarAPI;
import com.narcissu14.spacetech.utils.SkullUtil;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Narcissu14
 */
public class RedstonePWMachine extends SlimefunItem {
    private static final int[] PW_BORDER = {0, 1, 2, 3, 4, 5, 9, 18, 19, 20, 21, 22, 23};
    private static final int[] PW_ENTER_BORDER = {6, 7, 8, 15, 17, 24, 25, 26};
    private static final int[] PW_SETTING_BORDER = {27, 28, 29, 30, 31, 32, 36, 38, 39, 41, 45, 46, 47, 48, 49, 50};
    private static final int[] PW_ZERO_BORDER = {33, 34, 35, 42, 44, 51, 52, 53};

    private static final int[] PW_BUTTON_SLOT = {10, 11, 12, 13, 14};
    private static final String PW_BUTTON_NAME = "§e点击修改数字";
    private static final String[] PW_BUTTON_LORES = new String[]{"", "§e左键 §a+1", "§eShift + 左键 §a+3", "§e右键 §a-1", "§eShift + 右键 §a-3"};

    private static final String OWNER_KEY = "owner";
    private static final String PLACE_TIME_KEY = "place-time";
    private static final String INPUT_KEY = "input";
    private static final String PW_KEY = "password";

    private static @Nullable ItemStack PW_BUTTON = null;
    private static String id;

    static {
        try {
            PW_BUTTON = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY4ODZkOWM0MGVmN2Y1MGMyMzg4MjQ3OTJjNDFmYmZiNTRmNjY1ZjE1OWJmMWJjYjBiMjdiM2VhZDM3M2IifX19"),
                    PW_BUTTON_NAME, PW_BUTTON_LORES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义的方块信息key:
     * owner 方块放置者
     * place-time 方块放置的时间，用于检查是否afk等等
     * input 当前输入，默认00000(每次确认输入、放置、归零、退出界面)
     * password 匹配密码，默认00000(放置时)
     */
    public RedstonePWMachine(@NotNull ItemGroup itemGroup, @NotNull ItemStack item, @NotNull String name, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(itemGroup, item, name, recipeType, recipe);

        id = name;

        new BlockMenuPreset(name, getInventoryTitle()) {
            @Override
            public void init() {
                RedstonePWMachine.this.constructMenu(this);
            }

            @Override
            public void newInstance(@NotNull BlockMenu menu, @NotNull Block block) {
                menu.addMenuOpeningHandler(player -> {
                    //重置输入
                    StorageCacheUtils.setData(block.getLocation(), INPUT_KEY, "00000");

                });
            }

            @Override
            public boolean canOpen(Block block, Player player) {
                return false;
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow itemTransportFlow) {
                return new int[0];
            }
        };

        addItemHandler(new BlockPlaceHandler(false) {

            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent blockPlaceEvent) {
                Block b = blockPlaceEvent.getBlockPlaced();
                Player player = blockPlaceEvent.getPlayer();
                StorageCacheUtils.setData(b.getLocation(), OWNER_KEY, player.getName());
                StorageCacheUtils.setData(b.getLocation(), PLACE_TIME_KEY, String.valueOf(System.currentTimeMillis()));
                StorageCacheUtils.setData(b.getLocation(), INPUT_KEY, "00000");
                StorageCacheUtils.setData(b.getLocation(), PW_KEY, "00000");
            }
        }, new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Player player = blockBreakEvent.getPlayer();
                Block block = blockBreakEvent.getBlock();
                if (player.isOp()) {
                    return;
                }
                if (player.getName().equals(StorageCacheUtils.getData(block.getLocation(), OWNER_KEY))) {
                    return;
                }
                ActionBarAPI.sendActionBar(player, "§c§l只有密码机的放置者能够拆除！");
                blockBreakEvent.setCancelled(true);
            }
        });
    }

    private void constructMenu(@NotNull BlockMenuPreset preset) {
        for (int i : PW_BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " "),
                    (player, i1, itemStack, clickAction) -> false);
        }
        for (int i : PW_ENTER_BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1), " "),
                    (player, i12, itemStack, clickAction) -> false);
        }
        for (int i : PW_SETTING_BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " "),
                    (player, i13, itemStack, clickAction) -> false);
        }
        for (int i : PW_ZERO_BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1), " "),
                    (player, i14, itemStack, clickAction) -> false);
        }
        for (int i : PW_BUTTON_SLOT) {
            preset.addItem(i, PW_BUTTON,
                    (player, i15, item, clickAction) -> false);
        }
    }

    public String getMachineIdentifier() {
        //RS_PASSWORD_MACHINE
        return id;
    }

    public @NotNull String getInventoryTitle() {
        return "§l红石密码机";
    }

//    private void refreshButton(BlockMenu menu, Block block, int slot) {
//
//    }
}
