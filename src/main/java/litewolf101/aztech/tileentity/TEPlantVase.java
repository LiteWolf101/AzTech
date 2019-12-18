package litewolf101.aztech.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class TEPlantVase extends TileEntity {
    private Item vaseItem;
    private int vaseData;

    public TEPlantVase()
    {
    }

    public TEPlantVase(Item potItem, int potData)
    {
        this.vaseItem = potItem;
        this.vaseData = potData;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        ResourceLocation resourcelocation = Item.REGISTRY.getNameForObject(this.vaseItem);
        compound.setString("Item", resourcelocation == null ? "" : resourcelocation.toString());
        compound.setInteger("Data", this.vaseData);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if (compound.hasKey("Item", 8))
        {
            this.vaseItem = Item.getByNameOrId(compound.getString("Item"));
        }
        else
        {
            this.vaseItem = Item.getItemById(compound.getInteger("Item"));
        }

        this.vaseData = compound.getInteger("Data");
    }

    /**
     * Retrieves packet to send to the client whenever this Tile Entity is resynced via World.notifyBlockUpdate. For
     * modded TE's, this packet comes back to you clientside in {@link #onDataPacket}
     */
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 5, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void setItemStack(ItemStack stack)
    {
        if (stack.getItem() instanceof ItemSeedFood) {
            //Item itemseed = Item.getItemFromBlock(((ItemSeedFood)stack.getItem()).getPlant(this.world, this.pos).getBlock());
            this.vaseItem = Item.getItemFromBlock(Blocks.CARROTS);
            System.out.println(this.vaseItem);
            this.vaseData = 7; //This will crash if the block overrides the AGE property but extends BlockCrop
        } else {
            this.vaseItem = stack.getItem();
            this.vaseData = stack.getMetadata();
        }
    }

    public ItemStack getFlowerItemStack()
    {
        Block blockBush = Block.getBlockFromItem(this.vaseItem);
        if (blockBush != null) {
            if (blockBush instanceof BlockBush) {
                if (blockBush instanceof BlockCrops) {
                    return ((BlockCrops)blockBush).getItem(this.world, this.pos, blockBush.getDefaultState());
                }
                return new ItemStack(blockBush, 1, this.vaseData);
            }
        }
        if (this.vaseItem instanceof ItemSeeds) {
            return new ItemStack(((ItemSeeds) this.vaseItem).getPlant(this.world, null).getBlock());
        }
        return this.vaseItem == null ? ItemStack.EMPTY : new ItemStack(this.vaseItem, 1, this.vaseData);
    }

    @Nullable
    public Item getVaseItem()
    {
        return this.vaseItem;
    }

    public int getVaseData()
    {
        return this.vaseData;
    }
}
