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
    public final ModelRenderer ringSec;

    public ModelEyeMaster() {
        textureWidth = 80;
        textureHeight = 80;

        this.eye = new ModelRenderer(this, 0, 0);
        this.eye.addBox(-8f, -8f, -8f, 16, 16, 16);

        this.ring = new ModelRenderer(this, 0, 32);
        this.ring.addBox(-3f, -18f, -3f, 6, 6, 6);
        ModelRenderer ring2 = new ModelRenderer(this, 0, 56);
        ring2.addBox(-2f, -26f, -2f, 4, 8, 4);
        this.ring.addChild(ring2);

        this.ringSec = new ModelRenderer(this, 0, 68);
        ringSec.addBox(-2f, -16f, -2f, 4, 4, 4);

        this.shield = new ModelRenderer(this, 32, 68);
        this.shield.addBox(-2f, 15f, 28f, 4, 4, 4);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glPushMatrix();
        GL11.glTranslatef(0, MathHelper.sin(ageInTicks * 0.08f - 0.5f) / 8, 0);
        this.eye.rotationPointY = 16;
        this.eye.render(scale);

        this.ring.rotationPointY = 16;
        this.ring.rotateAngleZ = ageInTicks / 10;
        this.ring.render(scale);
        this.ring.rotateAngleZ = ageInTicks / 10 + 90 * ((float) Math.PI / 180);
        this.ring.render(scale);
        this.ring.rotateAngleZ = ageInTicks / 10 + 180 * ((float) Math.PI / 180);
        this.ring.render(scale);
        this.ring.rotateAngleZ = ageInTicks / 10 + 270 * ((float) Math.PI / 180);
        this.ring.render(scale);

        this.ringSec.rotationPointY = 16;
        this.ringSec.rotateAngleZ = ageInTicks / 10 + (45 * ((float) Math.PI / 180));
        this.ringSec.render(scale);
        this.ringSec.rotateAngleZ = ageInTicks / 10 + (135 * ((float) Math.PI / 180));
        this.ringSec.render(scale);
        this.ringSec.rotateAngleZ = ageInTicks / 10 + (225 * ((float) Math.PI / 180));
        this.ringSec.render(scale);
        this.ringSec.rotateAngleZ = ageInTicks / 10 + (315 * ((float) Math.PI / 180));
        this.ringSec.render(scale);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0, MathHelper.sin(ageInTicks * 0.08f + 1) / 4, 0);
        GL11.glRotated(10 * MathHelper.sin(ageInTicks * 0.08f), 0, 0, 1);
        GL11.glRotated(90 * ageInTicks * 0.12, 0, 1, 0);
        this.shield.rotateAngleY = 0;
        this.shield.render(scale);
        this.shield.rotateAngleY = 30 * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 2) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 3) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 4) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 5) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 6) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 7) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 8) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 9) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 10) * ((float) Math.PI / 180);
        this.shield.render(scale);
        this.shield.rotateAngleY = ((360 / 12) * 11) * ((float) Math.PI / 180);
        this.shield.render(scale);
        GL11.glPopMatrix();

    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.eye.rotateAngleY = netHeadYaw * 0.017453292F;
        this.eye.rotateAngleX = headPitch * 0.017453292F;
        MobEyeMaster eyeMaster = (MobEyeMaster) entityIn;

        float i = eyeMaster.getThisAttackTicks()/*ageInTicks % eyeMaster.getMaxAttackTick()*/;
        float j = 0;
        float k = eyeMaster.getMaxAttackWarmupTick();
        //System.out.println(i);

        if (i <= k){
            if (i <= 15) {
                j = (180 / 15) * i;
            } else if (i > 15 && i < k - 15) {
                j = 180;
            } else if (i >= k - 15 && i < k) {
                j = (180 / 15) * (i - k);
            } else {
                j = 0;
            }
        }
        this.eye.rotateAngleX = headPitch * 0.017453292F + j * (float)(Math.PI / 180);
    }
}
