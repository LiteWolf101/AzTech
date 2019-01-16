package litewolf101.aztech.objects.mobs;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on 11/1/2018.
 */
public class MobPyronant extends EntityMob implements IMob {
    public MobPyronant(World worldIn) {
        super(worldIn);
        setSize(0.7f, 2.45f);
        this.isImmuneToFire = true;
    }

    public MobPyronant(World world, double x, double y, double z, float rotationYaw) {
        this(world);
        this.rotationYaw = rotationYaw;
        this.setPosition(x, y, z);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    public void onLivingUpdate() {
        if (this.world.isRemote) {
            for (int i = 0; i < 2; ++i) {
                AzTech.proxy.spawnParticle(world, AzTechParticleTypes.PYRONANT, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F, 1.0F));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.7, 0.1f));
        this.tasks.addTask(1, new FireBallAttackAI(this));
        this.tasks.addTask(2, new HealFromFire(this));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 2F;
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95F;
    }

    private class FireBallAttackAI extends EntityAIBase {
        private final MobPyronant pyronant;
        private int attackTime;
        private int attackStep;
        public FireBallAttackAI(MobPyronant mobPyronant) {
            this.pyronant = mobPyronant;
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.pyronant.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        public void updateTask() {
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.pyronant.getAttackTarget();
            double d0 = this.pyronant.getDistanceSq(entitylivingbase);

            if (d0 < 4.0D) {
                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.pyronant.attackEntityAsMob(entitylivingbase);
                }

                this.pyronant.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < this.getFollowDistance() * this.getFollowDistance()) {
                double d1 = entitylivingbase.posX - this.pyronant.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.pyronant.posY + (double)(this.pyronant.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.pyronant.posZ;

                if (this.attackTime <= 0) {
                    ++this.attackStep;

                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                    }
                    else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    }
                    else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                    }

                    if (this.attackStep > 1) {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                        this.pyronant.world.playEvent((EntityPlayer)null, 1018, new BlockPos((int)this.pyronant.posX, (int)this.pyronant.posY, (int)this.pyronant.posZ), 0);

                        for (int i = 0; i < 3; ++i) {
                            EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.pyronant.world, this.pyronant, d1 + this.pyronant.getRNG().nextGaussian() * (double)f, d2, d3 + this.pyronant.getRNG().nextGaussian() * (double)f);
                            entitysmallfireball.posY = this.pyronant.posY + (double)(this.pyronant.height / 2.0F) + 0.5D;
                            this.pyronant.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }
                this.pyronant.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else {
                this.pyronant.getNavigator().clearPath();
                this.pyronant.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            super.updateTask();
        }

        private double getFollowDistance() {
            IAttributeInstance iattributeinstance = this.pyronant.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }
    }

    private class HealFromFire extends EntityAIBase {
        private MobPyronant pyronant;
        private int delay;
        public HealFromFire(MobPyronant mobPyronant) {
            this.pyronant = mobPyronant;
        }

        @Override
        public boolean shouldExecute() {
            return true;
        }

        @Override
        public void updateTask() {
            delay++;
            int check = 0;
            for (int x = -2; x <= 2; x++){
                for (int y = -2; y <= 2; y++){
                    for (int z = -2; z <= 2; z++){
                        BlockPos checkingpos = new BlockPos(this.pyronant.posX + x, this.pyronant.posY + y, this.pyronant.posZ + z);
                        if (world.getBlockState(checkingpos).getBlock() == Blocks.FIRE) {
                            check++;
                        }
                    }
                }
            }
            if (delay % 20 == 0) {
                this.pyronant.heal(0.2f * check);
            }
        }
    }
}
