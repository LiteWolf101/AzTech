package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

import java.util.Random;

public class CustomSpreadableDirtBlock extends SnowyDirtBlock {
    protected CustomSpreadableDirtBlock(Block.Properties builder) {
        super(builder);
    }

    private static boolean isSnowAbove(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = reader.getBlockState(blockpos);
        if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else {
            int i = LightEngine.func_215613_a(reader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(reader, blockpos));
            return i < reader.getMaxLightLevel();
        }
    }

    private static boolean func_220256_c(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return isSnowAbove(state, reader, pos) && !reader.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    @SuppressWarnings("deprecation")
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!isSnowAbove(state, worldIn, pos)) {
                worldIn.setBlockState(pos, ModBlocks.ANCIENT_DIRT.getDefaultState());
            } else {
                if (worldIn.getLight(pos.up()) >= 9) {
                    BlockState blockstate = this.getDefaultState();

                    for(int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.ANCIENT_DIRT && func_220256_c(blockstate, worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos, blockstate.with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
                        }
                    }
                }

            }
        }
    }
}
