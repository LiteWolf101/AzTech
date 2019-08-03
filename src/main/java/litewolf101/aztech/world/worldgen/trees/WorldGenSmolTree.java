package litewolf101.aztech.world.worldgen.trees;

import litewolf101.aztech.config.AzTechConfig;
import net.minecraft.block.BlockLog;
import static net.minecraft.block.BlockLog.LOG_AXIS;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

/**
 * Created by LiteWolf101 on Jan /17/2019
 */
public class WorldGenSmolTree extends WorldGenAbstractTree {

	protected IBlockState log = Blocks.LOG.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE).withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
	protected IBlockState leaves = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);

	public WorldGenSmolTree(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		Material materialBelow = worldIn.getBlockState(position.down()).getMaterial();
		if(position.getY() <= 13 && position.getY() + 4 >= 128 || materialBelow != Material.GRASS && materialBelow != Material.GROUND || worldIn.provider.getDimension() != AzTechConfig.dimension_ID) {
			return false;
		}

		int height = rand.nextInt(2) + 1;
		for(int y = 0; y <= height; y++) {
			setBlockAndNotifyAdequately(worldIn, position.up(y), log);
		}
		setBlockAndNotifyAdequately(worldIn, position.add(1, height, 0), leaves);
		setBlockAndNotifyAdequately(worldIn, position.add(-1, height, 0), leaves);
		setBlockAndNotifyAdequately(worldIn, position.add(0, height, 1), leaves);
		setBlockAndNotifyAdequately(worldIn, position.add(0, height, -1), leaves);
		setBlockAndNotifyAdequately(worldIn, position.up(height + 1), leaves);

		return true;
	}

}
