package litewolf101.aztech.world.biome;

import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.world.biome.BiomeAncientForest;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

/**
 * Created by LiteWolf101 on 10/20/2018.
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class AztechBiomes {
    public static ArrayList<Biome> BIOME_LIST = new ArrayList<Biome>();

    public static final BiomeAncientForest biomeAncientForest = new BiomeAncientForest();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        registry.register(biomeAncientForest);

        BiomeDictionary.addTypes(biomeAncientForest, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SPOOKY);

        //BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeAncientForest, 20));


        //BiomeManager.addSpawnBiome(biomeAncientForest);

        //BiomeProvider.allowedBiomes.add(biomeAncientForest);


        addBiomesToList();
    }

    public static void addBiomesToList(){
        BIOME_LIST.add(biomeAncientForest);
    }
}
