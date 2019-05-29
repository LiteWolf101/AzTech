package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.blocks.InsertiveRune;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;

import static litewolf101.aztech.objects.blocks.InsertiveRune.ACTIVATED;

/**
 * Created by LiteWolf101 on Feb
 * /15/2019
 */
public class TileEntityInsertiveRune extends TileEntity implements IInventory, ITickable {
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    public boolean locked;

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return (ItemStack)this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemStack = (ItemStack)this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && ItemStack.areItemStackTagsEqual(stack, itemStack);
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && index + 1 == 1 && !flag) {
            ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
            markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (player.isSpectator()) {
            player.sendMessage(new TextComponentString(TextFormatting.RED + "Cannot open gui in spectator mode!"));
        }
        return player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64 && !player.isSpectator();
    }

    @Override
    public void openInventory(EntityPlayer player) {
        world.playSound(player, pos, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1 ,1);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        world.playSound(player, pos, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1 ,1);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (locked) {
            if (index == 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public String getName() {
        return "Insertive Rune";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(this.getName());
    }

    @Override
    public void update() {
        IBlockState iblockstate = world.getBlockState(pos);
        boolean flag1 = false;
        Item item0 = this.getStackInSlot(0).getItem();
        Item item1 = this.getStackInSlot(1).getItem();

        if (this.getStackInSlot(0) != ItemStack.EMPTY) {
            if (item0 != Items.AIR && item0 == item1) {
                flag1 = true;
                if (iblockstate == BlocksInit.INSERTIVE_RUNE.getDefaultState().withProperty(BlockHorizontal.FACING, iblockstate.getValue(BlockHorizontal.FACING)).withProperty(ACTIVATED, false)) {
                    InsertiveRune.setState(true, this.world, this.pos);
                }
            } else {
                if (iblockstate == BlocksInit.INSERTIVE_RUNE.getDefaultState().withProperty(BlockHorizontal.FACING, iblockstate.getValue(BlockHorizontal.FACING)).withProperty(ACTIVATED, true)) {
                    InsertiveRune.setState(false, this.world, this.pos);
                }
            }
            if (flag1){
                this.markDirty();
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        this.locked = compound.getBoolean("Locked");
        ItemStackHelper.loadAllItems(compound, inventory);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, inventory);
        compound.setBoolean("Locked", isLocked());
        return compound;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
