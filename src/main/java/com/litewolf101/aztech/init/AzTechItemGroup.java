package com.litewolf101.aztech.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class AzTechItemGroup extends ItemGroup {
    public AzTechItemGroup() {
        super("aztech");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.AZTECH_RING);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public int getSearchbarWidth() {
        return 75;
    }

    //This is a pretty beat thing
    @OnlyIn(Dist.CLIENT)
    @Override
    public ResourceLocation getBackgroundImage() {
        return new ResourceLocation(com.litewolf101.aztech.AzTech.MODID, "textures/gui/tab_items.png");
    }

    @Override
    public ResourceLocation getTabsImage() {
        return new ResourceLocation(com.litewolf101.aztech.AzTech.MODID, "textures/gui/tab_icons.png");
    }
}
