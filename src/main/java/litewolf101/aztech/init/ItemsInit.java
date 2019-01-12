package litewolf101.aztech.init;

import litewolf101.aztech.objects.items.AzTechPortalItem;
import litewolf101.aztech.objects.items.GeoluminescentObeliskItem;
import litewolf101.aztech.objects.items.ItemBase;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class ItemsInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item AZTECH_BADGE = new ItemBase("aztech_badge");
    public static final Item RED_RUNE_SHARD = new ItemBase("red_rune_shard");
    public static final Item YELLOW_RUNE_SHARD = new ItemBase("yellow_rune_shard");
    public static final Item GREEN_RUNE_SHARD = new ItemBase("green_rune_shard");
    public static final Item BLUE_RUNE_SHARD = new ItemBase("blue_rune_shard");
    public static final Item WHITE_RUNE_SHARD = new ItemBase("white_rune_shard");
    public static final Item BLACK_RUNE_SHARD = new ItemBase("black_rune_shard");
    public static final Item GEOLUMINESCENT_OBELISK = new GeoluminescentObeliskItem("geoluminescent_obelisk_item", BlocksInit.GEOLUMINESCENT_OBELISK);
    public static final Item AZTECH_PORTAL = new AzTechPortalItem("aztech_portal_item", BlocksInit.AZTECH_PORTAL);
}
