package com.litewolf101.aztech.blocks.tileEntity;

import com.litewolf101.aztech.init.ModTileEntityTypes;
import com.litewolf101.aztech.utils.TemplePuzzleBlock;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

import static com.litewolf101.aztech.blocks.ObjectiveBlock.ACTIVATED;

public class TEObjectiveBlock extends TileEntity implements ITickableTileEntity {
    private final List<BlockPos> linkedPos = new ArrayList<BlockPos>();
    private int completedTiles;

    public TEObjectiveBlock(final TileEntityType<?> type) {
        super(type);
    }

    public TEObjectiveBlock() {
        this(ModTileEntityTypes.OBJECTIVE_BLOCK);
    }

    @Override
    public void tick() {
        if (this.world!= null && !this.world.isRemote) {
            if (this.linkedPos.isEmpty() && getBlockState() == getBlockState().with(ACTIVATED, true)) {
                this.world.setBlockState(this.pos, this.getBlockState().with(ACTIVATED, false), 3);

            } else if (!this.linkedPos.isEmpty()) {
                for (int i = 0; i < this.linkedPos.size(); ++i) {
                    if (this.world.getBlockState(this.linkedPos.get(i)).getBlock() instanceof TemplePuzzleBlock) {
                        if (((TemplePuzzleBlock) this.world.getBlockState(this.linkedPos.get(i)).getBlock()).getCompletionState(this.world.getBlockState(this.linkedPos.get(i)))) {
                            ++this.completedTiles;
                        }
                    } else {
                        this.linkedPos.remove(i);
                    }
                }
                if (getBlockState() == this.getBlockState().with(ACTIVATED, true) && this.completedTiles != this.linkedPos.size()) {
                    this.world.setBlockState(this.pos, this.getBlockState().with(ACTIVATED, false), 3);
                    this.completedTiles = 0;
                } else if (this.completedTiles == this.linkedPos.size()){
                    this.world.setBlockState(this.pos, this.getBlockState().with(ACTIVATED, true), 3);
                    this.completedTiles = 0;
                } else {
                    this.completedTiles = 0;
                }
            }
        }
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        if (nbt.contains("linked_positions")) {
            ListNBT listNBT = (ListNBT) nbt.get("linked_positions");
            if (listNBT != null && !listNBT.isEmpty()) {
                for (int i = 0; i < listNBT.size(); ++i) {
                    CompoundNBT blockpos = listNBT.getCompound(i);
                    this.linkedPos.add(i, new BlockPos(blockpos.getInt("x"), blockpos.getInt("y"), blockpos.getInt("z")));
                }
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        ListNBT listNBT = new ListNBT();

        for (int i = 0; i < this.linkedPos.size(); ++i) {
            BlockPos pos = this.linkedPos.get(i);
            if (pos != null) {
                CompoundNBT blockPos = new CompoundNBT();
                blockPos.putInt("x", pos.getX());
                blockPos.putInt("y", pos.getY());
                blockPos.putInt("z", pos.getZ());
                listNBT.add(i, blockPos);
            }
        }
        nbt.put("linked_positions", listNBT);
        return nbt;
    }

    public void addToLinkedList (BlockPos pos) {
        if (!this.linkedPos.contains(pos)) {
            this.linkedPos.add(pos);
        }
    }

    public void removeFromLinkedList (BlockPos pos) {
        this.linkedPos.remove(pos);
    }

    public void getLinkedPositionsList(ItemUseContext context) {
        if (this.linkedPos.isEmpty()) {
            context.getPlayer().sendMessage(new StringTextComponent("None"));
        } else {
            for (int i = 0; i < this.linkedPos.size(); ++i) {
                if (this.world.getBlockState(this.linkedPos.get(i)).getBlock() instanceof TemplePuzzleBlock) {
                    if (((TemplePuzzleBlock) this.world.getBlockState(this.linkedPos.get(i)).getBlock()).getCompletionState(this.world.getBlockState(this.linkedPos.get(i)))) {
                        context.getPlayer().sendMessage(new StringTextComponent(TextFormatting.AQUA + this.linkedPos.get(i).toString()));
                    } else {
                        context.getPlayer().sendMessage(new StringTextComponent(this.linkedPos.get(i).toString()));
                    }
                }
            }
        }
    }
}
