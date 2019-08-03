package litewolf101.aztech.objects.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class ModelPortalConstruct extends ModelBase {

	ModelRenderer base;
	ModelRenderer top;
	ModelRenderer outline;

	public ModelPortalConstruct() {
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-6, 0, -6, 12, 32, 12);

		outline = new ModelRenderer(this, 0, 59);
		outline.addBox(-8, 0, 7, 16, 1, 1);

		ModelRenderer outline2 = new ModelRenderer(this, 0, 59);
		outline2.addBox(-8, 0, -8, 16, 1, 1);
		outline.addChild(outline2);

		ModelRenderer outline3 = new ModelRenderer(this, 0, 44);
		outline3.addBox(-8, 0, -7, 1, 1, 14);
		outline.addChild(outline3);

		ModelRenderer outline4 = new ModelRenderer(this, 0, 44);
		outline4.addBox(7, 0, -7, 1, 1, 14);
		outline.addChild(outline4);

		ModelRenderer stub1 = new ModelRenderer(this, 0, 76);
		stub1.addBox(-8, 1, 7, 1, 1, 1);
		outline.addChild(stub1);

		ModelRenderer stub2 = new ModelRenderer(this, 0, 76);
		stub2.addBox(7, 1, 7, 1, 1, 1);
		outline.addChild(stub2);

		ModelRenderer stub3 = new ModelRenderer(this, 0, 76);
		stub3.addBox(-8, 1, -8, 1, 1, 1);
		outline.addChild(stub3);

		ModelRenderer stub4 = new ModelRenderer(this, 0, 76);
		stub4.addBox(7, 1, -8, 1, 1, 1);
		outline.addChild(stub4);

		top = new ModelRenderer(this, 48, 0);
		top.addBox(-6, 32, -6, 12, 11, 12);

		ModelRenderer shortLine = new ModelRenderer(this, 8, 69);
		shortLine.addBox(-6, 32, 6, 1, 6, 1);
		shortLine.addBox(5, 32, 6, 1, 6, 1);
		shortLine.addBox(-6, 32, -7, 1, 6, 1);
		shortLine.addBox(5, 32, -7, 1, 6, 1);
		shortLine.addBox(-7, 32, 5, 1, 6, 1);
		shortLine.addBox(-7, 32, -6, 1, 6, 1);
		shortLine.addBox(6, 32, 5, 1, 6, 1);
		shortLine.addBox(6, 32, -6, 1, 6, 1);
		top.addChild(shortLine);

		ModelRenderer medLine = new ModelRenderer(this, 4, 67);
		medLine.addBox(-4, 32, 6, 1, 8, 1);
		medLine.addBox(3, 32, 6, 1, 8, 1);
		medLine.addBox(-4, 32, -7, 1, 8, 1);
		medLine.addBox(3, 32, -7, 1, 8, 1);
		medLine.addBox(-7, 32, 3, 1, 8, 1);
		medLine.addBox(-7, 32, -4, 1, 8, 1);
		medLine.addBox(6, 32, 3, 1, 8, 1);
		medLine.addBox(6, 32, -4, 1, 8, 1);
		top.addChild(medLine);

		ModelRenderer bigLine = new ModelRenderer(this, 0, 63);
		bigLine.addBox(1, 32, 6, 1, 12, 1);
		bigLine.addBox(-2, 32, 6, 1, 12, 1);
		bigLine.addBox(1, 32, -7, 1, 12, 1);
		bigLine.addBox(-2, 32, -7, 1, 12, 1);
		bigLine.addBox(-7, 32, 1, 1, 12, 1);
		bigLine.addBox(-7, 32, -2, 1, 12, 1);
		bigLine.addBox(6, 32, 1, 1, 12, 1);
		bigLine.addBox(6, 32, -2, 1, 12, 1);
		top.addChild(bigLine);

		ModelRenderer topLine = new ModelRenderer(this, 0, 61);
		topLine.addBox(-6, 43, 1, 12, 1, 1);
		topLine.addBox(-6, 43, -2, 12, 1, 1);
		top.addChild(topLine);

		ModelRenderer otherTopLine = new ModelRenderer(this, 0, 78);
		otherTopLine.addBox(1, 43, -6, 1, 1, 12);
		otherTopLine.addBox(-2, 43, -6, 1, 1, 12);
		top.addChild(otherTopLine);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 0.7f);
		GlStateManager.pushMatrix();
		base.render(scale);
		GlStateManager.popMatrix();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		outline.render(scale);
		top.render(scale);
	}

	public void renderModel(float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 0.7f);
		GlStateManager.pushMatrix();
		base.render(scale);
		GlStateManager.popMatrix();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		outline.render(scale);
		top.render(scale);
	}

}
