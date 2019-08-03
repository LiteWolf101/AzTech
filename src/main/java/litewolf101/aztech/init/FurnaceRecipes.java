package litewolf101.aztech.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by LiteWolf101 on 10/22/2018.
 */
public class FurnaceRecipes {

	public static void registerFurnaceRecipes() {
		//overworld
		GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlocksInit.RUNE_ORE), 1, 0), new ItemStack(ItemsInit.RED_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlocksInit.RUNE_ORE), 1, 1), new ItemStack(ItemsInit.YELLOW_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlocksInit.RUNE_ORE), 1, 2), new ItemStack(ItemsInit.GREEN_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlocksInit.RUNE_ORE), 1, 3), new ItemStack(ItemsInit.BLUE_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlocksInit.RUNE_ORE), 1, 4), new ItemStack(ItemsInit.WHITE_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlocksInit.RUNE_ORE), 1, 5), new ItemStack(ItemsInit.BLACK_RUNE_SHARD), 1.4f);

		//aztech
		GameRegistry.addSmelting(BlocksInit.ANCIENT_COBBLESTONE, new ItemStack(BlocksInit.ANCIENT_STONE), 0.5f);
		GameRegistry.addSmelting(BlocksInit.COAL_ORE, new ItemStack(Items.COAL), 2f);
		GameRegistry.addSmelting(BlocksInit.IRON_ORE, new ItemStack(Items.IRON_INGOT), 1.7f);
		GameRegistry.addSmelting(BlocksInit.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 2.5f);
		GameRegistry.addSmelting(BlocksInit.REDSTONE_ORE, new ItemStack(Items.REDSTONE, 4), 1.7f);
		GameRegistry.addSmelting(BlocksInit.DIAMOND_ORE, new ItemStack(Items.DIAMOND), 2.8f);
		GameRegistry.addSmelting(BlocksInit.EMERALD_ORE, new ItemStack(Items.EMERALD), 3.4f);
		GameRegistry.addSmelting(BlocksInit.QUARTZ_ORE, new ItemStack(Items.QUARTZ), 1.5f);
		GameRegistry.addSmelting(BlocksInit.NEW_RED_RUNE_ORE, new ItemStack(ItemsInit.RED_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(BlocksInit.NEW_YELLOW_RUNE_ORE, new ItemStack(ItemsInit.YELLOW_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(BlocksInit.NEW_GREEN_RUNE_ORE, new ItemStack(ItemsInit.GREEN_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(BlocksInit.NEW_BLUE_RUNE_ORE, new ItemStack(ItemsInit.BLUE_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(BlocksInit.NEW_WHITE_RUNE_ORE, new ItemStack(ItemsInit.WHITE_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(BlocksInit.NEW_BLACK_RUNE_ORE, new ItemStack(ItemsInit.BLACK_RUNE_SHARD), 1.4f);
		GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlocksInit.TEMPLE_STONE), 1, 1), new ItemStack(Item.getItemFromBlock(BlocksInit.TEMPLE_STONE), 1, 2), 0.2f);

		//food
		GameRegistry.addSmelting(ItemsInit.SORGHUM_SOUP, new ItemStack(ItemsInit.SORGHUM_POPCORN), 0);

	}

}
