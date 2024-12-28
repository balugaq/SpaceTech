package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.utils.ActionBarAPI;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

    private static ItemStack PW_BUTTON = null;
    private static String id;

    static {
        try {
            PW_BUTTON = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY4ODZkOWM0MGVmN2Y1MGMyMzg4MjQ3OTJjNDFmYmZiNTRmNjY1ZjE1OWJmMWJjYjBiMjdiM2VhZDM3M2IifX19"),
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
     * */
    public RedstonePWMachine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, name, recipeType, recipe);

        id = name;

        new BlockMenuPreset(name, getInventoryTitle())
        {
            @Override
            public void init()
            {
                RedstonePWMachine.this.constructMenu(this);
            }

            @Override
            public void newInstance(BlockMenu menu, Block block) {
                menu.addMenuOpeningHandler(new MenuOpeningHandler() {
                    @Override
                    public void onOpen(Player player) {
                        //重置输入
                        BlockStorage.addBlockInfo(block, INPUT_KEY, "00000");

                    }
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

        registerBlockHandler(name, new SlimefunBlockHandler()
        {

            @Override
            public void onPlace(Player player, Block block, SlimefunItem slimefunItem) {
                BlockStorage.addBlockInfo(block, OWNER_KEY, player.getName());
                BlockStorage.addBlockInfo(block, PLACE_TIME_KEY, String.valueOf(System.currentTimeMillis()));
                BlockStorage.addBlockInfo(block, INPUT_KEY, "00000");
                BlockStorage.addBlockInfo(block, PW_KEY, "00000");
            }

            @Override
            public boolean onBreak(Player player, Block block, SlimefunItem slimefunItem, UnregisterReason unregisterReason) {
                if (player.isOp()) {
                    return true;
                }
                if (player.getName().equals(BlockStorage.getLocationInfo(block.getLocation(), OWNER_KEY))) {
                    return true;
                }
                ActionBarAPI.sendActionBar(player, "§c§l只有密码机的放置者能够拆除！");
                return false;
            }
        });
    }

    private void constructMenu(BlockMenuPreset preset){
        for(int i : PW_BORDER){
            preset.addItem(i, new CustomItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " "),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        for(int i : PW_ENTER_BORDER){
            preset.addItem(i, new CustomItem(new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1), " "),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        for(int i : PW_SETTING_BORDER){
            preset.addItem(i, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " "),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        for(int i : PW_ZERO_BORDER){
            preset.addItem(i, new CustomItem(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1), " "),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        for(int i : PW_BUTTON_SLOT){
            preset.addItem(i, PW_BUTTON,
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack item, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
    }

    public String getMachineIdentifier() {
        //RS_PASSWORD_MACHINE
        return id;
    }

    public String getInventoryTitle() {
        return "§l红石密码机";
    }

//    private void refreshButton(BlockMenu menu, Block block, int slot) {
//
//    }
}
