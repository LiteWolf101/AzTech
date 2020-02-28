package com.litewolf101.aztech.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;

import java.util.function.Supplier;

public class CustomBlockNamedItem extends BlockNamedItem {

    public CustomBlockNamedItem(Supplier<Block> block, Properties properties) {
        super(block.get(), properties);
    }
}
