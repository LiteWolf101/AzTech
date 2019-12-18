package litewolf101.aztech.event;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.utils.handlers.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class WorldSaveEvent {

    @SubscribeEvent
    public static void saveAzTechData(WorldEvent.Save event) {
        World world = event.getWorld();
        String gameDirec = FMLClientHandler.instance().getSavesDir().getPath();
        String levelName = world.getWorldInfo().getWorldName();
        int dimID = world.provider.getDimension();
        //String worldName = save + "/DIM" + dimID;
        String rawLocation = gameDirec + File.separator + levelName + File.separator + "aztech_data";
        //File file = new File(rawLocation);
        Path path = Paths.get(rawLocation);
        if (Files.exists(path)) {
            NetworkHandler.createNetworkFile(rawLocation, world.getWorldInfo());
        } else {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
