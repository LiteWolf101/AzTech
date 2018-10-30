package litewolf101.aztech.objects.mobs;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by LiteWolf101 on 10/27/2018.
 */
public class MobEyeMaster extends EntityMob implements IMob{

    public MobEyeMaster(World worldIn) {
        super(worldIn);
        setSize(3f, 3f);
        this.isImmuneToFire = true;
    }

    @Override
    public float getEyeHeight() {
        return 1.5f;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return true;
    }

    public MobEyeMaster(World world, double x, double y, double z, float rotationYaw) {
        this(world);
        this.rotationYaw = rotationYaw;
        this.setPosition(x, y, z);
    }

    @Override
    protected void initEntityAI() {
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(3, new EntityAIWander(this, 0.2D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F, 1.0F));
        this.tasks.addTask(2, new DrawLinesToGuaridans(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    public void onLivingUpdate()
    {
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        if (this.world.isRemote)
        {

            for (int i = 0; i < 2; ++i)
            {
                AzTech.proxy.spawnParticle(world, AzTechParticleTypes.EYE_MASTER, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
            }
        }

        super.onLivingUpdate();

        AxisAlignedBB detectbb = new AxisAlignedBB(this.posX, this.posY, this.posZ, this.posX + 10, this.posY + 10, this.posZ + 10).grow(10D);
        List<MobEyeGuardian> guards = world.getEntitiesWithinAABB(MobEyeGuardian.class, detectbb);
        if (guards.size() > 0){
            MobEyeGuardian highlight = guards.get(rand.nextInt(guards.size()));
            this.makeParticlesTo(highlight);
        }



    }

    public void makeParticlesTo(Entity highlight) {
        double sx = this.posX + 0.5D;
        double sy = this.posY + 1.0D;
        double sz = this.posZ + 0.5D;

        double dx = sx - highlight.posX;
        double dy = sy - highlight.posY - highlight.getEyeHeight();
        double dz = sz - highlight.posZ;

        for (int i = 0; i < 5; i++) {
            for (int v = 1; v < 8; v++){
                world.spawnParticle(EnumParticleTypes.REDSTONE, (sx - dx/8*v), (sy - dy/8*v), (sz - dz/8*v), 0, 0, 0);
            }
        }

    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AzTechSoundHandler.ENTITY_EYE_MASTER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return AzTechSoundHandler.ENTITY_EYE_GUARDIAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AzTechSoundHandler.ENTITY_EYE_GUARDIAN_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 2F;
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95F;
    }

    private class DrawLinesToGuaridans extends EntityAIBase {
        private final MobEyeMaster master;


        @Override
        public boolean shouldExecute() {
            return true;
        }

        public DrawLinesToGuaridans(MobEyeMaster mobEyeMaster) {
            this.master = mobEyeMaster;
        }

        @Override
        public void updateTask() {
            AxisAlignedBB detectbb = new AxisAlignedBB(master.posX, master.posY, master.posZ, master.posX + 1, master.posY + 1, master.posZ + 1).grow(10D);
            List<MobEyeGuardian> guards = world.getEntitiesWithinAABB(MobEyeGuardian.class, detectbb);
            if (guards.size() > 0){
                MobEyeGuardian highlight = guards.get(rand.nextInt(guards.size()));
                this.makeParticlesTo(highlight);
            }
        }

        public void makeParticlesTo(Entity highlight) {
            double sx = master.posX + 0.5D;
            double sy = master.posY + 1.0D;
            double sz = master.posZ + 0.5D;

            double dx = sx - highlight.posX;
            double dy = sy - highlight.posY - highlight.getEyeHeight();
            double dz = sz - highlight.posZ;

            for (int i = 0; i < 5; i++) {
                world.spawnParticle(EnumParticleTypes.REDSTONE, sx, sy, sz, -dx, -dy, -dz);
            }

        }
    }
}

