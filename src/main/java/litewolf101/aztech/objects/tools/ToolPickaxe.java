package litewolf101.aztech.objects.tools;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.item.ItemPickaxe;

/**
 * Created by LiteWolf101 on Jan /13/2019
 */
public class ToolPickaxe extends ItemPickaxe implements IHasModel {

	public ToolPickaxe(String name, ToolMaterial material) {
		super(material);
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