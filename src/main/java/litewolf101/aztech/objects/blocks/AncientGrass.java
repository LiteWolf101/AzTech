package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

/**
 * Created by LiteWolf101 on 9/25/2018.
 */
public class AncientGrass extends Block implements IHasModel, IGrowable{
    public AncientGrass(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setHarvestLevel("shovel", 0);
        setHardness(0.5f);
        setSoundType(SoundType.PLANT);
        setTickRandomly(true);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getLightOpacity(world, pos.up()) > 2) {
                world.setBlockState(pos, BlocksInit.ANCIENT_DIRT.getDefaultState());
            } else {
                if (world.getLightFromNeighbors(pos.up()) >= 5) {
                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !world.isBlockLoaded(blockpos)) {
                            return;
                        }
                        IBlockState iblockstate = world.getBlockState(blockpos.up());
                        IBlockState iblockstate1 = world.getBlockState(blockpos);

                        if (iblockstate1.getBlock() == BlocksInit.ANCIENT_DIRT && world.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(world, pos.up()) <= 2) {
                            world.setBlockState(blockpos, BlocksInit.ANCIENT_GRASS.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return BlocksInit.ANCIENT_DIRT.getItemDropped(BlocksInit.ANCIENT_DIRT.getDefaultState(), rand, fortune);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (worldIn.isAirBlock(blockpos1))
                    {
                        if (rand.nextInt(8) == 0)
                        {
                            worldIn.getBiome(blockpos1).plantFlower(worldIn, rand, blockpos1);
                        }
                        else
                        {
                            IBlockState iblockstate1 = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);

                            if (Blocks.TALLGRASS.canBlockStay(worldIn, blockpos1, iblockstate1))
                            {
                                worldIn.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (worldIn.getBlockState(blockpos1.down()).getBlock() != Blocks.GRASS || worldIn.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }

                ++j;
            }
        }
    }
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
        if (plantType == EnumPlantType.Plains){
            return true;
        }
        return false;
    }
}
