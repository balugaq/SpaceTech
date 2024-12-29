package com.narcissu14.spacetech.setup;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public class UURecipe {
    @Getter
    int ticks;
    @Getter
    ItemStack[] input;
    int uu;


    public UURecipe(int seconds, ItemStack[] input, int uuAmount) {

        this.ticks = seconds * 2;

        this.input = input;

        this.uu = uuAmount;

    }


    public int getUUAmount() {

        return this.uu;

    }


    public void setTicks(int ticks) {

        this.ticks = ticks;

    }

}
/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunItem\container\UURecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */