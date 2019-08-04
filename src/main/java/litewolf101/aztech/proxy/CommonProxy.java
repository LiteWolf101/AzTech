package litewolf101.aztech.proxy;

import litewolf101.aztech.config.AzTechConfig;
import litewolf101.aztech.dimension.AztechDimension;
import litewolf101.aztech.init.ModEntities;
import litewolf101.aztech.tileentity.*;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import litewolf101.aztech.utils.handlers.PacketHandler;
import litewolf101.aztech.world.worldgen.structures.WorldGenCustomStructures;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class CommonProxy {

	public static Configuration config;

	public static Configuration getConfig() {
		return config;
	}

	public void preInit(FMLPreInitializationEvent event) {
		File directory = event.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(), "aztech.cfg"));
		AzTechConfig.readConfig();

		AztechDimension.init();
		ModEntities.init();
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
		PacketHandler.registerMessages(Reference.MODID);
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
		if(config.hasChanged()) {
			System.out.println("Changed");
			config.save();
		}
	}

	public void registerItemRenderer(Item item, int meta, String id) {
	}

	public void registerVariantRenderer(Item item, int meta, String filename, String id) {
	}

	public void RegisterTileEntityRender() {
	}

	@SuppressWarnings("deprecation")
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TETempleRuneBlock.class, Reference.MODID + ":te_temple_rune_block");
		GameRegistry.registerTileEntity(TESlaughtiveRune0.class, Reference.MODID + ":te_slaughtive_rune_0");
		GameRegistry.registerTileEntity(TESlaughtiveRune1.class, Reference.MODID + ":te_slaughtive_rune_1");
		GameRegistry.registerTileEntity(TESlaughtiveRune2.class, Reference.MODID + ":te_slaughtive_rune_2");
		GameRegistry.registerTileEntity(TESlaughtiveRune3.class, Reference.MODID + ":te_slaughtive_rune_3");
		GameRegistry.registerTileEntity(TESlaughtiveRune4.class, Reference.MODID + ":te_slaughtive_rune_4");
		GameRegistry.registerTileEntity(TESlaughtiveRune5.class, Reference.MODID + ":te_slaughtive_rune_5");
		GameRegistry.registerTileEntity(TESlaughtiveRune6.class, Reference.MODID + ":te_slaughtive_rune_6");
		GameRegistry.registerTileEntity(TEAncientLaser.class, Reference.MODID + ":te_ancient_laser");
		GameRegistry.registerTileEntity(TEObjectorRune.class, Reference.MODID + ":te_objector_rune");
		GameRegistry.registerTileEntity(TEGeoObelisk.class, Reference.MODID + ":te_geo_obelisk");
		GameRegistry.registerTileEntity(TEPortalConstruct.class, Reference.MODID + ":te_portal_construct_basic");
		GameRegistry.registerTileEntity(masterPortalConstruct.class, Reference.MODID + ":te_master_portal_construct");
		GameRegistry.registerTileEntity(TETempleMirror.class, Reference.MODID + ":te_temple_mirror");
		GameRegistry.registerTileEntity(TileEntityInsertiveRune.class, Reference.MODID + ":te_insertive_rune");
		GameRegistry.registerTileEntity(TEEnemyEmitterRune.class, Reference.MODID + ":te_enemy_emitter_rune");
	}

	public void spawnParticle(World world, AzTechParticleTypes particle, double x, double y, double z, double velX, double velY, double velZ) {
	}

}
