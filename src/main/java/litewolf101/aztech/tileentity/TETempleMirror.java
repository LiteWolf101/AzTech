package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import static litewolf101.aztech.objects.blocks.AncientLaser.ACTIVATED;
import static litewolf101.aztech.objects.blocks.AncientLaser.FACING;
import static litewolf101.aztech.objects.blocks.LaserBlock.AXIS;
import static litewolf101.aztech.objects.blocks.TempleMirror.FLIPPED;
import static litewolf101.aztech.objects.blocks.TempleMirror.INVERT_IO;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * Created by LiteWolf101 on 11/4/2018.
 */
public class TETempleMirror extends TileEntity implements ITickable {

	@Override
	public void update() {
		//X
		if(world.getBlockState(pos).getValue(AXIS) == EnumFacing.Axis.X) {
			if(world.getBlockState(pos).getValue(INVERT_IO) == false) {
				if(world.getBlockState(pos).getValue(FLIPPED) == false) {
					if(canFireIntoMe(15, EnumFacing.WEST)) {
						setLaser(15, EnumFacing.UP);
					}
					else {
						replaceLaser(15, EnumFacing.UP);
					}
					if(canFireIntoMe(15, EnumFacing.EAST)) {
						setLaser(15, EnumFacing.DOWN);
					}
					else {
						replaceLaser(15, EnumFacing.DOWN);
					}
				}
				else if(world.getBlockState(pos).getValue(FLIPPED) == true) {
					if(canFireIntoMe(15, EnumFacing.WEST)) {
						setLaser(15, EnumFacing.DOWN);
					}
					else {
						replaceLaser(15, EnumFacing.DOWN);
					}
					if(canFireIntoMe(15, EnumFacing.EAST)) {
						setLaser(15, EnumFacing.UP);
					}
					else {
						replaceLaser(15, EnumFacing.UP);
					}
				}
			}
			else if(world.getBlockState(pos).getValue(INVERT_IO) == true) {
				if(world.getBlockState(pos).getValue(FLIPPED) == false) {
					if(canFireIntoMe(15, EnumFacing.DOWN)) {
						setLaser(15, EnumFacing.EAST);
					}
					else {
						replaceLaser(15, EnumFacing.EAST);
					}
					if(canFireIntoMe(15, EnumFacing.UP)) {
						setLaser(15, EnumFacing.WEST);
					}
					else {
						replaceLaser(15, EnumFacing.WEST);
					}
				}
				else if(world.getBlockState(pos).getValue(FLIPPED) == true) {
					if(canFireIntoMe(15, EnumFacing.DOWN)) {
						setLaser(15, EnumFacing.WEST);
					}
					else {
						replaceLaser(15, EnumFacing.WEST);
					}
					if(canFireIntoMe(15, EnumFacing.UP)) {
						setLaser(15, EnumFacing.EAST);
					}
					else {
						replaceLaser(15, EnumFacing.EAST);
					}
				}
			}
		}
		//Y
		if(world.getBlockState(pos).getValue(AXIS) == EnumFacing.Axis.Y) {
			if(world.getBlockState(pos).getValue(INVERT_IO) == false) {
				if(world.getBlockState(pos).getValue(FLIPPED) == false) {
					if(canFireIntoMe(15, EnumFacing.NORTH)) {
						setLaser(15, EnumFacing.EAST);
					}
					else {
						replaceLaser(15, EnumFacing.EAST);
					}
					if(canFireIntoMe(15, EnumFacing.SOUTH)) {
						setLaser(15, EnumFacing.WEST);
					}
					else {
						replaceLaser(15, EnumFacing.WEST);
					}
				}
				else if(world.getBlockState(pos).getValue(FLIPPED) == true) {
					if(canFireIntoMe(15, EnumFacing.NORTH)) {
						setLaser(15, EnumFacing.WEST);
					}
					else {
						replaceLaser(15, EnumFacing.WEST);
					}
					if(canFireIntoMe(15, EnumFacing.SOUTH)) {
						setLaser(15, EnumFacing.EAST);
					}
					else {
						replaceLaser(15, EnumFacing.EAST);
					}
				}
			}
			else if(world.getBlockState(pos).getValue(INVERT_IO) == true) {
				if(world.getBlockState(pos).getValue(FLIPPED) == false) {
					if(canFireIntoMe(15, EnumFacing.EAST)) {
						setLaser(15, EnumFacing.NORTH);
					}
					else {
						replaceLaser(15, EnumFacing.NORTH);
					}
					if(canFireIntoMe(15, EnumFacing.WEST)) {
						setLaser(15, EnumFacing.SOUTH);
					}
					else {
						replaceLaser(15, EnumFacing.SOUTH);
					}
				}
				else if(world.getBlockState(pos).getValue(FLIPPED) == true) {
					if(canFireIntoMe(15, EnumFacing.EAST)) {
						setLaser(15, EnumFacing.SOUTH);
					}
					else {
						replaceLaser(15, EnumFacing.SOUTH);
					}
					if(canFireIntoMe(15, EnumFacing.WEST)) {
						setLaser(15, EnumFacing.NORTH);
					}
					else {
						replaceLaser(15, EnumFacing.NORTH);
					}
				}
			}
		}
		//Z
		if(world.getBlockState(pos).getValue(AXIS) == EnumFacing.Axis.Z) {
			if(world.getBlockState(pos).getValue(INVERT_IO) == false) {
				if(world.getBlockState(pos).getValue(FLIPPED) == false) {
					if(canFireIntoMe(15, EnumFacing.NORTH)) {
						setLaser(15, EnumFacing.DOWN);
					}
					else {
						replaceLaser(15, EnumFacing.DOWN);
					}
					if(canFireIntoMe(15, EnumFacing.SOUTH)) {
						setLaser(15, EnumFacing.UP);
					}
					else {
						replaceLaser(15, EnumFacing.UP);
					}
				}
				else if(world.getBlockState(pos).getValue(FLIPPED) == true) {
					if(canFireIntoMe(15, EnumFacing.NORTH)) {
						setLaser(15, EnumFacing.UP);
					}
					else {
						replaceLaser(15, EnumFacing.UP);
					}
					if(canFireIntoMe(15, EnumFacing.SOUTH)) {
						setLaser(15, EnumFacing.DOWN);
					}
					else {
						replaceLaser(15, EnumFacing.DOWN);
					}
				}
			}
			else if(world.getBlockState(pos).getValue(INVERT_IO) == true) {
				if(world.getBlockState(pos).getValue(FLIPPED) == false) {
					if(canFireIntoMe(15, EnumFacing.DOWN)) {
						setLaser(15, EnumFacing.NORTH);
					}
					else {
						replaceLaser(15, EnumFacing.NORTH);
					}
					if(canFireIntoMe(15, EnumFacing.UP)) {
						setLaser(15, EnumFacing.SOUTH);
					}
					else {
						replaceLaser(15, EnumFacing.SOUTH);
					}
				}
				else if(world.getBlockState(pos).getValue(FLIPPED) == true) {
					if(canFireIntoMe(15, EnumFacing.DOWN)) {
						setLaser(15, EnumFacing.SOUTH);
					}
					else {
						replaceLaser(15, EnumFacing.SOUTH);
					}
					if(canFireIntoMe(15, EnumFacing.UP)) {
						setLaser(15, EnumFacing.NORTH);
					}
					else {
						replaceLaser(15, EnumFacing.NORTH);
					}
				}
			}
		}

	}

