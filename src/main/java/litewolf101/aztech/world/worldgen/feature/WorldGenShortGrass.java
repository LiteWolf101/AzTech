package litewolf101.aztech.world.worldgen.feature;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.blocks.ShortGrass;
import litewolf101.aztech.utils.handlers.EnumAzTechPlantType;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static litewolf101.aztech.objects.blocks.ShortGrass.PLANT_TYPE;

public class WorldGenShortGrass {
    private final IBlockState tallGrassState;

    public WorldGenShortGrass(EnumAzTechPlantType.EnumType shortGrassType)
    {
        this.tallGrassState = BlocksInit.SHORT_GRASS.getDefaultState().withProperty(PLANT_TYPE, shortGrassType);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && ((BlockBush) tallGrassState.getBlock()).canBlockStay(worldIn, blockpos, tallGrassState))
            {
                worldIn.setBlockState(blockpos, this.tallGrassState, 2);
            }
        }

        return true;
    }
}
