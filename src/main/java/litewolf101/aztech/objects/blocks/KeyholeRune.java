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
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by LiteWolf101 on 9/28/2018.
 */
public class KeyholeRune extends Block implements IHasModel, IMetaName {

	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

	public KeyholeRune(String name, Material material) {
		super(material);
		setTranslationKey(name);
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

}
