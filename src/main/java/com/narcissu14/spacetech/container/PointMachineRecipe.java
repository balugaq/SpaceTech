package com.narcissu14.spacetech.container;

import org.bukkit.inventory.ItemStack;

/**
 * @author Narcissu14
 * WARNING: 每次获取配方结果前，请务必判断配方类型
 *          这里暂时为了更高的效率，不做安全性处理
 */
public class PointMachineRecipe {

    private int ticks;
    private ItemStack[] input;
    private int points;
    private RecipeResultType type;

    private PointCheckType check;
    private int condition;

    private ItemStack[] output;
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

    public int getTicks() {
        return ticks;
    }

    public ItemStack[] getInput() {
        return this.input;
    }

    public int getPoints() {
        return this.points;
    }

    public RecipeResultType getType() {
        return type;
    }

    public PointCheckType getCheckType() {
        return check;
    }

    public int getCondition() {
        return condition;
    }

    //输出
    public ItemStack[] getOutput() {
        return this.output;
    }

    public int getValueMax() {
        return valueMax;
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
