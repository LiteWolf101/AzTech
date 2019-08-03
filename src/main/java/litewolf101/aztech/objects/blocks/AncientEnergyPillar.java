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
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AncientEnergyPillar extends Block implements IHasModel, IMetaName {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public AncientEnergyPillar(String name, Material material) {
		super(material);
		setRegistryName(name);
		setTranslationKey(name);
		setSoundType(SoundType.STONE);
		setCreativeTab(AzTech.CREATIVE_TAB);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		setLightLevel(1F);
		setHarvestLevel("pickaxe", 1);
		setHardness(0.5f);
		setResistance(100f);

		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing direction = EnumFacing.VALUES[meta > 5 ? meta - 5 : meta];
		return getDefaultState().withProperty(FACING, direction);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing direction = state.getValue(FACING);
		return direction.getIndex();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)));
	}

	public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase entity) {
		if(MathHelper.abs((float)entity.posX - (float)pos.getX()) < 2.0F && MathHelper.abs((float)entity.posZ - (float)pos.getZ()) < 2.0F) {
			double d0 = entity.posY + (double)entity.getEyeHeight();
			if(d0 - (double)pos.getY() > 2.0D) {
				return EnumFacing.UP;
			}

			if((double)pos.getY() - d0 > 0.0D) {
				return EnumFacing.DOWN;
			}
		}
		return entity.getHorizontalFacing().getOpposite();
	}

	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, FACING);
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