package litewolf101.aztech.utils;

import litewolf101.aztech.world.biome.BiomeAncientForest;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by LiteWolf101 on 10/20/2018.
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class AztechWorldUtils {
    public static final BiomeAncientForest biomeAncientForest = new BiomeAncientForest();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        registry.register(biomeAncientForest);

        BiomeDictionary.addTypes(biomeAncientForest, BiomeDictionary.Type.FOREST);

        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeAncientForest, 1000));


        BiomeManager.addSpawnBiome(biomeAncientForest);

        BiomeProvider.allowedBiomes.add(biomeAncientForest);
    }
}
