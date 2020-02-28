package com.litewolf101.aztech.items;

import com.litewolf101.aztech.client.gui.AzotomeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;
import java.util.List;

public class Azotome extends Item {
    public Azotome(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> openGui(world, player));
        player.addStat(Stats.ITEM_USED.get(this));
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @OnlyIn(Dist.CLIENT)
    private void openGui(World world, PlayerEntity player) {
        if (world.isRemote) {
            world.playSound(player, player.getPosition(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
            Minecraft.getInstance().displayGuiScreen(new AzotomeScreen());
        }
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag flag) {
        textComponents.add(new TranslationTextComponent("aztech.description.azotome"));
    }
}
