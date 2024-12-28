package com.narcissu14.spacetech.generator;

import com.narcissu14.spacetech.generator.populators.PlanetPopulator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Narcissu14
 */
public class SpaceGenerator extends ChunkGenerator {
    public SpaceGenerator() {

    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        world.getBlockAt(0, 59, 0).setType(Material.BEDROCK);

        return new Location(world, 0, 60, 0);
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Collections.singletonList(new PlanetPopulator());
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = Bukkit.createChunkData(world);

        return chunkData;
    }
}
