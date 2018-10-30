package litewolf101.aztech.init;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.objects.mobs.MobEyeGuardian;
import litewolf101.aztech.objects.mobs.MobEyeMaster;
import litewolf101.aztech.objects.mobs.render.RenderEyeGuardian;
import litewolf101.aztech.objects.mobs.render.RenderEyeMaster;
import litewolf101.aztech.utils.Reference;
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
        //EntityRegistry.registerModEntity(WMMEntityNames.FLOATING_STAR, MobFloatingStar.class, "floating_star", id++, WuffysMagicMayhem.instance, 64, 3, true, 16777215, 16776652);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "eye_guardian"), MobEyeGuardian.class, "eye_guardian", id++, AzTech.instance, 64, 3, true, 16765727, 11011584);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "eye_master"), MobEyeMaster.class, "eye_master", id++, AzTech.instance, 64, 3, true, 16765727, 5212623);

        //EntityRegistry.addSpawn(MobFloatingStar.class, 100, 1, 1, EnumCreatureType.AMBIENT, WMMWorldUtils.biomeStarlight);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        //RenderingRegistry.registerEntityRenderingHandler(MobFloatingStar.class, RenderFloatingStar.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(MobEyeGuardian.class, RenderEyeGuardian.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(MobEyeMaster.class, RenderEyeMaster.FACTORY);
    }
}
