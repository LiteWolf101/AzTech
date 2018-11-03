package litewolf101.aztech.objects.projectiles.render;

import litewolf101.aztech.objects.mobs.render.RenderEyeGuardian;
import litewolf101.aztech.objects.projectiles.ProjectileEyeLaser;
import litewolf101.aztech.objects.projectiles.model.ModelLaser;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on 11/2/2018.
 */
public class RenderEyeLaser extends Render<ProjectileEyeLaser>{
    private ResourceLocation texture = new ResourceLocation("aztech:textures/entity/eye_laser.png");
    public static final IRenderFactory FACTORY = new Factory();
    private final ModelLaser modelLaser = new ModelLaser();

    public RenderEyeLaser(RenderManager renderManager, ModelBase modelBaseIn) {
        super(renderManager);
    }

    @Override
    public void doRender(ProjectileEyeLaser entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float) x, (float) y + 0.5, (float) z);
        GlStateManager.rotate(entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        //GlStateManager.scale(2, 2, 2);
        this.modelLaser.render(entity, 0f, 0.0F, 0.0F, entity.rotationYaw, entity.rotationPitch, 0.09125F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(ProjectileEyeLaser entity) {
        return texture;
    }

    public static class Factory implements IRenderFactory<ProjectileEyeLaser> {

        @Override
        public Render<? super ProjectileEyeLaser> createRenderFor(RenderManager manager) {
            return new RenderEyeLaser(manager, new ModelLaser());
        }
    }
}
