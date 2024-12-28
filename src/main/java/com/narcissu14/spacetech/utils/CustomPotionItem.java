package com.narcissu14.spacetech.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Narcissu14
 */
public class CustomPotionItem extends ItemStack {

    public CustomPotionItem(PotionEffectType potionEffectType) {
        this(potionEffectType, false);
    }

    public CustomPotionItem(PotionEffectType potionEffectType, boolean glow) {
        super(new ItemStack(Material.POTION));
        PotionMeta pMeta = (PotionMeta) this.getItemMeta();
        pMeta.addCustomEffect(new PotionEffect(potionEffectType, 1, 1), false);
        pMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        if (glow) {
            pMeta.addEnchant(Enchantment.DURABILITY, 1, false);
            pMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        this.setItemMeta(pMeta);
    }
}
