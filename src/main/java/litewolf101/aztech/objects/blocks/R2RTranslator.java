package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.IRunePowerSource;
import litewolf101.aztech.utils.handlers.MiscHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by LiteWolf101 on 9/28/2018.
 */
public class R2RTranslator extends Block implements IHasModel, IMetaName, IRunePowerSource {

	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

	public R2RTranslator(String name, Material material) {
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setSoundType(SoundType.METAL);
		setCreativeTab(AzTech.CREATIVE_TAB);
		setDefaultState(this.blockState.getBaseState().withProperty(ACTIVATED, false));
		setBlockUnbreakable();
		setTickRandomly(true);

		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
		MiscHandler.SOURCES.add(getDefaultState().withProperty(ACTIVATED, true));
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		boolean activated = meta > 0;
		return getDefaultState().withProperty(ACTIVATED, activated);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		boolean activated = state.getValue(ACTIVATED);
		return (activated ? 1 : 0);
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		worldIn.scheduleUpdate(pos, this, 5);
		if(!worldIn.isRemote) {
			if(!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATED, false), 3);
			}
			else if(worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATED, true), 3);
			}
		}
	}

	@Override
	public int tickRate(World worldIn) {
		return 5;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, 5);
		if(!worldIn.isRemote) {
			if(!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATED, false), 3);
			}
			else if(worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, BlocksInit.R2R_TRANSLATOR.getDefaultState().withProperty(ACTIVATED, true), 3);
			}
		}
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		//this is done on purpose because only the first stage should show in the creative tab yet all the meta still exists
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, ACTIVATED);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this));
	}

	@Override
	public void registerModels() {
		AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		String name = null;
		switch(stack.getItemDamage()) {
			case 1:
				name = "on";

				break;
			default:
				name = "off";
		}
		return name;
	}

	@Override
	public IBlockState isRunePowerSourceAt(World world, IBlockState state, EnumFacing facing, BlockPos pos) {
		return getDefaultState().withProperty(ACTIVATED, true);
	}

}