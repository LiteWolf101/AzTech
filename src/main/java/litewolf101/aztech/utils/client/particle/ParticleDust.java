package litewolf101.aztech.utils.client.particle;

import litewolf101.aztech.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by LiteWolf101 on 10/29/2018.
 */
@SideOnly(Side.CLIENT)
public class ParticleDust extends Particle {
    float oSize;
    final float rotSpeed;

    public ParticleDust(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.setRBGColorF(1, 1, 1);
        this.particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation(Reference.MODID, "particle/dust").toString());
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        float f = 0.9F;
        this.particleScale *= 0.75F;
        this.particleScale *= 0.9F;
        this.oSize = this.particleScale;
        this.particleMaxAge = (int)(32.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int)((float)this.particleMaxAge * 0.9F);
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.particleAngle = (float)Math.random() * ((float)Math.PI * 2F);
    }

    public int getFXLayer(){
        return 1;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        this.particleScale = this.oSize * f;
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }

        this.prevParticleAngle = this.particleAngle;
        this.particleAngle += (float)Math.PI * this.rotSpeed * 2.0F;

        if (this.onGround)
        {
            this.prevParticleAngle = this.particleAngle = 0.0F;
        }

        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionY -= 0.003000000026077032D;
        this.motionY = Math.max(this.motionY, -0.14000000059604645D);
    }
}