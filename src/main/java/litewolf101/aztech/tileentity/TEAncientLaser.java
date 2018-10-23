package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumRuneState;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import static litewolf101.aztech.objects.blocks.AncientLaser.ACTIVATED;
import static litewolf101.aztech.objects.blocks.AncientLaser.FACING;
import static litewolf101.aztech.objects.blocks.BlockSlaughtiveRune.STAGE;
import static litewolf101.aztech.objects.blocks.LaserBlock.AXIS;
import static litewolf101.aztech.objects.blocks.R2RTranslator.ACTIVATION;

/**
 * Created by LiteWolf101 on 10/3/2018.
 */
public class TEAncientLaser extends TileEntity implements ITickable{
    @Override
    public void update() {
        IBlockState source1 = BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE);
        IBlockState source2 = BlocksInit.DETECTOR_RUNE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE);
        IBlockState source3 = BlocksInit.SLAUGHTIVE_RUNE.getDefaultState().withProperty(STAGE, EnumStage.EnumType.STAGE_6);

        EnumFacing direction = world.getBlockState(pos).getValue(FACING); //default
        Boolean activated = false; //default
        if (world.getBlockState(pos.north()) == source1 | world.getBlockState(pos.north()) == source2 | world.getBlockState(pos.north()) == source3){
            direction = EnumFacing.SOUTH;
            activated = true;
        } else if (world.getBlockState(pos.east()) == source1 | world.getBlockState(pos.east()) == source2 | world.getBlockState(pos.east()) == source3){
            direction = EnumFacing.WEST;
            activated = true;
        } else if (world.getBlockState(pos.south()) == source1 | world.getBlockState(pos.south()) == source2 | world.getBlockState(pos.south()) == source3){
            direction = EnumFacing.NORTH;
            activated = true;
        } else if (world.getBlockState(pos.west()) == source1 | world.getBlockState(pos.west()) == source2 | world.getBlockState(pos.west()) == source3){
            direction = EnumFacing.EAST;
            activated = true;
        } else if (world.getBlockState(pos.down()) == source1 | world.getBlockState(pos.down()) == source2 | world.getBlockState(pos.down()) == source3){
            direction = EnumFacing.UP;
            activated = true;
        } else if (world.getBlockState(pos.up()) == source1 | world.getBlockState(pos.up()) == source2 | world.getBlockState(pos.up()) == source3){
            direction = EnumFacing.DOWN;
            activated = true;
        }
        world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, activated).withProperty(FACING, direction));
        if (activated){
            setLaser(15, direction);
        } else {
            replaceLaser(15, direction);
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
            } else  if (direction == EnumFacing.EAST | direction == EnumFacing.WEST) {
                if (world.isAirBlock(pos.offset(direction, i))){
                    world.setBlockState(pos.offset(direction, i), BlocksInit.LASER_BLOCK.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z));
                } else if (world.getBlockState(pos.offset(direction, i)).getBlock() != BlocksInit.LASER_BLOCK){
                    i = 20;
                }
            } else  if (direction == EnumFacing.UP | direction == EnumFacing.DOWN) {
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
            if (world.getBlockState(pos.offset(direction, i)).getBlock() == BlocksInit.LASER_BLOCK) {
                world.setBlockToAir(pos.offset(direction, i));
            }
        }
    }
}