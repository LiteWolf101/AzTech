package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumRuneState;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by LiteWolf101 on 9/28/2018.
 */
public class R2RTranslator extends Block implements IHasModel,IMetaName{
    public static final PropertyEnum<EnumRuneState.EnumType> ACTIVATION = PropertyEnum.<EnumRuneState.EnumType>create("activation_state", EnumRuneState.EnumType.class);
    public R2RTranslator(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.METAL);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE));
        setBlockUnbreakable();
        setTickRandomly(true);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int tickRate(World worldIn) {
        return 5;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleUpdate(pos, this, 5);
        if (!worldIn.isRemote)
        {
            if (!worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE), 3);
            }
            else if (worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE), 3);
            }
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.scheduleUpdate(pos, this, 5);
        if (!worldIn.isRemote)
        {
            if (!worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE), 3);
            }
            else if (worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE), 3);
            }
        }
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
            AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "r_to_r_translator_" + EnumRuneState.EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumRuneState.EnumType.values()[stack.getItemDamage()].getName();
    }
}
