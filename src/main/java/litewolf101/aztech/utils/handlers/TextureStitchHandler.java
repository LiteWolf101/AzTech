package litewolf101.aztech.utils.handlers;

import litewolf101.aztech.utils.Reference;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by LiteWolf101 on 10/29/2018.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Reference.MODID, value = Side.CLIENT)
public class TextureStitchHandler {
    @SubscribeEvent
    public static void pre (TextureStitchEvent.Pre event){
        TextureMap map = event.getMap();
        map.registerSprite(new ResourceLocation(Reference.MODID, "particle/eye_master_particle"));
        map.registerSprite(new ResourceLocation(Reference.MODID, "particle/enemy_link_particle"));
        map.registerSprite(new ResourceLocation(Reference.MODID, "particle/sparkle"));
    }
}