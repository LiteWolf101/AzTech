package litewolf101.aztech.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumRuneState;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static litewolf101.aztech.objects.blocks.BlockSlaughtiveRune.STAGE;
import static litewolf101.aztech.objects.blocks.R2RTranslator.ACTIVATION;

/**
 * Created by LiteWolf101 on 10/3/2018.
 */
public class TERuneLine extends TileEntity implements ITickable{
    private final Set<BlockPos> blocksNeedingUpdate = Sets.newHashSet();
    private boolean ischild;
    private boolean isparent;
    @Override
    public void update() {
        if (isPowerSource(pos)){
            world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE));
        } else {
            world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE));
        }
    }

    public boolean isPowerSource(BlockPos pos){
        IBlockState source1 = BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE);
        IBlockState source2 = BlocksInit.DETECTOR_RUNE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE);
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

    public boolean isParentAtPos(BlockPos pos){
        if (isPowerSource(pos)){
             return true;
        } else {
            return false;
        }
    }

    public boolean areNeighborsTheSame(BlockPos pos){
        Block block = BlocksInit.RUNE_LINE;
        if(world.getBlockState(pos.north()).getBlock() == block | world.getBlockState(pos.east()).getBlock() == block | world.getBlockState(pos.south()).getBlock() == block | world.getBlockState(pos.west()).getBlock() == block | world.getBlockState(pos.up()).getBlock() == block | world.getBlockState(pos.down()).getBlock() == block){
            return true;
        } else return false;
    }

    public void doThing(){
        List<BlockPos> list = Lists.newArrayList(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();
        Iterator inr = list.iterator();
        BlockPos blockpos = (BlockPos)inr.next();
        if (isParentAtPos(pos)){
            world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE));

            world.setBlockState(blockpos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE));
        } else if (areNeighborsTheSame(pos) && !isParentAtPos(pos)){
            list.add(pos);
        } else if (!areNeighborsTheSame(pos) && !isParentAtPos(pos)){
            world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE));
        }

    }
}
