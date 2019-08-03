package litewolf101.aztech.config;

import litewolf101.aztech.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by LiteWolf101 on Jan /15/2019
 */
public class AzTechConfig {

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_DIMENSION = "dimension";

	public static int dimension_ID;
	public static boolean toogle_dimension_fog;
	public static int overworld_ore_frequency;
	public static int aztech_ore_frequency;
	public static int hut_frequency;
	public static int basic_dungeon_frequency;
	public static int portal_frequency;

	public static void readConfig() {
		Configuration cfg = CommonProxy.getConfig();
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initDimensionConfig(cfg);
		}
		catch(Exception e1) {
			System.out.println("Error loading config!" + e1);
		}
		finally {
			if(cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
		dimension_ID = cfg.getInt("AzTech Dimension ID", CATEGORY_GENERAL, 17, 2, 9001, "The dimension ID number for the AzTech dimension");
		toogle_dimension_fog = cfg.getBoolean("Toggle Dimension Fog", CATEGORY_GENERAL, false, "Set to true to enable fog in the AzTech dimension");
	}

	private static void initDimensionConfig(Configuration cfg) {
		overworld_ore_frequency = cfg.getInt("Overworld Ore Frequency", CATEGORY_DIMENSION, 20, 1, 9001, "How frequent AzTech ores spawn in the overworld. Lower number = higher chance");
		aztech_ore_frequency = cfg.getInt("AzTech Ore Frequency", CATEGORY_DIMENSION, 13, 1, 9001, "How frequent ores spawn in the AzTech dimension. Lower number = higher chance");
		hut_frequency = cfg.getInt("Hut Frequency", CATEGORY_DIMENSION, 150, 1, 9001, "How frequent huts spawn in the AzTech dimension. Lower number = higher chance");
		basic_dungeon_frequency = cfg.getInt("Basic Dungeon Frequency", CATEGORY_DIMENSION, 50, 1, 9001, "How frequent basic dungeons spawn in the AzTech dimension. Lower number = higher chance");
		portal_frequency = cfg.getInt("Portal Frequency", CATEGORY_DIMENSION, 500, 1, 9001, "How AzTech portals spawn in the overworld and AzTech dimension. Lower number = higher chance");
	}

}