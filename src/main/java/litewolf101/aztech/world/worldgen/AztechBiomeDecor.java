package litewolf101.aztech.world.worldgen;

import litewolf101.aztech.world.worldgen.trees.WorldGenAztechOakTree;
import litewolf101.aztech.world.worldgen.trees.WorldGenAztechOakTree2;
import litewolf101.aztech.world.worldgen.trees.WorldGenSmolTree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

import java.util.Random;

/**
 * Created by LiteWolf101 on 10/23/2018.
 */
public class AztechBiomeDecor extends BiomeDecorator {

	public int aztechOakTreesPerChunk = 5;
	public int aztechTallOakTreesPerChunk = 5;
	public int smolTreesPerChunk = 3;
	private WorldGenAztechOakTree genAztechOakTree = new WorldGenAztechOakTree(true);
	private WorldGenAztechOakTree2 genAztechOakTree2 = new WorldGenAztechOakTree2(true);
	private WorldGenSmolTree genSmolTree = new WorldGenSmolTree(true);

	@Override
	protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
		for(int i = 0; i < aztechOakTreesPerChunk; i++) {
			int rx = chunkPos.getX() + random.nextInt(16);
			int rz = chunkPos.getZ() + random.nextInt(16);
			genAztechOakTree.generate(worldIn, random, worldIn.getHeight(new BlockPos(rx, 0, rz)));
		}
		for(int i = 0; i < aztechTallOakTreesPerChunk; i++) {
			int rx = chunkPos.getX() + random.nextInt(16);
			int rz = chunkPos.getZ() + random.nextInt(16);
			genAztechOakTree2.generate(worldIn, random, worldIn.getHeight(new BlockPos(rx, 0, rz)));
		}

		for(int i = 0; i < aztechTallOakTreesPerChunk; i++) {
			int rx = chunkPos.getX() + random.nextInt(16);
			int rz = chunkPos.getZ() + random.nextInt(16);
			genSmolTree.generate(worldIn, random, worldIn.getHeight(new BlockPos(rx, 0, rz)));
		}
	}

	public void setAztechOakTreesPerChunk(int aztechOakTreesPerChunk) {
		this.aztechOakTreesPerChunk = aztechOakTreesPerChunk;
	}

	public void setTallAztechOakTreesPerChunk(int aztechTallOakTreesPerChunk) {
		this.aztechTallOakTreesPerChunk = aztechTallOakTreesPerChunk;
	}

	public void setSmolTreesPerChunk(int smolTreesPerChunk) {
		this.smolTreesPerChunk = smolTreesPerChunk;
	}

}
