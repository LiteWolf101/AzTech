package litewolf101.aztech.world.worldgen.feature;

import litewolf101.aztech.init.BlocksInit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by LiteWolf101 on Mar
 * /06/2019
 */
public class WorldGenMelonsInAzTech extends WorldGenerator {
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (Blocks.MELON_BLOCK.canPlaceBlockAt(worldIn, blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == BlocksInit.ANCIENT_GRASS)
            {
                worldIn.setBlockState(blockpos, Blocks.MELON_BLOCK.getDefaultState(), 2);
            }
        }

        return true;
    }
}
