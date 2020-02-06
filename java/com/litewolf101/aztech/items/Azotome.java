package com.litewolf101.aztech.items;

import com.litewolf101.aztech.client.gui.AzotomeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class Azotome extends Item {
    public Azotome(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        world.playSound(player, player.getPosition(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.MASTER, 1, 1);
        Minecraft.getInstance().displayGuiScreen(new AzotomeScreen());
        player.addStat(Stats.ITEM_USED.get(this));
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}
