package litewolf101.aztech.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.ArrayList;

/**
 * Created by LiteWolf101 on 10/21/2018.
 */
public class GenLayerBiomes extends GenLayer {

	private final ArrayList<Biome> biomesToGenerate;

	public GenLayerBiomes(long seed, GenLayer parentGenLayer) {
		super(seed);
		biomesToGenerate = AztechBiomes.BIOME_LIST;
		parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ) {
		parent.getInts(x, z, sizeX, sizeZ);
		int[] ints = IntCache.getIntCache(sizeX * sizeZ);

		for(int zz = 0; zz < sizeZ; ++zz) {
			for(int xx = 0; xx < sizeX; ++xx) {
				initChunkSeed(xx + x, zz + z);
				ints[xx + zz * sizeX] = Biome.getIdForBiome(biomesToGenerate.get(nextInt(biomesToGenerate.size()))); //temp hack for now
			}
		}

		return ints;
	}

}
