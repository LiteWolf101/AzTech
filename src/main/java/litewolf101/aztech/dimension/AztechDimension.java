package litewolf101.aztech.dimension;

import litewolf101.aztech.config.AzTechConfig;
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
		aztech = DimensionType.register(Reference.MODID, "_aztech", AzTechConfig.dimension_ID, AztechWorldProvider.class, false);
	}

	private static void registerDimensions() {
		DimensionManager.registerDimension(AzTechConfig.dimension_ID, AztechDimension.aztech);
	}

}
