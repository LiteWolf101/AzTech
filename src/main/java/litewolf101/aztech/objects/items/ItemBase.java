package litewolf101.aztech.objects.items;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.item.Item;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
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
