package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.tileentity.TETempleRuneBlock;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import litewolf101.aztech.utils.handlers.EnumRuneColor;
import net.minecraft.block.BlockContainer;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by LiteWolf101 on 9/29/2018.
 */
public class TempleRuneBlock extends BlockContainer implements IHasModel, IMetaName {

	public static final PropertyEnum<EnumRuneColor.EnumType> RUNE_COLOR = PropertyEnum.create("rune_color", EnumRuneColor.EnumType.class);

	public TempleRuneBlock(String name, Material material) {
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setSoundType(SoundType.STONE);
		setCreativeTab(AzTech.CREATIVE_TAB);
		setDefaultState(this.blockState.getBaseState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.RED));
		setHarvestLevel("pickaxe", 3);
		setHardness(4f);
		setLightLevel(0.5f);
		setResistance(1000f);

		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {

		return this.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {

		return state.getValue(RUNE_COLOR).getMeta();
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		double randx = rand.nextDouble() + x;
		double randy = rand.nextDouble() + y;
		double randz = rand.nextDouble() + z;

		if(worldIn.getBlockState(pos).getValue(RUNE_COLOR) == EnumRuneColor.EnumType.RED) {
			AzTech.proxy.spawnParticle(worldIn, AzTechParticleTypes.RED_SPARKLE, randx, randy, randz, 0, 0, 0);
		}
		else if(worldIn.getBlockState(pos).getValue(RUNE_COLOR) == EnumRuneColor.EnumType.YELLOW) {
			AzTech.proxy.spawnParticle(worldIn, AzTechParticleTypes.YELLOW_SPARKLE, randx, randy, randz, 0, 0, 0);
		}
		else if(worldIn.getBlockState(pos).getValue(RUNE_COLOR) == EnumRuneColor.EnumType.GREEN) {
			AzTech.proxy.spawnParticle(worldIn, AzTechParticleTypes.GREEN_SPARKLE, randx, randy, randz, 0, 0, 0);
		}
		else if(worldIn.getBlockState(pos).getValue(RUNE_COLOR) == EnumRuneColor.EnumType.BLUE) {
			AzTech.proxy.spawnParticle(worldIn, AzTechParticleTypes.BLUE_SPARKLE, randx, randy, randz, 0, 0, 0);
		}
		else if(worldIn.getBlockState(pos).getValue(RUNE_COLOR) == EnumRuneColor.EnumType.WHITE) {
			AzTech.proxy.spawnParticle(worldIn, AzTechParticleTypes.WHITE_SPARKLE, randx, randy, randz, 0, 0, 0);
		}
		else if(worldIn.getBlockState(pos).getValue(RUNE_COLOR) == EnumRuneColor.EnumType.BLACK) {
			AzTech.proxy.spawnParticle(worldIn, AzTechParticleTypes.BLACK_SPARKLE, randx, randy, randz, 0, 0, 0);
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(RUNE_COLOR).getMeta();
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {

		for(EnumRuneColor.EnumType runeColor$enumtype : EnumRuneColor.EnumType.values()) {

			items.add(new ItemStack(this, 1, runeColor$enumtype.getMeta()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, RUNE_COLOR);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void registerModels() {
		for(int i = 0; i < EnumRuneColor.EnumType.values().length; i++) {
			AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, EnumRuneColor.EnumType.values()[i].getName() + "_temple_rune_block", "inventory");
		}
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumRuneColor.EnumType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TETempleRuneBlock();
	}

}
