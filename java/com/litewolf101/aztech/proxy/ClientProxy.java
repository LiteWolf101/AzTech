package com.litewolf101.aztech.proxy;

import com.litewolf101.aztech.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.GrassColors;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;

public class ClientProxy implements com.litewolf101.aztech.proxy.IProxy {
    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    public void initBlockColors() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        //Blocks
        blockColors.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D),
                ModBlocks.ANCIENT_GRASS);

        //Items

        //Item Blocks
        itemColors.register((stack, tintIndex) -> {
                    BlockState BlockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
                    return blockColors.getColor(BlockState, null, null, tintIndex); },
                ModBlocks.ANCIENT_GRASS);
    }
}
