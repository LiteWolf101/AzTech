package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

import static com.litewolf101.aztech.blocks.TempleLaser.ACTIVATED;

public class BeamBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 0, 15);

    public BeamBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP).with(DISTANCE, 0));
    }

    @Override
    public int tickRate(IWorldReader p_149738_1_) {
        return 2;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, DISTANCE);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getFace();
        BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction.getOpposite()));
        return blockstate.getBlock() == this && blockstate.get(FACING) == direction ? this.getDefaultState().with(FACING, direction.getOpposite()) : this.getDefaultState().with(FACING, direction);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        setLasers(world, state, pos);
        world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        setLasers(world, state, pos);
        world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
    }

    public void setLasers(World world, BlockState state, BlockPos pos) {
        BlockState opposite = world.getBlockState(pos.offset(state.get(FACING).getOpposite()));
        boolean flag = opposite.getBlock() == ModBlocks.BEAM_BLOCK && opposite.get(FACING) == state.get(FACING) && opposite.get(DISTANCE) < state.get(DISTANCE);
        if (!world.isRemote()) {
            if (opposite == ModBlocks.TEMPLE_LASER.getDefaultState().with(ACTIVATED, true).with(FACING, state.get(FACING)) || flag) {
                if (world.getBlockState(pos.offset(state.get(FACING))).getBlock() == Blocks.AIR) {
                    if (state.get(DISTANCE) != 15) {
                        world.setBlockState(pos.offset(state.get(FACING)), ModBlocks.BEAM_BLOCK.getDefaultState().with(FACING, state.get(FACING)).with(DISTANCE, state.get(DISTANCE) + 1));
                    }
                }
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}
