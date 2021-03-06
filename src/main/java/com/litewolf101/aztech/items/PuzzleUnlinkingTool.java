package com.litewolf101.aztech.items;

import com.litewolf101.aztech.blocks.tileEntity.TEObjectiveBlock;
import com.litewolf101.aztech.init.ModBlocks;
import com.litewolf101.aztech.init.ModItems;
import com.litewolf101.aztech.utils.TemplePuzzleBlock;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PuzzleUnlinkingTool extends Item {
    public BlockPos linkingPos;

    public PuzzleUnlinkingTool(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> text, ITooltipFlag flag) {
        text.add(new StringTextComponent("To unlink puzzle blocks to the Objective block:"));
        text.add(new StringTextComponent("1. Right-Click on an Objective block to began unlinking."));
        text.add(new StringTextComponent("2. Right-Click again on a puzzle block to unlink it to the Objective block."));
        text.add(new StringTextComponent("(To change modes, Shift-Right-Click the air.)"));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (!context.getWorld().isRemote)
            if (player != null) {
                if (this.linkingPos == null && context.getWorld().getBlockState(context.getPos()).getBlock() != ModBlocks.OBJECTIVE_BLOCK){
                    player.sendStatusMessage(new StringTextComponent(TextFormatting.RED + "This is not an Objective Block. Select an Objective Block first!"), true);
                }

                //If we right-click an objective block, set the linking pos
                if (context.getWorld().getBlockState(context.getPos()).getBlock() == ModBlocks.OBJECTIVE_BLOCK){
                    this.linkingPos = context.getPos();
                    player.sendStatusMessage(new StringTextComponent("Objective block selected at [" + context.getPos().getX() + ", " + context.getPos().getY() + ", " + context.getPos().getZ() + "]"), true);
                }


                //If there is already a selected linking pos and we right-click a puzzle block, unlink it to the tile entity
                else if (this.linkingPos != null) {
                    if (context.getWorld().getBlockState(context.getPos()).getBlock() instanceof TemplePuzzleBlock) {
                        if (context.getWorld().getTileEntity(this.linkingPos) != null) {
                            if (context.getWorld().getTileEntity(this.linkingPos) instanceof TEObjectiveBlock) {
                                TileEntity te = context.getWorld().getTileEntity(this.linkingPos);
                                if (te != null) {
                                    ((TEObjectiveBlock)te).removeFromLinkedList(context.getPos());
                                    if (this.linkingPos != null) {
                                        player.sendStatusMessage(new StringTextComponent("Successfully unlinked [" + context.getPos().getX() + ", " + context.getPos().getY() + ", " + context.getPos().getZ() + "] from [" + this.linkingPos.getX() + ", " + this.linkingPos.getY() + ", " + this.linkingPos.getZ() + "]"), true);
                                    }
                                    this.linkingPos = null;
                                }
                            } else {
                                player.sendStatusMessage(new StringTextComponent(TextFormatting.RED + "Error: Block was either removed or corrupted."), true);
                                this.linkingPos = null;
                            }
                        } else {
                            player.sendStatusMessage(new StringTextComponent(TextFormatting.RED + "Error: Block was either removed or corrupted."), true);
                            this.linkingPos = null;
                        }
                    } else if (context.getWorld().getBlockState(context.getPos()).getBlock() != ModBlocks.OBJECTIVE_BLOCK) {
                        player.sendStatusMessage(new StringTextComponent(TextFormatting.RED + "This is not a Temple Puzzle Block"), true);
                    }
                }

            }
        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (ItemStack.areItemsEqual(player.getHeldItemMainhand(), new ItemStack(this))) {
            player.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.PUZZLE_LINKING_TOOL_DEBUG));
        }
        if (ItemStack.areItemsEqual(player.getHeldItemOffhand(), new ItemStack(this))) {
            player.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ModItems.PUZZLE_LINKING_TOOL_DEBUG));
        }
        return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
    }
}
