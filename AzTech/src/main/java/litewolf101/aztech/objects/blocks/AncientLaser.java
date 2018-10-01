package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumDirectional;
import litewolf101.aztech.utils.handlers.EnumRuneState;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

import static litewolf101.aztech.objects.blocks.R2RTranslator.ACTIVATION;

/**
 * Created by LiteWolf101 on 9/27/2018.
 */
public class AncientLaser extends Block implements IHasModel, IMetaName{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
    public AncientLaser(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(ACTIVATED, false));
        setLightLevel(1F);
        setHarvestLevel("pickaxe", 1);
        setHardness(1f);
        setTickRandomly(true);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int tickRate(World worldIn) {
        return 5;
    }

    /**@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleUpdate(pos, this, 5);
        if (!worldIn.isRemote)
            if (!worldIn.isRemote) {
                if (worldIn.getBlockState(pos.south()).getBlock() == BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE)) {
                    worldIn.setBlockState(pos, state.withProperty(ACTIVATED, true), 4);
                } else if (worldIn.getBlockState(pos.south()).getBlock() != BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE)) {
                    worldIn.scheduleUpdate(pos, this, 5);
                }
            }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        IBlockState blockState = BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE);
        IBlockState blockState2 = BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE);
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos.south()).getBlock() == blockState) {
                System.out.println("powered");
                worldIn.setBlockState(pos, state.withProperty(ACTIVATED, true), 4);
            } else if (worldIn.getBlockState(pos.south()).getBlock() != blockState) {
                System.out.println("unpowered");
                worldIn.scheduleUpdate(pos, this, 5);
            }
        }
        worldIn.scheduleUpdate(pos, this, 5);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos.south()).getBlock() == BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE)) {
                System.out.println("powered");
                worldIn.setBlockState(pos, state.withProperty(ACTIVATED, true), 4);
            } else if (worldIn.getBlockState(pos.south()).getBlock() != BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE)) {
                System.out.println("unpowered");
                worldIn.scheduleUpdate(pos, this, 5);
            }
        }
    }*/


    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!world.isRemote)
        {
            if (world.getBlockState(pos.north()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.scheduleUpdate(pos, this, 4);
            }
            else if (world.getBlockState(pos.east()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.scheduleUpdate(pos, this, 4);
            }
            else if (world.getBlockState(pos.south()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.scheduleUpdate(pos, this, 4);
            }
            else if (world.getBlockState(pos.west()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.scheduleUpdate(pos, this, 4);
            }
            else if (world.getBlockState(pos.up()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.scheduleUpdate(pos, this, 4);
            }
            else if (world.getBlockState(pos.down()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.scheduleUpdate(pos, this, 4);
            }

            else if (world.getBlockState(pos.north()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.SOUTH), 4);
            }
            else if (world.getBlockState(pos.east()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.WEST), 4);
            }
            else if (world.getBlockState(pos.south()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.NORTH), 4);
            }
            else if (world.getBlockState(pos.west()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.EAST), 4);
            }
            else if (world.getBlockState(pos.up()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.UP), 4);
            }
            else if (world.getBlockState(pos.down()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.DOWN), 4);
            }
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            //If the state is false
            if (world.getBlockState(pos.north()) != BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, state.withProperty(ACTIVATED, false), 4);
            }
            else if (world.getBlockState(pos.east()) != BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, state.withProperty(ACTIVATED, false), 4);
            }
            else if (world.getBlockState(pos.south()) != BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, state.withProperty(ACTIVATED, false), 4);
            }
            else if (world.getBlockState(pos.west()) != BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, state.withProperty(ACTIVATED, false), 4);
            }
            else if (world.getBlockState(pos.up()) != BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, state.withProperty(ACTIVATED, false), 4);
            }
            else if (world.getBlockState(pos.down()) != BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, state.withProperty(ACTIVATED, false), 4);
            }
        }
        world.scheduleUpdate(pos, this, 4);
        System.out.println("tick");
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            if (world.getBlockState(pos.north()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.SOUTH), 4);
            }
            else if (world.getBlockState(pos.east()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.WEST), 4);
            }
            else if (world.getBlockState(pos.south()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.NORTH), 4);
            }
            else if (world.getBlockState(pos.west()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.EAST), 4);
            }
            else if (world.getBlockState(pos.up()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.UP), 4);
            }
            else if (world.getBlockState(pos.down()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(0))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.DOWN), 4);
            }

            else if (world.getBlockState(pos.north()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.SOUTH), 4);
            }
            else if (world.getBlockState(pos.east()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.WEST), 4);
            }
            else if (world.getBlockState(pos.south()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.NORTH), 4);
            }
            else if (world.getBlockState(pos.west()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.EAST), 4);
            }
            else if (world.getBlockState(pos.up()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.UP), 4);
            }
            else if (world.getBlockState(pos.down()) == BlocksInit.R2R_TRANSLATOR.getStateFromMeta(1))
            {
                world.setBlockState(pos, BlocksInit.ANCIENT_LASER.getDefaultState().withProperty(ACTIVATED, true).withProperty(FACING, EnumFacing.DOWN), 4);
            }
        }
        world.scheduleUpdate(pos, this, this.tickRate(world));
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)).withProperty(ACTIVATED, false));
    }

    public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase entity) {
        if (MathHelper.abs((float) entity.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) entity.posZ - (float) pos.getZ()) < 2.0F) {
            double d0 = entity.posY + (double) entity.getEyeHeight();
            if (d0 - (double) pos.getY() > 2.0D) {
                return EnumFacing.UP;
            }

            if ((double) pos.getY() - d0 > 0.0D) {
                return EnumFacing.DOWN;
            }
        }
        return entity.getHorizontalFacing().getOpposite();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 4;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(ACTIVATED)).booleanValue()) {
            i |= 7;
        }
        return i;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(BlocksInit.ANCIENT_LASER, 1, 0);
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, new IProperty[] {FACING, ACTIVATED});
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
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumFacing.values()[stack.getItemDamage()].getName();
    }

    public boolean isBlockBeingActivated(BlockPos pos, World world){
        if (world.getBlockState(pos.south()).getBlock() == BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE)){
            System.out.println("powered");
            return true;
        } else return false;
    }
}
