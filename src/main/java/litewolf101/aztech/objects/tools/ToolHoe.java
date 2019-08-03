package litewolf101.aztech.objects.tools;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by LiteWolf101 on Jan /13/2019
 */
public class ToolHoe extends ItemHoe implements IHasModel {

	public ToolHoe(String name, Item.ToolMaterial material) {
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(AzTech.CREATIVE_TAB);

		ItemsInit.ITEMS.add(this);
	}

	@SuppressWarnings("incomplete-switch")
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);

		if(!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
			return EnumActionResult.FAIL;
		}
		else {
			int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
			if(hook != 0) {
				return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
			}

			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if(facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())) {
				if(block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
					this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
					return EnumActionResult.SUCCESS;
				}
				else if(block == BlocksInit.ANCIENT_GRASS || block == BlocksInit.ANCIENT_DIRT) {
					this.setBlock(itemstack, player, worldIn, pos, BlocksInit.ANCIENT_FARMLAND.getDefaultState());
					return EnumActionResult.SUCCESS;
				}

				if(block == Blocks.DIRT) {
					switch(iblockstate.getValue(BlockDirt.VARIANT)) {
						case DIRT:
							this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
							return EnumActionResult.SUCCESS;
						case COARSE_DIRT:
							this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
							return EnumActionResult.SUCCESS;
					}
				}
				if(block == BlocksInit.ANCIENT_DIRT) {
					//Change to ancient farmland for later
					this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
					return EnumActionResult.SUCCESS;
				}
			}

			return EnumActionResult.PASS;
		}
	}

	/**
	 * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise the damage on the stack.
	 */
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		return true;
	}

	protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state) {
		worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

		if(!worldIn.isRemote) {
			worldIn.setBlockState(pos, state, 11);
			stack.damageItem(1, player);
		}
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	public void registerModels() {
		AzTech.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
