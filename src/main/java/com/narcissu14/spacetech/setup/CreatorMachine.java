package com.narcissu14.spacetech.setup;

import com.narcissu14.spacetech.utils.MachineHelper;
import com.narcissu14.spacetech.utils.SkullUtil;
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
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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
import java.util.Random;

public abstract class CreatorMachine extends AContainer {
    private static final int[] codeBorder = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 23, 24, 25, 26};
    private static final int[] code = new int[]{10, 11, 12, 13, 14, 15, 16};
    private static final int[] border = new int[]{27, 30, 31, 32, 35, 36, 39, 41, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
    private static final int[] inputSign = new int[]{28, 29};
    private static final int[] outputSign = new int[]{33, 34};
    public static @NotNull Map<Block, MachineRecipe> processing = new HashMap<>();
    public static @NotNull Map<Block, Integer> progress = new HashMap<>();
    protected @NotNull List<MachineRecipe> recipes = new ArrayList<>();

    public CreatorMachine(@NotNull ItemGroup category, @NotNull ItemStack item, @NotNull String name, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(category, new SlimefunItemStack(name, item), recipeType, recipe);
        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent blockBreakEvent, @NotNull ItemStack itemStack, @NotNull List<ItemStack> list) {
                Block b = blockBreakEvent.getBlock();
                BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
                if (inv != null) {
                    for (int slot : CreatorMachine.this.getInputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        }
                    }
                    for (int slot : CreatorMachine.this.getOutputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        }
                    }
                }
                CreatorMachine.progress.remove(b);
                CreatorMachine.processing.remove(b);
            }
        });
        registerDefaultRecipes();
        new BlockMenuPreset(name, getItemName()) {
            public void init() {
                CreatorMachine.this.constructMenu(this);
            }

            public void newInstance(final @NotNull BlockMenu menu, final @NotNull Block b) {
                if (StorageCacheUtils.getData(b.getLocation(), "random-code") == null) {
                    Random random = new Random();
                    StorageCacheUtils.setData(b.getLocation(), "random-code", String.valueOf(random.nextInt(127)));
                }
                if (StorageCacheUtils.getData(b.getLocation(), "last-code") == null) {
                    StorageCacheUtils.setData(b.getLocation(), "last-code", "0000000");
                }
                if (StorageCacheUtils.getData(b.getLocation(), "set-code") == null) {
                    StorageCacheUtils.setData(b.getLocation(), "set-code", "0000000");
                }
                if (StorageCacheUtils.getData(b.getLocation(), "output-item") == null) {
                    StorageCacheUtils.setData(b.getLocation(), "output-item", "0");
                }
                try {
                    final char[] setCode = StorageCacheUtils.getData(b.getLocation(), "set-code").toCharArray();
                    for (int slot : CreatorMachine.code) {
                        final int loc = slot - CreatorMachine.code[0];
                        if (!StorageCacheUtils.hasBlock(b.getLocation()) || setCode[loc] == '0') {
                            menu.replaceExistingItem(slot, new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGViZTdlNTIxNTE2OWE2OTlhY2M2Y2VmYTdiNzNmZGIxMDhkYjg3YmI2ZGFlMjg0OWZiZTI0NzE0YjI3In19fQ=="), "&7编码: &a0", "", "&7正确的§e编码组合§7才能产出指定的物质", "§7每当你设置的编码较上一次§e更接近§7正确编码", "§7机器都会发出比较§e悦耳的声音", "&7另外有技巧地设置编码将有效地§e减少生产成本", "§7不过一旦被观测到正确的常数", "§7常数就会被§c重置", "§7这究竟是神的玩笑还是恶魔的诅咒呢", "", "§c> §7使用§d元物质§7进行制作", "§c> §7调整下方的物品修改你想制作的物品", "§6> §b点击此处将编码修改为§e1"));
                            menu.addMenuClickHandler(slot, (p, arg1, arg2, arg3) -> {
                                StorageCacheUtils.setData(b.getLocation(), "last-code", String.valueOf(setCode));
                                setCode[loc] = '1';
                                StorageCacheUtils.setData(b.getLocation(), "set-code", String.valueOf(setCode));
                                this.newInstance(menu, b);
                                return false;
                            });
                        } else {
                            menu.replaceExistingItem(slot, new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiYzJiY2ZiMmJkMzc1OWU2YjFlODZmYzdhNzk1ODVlMTEyN2RkMzU3ZmMyMDI4OTNmOWRlMjQxYmM5ZTUzMCJ9fX0="), "&7编码: &e1", "", "&7正确的§e编码组合§7才能产出指定的物质", "§7每当你设置的编码较上一次§e更接近§7正确编码", "§7机器都会发出比较§e悦耳的声音", "&7另外有技巧地设置编码将有效地§e减少生产成本", "§7不过一旦被观测到正确的常数", "§7常数就会被§c重置", "§7这究竟是神的玩笑还是恶魔的诅咒呢", "", "§c> §7使用§d元物质§7进行制作", "§c> §7调整下方的物品修改你想制作的物品", "§6> §b点击此处将编码修改为§a0"));
                            menu.addMenuClickHandler(slot, (p, arg1, arg2, arg3) -> {
                                StorageCacheUtils.setData(b.getLocation(), "last-code", String.valueOf(setCode));
                                setCode[loc] = '0';
                                StorageCacheUtils.setData(b.getLocation(), "set-code", String.valueOf(setCode));
                                this.newInstance(menu, b);
                                return false;
                            });
                        }
                    }
                } catch (Exception ignored) {
                }
                ItemStack resultItem = CreatorMachine.this.recipes.get(Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "output-item"))).getOutput()[0];
                menu.replaceExistingItem(22, new CustomItemStack(resultItem, "§7制作: " + resultItem.getItemMeta().getDisplayName()));
                menu.addMenuClickHandler(22, (p, arg1, arg2, arg3) -> {
                    Random random = new Random();
                    StorageCacheUtils.setData(b.getLocation(), "random-code", String.valueOf(random.nextInt(127)));
                    int outItem = Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "output-item"));
                    StorageCacheUtils.setData(b.getLocation(), "output-item", (outItem >= CreatorMachine.this.recipes.size() - 1) ? "0" : String.valueOf(++outItem));
                    this.newInstance(menu, b);
                    return false;
                });
            }

            public boolean canOpen(@NotNull Block b, @NotNull Player p) {
                boolean perm = (p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b, Interaction.INTERACT_BLOCK));
                return (perm && CreatorMachine.this.canUse(p, false));
            }

            public int[] getSlotsAccessedByItemTransport(@NotNull ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.INSERT)) {
                    return CreatorMachine.this.getInputSlots();
                }
                return CreatorMachine.this.getOutputSlots();
            }
        };
    }

    public void constructMenu(@NotNull BlockMenuPreset preset) {
        for (int i : codeBorder) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 15), " "), (player, i15, itemStack, clickAction) -> false);
        }
        for (int i : border) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7), " "), (player, i14, itemStack, clickAction) -> false);
        }
        for (int i : outputSign) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 11), "§b输出槽"), (player, i13, itemStack, clickAction) -> false);
        }
        for (int i : inputSign) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 4), "§e输入槽"), (player, i12, itemStack, clickAction) -> false);
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
        for (int i : code) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7), " "), (player, i1, itemStack, clickAction) -> false);
        }
    }

    public int[] getInputSlots() {
        return new int[]{37, 38};
    }

    public int[] getOutputSlots() {
        return new int[]{42, 43};
    }

    public MachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return (getProcessing(b) != null);
    }

    public void registerRecipe(@NotNull MachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks());
        this.recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    public @NotNull List<MachineRecipe> getMachineRecipes() {
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

    protected void pushMainItems(@NotNull Block b, ItemStack[] items) {
        if (StorageCacheUtils.getData(b.getLocation(), "random-code").equals(String.valueOf(invertBinary(StorageCacheUtils.getData(b.getLocation(), "set-code"))))) {
            Random random = new Random();
            StorageCacheUtils.setData(b.getLocation(), "random-code", String.valueOf(random.nextInt(127)));
            Inventory inv = inject(b);
            inv.addItem(items);
            for (int slot : getOutputSlots()) {
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, inv.getItem(slot));
            }
            b.getLocation().getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        } else {
            int randomCode = Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "random-code"));
            if (Math.abs(randomCode - invertBinary(StorageCacheUtils.getData(b.getLocation(), "last-code"))) > Math.abs(randomCode - invertBinary(StorageCacheUtils.getData(b.getLocation(), "set-code")))) {
                b.getLocation().getWorld().playSound(b.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
            } else {
                b.getLocation().getWorld().playSound(b.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
            }
        }
    }

    public void register(@NotNull SlimefunAddon addon) {
        addItemHandler(new BlockTicker() {
            public void tick(@NotNull Block b, SlimefunItem sf, Config data) {
                CreatorMachine.this.tick(b);
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
                pushMainItems(b, this.recipes.get(Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "output-item"))).getOutput());
                progress.remove(b);
                processing.remove(b);
            }
        } else {
            MachineRecipe r = null;
            Map<Integer, Integer> found = new HashMap<>();
            for (MachineRecipe recipe : this.recipes) {
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
                if (!fits(b, this.recipes.get(Integer.parseInt(StorageCacheUtils.getData(b.getLocation(), "output-item"))).getOutput())) {
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

    private int invertBinary(@NotNull String binary) {
        char[] chars = binary.toCharArray();
        int result = 0;
        int i = 6;
        for (char c : chars) {
            if (c == '1') {
                result = (int) (result + Math.pow(2.0D, i));
            }
            i--;
        }
        return result;
    }

    public abstract @NotNull String getInventoryTitle();

    public abstract ItemStack getProgressBar();

    public abstract void registerDefaultRecipes();

    public abstract int getEnergyConsumption();

    public abstract @NotNull String getMachineIdentifier();
}
