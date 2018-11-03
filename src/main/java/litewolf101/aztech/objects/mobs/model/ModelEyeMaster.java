package litewolf101.aztech.objects.mobs.model;

import litewolf101.aztech.objects.mobs.MobEyeMaster;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 10/29/2018.
 */
public class ModelEyeMaster extends ModelBase {

    public final ModelRenderer eye;
    public final ModelRenderer shield;
    public final ModelRenderer ring;

    public ModelEyeMaster() {
        textureWidth = 80;
        textureHeight = 80;

        this.eye = new ModelRenderer(this, 0, 0);
        this.eye.addBox(-8f, -8f, -8f, 16, 16, 16);

        this.ring = new ModelRenderer(this, 0, 32);
        this.ring.addBox(-3f, -18f, -3f, 6, 6, 6);
        this.ring.addBox(12f, -2f, -3f, 6, 6, 6);
        this.ring.addBox(-3f, 12f, -3f, 6, 6, 6);
        this.ring.addBox(-18f, -2f, -3f, 6, 6, 6);

        ModelRenderer ring2 = new ModelRenderer(this, 0, 56);
        ring2.addBox(-2f, -26f, -2f, 4, 8, 4);
        this.ring.addChild(ring2);

        ModelRenderer ring3 = new ModelRenderer(this, 24, 44);
        ring3.addBox(18f, -1f, -2f, 8, 4, 4);
        this.ring.addChild(ring3);

        ModelRenderer ring4 = new ModelRenderer(this, 16, 56);
        ring4.addBox(-2f, 18f, -2f, 4, 8, 4);
        this.ring.addChild(ring4);

        ModelRenderer ring5 = new ModelRenderer(this, 24, 44);
        ring5.addBox(-26f, -1f, -2f, 8, 4, 4);
        this.ring.addChild(ring5);

        ModelRenderer ring6 = new ModelRenderer(this, 0, 68);
        ring6.addBox(-2f, -16f, -2f, 4, 4, 4);
        ring6.addBox(12f, -1f, -2f, 4, 4, 4);
        ring6.addBox(-2f, 12f, -2f, 4, 4, 4);
        ring6.addBox(-16f, -1f, -2f, 4, 4, 4);
        ring6.rotateAngleZ = 0.8f;
        this.ring.addChild(ring6);

        this.shield = new ModelRenderer(this, 32, 68);
        this.shield.addBox(-2f, -1f, 28f, 4, 4, 4);


    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glPushMatrix();
        GL11.glTranslatef(0, MathHelper.sin(ageInTicks*0.08f - 0.5f)/8, 0);
        this.eye.render(scale);
        this.ring.render(scale);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0, MathHelper.sin(ageInTicks*0.08f + 1)/4, 0);
        GL11.glRotated(10* MathHelper.sin(ageInTicks*0.08f), 0, 0, 1);
        GL11.glRotated(90*ageInTicks*0.12, 0, 1, 0);
        this.shield.rotateAngleY = 0;
        this.shield.render(scale);
        this.shield.rotateAngleY = 30 * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 2) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 3) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 4) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 5) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 6) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 7) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 8) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 9) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 10) * ((float) Math.PI/180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360/12)* 11) * ((float) Math.PI/180);
        this.shield.render(scale);
        GL11.glPopMatrix();

    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.ring.rotateAngleZ = ageInTicks/5;

        this.eye.rotateAngleY = netHeadYaw * 0.017453292F;
        this.eye.rotateAngleX = headPitch * 0.017453292F;

        //MobEyeMaster eyeMaster = (MobEyeMaster) entityIn;
        //if (eyeMaster.isDoingAttack) {
        //    this.eye.rotateAngleX = 180 * (float)(Math.PI / 180);
        //}
        //if (!eyeMaster.isDoingAttack) {
        //    this.eye.rotateAngleX = 0;
        //} Fix
    }
}
