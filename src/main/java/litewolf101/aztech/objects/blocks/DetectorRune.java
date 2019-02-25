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
import net.minecraft.block.properties.PropertyBool;
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
    public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
    public DetectorRune(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(ACTIVATED, false));
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
        if (state.getValue(ACTIVATED).equals(true))
        {
            worldIn.setBlockState(pos, getDefaultState().withProperty(ACTIVATED, false), 3);
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
        if (world.getBlockState(pos).getValue(ACTIVATED).equals(false))
        {
            world.setBlockState(pos, getDefaultState().withProperty(ACTIVATED, true), 3);
        }
        world.scheduleUpdate(pos, this, this.tickRate(world));
    }

    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 1.2D;
        double d2 = (double)pos.getZ() + 0.5D;
        if (state.getValue(ACTIVATED).equals(true)){
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
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState().withProperty(ACTIVATED, false);
        switch (meta) {
            case 1:
                state = this.getDefaultState().withProperty(ACTIVATED, true);
            default: this.getDefaultState().withProperty(ACTIVATED, false);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return this.getDefaultState().getValue(ACTIVATED) ? 1 : 0;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {ACTIVATED});
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        String name = null;
        switch (stack.getItemDamage()){
            case 1:
                name = "on";

                break;
            default:
                name = "off";
        }
        return name;
    }
}
