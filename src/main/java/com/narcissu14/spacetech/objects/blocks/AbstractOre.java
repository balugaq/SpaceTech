package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

/**
 * @author Narcissu14
 */
public abstract class AbstractOre extends SlimefunItem {
    final String DURABILITY = "durability";
    final String BREAK_TIMES = "break-times";

    public AbstractOre(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    void damageItem(@NotNull Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (!(meta instanceof Damageable)) {
            return;
        }
        Damageable damage = (Damageable) meta;
        if (item.getType().toString().endsWith("_PICKAXE")) {
            short dur = (short) (damage.getDamage() + 1);
            if (dur >= item.getType().getMaxDurability()) {
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                return;
            }
            damage.setDamage(dur);
            item.setItemMeta(meta);
            player.updateInventory();
        }
    }

    int getBreakAmount(@NotNull Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        return STItems.getToolBreakAmount(item);
    }
}
