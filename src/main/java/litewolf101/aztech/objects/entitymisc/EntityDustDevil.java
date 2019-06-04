package litewolf101.aztech.objects.entitymisc;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityDustDevil extends Entity {
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    int tickLife = 0;

    public EntityDustDevil(World worldIn) {
        super(worldIn);
        this.setEntityInvulnerable(true);
        this.setSize(2, 3);
    }

    public EntityDustDevil (World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn);
        this.setSize(1, 1);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        double d0 = (double) MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    @Override
    public void onUpdate() {
        this.tickLife++;

        if (tickLife > 200) {
            if (rand.nextInt(100) == 0) {
                setDead();
            }
        }
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        if (rand.nextInt(50) == 0) {
            this.motionX = (float) (rand.nextInt(20) - 10) / 100;
            this.motionZ = (float) (rand.nextInt(20) - 10) / 100;
        }

        AzTech.proxy.spawnParticle(world, AzTechParticleTypes.SAND_DUST, posX, posY + 0.1, posZ, this.motionX, 0, this.motionZ);
        this.createRunningParticles();


        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (world.getBlockState(new BlockPos(this.posX + x, this.posY + y, this.posZ + z)).isFullBlock()) {
                        setDead();
                    }
                }
            }
        }

        if (rand.nextInt(50) == 0) {
            world.playSound(null, this.getPosition(), AzTechSoundHandler.ENTITY_DUST_DEVIL_WIND_1, SoundCategory.NEUTRAL, 1f, (float) (rand.nextInt(5)/10) + 1f);
        }

        AxisAlignedBB aabb = new AxisAlignedBB(this.getPosition(), this.getPosition().add(1, 1, 1)).grow(5D, 5D, 5D);
        List<Entity> nearbyEntities = world.getEntitiesWithinAABB(Entity.class, aabb);
        for (Entity entity : nearbyEntities) {
            if (this.rand.nextInt(2) == 0) {
                if (!(entity instanceof EntityDustDevil)) {
                    if (entity instanceof EntityPlayer) {
                        if (!(((EntityPlayer) entity).isCreative() | ((EntityPlayer) entity).isSpectator())) {
                            pullEntity(entity);
                        }
                    } else if (entity instanceof EntityLiving){
                        pullEntity(entity);
                    }
                }
            }
        }
    }

    void pullEntity (Entity entity) {
        BlockPos entityPos = entity.getPosition();
        Random rand = new Random();
        float moveFactor = (float) 0.07;
        if (entityPos.getX() > this.posX) {
            entity.motionX -= moveFactor;
        } else if (entityPos.getX() < this.posX) {
            entity.motionX += moveFactor;
        }
        if (entityPos.getY() > this.posY + 2) {
            entity.motionY -= moveFactor;
        } else if (entityPos.getY() < this.posY + 2) {
            if (rand.nextInt(5) == 1) {
                entity.motionY = 0.1;
            }
            entity.motionY += moveFactor;
        }
        if (entityPos.getZ() > this.posZ) {
            entity.motionZ -= moveFactor;
        } else if (entityPos.getZ() < this.posZ) {
            entity.motionZ += moveFactor;
        }
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("direction", 9) && compound.getTagList("direction", 6).tagCount() == 3)
        {
            NBTTagList nbttaglist1 = compound.getTagList("direction", 6);
            this.motionX = nbttaglist1.getDoubleAt(0);
            this.motionY = nbttaglist1.getDoubleAt(1);
            this.motionZ = nbttaglist1.getDoubleAt(2);
        }
        else
        {
            this.setDead();
        }

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
    }
}
