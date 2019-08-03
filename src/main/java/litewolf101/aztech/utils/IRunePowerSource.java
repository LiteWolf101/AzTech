package litewolf101.aztech.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRunePowerSource {

	IBlockState isRunePowerSourceAt(World world, IBlockState state, EnumFacing facing, BlockPos pos);

}
