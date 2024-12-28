package com.narcissu14.spacetech.objects;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.utils.CustomPotionItem;
import com.narcissu14.spacetech.utils.ItemUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Narcissu14
 */
public class STItems {
    private final static String OXYGEN_LORE = "§8\u21E8 §b\uA562§7 ";
    /** 普通的太空矿物 */
    public final static List<ItemStack> NORMAL_SPACE_ORE = new ArrayList<>();
    /** 星核中的太空矿物 */
    public final static List<ItemStack> RARE_SPACE_ORE = new ArrayList<>();
    /** 特殊矿物ID表 */
    public final static List<String> SPACE_ORE_LIST = new ArrayList<>();
    /** 特殊的挖矿工具，及其每次的挖掘度 */
    private static HashMap<String, Integer> miningTools = new HashMap<>();
    /** 特殊的电力挖矿工具，及其每次的挖掘度 */
    private static HashMap<ItemStack, Integer> eMiningTools = new HashMap<>();

    public static ItemStack COPPER_ORE_NUGGET = new CustomItem(new ItemStack(Material.GOLD_NUGGET), "§e铜矿粒", "", "§7小块的铜矿石碎片", "§7一定量的碎片可以拼成一整块硬铜矿");
    public static ItemStack COPPER_ORE = null;
    public static ItemStack SILVER_ORE_NUGGET = new CustomItem(new ItemStack(Material.LIGHT_GRAY_DYE, 1), "§e银矿粒", "", "§7小块的银矿石碎片", "§7一定量的碎片可以拼成一整块灰银矿");
    public static ItemStack SILVER_ORE = null;
    public static ItemStack TITANIUM_ORE_NUGGET;
    public static ItemStack TITANIUM_ORE = null;
    public static ItemStack TITANIUM = null;
    public static ItemStack TITANIUM_PLATE = new CustomItem(new ItemStack(Material.PAPER), "§d钛板", "", "§7使用钛金属制成的高强度板材");
    public static ItemStack NEODYMIUM_ORE_NUGGET = new CustomItem(new ItemStack(Material.CYAN_DYE, 1), "§d钕矿粒", "", "§7小块的钕矿碎片", "§7一定量的碎片可以拼成一整块钕矿");
    public static ItemStack NEODYMIUM_ORE = null;
    public static ItemStack NEODYMIUM = null;
    public static ItemStack ALUMINIUM_PLATE = new CustomItem(new ItemStack(Material.PAPER), "§b铝板", "", "§7延展性优秀的轻质金属板材");

    public static ItemStack ELEC_REFINING_MACHINE_1 = null;

