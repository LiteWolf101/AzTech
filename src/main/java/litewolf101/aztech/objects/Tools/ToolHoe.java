package litewolf101.aztech.objects.Tools;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;

/**
 * Created by LiteWolf101 on Jan
 * /13/2019
 */
public class ToolHoe extends ItemHoe implements IHasModel{
    public ToolHoe(String name, Item.ToolMaterial material) {
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
