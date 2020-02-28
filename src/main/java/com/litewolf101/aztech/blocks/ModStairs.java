package com.litewolf101.aztech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class ModStairs extends StairsBlock {
    public ModStairs(BlockState blockstate) {
        super(() -> blockstate, Properties.from(blockstate.getBlock()));
    }
}