	public boolean canFireIntoMe(int range, EnumFacing direction) {
		boolean check = false;
		if(world.getBlockState(pos).getValue(AXIS) == EnumFacing.Axis.X) {
			if(world.getBlockState(pos).getValue(INVERT_IO) == false) {
				if(isLaserActive(range, direction, direction, EnumFacing.Axis.Z) || isMirrorReflectingInto(range, direction, direction, EnumFacing.Axis.Z)) {
					check = true;
				}
			}
			else if(world.getBlockState(pos).getValue(INVERT_IO) == true) {
				if(isLaserActive(range, direction, direction, EnumFacing.Axis.Y) || isMirrorReflectingInto(range, direction, direction, EnumFacing.Axis.Y)) {
					check = true;
				}
			}
		}
		if(world.getBlockState(pos).getValue(AXIS) == EnumFacing.Axis.Y) {
			if(world.getBlockState(pos).getValue(INVERT_IO) == false) {
				if(isLaserActive(range, direction, direction, EnumFacing.Axis.X) || isMirrorReflectingInto(range, direction, direction, EnumFacing.Axis.X)) {
					check = true;
				}
			}
			else if(world.getBlockState(pos).getValue(INVERT_IO) == true) {
				if(isLaserActive(range, direction, direction, EnumFacing.Axis.Z) || isMirrorReflectingInto(range, direction, direction, EnumFacing.Axis.Z)) {
					check = true;
				}
			}
		}
		if(world.getBlockState(pos).getValue(AXIS) == EnumFacing.Axis.Z) {
			if(world.getBlockState(pos).getValue(INVERT_IO) == false) {
				if(isLaserActive(range, direction, direction, EnumFacing.Axis.X) || isMirrorReflectingInto(range, direction, direction, EnumFacing.Axis.X)) {
					check = true;
				}
			}
			else if(world.getBlockState(pos).getValue(INVERT_IO) == true) {
				if(isLaserActive(range, direction, direction, EnumFacing.Axis.Y) || isMirrorReflectingInto(range, direction, direction, EnumFacing.Axis.Y)) {
					check = true;
				}
			}
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

	public boolean isLaserActive(int range, EnumFacing direction, EnumFacing offsetLaserDirection, EnumFacing.Axis laserBlockAxis) {
		Boolean check = false;
		if(world.getBlockState(pos.offset(offsetLaserDirection)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, laserBlockAxis)) {
			for(int i = 1; i <= range; i++) {
				if(world.getBlockState(pos.offset(direction, i)) == BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(FACING, direction.getOpposite()).withProperty(ACTIVATED, true)) {
					check = true;
					range = 20;
				}
			}
		}
		return check;
	}

	public boolean isMirrorReflectingInto(int range, EnumFacing mirrorOffsetDirection, EnumFacing offsetLaserDirection, EnumFacing.Axis laserBlockAxis) {
		Boolean check = false;
		if(world.getBlockState(pos.offset(offsetLaserDirection)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, laserBlockAxis)) {
			for(int i = 1; i <= range; i++) {
				if(mirrorOffsetDirection == EnumFacing.NORTH || mirrorOffsetDirection == EnumFacing.SOUTH) {
					if(world.getBlockState(pos.offset(mirrorOffsetDirection, i)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(mirrorOffsetDirection, i)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, true).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(mirrorOffsetDirection, i)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, false).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(mirrorOffsetDirection, i)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, true).withProperty(INVERT_IO, true)) {
						check = true;
						range = 20;
					}
				}
				if(mirrorOffsetDirection == EnumFacing.EAST || mirrorOffsetDirection == EnumFacing.WEST) {
					if(world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, true).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, false).withProperty(INVERT_IO, true) || world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, true).withProperty(INVERT_IO, true)) {
						check = true;
						range = 20;
					}
				}
				if(mirrorOffsetDirection == EnumFacing.UP || mirrorOffsetDirection == EnumFacing.DOWN) {
					if(world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, false).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, true).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, false).withProperty(INVERT_IO, false) || world.getBlockState(pos.offset(mirrorOffsetDirection, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, true).withProperty(INVERT_IO, false)) {
						check = true;
						range = 20;
					}
				}
			}
		}
		return check;
	}

	//public boolean activeLaser(int range, EnumFacing direction) {
	//    int check = 0;
	//    for (int i = 1; i <= range; i++) {
	//        if (world.getBlockState(pos.offset(direction, i+1)).getBlock() == BlocksInit.TEMPLE_MIRROR) {
	//            break;
	//        } else if (world.getBlockState(pos.offset(direction, i+1)) == BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(FACING, direction.getOpposite()).withProperty(ACTIVATED, true)){
	//            check++;
	//        }
	//    }
	//    return check > 0;
	//}
}
