package litewolf101.aztech;

import litewolf101.aztech.commands.CommandAzTech;
import litewolf101.aztech.config.AzTechConfig;
import litewolf101.aztech.init.FurnaceRecipes;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.proxy.CommonProxy;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.utils.handlers.RegistryHandler;
import litewolf101.aztech.world.worldgen.ores.WorldGenAzTechOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT_VERSIONS)
public class AzTech {

    @Mod.Instance
    public static AzTech instance;

    @SidedProxy(clientSide = "litewolf101.aztech.proxy.ClientProxy", serverSide = "litewolf101.aztech.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs CREATIVE_TAB = new CreativeTabs(Reference.MODID) {

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemsInit.AZTECH_BADGE);
        }
    };

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        System.out.println(Reference.MODID + ":preInit");
        proxy.preInit(event);
        proxy.registerTileEntities();
        proxy.RegisterTileEntityRender();
        GameRegistry.registerWorldGenerator(new WorldGenAzTechOres(), 3);

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        System.out.println(Reference.MODID + ":init");
        proxy.init(event);
        FurnaceRecipes.registerFurnaceRecipes();
        RegistryHandler.otherRegistries();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        System.out.println(Reference.MODID + ":postInit");
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandAzTech());
    }
}
