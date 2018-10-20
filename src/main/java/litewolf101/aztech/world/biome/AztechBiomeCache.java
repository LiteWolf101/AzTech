package litewolf101.aztech.world.biome;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;

import java.util.List;

/**
 * Created by LiteWolf101 on 10/20/2018.
 */
public class AztechBiomeCache extends net.minecraft.world.biome.BiomeCache {
    private final Long2ObjectMap<Block> cacheMap = new Long2ObjectOpenHashMap<Block>(4096);
    private final List<Block> cache = Lists.<BiomeCache.Block>newArrayList();
    private long lastCleanupTime;
    public AztechBiomeCache(BiomeProvider provider) {
        super(provider);
    }

    @Override
    public Block getEntry(int x, int z) {
        x = x >> 4;
        z = z >> 4;
        long i = (long)x & 4294967295L | ((long)z & 4294967295L) << 32;
        BiomeCache.Block biomecache$block = (BiomeCache.Block)this.cacheMap.get(i);

        if (biomecache$block == null)
        {
            biomecache$block = new BiomeCache.Block(x, z);
            this.cacheMap.put(i, biomecache$block);
            this.cache.add(biomecache$block);
        }

        biomecache$block.lastAccessTime = MinecraftServer.getCurrentTimeMillis();
        return biomecache$block;
    }

    @Override
    public Biome getBiome(int x, int z, Biome defaultValue) {
        Biome biome = this.getEntry(x, z).getBiome(x, z);
        return biome == null ? defaultValue : biome;
    }

    @Override
    public void cleanupCache() {
        long i = MinecraftServer.getCurrentTimeMillis();
        long j = i - this.lastCleanupTime;

        if (j > 7500L || j < 0L)
        {
            this.lastCleanupTime = i;

            for (int k = 0; k < this.cache.size(); ++k)
            {
                BiomeCache.Block biomecache$block = this.cache.get(k);
                long l = i - biomecache$block.lastAccessTime;

                if (l > 30000L || l < 0L)
                {
                    this.cache.remove(k--);
                    long i1 = (long)biomecache$block.x & 4294967295L | ((long)biomecache$block.z & 4294967295L) << 32;
                    this.cacheMap.remove(i1);
                }
            }
        }
    }

    @Override
    public Biome[] getCachedBiomes(int x, int z) {
        return this.getEntry(x, z).biomes;
    }
}
