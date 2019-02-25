package litewolf101.aztech.objects.Tools;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.item.ItemSpade;

/**
 * Created by LiteWolf101 on Jan
 * /13/2019
 */
public class ToolShovel extends ItemSpade implements IHasModel{
    public ToolShovel(String name, ToolMaterial material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AzTech.CREATIVE_TAB);

        ItemsInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
