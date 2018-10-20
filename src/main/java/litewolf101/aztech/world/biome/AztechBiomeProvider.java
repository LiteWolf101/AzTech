package litewolf101.aztech.world.biome;

import litewolf101.aztech.utils.AztechWorldUtils;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;


/**
 * Created by LiteWolf101 on 10/20/2018.
 */
public class AztechBiomeProvider extends BiomeProvider {

    public AztechBiomeProvider(World world) {
        super(world.getWorldInfo());
        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(AztechWorldUtils.biomeAncientForest);
        makeLayers(world.getSeed());

    }

    private void makeLayers(long seed) {
        GenLayer biomes = new GenLayerAztechBiomes(1L);

        biomes = new GenLayerZoom(1000, biomes);

        GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

        biomes.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);

        GenLayer genBiomes = biomes;
        GenLayer biomeIndexLayer = genlayervoronoizoom;

    }

}
