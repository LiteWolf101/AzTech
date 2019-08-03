package litewolf101.aztech.objects.mobs.render;

import litewolf101.aztech.objects.mobs.MobEyeMaster;
import litewolf101.aztech.objects.mobs.model.ModelEyeMaster;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on 10/27/2018.
 */
public class RenderEyeMaster extends RenderLiving<MobEyeMaster> {

	public static final IRenderFactory FACTORY = new Factory();
	private final ModelEyeMaster modelEyeMaster;
	private ResourceLocation mobTexture = new ResourceLocation("aztech:textures/entity/eye_master.png");

	public RenderEyeMaster(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, new ModelEyeMaster(), 0.3F);
		modelEyeMaster = (ModelEyeMaster)super.mainModel;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(MobEyeMaster entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<MobEyeMaster> {

		@Override
		public Render<? super MobEyeMaster> createRenderFor(RenderManager manager) {
			return new RenderEyeMaster(manager, new ModelEyeMaster(), 0.7F);
		}

	}

}
