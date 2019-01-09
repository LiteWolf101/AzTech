package litewolf101.aztech.utils;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

/**
 * Created by LiteWolf101 on 1/8/2019.
 */
public class AzTechLootTables extends LootTableList {
    public static final ResourceLocation EYE_GUARDIAN = register("entities/eye_guardian");


    private static ResourceLocation register(String id) {
        return register(new ResourceLocation("aztech", id));
    }
}