package litewolf101.aztech.world.worldgen.feature;

import litewolf101.aztech.init.BlocksInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by LiteWolf101 on 10/24/2018.
 */
public class WorldGenDryMudReplaceable extends WorldGenerator{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8),0, rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && Blocks.CACTUS.canBlockStay(worldIn, blockpos) && worldIn.getBlockState(position.down()).getBlock() != Blocks.CACTUS)
            {
                worldIn.setBlockState(blockpos.down(), BlocksInit.ANCIENT_DRY_MUD.getDefaultState(), 2);
            }
        }

        return true;
    }


}
