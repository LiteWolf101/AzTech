package litewolf101.aztech.objects.items;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.item.ItemSoup;

/**
 * Created by LiteWolf101 on Feb /13/2019
 */
public class ItemSorghumPopcorn extends ItemSoup implements IHasModel {

	public ItemSorghumPopcorn(String name) {
		super(8);
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
