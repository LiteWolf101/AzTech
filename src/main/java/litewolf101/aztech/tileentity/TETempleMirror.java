package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.blocks.TempleMirror;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import org.lwjgl.Sys;

import static litewolf101.aztech.objects.blocks.AncientLaser.ACTIVATED;
import static litewolf101.aztech.objects.blocks.AncientLaser.FACING;
import static litewolf101.aztech.objects.blocks.LaserBlock.AXIS;
import static litewolf101.aztech.objects.blocks.TempleMirror.FLIPPED;

/**
 * Created by LiteWolf101 on 11/4/2018.
 */
public class TETempleMirror extends TileEntity implements ITickable{
    @Override
    public void update() {
        //Y
        if (world.getBlockState(pos) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false)) {
            if (activeLaser(15, EnumFacing.NORTH)) {
                setLaser(15, EnumFacing.EAST);
            } else if (isMirrorAtPos(15, EnumFacing.NORTH) && world.getBlockState(pos.north()) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X)) {
                setLaser(15, EnumFacing.EAST);
            } else if (world.getBlockState(pos.north()) != BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X) && !activeLaser(15, EnumFacing.NORTH)) {
                System.out.println("hi");
                //replaceLaser(15, EnumFacing.EAST);
            }
            /**if (activeLaser(15, EnumFacing.NORTH)){
                setLaser(15, EnumFacing.EAST);
            }
            if (activeLaser(15, EnumFacing.EAST)){
                setLaser(15, EnumFacing.NORTH);
            }
            if (!activeLaser(15, EnumFacing.NORTH) && !activeLaser(15, EnumFacing.EAST)  && !isMirrorAtPos(15, EnumFacing.EAST) && !isMirrorAtPos(15, EnumFacing.NORTH)) {
                replaceLaser(15, EnumFacing.NORTH);
                replaceLaser(15, EnumFacing.EAST);
            }
            if (isMirrorAtPos(15, EnumFacing.NORTH)) {
                if (canMirroredLaserFireIntoMe(15, EnumFacing.NORTH)) {
                    if (world.getBlockState(pos.offset(EnumFacing.NORTH)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X)) {
                        setLaser(15, EnumFacing.EAST);
                    }
                }
            }*/
            /**if (isMirrorAtPos(15, EnumFacing.WEST)) {
                if (canMirroredLaserFireIntoMe(15, EnumFacing.WEST)) {
                    if (world.getBlockState(pos.offset(EnumFacing.WEST)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z)) {
                        setLaser(15, EnumFacing.SOUTH);
                    } else {
                        replaceLaser(15, EnumFacing.SOUTH);
                    }
                }
            }
            if (isMirrorAtPos(15, EnumFacing.EAST)) {
                if (canMirroredLaserFireIntoMe(15, EnumFacing.EAST)) {
                    if (world.getBlockState(pos.offset(EnumFacing.EAST)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z)) {
                        setLaser(15, EnumFacing.NORTH);
                    } else {
                        replaceLaser(15, EnumFacing.NORTH);
                    }
                }
            }*/

            /**if (activeLaser(15, EnumFacing.SOUTH)){
                setLaser(15, EnumFacing.WEST);
            }
            if (activeLaser(15, EnumFacing.WEST)){
                setLaser(15, EnumFacing.SOUTH);
            }
            if (!activeLaser(15, EnumFacing.SOUTH) && !activeLaser(15, EnumFacing.WEST)  && !isMirrorAtPos(15, EnumFacing.WEST) && !isMirrorAtPos(15, EnumFacing.SOUTH)) {
                replaceLaser(15, EnumFacing.SOUTH);
                replaceLaser(15, EnumFacing.WEST);
            }
        }
        if (world.getBlockState(pos) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, true)) {
            if (activeLaser(15, EnumFacing.NORTH)){
                setLaser(15, EnumFacing.WEST);
            }
            if (activeLaser(15, EnumFacing.WEST)){
                setLaser(15, EnumFacing.NORTH);
            }
            if (!activeLaser(15, EnumFacing.NORTH) && !activeLaser(15, EnumFacing.WEST)) {
                replaceLaser(15, EnumFacing.NORTH);
                replaceLaser(15, EnumFacing.WEST);
            }
            if (activeLaser(15, EnumFacing.SOUTH)){
                setLaser(15, EnumFacing.EAST);
            }
            if (activeLaser(15, EnumFacing.EAST)){
                setLaser(15, EnumFacing.SOUTH);
            }
            if (!activeLaser(15, EnumFacing.SOUTH) && !activeLaser(15, EnumFacing.EAST)) {
                replaceLaser(15, EnumFacing.SOUTH);
                replaceLaser(15, EnumFacing.EAST);
            }
        }
        //Z
        if (world.getBlockState(pos) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, false)) {
            if (activeLaser(15, EnumFacing.NORTH)){
                setLaser(15, EnumFacing.DOWN);
            }
            if (activeLaser(15, EnumFacing.DOWN)){
                setLaser(15, EnumFacing.NORTH);
            }
            if (!activeLaser(15, EnumFacing.NORTH) && !activeLaser(15, EnumFacing.DOWN)) {
                replaceLaser(15, EnumFacing.NORTH);
                replaceLaser(15, EnumFacing.DOWN);
            }
            if (activeLaser(15, EnumFacing.SOUTH)){
                setLaser(15, EnumFacing.UP);
            }
            if (activeLaser(15, EnumFacing.UP)){
                setLaser(15, EnumFacing.SOUTH);
            }
            if (!activeLaser(15, EnumFacing.SOUTH) && !activeLaser(15, EnumFacing.UP)) {
                replaceLaser(15, EnumFacing.SOUTH);
                replaceLaser(15, EnumFacing.UP);
            }
        }
        if (world.getBlockState(pos) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, true)) {
            if (activeLaser(15, EnumFacing.NORTH)){
                setLaser(15, EnumFacing.UP);
            }
            if (activeLaser(15, EnumFacing.UP)){
                setLaser(15, EnumFacing.NORTH);
            }
            if (!activeLaser(15, EnumFacing.NORTH) && !activeLaser(15, EnumFacing.UP)) {
                replaceLaser(15, EnumFacing.NORTH);
                replaceLaser(15, EnumFacing.UP);
            }
            if (activeLaser(15, EnumFacing.SOUTH)){
                setLaser(15, EnumFacing.DOWN);
            }
            if (activeLaser(15, EnumFacing.DOWN)){
                setLaser(15, EnumFacing.SOUTH);
            }
            if (!activeLaser(15, EnumFacing.SOUTH) && !activeLaser(15, EnumFacing.DOWN)) {
                replaceLaser(15, EnumFacing.SOUTH);
                replaceLaser(15, EnumFacing.DOWN);
            }
        }
        //X
        if (world.getBlockState(pos) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, false)) {
            if (activeLaser(15, EnumFacing.EAST)){
                setLaser(15, EnumFacing.DOWN);
            }
            if (activeLaser(15, EnumFacing.DOWN)){
                setLaser(15, EnumFacing.EAST);
            }
            if (!activeLaser(15, EnumFacing.EAST) && !activeLaser(15, EnumFacing.DOWN)) {
                replaceLaser(15, EnumFacing.EAST);
                replaceLaser(15, EnumFacing.DOWN);
            }
            if (activeLaser(15, EnumFacing.WEST)){
                setLaser(15, EnumFacing.UP);
            }
            if (activeLaser(15, EnumFacing.UP)){
                setLaser(15, EnumFacing.WEST);
            }
            if (!activeLaser(15, EnumFacing.WEST) && !activeLaser(15, EnumFacing.UP)) {
                replaceLaser(15, EnumFacing.WEST);
                replaceLaser(15, EnumFacing.UP);
            }
        }
        if (world.getBlockState(pos) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, true)) {
            if (activeLaser(15, EnumFacing.WEST)){
                setLaser(15, EnumFacing.DOWN);
            }
            if (activeLaser(15, EnumFacing.DOWN)){
                setLaser(15, EnumFacing.WEST);
            }
            if (!activeLaser(15, EnumFacing.WEST) && !activeLaser(15, EnumFacing.DOWN)) {
                replaceLaser(15, EnumFacing.WEST);
                replaceLaser(15, EnumFacing.DOWN);
            }
            if (activeLaser(15, EnumFacing.EAST)){
                setLaser(15, EnumFacing.UP);
            }
            if (activeLaser(15, EnumFacing.UP)){
                setLaser(15, EnumFacing.EAST);
            }
            if (!activeLaser(15, EnumFacing.EAST) && !activeLaser(15, EnumFacing.UP)) {
                replaceLaser(15, EnumFacing.EAST);
                replaceLaser(15, EnumFacing.UP);
            }*/
        }
    }

    public void setLaser(int range, EnumFacing direction){
        for (int i = 1; i <= range; i++) {
            if (direction == EnumFacing.NORTH | direction == EnumFacing.SOUTH) {
                if (world.isAirBlock(pos.offset(direction, i))){
                    world.setBlockState(pos.offset(direction, i), BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X));
                } else if (world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK){
                    i = 20;
                }
            } else if (direction == EnumFacing.EAST | direction == EnumFacing.WEST) {
                if (world.isAirBlock(pos.offset(direction, i))){
                    world.setBlockState(pos.offset(direction, i), BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z));
                } else if (world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK){
                    i = 20;
                }
            } else if (direction == EnumFacing.UP | direction == EnumFacing.DOWN) {
                if (world.isAirBlock(pos.offset(direction, i))){
                    world.setBlockState(pos.offset(direction, i), BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
                } else if (world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK){
                    i = 20;
                }
            }
        }
    }

    public void replaceLaser(int range, EnumFacing direction){
        for (int i = 1; i <= range; i++) {
            if (direction == EnumFacing.NORTH | direction == EnumFacing.SOUTH) {
                if (world.getBlockState(pos.offset(direction, i)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X)){
                    world.setBlockToAir(pos.offset(direction, i));
                } else if (world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK){
                    i = 20;
                }
            } else if (direction == EnumFacing.EAST | direction == EnumFacing.WEST) {
                if (world.getBlockState(pos.offset(direction, i)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z)){
                    world.setBlockToAir(pos.offset(direction, i));
                } else if (world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK){
                    i = 20;
                }
            } else if (direction == EnumFacing.UP | direction == EnumFacing.DOWN) {
                if (world.getBlockState(pos.offset(direction, i)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y)){
                    world.setBlockToAir(pos.offset(direction, i));
                } else if (world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK){
                    i = 20;
                }
            }
        }
    }

    public boolean activeLaser(int range, EnumFacing direction) {
        int check = 0;
        for (int i = 1; i <= range; i++) {
            if (world.getBlockState(pos.offset(direction, i+1)).getBlock() == BlocksInit.TEMPLE_MIRROR) {
                break;
            } else if (world.getBlockState(pos.offset(direction, i+1)) == BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(FACING, direction.getOpposite()).withProperty(ACTIVATED, true)){
                check++;
            }
        }
        return check > 0;
    }

    public boolean isMirrorAtPos(int range, EnumFacing direction) {
        int check = 0;
        for (int i = 1; i <= range; i++) {
            if (world.getBlockState(pos.offset(direction, i+1)).getBlock() == BlocksInit.TEMPLE_MIRROR){
                check++;
            }
        }
        return check > 0;
    }

    public boolean canMirroredLaserFireIntoMe(int range, EnumFacing direction) {
        int check = 0;
        for (int i = 1; i <= range; i++) {
            //X
            if (direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH) {
                if (world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, true)) {
                    check++;
                }
            }
            //Y
            if (direction == EnumFacing.UP || direction == EnumFacing.DOWN) {
                if (world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, true)) {
                    check++;
                }
            }
            //Z
            if (direction == EnumFacing.EAST || direction == EnumFacing.WEST) {
                if (world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, true)) {
                    check++;
                }
            }
        }
        return check == 0;
    }
    public boolean fromMirrorFiring(int range, EnumFacing direction) {
        int check = 0;
        for (int i = 1; i <= range; i++) {

            if (world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR) {
                check++;
            }

            //Y
            //if (direction == EnumFacing.UP || direction == EnumFacing.DOWN) {
            //    if (world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, true)) {
            //        check++;
            //    }
            //}
            //Z
            //if (direction == EnumFacing.EAST || direction == EnumFacing.WEST) {
            //    if (world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, false) || world.getBlockState(pos.offset(direction, i + 1)) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(TempleMirror.AXIS, EnumFacing.Axis.Z).withProperty(FLIPPED, true)) {
            //        check++;
            //    }
            //}
        }
        return check > 0;
    }
}
