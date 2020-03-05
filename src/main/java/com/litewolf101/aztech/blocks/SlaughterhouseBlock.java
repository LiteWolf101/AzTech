package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.blocks.tileEntity.TESlaughterhouseBlock;
import com.litewolf101.aztech.init.ModTileEntityTypes;
import com.litewolf101.aztech.utils.RunePowerSource;
import com.litewolf101.aztech.utils.TemplePuzzleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class SlaughterhouseBlock extends Block implements TemplePuzzleBlock, RunePowerSource {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_0_8;
    public SlaughterhouseBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, 0));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.SLAUGHTERHOUSE_DETECTOR.create();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LEVEL) == 8) {
            spawnParticles(worldIn, pos);
        }

    }

    private static void spawnParticles(World p_180691_0_, BlockPos worldIn) {
        double d0 = 0.5625D;
        Random random = p_180691_0_.rand;

        for(Direction direction : Direction.values()) {
            BlockPos blockpos = worldIn.offset(direction);
            if (!p_180691_0_.getBlockState(blockpos).isOpaqueCube(p_180691_0_, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getXOffset() : (double)random.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getYOffset() : (double)random.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getZOffset() : (double)random.nextFloat();
                p_180691_0_.addParticle(RedstoneParticleData.REDSTONE_DUST, (double)worldIn.getX() + d1, (double)worldIn.getY() + d2, (double)worldIn.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean getCompletionState(BlockState state) {
        return state.get(LEVEL) == 8;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        TileEntity te = world.getTileEntity(result.getPos());
        if (player.getHeldItemMainhand().isEmpty()) {
            if (te != null) {
                if (te instanceof TESlaughterhouseBlock) {
                    if (!world.isRemote){
                        player.sendMessage(new StringTextComponent(TextFormatting.RED + "Target: " + TextFormatting.RESET + ((TESlaughterhouseBlock) te).getTargetEntity().getRegistryName()));
                        player.sendMessage(new StringTextComponent(TextFormatting.RED + "Count: " + TextFormatting.AQUA + ((TESlaughterhouseBlock) te).getCurrentKillCount() + "/" + ((TESlaughterhouseBlock) te).getMaxKillCount()));
                    }
                }
            }
        }

        if (player.getHeldItemMainhand().getItem() instanceof SpawnEggItem) {
            EntityType type = ((SpawnEggItem)player.getHeldItemMainhand().getItem()).getType(null);
            if (type != null) {
                if (te != null) {
                    if (te instanceof TESlaughterhouseBlock) {
                        ((TESlaughterhouseBlock) te).setTargetEntity((LivingEntity) type.create(world));
                        if (!world.isRemote) {
                            player.sendMessage(new StringTextComponent(TextFormatting.RED + "Set target entity to: " + TextFormatting.RESET + type.getRegistryName()));
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean getPoweredState(BlockState state) {
        return state.get(LEVEL) == 8;
    }
}
