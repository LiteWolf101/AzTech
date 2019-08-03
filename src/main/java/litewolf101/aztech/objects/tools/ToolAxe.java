package litewolf101.aztech.objects.tools;

import com.google.common.collect.Sets;
import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;

import java.util.Set;

/**
 * Created by LiteWolf101 on Jan /13/2019
 */
public class ToolAxe extends ItemTool implements IHasModel {

	private static final Set<Block> EFFECTIVE = Sets.newHashSet(Blocks.ACACIA_DOOR, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_STAIRS, Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.BED, Blocks.BIRCH_DOOR, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_STAIRS, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.CHEST, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.CACTUS, Blocks.DOUBLE_WOODEN_SLAB, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_STAIRS, Blocks.HAY_BLOCK, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_STAIRS, Blocks.LADDER, Blocks.LOG, Blocks.LOG2, Blocks.LEAVES, Blocks.LEAVES2, Blocks.LEVER, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.MELON_STEM, Blocks.OAK_DOOR, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_STAIRS, Blocks.PUMPKIN, Blocks.PLANKS, Blocks.PUMPKIN_STEM, Blocks.RED_MUSHROOM_BLOCK, Blocks.SLIME_BLOCK, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_STAIRS, Blocks.STANDING_BANNER, Blocks.STANDING_SIGN, Blocks.TRAPDOOR, Blocks.TRAPPED_CHEST, Blocks.WOODEN_SLAB, Blocks.WOODEN_PRESSURE_PLATE, Blocks.WOODEN_BUTTON, Blocks.WALL_BANNER, Blocks.WALL_SIGN, Blocks.WEB);

	public ToolAxe(String name, ToolMaterial material) {
		super(material, EFFECTIVE);
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(AzTech.CREATIVE_TAB);

		ItemsInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		AzTech.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
