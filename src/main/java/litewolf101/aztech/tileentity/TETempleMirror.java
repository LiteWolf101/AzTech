package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

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
        if (world.getBlockState(pos) == BlocksInit.TEMPLE_MIRROR.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(FLIPPED, false)) {
            if (world.getBlockState(pos.offset(EnumFacing.NORTH)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X)) {
                setLaser(15, EnumFacing.EAST);
            } else if (world.isAirBlock(pos.offset(EnumFacing.NORTH))) {
                if (doReplace(15, EnumFacing.EAST)) {
                    replaceLaser(15, EnumFacing.EAST);
                }
            } else if (!doReplace(15, EnumFacing.NORTH) && !doReplace(15, EnumFacing.EAST)) {
                replaceLaser(15, EnumFacing.NORTH);
                replaceLaser(15, EnumFacing.EAST);
            }
            if (world.getBlockState(pos.offset(EnumFacing.EAST)) == BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z)) {
                setLaser(15, EnumFacing.NORTH);
            } else if (world.isAirBlock(pos.offset(EnumFacing.EAST))) {
                if (doReplace(15, EnumFacing.NORTH)) {
                    replaceLaser(15, EnumFacing.NORTH);
                }
            }
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

    public boolean doReplace(int range, EnumFacing direction) {
        int check = 0;
        for (int i = 1; i <= range; i++) {
            if (world.getBlockState(pos.offset(direction, i+1)) == BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(FACING, direction.getOpposite()).withProperty(ACTIVATED, true)){
                check++;
            }
        }
        return check == 0;
    }

    public boolean doubleReplace(int range, EnumFacing direction1, EnumFacing direction2) {
        int check = 0;
        for (int i = 1; i <= range; i++) {
            if (world.getBlockState(pos.offset(direction1, i+1)) == BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(FACING, direction1.getOpposite()).withProperty(ACTIVATED, false)){
                check++;
            }
            if (world.getBlockState(pos.offset(direction2, i+1)) == BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(FACING, direction2.getOpposite()).withProperty(ACTIVATED, false)) {
                check++;
            }
        }
        return check == 2;
    }
}
