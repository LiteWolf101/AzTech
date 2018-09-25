package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumEmpowerType;
import litewolf101.aztech.utils.handlers.EnumStage;
import litewolf101.aztech.utils.handlers.EnumTempleStoneType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by LiteWolf101 on 9/24/2018.
 */
public class EmpowerRune extends Block implements IHasModel, IMetaName{
    public static final PropertyEnum<EnumEmpowerType.EnumType> EMPOWER_TYPE = PropertyEnum.<EnumEmpowerType.EnumType>create("empower_type", EnumEmpowerType.EnumType.class);
    public EmpowerRune(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(EMPOWER_TYPE, EnumEmpowerType.EnumType.HOSTILE));
        setHarvestLevel("pickaxe", 1);
        setHardness(2f);
        setTickRandomly(true);

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
        giveEffect(worldIn, state, pos);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleUpdate(pos, this, 20);
        giveEffect(worldIn, state, pos);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumEmpowerType.EnumType)state.getValue(EMPOWER_TYPE)).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {

        for(EnumEmpowerType.EnumType empowerType$enumtype : EnumEmpowerType.EnumType.values()) {

            items.add(new ItemStack(this, 1, empowerType$enumtype.getMeta()));
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {

        return this.getDefaultState().withProperty(EMPOWER_TYPE, EnumEmpowerType.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        return ((EnumEmpowerType.EnumType)state.getValue(EMPOWER_TYPE)).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, new IProperty[] {EMPOWER_TYPE});
    }

    @Override
    public void registerModels() {
        for(int i = 0; i < EnumEmpowerType.EnumType.values().length; i++)
        {
            AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, EnumEmpowerType.EnumType.values()[i].getName() + "_empower_rune", "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumEmpowerType.EnumType.values()[stack.getItemDamage()].getName();
    }

    public void giveEffect(World world, IBlockState state, BlockPos pos){
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        AxisAlignedBB detectbb = new AxisAlignedBB(pos, pos.add(1, 1, 1)).grow(5D, 1D, 5D);
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, detectbb);
        for (EntityLivingBase entity : entities) {
            if (world.getBlockState(pos) == state.withProperty(EMPOWER_TYPE, EnumEmpowerType.EnumType.HOSTILE)){
                if (entity instanceof IMob) {
                    entity.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 40, 2));
                }
            }
            if (world.getBlockState(pos) == state.withProperty(EMPOWER_TYPE, EnumEmpowerType.EnumType.FRIENDLY)){
                if (entity instanceof EntityPlayer) {
                    entity.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 40, 2));
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.5;
        double z = pos.getZ() + 0.5;
        double random = rand.nextDouble();
        double random1 = rand.nextDouble();
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x + 5, y, z + 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z + 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x - 5, y, z + 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x - 5, y, z, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x - 5, y, z - 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z - 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x + 5, y, z - 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x + 5, y, z, 0, 0, 0);
    }
}
