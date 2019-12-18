package litewolf101.aztech.objects.mobs.render;

import litewolf101.aztech.objects.mobs.MobEyeGuardian;
import litewolf101.aztech.objects.mobs.model.ModelEyeGuardian;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on 10/26/2018.
 */
public class RenderEyeGuardian extends RenderLiving<MobEyeGuardian> {

    public static final IRenderFactory FACTORY = new Factory();
    private final ModelEyeGuardian modelEyeGuardian;
    private ResourceLocation mobTexture = new ResourceLocation("aztech:textures/entity/eye_guardian.png");

    public RenderEyeGuardian(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, new ModelEyeGuardian(), 0.3F);
        modelEyeGuardian = (ModelEyeGuardian) super.mainModel;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(MobEyeGuardian entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<MobEyeGuardian> {

        @Override
        public Render<? super MobEyeGuardian> createRenderFor(RenderManager manager) {
            return new RenderEyeGuardian(manager, new ModelEyeGuardian(), 0.3F);
        }

    }

}
