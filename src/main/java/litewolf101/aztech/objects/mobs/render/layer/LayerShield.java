package litewolf101.aztech.objects.mobs.render.layer;

import litewolf101.aztech.objects.mobs.BossUltimateEye;
import litewolf101.aztech.objects.mobs.model.ModelShield;
import litewolf101.aztech.objects.mobs.render.RenderUltimateEye;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 11/2/2018.
 */
@SideOnly(Side.CLIENT)
public class LayerShield<T extends BossUltimateEye> implements LayerRenderer<T> {
    private ResourceLocation texture = new ResourceLocation("aztech:textures/entity/shield.png");
    private final RenderUltimateEye<T> renderUltimateEye;
    private final ModelShield modelShield;

    public LayerShield(RenderUltimateEye<T> renderUltimateEye)
    {
        this.renderUltimateEye = renderUltimateEye;
        modelShield = new ModelShield();
    }
    @Override
    public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.renderUltimateEye.bindTexture(texture);
        if (entitylivingbaseIn.getInvulnerable()) {
            GlStateManager.scale(1.75f, 1.75f, 1.75f);
            GlStateManager.translate(0, 0.5, 0);
            GlStateManager.rotate(90 * MathHelper.sin(ageInTicks * 0.08f), 0, 0, 1);
            GlStateManager.rotate(90 * MathHelper.cos(ageInTicks * 0.08f), 1, 0, 0);
            GlStateManager.rotate(90 * ageInTicks * 0.04f, 0, 1, 0);
            GlStateManager.translate(0, -0.5, 0);
            modelShield.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
