package litewolf101.aztech.objects.mobs;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import static litewolf101.aztech.init.PotionsInit.STATIC;

/**
 * Created by LiteWolf101 on Mar /12/2019
 */
public class MobSpark extends EntityFireball {

    public EntityLivingBase shootingEntity;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    private int ticksAlive;
    private int ticksInAir;

    public MobSpark(World worldIn) {
        super(worldIn);
        setSize(0.5f, 0.5f);
    }

    public MobSpark(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        double d0 = MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    public MobSpark(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn);
        this.shootingEntity = shooter;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        accelX = accelX + this.rand.nextGaussian() * 0.4D;
        accelY = accelY + this.rand.nextGaussian() * 0.4D;
        accelZ = accelZ + this.rand.nextGaussian() * 0.4D;
        double d0 = MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public void onUpdate() {
        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead) && this.world.isBlockLoaded(new BlockPos(this))) {

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.forwardsRaycast(this, true, this.ticksInAir >= 25, this.shootingEntity);

            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();

            if (this.isInWater()) {
                for (int i = 0; i < 4; ++i) {
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= f;
            this.motionY *= f;
            this.motionZ *= f;
            AzTech.proxy.spawnParticle(world, AzTechParticleTypes.WHITE_SPARKLE, this.posX, this.posY + 0.25D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.setPosition(this.posX, this.posY, this.posZ);
        } else {
            this.setDead();
        }
    }

    protected boolean isFireballFiery() {
        return false;
    }

    protected float getMotionFactor() {
        return 1F;
    }

    @Override
    public void onImpact(RayTraceResult raytraceresult) {
        Entity entity = raytraceresult.entityHit;
        if (entity == null) {
            //EnumFacing.Axis axis = raytraceresult.sideHit.getAxis();
            EnumFacing facing = raytraceresult.sideHit;
            /*if (axis != null) {
                if (axis == EnumFacing.Axis.X) {
                    this.motionX = -this.motionX;
                } else if (axis == EnumFacing.Axis.Y) {
                    this.motionY = -this.motionY;
                } else if (axis == EnumFacing.Axis.Z) {
                    this.motionZ = -this.motionZ;
                }
            }*/
            if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
                this.motionX = -this.motionX;
            }
            if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
                this.motionZ = -this.motionZ;
            }
            if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
                this.motionY = -this.motionY;
            }
        } else {
            if (entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).addPotionEffect(new PotionEffect(STATIC, 40));
                world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1f, 2f, false);
            }
            setDead();
        }
        world.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_METAL_BREAK, SoundCategory.HOSTILE, 1f, 2f, false);

    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public float getCollisionBorderSize() {
        return 5F;
    }

}
