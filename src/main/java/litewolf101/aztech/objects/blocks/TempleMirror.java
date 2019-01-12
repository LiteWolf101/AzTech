package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.tileentity.TETempleMirror;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.util.EnumFacing.Axis.Y;

/**
 * Created by LiteWolf101 on 10/6/2018.
 */
public class TempleMirror extends Block implements IHasModel, IMetaName, ITileEntityProvider {
    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);
    public static final PropertyBool FLIPPED = PropertyBool.create("flipped");
    public TempleMirror(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 1);
        setHardness(2f);
        setSoundType(SoundType.GLASS);
        setDefaultState(blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X).withProperty(FLIPPED, false));
        setResistance(100f);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AXIS, FLIPPED});
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(AXIS, getFacingFromEntity(pos, placer)).withProperty(FLIPPED, false));
    }

    public static EnumFacing.Axis getFacingFromEntity(BlockPos pos, EntityLivingBase entity) {
        if (MathHelper.abs((float) entity.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) entity.posZ - (float) pos.getZ()) < 2.0F) {
            double d0 = entity.posY + (double) entity.getEyeHeight();
            if (d0 - (double) pos.getY() > 2.0D) {
                return Y;
            }

            if ((double) pos.getY() - d0 > 0.0D) {
                return Y;
            }
        }
        return entity.getHorizontalFacing().getAxis();
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.X;
        Boolean flipped = this.getDefaultState().getValue(FLIPPED);
        if (meta == 0) {
            enumfacing$axis = EnumFacing.Axis.X;
            flipped = false;
        } else if (meta == 1) {
            enumfacing$axis = Y;
            flipped = false;
        } else if (meta == 2) {
            enumfacing$axis = EnumFacing.Axis.Z;
            flipped = false;
        } else if (meta == 3) {
            enumfacing$axis = EnumFacing.Axis.X;
            flipped = true;
        } else if (meta == 4) {
            enumfacing$axis = Y;
            flipped = true;
        } else if (meta == 5) {
            enumfacing$axis = EnumFacing.Axis.Z;
            flipped = true;
        }
        return this.getDefaultState().withProperty(AXIS, enumfacing$axis).withProperty(FLIPPED, flipped);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        if (!state.getValue(FLIPPED)){
            if (state.getValue(AXIS) == EnumFacing.Axis.X){
                i = 0;
            } else if (state.getValue(AXIS) == Y){
                i = 1;
            } else if (state.getValue(AXIS) == EnumFacing.Axis.Z){
                i = 2;
            }
        } else if(state.getValue(FLIPPED)){
            if (state.getValue(AXIS) == EnumFacing.Axis.X){
                i = 3;
            } else if (state.getValue(AXIS) == Y){
                i = 4;
            } else if (state.getValue(AXIS) == EnumFacing.Axis.Z){
                i = 5;
            }
        }

        return i;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(BlocksInit.TEMPLE_MIRROR, 1, 0);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState p_getRenderType_1_) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isFullCube(IBlockState p_isFullCube_1_) {
        return false;
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumFacing.Axis.values()[stack.getItemDamage()].getName();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TETempleMirror();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.getBlockState(pos).getValue(FLIPPED) == false) {
            worldIn.setBlockState(pos, state.withProperty(FLIPPED, true));
        } else if (worldIn.getBlockState(pos).getValue(FLIPPED) == true) {
            worldIn.setBlockState(pos, state.withProperty(FLIPPED, false));
        }
        return true;
    }
}
