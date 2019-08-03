package litewolf101.aztech.objects.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class BaseFlatTile extends ModelBase {

	ModelRenderer base;

	public BaseFlatTile() {
		textureWidth = 64;
		textureHeight = 64;
		base = new ModelRenderer(this, 0, 0);
		base.addBox(0, 0, 0, 16, 2, 16);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		base.render(scale);
	}

	public void renderModel(float scale) {
		base.render(scale);
	}

}
