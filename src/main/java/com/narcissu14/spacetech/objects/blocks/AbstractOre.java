package com.narcissu14.spacetech.objects.blocks;

import com.narcissu14.spacetech.objects.STItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Narcissu14
 */
public abstract class AbstractOre extends SlimefunItem {
    final String DURABILITY = "durability";
    final String BREAK_TIMES = "break-times";

    public AbstractOre(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);
    }

    void damageItem(Player player){
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !item.hasItemMeta()) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (!(meta instanceof Damageable)) {
        	return;
        }
        Damageable damage = (Damageable)meta;
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

    int getBreakAmount(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null) {
            return 1;
        }
        return STItems.getToolBreakAmount(item);
    }
}
