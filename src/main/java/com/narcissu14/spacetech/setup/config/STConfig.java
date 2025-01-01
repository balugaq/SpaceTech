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

    public STConfig(@NotNull File configFile) {
        super(configFile);
    }

    @Override
    public void initOptions(@NotNull YamlConfiguration config) {
        spaceWorldList = config.getStringList("SpaceWorld.SpaceWorldList");
    }

    @Override
    public void reloadConfig() {
        super.config = YamlConfiguration.loadConfiguration(super.file);
        initOptions(super.config);
    }
}
