package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.init.BlocksInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockTempleNormalDoubleSlab extends BlockSlabBase {

    public BlockTempleNormalDoubleSlab(String name, Material material, BlockSlab half, float hardness, int toolLevel, float resistance) {
        super(name, material, half);
        this.blockHardness = hardness;
        this.setHarvestLevel("pickaxe", toolLevel);
        this.blockResistance = resistance;
    }

    public boolean isDouble() {
        return true;
    }

    @Override
    public Block getHalfBase() {
        return BlocksInit.TEMPLE_NORMAL_SLAB_HALF;
    }
}
