package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class RedRuneLine extends RuneLine implements IHasModel, IMetaName {

	public RedRuneLine(String name) {
		super();
		setTranslationKey(name);
		setRegistryName(name);
		setDefaultState(blockState.getBaseState().withProperty(ACTIVATED, false));
		setCreativeTab(AzTech.CREATIVE_TAB);  //remove after test
		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState().withProperty(ACTIVATED, false);
		switch(meta) {
			case 1:
				state = this.getDefaultState().withProperty(ACTIVATED, true);
			default:
				this.getDefaultState().withProperty(ACTIVATED, false);
		}
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return this.getDefaultState().getValue(ACTIVATED) ? 1 : 0;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
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
