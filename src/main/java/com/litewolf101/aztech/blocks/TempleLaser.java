package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.init.ModBlocks;
import com.litewolf101.aztech.utils.RunePowerSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

import static com.litewolf101.aztech.blocks.BeamBlock.DISTANCE;

public class TempleLaser extends Block {
    private static final VoxelShape BASE_UP = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D);
    private static final VoxelShape BASE2_UP = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D);
    private static final VoxelShape COIL_UP = Block.makeCuboidShape(4.5D, 2.0D, 4.5D, 11.5D, 8.0D, 11.5D);
    private static final VoxelShape POLE_UP = Block.makeCuboidShape(6.0D, 2.0D, 6.0D, 10.0D, 14.0D, 10.0D);

    private static final VoxelShape BASE_D = Block.makeCuboidShape(2.0D, 15.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape BASE2_D = Block.makeCuboidShape(4.0D, 14.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    private static final VoxelShape COIL_D = Block.makeCuboidShape(4.5D, 9.0D, 4.5D, 11.5D, 14.0D, 11.5D);
    private static final VoxelShape POLE_D = Block.makeCuboidShape(6.0D, 2.0D, 6.0D, 10.0D, 14.0D, 10.0D);

    private static final VoxelShape BASE_S = Block.makeCuboidShape(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 1.0D);
    private static final VoxelShape BASE2_S = Block.makeCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 2.0D);
    private static final VoxelShape COIL_S = Block.makeCuboidShape(4.5D, 4.5D, 2D, 11.5D, 11.5D, 8D);
    private static final VoxelShape POLE_S = Block.makeCuboidShape(6.0D, 6.0D, 2.0D, 10.0D, 10.0D, 14.0D);

    private static final VoxelShape BASE_N = Block.makeCuboidShape(2.0D, 2.0D, 15.0D, 14.0D, 14.0D, 16.0D);
    private static final VoxelShape BASE2_N = Block.makeCuboidShape(4.0D, 4.0D, 14.0D, 12.0D, 12.0D, 16.0D);
    private static final VoxelShape COIL_N = Block.makeCuboidShape(4.5D, 4.5D, 9D, 11.5D, 11.5D, 14D);
    private static final VoxelShape POLE_N = Block.makeCuboidShape(6.0D, 6.0D, 2.0D, 10.0D, 10.0D, 14.0D);

    private static final VoxelShape BASE_E = Block.makeCuboidShape(0.0D, 2.0D, 2.0D, 1.0D, 14.0D, 14.0D);
    private static final VoxelShape BASE2_E = Block.makeCuboidShape(0.0D, 4.0D, 4.0D, 2.0D, 12.0D, 12.0D);
    private static final VoxelShape COIL_E = Block.makeCuboidShape(2D, 4.5D, 4.5D, 8D, 11.5D, 11.5D);
    private static final VoxelShape POLE_E = Block.makeCuboidShape(2.0D, 6.0D, 6.0D, 14.0D, 10.0D, 10.0D);

    private static final VoxelShape BASE_W = Block.makeCuboidShape(15.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);
    private static final VoxelShape BASE2_W = Block.makeCuboidShape(14.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);
    private static final VoxelShape COIL_W = Block.makeCuboidShape(9D, 4.5D, 4.5D, 14D, 11.5D, 11.5D);
    private static final VoxelShape POLE_W = Block.makeCuboidShape(2.0D, 6.0D, 6.0D, 14.0D, 10.0D, 10.0D);

    private static final VoxelShape UP_AABB = VoxelShapes.or(BASE_UP, BASE2_UP, COIL_UP, POLE_UP);
    private static final VoxelShape DOWN_AABB = VoxelShapes.or(BASE_D, BASE2_D, COIL_D, POLE_D);
    private static final VoxelShape SOUTH_AABB = VoxelShapes.or(BASE_S, BASE2_S, COIL_S, POLE_S);
    private static final VoxelShape NORTH_AABB = VoxelShapes.or(BASE_N, BASE2_N, COIL_N, POLE_N);
    private static final VoxelShape EAST_AABB = VoxelShapes.or(BASE_E, BASE2_E, COIL_E, POLE_E);
    private static final VoxelShape WEST_AABB = VoxelShapes.or(BASE_W, BASE2_W, COIL_W, POLE_W);

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty ACTIVATED = BlockStateProperties.POWERED;

    public TempleLaser(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP).with(ACTIVATED, false));
    }

    @Override
    public int tickRate(IWorldReader p_149738_1_) {
        return 5;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch (state.get(FACING)) {
            case UP:
                return UP_AABB;
            case DOWN:
                return DOWN_AABB;
            case NORTH:
                return NORTH_AABB;
            case EAST:
                return EAST_AABB;

            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
        }
        return UP_AABB;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVATED);
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
        setLaser(world, state, pos);
        world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        setLaser(world, state, pos);
        world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
    }

    public void setLaser(World world, BlockState state, BlockPos pos) {
        Direction[] directions = Direction.values();
        if (!world.isRemote())
            for (Direction direction : directions) {
                if (direction == state.get(FACING).getOpposite()) {
                    if (world.getBlockState(pos.offset(direction)).getBlock() instanceof RunePowerSource) {
                        if (((RunePowerSource)world.getBlockState(pos.offset(direction)).getBlock()).getPoweredState(world.getBlockState(pos.offset(direction)))) {
                            if (world.getBlockState(pos.offset(state.get(FACING))).getBlock() == Blocks.AIR) {
                                world.setBlockState(pos, state.with(ACTIVATED, true));
                                world.setBlockState(pos.offset(state.get(FACING)), ModBlocks.BEAM_BLOCK.getDefaultState().with(FACING, state.get(FACING)).with(DISTANCE, 0));
                            }
                        } else {
                            world.setBlockState(pos, state.with(ACTIVATED, false));
                        }
                    } else {
                        world.setBlockState(pos, state.with(ACTIVATED, false));
                    }
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
