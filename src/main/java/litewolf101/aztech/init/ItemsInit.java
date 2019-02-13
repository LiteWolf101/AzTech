package litewolf101.aztech.init;

import litewolf101.aztech.objects.Armour.ArmourBase;
import litewolf101.aztech.objects.Tools.*;
import litewolf101.aztech.objects.items.AzTechPortalItem;
import litewolf101.aztech.objects.items.GeoluminescentObeliskItem;
import litewolf101.aztech.objects.items.ItemBase;
import litewolf101.aztech.objects.items.ItemFoodSorghum;
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
    public static final Item.ToolMaterial TOOL_RUNE = EnumHelper.addToolMaterial("tool_rune", 2, 657, 2.3f, 1.7f, 13);

    //materials armor
    public static final ItemArmor.ArmorMaterial ARMOUR_RED_RUNE = EnumHelper.addArmorMaterial("armour_red_rune", Reference.MODID + ":red_rune", 20, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7f);
    public static final ItemArmor.ArmorMaterial ARMOUR_YELLOW_RUNE = EnumHelper.addArmorMaterial("armour_yellow_rune", Reference.MODID + ":yellow_rune", 20, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7f);
    public static final ItemArmor.ArmorMaterial ARMOUR_GREEN_RUNE = EnumHelper.addArmorMaterial("armour_green_rune", Reference.MODID + ":green_rune", 20, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7f);
    public static final ItemArmor.ArmorMaterial ARMOUR_BLUE_RUNE = EnumHelper.addArmorMaterial("armour_blue_rune", Reference.MODID + ":blue_rune", 20, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7f);
    public static final ItemArmor.ArmorMaterial ARMOUR_WHITE_RUNE = EnumHelper.addArmorMaterial("armour_white_rune", Reference.MODID + ":white_rune", 20, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7f);
    public static final ItemArmor.ArmorMaterial ARMOUR_BLACK_RUNE = EnumHelper.addArmorMaterial("armour_back_rune", Reference.MODID + ":black_rune", 20, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7f);

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

    //food
    public static final Item SORGHUM = new ItemFoodSorghum("food_sorghum");

    //tools/weapons
    public static final Item RED_RUNE_HOE = new ToolHoe("red_rune_hoe", TOOL_RUNE);
    public static final Item RED_RUNE_AXE = new ToolAxe("red_rune_axe", TOOL_RUNE);
    public static final Item RED_RUNE_SHOVEL = new ToolShovel("red_rune_shovel", TOOL_RUNE);
    public static final Item RED_RUNE_SWORD = new ToolSword("red_rune_sword", TOOL_RUNE);
    public static final Item RED_RUNE_PICKAXE = new ToolPickaxe("red_rune_pickaxe", TOOL_RUNE);

    public static final Item YELLOW_RUNE_HOE = new ToolHoe("yellow_rune_hoe", TOOL_RUNE);
    public static final Item YELLOW_RUNE_AXE = new ToolAxe("yellow_rune_axe", TOOL_RUNE);
    public static final Item YELLOW_RUNE_SHOVEL = new ToolShovel("yellow_rune_shovel", TOOL_RUNE);
    public static final Item YELLOW_RUNE_SWORD = new ToolSword("yellow_rune_sword", TOOL_RUNE);
    public static final Item YELLOW_RUNE_PICKAXE = new ToolPickaxe("yellow_rune_pickaxe", TOOL_RUNE);

    public static final Item GREEN_RUNE_HOE = new ToolHoe("green_rune_hoe", TOOL_RUNE);
    public static final Item GREEN_RUNE_AXE = new ToolAxe("green_rune_axe", TOOL_RUNE);
    public static final Item GREEN_RUNE_SHOVEL = new ToolShovel("green_rune_shovel", TOOL_RUNE);
    public static final Item GREEN_RUNE_SWORD = new ToolSword("green_rune_sword", TOOL_RUNE);
    public static final Item GREEN_RUNE_PICKAXE = new ToolPickaxe("green_rune_pickaxe", TOOL_RUNE);

    public static final Item BLUE_RUNE_HOE = new ToolHoe("blue_rune_hoe", TOOL_RUNE);
    public static final Item BLUE_RUNE_AXE = new ToolAxe("blue_rune_axe", TOOL_RUNE);
    public static final Item BLUE_RUNE_SHOVEL = new ToolShovel("blue_rune_shovel", TOOL_RUNE);
    public static final Item BLUE_RUNE_SWORD = new ToolSword("blue_rune_sword", TOOL_RUNE);
    public static final Item BLUE_RUNE_PICKAXE = new ToolPickaxe("blue_rune_pickaxe", TOOL_RUNE);

    public static final Item WHITE_RUNE_HOE = new ToolHoe("white_rune_hoe", TOOL_RUNE);
    public static final Item WHITE_RUNE_AXE = new ToolAxe("white_rune_axe", TOOL_RUNE);
    public static final Item WHITE_RUNE_SHOVEL = new ToolShovel("white_rune_shovel", TOOL_RUNE);
    public static final Item WHITE_RUNE_SWORD = new ToolSword("white_rune_sword", TOOL_RUNE);
    public static final Item WHITE_RUNE_PICKAXE = new ToolPickaxe("white_rune_pickaxe", TOOL_RUNE);

    public static final Item BLACK_RUNE_HOE = new ToolHoe("black_rune_hoe", TOOL_RUNE);
    public static final Item BLACK_RUNE_AXE = new ToolAxe("black_rune_axe", TOOL_RUNE);
    public static final Item BLACK_RUNE_SHOVEL = new ToolShovel("black_rune_shovel", TOOL_RUNE);
    public static final Item BLACK_RUNE_SWORD = new ToolSword("black_rune_sword", TOOL_RUNE);
    public static final Item BLACK_RUNE_PICKAXE = new ToolPickaxe("black_rune_pickaxe", TOOL_RUNE);

    //armor
    public static final Item RED_RUNE_HELMET = new ArmourBase("red_rune_helmet", ARMOUR_RED_RUNE, 1, EntityEquipmentSlot.HEAD);
    public static final Item RED_RUNE_CHESTPLATE = new ArmourBase("red_rune_chestplate", ARMOUR_RED_RUNE, 1, EntityEquipmentSlot.CHEST);
    public static final Item RED_RUNE_LEGGINGS = new ArmourBase("red_rune_leggings", ARMOUR_RED_RUNE, 2, EntityEquipmentSlot.LEGS);
    public static final Item RED_RUNE_BOOTS = new ArmourBase("red_rune_boots", ARMOUR_RED_RUNE, 1, EntityEquipmentSlot.FEET);

    public static final Item YELLOW_RUNE_HELMET = new ArmourBase("yellow_rune_helmet", ARMOUR_YELLOW_RUNE, 1, EntityEquipmentSlot.HEAD);
    public static final Item YELLOW_RUNE_CHESTPLATE = new ArmourBase("yellow_rune_chestplate", ARMOUR_YELLOW_RUNE, 1, EntityEquipmentSlot.CHEST);
    public static final Item YELLOW_RUNE_LEGGINGS = new ArmourBase("yellow_rune_leggings", ARMOUR_YELLOW_RUNE, 2, EntityEquipmentSlot.LEGS);
    public static final Item YELLOW_RUNE_BOOTS = new ArmourBase("yellow_rune_boots", ARMOUR_YELLOW_RUNE, 1, EntityEquipmentSlot.FEET);

    public static final Item GREEN_RUNE_HELMET = new ArmourBase("green_rune_helmet", ARMOUR_GREEN_RUNE, 1, EntityEquipmentSlot.HEAD);
    public static final Item GREEN_RUNE_CHESTPLATE = new ArmourBase("green_rune_chestplate", ARMOUR_GREEN_RUNE, 1, EntityEquipmentSlot.CHEST);
    public static final Item GREEN_RUNE_LEGGINGS = new ArmourBase("green_rune_leggings", ARMOUR_GREEN_RUNE, 2, EntityEquipmentSlot.LEGS);
    public static final Item GREEN_RUNE_BOOTS = new ArmourBase("green_rune_boots", ARMOUR_GREEN_RUNE, 1, EntityEquipmentSlot.FEET);

    public static final Item BLUE_RUNE_HELMET = new ArmourBase("blue_rune_helmet", ARMOUR_BLUE_RUNE, 1, EntityEquipmentSlot.HEAD);
    public static final Item BLUE_RUNE_CHESTPLATE = new ArmourBase("blue_rune_chestplate", ARMOUR_BLUE_RUNE, 1, EntityEquipmentSlot.CHEST);
    public static final Item BLUE_RUNE_LEGGINGS = new ArmourBase("blue_rune_leggings", ARMOUR_BLUE_RUNE, 2, EntityEquipmentSlot.LEGS);
    public static final Item BLUE_RUNE_BOOTS = new ArmourBase("blue_rune_boots", ARMOUR_BLUE_RUNE, 1, EntityEquipmentSlot.FEET);

    public static final Item WHITE_RUNE_HELMET = new ArmourBase("white_rune_helmet", ARMOUR_WHITE_RUNE, 1, EntityEquipmentSlot.HEAD);
    public static final Item WHITE_RUNE_CHESTPLATE = new ArmourBase("white_rune_chestplate", ARMOUR_WHITE_RUNE, 1, EntityEquipmentSlot.CHEST);
    public static final Item WHITE_RUNE_LEGGINGS = new ArmourBase("white_rune_leggings", ARMOUR_WHITE_RUNE, 2, EntityEquipmentSlot.LEGS);
    public static final Item WHITE_RUNE_BOOTS = new ArmourBase("white_rune_boots", ARMOUR_WHITE_RUNE, 1, EntityEquipmentSlot.FEET);

    public static final Item BLACK_RUNE_HELMET = new ArmourBase("black_rune_helmet", ARMOUR_BLACK_RUNE, 1, EntityEquipmentSlot.HEAD);
    public static final Item BLACK_RUNE_CHESTPLATE = new ArmourBase("black_rune_chestplate", ARMOUR_BLACK_RUNE, 1, EntityEquipmentSlot.CHEST);
    public static final Item BLACK_RUNE_LEGGINGS = new ArmourBase("black_rune_leggings", ARMOUR_BLACK_RUNE, 2, EntityEquipmentSlot.LEGS);
    public static final Item BLACK_RUNE_BOOTS = new ArmourBase("black_rune_boots", ARMOUR_BLACK_RUNE, 1, EntityEquipmentSlot.FEET);
}
