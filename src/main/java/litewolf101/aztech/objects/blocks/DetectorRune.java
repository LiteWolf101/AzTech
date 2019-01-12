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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by LiteWolf101 on 9/25/2018.
 */
public class DetectorRune extends Block implements IHasModel, IMetaName{
    public static final PropertyEnum<EnumRuneState.EnumType> ACTIVATION = PropertyEnum.<EnumRuneState.EnumType>create("activation_state", EnumRuneState.EnumType.class);
    public DetectorRune(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE));
        setHarvestLevel("pickaxe", 1);
        setHardness(2f);
        setTickRandomly(true);
        setResistance(100f);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        this.randomDisplayTick(state,worldIn, pos, rand);
        worldIn.scheduleUpdate(pos, this, 20);
        if (state.getValue(ACTIVATION).equals(EnumRuneState.EnumType.ACTIVE))
        {
            worldIn.setBlockState(pos, getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.INACTIVE), 3);
        }
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity)
    {
        if(entity instanceof EntityPlayer) {
            this.activate(world, pos);
        }
    }

    private void activate(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getValue(ACTIVATION).equals(EnumRuneState.EnumType.INACTIVE))
        {
            world.setBlockState(pos, getDefaultState().withProperty(ACTIVATION, EnumRuneState.EnumType.ACTIVE), 3);
            //for (EnumFacing enumfacing : EnumFacing.values())
            //{
                //world.notifyNeighborsOfStateChange(pos.offset(enumfacing), this);
            //}
        }
        world.scheduleUpdate(pos, this, this.tickRate(world));
    }

    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 1.2D;
        double d2 = (double)pos.getZ() + 0.5D;
        if (state.getValue(ACTIVATION).equals(EnumRuneState.EnumType.ACTIVE)){
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleUpdate(pos, this, 20);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
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
            AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "detector_rune_" + EnumRuneState.EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumRuneState.EnumType.values()[stack.getItemDamage()].getName();
    }


}
