package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumAzTechPlantType;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ShortGrass extends BlockBush implements IHasModel, IMetaName, IShearable {

	public static final PropertyEnum<EnumAzTechPlantType.EnumType> PLANT_TYPE = PropertyEnum.create("aztech_plant_type", EnumAzTechPlantType.EnumType.class);
	protected static final AxisAlignedBB SHORT_BB = new AxisAlignedBB(0, 0, 0, 1, 0.0625, 1);
	protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	public ShortGrass(String name, Material material) {
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setDefaultState(this.blockState.getBaseState().withProperty(PLANT_TYPE, EnumAzTechPlantType.EnumType.NORMAL));

		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(PLANT_TYPE, EnumAzTechPlantType.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(PLANT_TYPE).getMeta();
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(EnumAzTechPlantType.EnumType plantType$enumtype : EnumAzTechPlantType.EnumType.values()) {
			items.add(new ItemStack(this, 1, plantType$enumtype.getMeta()));
		}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PLANT_TYPE);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if(RANDOM.nextInt(8) != 0) {
			return;
		}
		ItemStack seed = net.minecraftforge.common.ForgeHooks.getGrassSeed(RANDOM, fortune);
		if(!seed.isEmpty()) {
			drops.add(seed);
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void registerModels() {
		for(int i = 0; i < EnumAzTechPlantType.EnumType.values().length; i++) {
			AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "short_grass_" + EnumAzTechPlantType.EnumType.values()[i].getName(), "inventory");
		}
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumAzTechPlantType.EnumType.values()[stack.getItemDamage()].getName();
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == BlocksInit.ANCIENT_GRASS || state.getBlock() == BlocksInit.ANCIENT_DIRT;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		boolean isFlat = source.getBlockState(pos) == state.withProperty(PLANT_TYPE, EnumAzTechPlantType.EnumType.FLAT);
		return isFlat ? SHORT_BB : TALL_GRASS_AABB;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(BlocksInit.SHORT_GRASS, 1, world.getBlockState(pos).getValue(PLANT_TYPE).getMeta()));
	}

}
