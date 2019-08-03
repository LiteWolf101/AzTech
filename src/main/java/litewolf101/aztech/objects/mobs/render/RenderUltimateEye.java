package litewolf101.aztech.objects.mobs.render;

import litewolf101.aztech.objects.mobs.BossUltimateEye;
import litewolf101.aztech.objects.mobs.model.ModelUltimateEye;
import litewolf101.aztech.objects.mobs.render.layer.LayerShield;
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
public class RenderUltimateEye<T extends BossUltimateEye> extends RenderLiving<BossUltimateEye> {

	public static final IRenderFactory FACTORY = new Factory();
	private final ModelUltimateEye modelUltimateEye;
	private ResourceLocation mobTexture = new ResourceLocation("aztech:textures/entity/ultimate_eye.png");

	public RenderUltimateEye(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, new ModelUltimateEye(), 0.7F);
		modelUltimateEye = (ModelUltimateEye)super.mainModel;
		this.addLayer(new LayerShield<>(this));
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(BossUltimateEye entity) {
		return mobTexture;
	}

	public static class Factory implements IRenderFactory<BossUltimateEye> {

		@Override
		public Render<? super BossUltimateEye> createRenderFor(RenderManager manager) {
			return new RenderUltimateEye(manager, new ModelUltimateEye(), 0.7F);
		}

	}

}
