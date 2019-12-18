package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by LiteWolf101 on 10/16/2018.
 */
public class AncientOre extends Block implements IHasModel {

    public AncientOre(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 2);
        setHardness(2f);
        setResistance(5.8f);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int quantityDropped(Random rand) {
        return this == BlocksInit.LAPIS_ORE ? 4 + rand.nextInt(8) : 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this == BlocksInit.COAL_ORE ? Items.COAL : (this == BlocksInit.REDSTONE_ORE ? Items.REDSTONE : (this == BlocksInit.LAPIS_ORE ? Items.DYE : (this == BlocksInit.DIAMOND_ORE ? Items.DIAMOND : (this == BlocksInit.EMERALD_ORE ? Items.EMERALD : (this == BlocksInit.QUARTZ_ORE ? Items.QUARTZ : (this == BlocksInit.NEW_RED_RUNE_ORE ? ItemsInit.RED_RUNE_SHARD : (this == BlocksInit.NEW_YELLOW_RUNE_ORE ? ItemsInit.YELLOW_RUNE_SHARD : (this == BlocksInit.NEW_GREEN_RUNE_ORE ? ItemsInit.GREEN_RUNE_SHARD : (this == BlocksInit.NEW_BLUE_RUNE_ORE ? ItemsInit.BLUE_RUNE_SHARD : (this == BlocksInit.NEW_WHITE_RUNE_ORE ? ItemsInit.WHITE_RUNE_SHARD : (this == BlocksInit.NEW_BLACK_RUNE_ORE ? ItemsInit.BLACK_RUNE_SHARD : Item.getItemFromBlock(this))))))))))));
    }

    @Override
    public int damageDropped(IBlockState p_damageDropped_1_) {
        return this == BlocksInit.LAPIS_ORE ? EnumDyeColor.BLUE.getDyeDamage() : 0;
    }

    @Override
    public ItemStack getItem(World p_getItem_1_, BlockPos p_getItem_2_, IBlockState p_getItem_3_) {
        return new ItemStack(this);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        return this == BlocksInit.COAL_ORE ? 3 : (this == BlocksInit.REDSTONE_ORE ? 6 : (this == BlocksInit.LAPIS_ORE ? 12 : (this == BlocksInit.DIAMOND_ORE ? 20 : (this == BlocksInit.EMERALD_ORE ? 22 : (this == BlocksInit.QUARTZ_ORE ? 16 : (this == BlocksInit.NEW_RED_RUNE_ORE ? 9 : (this == BlocksInit.NEW_YELLOW_RUNE_ORE ? 9 : (this == BlocksInit.NEW_GREEN_RUNE_ORE ? 9 : (this == BlocksInit.NEW_BLUE_RUNE_ORE ? 9 : (this == BlocksInit.NEW_WHITE_RUNE_ORE ? 9 : (this == BlocksInit.NEW_BLACK_RUNE_ORE ? 9 : 0)))))))))));
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
