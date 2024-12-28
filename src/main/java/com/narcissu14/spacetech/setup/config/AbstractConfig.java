package com.narcissu14.spacetech.setup.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author Narcissu14
 */
public abstract class AbstractConfig {
    File file;
    YamlConfiguration config;

    public AbstractConfig(File configFile) {
        this.file = configFile;
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        this.config = config;
        initOptions(config);
    }

    public abstract void initOptions(YamlConfiguration config);

    public abstract void reloadConfig();
}
