package litewolf101.aztech.utils.client.particle;

import litewolf101.aztech.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by LiteWolf101 on 10/29/2018.
 */
public class ParticleEnemyLink extends Particle {

    public ParticleEnemyLink(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.setRBGColorF(1, 1, 1);
        this.particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation(Reference.MODID, "particle/enemy_link_particle").toString());
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;

        this.particleScale = 1f;
    }

    public int getFXLayer(){
        return 1;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
}