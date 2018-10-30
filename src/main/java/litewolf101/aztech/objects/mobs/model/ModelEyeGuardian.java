package litewolf101.aztech.objects.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 10/26/2018.
 */
public class ModelEyeGuardian extends ModelBase {

    public final ModelRenderer eye;
    public final ModelRenderer shield;
    public final ModelRenderer ring;

    public ModelEyeGuardian() {
        textureWidth = 160;
        textureHeight = 160;

        //main body
        this.eye = new ModelRenderer(this, 0, 0);
        this.eye.addBox(-5.5f, -5.5f, -5.5f, 11, 11, 11);

        //shield
        this.shield = new ModelRenderer(this, 44, 0);
        this.shield.addBox(8.5f, -9.5f, 8.5f, 1, 19, 1);
        this.shield.addBox(8.5f, -9.5f, -9.5f, 1, 19, 1);
        this.shield.addBox(-9.5f, -9.5f, -9.5f, 1, 19, 1);
        this.shield.addBox(-9.5f, -9.5f, 8.5f, 1, 19, 1);

        ModelRenderer shield1 = new ModelRenderer(this, 4, 22);
        shield1.addBox(-9.5f, -9.5f, -9.5f, 1, 1, 19);
        shield1.addBox(8.5f, -9.5f, -9.5f, 1, 1, 19);
        shield1.addBox(8.5f, 9.5f, -9.5f, 1, 1, 19);
        shield1.addBox(-9.5f, 9.5f, -9.5f, 1, 1, 19);
        this.shield.addChild(shield1);

        ModelRenderer shield2 = new ModelRenderer(this, 84, 22);
        shield2.addBox(-9.5f, -9.5f, 8.5f, 19, 1, 1);
        shield2.addBox(-9.5f, -9.5f, -9.5f, 19, 1, 1);
        shield2.addBox(-9.5f, 9.5f, -9.5f, 19, 1, 1);
        shield2.addBox(-9.5f, 9.5f, 8.5f, 19, 1, 1);
        this.shield.addChild(shield2);

        //ring
        this.ring = new ModelRenderer(this, 0, 64);
        this.ring.addBox(-1.5f, -19.5f, -1.5f, 3, 3, 3);
        this.ring.addBox(-19.5f, -1.5f, -1.5f, 3, 3, 3);
        this.ring.addBox(-1.5f, 16.5f, -1.5f, 3, 3, 3);
        this.ring.addBox(16.5f, -1.5f, -1.5f, 3, 3, 3);

        ModelRenderer ring2 = new ModelRenderer(this, 52, 62);
        ring2.addBox(16f, -2f, -2f, 4, 4, 4);
        ring2.addBox(-2f, -20f, -2f, 4, 4, 4);
        ring2.addBox(-20f, -2f, -2f, 4, 4, 4);
        ring2.addBox(-2f, 16f, -2f, 4, 4, 4);
        ring2.rotateAngleZ = 0.8f;
        this.ring.addChild(ring2);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glPushMatrix();
        GL11.glTranslatef(0, MathHelper.sin(ageInTicks*0.08f - 0.5f)/8, 0);
        this.eye.render(scale);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glRotated(90*ageInTicks*0.1, 0, 1, 0);
        GL11.glRotated(90*ageInTicks*0.1, 1, 0, 0);
        this.shield.render(scale);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glRotated(90*ageInTicks*0.1, 0, 0, 1);
        this.ring.render(scale);
        GL11.glPopMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        this.eye.rotateAngleY = netHeadYaw * 0.017453292F;
        this.eye.rotateAngleX = headPitch * 0.017453292F;
    }
}
