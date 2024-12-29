package com.narcissu14.spacetech.container;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * @author Narcissu14
 * WARNING: 每次获取配方结果前，请务必判断配方类型
 * 这里暂时为了更高的效率，不做安全性处理
 */
public class PointMachineRecipe {

    @Getter
    private final ItemStack[] input;
    @Getter
    private final int points;
    @Getter
    private final RecipeResultType type;
    private final PointCheckType check;
    @Getter
    private final int condition;
    @Getter
    private int ticks;
    //输出
    @Getter
    private ItemStack[] output;
    @Getter
    private int valueMax;

    //TODO 需要对扣除点数的配方进行点数判断
    public PointMachineRecipe(int seconds, ItemStack[] input, int points, ItemStack[] output, PointCheckType check, int condition) {
        this.ticks = seconds * 2;
        this.input = input;
        this.points = points;
        type = RecipeResultType.ITEM;
        this.check = check;
        this.condition = condition;

        this.output = output;
    }

    public PointMachineRecipe(int seconds, ItemStack[] input, int points, int valueMax, PointCheckType check, int condition) {
        this.ticks = seconds * 2;
        this.input = input;
        this.points = points;
        type = RecipeResultType.LORE;
        this.check = check;
        this.condition = condition;

        this.valueMax = valueMax;
    }

    public PointMachineRecipe(int seconds, ItemStack[] input, int points, ItemStack[] output) {
        this.ticks = seconds * 2;
        this.input = input;
        this.points = points;
        type = RecipeResultType.ITEM;
        this.check = PointCheckType.NO_CHECK;
        this.condition = 0;

        this.output = output;
    }

    public PointMachineRecipe(int seconds, ItemStack[] input, int points, int valueMax) {
        this.ticks = seconds * 2;
        this.input = input;
        this.points = points;
        type = RecipeResultType.LORE;
        this.check = PointCheckType.NO_CHECK;
        this.condition = 0;

        this.valueMax = valueMax;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public PointCheckType getCheckType() {
        return check;
    }

    public enum RecipeResultType {
        /**
         * 用于标识当前配方的输出类型
         */
        ITEM, LORE
    }

    public enum PointCheckType {
        /**
         * 用于标识当前配方的扣除值检查类型
         */
        BIG_THAN, SMALL_THAN, BIG_THAN_OR_EQUAL, SMALL_THAN_OR_EQUAL, EQUAL, NO_CHECK
    }
}
