package litewolf101.aztech.objects.mobs;

import com.google.common.base.Predicates;
import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by LiteWolf101 on 10/27/2018.
 */
public class MobEyeMaster extends EntityMob implements IMob {
    public static boolean isDoingAttack;


    public int attackTicks;

    public MobEyeMaster(World worldIn) {
        super(worldIn);
        setSize(3f, 3f);
        this.isImmuneToFire = true;
        this.attackTicks = 400;
        this.hasNoGravity();
        isDoingAttack = DrawLinesToGuaridans.isAttacking;
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
        this.attackTicks = 400;
    }

    @Override
    protected void initEntityAI() {
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(3, new EntityAIWander(this, 0.2D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F, 1.0F));
        this.tasks.addTask(2, new DrawLinesToGuaridans(this, attackTicks));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    public void onLivingUpdate()
    {
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.2D;
        }

        if (this.world.isRemote)
        {

            for (int i = 0; i < 2; ++i)
            {
                AzTech.proxy.spawnParticle(world, AzTechParticleTypes.EYE_MASTER, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
            }
        }

        super.onLivingUpdate();
    }

    @Nullable
    @Override
    protected Item getDropItem() {
        return ItemsInit.BLUE_RUNE_SHARD;
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

    private static class DrawLinesToGuaridans extends EntityAIBase {
        public static boolean isAttacking;
        public int attackCooldown;
        private final MobEyeMaster master;


        public DrawLinesToGuaridans(MobEyeMaster mobEyeMaster, int attackCooldown) {
            this.master = mobEyeMaster;
            this.attackCooldown = attackCooldown;
        }

        @Override
        public boolean shouldExecute() {
            return true;
        }

        @Override
        public void updateTask() {
            Entity entity = this.master.world.getClosestPlayer(this.master.posX, this.master.posY, this.master.posZ, 48d, false);
            --this.attackCooldown;
            if (attackCooldown > 50) {
                isAttacking = false;
                this.master.setEntityInvulnerable(true);
                AxisAlignedBB detectbb = new AxisAlignedBB(master.posX, master.posY, master.posZ, master.posX + 10, master.posY + 10, master.posZ + 10).grow(10D);
                List<MobEyeGuardian> guards = this.master.world.getEntitiesWithinAABB(MobEyeGuardian.class, detectbb);
                if (guards.size() > 0){
                    MobEyeGuardian highlight = guards.get(this.master.rand.nextInt(guards.size()));
                    this.makeParticlesTo(highlight);
                    highlight.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 10, 4));
                }
            }
            if (attackCooldown == 50){
                this.master.world.playSound(null, this.master.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.HOSTILE, 2f, 1f);
            }
            if (attackCooldown < 50) {
                isAttacking = true;
                this.master.setEntityInvulnerable(false);
                if (entity != null && !((EntityPlayer) entity).isCreative()){
                    if (this.master.getEntityBoundingBox().intersects(entity.getEntityBoundingBox()))
                    {
                        this.master.attackEntityAsMob(entity);
                        this.master.motionY = 0.4d;
                    }
                    else
                    {

                        Vec3d vec3d = entity.getPositionEyes(1.0F);
                        this.master.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 0.8D);

                    }
                }
            }

            if (attackCooldown < 0){
                this.attackCooldown = master.attackTicks;
            }
        }

        public void makeParticlesTo(Entity highlight) {
            double sx = master.posX + 0.5D;
            double sy = master.posY + 1.0D;
            double sz = master.posZ + 0.5D;

            double dx = sx - highlight.posX;
            double dy = sy - highlight.posY - highlight.getEyeHeight();
            double dz = sz - highlight.posZ;


            for (int v = 1; v < 8; v++){
                AzTech.proxy.spawnParticle(this.master.world, AzTechParticleTypes.ENEMY_LINK, (sx - dx/8*v), (sy - dy/8*v), (sz - dz/8*v), 0, 0, 0);
            }
        }
    }
}

