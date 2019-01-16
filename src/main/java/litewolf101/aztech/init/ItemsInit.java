package litewolf101.aztech.init;

import litewolf101.aztech.objects.Armour.ArmourBase;
import litewolf101.aztech.objects.Tools.*;
import litewolf101.aztech.objects.items.AzTechPortalItem;
import litewolf101.aztech.objects.items.GeoluminescentObeliskItem;
import litewolf101.aztech.objects.items.ItemBase;
import litewolf101.aztech.utils.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class ItemsInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //materials tools
    public static final Item.ToolMaterial TOOL_RED_RUNE = EnumHelper.addToolMaterial("tool_red_rune", 2, 657, 2.3f, 1.7f, 13);

    //materials armor
    public static final ItemArmor.ArmorMaterial ARMOUR_RED_RUNE = EnumHelper.addArmorMaterial("armour_red_rune", Reference.MODID + ":red_rune", 20, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7f);

    //items
    public static final Item AZTECH_BADGE = new ItemBase("aztech_badge");
    public static final Item RED_RUNE_SHARD = new ItemBase("red_rune_shard");
    public static final Item YELLOW_RUNE_SHARD = new ItemBase("yellow_rune_shard");
    public static final Item GREEN_RUNE_SHARD = new ItemBase("green_rune_shard");
    public static final Item BLUE_RUNE_SHARD = new ItemBase("blue_rune_shard");
    public static final Item WHITE_RUNE_SHARD = new ItemBase("white_rune_shard");
    public static final Item BLACK_RUNE_SHARD = new ItemBase("black_rune_shard");
    public static final Item GEOLUMINESCENT_OBELISK = new GeoluminescentObeliskItem("geoluminescent_obelisk_item", BlocksInit.GEOLUMINESCENT_OBELISK);
    public static final Item AZTECH_PORTAL = new AzTechPortalItem("aztech_portal_item", BlocksInit.AZTECH_PORTAL);

    //tools/weapons
    public static final Item RED_RUNE_HOE = new ToolHoe("red_rune_hoe", TOOL_RED_RUNE);

    public static final Item RED_RUNE_AXE = new ToolAxe("red_rune_axe", TOOL_RED_RUNE);

    public static final Item RED_RUNE_SHOVEL = new ToolShovel("red_rune_shovel", TOOL_RED_RUNE);

    public static final Item RED_RUNE_SWORD = new ToolSword("red_rune_sword", TOOL_RED_RUNE);

    public static final Item RED_RUNE_PICKAXE = new ToolPickaxe("red_rune_pickaxe", TOOL_RED_RUNE);

    //armor
    public static final Item RED_RUNE_HELMET = new ArmourBase("red_rune_helmet", ARMOUR_RED_RUNE, 1, EntityEquipmentSlot.HEAD);

    public static final Item RED_RUNE_CHESTPLATE = new ArmourBase("red_rune_chestplate", ARMOUR_RED_RUNE, 1, EntityEquipmentSlot.CHEST);

    public static final Item RED_RUNE_LEGGINGS = new ArmourBase("red_rune_leggings", ARMOUR_RED_RUNE, 2, EntityEquipmentSlot.LEGS);

    public static final Item RED_RUNE_BOOTS = new ArmourBase("red_rune_boots", ARMOUR_RED_RUNE, 1, EntityEquipmentSlot.FEET);
}
