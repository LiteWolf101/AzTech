package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTempleNormalHalfSlab extends BlockSlabBase implements IHasModel {
    public BlockTempleNormalHalfSlab(String name, Material material, BlockSlab half, BlockSlab doubleHalf, int hardness, int toolLevel, float resistance) {
        super(name, material, half);
        //System.out.println(half);
        this.blockHardness = hardness;
        this.setHarvestLevel("pickaxe", toolLevel);
        this.blockResistance = resistance;

        ItemsInit.ITEMS.add(new ItemSlab(this, this, doubleHalf).setRegistryName(name));
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public Block getHalfBase() {
        return BlocksInit.TEMPLE_NORMAL_SLAB_HALF;
    }
}
