package litewolf101.aztech.init;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.objects.entitymisc.EntityDustDevil;
import litewolf101.aztech.objects.mobs.*;
import litewolf101.aztech.objects.mobs.render.*;
import litewolf101.aztech.objects.projectiles.ProjectileEyeLaser;
import litewolf101.aztech.objects.projectiles.render.RenderEyeLaser;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.world.biome.AztechBiomes;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by LiteWolf101 on 10/26/2018.
 */
public class ModEntities {

	public static void init() {
		int id = 1;
		//Normal
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "eye_guardian"), MobEyeGuardian.class, "eye_guardian", id++, AzTech.instance, 64, 3, true, 16765727, 11011584);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "eye_master"), MobEyeMaster.class, "eye_master", id++, AzTech.instance, 64, 3, true, 16765727, 5212623);
		//TODO Pyronant doesn't fit with the theme of this mod. Will be moved to a misc mod or something, I don't know.
		//EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "pyronant"), MobPyronant.class, "pyronant", id++, AzTech.instance, 64, 3, true, 4930138, 10455985);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "spark"), MobSpark.class, "spark", id++, AzTech.instance, 64, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ancient_squid"), MobAncientSquid.class, "ancient_squid", id++, AzTech.instance, 64, 3, true, 0, 455);

		//Bosses
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ultimate_eye"), BossUltimateEye.class, "ultimate_eye", id++, AzTech.instance, 64, 3, true, 255, 9462);

		//Projectiles
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "eye_laser"), ProjectileEyeLaser.class, "eye_laser", id++, AzTech.instance, 64, 3, true);

		//Misc
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "dust_devil"), EntityDustDevil.class, "dust_devil", id++, AzTech.instance, 64, 3, true);

		//Spawns
		EntitySpawnPlacementRegistry.setPlacementType(MobAncientSquid.class, EntityLiving.SpawnPlacementType.IN_WATER);
		EntityRegistry.addSpawn(MobAncientSquid.class, 100, 4, 4, EnumCreatureType.WATER_CREATURE, AztechBiomes.biomeAncientOcean);

	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		RenderingRegistry.registerEntityRenderingHandler(MobEyeGuardian.class, RenderEyeGuardian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(MobEyeMaster.class, RenderEyeMaster.FACTORY);
		//RenderingRegistry.registerEntityRenderingHandler(MobPyronant.class, RenderPyronant.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ProjectileEyeLaser.class, RenderEyeLaser.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(BossUltimateEye.class, RenderUltimateEye.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(MobSpark.class, RenderSpark.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(MobAncientSquid.class, RenderAncientSquid.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDustDevil.class, RenderDustDevil.FACTORY);
	}

}
