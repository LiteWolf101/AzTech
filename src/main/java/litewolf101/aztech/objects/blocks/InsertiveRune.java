package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.tileentity.TileEntityInsertiveRune;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.gui.GUIHandler;
import litewolf101.aztech.utils.handlers.MiscHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by LiteWolf101 on 9/28/2018.
 */
public class InsertiveRune extends BlockContainer implements IHasModel, IMetaName {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
    public static final PropertyBool LOCKED = PropertyBool.create("locked");
    private static boolean keepInventory;

    public InsertiveRune(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 1);
        setHardness(2f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVATED, false).withProperty(LOCKED, true));
        setResistance(100f);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
        MiscHandler.SOURCES.add(getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVATED, true));
        MiscHandler.SOURCES.add(getDefaultState().withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVATED, true));
        MiscHandler.SOURCES.add(getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVATED, true));
        MiscHandler.SOURCES.add(getDefaultState().withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVATED, true));
    }

    public static void setState(boolean active, boolean locked, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active) {
            worldIn.setBlockState(pos, BlocksInit.INSERTIVE_RUNE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVATED, true).withProperty(LOCKED, locked), 3);
            worldIn.setBlockState(pos, BlocksInit.INSERTIVE_RUNE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVATED, true).withProperty(LOCKED, locked), 3);
        } else {
            worldIn.setBlockState(pos, BlocksInit.INSERTIVE_RUNE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVATED, false).withProperty(LOCKED, locked), 3);
            worldIn.setBlockState(pos, BlocksInit.INSERTIVE_RUNE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVATED, false).withProperty(LOCKED, locked), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityInsertiveRune) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), ((TileEntityInsertiveRune) tileentity).getStackInSlot(0));
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing direction = EnumFacing.SOUTH;
        boolean locked = meta < 8;
        boolean activated = (meta > 3 && meta < 8) || (meta > 11);
        int i = meta - (locked ? - 8 : 0) + (activated ? - 4 : 0);
        if (i == 0) {
            direction = EnumFacing.SOUTH;
        }
        if (i == 1) {
            direction = EnumFacing.WEST;
        }
        if (i == 2) {
            direction = EnumFacing.NORTH;
        }
        if (i == 3) {
            direction = EnumFacing.EAST;
        }

        //EnumFacing direction = EnumFacing.VALUES[meta > 5 ? meta - 5 : meta];
        return getDefaultState().withProperty(FACING, direction).withProperty(ACTIVATED, activated).withProperty(LOCKED, locked);

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing direction = state.getValue(FACING);
        boolean activated = state.getValue(ACTIVATED);
        boolean locked = state.getValue(LOCKED);
        return direction.getHorizontalIndex() + (activated ? 4 : 0) + (locked ? 8 : 0);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlocksInit.INSERTIVE_RUNE);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn.getHeldItemMainhand() == ItemStack.EMPTY) {
                playerIn.openGui(AzTech.instance, GUIHandler.BLOCK_INSERTIVE_RUNE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            } else if (playerIn.isSpectator()) {
                playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Cannot open gui in spectator mode!"));
            } else if (playerIn.getHeldItemMainhand().getItem() == ItemsInit.AZTECH_BADGE) {
                if (worldIn.getBlockState(pos).getValue(LOCKED) == false) {
                    System.out.println("Locking!");
                    worldIn.setBlockState(pos, state.withProperty(LOCKED, true), 3);
                    /*TileEntity te = worldIn.getTileEntity(pos);
                    if (te instanceof TileEntityInsertiveRune) {
                        ((TileEntityInsertiveRune) te).setLocked(true);
                    }*/
                } else {
                    System.out.println("Unlocking!");
                    worldIn.setBlockState(pos, state.withProperty(LOCKED, false), 3);
                    /*TileEntity te = worldIn.getTileEntity(pos);
                    if (te instanceof TileEntityInsertiveRune) {
                        ((TileEntityInsertiveRune) te).setLocked(false);
                    }*/
                }
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ACTIVATED, LOCKED);
    }

    @Override
    public ItemStack getPickBlock(@Nonnull IBlockState state, RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player) {
        return new ItemStack(BlocksInit.INSERTIVE_RUNE, 1, 0);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityInsertiveRune();
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumFacing.values()[stack.getItemDamage()].getName();
    }

}
