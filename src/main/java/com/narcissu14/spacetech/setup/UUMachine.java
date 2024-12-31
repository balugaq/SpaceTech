package com.narcissu14.spacetech.setup;

import com.narcissu14.spacetech.utils.MachineHelper;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UUMachine extends AContainer {
    public static final @NotNull Map<Block, UURecipe> processing = new HashMap<>();
    public static final @NotNull Map<Block, Integer> progress = new HashMap<>();
    private static final int[] uuBorder = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static final int[] uuInfo = new int[]{10, 11, 12, 13, 14, 15, 16};
    private static final int[] border = new int[]{27, 30, 31, 32, 35, 36, 39, 41, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
    private static final int[] inputSign = new int[]{28, 29};
    private static final int[] outputSign = new int[]{33, 34};
    private static final ItemStack uuItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 10);
    protected final @NotNull List<UURecipe> recipes = new ArrayList<>();

    public UUMachine(@NotNull ItemGroup category, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(category, item, recipeType, recipe);
        setProcessingSpeed(1);
        new BlockMenuPreset(getId(), getInventoryTitle()) {
            public void init() {
                UUMachine.this.constructMenu(this);
            }

            public void newInstance(@NotNull BlockMenu menu, @NotNull Block b) {
            }

            public boolean canOpen(@NotNull Block b, @NotNull Player p) {
                boolean perm = (p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b, Interaction.INTERACT_BLOCK));
                return (perm && UUMachine.this.canUse(p, true));
            }

            public int[] getSlotsAccessedByItemTransport(@NotNull ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.INSERT)) {
                    return UUMachine.this.getInputSlots();
                }
                return UUMachine.this.getOutputSlots();
            }
        };
        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent blockBreakEvent, @NotNull ItemStack itemStack, @NotNull List<ItemStack> list) {
                Block b = blockBreakEvent.getBlock();
                BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
                if (inv != null) {
                    for (int slot : UUMachine.this.getInputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        }
                    }
                    for (int slot : UUMachine.this.getOutputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        }
                    }
                }
                UUMachine.progress.remove(b);
                UUMachine.processing.remove(b);
            }
        });
        registerDefaultRecipes();
    }

    public void constructMenu(@NotNull BlockMenuPreset preset) {
        for (int i : uuBorder) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 15), " "), (player, i14, itemStack, clickAction) -> false);
        }
        for (int i : border) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7), " "), (player, i13, itemStack, clickAction) -> false);
        }
        for (int i : outputSign) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 11), "§b输出槽"), (player, i12, itemStack, clickAction) -> false);
        }
        for (int i : inputSign) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 4), "§e输入槽"), (player, i1, itemStack, clickAction) -> false);
        }
        preset.addItem(40, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 15), " "), (player, i, itemStack, clickAction) -> false);
        for (int i : getOutputSlots()) {
            preset.addItem(i, null, new ChestMenu.AdvancedMenuClickHandler() {
                public boolean onClick(Player player, int i, ItemStack item, ClickAction action) {
                    return false;
                }

                public boolean onClick(InventoryClickEvent event, Player player, int slot, @Nullable ItemStack item, ClickAction action) {
                    if (item == null) return true;
                    item.getType();
                    return item.getType() == Material.AIR;
                }
            });
        }
        for (int i : uuInfo) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7), " "), new ChestMenu.AdvancedMenuClickHandler() {
                public boolean onClick(InventoryClickEvent inventoryClickEvent, Player player, int i, ItemStack itemStack, ClickAction clickAction) {
                    return false;
                }

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

    public UURecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return (getProcessing(b) != null);
    }

    public void registerRecipe(@NotNull UURecipe recipe) {
        recipe.setTicks(recipe.getTicks());
        this.recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, int uuAmount) {
        registerRecipe(new UURecipe(seconds, input, uuAmount));
    }

    public @NotNull List<UURecipe> getUURecipes() {
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

    protected void pushMainItems(@NotNull Block b, ItemStack[] items, int uu) {
        if (StorageCacheUtils.getData(b.getLocation(), "uuAmount") == null) {
            StorageCacheUtils.setData(b.getLocation(), "uuAmount", "0");
        } else {
            StorageCacheUtils.setData(b.getLocation(), "uuAmount", String.valueOf(uu + Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "uuAmount"))));
        }
        if (Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "uuAmount")) >= getUUFull()) {
            StorageCacheUtils.setData(b.getLocation(), "uuAmount", "0");
            Inventory inv = inject(b);
            inv.addItem(items);
            for (int slot : getOutputSlots()) {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, inv.getItem(slot));
            }
        }
        int amount = Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "uuAmount"));
        for (int i : uuInfo) {
            if ((i - uuInfo[0]) * getUUFull() / 7 <= amount) {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(i, new CustomItemStack(uuItem, "§7元物质量: §d" + amount + "§7/§c100000"));
            } else {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7), " "));
            }
        }
    }

    public void register(@NotNull SlimefunAddon addon) {
        addItemHandler(new BlockTicker() {
            public void tick(@NotNull Block b, SlimefunItem sf, Config data) {
                UUMachine.this.tick(b);
            }

            public void uniqueTick() {
            }

            public boolean isSynchronized() {
                return false;
            }
        });
        super.register(addon);
    }

    protected void tick(@NotNull Block b) {
        if (isProcessing(b)) {
            int timeleft = progress.get(b);
            if (timeleft > 0) {
                ItemStack item = getProgressBar().clone();
                item.setDurability(MachineHelper.getDurability(item, timeleft, processing.get(b).getTicks()));
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(" ");
                List<String> lore = new ArrayList<>();
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
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(40, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 15), " "));
                pushMainItems(b, getOutput(), processing.get(b).getUUAmount());
                progress.remove(b);
                processing.remove(b);
            }
        } else {
            UURecipe r = null;
            Map<Integer, Integer> found = new HashMap<>();
            for (UURecipe recipe : this.recipes) {
                for (ItemStack input : recipe.getInput()) {
                    for (int slot : getInputSlots()) {
                        if (SlimefunUtils.isItemSimilar(StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot), input, true)) {
                            if (input != null) {
                                found.put(slot, input.getAmount());
                            }
                        }
                    }
                }
                if (found.size() == (recipe.getInput()).length) {
                    r = recipe;
                    break;
                }
                found.clear();
            }
            if (r != null) {
                if (!fits(b, getOutput())) {
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

    public abstract int getUUFull();

    public abstract ItemStack[] getOutput();

    public abstract @NotNull String getInventoryTitle();

    public abstract ItemStack getProgressBar();

    public abstract void registerDefaultRecipes();

    public abstract int getEnergyConsumption();

    public abstract int getLevel();

    public abstract @NotNull String getMachineIdentifier();
}