package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by LiteWolf101 on Feb /07/2019
 */
public class AncientFarmland extends Block implements IHasModel, IMetaName {

	public static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);
	protected static final AxisAlignedBB FARMLAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
	protected static final AxisAlignedBB field_194405_c = new AxisAlignedBB(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);

	public AncientFarmland(String name, Material materialIn) {
		super(materialIn);
		setTranslationKey(name);
		setRegistryName(name);
		setSoundType(SoundType.GROUND);
		setCreativeTab(AzTech.CREATIVE_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setLightOpacity(255);
		this.setHardness(0.6F);

		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(MOISTURE, Integer.valueOf(meta & 7));
	}

	public int getMetaFromState(IBlockState state) {
		return state.getValue(MOISTURE).intValue();
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FARMLAND_AABB;
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		switch(side) {
			case UP:
				return true;
			case NORTH:
			case SOUTH:
			case WEST:
			case EAST:
				IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
				Block block = iblockstate.getBlock();
				return !iblockstate.isOpaqueCube() && block != Blocks.FARMLAND && block != Blocks.GRASS_PATH && block != BlocksInit.ANCIENT_FARMLAND;
			default:
				return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
		}
	}

	@SuppressWarnings("deprecation")
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = state.getValue(MOISTURE).intValue();

		if(!this.hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
			if(i > 0) {
				worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
			}
			else if(!this.hasCrops(worldIn, pos)) {
				turnToDirt(worldIn, pos);
			}
		}
		else if(i < 7) {
			worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i + 1)), 2);
		}
	}

	private boolean hasWater(World worldIn, BlockPos pos) {
		for(BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
			if(worldIn.getBlockState(blockpos$mutableblockpos).getMaterial() == Material.WATER) {
				return true;
			}
		}

		return false;
	}

	private boolean hasCrops(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos.up()).getBlock();
		return block instanceof net.minecraftforge.common.IPlantable && canSustainPlant(worldIn.getBlockState(pos), worldIn, pos, net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)block);
	}

	protected static void turnToDirt(World p_190970_0_, BlockPos worldIn) {
		p_190970_0_.setBlockState(worldIn, BlocksInit.ANCIENT_DIRT.getDefaultState());
		AxisAlignedBB axisalignedbb = field_194405_c.offset(worldIn);

		for(Entity entity : p_190970_0_.getEntitiesWithinAABBExcludingEntity(null, axisalignedbb)) {
			double d0 = Math.min(axisalignedbb.maxY - axisalignedbb.minY, axisalignedbb.maxY - entity.getEntityBoundingBox().minY);
			entity.setPositionAndUpdate(entity.posX, entity.posY + d0 + 0.001D, entity.posZ);
		}
	}

	@SuppressWarnings("deprecation")
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);

		if(worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
			turnToDirt(worldIn, pos);
		}
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);

		if(worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
			turnToDirt(worldIn, pos);
		}
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlocksInit.ANCIENT_DIRT);
	}

	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if(net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, BlocksInit.ANCIENT_DIRT.getDefaultState(), fallDistance, entityIn)) // Forge: Move logic to Entity#canTrample
		{
			turnToDirt(worldIn, pos);
		}

		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, MOISTURE);
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		return true;
	}

	@Override
	public boolean isFertile(World world, BlockPos pos) {
		return world.getBlockState(pos).getValue(MOISTURE).intValue() > 3;
	}

	@Override
	public void registerModels() {
		for(int i = 0; i < 7; i++) {
			AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "ancient_farmland_moist_" + i, "inventory");
		}
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		String name = null;
		switch(stack.getItemDamage()) {
			case 1:
				name = "moist_1";
			case 2:
				name = "moist_2";
			case 3:
				name = "moist_3";
			case 4:
				name = "moist_4";
			case 5:
				name = "moist_5";
			case 6:
				name = "moist_6";
			case 7:
				name = "moist_7";
				break;
			default:
				name = "moist_0";
		}
		return name;
	}

}
