package com.narcissu14.spacetech.setup.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

/**
 * @author Narcissu14
 */
public class STConfig extends AbstractConfig {
    public static List<String> spaceWorldList;
    /**
     * 基岩破穿器一次启动最多沿破穿方向连续破穿多少格基岩
     */
    public static int bedrockBreakExtendMax;
    /**
     * 太空GPS选址的随机搜索半径
     */
    public static int spaceGpsSearchRadius;
    /**
     * 太空GPS选址的最大随机尝试次数
     */
    public static int spaceGpsMaxFindTimes;
    /**
     * 是否打印每次GPS选址尝试的坐标
     */
    public static boolean debugSpaceGps;

    public STConfig(@NotNull File configFile) {
        super(configFile);
    }

    @Override
    public void initOptions(@NotNull YamlConfiguration config) {
        spaceWorldList = config.getStringList("SpaceWorld.SpaceWorldList");
        bedrockBreakExtendMax = Math.max(1, config.getInt("Mechanics.BedrockBreakMachine.ExtendBlocks", 4));
        spaceGpsSearchRadius = Math.max(16, config.getInt("Mechanics.SpaceGPS.SearchRadius", 500));
        spaceGpsMaxFindTimes = Math.max(1, config.getInt("Mechanics.SpaceGPS.MaxFindTimes", 64));
        debugSpaceGps = config.getBoolean("Mechanics.SpaceGPS.Debug", false);
    }

    @Override
    public void reloadConfig() {
        super.config = YamlConfiguration.loadConfiguration(super.file);
        initOptions(super.config);
    }
}
