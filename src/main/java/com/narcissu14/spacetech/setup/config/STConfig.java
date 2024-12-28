package com.narcissu14.spacetech.setup.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

/**
 * @author Narcissu14
 */
public class STConfig extends AbstractConfig {
    public static List<String> spaceWorldList;
    public static boolean originalSlimefun;

    public STConfig(File configFile) {
        super(configFile);
    }

    @Override
    public void initOptions(YamlConfiguration config) {
        spaceWorldList = config.getStringList("SpaceWorld.SpaceWorldList");
        originalSlimefun = config.getBoolean("OriginalSlimefun");
    }

    @Override
    public void reloadConfig() {
        super.config = YamlConfiguration.loadConfiguration(super.file);
        initOptions(super.config);
    }
}
