package com.narcissu14.spacetech.objects;

import com.narcissu14.spacetech.utils.CustomPotionItem;
import com.narcissu14.spacetech.utils.ItemUtils;
import com.narcissu14.spacetech.utils.SkullUtil;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChargeUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Narcissu14
 */
public class STItems {
    public static SlimefunItemStack UU = null;
    public static SlimefunItemStack UU_MACHINE = null;
    public static SlimefunItemStack ITEM_CREATOR = null;
    public static SlimefunItemStack IRIDIUM = null;
    /**
     * 普通的太空矿物
     */
    public final static List<ItemStack> NORMAL_SPACE_ORE = new ArrayList<>();
    /**
     * 星核中的太空矿物
     */
    public final static List<ItemStack> RARE_SPACE_ORE = new ArrayList<>();
    /**
     * 特殊矿物ID表
     */
    public final static List<String> SPACE_ORE_LIST = new ArrayList<>();
    public static final @NotNull SlimefunItemStack COPPER_ORE_NUGGET = new SlimefunItemStack("SPACETECH_COPPER_ORE_NUGGET", new CustomItemStack(new ItemStack(Material.GOLD_NUGGET), "§e铜矿粒", "", "§7小块的铜矿石碎片", "§7一定量的碎片可以拼成一整块硬铜矿"));
    public static final @NotNull SlimefunItemStack SILVER_ORE_NUGGET = new SlimefunItemStack("SPACETECH_SILVER_ORE_NUGGET", new CustomItemStack(new ItemStack(Material.LIGHT_GRAY_DYE, 1), "§e银矿粒", "", "§7小块的银矿石碎片", "§7一定量的碎片可以拼成一整块灰银矿"));
    public static final SlimefunItemStack TITANIUM_ORE_NUGGET;
    public static final @NotNull SlimefunItemStack TITANIUM_PLATE = new SlimefunItemStack("SPACETECH_TITANIUM_PLATE", new CustomItemStack(new ItemStack(Material.PAPER), "§d钛板", "", "§7使用钛金属制成的高强度板材"));
    public static final @NotNull SlimefunItemStack NEODYMIUM_ORE_NUGGET = new SlimefunItemStack("SPACETECH_NEODYMIUM_ORE_NUGGET", new CustomItemStack(new ItemStack(Material.CYAN_DYE, 1), "§d钕矿粒", "", "§7小块的钕矿碎片", "§7一定量的碎片可以拼成一整块钕矿"));
    public static final @NotNull SlimefunItemStack ALUMINIUM_PLATE = new SlimefunItemStack("SPACETECH_ALUMINIUM_PLATE", new CustomItemStack(new ItemStack(Material.PAPER), "§b铝板", "", "§7延展性优秀的轻质金属板材"));
    // 虽然带耐久度的构造函数废弃了,但依旧十分好用
    public static final @NotNull SlimefunItemStack STEEL_PICKAXE = new SlimefunItemStack("SPACETECH_STEEL_PICKAXE", addEnchantment(new CustomItemStack(Material.IRON_PICKAXE, "§a钢制镐", "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 7"), Enchantment.DURABILITY,1));
    public static final @NotNull SlimefunItemStack DAMASCUS_PICKAXE = new SlimefunItemStack("SPACETECH_DAMASCUS_PICKAXE", addEnchantment(new CustomItemStack(Material.IRON_PICKAXE, "§a水纹钢镐", "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 9"), Enchantment.DURABILITY,2));
    public static final @NotNull SlimefunItemStack HARDENED_METAL_PICKAXE = new SlimefunItemStack("SPACETECH_HARDENED_METAL_PICKAXE", addEnchantment(new CustomItemStack(Material.IRON_PICKAXE, "§a精钢镐", "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 11"), Enchantment.DURABILITY,3));
    public static final @NotNull SlimefunItemStack REINFORCED_ALLOY_PICKAXE = new SlimefunItemStack("SPACETECH_REINFORCED_ALLOY_PICKAXE", addEnchantment(new CustomItemStack(Material.IRON_PICKAXE, "§a强化合金镐", "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 14"), Enchantment.DURABILITY,5));
    public static final @NotNull SlimefunItemStack CARBONADO_PICKAXE = new SlimefunItemStack("SPACETECH_CARBONADO_PICKAXE", addEnchantment(new CustomItemStack(Material.DIAMOND_PICKAXE, "§a黑钻镐", "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 20"), Enchantment.DURABILITY, 2));
    public static final @NotNull SlimefunItemStack EMPTY_UNIT = new SlimefunItemStack("SPACETECH_EMPTY_UNIT", new CustomItemStack(new ItemStack(Material.GLASS_BOTTLE), "§9空单元", "", "§7一个标准化物质存储单元", "§7用于承载一些物质", "§7或是作为一些物品的部件"));
    public static final @NotNull SlimefunItemStack SUPER_CIRCUIT_BOARD = new SlimefunItemStack("SPACETECH_SUPER_CIRCUIT_BOARD", new CustomItemStack(new ItemStack(Material.DETECTOR_RAIL), "§d超级电路板", "", "§7使用最尖端材料制作的电路板", "§7用于制造一些高科技产品"));
    public static final @NotNull SlimefunItemStack UNIT_HYDRAZINE = new SlimefunItemStack("SPACETECH_UNIT_HYDRAZINE", new CustomItemStack(new CustomPotionItem(PotionEffectType.HEALTH_BOOST), "§9联氨单元", "", "§7一个单元的联氨", "§7联氨多用作于太空发射的燃料"));
    public static final @NotNull SlimefunItemStack HARD_AXLE = new SlimefunItemStack("SPACETECH_HARD_AXLE", new CustomItemStack(new ItemStack(Material.BLAZE_ROD), "§e高强度转轴", "", "§7兼具硬度与韧度的转轴", "§7可用于加工制造"));
    public static final @NotNull SlimefunItemStack BEDROCK_DRILL = new SlimefunItemStack("SPACETECH_BEDROCK_DRILL", new CustomItemStack(new ItemStack(Material.HOPPER), "§9基岩钻头", "", "§7放入基岩破穿器使用", "§7可以用来破穿基岩"));
    public static final @NotNull SlimefunItemStack ITEM_CREATER_CODE_SCANNER = new SlimefunItemStack("SPACETECH_ITEM_CREATER_CODE_SCANNER", new CustomItemStack(new ItemStack(Material.CLOCK), "§9物质编码解译器", "", "&c&o&8\u21E8 &e\u26A1 &70 / 2048 J", "", "§eShift + 右键 §7物质制造机使用", "§7可以为你解译出首两位的编码"));
    public static final @NotNull SlimefunItemStack EM_RESTRAINT_DEVICE = new SlimefunItemStack("SPACETECH_EM_RESTRAINT_DEVICE", new CustomItemStack(new ItemStack(Material.COMPARATOR), "§e电磁拘束装置", "", "§7用于制造一些高科技产品", "§7可以通过电磁效应捕获、固定物质"));
    public static final @NotNull SlimefunItemStack EMPTY_EM_UNIT = new SlimefunItemStack("SPACETECH_EMPTY_EM_UNIT", new CustomItemStack(Material.GLASS_BOTTLE, "§e空电磁拘束单元", "", "§7通过电磁技术", "§7这个单元可以储存一些不稳定物质", "DURABILITY-1"));
    public static final @NotNull SlimefunItemStack ANTIMATTER_EM_UNIT = new SlimefunItemStack("SPACETECH_ANTIMATTER_EM_UNIT", new CustomItemStack(new CustomPotionItem(PotionEffectType.GLOWING, true), "§e反物质单元", "", "§7通过电磁拘束技术贮存反物质避免碰撞湮灭"));
    public static final @NotNull SlimefunItemStack BEDROCK_EM_UNIT = new SlimefunItemStack("SPACETECH_BEDROCK_EM_UNIT", new CustomItemStack(new CustomPotionItem(PotionEffectType.FAST_DIGGING, true), "§e基岩粉末单元", "", "§7基岩粉末一旦接触普通物质", "§7就会极快地分解消散", "§7但利用电磁拘束技术", "§7现在基岩粉末能够被保存了"));
    public static final @NotNull SlimefunItemStack BEDROCK_EM_UNIT_3 = new SlimefunItemStack("SPACETECH_BEDROCK_EM_UNIT_3", new CustomItemStack(new CustomPotionItem(PotionEffectType.FAST_DIGGING, true), "§e基岩粉末单元 §8*3", "", "§7基岩粉末一旦接触普通物质", "§7就会极快地分解消散", "§7但利用电磁拘束技术", "§7现在基岩粉末能够被保存了"));
    public static final @NotNull SlimefunItemStack SPACE_ORE_SCANNER = new SlimefunItemStack("SPACETECH_SPACE_ORE_SCANNER", new CustomItemStack(new CustomItemStack(new ItemStack(Material.COMPASS), "§e太空矿物测距仪", "", "§7能够检测某个方块周围§a5§7格范围中的太空矿物", "§eShift + 右键 §7方块使用")), meta -> ChargeUtils.setCharge(meta, 0, 256));
    public static final @NotNull SlimefunItemStack PARTICLE_GENERATOR_1 = new SlimefunItemStack("SPACETECH_PARTICLE_GENERATOR_1", new CustomItemStack(new ItemStack(Material.PURPLE_STAINED_GLASS, 1), "§e高能粒子发电机", "", "§7只能在太空中使用", "§7利用太空中的高能粒子发电", "&8\u21E8 &e\u26A1 &732 J/s"));
    public static final @NotNull SlimefunItemStack PARTICLE_GENERATOR_2 = new SlimefunItemStack("SPACETECH_PARTICLE_GENERATOR_2", new CustomItemStack(new ItemStack(Material.PURPLE_STAINED_GLASS, 1), "§6强化型高能粒子发电机", "", "§7只能在太空中使用", "§7利用太空中的高能粒子发电", "&8\u21E8 &e\u26A1 &764 J/s"));
    public static final @NotNull SlimefunItemStack PARTICLE_GENERATOR_3 = new SlimefunItemStack("SPACETECH_PARTICLE_GENERATOR_3", new CustomItemStack(new ItemStack(Material.PURPLE_STAINED_GLASS, 1), "§c超级高能粒子发电机", "", "§7只能在太空中使用", "§7利用太空中的高能粒子发电", "&8\u21E8 &e\u26A1 &7128 J/s"));
    public static final @NotNull SlimefunItemStack ANTIMATTER_BEDROCK_DRILL = new SlimefunItemStack("SPACETECH_ANTIMATTER_BEDROCK_DRILL", new CustomItemStack(new ItemStack(Material.HOPPER), "§9反物质爆破钻头", "", "§7放入基岩爆碎机使用", "§7可以用来爆碎基岩获取基岩粉末"));
    public static final @NotNull SlimefunItemStack RIG = new SlimefunItemStack("SPACETECH_RIG", new CustomItemStack(new ItemStack(Material.TRIPWIRE_HOOK), "§9钻机", "", "§7用于合成矿钻"));
    public static final @NotNull SlimefunItemStack ORE_RIG_REINFORCED = new SlimefunItemStack("SPACETECH_ORE_RIG_REINFORCED", ItemUtils.setUnbreakable(new CustomItemStack(new CustomItemStack(new ItemStack(Material.GOLDEN_PICKAXE), "§e强化合金钻机", "", "§7特制的矿钻", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 30"), meta -> ChargeUtils.setCharge(meta, 0, 256)), true));
    public static final @NotNull SlimefunItemStack ORE_RIG_CARBONADO = new SlimefunItemStack("SPACETECH_ORE_RIG_CARBONADO", ItemUtils.setUnbreakable(new CustomItemStack(new CustomItemStack(new ItemStack(Material.GOLDEN_PICKAXE), "§e黑钻石钻机", "", "§7特制的矿钻", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 40"), meta -> ChargeUtils.setCharge(meta, 0, 512)), true));
    public static final @NotNull SlimefunItemStack ORE_RIG_TITANIUM = new SlimefunItemStack("SPACETECH_ORE_RIG_TITIANIUM", ItemUtils.setUnbreakable(new CustomItemStack(new CustomItemStack(new ItemStack(Material.GOLDEN_PICKAXE), "§e钛合金钻机", "", "§7特制的矿钻", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 50"), meta -> ChargeUtils.setCharge(meta, 0, 1024)), true));
    public static final @NotNull SlimefunItemStack BEDROCK_DIAMOND_PICKAXE = new SlimefunItemStack("SPACETECH_BEDROCK_DIAMOND_PICKAXE", ItemUtils.setUnbreakable(new CustomItemStack(new ItemStack(Material.DIAMOND_PICKAXE), "§d基岩镐§7(§b钻石外形§7)", "", "§7坚不可摧的基岩矿镐", "§7外部装饰成了钻石镐的样子"), true));
    public static final @NotNull SlimefunItemStack BEDROCK_DIAMOND_SWORD = new SlimefunItemStack("SPACETECH_BEDROCK_DIAMOND_SWORD", ItemUtils.setUnbreakable(new CustomItemStack(new ItemStack(Material.DIAMOND_SWORD), "§d基岩剑§7(§b钻石外形§7)", "", "§7坚不可摧的基岩剑", "§7外部装饰成了钻石剑的样子"), true));
    public static final @NotNull SlimefunItemStack BEDROCK_DIAMOND_SPADE = new SlimefunItemStack("SPACETECH_BEDROCK_DIAMOND_SPADE", ItemUtils.setUnbreakable(new CustomItemStack(new ItemStack(Material.DIAMOND_SHOVEL), "§d基岩铲§7(§b钻石外形§7)", "", "§7坚不可摧的基岩铲", "§7外部装饰成了钻石铲的样子"), true));
    public static final @NotNull SlimefunItemStack BEDROCK_DIAMOND_AXE = new SlimefunItemStack("SPACETECH_BEDROCK_DIAMOND_AXE", ItemUtils.setUnbreakable(new CustomItemStack(new ItemStack(Material.DIAMOND_AXE), "§d基岩斧§7(§b钻石外形§7)", "", "§7坚不可摧的基岩斧", "§7外部装饰成了钻石斧的样子"), true));
    public static final @NotNull SlimefunItemStack BEDROCK_DIAMOND_HOE = new SlimefunItemStack("SPACETECH_BEDROCK_DIAMOND_HOE", ItemUtils.setUnbreakable(new CustomItemStack(new ItemStack(Material.DIAMOND_HOE), "§d基岩锄§7(§b钻石外形§7)", "", "§7坚不可摧的基岩锄", "§7外部装饰成了钻石锄的样子"), true));
    private final static String OXYGEN_LORE = "§8\u21E8 §b\uA562§7 ";
    /**
     * 特殊的挖矿工具，及其每次的挖掘度
     */
    private static final @NotNull HashMap<String, Integer> miningTools = new HashMap<>();
    /**
     * 特殊的电力挖矿工具，及其每次的挖掘度
     */
    private static final @NotNull HashMap<ItemStack, Integer> eMiningTools = new HashMap<>();
    public static ItemStack COPPER_ORE = null;
    public static ItemStack SILVER_ORE = null;
    public static ItemStack TITANIUM_ORE = null;
    public static SlimefunItemStack TITANIUM = null;
    public static ItemStack NEODYMIUM_ORE = null;
    public static SlimefunItemStack NEODYMIUM = null;
    public static ItemStack ELEC_REFINING_MACHINE_1 = null;
    public static SlimefunItemStack SPACE_HELMET_1 = null;
    public static SlimefunItemStack SPACE_HELMET_2 = null;
    public static SlimefunItemStack SPACE_HELMET_3 = null;
    public static SlimefunItemStack SPACE_HELMET_4 = null;
    public static ItemStack OXYGEN_CHARGE_MACHINE = null;
    public static ItemStack HYDRAZINE_MACHINE = null;
    public static ItemStack SPACE_GPS_MARK_MACHINE = null;
    public static ItemStack BEDROCK_BREAK_MACHINE = null;
    public static ItemStack ANTIMATTER_COLLECT_MACHINE = null;
    public static ItemStack ANTIMATTER_GENERATOR = null;
    public static ItemStack BEDROCK_CRACK_MACHINE = null;
    public static ItemStack UNIT_ITEM_CRAFTER = null;

    static {
        try {
            UU = new SlimefunItemStack("SPACETECH_UU", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGYxNGYzMTc5Yjg2ZjY5YjNlZmE3NDcyZGFjYWViMjMzOWY2MjkwZDJkODE3MzYyNzkzMzQ4YWJkOThlMDIxIn19fQ=="), "&d元物质", "", "§7这堆元物质被特殊的力场禁锢而得以保存", "§7据研究元物质是万物的最基本组成粒子", "§7珍贵的元物质有非常广泛的用途"));
            UU_MACHINE = new SlimefunItemStack("SPACETECH_UU_MACHINE", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY4NGY0YTZlZDE0Mjg2NWRiMDkzOGU0ODc2NzY4NDlhNTRkNjQzNzhlMmU5ZTdmNzEzYjliMWU5ZDA0MSJ9fX0="), "&d元物质分离机", "", "&7能够将特定的物质分离为的元物质", "&7并将这些元物质收集加以力场保存", "§7元物质用途广泛且极为珍贵", "", "§7▷▷ §b耗电: §e400 J/s", "§7▷▷ §b缓存: §e12800 J"));
            ITEM_CREATOR = new SlimefunItemStack("SPACETECH_ITEM_CREATOR", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE3YjNiMTZlNWQwYzRjZmQyMWM0ZWI5MTMzZTk2OWFhZDdjYzczMDNjY2RmMzE3NTEyZTI2YTQ4NzliNTEifX19"), "&d物质制造机", "", "&7通过手动解析物质编码", "&7使用元物质制造出想要的新物质", "§7甚至制作出自然界不存在的元素物质", "", "§7▷▷ §b耗电: §e768 J/s", "§7▷▷ §b缓存: §e65535 J"));
            IRIDIUM = new SlimefunItemStack("SPACETECH_IRIDIUM", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGNmNWI4ZGViM2Q3YWZlMGY0ZmZhNjI3N2RlZjk3NjYxOWE2N2NjMTYzMTUzMTk4M2U4NTkwODI2ZWRlNjQ2MCJ9fX0="), "&d铱", "", "&7一种稀有的贵重金属", "&7可以用于制作一些高级装备"));

            ItemMeta emUnitMeta = EMPTY_EM_UNIT.getItemMeta();
            emUnitMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            EMPTY_EM_UNIT.setItemMeta(emUnitMeta);

            COPPER_ORE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjBjNzliMThmOGEwYmQ2ZGM4ZmE4MGU0NjRiMmYyZWViM2E4NzdmY2VlYjZhMjc2Mjg0ZTE4YWRjM2NhNmE2In19fQ=="),
                    "&e硬铜矿", "", "§7一块被坚硬杂质混构成的铜矿石", "§7普通的稿子挖掘起来非常艰难", "§7挖掘次数过多获得的矿石量会减少");
            SILVER_ORE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQzOWFlNWE4ZDUzODNkNjY4Y2Q1NGJlYzhmOThjYzZjODI2NTk5YWRmNzlkNmJiNjlmNTFiMGU4YzQ1OGIifX19"),
                    "&f灰银矿", "", "§7一块表面呈灰色的银矿石", "§7普通的稿子挖掘起来非常艰难", "§7挖掘次数过多获得的矿石量会减少");
            TITANIUM_ORE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg2ZjhkYjY1MDgzM2M1MmE2NzAzZWVmN2U2YzcwYTIzNjczNGJmOWQxMzJjODU1ZGU0ZTZmOTllNGE5MjAifX19"),
                    "&d金红石矿", "", "§7矿脉闪耀着金红色光芒的矿石", "§7可以用于提炼钛", "§7挖掘起来极度艰难", "§7挖掘次数过多获得的矿石量会减少");
            TITANIUM = new SlimefunItemStack("SPACETECH_TITANIUM", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU5MmVjOWFhOTgwYzY4NTQ4ZDhiMzk2ZjYxZGY5ZGY1MTZkMDRiZmMyNjdjMzQ4NGQ1ZjJkYzg2OTkxZSJ9fX0="),
                    "&d钛", "", "§7一种极富实用价值的金属", "§7许多高精设备的制造都需要使用到它"));
            NEODYMIUM_ORE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg0YmU0YTQ1OGRjNjkyOTljYjIwOTFkYmJiMTYwMTY3ZGYyNTE2N2Q0ZTQwZDI0ODg1OGE1YjgxYWRhMyJ9fX0="),
                    "&d钕矿", "", "§7一种稀土金属矿石", "§7可以用于提炼钕", "§7挖掘起来极度艰难", "§7挖掘次数过多获得的矿石量会减少");
            NEODYMIUM = new SlimefunItemStack("SPACETECH_NEODYMIUM", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRhNzcwYjY4NzRmYWJkMjhhNzM0NTI2YzU3MzlmMGNkNmI2YTk1NDhjYjlkZGI1NmZiMmRjMjVmODQ5NWMxIn19fQ=="),
                    "&d钕", "", "§7一种稀有的金属", "§7可以制作出高磁性的物品"));
            ELEC_REFINING_MACHINE_1 = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVmNzc0MTAxMGUyZDJmZTUxYzMwNDI0NDkxZmMyNWVkNWM0ODE0N2RjYTY5NDVjYWNhYWNhMjQ0NjQxIn19fQ=="),
                    "&b电磁炼矿机&7-&eI", "", "§7利用电磁精炼矿石的机器", "§7许多高级的矿产需要用这个机器炼出", "&8\u21E8 &e\u26A1 &7512 J 缓存", "&8\u21E8 &e\u26A1 &764 J/s");
            SPACE_HELMET_1 = new SlimefunItemStack("SPACETECH_SPACE_HELMET_1", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eI", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 600"));
            SPACE_HELMET_2 = new SlimefunItemStack("SPACETECH_SPACE_HELMET_2", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eII", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 1200"));
            SPACE_HELMET_3 = new SlimefunItemStack("SPACETECH_SPACE_HELMET_3", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eIII", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 2400"));
            SPACE_HELMET_4 = new SlimefunItemStack("SPACETECH_SPACE_HELMET_4", new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eIV", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 4000"));
            OXYGEN_CHARGE_MACHINE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmIyYWJkNjY5MzlmNGNiNzI1N2E4OGNmNTJmYmM2ZmRjZWVjMTQzM2VjMmE2ZWYxNmQ2MmUzNGY2MjM4NzgxIn19fQ=="),
                    "&b电力充氧机", "", "§7通过电解水生产氧气", "§7生产出的氧气可供储氧设备进行充氧", "&8\u21E8 &e\u26A1 &7256 J 缓存", "&8\u21E8 &e\u26A1 &732 J/s");
            HYDRAZINE_MACHINE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJiNTU1NjBjNjk1ZDk3NmIzNDZlMTg4ZDNkZjJiY2Q4YzVhYTMyYjkzMzE0MWE5NzE1YzQyZjY0Y2I2Y2VlIn19fQ=="),
                    "&b联氨合成机", "", "§7利用空气和腐烂、发酵材料合成联氨", "§7蓄满联氨后", "§7可以使用空单元灌装联氨", "&8\u21E8 &e\u26A1 &7512 J 缓存", "&8\u21E8 &e\u26A1 &764 J/s");
            SPACE_GPS_MARK_MACHINE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmMzMwZjA4MGEyYTNkMWE4YWRhYjNlNjdmM2VkMzgxMTY1OGIyODczMDVkYjRiNWM0YTYwOTc3NTc1MzUifX19"),
                    "&6太空GPS发射机", "", "§7充满联氨后可以进行一次发射", "§7成功发射后，将为你在太空中设置一个GPS标记点", "&8\u21E8 &e\u26A1 &7128 J 缓存", "&8\u21E8 &e\u26A1 &716 J/s");
            BEDROCK_BREAK_MACHINE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ3N2Y3ODhlNmVmMjFmMTVkNGEzYzg5Mzg3NTM3MDIwMWU0MmViNjAyMWNjMWZlMDZhOTcxMmIwMjI4MDNhIn19fQ=="),
                    "&9基岩破穿器", "", "§7使用钻头对基岩进行破穿", "§7可以对相接的6个任一方向进行破穿", "&8\u21E8 &e\u26A1 &71024 J 缓存", "&8\u21E8 &e\u26A1 &7128 J/s");
            ANTIMATTER_COLLECT_MACHINE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJiNTU1NjBjNjk1ZDk3NmIzNDZlMTg4ZDNkZjJiY2Q4YzVhYTMyYjkzMzE0MWE5NzE1YzQyZjY0Y2I2Y2VlIn19fQ=="),
                    "&d反物质捕获机", "", "§7使用电磁拘束技术", "§7对宇宙空间中的反物质粒子进行捕捉", "§7捕捉过程需要消耗青金石", "&8\u21E8 &e\u26A1 &71024 J 缓存", "&8\u21E8 &e\u26A1 &7128 J/s");
            //TODO 暂不实装
            ANTIMATTER_GENERATOR = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDlmNDgwNjBhOTZjZGVhZTNjYjQxNzNkMjRmNTQzMTcwNGIyYjEyNDQ4ZDk1ZjMxZDcxY2JlNGE3YjhkZWIifX19"),
                    "&d反物质发电机", "", "§7利用正反物质的湮灭能量发电", "§7能够产出巨量的电力", "§b需要冷却", "&8\u21E8 &e\u26A1 &765536 J 缓存", "&8\u21E8 &e\u26A1 &71536 J/s");
            BEDROCK_CRACK_MACHINE = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzE2ZmRkZWMzZWRhNGFlZDhmYTJkZWJhZjQ3NjllYzdjNmYzYTdmYzdiMzRkNTUxZDE2MmZlNGE3NjZkIn19fQ=="),
                    "&d基岩爆碎机", "", "§7利用反物质爆破钻头制造&e「基岩扬尘」", "§7再输入电磁拘束单元收集&e「基岩扬尘」", "§7以获取&e「瓶装基岩」", "&8\u21E8 &e\u26A1 &72048 J 缓存", "&8\u21E8 &e\u26A1 &7256 J/s");
            UNIT_ITEM_CRAFTER = new CustomItemStack(SkullUtil.getByBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWZjMDVhZjBlODhjNmZiMTBiNGM4YzhiODFiN2FhNjU4ZTY0NjQ5NzI0Y2I3M2JiOWJiMGYyNWYyOGJkIn19fQ=="),
                    "&a单元物质加工机", "", "§7可以将一些存储在单元中的物质加工为方块", "&8\u21E8 &e\u26A1 &71024 J 缓存", "&8\u21E8 &e\u26A1 &7128 J/s");
        } catch (Exception e) {
            e.printStackTrace();
        }

        TITANIUM_ORE_NUGGET = new SlimefunItemStack("SPACETECH_TITANIUM_ORE_NUGGET",new CustomItemStack(new ItemStack(Material.RED_DYE, 1), "§d金红石矿粒", "", "§7小块的金红石矿碎片", "§7一定量的碎片可以拼成一整块金红石矿"));

        //普通太空矿物
        NORMAL_SPACE_ORE.add(COPPER_ORE);
        NORMAL_SPACE_ORE.add(SILVER_ORE);
        //星核矿物
        RARE_SPACE_ORE.add(TITANIUM_ORE);
        RARE_SPACE_ORE.add(NEODYMIUM_ORE);
        //矿物表
        SPACE_ORE_LIST.add("SPACETECH_COPPER_ORE");
        SPACE_ORE_LIST.add("SPACETECH_SILVER_ORE");
        SPACE_ORE_LIST.add("SPACETECH_TITANIUM_ORE");
        SPACE_ORE_LIST.add("SPACETECH_NEODYMIUM_ORE");
        //挖掘工具
        miningTools.put("SPACETECH_STEEL_PICKAXE", 7);
        miningTools.put("SPACETECH_DAMASCUS_PICKAXE", 9);
        miningTools.put("SPACETECH_HARDENED_METAL_PICKAXE", 11);
        miningTools.put("SPACETECH_REINFORCED_ALLOY_PICKAXE", 14);
        miningTools.put("SPACETECH_CARBONADO_PICKAXE", 20);
        //电力挖掘工具
        eMiningTools.put(ORE_RIG_REINFORCED, 30);
        eMiningTools.put(ORE_RIG_CARBONADO, 40);
        eMiningTools.put(ORE_RIG_TITANIUM, 50);
    }

    public static boolean isEMiningTools(ItemStack item) {
        for (Entry<ItemStack, Integer> entry : eMiningTools.entrySet()) {
            if (SlimefunUtils.isItemSimilar(item, entry.getKey(), false)) {
                return true;
            }
        }
        return false;
    }

    public static int getToolBreakAmount(ItemStack item) {
        for (Entry<ItemStack, Integer> entry : eMiningTools.entrySet()) {
            if (SlimefunUtils.isItemSimilar(item, (entry.getKey()), false)) {
                return entry.getValue();
            }
        }
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem != null && miningTools.containsKey(sfItem.getId())) {
            return miningTools.get(sfItem.getId());
        }
        return 5;
    }

    public static int getToolCapacity(ItemStack item) {
        if (SlimefunUtils.isItemSimilar(ORE_RIG_REINFORCED, item, false, false)) {
            return 256;
        }

        if (SlimefunUtils.isItemSimilar(ORE_RIG_CARBONADO, item, false, false)) {
            return 512;
        }

        if (SlimefunUtils.isItemSimilar(ORE_RIG_TITANIUM, item, false, false)) {
            return 1024;
        }

        return 0;
    }

    /**
     * @param item 被检测的物品
     *             检测物品是否为氧气装备
     */
    public static boolean isOxygenEquip(@Nullable ItemStack item) {
        if (item == null || item.getType().equals(Material.AIR)) {
            return false;
        }
        if (!item.hasItemMeta()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) {
            return false;
        }
        List<String> lores = meta.getLore();
        return lores.get(1).equals("§8> 氧气装备");
    }

    /**
     * @param item 被检测的物品
     *             检测物品是否有氧气
     */
    public static boolean isEquipHasOxygen(@NotNull ItemStack item) {
        if (!isOxygenEquip(item)) {
            return false;
        }
        List<String> lores = item.getItemMeta().getLore();
        return !lores.get(lores.size() - 2).equals(OXYGEN_LORE + "0");
    }


    /**
     * @param lores 物品Lores
     *              获取物品的氧气值
     *              本方法需使用前预先检测 STItems#isOxygenEquip(ItemStack item)
     */
    public static int getOxygenValue(@NotNull List<String> lores) {
        String oxygenLore = lores.get(lores.size() - 2).replace(OXYGEN_LORE, "");
        return Integer.parseInt(oxygenLore);
    }

    /**
     * @param lores  物品Lores
     * @param oxygen 氧气值
     * @param isAdd  若true则进行添加，false则进行氧气值设置
     *               本方法需使用前预先检测 STItems#isOxygenEquip(ItemStack item)
     */
    public static @NotNull String modifyOxygenValue(@NotNull List<String> lores, int oxygen, boolean isAdd) {
        int newAmount = oxygen;
        if (isAdd) {
            String oxygenLore = lores.get(lores.size() - 2).replace(OXYGEN_LORE, "");
            int value = Integer.parseInt(oxygenLore);
            newAmount = value + oxygen;
            if (newAmount < 0) {
                newAmount = 0;
            }
        }
        return OXYGEN_LORE + newAmount;
    }

    public static @NotNull ItemStack addEnchantment(@NotNull ItemStack item, @NotNull Enchantment enchantment, int level) {
        item.addUnsafeEnchantment(enchantment, level);
        return item;
    }
}
