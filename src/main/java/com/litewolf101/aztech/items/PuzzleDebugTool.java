package com.litewolf101.aztech.items;

import com.litewolf101.aztech.blocks.tileEntity.TEObjectiveBlock;
import com.litewolf101.aztech.init.ModBlocks;
import com.litewolf101.aztech.init.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PuzzleDebugTool extends Item {
    public PuzzleDebugTool(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> text, ITooltipFlag flag) {
        text.add(new StringTextComponent("To look up linked data on the Objective block:"));
        text.add(new StringTextComponent("1. Shift-Right-Click on an Objective block to look at data."));
        text.add(new StringTextComponent("(To change modes, Shift-Right-Click the air.)"));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote) {
            if (context.getPlayer() != null && context.getPlayer().isSneaking()) {
                if (context.getWorld().getBlockState(context.getPos()).getBlock() == ModBlocks.OBJECTIVE_BLOCK) {
                    if (context.getWorld().getTileEntity(context.getPos()) != null) {
                        TileEntity te = context.getWorld().getTileEntity(context.getPos());
                        if (te instanceof TEObjectiveBlock) {
                            context.getPlayer().sendMessage(new StringTextComponent(TextFormatting.GREEN + "Linked Positions:"));
                            ((TEObjectiveBlock) te).getLinkedPositionsList(context);
                            return ActionResultType.SUCCESS;
                        }
                    }
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (ItemStack.areItemsEqual(player.getHeldItemMainhand(), new ItemStack(this))) {
            player.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.PUZZLE_LINKING_TOOL_LINK));
        }
        if (ItemStack.areItemsEqual(player.getHeldItemOffhand(), new ItemStack(this))) {
            player.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ModItems.PUZZLE_LINKING_TOOL_LINK));
        }
        return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
    }
}
