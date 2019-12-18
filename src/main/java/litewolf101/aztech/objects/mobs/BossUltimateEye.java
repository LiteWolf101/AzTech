package litewolf101.aztech.objects.mobs;

import litewolf101.aztech.objects.projectiles.ProjectileEyeLaser;
import litewolf101.aztech.utils.AzTechLootTables;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by LiteWolf101 on Jan/11/2019
 */
public class BossUltimateEye extends EntityMob implements IMob {

    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(BossUltimateEye.class, DataSerializers.BOOLEAN);
    private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(new TextComponentString(TextFormatting.GOLD + "Ultimate Eye"), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    public int shieldTime;
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;

    public BossUltimateEye(World worldIn, boolean isInvulnerable) {
        this(worldIn);
    }

    public BossUltimateEye(World world) {
        super(world);
        this.dataManager.register(INVULNERABLE, true);
        setSize(2f, 2f);
        this.isImmuneToFire = true;
        this.experienceValue = 450;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(3, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F, 1.0F));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        //this.tasks.addTask(5, new SpawnEyesAI(this));
        this.tasks.addTask(5, new ZipAI(this));
        this.tasks.addTask(1, new LaserAttack(this));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.setHomePosAndDistance(this.getPosition(), 12);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AzTechSoundHandler.ENTITY_EYE_GUARDIAN_AMBIENT;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("is_invulnerable", this.getInvulnerable());
    }

    public boolean getInvulnerable() {
        return this.dataManager.get(INVULNERABLE);
    }

    public void setInvulnerable(boolean isInvulnerable) {
        this.dataManager.set(INVULNERABLE, isInvulnerable);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.setInvulnerable(compound.getBoolean("is_invulnerable"));
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return AzTechLootTables.ULTIMATE_EYE;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    protected void updateAITasks() {

        --this.heightOffsetUpdateTime;

        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 50;
            this.heightOffset = 1.5F + (float) this.rand.nextGaussian() * 3.0F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + (double) entitylivingbase.getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset) {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }

        //shield AI
        --this.shieldTime;
        if (shieldTime <= 0) {
            shieldTime = 200;
        }
        if (shieldTime == 200) {
            world.playSound(null, this.getPosition(), AzTechSoundHandler.SHIELD_POWERUP, SoundCategory.HOSTILE, 1f, 1f);
            world.playSound(null, this.getPosition(), AzTechSoundHandler.SHIELD_RUNNING, SoundCategory.HOSTILE, 1f, 1f);
            this.setEntityInvulnerable(true);
            setInvulnerable(true);

        }
        if (shieldTime >= 100) {
            heal(0.15f);
        }
        if (shieldTime == 100) {
            world.playSound(null, this.getPosition(), AzTechSoundHandler.SHIELD_POWERDOWN, SoundCategory.HOSTILE, 1f, 1f);
            this.setEntityInvulnerable(false);
            setInvulnerable(false);
        }
        if (shieldTime < 100) {
        }

        super.updateAITasks();
    }

