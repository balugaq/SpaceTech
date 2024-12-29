package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.container.PointMachineRecipe;
import com.narcissu14.spacetech.setup.config.STConfig;
import com.narcissu14.spacetech.utils.ChargeableBlock;
import com.narcissu14.spacetech.utils.MachineHelper;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Narcissu14
 * 抽象的点数累积型机器(例如UU机)
 * 输入的原材料可以变为点数，也能直接产出产品或是消耗点数后产出产品
 */
public abstract class AbstractPointsMachine extends AContainer {
    public static final @NotNull Map<Block, PointMachineRecipe> processing = new HashMap<Block, PointMachineRecipe>();
    public static final @NotNull Map<Block, Integer> progress = new HashMap<Block, Integer>();
    protected static final @NotNull Map<Block, ItemStack> charginItems = new HashMap<Block, ItemStack>();
    static final String POINTS_KEY = "machine-points";
    private static final int[] POINTS_BORDER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static final int[] POINTS_INFO = {10, 11, 12, 13, 14, 15, 16};
    private static final int[] BORDER = {27, 30, 31, 32, 35, 36, 39, 41, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
    private static final int[] INPUT_SIGN = {28, 29};
    private static final int[] OUTPUT_SIGN = {33, 34};
    private static final @NotNull ItemStack NO_POINTS_ITEM = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
    protected final @NotNull List<PointMachineRecipe> recipes = new ArrayList<PointMachineRecipe>();

    public AbstractPointsMachine(@NotNull ItemGroup itemGroup, @NotNull ItemStack itemStack, @NotNull String id, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        this(itemGroup, new SlimefunItemStack(id, itemStack), recipeType, recipe);
    }

    public AbstractPointsMachine(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
        String name = this.getItemName();

        new BlockMenuPreset(name, getInventoryTitle()) {
            @Override
            public void init() {
                AbstractPointsMachine.this.constructMenu(this);
            }

            @Override
            public void newInstance(@NotNull BlockMenu menu, @NotNull Block b) {
                addExtraMenuHandler(menu, b);
            }

            @Override
            public boolean canOpen(@NotNull Block b, @NotNull Player p) {
                boolean perm = p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b, Interaction.INTERACT_BLOCK);
                return perm && (STConfig.originalSlimefun || AbstractPointsMachine.this.canUse(p, true));
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(@NotNull ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.INSERT)) {
                    return AbstractPointsMachine.this.getInputSlots();
                }
                return AbstractPointsMachine.this.getOutputSlots();
            }
        };
        addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                Player p = e.getPlayer();
                Block b = e.getBlockPlaced();
                runOnBlockPlace(p, b);
                if (StorageCacheUtils.getData(b.getLocation(), POINTS_KEY) == null) {
                    StorageCacheUtils.setData(b.getLocation(), POINTS_KEY, "0");
                }
            }
        }, new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent e, @NotNull ItemStack tool, @NotNull List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
                if (inv != null) {
                    for (int slot : AbstractPointsMachine.this.getInputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        }
                    }
                    for (int slot : AbstractPointsMachine.this.getOutputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        }
                    }
                }
                AbstractPointsMachine.progress.remove(b);
                AbstractPointsMachine.processing.remove(b);
                ItemStack dropItem = AbstractPointsMachine.charginItems.get(b);
                if (dropItem != null) {
                    b.getWorld().dropItemNaturally(b.getLocation(), dropItem);
                }
                AbstractPointsMachine.charginItems.remove(b);
            }
        });
        registerDefaultRecipes();
    }

    public void constructMenu(@NotNull BlockMenuPreset preset) {
        for (int i : POINTS_BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " "),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        for (int i : BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE, 1), " "),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        for (int i : OUTPUT_SIGN) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1), "§b输出槽"),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        for (int i : INPUT_SIGN) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1), "§e输入槽"),
                    new ChestMenu.MenuClickHandler() {
                        @Override
                        public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                            return false;
                        }
                    });
        }
        //进度显示
        preset.addItem(40, new CustomItemStack(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " "),
                new ChestMenu.MenuClickHandler() {
                    @Override
                    public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                        return false;
                    }
                });
        //禁止随意将物品放入输出槽
        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new ChestMenu.AdvancedMenuClickHandler() {
                @Override
                public boolean onClick(Player player, int i, ItemStack item, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent event, Player player, int slot, @Nullable ItemStack item, ClickAction action) {
                    if ((item == null)) return true;
                    item.getType();
                    return item.getType() == Material.AIR;
                }
            });
        }
        //POINT量信息
        for (int i : POINTS_INFO) {
            preset.addItem(i, new CustomItemStack(NO_POINTS_ITEM, " "), new ChestMenu.MenuClickHandler() {
                @Override
                public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                    return false;
                }
            });
        }
    }

    public int[] getInputSlots() {
        return new int[]{37, 38};
    }

    public int[] getOutputSlots() {
        return new int[]{42, 43};
    }

    public abstract @NotNull String getInventoryTitle();

    public abstract void registerDefaultRecipes();

    public abstract int getEnergyConsumption();

    public abstract int getLevel();

    public abstract @NotNull String getMachineIdentifier();

    public abstract ItemStack getPointsItem();

    public abstract int getPointsMax();

    public abstract String getPointsName();

    /**
     * onPlace中额外的触发内容
     */
    public void runOnBlockPlace(Player p, Block b) {

    }

    /**
     * 生产流程开始前，最后的额外条件检测内容
     * 即，执行此方法时，若返回结果为true，当前配方一定会开始生产
     * 默认true
     */
    boolean startTickCheck(Block b, PointMachineRecipe recipe) {
        return true;
    }

    /**
     * 新增额外的GUI按钮、界面设定
     */
    public abstract void addExtraMenuHandler(BlockMenu menu, Block b);

    /**
     * 检测比对物品是否为可消耗点数充点的物品
     * 如果物品不是可充物品或点数已满，则返回false
     */
    public abstract boolean isChargeableItem(ItemStack input);

    /**
     * 获取指定物品的点数
     * WARNING: 为了效率此处没有检测是否为可充点物品
     * 请在每次使用前确保物品为可充点物品
     */
    public abstract int getItemPoints(ItemStack input);

    /**
     * 修改指定物品的点数
     * WARNING: 为了效率此处没有检测是否为可充点物品
     * 请在每次使用前确保物品为可充点物品
     */
    public abstract @Nullable ItemStack modifyItemPoints(ItemStack input, int points, boolean isAdd);

    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    public PointMachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return getProcessing(b) != null;
    }

    public void registerRecipe(@NotNull PointMachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks());
        this.recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, int points, ItemStack[] output) {
        registerRecipe(new PointMachineRecipe(seconds, input, points, output));
    }

    public void registerRecipe(int seconds, ItemStack[] input, int points, int valueMax) {
        registerRecipe(new PointMachineRecipe(seconds, input, points, valueMax));
    }

    public void registerRecipe(int seconds, ItemStack[] input, int points, ItemStack[] output, PointMachineRecipe.PointCheckType check, int condition) {
        registerRecipe(new PointMachineRecipe(seconds, input, points, output, check, condition));
    }

    public void registerRecipe(int seconds, ItemStack[] input, int points, int valueMax, PointMachineRecipe.PointCheckType check, int condition) {
        registerRecipe(new PointMachineRecipe(seconds, input, points, valueMax, check, condition));
    }

    public List<PointMachineRecipe> getPointMachineRecipes() {
        return this.recipes;
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

    protected void pushMainItems(@NotNull Block b, @NotNull PointMachineRecipe recipe) {
        //旧point值
        int nowValue = Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), POINTS_KEY));
        //新point值
        int newValue = recipe.getPoints() + nowValue;
        if (newValue > getPointsMax()) {
            newValue = getPointsMax();
        } else if (newValue < 0) {
            newValue = 0;
        }
        //处理结果输出，如果是无结果输出的配方，则不进行处理
        if (recipe.getType().equals(PointMachineRecipe.RecipeResultType.ITEM)) {
            if (recipe.getOutput() != null && recipe.getOutput().length > 0) {
                if (newValue >= 0) {
                    Inventory inv = inject(b);
                    inv.addItem(recipe.getOutput());
                    for (int slot : getOutputSlots()) {
                        StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, inv.getItem(slot));
                    }
                }
            }
        } else if (recipe.getType().equals(PointMachineRecipe.RecipeResultType.LORE)) {
            //输出为Lore修改的配方
            //已经在tick中判断为可充点
            //这里先假设配方仅有一个输入物
            ItemStack output = charginItems.get(b);
            int points = recipe.getValueMax() - getItemPoints(output);
            if (nowValue - points < 0) {
                points = nowValue;
                newValue = 0;
            } else {
                newValue = nowValue - points;
            }
            ItemStack outItem = modifyItemPoints(output, points, true);

            boolean canOutput = false;
            for (int slot : getOutputSlots()) {
                if (StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot) == null) {
                    Inventory inv = inject(b);
                    inv.addItem(outItem);
                    StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, outItem);
                    canOutput = true;
                    charginItems.remove(b);
                    break;
                }
            }
            if (!canOutput) {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(getOutputSlots()[getOutputSlots().length - 1], outItem);
                charginItems.remove(b);
            }

        }
        //增减point值
        StorageCacheUtils.setData(b.getLocation(), POINTS_KEY, String.valueOf(newValue));
        //处理GUI显示
        placePointsBar(nowValue, newValue, b);
    }

    private void placePointsBar(int nowValue, int newValue, @NotNull Block b) {
        for (int i : POINTS_INFO) {
            if (newValue != 0 && (i - POINTS_INFO[0]) * getPointsMax() / 7 <= nowValue) {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(i, new CustomItemStack(getPointsItem(), getPointsName() + newValue + "§7/§e" + getPointsMax()));
            } else {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(i, new CustomItemStack(NO_POINTS_ITEM, getPointsName() + newValue + "§7/§e" + getPointsMax()));
            }
        }
    }

    @Override
    public void register(@NotNull SlimefunAddon addon) {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(@NotNull Block b, SlimefunItem sf, Config data) {
                AbstractPointsMachine.this.tick(b);
            }

            @Override
            public void uniqueTick() {
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
        super.register(addon);
    }

    protected void tick(@NotNull Block b) {
        ItemMeta im;
        if (isProcessing(b)) {
            int timeleft = progress.get(b);
            if (timeleft > 0) {
                ItemStack item = getProgressBar().clone();
                im = item.getItemMeta();
                if (im instanceof Damageable) {
                    ((Damageable) im).setDamage(MachineHelper.getDurability(item, timeleft, processing.get(b).getTicks()));
                }
                im.setDisplayName(" ");
                List<String> lore = new ArrayList<String>();
                lore.add(MachineHelper.getProgress(timeleft, processing.get(b).getTicks()));
                lore.add("");
                lore.add(MachineHelper.getTimeLeft(timeleft / 2));
                im.setLore(lore);
                item.setItemMeta(im);

                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(40, item);
                if (ChargeableBlock.isChargeable(b)) {
                    if (ChargeableBlock.getCharge(b) < getEnergyConsumption()) {
                        return;
                    }
                    ChargeableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else {
                    progress.put(b, timeleft - 1);
                }
            } else {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(40, new CustomItemStack(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " "));
                if (!startTickCheck(b, processing.get(b))) {
                    return;
                }
                pushMainItems(b, processing.get(b));
                progress.remove(b);
                processing.remove(b);
                charginItems.remove(b);
            }
        } else {
            //如果没有在进行生产流程
            PointMachineRecipe r = null;
            Map<Integer, Integer> found = new HashMap<Integer, Integer>();

            recipeCheck:
            for (PointMachineRecipe recipe : this.recipes) {
                //判断是否有足够扣除的点数
                if (!recipe.getCheckType().equals(PointMachineRecipe.PointCheckType.NO_CHECK)) {
                    int value = Integer.valueOf(StorageCacheUtils.getData(b.getLocation(), POINTS_KEY));
                    switch (recipe.getCheckType()) {
                        case BIG_THAN:
                            if (!(value > recipe.getCondition())) {
                                continue;
                            }
                            break;
                        case SMALL_THAN:
                            if (!(value < recipe.getCondition())) {
                                continue;
                            }
                            break;
                        case BIG_THAN_OR_EQUAL:
                            if (!(value >= recipe.getCondition())) {
                                continue;
                            }
                            break;
                        case SMALL_THAN_OR_EQUAL:
                            if (!(value <= recipe.getCondition())) {
                                continue;
                            }
                            break;
                        case EQUAL:
                            if (value != recipe.getCondition()) {
                                continue;
                            }
                            break;
                        default:
                            break;
                    }
                }
                //判断为哪种配方形式后再行处理
                if (recipe.getType().equals(PointMachineRecipe.RecipeResultType.ITEM)) {
                    for (ItemStack input : recipe.getInput()) {
                        for (int slot : getInputSlots()) {
                            if (SlimefunUtils.isItemSimilar(StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot), input, true)) {
                                if (input != null) {
                                    found.put(slot, input.getAmount());
                                }
                                //break;
                            }
                        }
                    }
                    if (found.size() == recipe.getInput().length) {
                        r = recipe;
                        break;
                    }
                    found.clear();
                } else if (recipe.getType().equals(PointMachineRecipe.RecipeResultType.LORE)) {
                    //检查输出槽是否有空位
                    boolean hasEmptyOutSlot = false;
                    for (int outSlot : getOutputSlots()) {
                        if (StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(outSlot) == null) {
                            hasEmptyOutSlot = true;
                            break;
                        }
                    }
                    if (!hasEmptyOutSlot) {
                        return;
                    }
                    ItemStack realInput = null;
                    for (ItemStack input : recipe.getInput()) {
                        for (int slot : getInputSlots()) {
                            if (SlimefunUtils.isItemSimilar(StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot), input, false)) {
                                if (input != null) {
                                    found.put(slot, input.getAmount());
                                    realInput = input;
                                }
                            }
                        }
                    }
                    if (realInput != null) {
                        charginItems.put(b, realInput);
                    }
                    if (found.size() == recipe.getInput().length) {
                        r = recipe;
                        break;
                    }
                    found.clear();
                }
            }
            if (r != null) {
                //检测Points是否已满，且配方依然要对points进行增加，是则return
                if (StorageCacheUtils.getData(b.getLocation(), POINTS_KEY).equals(String.valueOf(getPointsMax())) && r.getPoints() > 0) {
                    return;
                }
                if (r.getOutput() != null && !fits(b, r.getOutput())) {
                    return;
                }
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    StorageCacheUtils.getMenu(b.getLocation()).consumeItem(entry.getKey(), entry.getValue());
                }
                processing.put(b, r);
                progress.put(b, r.getTicks());
            }
        }
    }

    public abstract int getCapacity();

    public abstract int getSpeed();
}
