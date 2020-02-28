package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class ModOreBlock extends Block {
    public ModOreBlock(Block.Properties properties) {
        super(properties);
    }

    protected int getExpFromOre(Random random) {
        if (
                this == ModBlocks.RED_RUNE_ORE
                        || this == ModBlocks.YELLOW_RUNE_ORE
                        || this == ModBlocks.GREEN_RUNE_ORE
                        || this == ModBlocks.BLUE_RUNE_ORE
                        || this == ModBlocks.WHITE_RUNE_ORE
                        || this == ModBlocks.BLACK_RUNE_ORE
                        || this == ModBlocks.ANCIENT_RED_RUNE_ORE
                        || this == ModBlocks.ANCIENT_YELLOW_RUNE_ORE
                        || this == ModBlocks.ANCIENT_GREEN_RUNE_ORE
                        || this == ModBlocks.ANCIENT_BLUE_RUNE_ORE
                        || this == ModBlocks.ANCIENT_WHITE_RUNE_ORE
                        || this == ModBlocks.ANCIENT_BLACK_RUNE_ORE
        ) {
            return MathHelper.nextInt(random, 1, 4);
        } else if (this == ModBlocks.ANCIENT_COAL_ORE) {
            return MathHelper.nextInt(random, 0, 2);
        } else if (this == ModBlocks.ANCIENT_DIAMOND_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        } else if (this == ModBlocks.ANCIENT_EMERALD_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        } else if (this == ModBlocks.ANCIENT_LAPIS_ORE) {
            return MathHelper.nextInt(random, 2, 5);
        } else {
            return this == ModBlocks.ANCIENT_QUARTZ_ORE ? MathHelper.nextInt(random, 2, 5) : 0;
        }
    }

    /**
     * Perform side-effects from block dropping, such as creating silverfish
     */
    @Override
    @SuppressWarnings("deprecation")
    public void spawnAdditionalDrops(BlockState state, World worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExpFromOre(RANDOM) : 0;
    }

    @Override
    public int getHarvestLevel(BlockState p_getHarvestLevel_1_) {
        return super.getHarvestLevel(p_getHarvestLevel_1_);
    }
}
