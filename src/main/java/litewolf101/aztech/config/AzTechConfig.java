package litewolf101.aztech.config;

import litewolf101.aztech.utils.Reference;
import net.minecraftforge.common.config.Config;

/**
 * Created by LiteWolf101 on Jan
 * /15/2019
 */
@Config(modid = Reference.MODID, name = "aztech_config", type = Config.Type.INSTANCE)
public class AzTechConfig {
    @Config.LangKey("config.toggle_fog")
    @Config.RequiresMcRestart
    @Config.Comment("Set to true to enable fog in the AzTech dimension")
    public static boolean toogle_dimension_fog = false;

    @Config.LangKey("config.dim_id")
    @Config.RequiresMcRestart
    @Config.Comment("Dimension ID")
    public static int dimension_ID = 17;

    @Config.LangKey("config.overworld_ore_freq")
    @Config.RequiresWorldRestart
    @Config.Comment("How often AzTech ores spawn in the overworld (Higher number=lower chance)")
    public static int overworld_ore_frequency = 20;

    @Config.LangKey("config.aztech_ore_freq")
    @Config.RequiresWorldRestart
    @Config.Comment("How often AzTech ores spawn in the AzTech dimension (Higher number=lower chance)")
    public static int aztech_ore_frequency = 13;

    @Config.LangKey("config.aztech_portal_freq")
    @Config.RequiresWorldRestart
    @Config.Comment("How often AzTech portals spawn in the overworld (Higher number=lower chance)")
    public static int portal_frequency = 500;

    @Config.LangKey("config.aztech_hut_freq")
    @Config.RequiresWorldRestart
    @Config.Comment("How often huts spawn in the AzTech dimension (Higher number=lower chance)")
    public static int hut_frequency = 150;

    @Config.LangKey("config.aztech_basic_dungeon_freq")
    @Config.RequiresWorldRestart
    @Config.Comment("How often basic dungeons spawn in the AzTech dimension (Higher number=lower chance)")
    public static int basic_dungeon_frequency = 50;
}