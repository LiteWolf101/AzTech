package litewolf101.aztech.objects.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by LiteWolf101 on 11/5/2018.
 */
public class ModelMirror extends ModelBase {

    ModelRenderer pane;

    public ModelMirror() {
        textureWidth = 48;
        textureHeight = 48;

        pane = new ModelRenderer(this, 0, 0);
        pane.addBox(-8f, -8f, -0.5f, 16, 16, 1);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        pane.render(scale);
    }

    public void renderModel(float scale) {
        pane.render(scale);
    }

}
