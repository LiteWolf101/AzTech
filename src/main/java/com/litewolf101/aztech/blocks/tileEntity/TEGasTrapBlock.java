package com.litewolf101.aztech.blocks.tileEntity;

import com.litewolf101.aztech.init.ModTileEntityTypes;
import com.litewolf101.aztech.utils.RunePowerSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;

public class TEGasTrapBlock extends TileEntity implements ITickableTileEntity {
    private int cooldownTicks = 200;
    private int maxCooldown = 200;
    private List<EffectInstance> potionEffect = defaultEffects();
    private int range = 6;

    public TEGasTrapBlock(final TileEntityType<?> type) {
        super(type);
    }

    public TEGasTrapBlock() {
        this(ModTileEntityTypes.GAS_TRAP_BLOCK);
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isRemote()) {
            boolean flag = false;
            if (this.cooldownTicks == 0) {
                for (Direction direction : Direction.values()) {
                    if (direction != Direction.UP) {
                        if (this.world.getBlockState(pos.offset(direction)).getBlock() instanceof RunePowerSource) {
                            if (((RunePowerSource) this.world.getBlockState(this.pos.offset(direction)).getBlock()).getPoweredState(this.world.getBlockState(this.pos.offset(direction)))) {
                                flag = true;
                                continue;
                            }
                        }
                    }
                }

                if (flag) {
                    AxisAlignedBB bb = new AxisAlignedBB(this.pos.add(-this.range, 0, -this.range), this.pos.add(this.range, this.range, this.range));
                    List<LivingEntity> entityList = this.world.getEntitiesWithinAABB(LivingEntity.class, bb);
                    for (LivingEntity entities : entityList) {
                        if (this.potionEffect != null && !this.potionEffect.isEmpty()) {
                            for (EffectInstance effect : this.potionEffect) {
                                entities.addPotionEffect(new EffectInstance(effect.getPotion(), effect.getDuration(), effect.getAmplifier()));
                            }
                        }
                    }
                    //spawn particles in range
                    this.cooldownTicks = this.maxCooldown;
                }
            }
            --this.cooldownTicks;
            this.cooldownTicks = Math.max(0, this.cooldownTicks);
        }

    }

    @Override
    public void read(CompoundNBT nbt) {
        if (nbt.contains("maxCooldown")) {
            this.maxCooldown = nbt.getInt("maxCooldown");
        } else {
            this.maxCooldown = 200;
        }
        if (nbt.contains("cooldownTick")) {
            this.cooldownTicks = nbt.getInt("cooldownTick");
        } else {
            this.cooldownTicks = this.maxCooldown;
        }
        if (nbt.contains("range")) {
            this.range = nbt.getInt("range");
        } else {
            this.range = 6;
        }
        if (nbt.contains("TrapEffects")) {
            ListNBT effects = (ListNBT) nbt.get("TrapEffects");
            List<EffectInstance> test = new ArrayList();
            if (effects != null && !effects.isEmpty()) {
                for (int i = 0; i < effects.size(); ++i) {
                    EffectInstance instance = EffectInstance.read((CompoundNBT) effects.get(i));
                    test.add(instance);
                }
                this.potionEffect = test;
            }
        } else {
            this.potionEffect = defaultEffects();
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        if (this.maxCooldown > 0) {
            nbt.putInt("maxCooldown", this.maxCooldown);
        } else {
            nbt.putInt("maxCooldown", 200);
        }
        nbt.putInt("cooldownTick", this.cooldownTicks);
        if (this.range > 0) {
            nbt.putInt("range", this.range);
        } else {
            nbt.putInt("range", 6);
        }
        ListNBT effects = new ListNBT();
        if (this.potionEffect != null) {
            for (EffectInstance instance : this.potionEffect) {
                CompoundNBT potion = new CompoundNBT();
                instance.write(potion);
                effects.add(potion);
            }
        } else {
            for (EffectInstance instance: defaultEffects()) {
                CompoundNBT potion = new CompoundNBT();
                instance.write(potion);
                effects.add(potion);
            }
        }
        nbt.put("TrapEffects", effects);
        return nbt;
    }

    public List<EffectInstance> defaultEffects(){
        List<EffectInstance> list = new ArrayList<>();
        list.add(new EffectInstance(Effects.POISON, 200, 2));
        list.add(new EffectInstance(Effects.SLOWNESS, 200, 2));
        list.add(new EffectInstance(Effects.BLINDNESS, 100, 1));
        list.add(new EffectInstance(Effects.NAUSEA, 800, 2));
        list.add(new EffectInstance(Effects.WEAKNESS, 400, 1));
        list.add(new EffectInstance(Effects.HUNGER, 700, 2));
        list.add(new EffectInstance(Effects.MINING_FATIGUE, 1000, 2));
        list.add(new EffectInstance(Effects.LEVITATION, 200, 4));

        return list;
    }

    public void addEffect(EffectInstance effect) {
        this.potionEffect.add(effect);
    }

    public void replaceEffectsFromItemStack (ItemStack stack) {
        if (stack.getItem() instanceof PotionItem) {
            this.potionEffect.clear();
            this.potionEffect.addAll(PotionUtils.getEffectsFromStack(stack));
        }
    }

    public void addEffectsFromItemStack (ItemStack stack) {
        if (stack.getItem() instanceof PotionItem) {
            this.potionEffect.addAll(PotionUtils.getEffectsFromStack(stack));
        }
    }

    public void removeEffect (EffectInstance effect) {
        this.potionEffect.remove(effect);
    }

    public List<EffectInstance> getEffectList(){
        return this.potionEffect;
    }
}