    @SuppressWarnings("deprecation")
    // 虽然带耐久度的构造函数废弃了,但依旧十分好用
    public static ItemStack STEEL_PICKAXE = new CustomItem(Material.IRON_PICKAXE, "§a钢制镐", 0, new String[]{"", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 7"},
            new String[]{"DURABILITY-1"});
    @SuppressWarnings("deprecation")
    public static ItemStack DAMASCUS_PICKAXE = new CustomItem(Material.IRON_PICKAXE, "§a水纹钢镐", 0, new String[]{"", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 9"},
            new String[]{"DURABILITY-2"});
    @SuppressWarnings("deprecation")
    public static ItemStack HARDENED_METAL_PICKAXE = new CustomItem(Material.IRON_PICKAXE, "§a精钢镐", 0, new String[]{ "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 11"},
            new String[]{"DURABILITY-3"});
    @SuppressWarnings("deprecation")
    public static ItemStack REINFORCED_ALLOY_PICKAXE = new CustomItem(Material.IRON_PICKAXE, "§a强化合金镐", 0, new String[]{ "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 14"},
            new String[]{"DURABILITY-5"});
    @SuppressWarnings("deprecation")
    public static ItemStack CARBONADO_PICKAXE = new CustomItem(Material.DIAMOND_PICKAXE, "§a黑钻镐", 0, new String[]{ "", "§7特制的矿镐", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 20"},
            new String[]{"DURABILITY-2"});

    public static ItemStack EMPTY_UNIT = new CustomItem(new ItemStack(Material.GLASS_BOTTLE), "§9空单元", "", "§7一个标准化物质存储单元", "§7用于承载一些物质", "§7或是作为一些物品的部件");

    public static ItemStack SUPER_CIRCUIT_BOARD = new CustomItem(new ItemStack(Material.DETECTOR_RAIL), "§d超级电路板", "", "§7使用最尖端材料制作的电路板", "§7用于制造一些高科技产品");

    public static ItemStack SPACE_HELMET_1 = null;
    public static ItemStack SPACE_HELMET_2 = null;
    public static ItemStack SPACE_HELMET_3 = null;
    public static ItemStack SPACE_HELMET_4 = null;

    public static ItemStack OXYGEN_CHARGE_MACHINE = null;

    public static ItemStack HYDRAZINE_MACHINE = null;
    public static ItemStack SPACE_GPS_MARK_MACHINE = null;

    public static ItemStack UNIT_HYDRAZINE = new CustomItem(new CustomPotionItem(PotionEffectType.HEALTH_BOOST), "§9联氨单元", "", "§7一个单元的联氨", "§7联氨多用作于太空发射的燃料");
    public static ItemStack HARD_AXLE = new CustomItem(new ItemStack(Material.BLAZE_ROD), "§e高强度转轴", "", "§7兼具硬度与韧度的转轴", "§7可用于加工制造");

    public static ItemStack BEDROCK_DRILL = new CustomItem(new ItemStack(Material.HOPPER), "§9基岩钻头", "", "§7放入基岩破穿器使用", "§7可以用来破穿基岩");
    public static ItemStack BEDROCK_BREAK_MACHINE = null;

    public static ItemStack ITEM_CREATER_CODE_SCANNER = new CustomItem(new ItemStack(Material.CLOCK), "§9物质编码解译器", "", "&c&o&8\u21E8 &e\u26A1 &70 / 2048 J", "", "§eShift + 右键 §7物质制造机使用", "§7可以为你解译出首两位的编码");

    public static ItemStack EM_RESTRAINT_DEVICE = new CustomItem(new ItemStack(Material.COMPARATOR), "§e电磁拘束装置", "", "§7用于制造一些高科技产品", "§7可以通过电磁效应捕获、固定物质");

    @SuppressWarnings("deprecation")
	public static ItemStack EMPTY_EM_UNIT = new CustomItem(Material.GLASS_BOTTLE, "§e空电磁拘束单元", 0, new String[]{"", "§7通过电磁技术", "§7这个单元可以储存一些不稳定物质"}, new String[]{"DURABILITY-1"});
    public static ItemStack ANTIMATTER_EM_UNIT = new CustomItem(new CustomPotionItem(PotionEffectType.GLOWING, true), "§e反物质单元", "", "§7通过电磁拘束技术贮存反物质避免碰撞湮灭");
    public static ItemStack BEDROCK_EM_UNIT = new CustomItem(new CustomPotionItem(PotionEffectType.FAST_DIGGING, true), "§e基岩粉末单元","", "§7基岩粉末一旦接触普通物质", "§7就会极快地分解消散", "§7但利用电磁拘束技术", "§7现在基岩粉末能够被保存了");
    public static ItemStack BEDROCK_EM_UNIT_3 = new CustomItem(new CustomPotionItem(PotionEffectType.FAST_DIGGING, true), "§e基岩粉末单元 §8*3","", "§7基岩粉末一旦接触普通物质", "§7就会极快地分解消散", "§7但利用电磁拘束技术", "§7现在基岩粉末能够被保存了");

    public static ItemStack SPACE_ORE_SCANNER = new CustomItem(new ItemStack(Material.COMPASS), "§e太空矿物测距仪", "", "&c&o&8\u21E8 &e\u26A1 &70 / 256 J", "§7能够检测某个方块周围§a5§7格范围中的太空矿物", "§eShift + 右键 §7方块使用");

    public static ItemStack PARTICLE_GENERATOR_1 = new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS, 1), "§e高能粒子发电机", "", "§7只能在太空中使用", "§7利用太空中的高能粒子发电", "&8\u21E8 &e\u26A1 &7128 J 缓存", "&8\u21E8 &e\u26A1 &732 J/s");
    public static ItemStack PARTICLE_GENERATOR_2 = new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS, 1), "§6强化型高能粒子发电机", "", "§7只能在太空中使用", "§7利用太空中的高能粒子发电", "&8\u21E8 &e\u26A1 &7256 J 缓存", "&8\u21E8 &e\u26A1 &764 J/s");
    public static ItemStack PARTICLE_GENERATOR_3 = new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS, 1), "§c超级高能粒子发电机", "", "§7只能在太空中使用", "§7利用太空中的高能粒子发电", "&8\u21E8 &e\u26A1 &7512 J 缓存", "&8\u21E8 &e\u26A1 &7128 J/s");

    public static ItemStack ANTIMATTER_COLLECT_MACHINE = null;
    public static ItemStack ANTIMATTER_GENERATOR = null;

    public static ItemStack ANTIMATTER_BEDROCK_DRILL = new CustomItem(new ItemStack(Material.HOPPER), "§9反物质爆破钻头", "", "§7放入基岩爆碎机使用", "§7可以用来爆碎基岩获取基岩粉末");

    public static ItemStack BEDROCK_CRACK_MACHINE = null;

    public static ItemStack UNIT_ITEM_CRAFTER = null;

    public static ItemStack RIG = new CustomItem(new ItemStack(Material.TRIPWIRE_HOOK), "§9钻机", "", "§7用于合成矿钻");
    public static ItemStack ORE_RIG_REINFORCED = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.GOLDEN_PICKAXE), "§e强化合金钻机", "", "&c&o&8\u21E8 &e\u26A1 &70 / 256 J", "", "§7特制的矿钻", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 30"), true);
    public static ItemStack ORE_RIG_CARBONADO = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.GOLDEN_PICKAXE), "§e黑钻石钻机", "", "&c&o&8\u21E8 &e\u26A1 &70 / 512 J", "", "§7特制的矿钻", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 40"), true);
    public static ItemStack ORE_RIG_TITIANIUM = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.GOLDEN_PICKAXE), "§e钛合金钻机", "", "&c&o&8\u21E8 &e\u26A1 &70 / 1024 J", "", "§7特制的矿钻", "§7用于挖掘一些特殊矿物", "§7挖普通的方块并没有加成", "§e挖掘能力:§6 50"), true);

    public static ItemStack BEDROCK_DIAMOND_PICKAXE = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.DIAMOND_PICKAXE), "§d基岩镐§7(§b钻石外形§7)", "", "§7坚不可摧的基岩矿镐", "§7外部装饰成了钻石镐的样子"), true);
    public static ItemStack BEDROCK_DIAMOND_SWORD = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.DIAMOND_SWORD), "§d基岩剑§7(§b钻石外形§7)", "", "§7坚不可摧的基岩剑", "§7外部装饰成了钻石剑的样子"), true);
    public static ItemStack BEDROCK_DIAMOND_SPADE = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.DIAMOND_SHOVEL), "§d基岩铲§7(§b钻石外形§7)", "", "§7坚不可摧的基岩铲", "§7外部装饰成了钻石铲的样子"), true);
    public static ItemStack BEDROCK_DIAMOND_AXE = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.DIAMOND_AXE), "§d基岩斧§7(§b钻石外形§7)", "", "§7坚不可摧的基岩斧", "§7外部装饰成了钻石斧的样子"), true);
    public static ItemStack BEDROCK_DIAMOND_HOE = ItemUtils.setUnbreakable(new CustomItem(new ItemStack(Material.DIAMOND_HOE), "§d基岩锄§7(§b钻石外形§7)", "", "§7坚不可摧的基岩锄", "§7外部装饰成了钻石锄的样子"), true);

    static {
        try {
            ItemMeta emUnitMeta = EMPTY_EM_UNIT.getItemMeta();
            emUnitMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            EMPTY_EM_UNIT.setItemMeta(emUnitMeta);

            COPPER_ORE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjBjNzliMThmOGEwYmQ2ZGM4ZmE4MGU0NjRiMmYyZWViM2E4NzdmY2VlYjZhMjc2Mjg0ZTE4YWRjM2NhNmE2In19fQ=="),
                    "&e硬铜矿", "", "§7一块被坚硬杂质混构成的铜矿石", "§7普通的稿子挖掘起来非常艰难", "§7挖掘次数过多获得的矿石量会减少");
            SILVER_ORE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQzOWFlNWE4ZDUzODNkNjY4Y2Q1NGJlYzhmOThjYzZjODI2NTk5YWRmNzlkNmJiNjlmNTFiMGU4YzQ1OGIifX19"),
                    "&f灰银矿", "", "§7一块表面呈灰色的银矿石", "§7普通的稿子挖掘起来非常艰难", "§7挖掘次数过多获得的矿石量会减少");
            TITANIUM_ORE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg2ZjhkYjY1MDgzM2M1MmE2NzAzZWVmN2U2YzcwYTIzNjczNGJmOWQxMzJjODU1ZGU0ZTZmOTllNGE5MjAifX19"),
                    "&d金红石矿", "", "§7矿脉闪耀着金红色光芒的矿石", "§7可以用于提炼钛", "§7挖掘起来极度艰难", "§7挖掘次数过多获得的矿石量会减少");
            TITANIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU5MmVjOWFhOTgwYzY4NTQ4ZDhiMzk2ZjYxZGY5ZGY1MTZkMDRiZmMyNjdjMzQ4NGQ1ZjJkYzg2OTkxZSJ9fX0="),
                    "&d钛", "", "§7一种极富实用价值的金属", "§7许多高精设备的制造都需要使用到它");
            NEODYMIUM_ORE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg0YmU0YTQ1OGRjNjkyOTljYjIwOTFkYmJiMTYwMTY3ZGYyNTE2N2Q0ZTQwZDI0ODg1OGE1YjgxYWRhMyJ9fX0="),
                    "&d钕矿", "", "§7一种稀土金属矿石", "§7可以用于提炼钕", "§7挖掘起来极度艰难", "§7挖掘次数过多获得的矿石量会减少");
            NEODYMIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRhNzcwYjY4NzRmYWJkMjhhNzM0NTI2YzU3MzlmMGNkNmI2YTk1NDhjYjlkZGI1NmZiMmRjMjVmODQ5NWMxIn19fQ=="),
                    "&d钕", "", "§7一种稀有的金属", "§7可以制作出高磁性的物品");
            ELEC_REFINING_MACHINE_1 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVmNzc0MTAxMGUyZDJmZTUxYzMwNDI0NDkxZmMyNWVkNWM0ODE0N2RjYTY5NDVjYWNhYWNhMjQ0NjQxIn19fQ=="),
                            "&b电磁炼矿机&7-&eI", "", "§7利用电磁精炼矿石的机器", "§7许多高级的矿产需要用这个机器炼出", "&8\u21E8 &e\u26A1 &7512 J 缓存", "&8\u21E8 &e\u26A1 &764 J/s");
            SPACE_HELMET_1 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eI", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 600");
            SPACE_HELMET_2 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eII", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 1200");
            SPACE_HELMET_3 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eIII", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 2400");
            SPACE_HELMET_4 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4YWFkNjczMTU3YzkyMzE3YTg4YjFmODZmNTI3MWYxY2Q3Mzk3ZDdmYzhlYzMyODFmNzMzZjc1MTYzNCJ9fX0="),
                    "&b太空盔&8-&eIV", "", "§8> 氧气装备", "§7能够在无氧环境中供氧", "§7保障你的生命安全", OXYGEN_LORE + "0", "§8\u21E8 §9\uA563§7 4000");
            OXYGEN_CHARGE_MACHINE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmIyYWJkNjY5MzlmNGNiNzI1N2E4OGNmNTJmYmM2ZmRjZWVjMTQzM2VjMmE2ZWYxNmQ2MmUzNGY2MjM4NzgxIn19fQ=="),
                    "&b电力充氧机", "", "§7通过电解水生产氧气", "§7生产出的氧气可供储氧设备进行充氧", "&8\u21E8 &e\u26A1 &7256 J 缓存", "&8\u21E8 &e\u26A1 &732 J/s");
            HYDRAZINE_MACHINE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJiNTU1NjBjNjk1ZDk3NmIzNDZlMTg4ZDNkZjJiY2Q4YzVhYTMyYjkzMzE0MWE5NzE1YzQyZjY0Y2I2Y2VlIn19fQ=="),
                    "&b联氨合成机", "", "§7利用空气和腐烂、发酵材料合成联氨", "§7蓄满联氨后", "§7可以使用空单元灌装联氨", "&8\u21E8 &e\u26A1 &7512 J 缓存", "&8\u21E8 &e\u26A1 &764 J/s");
            SPACE_GPS_MARK_MACHINE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmMzMwZjA4MGEyYTNkMWE4YWRhYjNlNjdmM2VkMzgxMTY1OGIyODczMDVkYjRiNWM0YTYwOTc3NTc1MzUifX19"),
                    "&6太空GPS发射机", "", "§7充满联氨后可以进行一次发射", "§7成功发射后，将为你在太空中设置一个GPS标记点", "&8\u21E8 &e\u26A1 &7128 J 缓存", "&8\u21E8 &e\u26A1 &716 J/s");
            BEDROCK_BREAK_MACHINE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ3N2Y3ODhlNmVmMjFmMTVkNGEzYzg5Mzg3NTM3MDIwMWU0MmViNjAyMWNjMWZlMDZhOTcxMmIwMjI4MDNhIn19fQ=="),
                    "&9基岩破穿器", "", "§7使用钻头对基岩进行破穿", "§7可以对相接的6个任一方向进行破穿", "&8\u21E8 &e\u26A1 &71024 J 缓存", "&8\u21E8 &e\u26A1 &7128 J/s");
            ANTIMATTER_COLLECT_MACHINE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJiNTU1NjBjNjk1ZDk3NmIzNDZlMTg4ZDNkZjJiY2Q4YzVhYTMyYjkzMzE0MWE5NzE1YzQyZjY0Y2I2Y2VlIn19fQ=="),
                    "&d反物质捕获机", "", "§7使用电磁拘束技术", "§7对宇宙空间中的反物质粒子进行捕捉", "§7捕捉过程需要消耗青金石", "&8\u21E8 &e\u26A1 &71024 J 缓存", "&8\u21E8 &e\u26A1 &7128 J/s");
            //TODO 暂不实装
            ANTIMATTER_GENERATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDlmNDgwNjBhOTZjZGVhZTNjYjQxNzNkMjRmNTQzMTcwNGIyYjEyNDQ4ZDk1ZjMxZDcxY2JlNGE3YjhkZWIifX19"),
                    "&d反物质发电机", "", "§7利用正反物质的湮灭能量发电", "§7能够产出巨量的电力", "§b需要冷却", "&8\u21E8 &e\u26A1 &765536 J 缓存", "&8\u21E8 &e\u26A1 &71536 J/s");
            BEDROCK_CRACK_MACHINE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzE2ZmRkZWMzZWRhNGFlZDhmYTJkZWJhZjQ3NjllYzdjNmYzYTdmYzdiMzRkNTUxZDE2MmZlNGE3NjZkIn19fQ=="),
                    "&d基岩爆碎机", "", "§7利用基岩钻头配合反物质制造基岩扬尘", "§7收集到的基岩粉末可存储到电磁拘束单元中", "&8\u21E8 &e\u26A1 &72048 J 缓存", "&8\u21E8 &e\u26A1 &7256 J/s");
            UNIT_ITEM_CRAFTER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWZjMDVhZjBlODhjNmZiMTBiNGM4YzhiODFiN2FhNjU4ZTY0NjQ5NzI0Y2I3M2JiOWJiMGYyNWYyOGJkIn19fQ=="),
                    "&a单元物质加工机", "", "§7可以将一些存储在单元中的物质加工为方块", "&8\u21E8 &e\u26A1 &71024 J 缓存", "&8\u21E8 &e\u26A1 &7128 J/s");
        } catch (Exception e) {
            e.printStackTrace();
        }

        TITANIUM_ORE_NUGGET = new CustomItem(new ItemStack(SpaceTech.IS_VERSION_13 ? Material.getMaterial("ROSE_RED ") : Material.RED_DYE, 1), "§d金红石矿粒", "", "§7小块的金红石矿碎片", "§7一定量的碎片可以拼成一整块金红石矿");

        //普通太空矿物
        NORMAL_SPACE_ORE.add(COPPER_ORE);
        NORMAL_SPACE_ORE.add(SILVER_ORE);
        //星核矿物
        RARE_SPACE_ORE.add(TITANIUM_ORE);
        RARE_SPACE_ORE.add(NEODYMIUM_ORE);
        //矿物表
        SPACE_ORE_LIST.add("COPPER_ORE");
        SPACE_ORE_LIST.add("SILVER_ORE");
        SPACE_ORE_LIST.add("TITANIUM_ORE");
        SPACE_ORE_LIST.add("NEODYMIUM_ORE");
        //挖掘工具
        miningTools.put("STEEL_PICKAXE", 7);
        miningTools.put("DAMASCUS_PICKAXE", 9);
        miningTools.put("HARDENED_METAL_PICKAXE", 11);
        miningTools.put("REINFORCED_ALLOY_PICKAXE", 14);
        miningTools.put("CARBONADO_PICKAXE", 20);
        //电力挖掘工具
        eMiningTools.put(ORE_RIG_REINFORCED, 30);
        eMiningTools.put(ORE_RIG_CARBONADO, 40);
        eMiningTools.put(ORE_RIG_TITIANIUM, 50);
    }

    public static boolean isEMiningTools(ItemStack item) {
        for (Entry<ItemStack, Integer> entry : eMiningTools.entrySet()) {
            if (SlimefunManager.isItemSimiliar(item, entry.getKey(), false)) {
                return true;
            }
        }
        return false;
    }

    public static int getToolBreakAmount(ItemStack item) {
        for (Entry<ItemStack, Integer> entry : eMiningTools.entrySet()) {
            if (SlimefunManager.isItemSimiliar(item, (entry.getKey()), false)) {
                return entry.getValue();
            }
        }
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem != null && miningTools.containsKey(sfItem.getID())) {
            return miningTools.get(sfItem.getID());
        }
        return 5;
    }

    /**
     * @param item 被检测的物品
     *             检测物品是否为氧气装备
     */
    public static boolean isOxygenEquip(ItemStack item) {
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
        if (!lores.get(1).equals("§8> 氧气装备")) {
            return false;
        }
        return true;
    }

    /**
     * @param item 被检测的物品
     * 检测物品是否有氧气
     */
    public static boolean isEquipHasOxygen(ItemStack item) {
        if (!isOxygenEquip(item)) {
            return false;
        }
        List<String> lores = item.getItemMeta().getLore();
        if (lores.get(lores.size() - 2).equals(OXYGEN_LORE + "0")) {
            return false;
        }
        return true;
    }


    /**
     * @param lores 物品Lores
     * 获取物品的氧气值
     * 本方法需使用前预先检测 STItems#isOxygenEquip(ItemStack item)
     */
    public static int getOxygenValue(List<String> lores) {
        String oxygenLore = lores.get(lores.size() - 2).replace(OXYGEN_LORE, "");
        return Integer.valueOf(oxygenLore);
    }

    /**
     * @param lores 物品Lores
     * @param oxygen 氧气值
     * @param isAdd 若true则进行添加，false则进行氧气值设置
     * 本方法需使用前预先检测 STItems#isOxygenEquip(ItemStack item)
     */
    public static String modifyOxygenValue(List<String> lores, int oxygen, boolean isAdd) {
        int newAmount = oxygen;
        if (isAdd) {
            String oxygenLore = lores.get(lores.size() - 2).replace(OXYGEN_LORE, "");
            int value = Integer.valueOf(oxygenLore);
            newAmount = value + oxygen;
            if (newAmount < 0) {
                newAmount = 0;
            }
        }
        return OXYGEN_LORE + newAmount;
    }
}
