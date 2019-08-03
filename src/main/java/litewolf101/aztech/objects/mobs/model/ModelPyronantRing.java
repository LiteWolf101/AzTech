package litewolf101.aztech.objects.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 11/2/2018.
 */
public class ModelPyronantRing extends ModelBase {

	public final ModelRenderer ring;

	public ModelPyronantRing() {
		textureWidth = 64;
		textureHeight = 64;

		this.ring = new ModelRenderer(this, 32, 36);
		this.ring.addBox(-1f, -16f, -18f, 2, 12, 2);
		this.ring.rotateAngleX = (float)(45 * (Math.PI / 180));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GL11.glPushMatrix();
		GL11.glRotated(90 * ageInTicks * 0.08, 0, 1, 0);
		GL11.glTranslatef(0, 0.1f * MathHelper.cos(ageInTicks * 0.1f) + 0.5f, 0);
		this.ring.rotateAngleY = (float)(0 * (Math.PI / 180));
		this.ring.render(scale);
		this.ring.rotateAngleY = (float)(45 * (Math.PI / 180));
		this.ring.render(scale);
		this.ring.rotateAngleY = (float)(90 * (Math.PI / 180));
		this.ring.render(scale);
		this.ring.rotateAngleY = (float)(135 * (Math.PI / 180));
		this.ring.render(scale);
		this.ring.rotateAngleY = (float)(180 * (Math.PI / 180));
		this.ring.render(scale);
		this.ring.rotateAngleY = (float)(225 * (Math.PI / 180));
		this.ring.render(scale);
		this.ring.rotateAngleY = (float)(270 * (Math.PI / 180));
		this.ring.render(scale);
		this.ring.rotateAngleY = (float)(315 * (Math.PI / 180));
		this.ring.render(scale);
		GL11.glPopMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
	}

}
