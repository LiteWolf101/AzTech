package litewolf101.aztech.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by LiteWolf101 on 10/20/2018.
 */
public class AztechBiomeProvider extends BiomeProvider {

	private static final float rainfall = 0F;
	private static final List<Biome> allowedBiomes = Arrays.asList(AztechBiomes.biomeAncientForest);

	private final List<Biome> biomesToSpawnIn;
	private final BiomeCache biomeCache;
	private final GenLayer biomeGenLayer;

	public AztechBiomeProvider(World world) {
		biomesToSpawnIn = new ArrayList<Biome>(allowedBiomes);
		biomeCache = new BiomeCache(this);
		biomeGenLayer = GenLayerAztech.initializeAllBiomeGenerators(world.getSeed(), world.getWorldInfo().getTerrainType())[1];

	}

	@Override
	public List<Biome> getBiomesToSpawnIn() {
		return biomesToSpawnIn;
	}

	@Override
	public Biome[] getBiomesForGeneration(Biome biomesForGeneration[], int x, int z, int sizeX, int sizeZ) {
		IntCache.resetIntCache();

		if(biomesForGeneration == null || biomesForGeneration.length < sizeX * sizeZ) {
			biomesForGeneration = new Biome[sizeX * sizeZ];
		}

		int[] biomeArray = biomeGenLayer.getInts(x, z, sizeX, sizeZ);

		for(int index = 0; index < sizeX * sizeZ; ++index) {
			biomesForGeneration[index] = Biome.getBiome(biomeArray[index], Biomes.DEFAULT);
		}

		return biomesForGeneration;
	}

	public Biome[] getBiomes(@Nullable Biome[] oldBiomeList, int x, int z, int width, int depth) {
		return this.getBiomes(oldBiomeList, x, z, width, depth, true);
	}

	@Override
	public Biome[] getBiomes(@Nullable Biome[] biomesForGeneration, int x, int z, int sizeX, int sizeZ, boolean useCache) {
		IntCache.resetIntCache();

		if(biomesForGeneration == null || biomesForGeneration.length < sizeX * sizeZ) {
			biomesForGeneration = new Biome[sizeX * sizeZ];
		}

		if(useCache && sizeX == 16 && sizeZ == 16 && (x & 15) == 0 && (z & 15) == 0) {
			Biome[] cachedBiomes = biomeCache.getCachedBiomes(x, z);
			System.arraycopy(cachedBiomes, 0, biomesForGeneration, 0, sizeX * sizeZ);
			return biomesForGeneration;
		}
		else {
			int[] generatedBiomes = biomeGenLayer.getInts(x, z, sizeX, sizeZ);
			for(int index = 0; index < sizeX * sizeZ; ++index) {
				biomesForGeneration[index] = Biome.getBiome(generatedBiomes[index], Biomes.DEFAULT);
			}

			return biomesForGeneration;
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean areBiomesViable(int x, int z, int checkRadius, List viableBiomes) {
		IntCache.resetIntCache();
		int minX = x - checkRadius >> 2;
		int minZ = z - checkRadius >> 2;
		int maxX = x + checkRadius >> 2;
		int maxZ = z + checkRadius >> 2;
		int sizeX = maxX - minX + 1;
		int sizeZ = maxZ - minZ + 1;
		int[] biomeArray = biomeGenLayer.getInts(minX, minZ, sizeX, sizeZ);

		for(int index = 0; index < sizeX * sizeZ; ++index) {
			if(!viableBiomes.contains(Biome.getBiome(biomeArray[index], Biomes.DEFAULT))) {
				return false;
			}
		}

		return true;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public BlockPos findBiomePosition(int x, int z, int checkRadius, List viableBiomes, Random rand) {
		IntCache.resetIntCache();
		int minX = x - checkRadius >> 2;
		int minZ = z - checkRadius >> 2;
		int maxX = x + checkRadius >> 2;
		int maxZ = z + checkRadius >> 2;
		int sizeX = maxX - minX + 1;
		int sizeZ = maxZ - minZ + 1;
		int[] biomeArray = biomeGenLayer.getInts(minX, minZ, sizeX, sizeZ);
		BlockPos pos = null;
		int attempts = 0;

		for(int index = 0; index < sizeX * sizeZ; ++index) {
			int finalX = minX + index % sizeX << 2;
			int finalZ = minZ + index / sizeX << 2;

			if(viableBiomes.contains(Biome.getBiome(biomeArray[index], Biomes.DEFAULT)) && (pos == null || rand.nextInt(attempts + 1) == 0)) {
				pos = new BlockPos(finalX, 0, finalZ);
				++attempts;
			}
		}

		return pos;
	}

	@Override
	public void cleanupCache() {
		biomeCache.cleanupCache();
	}

	@Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.getNewBiomeGens();
	}

}
