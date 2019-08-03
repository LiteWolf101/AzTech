package litewolf101.aztech.world.biome;

import litewolf101.aztech.utils.Reference;
import net.minecraft.world.biome.Biome;
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

	public static final BiomeAncientForest biomeAncientForest = new BiomeAncientForest();
	public static final BiomeAridLands biomeAridLands = new BiomeAridLands();
	public static final BiomeMurkySwamp biomeMurkySwamp = new BiomeMurkySwamp();
	public static final BiomeAncientOcean biomeAncientOcean = new BiomeAncientOcean();
	public static final BiomeAncientJungle biomeAncientJungle = new BiomeAncientJungle();
	public static ArrayList<Biome> BIOME_LIST = new ArrayList<Biome>();

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		IForgeRegistry<Biome> registry = event.getRegistry();

		registerBiome(registry, biomeAncientForest, "ancient_forest", BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SPOOKY);
		registerBiome(registry, biomeAridLands, "arid_lands", BiomeManager.BiomeType.DESERT, 20, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT);
		registerBiome(registry, biomeMurkySwamp, "murky_swamp", BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET);
		registerBiome(registry, biomeAncientOcean, "ancient_ocean", BiomeManager.BiomeType.WARM, 15, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET);
		registerBiome(registry, biomeAncientJungle, "ancient_jungle", BiomeManager.BiomeType.WARM, 30, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.JUNGLE);
	}

	private static <T extends Biome> void registerBiome(final IForgeRegistry<Biome> registry, final T biome, final String biomeName, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
		registry.register(biome.setRegistryName(Reference.MODID, biomeName));
		BiomeDictionary.addTypes(biome, types);
		//BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
		for(int x = 0; x < weight; x++) {
			BIOME_LIST.add(biome); //temp hack for now
		}
	}

}
