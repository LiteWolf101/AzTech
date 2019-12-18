package litewolf101.aztech.objects.mobs.render;

import litewolf101.aztech.objects.entitymisc.EntityDustDevil;
import litewolf101.aztech.objects.mobs.model.ModelDustDevil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderDustDevil extends Render<EntityDustDevil> {

    public static final IRenderFactory FACTORY = new RenderDustDevil.Factory();
    private final ModelDustDevil modelDustDevil = new ModelDustDevil();
    private ResourceLocation texture = new ResourceLocation("aztech:textures/blocks/dust_sand.png");

    public RenderDustDevil(RenderManager manager, ModelBase modelBase) {
        super(manager);
    }

    /*@Override
    public void doRender(EntityDustDevil entity, double x, double y, double z, float entityYaw, float partialTicks) {
        long angle = (System.currentTimeMillis() / 2) % 360;
        GlStateManager.pushMatrix();
        this.bindTexture(texture);
        GlStateManager.translate((float) x, (float) y + 0.5, (float) z);
        GlStateManager.rotate(angle, 0f, 2f, 0f);
        this.modelDustDevil.renderModel(0.01f);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

    }*/

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDustDevil entity) {
        return texture;
    }

    public static class Factory implements IRenderFactory<EntityDustDevil> {

        @Override
        public Render<? super EntityDustDevil> createRenderFor(RenderManager manager) {
            return new RenderDustDevil(manager, new ModelDustDevil());
        }

    }

}
