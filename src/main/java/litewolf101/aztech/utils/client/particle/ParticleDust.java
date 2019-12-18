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
	private final double portalPosX;
	private final double portalPosY;
	private final double portalPosZ;
	private final float portalParticleScale;

	public ParticleDust(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.setRBGColorF(1, 1, 1);
		this.particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation(Reference.MODID, "particle/dust").toString());
		this.motionX = xSpeedIn;
		this.motionY = ySpeedIn;
		this.motionZ = zSpeedIn;
		this.posX = xCoordIn;
		this.posY = yCoordIn;
		this.posZ = zCoordIn;
		this.portalPosX = this.posX;
		this.portalPosY = this.posY;
		this.portalPosZ = this.posZ;
		float f = this.rand.nextFloat() * 0.6F + 0.4F;
		this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
		this.portalParticleScale = this.particleScale;
		this.particleRed = f * 0.9F;
		this.particleGreen = f * 0.3F;
		this.particleBlue = f;
		this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		float f = (float)this.particleAge / (float)this.particleMaxAge;
		float f1 = -f + f * f * 2.0F;
		float f2 = 1.0F - f1;
		this.posX = this.portalPosX + this.motionX * (double)f2;
		this.posY = this.portalPosY + this.motionY * (double)f2 + (double)(1.0F - f);
		this.posZ = this.portalPosZ + this.motionZ * (double)f2;

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setExpired();
		}
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge;
		f = 3.0F - f;
		f = f * f;
		f = 3.0F - f;
		this.particleScale = this.portalParticleScale * f;
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	public int getFXLayer() {
		return 1;
	}

}