package com.narcissu14.spacetech;

import com.narcissu14.spacetech.generator.SpaceGenerator;
import com.narcissu14.spacetech.listener.ItemListener;
import com.narcissu14.spacetech.listener.SpaceWorldListener;
import com.narcissu14.spacetech.setup.STItemSetup;
import com.narcissu14.spacetech.setup.config.STConfig;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author Narcissu14
 */
public final class SpaceTech extends JavaPlugin implements SlimefunAddon {
    public static boolean IS_VERSION_13;
    @Getter
    private static SpaceTech instance;

    static {
        IS_VERSION_13 = false;
        try {
            Integer verNum = Integer.valueOf(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);
            IS_VERSION_13 = verNum == 13;
        } catch (NumberFormatException ignored) {

        }
    }

    private STConfig config;

    @Override

    public void onEnable() {
        //配置文件
        getDataFolder().mkdirs();
        File configFile = new File(getDataFolder() + File.separator + "config.yml");
        createDefaultConfiguration(configFile, "config.yml");
        config = new STConfig(configFile);

        // 注册物品
        STItemSetup.setupItems();

        //注册太空世界监听器
        // mv create space end -g SpaceTech -t Flat
        new SpaceWorldListener(this);
        //注册物品监听
        new ItemListener(this);
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public @NotNull ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        return new SpaceGenerator();
    }

    /**
     * 创建默认配置文件
     */
    private void createDefaultConfiguration(@NotNull File actual, String defaultName) {
        File parent = actual.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        if (actual.exists()) {
            return;
        }
        InputStream input = null;
        try {
            JarFile file = new JarFile(getFile());
            ZipEntry copy = file.getEntry("resources/" + defaultName);
            if (copy == null) {
                file.close();
                throw new FileNotFoundException();
            }
            input = file.getInputStream(copy);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(actual);
                byte[] buf = new byte[' '];
                int length = 0;
                while ((length = input.read(buf)) > 0) {
                    output.write(buf, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ignored) {
                }
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ignored) {
                }
            }
            file.close();
        } catch (IOException ignored) {
        }
    }

    public STConfig getConfigData() {
        return config;
    }

    @NotNull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return null;
    }
}
