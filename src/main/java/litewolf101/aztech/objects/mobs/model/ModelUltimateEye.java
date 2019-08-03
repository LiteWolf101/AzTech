package litewolf101.aztech.objects.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 1/10/2019.
 */
public class ModelUltimateEye extends ModelBase {

	public final ModelRenderer eye;
	public final ModelRenderer minieye;
	public final ModelRenderer ring;
	public final ModelRenderer ring2;

	public ModelUltimateEye() {
		textureWidth = 128;
		textureHeight = 128;

		this.eye = new ModelRenderer(this, 0, 0);
		this.eye.addBox(-16, -8, -16, 32, 32, 32);

		this.minieye = new ModelRenderer(this, 0, 64);
		this.minieye.addBox(-4, -46, -4, 8, 8, 8);

		this.ring = new ModelRenderer(this, 32, 64);
		this.ring.addBox(-2, -66, -2, 4, 20, 4);

		ModelRenderer ringside = new ModelRenderer(this, 0, 88);
		ringside.addBox(-4, -60, -1, 2, 14, 2);
		ringside.addBox(2, -60, -1, 2, 14, 2);
		this.ring.addChild(ringside);

		ring2 = new ModelRenderer(this, 48, 64);
		ring2.addBox(-3, -48, -3, 6, 6, 6);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		eye.render(scale);

		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0.5f, 1f);
		GL11.glRotated(ageInTicks * 4, 0, 0, 1);
		minieye.rotateAngleX = 120;
		minieye.rotateAngleZ = 0;
		minieye.render(scale);
		minieye.rotateAngleZ = (72 * 1) * ((float)Math.PI / 180);
		minieye.render(scale);
		minieye.rotateAngleZ = (72 * 2) * ((float)Math.PI / 180);
		minieye.render(scale);
		minieye.rotateAngleZ = (72 * 3) * ((float)Math.PI / 180);
		minieye.render(scale);
		minieye.rotateAngleZ = (72 * 4) * ((float)Math.PI / 180);
		minieye.render(scale);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0.5f, 1f);
		GL11.glRotated(ageInTicks * 4, 0, 0, 1);
		ring2.rotateAngleX = 120;
		ring2.rotateAngleZ = 36 * ((float)Math.PI / 180);
		ring2.render(scale);
		ring2.rotateAngleZ = (72 * 1 + 36) * ((float)Math.PI / 180);
		ring2.render(scale);
		ring2.rotateAngleZ = (72 * 2 + 36) * ((float)Math.PI / 180);
		ring2.render(scale);
		ring2.rotateAngleZ = (72 * 3 + 36) * ((float)Math.PI / 180);
		ring2.render(scale);
		ring2.rotateAngleZ = (72 * 4 + 36) * ((float)Math.PI / 180);
		ring2.render(scale);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0.5f, 0);
		GL11.glRotated(90, 1, 0, 0);
		GL11.glRotated(10 * MathHelper.sin(ageInTicks * 0.08f), 1, 0, 0);
		GL11.glRotated(10 * MathHelper.cos(ageInTicks * 0.08f), 0, 1, 0);
		GL11.glRotated(ageInTicks * 8, 0, 0, 1);
		ring.rotateAngleZ = 0;
		ring.render(scale);
		ring.rotateAngleZ = (36 * 1) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 2) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 3) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 4) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 5) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 6) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 7) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 8) * ((float)Math.PI / 180);
		ring.render(scale);
		ring.rotateAngleZ = (36 * 9) * ((float)Math.PI / 180);
		ring.render(scale);
		GL11.glPopMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.eye.rotateAngleY = netHeadYaw * 0.017453292F;
		this.eye.rotateAngleX = headPitch * 0.017453292F;
	}

}
