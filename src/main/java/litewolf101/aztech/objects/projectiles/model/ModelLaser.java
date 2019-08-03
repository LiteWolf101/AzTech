package litewolf101.aztech.objects.projectiles.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by LiteWolf101 on 11/2/2018.
 */
public class ModelLaser extends ModelBase {

	public final ModelRenderer laser;

	public ModelLaser() {
		textureWidth = 32;
		textureHeight = 32;

		laser = new ModelRenderer(this, 0, 0);
		laser.addBox(-1f, -1f, -6f, 1, 1, 12);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		//Add GL methods
		laser.render(scale);
	}

}
