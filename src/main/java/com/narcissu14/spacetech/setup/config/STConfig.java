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

    public STConfig(@NotNull File configFile) {
        super(configFile);
    }

    @Override
    public void initOptions(@NotNull YamlConfiguration config) {
        spaceWorldList = config.getStringList("SpaceWorld.SpaceWorldList");
        bedrockBreakExtendMax = Math.max(1, config.getInt("Mechanics.BedrockBreakMachine.ExtendBlocks", 4));
    }

    @Override
    public void reloadConfig() {
        super.config = YamlConfiguration.loadConfiguration(super.file);
        initOptions(super.config);
    }
}
