package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.tileentity.TEPlantVase;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPlantVase extends Block implements IHasModel, ITileEntityProvider {
    public BlockPlantVase(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 0);
        setHardness(0.1f);
        setSoundType(SoundType.METAL);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        TEPlantVase tePlantVase = this.getTileEntity(worldIn, pos);

        if (tePlantVase == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack1 = tePlantVase.getFlowerItemStack();

            if (itemstack1.isEmpty())
            {
                if (!this.canBePotted(itemstack))
                {
                    return false;
                }

                tePlantVase.setItemStack(itemstack);

                if (!playerIn.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }
            }
            else
            {
                if (itemstack.isEmpty())
                {
                    playerIn.setHeldItem(hand, itemstack1);
                }
                else if (!playerIn.addItemStackToInventory(itemstack1))
                {
                    playerIn.dropItem(itemstack1, false);
                }

                tePlantVase.setItemStack(ItemStack.EMPTY);
            }

            tePlantVase.markDirty();
            worldIn.notifyBlockUpdate(pos, state, state, 3);
            return true;
        }
    }

    private boolean canBePotted(ItemStack stack)
    {
        Block block = Block.getBlockFromItem(stack.getItem());
        if (block instanceof BlockBush) {
            return true;
        }
        if (stack.getItem() instanceof ItemSeedFood) {
            return true;
        }
        return stack.getItem() instanceof ItemSeeds;
    }

    @Nullable
    private TEPlantVase getTileEntity(World worldIn, BlockPos pos)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof TEPlantVase ? (TEPlantVase)tileentity : null;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        TEPlantVase tePlantVase = this.getTileEntity(worldIn, pos);

        if (tePlantVase != null)
        {
            ItemStack itemstack = tePlantVase.getFlowerItemStack();

            if (!itemstack.isEmpty())
            {
                return itemstack;
            }
        }

        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TEPlantVase();
    }
}