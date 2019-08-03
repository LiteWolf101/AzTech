package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.tileentity.*;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.IRunePowerSource;
import litewolf101.aztech.utils.handlers.EnumStage;
import litewolf101.aztech.utils.handlers.MiscHandler;
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
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by LiteWolf101 on 9/21/2018.
 */
public class BlockSlaughtiveRune extends BlockContainer implements IHasModel, IMetaName, IRunePowerSource {

	public static final PropertyEnum<EnumStage.EnumType> STAGE = PropertyEnum.create("stage", EnumStage.EnumType.class);

	public BlockSlaughtiveRune(String name, Material material) {
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setSoundType(SoundType.STONE);
		setCreativeTab(AzTech.CREATIVE_TAB);
		setDefaultState(this.blockState.getBaseState().withProperty(STAGE, EnumStage.EnumType.STAGE_0));
		setHarvestLevel("pickaxe", 1);
		setHardness(2f);
		setTickRandomly(true);
		setResistance(100f);

		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
		MiscHandler.SOURCES.add(this.getDefaultState().withProperty(STAGE, EnumStage.EnumType.STAGE_6));
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {

		return this.getDefaultState().withProperty(STAGE, EnumStage.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {

		return state.getValue(STAGE).getMeta();
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
		if(worldIn.getBlockState(pos) == stateIn.withProperty(STAGE, EnumStage.EnumType.STAGE_6)) {
			worldIn.spawnParticle(EnumParticleTypes.REDSTONE, (x - 0.5) + random, y, (z - 0.5) + random1, 0, 0.1D, 0);
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
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, STAGE);
	}

	//TODO make this a tile entity

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void registerModels() {
		for(int i = 0; i < EnumStage.EnumType.values().length; i++) {
			AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "slaughtive_rune_" + EnumStage.EnumType.values()[i].getName(), "inventory");
		}
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumStage.EnumType.values()[stack.getItemDamage()].getName();
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if(meta == 0) {
			return new TESlaughtiveRune0();
		}
		else if(meta == 1) {
			return new TESlaughtiveRune1();
		}
		else if(meta == 2) {
			return new TESlaughtiveRune2();
		}
		else if(meta == 3) {
			return new TESlaughtiveRune3();
		}
		else if(meta == 4) {
			return new TESlaughtiveRune4();
		}
		else if(meta == 5) {
			return new TESlaughtiveRune5();
		}
		else if(meta == 6) {
			return new TESlaughtiveRune6();
		}
		else {
			return null;
		}
	}

	@Override
	public IBlockState isRunePowerSourceAt(World world, IBlockState state, EnumFacing facing, BlockPos pos) {
		return this.getDefaultState().withProperty(STAGE, EnumStage.EnumType.STAGE_6);
	}

}
