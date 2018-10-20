package litewolf101.aztech.world.biome;

import litewolf101.aztech.utils.AztechWorldUtils;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

/**
 * Created by LiteWolf101 on 10/20/2018.
 */
public class GenLayerAztechBiomes extends GenLayer {
    protected Biome commonBiomes[] = (new Biome[]{
            AztechWorldUtils.biomeAncientForest
    });

    public GenLayerAztechBiomes(long l) {
        super(l);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int dest[] = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                initChunkSeed(dx + x, dz + z);
                dest[dx + dz * width] = Biome.getIdForBiome(commonBiomes[nextInt(commonBiomes.length)]);
            }

        }
        return dest;
    }
}
