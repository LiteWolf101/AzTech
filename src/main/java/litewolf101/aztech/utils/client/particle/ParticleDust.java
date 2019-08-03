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

	final float rotSpeed;
	float oSize;
	long time = ((System.currentTimeMillis() / 10) % 10);
	float bendDiv = ((float)time) / 10;
	float pi = (float)Math.PI;
	float xBend = 0.02f * MathHelper.sin(bendDiv * (4 * pi));
	float zBend = 0.02f * MathHelper.cos(bendDiv * (2 * pi));

	public ParticleDust(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.setRBGColorF(1, 1, 1);
		this.particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation(Reference.MODID, "particle/dust").toString());
		this.motionX = 0.0D + xBend + xSpeedIn;
		this.motionY = 0.0D + ySpeedIn;
		this.motionZ = 0.0D + zBend + zSpeedIn;
		float f = 0.9F;
		this.particleScale *= 1.1F;
		this.particleScale *= 0.9F;
		this.oSize = this.particleScale;
		this.particleMaxAge = (int)(26.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * 0.9F);
		this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
		this.particleAngle = (float)Math.random() * ((float)Math.PI * 2F);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if(this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}

		this.prevParticleAngle = this.particleAngle;
		this.particleAngle += (float)Math.PI * this.rotSpeed * 2.0F;

		if(this.onGround) {
			this.prevParticleAngle = this.particleAngle = 0.0F;
		}

		this.move(this.motionX, this.motionY, this.motionZ);
		this.motionY += 0.002000000026077032D;
		this.motionY = Math.max(this.motionY, -0.35000000059604645D);
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		this.particleScale = this.oSize * f;
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	public int getFXLayer() {
		return 1;
	}

}