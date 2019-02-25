package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.tileentity.masterPortalConstruct;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumPortalPart;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class PortalMultiblock extends Block implements IHasModel, IMetaName, ITileEntityProvider{
    public static final PropertyEnum<EnumPortalPart.EnumType> PART = PropertyEnum.<EnumPortalPart.EnumType>create("part", EnumPortalPart.EnumType.class);
    public PortalMultiblock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setDefaultState(this.blockState.getBaseState().withProperty(PART, EnumPortalPart.EnumType.BOTTOM));

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (state.getValue(PART) == EnumPortalPart.EnumType.BOTTOM && world.getBlockState(pos).getBlock() == this){
            for (int i = 0; i <= 3; i++){
                world.setBlockToAir(pos.up(i));
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 7f, true);
            }
        }
        if (state.getValue(PART) == EnumPortalPart.EnumType.MIDDLE && world.getBlockState(pos).getBlock() == this){
            for (int i = -1; i <= 2; i++){
                world.setBlockToAir(pos.up(i));
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 7f, true);
            }
        }
        if (state.getValue(PART) == EnumPortalPart.EnumType.TOP && world.getBlockState(pos).getBlock() == this){
            for (int i = -2; i <= 1; i++){
                world.setBlockToAir(pos.up(i));
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 7f, true);
            }
        }
        if (state.getValue(PART) == EnumPortalPart.EnumType.BRACE && world.getBlockState(pos).getBlock() == this){
            for (int i = -3; i <= 0; i++){
                world.setBlockToAir(pos.up(i));
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0.5f, true);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {

        return this.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.byMetadata(meta));
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        if (blockState == blockState.withProperty(PART, EnumPortalPart.EnumType.BOTTOM) || blockState == blockState.withProperty(PART, EnumPortalPart.EnumType.MIDDLE)){
            return NULL_AABB;
        } else return FULL_BLOCK_AABB;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumPortalPart.EnumType)state.getValue(PART)).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(ItemsInit.AZTECH_PORTAL);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {PART});
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public void registerModels() {
        for(int i = 0; i < EnumPortalPart.EnumType.values().length; i++)
        {
            AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "aztech_portal_construct_" + EnumPortalPart.EnumType.values()[i].getName(), "inventory");
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new masterPortalConstruct();
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumPortalPart.EnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        if (state == state.withProperty(PART, EnumPortalPart.EnumType.BOTTOM) || state == state.withProperty(PART, EnumPortalPart.EnumType.MIDDLE)){
            return false;
        } else return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }
}
