package litewolf101.aztech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class TEGeoObelisk extends TileEntity {
    private boolean hasMaster, isMaster;
    private BlockPos masterPos;

    public void reset()
    {
        masterPos = new BlockPos(0, 0, 0);
        hasMaster = false;
        isMaster = false;
    }

    public boolean checkForMaster()
    {
        TileEntity tile = world.getTileEntity(masterPos);
        return(tile != null && (tile instanceof TEGeoObelisk));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data)
    {
        data = super.writeToNBT(data);
        if(masterPos == null)
            masterPos = new BlockPos(0, 0, 0);
        data.setInteger("master_x", masterPos.getX());
        data.setInteger("master_y", masterPos.getY());
        data.setInteger("master_z", masterPos.getZ());
        data.setBoolean("hasMaster", hasMaster);
        data.setBoolean("isMaster", isMaster);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        super.readFromNBT(data);
        int masterX = data.getInteger("master_x");
        int masterY = data.getInteger("master_y");
        int masterZ = data.getInteger("master_z");
        this.masterPos = new BlockPos(masterX, masterY, masterZ);
        hasMaster = data.getBoolean("hasMaster");
        isMaster = data.getBoolean("isMaster");
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }

    public boolean hasMaster()
    {
        return hasMaster;
    }

    public boolean isMaster()
    {
        return isMaster;
    }

    public BlockPos getMasterPostion()
    {
        return masterPos;
    }

    public void setHasMaster(boolean bool)
    {
        hasMaster = bool;
    }

    public void setIsMaster(boolean bool)
    {
        isMaster = bool;
    }

    public void setMasterCoords(int x, int y, int z)
    {
        this.setMasterCoords(new BlockPos(x, y, z));
    }

    public void setMasterCoords(BlockPos pos)
    {
        this.masterPos = pos;
    }
}
