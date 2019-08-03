package litewolf101.aztech.utils.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.reflect.Field;

/**
 * Created by LiteWolf101 on Mar /05/2019
 */
public class PacketReturnLockedState implements IMessage {

	private boolean messageValid;

	private BlockPos pos;
	private boolean isLocked;
	private String className;
	private String fieldLockedName;

	public PacketReturnLockedState() {
		this.messageValid = false;
	}

	public PacketReturnLockedState(BlockPos pos, boolean isLocked, String className, String fieldLockedName) {
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
		}
		catch(Exception e) {
			System.out.println("§dFailiure!: " + e);
			return;
		}
		this.messageValid = true;

	}

	@Override
	public void toBytes(ByteBuf buf) {
		if(!this.messageValid) {
			return;
		}
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeBoolean(isLocked);
		ByteBufUtils.writeUTF8String(buf, this.className);
		ByteBufUtils.writeUTF8String(buf, this.fieldLockedName);
	}

	public static class Handler implements IMessageHandler<PacketReturnLockedState, IMessage> {

		@Override
		public IMessage onMessage(PacketReturnLockedState message, MessageContext ctx) {
			if(!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message));
			return null;
		}

		void processMessage(PacketReturnLockedState message) {
			try {
				Class clazz = Class.forName(message.className);
				Field locked = clazz.getDeclaredField(message.fieldLockedName);
				locked.setBoolean(clazz, message.isLocked);
			}
			catch(Exception e) {
				System.err.println(e);
			}
		}

	}

}
