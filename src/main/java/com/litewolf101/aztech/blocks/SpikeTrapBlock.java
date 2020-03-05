package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.init.ModBlocks;
import com.litewolf101.aztech.utils.RunePowerSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class SpikeTrapBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
    public SpikeTrapBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP).with(TRIGGERED, false));
    }

    @Override
    public int tickRate(IWorldReader p_149738_1_) {
        return 5;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TRIGGERED);
    }

    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getFace();
        BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction.getOpposite()));
        return blockstate.getBlock() == this && blockstate.get(FACING) == direction ? this.getDefaultState().with(FACING, direction.getOpposite()) : this.getDefaultState().with(FACING, direction);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!state.get(TRIGGERED)) {
            setSpikes(world, state, pos);
            world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(TRIGGERED)) {
            setSpikes(world, state, pos);
            world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
        }
    }

    public void setSpikes(World world, BlockState state, BlockPos pos) {
        Direction[] directions = Direction.values();
        if (!world.isRemote())
        for (Direction direction : directions) {
            if (direction != state.get(FACING)) {
                if (world.getBlockState(pos.offset(direction)).getBlock() instanceof RunePowerSource) {
                    if (((RunePowerSource)world.getBlockState(pos.offset(direction)).getBlock()).getPoweredState(world.getBlockState(pos.offset(direction)))) {
                        world.destroyBlock(pos.offset(state.get(FACING)), true);
                        world.setBlockState(pos.offset(state.get(FACING)), ModBlocks.SPIKE_BLOCK.getDefaultState().with(FACING, state.get(FACING)));
                        world.setBlockState(pos, state.with(TRIGGERED, true));
                    }
                }
            }
        }
    }
}
