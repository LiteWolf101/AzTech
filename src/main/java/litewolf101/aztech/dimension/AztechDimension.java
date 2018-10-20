package litewolf101.aztech.dimension;

import litewolf101.aztech.utils.AztechWorldProvider;
import litewolf101.aztech.utils.Reference;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class AztechDimension {
    public static DimensionType aztech;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        aztech = DimensionType.register(Reference.MODID, "_aztech", 17, AztechWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(17, AztechDimension.aztech);
    }
}
