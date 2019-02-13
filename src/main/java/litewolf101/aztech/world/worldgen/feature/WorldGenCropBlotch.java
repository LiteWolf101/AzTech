package litewolf101.aztech.world.worldgen.feature;

import litewolf101.aztech.init.BlocksInit;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static litewolf101.aztech.objects.blocks.AncientFarmland.MOISTURE;
import static litewolf101.aztech.objects.blocks.BlockSorghum.AGE;

/**
 * Created by LiteWolf101 on Feb
 * /13/2019
 */
public class WorldGenCropBlotch extends WorldGenerator {
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (worldIn.getBlockState(position).getBlock() != BlocksInit.ANCIENT_GRASS) {
            return false;
        }
        worldIn.setBlockState(position, Blocks.WATER.getDefaultState());
        for (int x = 0; x < 4; x++) {
            for (int z = 0; z < 4; z++) {

                    if (rand.nextInt(2) == 0) {
                        BlockPos pos = new BlockPos(position.getX() + x, position.getY(), position.getZ() + z);
                        if (worldIn.getBlockState(pos) == BlocksInit.ANCIENT_GRASS.getDefaultState()) {
                            worldIn.setBlockState(pos, BlocksInit.ANCIENT_FARMLAND.getDefaultState().withProperty(MOISTURE, 7));
                            worldIn.setBlockState(pos.up(), BlocksInit.SORGHUM.getDefaultState().withProperty(AGE, 7));
                        }
                    }
            }
        }
        return true;
    }
}
