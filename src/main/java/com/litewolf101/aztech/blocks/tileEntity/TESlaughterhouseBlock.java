package com.litewolf101.aztech.blocks.tileEntity;

import com.litewolf101.aztech.init.ModTileEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import java.util.List;

import static com.litewolf101.aztech.blocks.SlaughterhouseBlock.LEVEL;

public class TESlaughterhouseBlock extends TileEntity implements ITickableTileEntity {
    private int maxKillCount;
    private int currentKillCount;
    private EntityType targetEntity;

    public TESlaughterhouseBlock(TileEntityType<?> type) {
        super(type);
    }

    public TESlaughterhouseBlock() {
        this(ModTileEntityTypes.SLAUGHTERHOUSE_DETECTOR);
    }

    @Override
    public void tick() {
        if (!this.world.isRemote) {
            if (this.maxKillCount == 0) {
                this.maxKillCount = 1;
            }
            if (this.targetEntity != null) {
                AxisAlignedBB bb = new AxisAlignedBB(this.pos.add(-4, 0, -4), this.pos.add(5, 5, 5));
                List<Entity> nearbyEntities = this.world.getEntitiesWithinAABB(Entity.class, bb);

                for (Entity entities : nearbyEntities) {
                    if (entities.getType() == this.targetEntity) {
                        if (((LivingEntity)entities).getAttackingEntity() instanceof PlayerEntity){
                            if (((LivingEntity) entities).deathTime > 18) {
                                if (this.currentKillCount != this.maxKillCount) {
                                    ++this.currentKillCount;
                                }
                            }
                        }
                    }
                }
            }
            float percLevel = (float)(this.currentKillCount / (float)this.maxKillCount);
            int blockLevel = MathHelper.floor(8 * percLevel);
            this.world.setBlockState(pos, getBlockState().with(LEVEL, blockLevel));
        }
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        System.out.println("READ");
        System.out.println(nbt.get("target_entity"));
        System.out.println(this.targetEntity);
        if (nbt.contains("target_entity")) {
            CompoundNBT entity = nbt.getCompound("target_entity");
            if (EntityType.byKey(entity.getString("id")).isPresent()) {
                EntityType type = EntityType.byKey(entity.getString("id")).get();
                this.targetEntity = type;
            }
        }
        this.maxKillCount = nbt.getInt("max_kill_count");
        this.currentKillCount = nbt.getInt("current_kill_count");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        System.out.println("WRITE");
        CompoundNBT target = new CompoundNBT();
        if (this.targetEntity != null) {
            target.putString("id", this.targetEntity.getRegistryName().toString());
        }
        nbt.put("target_entity", target);
        if (this.maxKillCount != 0) {
            nbt.putInt("max_kill_count", this.maxKillCount);
        } else {
            nbt.putInt("max_kill_count", 1);
        }
        nbt.putInt("current_kill_count", this.currentKillCount);
        System.out.println(nbt.get("target_entity"));
        System.out.println(this.targetEntity);
        return nbt;
    }

    public void setTargetEntity (LivingEntity entity) {
        this.targetEntity = entity.getType();
    }

    public EntityType getTargetEntity () {
        return this.targetEntity;
    }

    public int getCurrentKillCount() {
        return this.currentKillCount;
    }

    public int getMaxKillCount() {
        return this.maxKillCount;
    }

    public void setMaxKillCount(int count) {
        this.maxKillCount = count;
    }
}
