package litewolf101.aztech.objects.mobs;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.objects.projectiles.ProjectileEyeLaser;
import litewolf101.aztech.utils.AzTechLootTables;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on 10/26/2018.
 */
public class MobEyeGuardian extends EntityMob implements IMob {

	private float heightOffset = 0.5F;
	private int heightOffsetUpdateTime;

	public MobEyeGuardian(World world, double x, double y, double z, float rotationYaw) {
		this(world);
		this.rotationYaw = rotationYaw;
		this.setPosition(x, y, z);
	}

	public MobEyeGuardian(World worldIn) {
		super(worldIn);
		setSize(2.4375f, 2.4375f);
		this.isImmuneToFire = true;
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(3, new EntityAIWander(this, 0.2D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F, 1.0F));
		this.tasks.addTask(1, new LaserAttack(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityCreeper.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, MobPyronant.class, true));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 6.0F, 0.2D, 0.2D));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AzTechSoundHandler.ENTITY_EYE_GUARDIAN_AMBIENT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return AzTechLootTables.EYE_GUARDIAN;
	}

	protected void updateAITasks() {

		--this.heightOffsetUpdateTime;

		if(this.heightOffsetUpdateTime <= 0) {
			this.heightOffsetUpdateTime = 100;
			this.heightOffset = 1.5F + (float)this.rand.nextGaussian() * 3.0F;
		}

		EntityLivingBase entitylivingbase = this.getAttackTarget();

		if(entitylivingbase != null && entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
			this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
			this.isAirBorne = true;
		}

		super.updateAITasks();
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Nullable
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
		this.setEquipmentBasedOnDifficulty(difficulty);
		this.setEnchantmentBasedOnDifficulty(difficulty);
		return ientitylivingdata;
	}

	public void onLivingUpdate() {
		if(!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}

		if(this.world.isRemote) {

			for(int i = 0; i < 2; ++i) {
				AzTech.proxy.spawnParticle(world, AzTechParticleTypes.EYE_GUARDIAN, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
			}
		}

		super.onLivingUpdate();
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
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	}

	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected float getSoundVolume() {
		return 2F;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}

	private class LaserAttack extends EntityAIBase {

		private final MobEyeGuardian guardian;
		private int attackTime;
		private int attackStep;

		public LaserAttack(MobEyeGuardian guardian) {
			this.guardian = guardian;
			this.setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = this.guardian.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		public void updateTask() {
			--this.attackTime;
			EntityLivingBase entitylivingbase = this.guardian.getAttackTarget();
			double d0 = this.guardian.getDistanceSq(entitylivingbase);

			if(d0 < 4.0D) {
				if(this.attackTime <= 0) {
					this.attackTime = 20;
					this.guardian.attackEntityAsMob(entitylivingbase);
				}

			}
			else if(d0 < this.getFollowDistance() * this.getFollowDistance()) {
				double d1 = entitylivingbase.posX - this.guardian.posX;
				double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.guardian.posY + (double)(this.guardian.height / 2.0F));
				double d3 = entitylivingbase.posZ - this.guardian.posZ;

				if(this.attackTime <= 0) {
					++this.attackStep;

					if(this.attackStep == 1) {
						this.attackTime = 60;
					}
					else if(this.attackStep <= 4) {
						this.attackTime = 6;
					}
					else {
						this.attackTime = 100;
						this.attackStep = 0;
					}

					if(this.attackStep > 3) {
						float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.1F;
						guardian.world.playSound(null, guardian.getPosition(), AzTechSoundHandler.PROJECTILE_EYE_LASER_FIRED, SoundCategory.HOSTILE, 2f, 1f);

						for(int i = 0; i < 1; ++i) {
							ProjectileEyeLaser eyeLaser = new ProjectileEyeLaser(this.guardian.world, this.guardian, d1 + this.guardian.getRNG().nextGaussian() * (double)f, d2, d3 + this.guardian.getRNG().nextGaussian() * (double)f);
							//fix...somehow.
							eyeLaser.posY = this.guardian.posY + (double)(this.guardian.height / 2.0F);
							eyeLaser.rotationPitch = this.guardian.rotationPitch;
							eyeLaser.rotationYaw = this.guardian.rotationYaw;
							this.guardian.world.spawnEntity(eyeLaser);
						}
					}
				}

				this.guardian.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
			}
			else {
				this.guardian.getNavigator().clearPath();
				this.guardian.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}

			super.updateTask();
		}

		private double getFollowDistance() {
			IAttributeInstance iattributeinstance = this.guardian.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
			return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
		}

	}

}
