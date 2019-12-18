package litewolf101.aztech.objects.mobs.render.layer;

import litewolf101.aztech.objects.mobs.MobPyronant;
import litewolf101.aztech.objects.mobs.model.ModelPyronantRing;
import litewolf101.aztech.objects.mobs.render.RenderPyronant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by LiteWolf101 on 11/2/2018.
 */
@SideOnly(Side.CLIENT)
public class LayerPyronantThingies<T extends MobPyronant> implements LayerRenderer<T> {

    private final RenderPyronant<T> renderPyronant;
    private final ModelPyronantRing modelPyronantRing;
    private ResourceLocation pyronantTexture = new ResourceLocation("aztech:textures/entity/pyronant.png");

    public LayerPyronantThingies(RenderPyronant<T> renderPyronant) {
        this.renderPyronant = renderPyronant;
        modelPyronantRing = new ModelPyronantRing();
    }

    @Override
    public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.renderPyronant.bindTexture(pyronantTexture);

        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

        if (entitylivingbaseIn.isInvisible()) {
            GlStateManager.depthMask(false);
        } else {
            GlStateManager.depthMask(true);
        }

        int i = 61680;
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        modelPyronantRing.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        i = entitylivingbaseIn.getBrightnessForRender();
        j = i % 65536;
        k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
        this.renderPyronant.setLightmap(entitylivingbaseIn);
        GlStateManager.enableAlpha();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

}