    @Override
    public float getEyeHeight() {
        return 1f;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return true;
    }

    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        return ientitylivingdata;
    }

    public void onLivingUpdate() {
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }
        super.onLivingUpdate();

        BlockPos checkpos = new BlockPos(this.posX, this.posY - 3, this.posZ);
        IBlockState groundBlock = this.world.getBlockState(checkpos);
        Material material = groundBlock.getMaterial();

        if (material != Material.AIR) {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        if (!this.isWithinHomeDistanceCurrentPosition()) {
            moveToBlockPosAndAngles(this.getHomePosition(), this.rotationYaw, this.rotationPitch);
        }
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
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

    public class SpawnEyesAI extends EntityAIBase {

        private BossUltimateEye bossUltimateEye;
        private int spawnTicks;

        public SpawnEyesAI(BossUltimateEye bossUltimateEyeIn) {
            this.bossUltimateEye = bossUltimateEyeIn;
        }

        @Override
        public boolean shouldExecute() {
            return bossUltimateEye.isEntityAlive();
        }

        @Override
        public void updateTask() {
            Random randx = new Random();
            Random randz = new Random();
            int x = randx.nextInt(7) - 3;
            int z = randz.nextInt(7) - 3;
            BlockPos newPos = new BlockPos(bossUltimateEye.posX + x, bossUltimateEye.posY, bossUltimateEye.posZ + z);
            ++spawnTicks;
            if (spawnTicks >= 400 && !isCoordsInWall(world, newPos)) {
                spawnTicks = 0;
                spawnEyes(world, newPos);
            } else if (spawnTicks >= 400 && isCoordsInWall(world, newPos)) {
                spawnTicks = 395;
            }
        }

        private boolean isCoordsInWall(World world, BlockPos pos) {
            int check = 0;
            for (int x = -1; x <= 3; x++) {
                for (int y = -1; y <= 3; y++) {
                    for (int z = -1; z <= 3; z++) {
                        BlockPos checkingpos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                        if (!world.isAirBlock(checkingpos)) {
                            check++;
                        }
                    }
                }
            }
            return check > 0;
        }

        private void spawnEyes(World world, BlockPos pos) {
            MobEyeGuardian eyeGuardian = new MobEyeGuardian(world);
            MobEyeMaster eyeMaster = new MobEyeMaster(world);
            eyeGuardian.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), bossUltimateEye.rotationYaw, bossUltimateEye.rotationPitch);
            eyeMaster.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), bossUltimateEye.rotationYaw, bossUltimateEye.rotationPitch);
            world.spawnEntity(eyeGuardian);
            world.spawnEntity(eyeMaster);
        }

    }

    private class ZipAI extends EntityAIBase {

        private BossUltimateEye bossUltimateEye;
        private int teleportTicks;

        public ZipAI(BossUltimateEye bossUltimateEyeIn) {
            this.bossUltimateEye = bossUltimateEyeIn;
        }

        @Override
        public boolean shouldExecute() {
            return bossUltimateEye.isEntityAlive();
        }

        @Override
        public void updateTask() {
            Random randx = new Random();
            Random randz = new Random();
            int x = randx.nextInt(5) - 2;
            int z = randz.nextInt(5) - 2;
            teleportTicks--;
            if (teleportTicks <= 0) {
                teleportTicks = 100;
            }
            if (teleportTicks == 25 || teleportTicks == 20 || teleportTicks == 15 || teleportTicks == 10 || teleportTicks == 5) {
                move(MoverType.SELF, x, 0, z);
                world.playSound(null, bossUltimateEye.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1f, 1f);
            }
        }

    }

    private class LaserAttack extends EntityAIBase {

        private final BossUltimateEye ultimateEye;
        private int attackTime;
        private int attackStep;

        public LaserAttack(BossUltimateEye ultimateEye) {
            this.ultimateEye = ultimateEye;
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.ultimateEye.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        public void updateTask() {
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.ultimateEye.getAttackTarget();
            double d0 = this.ultimateEye.getDistanceSq(entitylivingbase);

            if (d0 < 4.0D) {
                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.ultimateEye.attackEntityAsMob(entitylivingbase);
                }

            } else if (d0 < this.getFollowDistance() * this.getFollowDistance()) {
                double d1 = entitylivingbase.posX - this.ultimateEye.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (this.ultimateEye.posY + (double) (this.ultimateEye.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.ultimateEye.posZ;

                if (this.attackTime <= 0) {
                    ++this.attackStep;

                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                    }

                    if (this.attackStep > 3) {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.1F;
                        ultimateEye.world.playSound(null, ultimateEye.getPosition(), AzTechSoundHandler.PROJECTILE_EYE_LASER_FIRED, SoundCategory.HOSTILE, 2f, 1f);

                        for (int i = 0; i < 3; ++i) {
                            ProjectileEyeLaser eyeLaser = new ProjectileEyeLaser(this.ultimateEye.world, this.ultimateEye, d1 + this.ultimateEye.getRNG().nextGaussian() * (double) f, d2, d3 + this.ultimateEye.getRNG().nextGaussian() * (double) f);
                            //fix...somehow.
                            eyeLaser.posY = this.ultimateEye.posY + (double) (this.ultimateEye.height / 2.0F);
                            eyeLaser.rotationPitch = this.ultimateEye.rotationPitch;
                            eyeLaser.rotationYaw = this.ultimateEye.rotationYaw;
                            this.ultimateEye.world.spawnEntity(eyeLaser);
                        }
                    }
                }

                this.ultimateEye.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            } else {
                this.ultimateEye.getNavigator().clearPath();
                this.ultimateEye.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }

        private double getFollowDistance() {
            IAttributeInstance iattributeinstance = this.ultimateEye.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }

    }

}
