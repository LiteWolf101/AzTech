package litewolf101.aztech.objects.mobs.render;

import litewolf101.aztech.objects.mobs.MobAncientSquid;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by LiteWolf101 on Mar
 * /12/2019
 */
public class RenderAncientSquid extends RenderLiving<MobAncientSquid> {
    private static final ResourceLocation SQUID_TEXTURES = new ResourceLocation("aztech:textures/entity/ancient_squid.png");
    public static final IRenderFactory FACTORY = new RenderAncientSquid.Factory();
    private final ModelSquid modelSquid;

    public RenderAncientSquid(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, new ModelSquid(), 0.7F);
        modelSquid = (ModelSquid) super.mainModel;
    }

    public static class Factory implements IRenderFactory<MobAncientSquid> {

        @Override
        public Render<? super MobAncientSquid> createRenderFor(RenderManager manager) {
            return new RenderAncientSquid(manager, new ModelSquid(), 0.7F);
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(MobAncientSquid entity)
    {
        return SQUID_TEXTURES;
    }

    protected void applyRotations(MobAncientSquid entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        float f = entityLiving.prevSquidPitch + (entityLiving.squidPitch - entityLiving.prevSquidPitch) * partialTicks;
        float f1 = entityLiving.prevSquidYaw + (entityLiving.squidYaw - entityLiving.prevSquidYaw) * partialTicks;
        GlStateManager.translate(0.0F, 0.5F, 0.0F);
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, -1.2F, 0.0F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(MobAncientSquid livingBase, float partialTicks)
    {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }
}
