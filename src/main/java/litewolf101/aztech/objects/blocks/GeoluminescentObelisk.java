package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.tileentity.TEGeoObelisk;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumHalf;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by LiteWolf101 on 10/18/2018.
 */
public class GeoluminescentObelisk extends Block implements IHasModel, IMetaName, ITileEntityProvider{
    public static final PropertyEnum<EnumHalf.EnumType> HALF = PropertyEnum.<EnumHalf.EnumType>create("half", EnumHalf.EnumType.class);
    public GeoluminescentObelisk(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 2);
        setHardness(2f);
        setLightLevel(0.8f);
        setDefaultState(this.blockState.getBaseState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        //return state.getValue(HALF) == EnumHalf.EnumType.TOP? Items.AIR:ItemsInit.GEOLUMINESCENT_OBELISK;
        return ItemsInit.GEOLUMINESCENT_OBELISK;
    }

    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        BlockPos down = pos.down();
        BlockPos up = pos.up();
        if(state.getValue(HALF) == EnumHalf.EnumType.TOP && world.getBlockState(down).getBlock() == this) {
            world.setBlockToAir(down);
        }

        if(state.getValue(HALF) == EnumHalf.EnumType.BOTTOM && world.getBlockState(up).getBlock() == this) {
            if(player.capabilities.isCreativeMode) {
                world.setBlockToAir(pos);
            }
            world.setBlockToAir(up);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {

        return this.getDefaultState().withProperty(HALF, EnumHalf.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumHalf.EnumType)state.getValue(HALF)).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(ItemsInit.GEOLUMINESCENT_OBELISK);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {HALF});
    }

    @Override
    public void registerModels() {
        for(int i = 0; i < EnumHalf.EnumType.values().length; i++)
        {
            AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "geoluminescent_obelisk_" + EnumHalf.EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumHalf.EnumType.values()[stack.getItemDamage()].getName();
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        if (pos.getY() >= worldIn.getHeight() - 1)
        {
            return false;
        }
        else
        {
            IBlockState state = worldIn.getBlockState(pos.down());
            return (state.isTopSolid() || state.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEGeoObelisk();
    }
}
