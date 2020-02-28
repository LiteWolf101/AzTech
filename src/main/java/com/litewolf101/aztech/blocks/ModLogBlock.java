package com.litewolf101.aztech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class ModLogBlock extends LogBlock {
    private final Supplier<Block> strippedLogBlock;

    public ModLogBlock(Supplier<Block> strippedLog, MaterialColor color, Properties properties) {
        super(color, properties);
        this.strippedLogBlock = strippedLog;
    }

    private ModLogBlock(Block strippedLog, MaterialColor color, Properties properties) {
        super(color, properties);
        this.strippedLogBlock = () -> strippedLog;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult result) {
        if (entity.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem) {
            world.playSound(entity, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            entity.getHeldItem(Hand.MAIN_HAND).damageItem(1, (PlayerEntity)entity, (playerEnt) -> {
                ((PlayerEntity)entity).sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
            world.setBlockState(pos, this.strippedLogBlock.get().getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS)));
        }
        return entity.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem;
    }
}
