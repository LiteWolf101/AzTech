package com.litewolf101.aztech.blocks.tileEntity;

import com.litewolf101.aztech.init.ModTileEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

import static com.litewolf101.aztech.blocks.SlaughterhouseBlock.LEVEL;

public class TESlaughterhouseBlock extends TileEntity implements ITickableTileEntity {
    private int maxKillCount;
    private int currentKillCount;
    private LivingEntity targetEntity;

    public TESlaughterhouseBlock(TileEntityType<?> type) {
        super(type);
    }

    public TESlaughterhouseBlock() {
        this(ModTileEntityTypes.SLAUGHTERHOUSE_DETECTOR);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (this.maxKillCount == 0) {
                this.maxKillCount = 1;
            }
            if (this.targetEntity != null) {
                AxisAlignedBB bb = new AxisAlignedBB(this.pos.add(-4, 0, -4), this.pos.add(5, 5, 5));
                List<Entity> nearbyEntities = world.getEntitiesWithinAABB(Entity.class, bb);
                //List<PlayerEntity> nearbyPlayers = world.getEntitiesWithinAABB(PlayerEntity.class, bb);

                for (Entity entities : nearbyEntities) {
                    if (entities.getClass() == this.targetEntity.getClass()) {
                        if (((LivingEntity)entities).getAttackingEntity() instanceof PlayerEntity){
                            if (((LivingEntity) entities).getHealth() == 0) {
                                if (this.currentKillCount != this.maxKillCount) {
                                    ++this.currentKillCount;
                                }
                            }
                        }
                    }
                }
            } else {
                this.setTargetEntity(EntityType.PIG.create(world));
            }
            float percLevel = (float)(this.currentKillCount / (float)this.maxKillCount);
            int blockLevel = MathHelper.floor(8 * percLevel);
            this.world.setBlockState(pos, getBlockState().with(LEVEL, blockLevel));
        }
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        if (nbt.contains("target_entity")) {
            CompoundNBT entity = nbt.getCompound("target_entity");
            EntityType type = EntityType.byKey(entity.getString("id")).get();
            Entity entity1 = type.create(world);
            this.targetEntity = (LivingEntity) entity1;
        } else {
            this.targetEntity = new PigEntity(EntityType.PIG, this.world);
        }
        this.maxKillCount = nbt.getInt("max_kill_count");
        this.currentKillCount = nbt.getInt("current_kill_count");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        CompoundNBT target = new CompoundNBT();
        if (this.targetEntity != null) {
            target.putString("id", ForgeRegistries.ENTITIES.getKey(this.targetEntity.getType()).toString());
        } else {
            target.putString("id", ForgeRegistries.ENTITIES.getKey(EntityType.PIG).toString());
        }
        nbt.put("target_entity", target);
        if (this.maxKillCount != 0) {
            nbt.putInt("max_kill_count", this.maxKillCount);
        } else {
            nbt.putInt("max_kill_count", 1);
        }
        nbt.putInt("current_kill_count", this.currentKillCount);
        return nbt;
    }

    public void setTargetEntity (LivingEntity entity) {
        this.targetEntity = entity;
    }

    public Entity getTargetEntity () {
        return this.targetEntity == null ? EntityType.PIG.create(world) : this.targetEntity;
    }

    public int getCurrentKillCount() {
        return this.currentKillCount;
    }

    public int getMaxKillCount() {
        return this.maxKillCount;
    }
}
