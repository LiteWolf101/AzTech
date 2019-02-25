package litewolf101.aztech.objects.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by LiteWolf101 on 9/29/2018.
 */
public class ModelRuneCore extends ModelBase{
    ModelRenderer base;

    public ModelRuneCore(){
        textureHeight = 16;
        textureWidth = 16;

        base = new ModelRenderer(this, 0, 0);
        base.addBox(0, 0, 0,16, 16, 16);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        base.render(scale);
    }

    public void renderModel(float scale){
        base.render(scale);
    }
}
