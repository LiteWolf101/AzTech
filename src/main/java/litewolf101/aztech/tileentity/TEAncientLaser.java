package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import static litewolf101.aztech.objects.blocks.AncientLaser.ACTIVATED;
import static litewolf101.aztech.objects.blocks.AncientLaser.FACING;
import static litewolf101.aztech.objects.blocks.LaserBlock.AXIS;
import static litewolf101.aztech.objects.blocks.TempleMirror.FLIPPED;
import static litewolf101.aztech.objects.blocks.TempleMirror.INVERT_IO;
import litewolf101.aztech.utils.handlers.MiscHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by LiteWolf101 on 10/3/2018.
 */
public class TEAncientLaser extends TileEntity implements ITickable {

	public static void staticReplaceLaser(EnumFacing direction, World world, BlockPos pos) {
		for(int i = 1; i <= 15; i++) {
			if(world.getBlockState(pos.offset(direction, i)).getBlock() == BlocksInit.LASER_BLOCK) {
				world.setBlockToAir(pos.offset(direction, i));
			}
		}
	}

	@Override
	public void update() {

		EnumFacing direction = world.getBlockState(pos).getValue(FACING); //default
		Boolean activated = false; //default
		if(MiscHandler.SOURCES.contains(world.getBlockState(pos.north())) || isActiveObjector(pos.north())) {
			direction = EnumFacing.SOUTH;
			activated = true;
		}
		else if(MiscHandler.SOURCES.contains(world.getBlockState(pos.east())) || isActiveObjector(pos.east())) {
			direction = EnumFacing.WEST;
			activated = true;
		}
		else if(MiscHandler.SOURCES.contains(world.getBlockState(pos.south())) || isActiveObjector(pos.south())) {
			direction = EnumFacing.NORTH;
			activated = true;
		}
		else if(MiscHandler.SOURCES.contains(world.getBlockState(pos.west())) || isActiveObjector(pos.west())) {
			direction = EnumFacing.EAST;
			activated = true;
		}
		else if(MiscHandler.SOURCES.contains(world.getBlockState(pos.up())) || isActiveObjector(pos.up())) {
			direction = EnumFacing.DOWN;
			activated = true;
		}
		else if(MiscHandler.SOURCES.contains(world.getBlockState(pos.down())) || isActiveObjector(pos.down())) {
			direction = EnumFacing.UP;
			activated = true;
		}
		world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, activated).withProperty(FACING, direction));
		if(activated) {
			setLaser(15, direction);
		}
		else if(doReplace(15, direction) && isFightingLaserInactive(15, direction)) {
			replaceLaser(15, direction);
		}
	}

	public boolean isActiveObjector(BlockPos pos) {
		boolean check = false;
		if(world.getBlockState(pos) == BlocksInit.OBJECTOR_RUNE.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVATED, true)) {
			check = true;
		}
		if(world.getBlockState(pos) == BlocksInit.OBJECTOR_RUNE.getDefaultState().withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVATED, true)) {
			check = true;
		}
		if(world.getBlockState(pos) == BlocksInit.OBJECTOR_RUNE.getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVATED, true)) {
			check = true;
		}
		if(world.getBlockState(pos) == BlocksInit.OBJECTOR_RUNE.getDefaultState().withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVATED, true)) {
			check = true;
		}
		if(world.getBlockState(pos) == BlocksInit.OBJECTOR_RUNE.getDefaultState().withProperty(FACING, EnumFacing.UP).withProperty(ACTIVATED, true)) {
			check = true;
		}
		if(world.getBlockState(pos) == BlocksInit.OBJECTOR_RUNE.getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(ACTIVATED, true)) {
			check = true;
		}
		return check;
	}

	public void setLaser(int range, EnumFacing direction) {
		for(int i = 1; i <= range; i++) {
			if(direction == EnumFacing.NORTH | direction == EnumFacing.SOUTH) {
				if(world.isAirBlock(pos.offset(direction, i))) {
					world.setBlockState(pos.offset(direction, i), BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X));
				}
				else if(world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK) {
					i = 20;
				}
			}
			else if(direction == EnumFacing.EAST | direction == EnumFacing.WEST) {
				if(world.isAirBlock(pos.offset(direction, i))) {
					world.setBlockState(pos.offset(direction, i), BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z));
				}
				else if(world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK) {
					i = 20;
				}
			}
			else if(direction == EnumFacing.UP | direction == EnumFacing.DOWN) {
				if(world.isAirBlock(pos.offset(direction, i))) {
					world.setBlockState(pos.offset(direction, i), BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
				}
				else if(world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK) {
					i = 20;
				}
			}
		}
	}

	public boolean doReplace(int range, EnumFacing direction) {
		int check = 0;
		for(int i = 1; i <= range; i++) {
			//X
			if(world.getBlockState(pos).getValue(FACING) == EnumFacing.NORTH || (world.getBlockState(pos).getValue(FACING) == EnumFacing.SOUTH)) {
				if(world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, true).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, false).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, true).withProperty(INVERT_IO, true)) {
					check++;
				}
			}
			//Y

			if(world.getBlockState(pos).getValue(FACING) == EnumFacing.UP || (world.getBlockState(pos).getValue(FACING) == EnumFacing.DOWN)) {
				if(world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, false).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, true).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, false).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, true).withProperty(INVERT_IO, false)) {
					check++;
				}
			}
			//Z
			if(world.getBlockState(pos).getValue(FACING) == EnumFacing.EAST || (world.getBlockState(pos).getValue(FACING) == EnumFacing.WEST)) {
				if(world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, true).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, false).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, true).withProperty(INVERT_IO, true)) {
					check++;
				}
			}
		}
		return check == 0;
	}

	public boolean isFightingLaserInactive(int range, EnumFacing direction) {
		int check = 0;
		for(int i = 1; i <= range; i++) {
			if(!world.getBlockState(pos).getValue(ACTIVATED) && world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(FACING, direction.getOpposite()).withProperty(ACTIVATED, true)) {
				check++;
			}
		}
		return check == 0;
	}

	public void replaceLaser(int range, EnumFacing direction) {
		for(int i = 1; i <= range; i++) {
			if(direction == EnumFacing.NORTH | direction == EnumFacing.SOUTH) {
				if(world.getBlockState(pos.offset(direction, i)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X)) {
					world.setBlockToAir(pos.offset(direction, i));
				}
				else if(world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK) {
					i = 20;
				}
			}
			else if(direction == EnumFacing.EAST | direction == EnumFacing.WEST) {
				if(world.getBlockState(pos.offset(direction, i)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z)) {
					world.setBlockToAir(pos.offset(direction, i));
				}
				else if(world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK) {
					i = 20;
				}
			}
			else if(direction == EnumFacing.UP | direction == EnumFacing.DOWN) {
				if(world.getBlockState(pos.offset(direction, i)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y)) {
					world.setBlockToAir(pos.offset(direction, i));
				}
				else if(world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK) {
					i = 20;
				}
			}
		}
	}

}
