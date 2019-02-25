package litewolf101.aztech.objects.mobs.render;

import litewolf101.aztech.objects.mobs.MobPyronant;
import litewolf101.aztech.objects.mobs.model.ModelPyronant;
import litewolf101.aztech.objects.mobs.render.layer.LayerPyronantThingies;
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
public class RenderPyronant<T extends MobPyronant> extends RenderLiving<MobPyronant>{
    private ResourceLocation mobTexture = new ResourceLocation("aztech:textures/entity/pyronant.png");
    public static final IRenderFactory FACTORY = new Factory();
    private final ModelPyronant modelPyronant;

    public RenderPyronant(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, new ModelPyronant(), 0.3F);
        modelPyronant = (ModelPyronant) super.mainModel;
        this.addLayer(new LayerPyronantThingies(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(MobPyronant entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<MobPyronant> {

        @Override
        public Render<? super MobPyronant> createRenderFor(RenderManager manager) {
            return new RenderPyronant(manager, new ModelPyronant(), 0.3F);
        }
    }
}
