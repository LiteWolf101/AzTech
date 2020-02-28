package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class WetMud extends Block {
    public WetMud(final Block.Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up()) && random.nextInt(5) == 1) {
            worldIn.setBlockState(pos, ModBlocks.DRY_MUD.getDefaultState(), 2);
        }
    }

    private static boolean hasWater(IWorldReader worldIn, BlockPos pos) {
        for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-2, 0, -2), pos.add(2, 1, 2))) {
            if (worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
                return true;
            }
        }

        return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
    }
}
