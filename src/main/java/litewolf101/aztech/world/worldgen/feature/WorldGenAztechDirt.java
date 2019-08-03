package litewolf101.aztech.world.worldgen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by LiteWolf101 on Feb /06/2019
 */
public class WorldGenAztechDirt extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if(worldIn.getBlockState(position).getMaterial() != Material.SAND) {
			return false;
		}
		else {
			int i = rand.nextInt(5) + 2;
			int j = 1;

			for(int k = position.getX() - i; k <= position.getX() + i; ++k) {
				for(int l = position.getZ() - i; l <= position.getZ() + i; ++l) {
					int i1 = k - position.getX();
					int j1 = l - position.getZ();

					if(i1 * i1 + j1 * j1 <= i * i) {
						for(int k1 = position.getY() - 1; k1 <= position.getY() + 1; ++k1) {
							BlockPos blockpos = new BlockPos(k, k1, l);
							Block block = worldIn.getBlockState(blockpos).getBlock();

							if(block == Blocks.GRAVEL || block == Blocks.CLAY) {
								worldIn.setBlockState(blockpos, Blocks.DIRT.getDefaultState(), 2);
							}
						}
					}
				}
			}
		}
		return true;
	}

}
