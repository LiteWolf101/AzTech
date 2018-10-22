package litewolf101.aztech.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by LiteWolf101 on 10/22/2018.
 */
public class FurnaceRecipes {
    public static void registerFurnaceRecipes(){
        GameRegistry.addSmelting(BlocksInit.ANCIENT_COBBLESTONE, new ItemStack(BlocksInit.ANCIENT_STONE), 0.5f);
        GameRegistry.addSmelting(BlocksInit.IRON_ORE, new ItemStack(Items.IRON_INGOT), 1.7f);
        GameRegistry.addSmelting(BlocksInit.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 2.5f);
    }
}
