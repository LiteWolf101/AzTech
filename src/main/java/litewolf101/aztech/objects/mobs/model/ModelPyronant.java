package litewolf101.aztech.objects.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 11/1/2018.
 */
public class ModelPyronant extends ModelBase {
    //Some people ask me why I do this. It's for organization reasons. These visually separate the main
    //pieces from their children.
    public final ModelRenderer head;
    public final ModelRenderer body;
    public final ModelRenderer leftArm;
    public final ModelRenderer rightArm;
    public final ModelRenderer leftLeg;
    public final ModelRenderer rightLeg;

    public ModelPyronant(){
        textureWidth = 64;
        textureHeight = 64;


        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4f, -8f, -4f, 8, 8, 8);

        body = new ModelRenderer(this, 32, 0);
        body.addBox(-4f, 0f, -2f, 8, 12, 4);

        leftArm = new ModelRenderer(this, 0, 16);
        leftArm.addBox(-8f, 0f, -2f, 4, 6, 4);
        ModelRenderer leftArmLower = new ModelRenderer(this, 16, 16);
        leftArmLower.addBox(-8f, 5f, 0f, 4, 6, 4);
        leftArmLower.rotateAngleX = -(float)(20 * (Math.PI/180));
        leftArm.addChild(leftArmLower);

        rightArm = new ModelRenderer(this, 32, 16);
        rightArm.addBox(4f, 0f, -2f, 4, 6, 4);
        ModelRenderer rightArmLower = new ModelRenderer(this, 0, 26);
        rightArmLower.addBox(4f, 5f, 0f, 4, 6, 4);
        rightArmLower.rotateAngleX = -(float)(20 * (Math.PI/180));
        rightArm.addChild(rightArmLower);

        leftLeg = new ModelRenderer(this, 16, 26);
        leftLeg.addBox(-4f, 12f, 0f, 4, 6, 4);
        ModelRenderer leftLegLower = new ModelRenderer(this, 32, 26);
        leftLegLower.addBox(-4f, 17f, -6f, 4, 6, 4);
        leftLegLower.rotateAngleX = (float)(20 * (Math.PI/180));
        leftLeg.addChild(leftLegLower);

        rightLeg = new ModelRenderer(this, 16, 26);
        rightLeg.addBox(0f, 8f, 6f, 4, 6, 4);
        ModelRenderer rightLegLower = new ModelRenderer(this, 32, 26);
        rightLegLower.addBox(0f, 14f, -7f, 4, 6, 4);
        rightLegLower.rotateAngleX = (float)(50 * (Math.PI/180));
        rightLeg.addChild(rightLegLower);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0.1f*MathHelper.sin(ageInTicks*0.1f)-0.3f, 0);
        head.render(scale);
        body.render(scale);
        leftArm.render(scale);
        rightArm.render(scale);
        leftLeg.render(scale);
        rightLeg.render(scale);
        GL11.glPopMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        head.rotateAngleY = netHeadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;

        leftArm.rotateAngleX = -(float)(5 * (Math.PI/180));
        leftArm.rotateAngleY = (float)(5 * (Math.PI/180));
        leftArm.rotateAngleZ = (float)(1.5f*MathHelper.sin(ageInTicks*0.1f) * (Math.PI/180));

        rightArm.rotateAngleX = -(float)(5 * (Math.PI/180));
        rightArm.rotateAngleY = -(float)(5 * (Math.PI/180));
        rightArm.rotateAngleZ = -(float)(1.5f*MathHelper.sin(ageInTicks*0.1f) * (Math.PI/180));

        leftLeg.rotateAngleX = -(float)(10 * (Math.PI/180));
        leftLeg.rotateAngleY = (float)(10 * (Math.PI/180));

        rightLeg.rotateAngleX = -(float)(45 * (Math.PI/180));
        rightLeg.rotateAngleY = -(float)(10 * (Math.PI/180));
    }

}
