package litewolf101.aztech.objects.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.tileentity.TERuneLine;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumDirectional;
import litewolf101.aztech.utils.handlers.EnumRuneState;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.omg.PortableInterceptor.INACTIVE;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static litewolf101.aztech.objects.blocks.BlockSlaughtiveRune.STAGE;
import static litewolf101.aztech.objects.blocks.R2RTranslator.ACTIVATED;

/**
 * Created by LiteWolf101 on 9/28/2018.
 */
public class RuneLine extends BlockContainer implements IHasModel, IMetaName{
    public static final PropertyEnum<EnumRuneState.EnumType> ACTIVATION = PropertyEnum.<EnumRuneState.EnumType>create("activation_state", EnumRuneState.EnumType.class);
    public RuneLine(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.METAL);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE));
        setBlockUnbreakable();

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }
    @Override
    public int tickRate(World world) {
        return 15;
    }

    private static void changeToActiveBlock(World world, BlockPos pos, IBlockState state) {
        changeToBlockState(world, pos, state);
        world.playSound(null, pos, SoundEvents.BLOCK_NOTE_HAT, SoundCategory.BLOCKS, 0.3F, 0.6F);
        world.scheduleUpdate(pos, state.getBlock(), 10);
    }

    private static void changeToBlockState(World world, BlockPos pos, IBlockState state) {
        Block thereBlock = world.getBlockState(pos).getBlock();

        if (thereBlock == BlocksInit.RUNE_LINE) {
            world.setBlockState(pos, state, 3);
            world.markBlockRangeForRenderUpdate(pos, pos);
            world.notifyNeighborsRespectDebug(pos, thereBlock, false);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {

        if (world.isRemote) return;

        EnumRuneState.EnumType variant = state.getValue(ACTIVATION);


        if (variant == EnumRuneState.EnumType.INACTIVE && isPowerSource(world, pos)) {
            changeToActiveBlock(world, pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.POWERING_UP));
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {

        if (world.isRemote) return;

        EnumRuneState.EnumType variant = state.getValue(ACTIVATION);

        if (variant == EnumRuneState.EnumType.ACTIVE) {

            world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.POWERING_DOWN));
            world.scheduleUpdate(pos, this, 10);
            world.notifyNeighborsRespectDebug(pos, this, false);
            //world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);

            // activate all adjacent inactive vanish blocks
            for (EnumFacing e : EnumFacing.VALUES) {
                checkAndActivateBlock(world, pos.offset(e));
            }
        }
        if (variant == EnumRuneState.EnumType.POWERING_UP) {
            world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE));
            world.scheduleUpdate(pos, this, 10);
            world.notifyNeighborsRespectDebug(pos, this, false);
        }
        if (variant == EnumRuneState.EnumType.POWERING_DOWN) {
            world.setBlockState(pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE));
            world.scheduleUpdate(pos, this, 10);
            world.notifyNeighborsRespectDebug(pos, this, false);
        }
    }

    public static void checkAndActivateBlock(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block == BlocksInit.RUNE_LINE && state.getValue(ACTIVATION) == EnumRuneState.EnumType.INACTIVE) {
            changeToActiveBlock(world, pos, BlocksInit.RUNE_LINE.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.POWERING_UP));
        }
    }

    public boolean isPowerSource(World world, BlockPos pos){
        IBlockState source1 = BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATED, true);
        IBlockState source2 = BlocksInit.DETECTOR_RUNE.getDefaultState().withProperty(ACTIVATED, true);
        IBlockState source3 = BlocksInit.SLAUGHTIVE_RUNE.getDefaultState().withProperty(STAGE, EnumStage.EnumType.STAGE_6);
        if (world.getBlockState(pos.north()) == source1 | world.getBlockState(pos.north()) == source2 | world.getBlockState(pos.north()) == source3){
            return true;
        } else if (world.getBlockState(pos.east()) == source1 | world.getBlockState(pos.east()) == source2 | world.getBlockState(pos.east()) == source3){
            return true;
        } else if (world.getBlockState(pos.south()) == source1 | world.getBlockState(pos.south()) == source2 | world.getBlockState(pos.south()) == source3){
            return true;
        } else if (world.getBlockState(pos.west()) == source1 | world.getBlockState(pos.west()) == source2 | world.getBlockState(pos.west()) == source3){
            return true;
        } else if (world.getBlockState(pos.down()) == source1 | world.getBlockState(pos.down()) == source2 | world.getBlockState(pos.down()) == source3){
            return true;
        } else if (world.getBlockState(pos.up()) == source1 | world.getBlockState(pos.up()) == source2 | world.getBlockState(pos.up()) == source3){
            return true;
        } else return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        //this is done on purpose because only the first stage should show in the creative tab yet all the meta still exists
    }
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }


    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumRuneState.EnumType)state.getValue(ACTIVATION)).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {ACTIVATION});
    }

    @Override
    public void registerModels() {
        for(int i = 0; i < EnumRuneState.EnumType.values().length; i++)
        {
            AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "rune_line_" + EnumRuneState.EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumRuneState.EnumType.values()[stack.getItemDamage()].getName();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TERuneLine();
    }
}
