package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumRuneState;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static litewolf101.aztech.objects.blocks.BlockSlaughtiveRune.STAGE;
import static litewolf101.aztech.objects.blocks.R2RTranslator.ACTIVATED;
import static litewolf101.aztech.objects.blocks.RuneLine.ACTIVATION;

/**
 * Created by LiteWolf101 on 10/3/2018.
 */
public class TERuneLine extends TileEntity implements ITickable {
    int tick;
    @Override
    public void update() {
        /**tick++;
        if (world.getBlockState(pos) == BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE)){
            if (isPowerSource(world, pos)) {
                world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.POWERING_UP));
            }
        } else if (world.getBlockState(pos) == BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE)){
            if (!isPowerSource(world, pos)) {
                world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.POWERING_DOWN));
            }
        }

        if (tick % 10 == 0) {
            if (world.getBlockState(pos) == BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.POWERING_UP)){
                world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE));
            } else if (world.getBlockState(pos) == BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.POWERING_DOWN)){
                world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE));
            }
        }*/
    }

    public boolean isPowerSource(World world, BlockPos pos){
        IBlockState source1 = BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATED, true);
        IBlockState source2 = BlocksInit.DETECTOR_RUNE.getDefaultState().withProperty(ACTIVATED, true);
        IBlockState source3 = BlocksInit.SLAUGHTIVE_RUNE.getDefaultState().withProperty(STAGE, EnumStage.EnumType.STAGE_6);
        if (world.getBlockState(pos.north()) == source1 | world.getBlockState(pos.north()) == source2 | world.getBlockState(pos.north()) == source3){
            return true;
        } else if (world.getBlockState(pos.east()) == source1 | world.getBlockState(pos.east()) == source2 | world.getBlockState(pos.east()) == source3){
            return true;
        } else if (world.getBlockState(pos.south()) == source1 | world.getBlockState(pos.south()) == source2 | world.getBlockState(pos.south()) == source3){
            return true;
        } else if (world.getBlockState(pos.west()) == source1 | world.getBlockState(pos.west()) == source2 | world.getBlockState(pos.west()) == source3){
            return true;
        } else if (world.getBlockState(pos.down()) == source1 | world.getBlockState(pos.down()) == source2 | world.getBlockState(pos.down()) == source3){
            return true;
        } else if (world.getBlockState(pos.up()) == source1 | world.getBlockState(pos.up()) == source2 | world.getBlockState(pos.up()) == source3){
            return true;
        } else return false;
    }
}
