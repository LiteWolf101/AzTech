package litewolf101.aztech.objects.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDustDevil extends ModelBase {

    ModelRenderer block;

    public ModelDustDevil() {
        textureWidth = 64;
        textureHeight = 32;

        block = new ModelRenderer(this, 0, 0);
        block.addBox(-37, 0, 8, 16, 16, 16);
        block.addBox(26, 16, -14, 16, 16, 16);
        block.addBox(-13, 36, 26, 16, 16, 16);
        block.addBox(9, 70, -27, 16, 16, 16);
        block.addBox(0, 82, 25, 16, 16, 16);
        block.addBox(37, 55, -21, 16, 16, 16);

    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        block.render(scale);
    }

    public void renderModel(float scale) {
        block.render(scale);
    }

}
