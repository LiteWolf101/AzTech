package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import static litewolf101.aztech.objects.blocks.AncientLaser.FACING;
import static litewolf101.aztech.objects.blocks.LaserBlock.AXIS;
import static litewolf101.aztech.objects.blocks.ObjectorRune.ACTIVATED;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * Created by LiteWolf101 on 10/5/2018.
 */
public class TEObjectorRune extends TileEntity implements ITickable {

	@Override
	public void update() {
		EnumFacing direction = world.getBlockState(pos).getValue(FACING); //default
		Boolean activated = false; //default
		IBlockState laserX = BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X);
		IBlockState laserY = BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y);
		IBlockState laserZ = BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z);

		if(direction == EnumFacing.NORTH | direction == EnumFacing.SOUTH && world.getBlockState(pos.offset(direction)) == laserX) {
			activated = true;
		}
		else if(direction == EnumFacing.EAST | direction == EnumFacing.WEST && world.getBlockState(pos.offset(direction)) == laserZ) {
			activated = true;
		}
		else if(direction == EnumFacing.UP | direction == EnumFacing.DOWN && world.getBlockState(pos.offset(direction)) == laserY) {
			activated = true;
		}

		world.setBlockState(pos, BlocksInit.OBJECTOR_RUNE.getDefaultState().withProperty(ACTIVATED, activated).withProperty(FACING, direction));
	}

}
