package litewolf101.aztech.utils.container;

import litewolf101.aztech.tileentity.TileEntityInsertiveRune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by LiteWolf101 on Feb
 * /15/2019
 */
public class ContainerInsertiveRune extends Container {
    private final TileEntityInsertiveRune tileEntity;
    public ContainerInsertiveRune(InventoryPlayer player, TileEntityInsertiveRune tileEntity, EntityPlayer entityPlayer) {
        this.tileEntity = tileEntity;
        player.openInventory(entityPlayer);

        //top slots
        this.addSlotToContainer(new Slot(tileEntity, 0, 44, 31));
        this.addSlotToContainer(new SlotLockable(tileEntity, tileEntity, 1, 116, 31));

        //inventory
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        //hotbar
        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(player, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        tileEntity.closeInventory(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();
            int inventorySize = tileEntity.getSizeInventory();

            if (fromSlot < inventorySize) {
                // From container inventory to player's inventory
                if (!this.mergeItemStack(current, inventorySize, inventorySize + 36, true))
                    return ItemStack.EMPTY;
            } else {
                // From the player's inventory to container
                if (!this.mergeItemStack(current, 0, inventorySize, false))
                    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return null;
            slot.onTake(playerIn, current);
        }
        return previous;
    }
}
