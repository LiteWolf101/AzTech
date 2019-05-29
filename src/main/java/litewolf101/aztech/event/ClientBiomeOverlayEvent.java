package litewolf101.aztech.event;

import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.world.biome.AztechBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;


/**
 * Created by LiteWolf101 on Mar
 * /09/2019
 */
//@Mod.EventBusSubscriber(modid = Reference.MODID, value = Side.CLIENT)
public class ClientBiomeOverlayEvent {
    /**
     * This is just used to tick the fade-in for our overlay
     */
    static int alph;

    //@SubscribeEvent
    public static void onBiomeEnter(RenderGameOverlayEvent.Post event) {
        /**
         * Limit our int to anywhere between 0 and 5000
         */
        int alphcheck = Math.min(alph, 5000);

        /**
         * @param playerPos gets the position of the player
         * @param biome gets the biome at that position
         */
        BlockPos playerPos = Minecraft.getMinecraft().player.getPosition();
        Class biome = Minecraft.getMinecraft().player.getEntityWorld().getBiome(playerPos).getClass();

        /**
         * if the biome at the position matches the one we're looking for, do stuff
         */
        if (biome == AztechBiomes.biomeAncientForest.getClass()) {
            if (alph < 5000) {
                alph++;
                alph++;
            }
        }
        if (alph > 0) {
            alph--;
        }

        /**
         * This renders our overlay onto the screen.
         * @param alph is used to handle the fade-in/fade-out of our texture
         */
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MODID,"textures/blocks/temple_mirror_inner.png"));
        GlStateManager.color(0.2f, 1, 1, (float) alphcheck / 25000);
        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(0, 0, 0, 0, Minecraft.getMinecraft().displayWidth/4, Minecraft.getMinecraft().displayHeight/4);
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }
}
