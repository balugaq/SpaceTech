package com.narcissu14.spacetech;

import com.narcissu14.spacetech.event.SpaceOxygenEvent;
import com.narcissu14.spacetech.generator.SpaceGenerator;
import com.narcissu14.spacetech.listener.ItemListener;
import com.narcissu14.spacetech.listener.SpaceWorldListener;
import com.narcissu14.spacetech.setup.STItemSetup;
import com.narcissu14.spacetech.setup.config.STConfig;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author Narcissu14
 */
public final class SpaceTech extends JavaPlugin implements SlimefunAddon {
    private static final Set<String> preventEndDragon = new HashSet<>();
    private static final Set<String> preventBedrock = new HashSet<>();
    @Getter
    private static SpaceTech instance;

    private STConfig config;

    @Override

    public void onEnable() {
        getLogger().info("Enabling SpaceTech...");
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
        loadSpaceWorld();
        getLogger().info("SpaceTech has been enabled!");
    }

    private void loadSpaceWorld() {
        for (String worldName : STConfig.spaceWorldList) {
            WorldCreator newWorld = new WorldCreator(worldName);
            if (Bukkit.getWorld(worldName) == null) {
                newWorld.environment(World.Environment.THE_END);
                long seed = new Random().nextLong();
                newWorld = newWorld.seed(seed);
                newWorld = newWorld.type(WorldType.NORMAL);
                newWorld = newWorld.generateStructures(false);
                newWorld = newWorld.generator(new SpaceGenerator());
                World world = newWorld.createWorld();
                if (world == null) {
                    getInstance().getLogger().info("Failed to create planet \"" + worldName + "\"! ErrorCode: 5");
                    return;
                }

                if (world.getEnvironment() == World.Environment.THE_END) {
                    // Prevents ender dragon spawn using portal
                    preventEndDragon.add(worldName);
                    Location endPortal = world.getBlockAt(0, 0, 0).getLocation();
                    endPortal.getBlock().setType(Material.END_PORTAL);

                    // remove end portal when the chunk load (make sure bukkit has ignored the world)
                    Bukkit.getScheduler().runTaskTimer(SpaceTech.getInstance(), () -> {
                        if (!preventEndDragon.contains(worldName)) {
                            return;
                        }

                        for (Player player : world.getPlayers()) {
                            if (player.getLocation().distance(endPortal) < 20) {
                                if (world.getBlockAt(0, 0, 0).getType() == Material.END_PORTAL) {
                                    world.getBlockAt(0, 0, 0).setType(Material.AIR);
                                }

                                preventEndDragon.remove(worldName);
                            }
                        }
                    }, 1, 20);

                    // Same, but for bedrock
                    preventBedrock.add(worldName);
                    Location bedrock = world.getBlockAt(0, 60, 0).getLocation();
                    Bukkit.getScheduler().runTaskTimer(SpaceTech.getInstance(), () -> {
                        if (preventBedrock.contains(worldName)) {
                            return;
                        }

                        for (Player player : world.getPlayers()) {
                            if (player.getLocation().distance(bedrock) < 20) {
                                if (world.getBlockAt(0, 60, 0).getType() == Material.BEDROCK) {
                                    world.getBlockAt(0, 60, 0).setType(Material.AIR);
                                }

                                preventBedrock.remove(worldName);
                            }
                        }
                    }, 1, 20);
                }
                getInstance().getLogger().info("Space world \"" + worldName + "\" created with seed " + seed);
                return;
            }
        }
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
