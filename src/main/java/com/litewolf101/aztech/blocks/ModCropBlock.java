package com.litewolf101.aztech.blocks;

import com.litewolf101.aztech.init.ModBlocks;
import com.litewolf101.aztech.utils.NoAutomaticBlockItem;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class ModCropBlock extends CropsBlock implements NoAutomaticBlockItem {
    private final Supplier<Item> crop;

    public ModCropBlock(Supplier<Item> cropItem, Block.Properties properties) {
        super(properties);
        this.crop = () -> (Item) cropItem;
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return (Item)this.crop;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getBlock() == Blocks.FARMLAND || state.getBlock() == ModBlocks.ANCIENT_FARMLAND || state.getBlock() instanceof FarmlandBlock;
    }
}
