package litewolf101.aztech.objects.Armour;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemHoe;

/**
 * Created by LiteWolf101 on Jan
 * /13/2019
 */
public class ArmourBase extends ItemArmor implements IHasModel{
    public ArmourBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
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
