package litewolf101.aztech.objects.mobs.render;

import litewolf101.aztech.objects.mobs.MobSpark;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on Mar /12/2019
 */
public class RenderSpark extends Render<MobSpark> {

	public static final IRenderFactory FACTORY = new RenderSpark.Factory();
	private float lbx = 0f;
	private float lby = 0f;
	private ResourceLocation texture1 = new ResourceLocation("aztech:textures/entity/spark_1.png");
	private ResourceLocation texture2 = new ResourceLocation("aztech:textures/entity/spark_2.png");
	private ResourceLocation texture3 = new ResourceLocation("aztech:textures/entity/spark_3.png");
	private ResourceLocation texture4 = new ResourceLocation("aztech:textures/entity/spark_4.png");

	public RenderSpark(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(MobSpark entity, double x, double y, double z, float entityYaw, float partialTicks) {
		int tick = entity.ticksExisted % 40;
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		lbx = OpenGlHelper.lastBrightnessX;
		lby = OpenGlHelper.lastBrightnessY;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 220f, 220f);
		if(tick == 17) {
			this.bindTexture(texture2);
		}
		else if(tick == 25) {
			this.bindTexture(texture3);
		}
		else if(tick == 33) {
			this.bindTexture(texture4);
		}
		else {
			this.bindTexture(texture1);
		}
		GlStateManager.translate((float)x, (float)y, (float)z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(1, 1, 1);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		float f = 1.0F;
		float f1 = 0.5F;
		float f2 = 0.25F;
		GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

		if(this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
		bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		tessellator.draw();

		if(this.renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		OpenGlHelper.lastBrightnessX = lbx;
		OpenGlHelper.lastBrightnessY = lby;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY);
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(MobSpark entity) {
		return texture1;
	}

	public static class Factory implements IRenderFactory<MobSpark> {

		@Override
		public Render<? super MobSpark> createRenderFor(RenderManager manager) {
			return new RenderSpark(manager);
		}

	}

}
