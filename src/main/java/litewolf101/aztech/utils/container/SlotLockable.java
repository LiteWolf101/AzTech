package litewolf101.aztech.utils.container;

import litewolf101.aztech.tileentity.TileEntityInsertiveRune;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by LiteWolf101 on Mar
 * /06/2019
 */
public class SlotLockable extends Slot {
    public TileEntityInsertiveRune tileEntity;
    public SlotLockable(TileEntityInsertiveRune tileEntity, IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean isHere(IInventory inv, int slotIn)
    {
        return tileEntity.isLocked();
    }
}
