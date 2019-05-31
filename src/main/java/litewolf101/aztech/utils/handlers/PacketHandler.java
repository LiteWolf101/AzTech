package litewolf101.aztech.utils.handlers;

import litewolf101.aztech.utils.network.PacketReturnLockedState;
import litewolf101.aztech.utils.network.PacketSendLockedState;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by LiteWolf101 on Mar
 * /05/2019
 */
public class PacketHandler {
    public static SimpleNetworkWrapper INSTANCE;
    private static int ID = 0;

    private static int nextID(){
        return ID++;
    }

    public static void registerMessages(String channelName){
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);

        INSTANCE.registerMessage(PacketSendLockedState.Handler.class, PacketSendLockedState.class, nextID(), Side.SERVER);

        INSTANCE.registerMessage(PacketReturnLockedState.Handler.class, PacketReturnLockedState.class, nextID(), Side.CLIENT);
    }
}
