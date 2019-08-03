package litewolf101.aztech.utils.client.particle;

import litewolf101.aztech.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by LiteWolf101 on 11/3/2018.
 */
public class ParticleRedSparkle extends Particle {

	public ParticleRedSparkle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.setRBGColorF(1, 0, 0);
		this.particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation(Reference.MODID, "particle/sparkle").toString());
		this.motionX = 0;
		this.motionY = 0.01d;
		this.motionZ = 0;
		this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
		this.particleMaxAge = 50 + this.rand.nextInt(10);
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	public int getFXLayer() {
		return 1;
	}

}
