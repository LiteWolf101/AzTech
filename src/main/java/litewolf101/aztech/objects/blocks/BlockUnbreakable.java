package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Created by LiteWolf101 on 9/24/2018.
 */
public class BlockUnbreakable extends Block implements IHasModel {

	public BlockUnbreakable(String name, Material material) {
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(AzTech.CREATIVE_TAB);
		setBlockUnbreakable();
		setResistance(2000);

		BlocksInit.BLOCKS.add(this);
		ItemsInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

}
