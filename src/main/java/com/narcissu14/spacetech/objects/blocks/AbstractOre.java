package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

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
        if (!item.getType().toString().endsWith("_PICKAXE")) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        //无法破坏的工具（如基岩镐、电力钻机）不消耗耐久
        if (meta == null || meta.isUnbreakable() || !(meta instanceof Damageable damage)) {
            return;
        }
        int maxDurability = item.getType().getMaxDurability();
        if (maxDurability <= 0) {
            return;
        }
        //耐久附魔与原版一致：等级 n 时有 1/(n+1) 的概率仍然消耗耐久
        int unbreaking = meta.getEnchantLevel(Enchantment.DURABILITY);
        if (unbreaking > 0 && ThreadLocalRandom.current().nextInt(unbreaking + 1) != 0) {
            return;
        }
        int damageNow = damage.getDamage() + 1;
        if (damageNow >= maxDurability) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 0.8F, 1F);
            return;
        }
        damage.setDamage(damageNow);
        item.setItemMeta(meta);
        player.updateInventory();
    }

    int getBreakAmount(@NotNull Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        return STItems.getToolBreakAmount(item);
    }
}
