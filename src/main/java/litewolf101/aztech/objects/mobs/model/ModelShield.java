package litewolf101.aztech.objects.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by LiteWolf101 on 1/10/2019.
 */
public class ModelShield extends ModelBase {

    public final ModelRenderer main;

    public ModelShield() {
        textureWidth = 128;
        textureHeight = 64;

        main = new ModelRenderer(this, 0, 0);
        main.addBox(-16, -8, -16, 32, 32, 32);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        main.render(scale);

    }

}
