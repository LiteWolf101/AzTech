package com.litewolf101.aztech.items;

import com.litewolf101.aztech.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AzTechRing extends Item {

    public AzTechRing(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(blockpos);

        //TODO Make item work with portal to activate it
        if (state == ModBlocks.ANCIENT_BEDROCK.getDefaultState()) {
            if (world.isRemote) {
                return ActionResultType.SUCCESS;
            } else {
                world.playSound(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1, 1);
                context.getItem().damageItem(1, player, (p_220043_1_) -> p_220043_1_.sendBreakAnimation(context.getHand()));
            }
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }

    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag flag) {
        textComponents.add(new TranslationTextComponent("aztech.description.aztech_ring"));
    }
}
