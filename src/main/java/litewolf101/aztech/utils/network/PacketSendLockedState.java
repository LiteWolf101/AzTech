package litewolf101.aztech.utils.network;

import io.netty.buffer.ByteBuf;
import litewolf101.aztech.tileentity.TileEntityInsertiveRune;
import litewolf101.aztech.utils.handlers.PacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by LiteWolf101 on Mar
 * /05/2019
 */
public class PacketSendLockedState implements IMessage {
    private boolean messageValid;

    private BlockPos pos;
    private boolean isLocked;
    private String className;
    private String fieldLockedName;


    public PacketSendLockedState() {
        this.messageValid = false;
    }

    public PacketSendLockedState(BlockPos pos, boolean isLocked, String className, String fieldLockedName) {
        this.pos = pos;
        this.isLocked = isLocked;
        this.className = className;
        this.fieldLockedName = fieldLockedName;
        this.messageValid = true;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
            this.isLocked = buf.readBoolean();
            this.className = ByteBufUtils.readUTF8String(buf);
            this.fieldLockedName = ByteBufUtils.readUTF8String(buf);
        } catch (Exception e){
            System.out.println("§dFailiure!: " + e);
            return;
        }
        this.messageValid = true;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (!this.messageValid) {
            return;
        }
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        buf.writeBoolean(isLocked);
        ByteBufUtils.writeUTF8String(buf, this.className);
        ByteBufUtils.writeUTF8String(buf, this.fieldLockedName);
    }

    public static class Handler implements IMessageHandler<PacketSendLockedState, IMessage> {
        @Override
        public IMessage onMessage(PacketSendLockedState message, MessageContext ctx) {
            if (!message.messageValid && ctx.side != Side.SERVER) {
                return null;
            }
            //FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message, s));

            final EntityPlayerMP sendingPlayer = ctx.getServerHandler().player;
            if (sendingPlayer == null) {
                System.err.println("EntityPlayerMP was null when AirstrikeMessageToServer was received");
                return null;
            }

            final WorldServer playerWorldServer = sendingPlayer.getServerWorld();
            playerWorldServer.addScheduledTask(new Runnable() {
                public void run() {
                    processMessage(message, sendingPlayer);
                }
            });
            return null;
        }

        void processMessage(PacketSendLockedState message, EntityPlayerMP sendingPlayer) {
            TileEntity tileEntity = sendingPlayer.getServerWorld().getTileEntity(message.pos);

            if (tileEntity == null) {
                return;
            }

            if (tileEntity instanceof TileEntityInsertiveRune) {
                ((TileEntityInsertiveRune) tileEntity).setLocked(message.isLocked);
            }

            PacketHandler.INSTANCE.sendTo(new PacketReturnLockedState(message.pos, message.isLocked, message.className, message.fieldLockedName), sendingPlayer);
        }
    }
}
