package litewolf101.aztech.config;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiteWolf101 on Jan
 * /15/2019
 */
public class AzTechConfig {
    private static Configuration config = null;

    public static final String CATEGORY_DIMENSION = "dimension";

    public static int dimension_ID;
    public static boolean toogle_dimension_fog;
    public static int overworld_ore_frequency;
    public static int aztech_ore_frequency;
    public static int hut_frequency;
    public static int basic_dungeon_frequency;
    public static int portal_frequency;


    public static void preInit(){
        File configFile = new File(Loader.instance().getConfigDir(), "AzTech.cfg");
        config = new Configuration(configFile);
        syncFromFiles();
    }

    public static Configuration getConfig() {
        return config;
    }

    public static void clientPreInit() {

    }

    public static void syncFromFiles() {
        syncConfig(true, true);
    }

    public static void syncFromFields() {
        syncConfig(false, false);
    }

    private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig){
        if (loadFromConfigFile) {
            config.load();
        }
        //TODO fix categories
        //properties
        Property propertyDimensionID = config.get(CATEGORY_DIMENSION, "dimension_id", 17);
        propertyDimensionID.setLanguageKey("aztech.config.dimension.dimension_id.name");
        propertyDimensionID.setComment(I18n.format("aztech.config.dimension.dimension_id.comment"));

        Property propertyToggleDimensionalFog = config.get(CATEGORY_DIMENSION, "enable_dimensional_fog", true);
        propertyToggleDimensionalFog.setLanguageKey("aztech.config.dimension.toggle_dimensional_fog.name");
        propertyToggleDimensionalFog.setComment(I18n.format("aztech.config.dimension.toggle_dimensional_fog.comment"));

        Property propertyOvrwrldOreFreq = config.get(CATEGORY_DIMENSION, "overworld_ore_frequency", 20);
        propertyOvrwrldOreFreq.setLanguageKey("aztech.config.world.overworld_ore_frequency.name");
        propertyOvrwrldOreFreq.setComment(I18n.format("aztech.config.world.overworld_ore_frequency.comment"));

        Property propertyAzTechOreFreq = config.get(CATEGORY_DIMENSION, "aztech_ore_frequency", 13);
        propertyAzTechOreFreq.setLanguageKey("aztech.config.world.aztech_ore_frequency.name");
        propertyAzTechOreFreq.setComment(I18n.format("aztech.config.world.aztech_ore_frequency.comment"));

        Property propertyHutFreq = config.get(CATEGORY_DIMENSION, "hut_frequency", 150);
        propertyHutFreq.setLanguageKey("aztech.config.world.hut_frequency.name");
        propertyHutFreq.setComment(I18n.format("aztech.config.world.hut_frequency.comment"));

        Property propertyBasicDungeonFreq = config.get(CATEGORY_DIMENSION, "basic_dungeon_frequency", 50);
        propertyBasicDungeonFreq.setLanguageKey("aztech.config.world.basic_dungeon_frequency.name");
        propertyBasicDungeonFreq.setComment(I18n.format("aztech.config.world.basic_dungeon_frequency.comment"));

        Property propertyPortalFreq = config.get(CATEGORY_DIMENSION, "portal_frequency", 500);
        propertyPortalFreq.setLanguageKey("aztech.config.world.portal_frequency.name");
        propertyPortalFreq.setComment(I18n.format("aztech.config.world.portal_frequency.comment"));


        //list
        List<String> propertyOrderDimension = new ArrayList<String>();
        propertyOrderDimension.add(propertyDimensionID.getName());
        propertyOrderDimension.add(propertyToggleDimensionalFog.getName());
        propertyOrderDimension.add(propertyAzTechOreFreq.getName());
        propertyOrderDimension.add(propertyOvrwrldOreFreq.getName());
        propertyOrderDimension.add(propertyHutFreq.getName());
        propertyOrderDimension.add(propertyBasicDungeonFreq.getName());
        propertyOrderDimension.add(propertyPortalFreq.getName());

        config.setCategoryPropertyOrder(CATEGORY_DIMENSION, propertyOrderDimension);

        if (readFieldsFromConfig) {
            dimension_ID = propertyDimensionID.getInt();
            overworld_ore_frequency = propertyOvrwrldOreFreq.getInt();
            aztech_ore_frequency = propertyAzTechOreFreq.getInt();
            hut_frequency = propertyHutFreq.getInt();
            basic_dungeon_frequency = propertyBasicDungeonFreq.getInt();
            portal_frequency = propertyPortalFreq.getInt();
            toogle_dimension_fog = propertyToggleDimensionalFog.getBoolean();
        }

        propertyDimensionID.set(dimension_ID);
        propertyToggleDimensionalFog.set(toogle_dimension_fog);
        propertyOvrwrldOreFreq.set(overworld_ore_frequency);
        propertyAzTechOreFreq.set(aztech_ore_frequency);
        propertyHutFreq.set(hut_frequency);
        propertyBasicDungeonFreq.set(basic_dungeon_frequency);
        propertyPortalFreq.set(portal_frequency);

        if (config.hasChanged()) {
            config.save();
        }
    }
}
