package com.litewolf101.aztech.utils;

import com.litewolf101.aztech.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import java.util.function.Supplier;

public enum ItemTierAzTech implements IItemTier {
    RED_RUNE(2, 780, 7.0F, 2.5F, 13, () -> {
        return Ingredient.fromItems(ModItems.RED_RUNE);
    }),
    YELLOW_RUNE(2, 780, 7.0F, 2.5F, 13, () -> {
        return Ingredient.fromItems(ModItems.YELLOW_RUNE);
    }),
    GREEN_RUNE(2, 780, 7.0F, 2.5F, 13, () -> {
        return Ingredient.fromItems(ModItems.GREEN_RUNE);
    }),
    BLUE_RUNE(2, 780, 7.0F, 2.5F, 13, () -> {
        return Ingredient.fromItems(ModItems.BLUE_RUNE);
    }),
    WHITE_RUNE(2, 780, 7.0F, 2.5F, 13, () -> {
        return Ingredient.fromItems(ModItems.WHITE_RUNE);
    }),
    BLACK_RUNE(2, 780, 7.0F, 2.5F, 13, () -> {
        return Ingredient.fromItems(ModItems.BLACK_RUNE);
    }),
    GREAT_AZTECH(3, 1826, 9.0F, 3.8F, 10, () -> {
        return Ingredient.fromItems(ModItems.ENTROPY_RUNE);
    }),
    ULTIMATE_AZTECH(3, 2165, 11.0F, 4.3F, 8, () -> {
        return null;
    })
    ;
    //Can harvest levels go over 4?

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadBase<Ingredient> repairMaterial;

    private ItemTierAzTech(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}
