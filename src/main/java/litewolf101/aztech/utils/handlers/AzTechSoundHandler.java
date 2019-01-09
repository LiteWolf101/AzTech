package litewolf101.aztech.utils.handlers;

import litewolf101.aztech.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Created by LiteWolf101 on 10/26/2018.
 */
public class AzTechSoundHandler {
    public static SoundEvent ENTITY_EYE_GUARDIAN_AMBIENT;
    public static SoundEvent ENTITY_EYE_GUARDIAN_HURT;
    public static SoundEvent ENTITY_EYE_GUARDIAN_DEATH;
    public static SoundEvent ENTITY_EYE_MASTER_AMBIENT;
    public static SoundEvent PROJECTILE_EYE_LASER_FIRED;
    public static SoundEvent PORTAL_POWER_UP;
    public static SoundEvent PORTAL_AMBIENT;


    public static void init() {
        ENTITY_EYE_GUARDIAN_AMBIENT = registerSounds("entity.eye_guardian.eye_ambient");
        ENTITY_EYE_GUARDIAN_HURT = registerSounds("entity.eye_guardian.eye_hurt");
        ENTITY_EYE_GUARDIAN_DEATH = registerSounds("entity.eye_guardian.eye_death");
        ENTITY_EYE_MASTER_AMBIENT = registerSounds("entity.eye_master.eye_master_ambient");
        PROJECTILE_EYE_LASER_FIRED = registerSounds("entity.eye_laser.fired");
        PORTAL_POWER_UP = registerSounds("block.portal_power_up");
        PORTAL_AMBIENT = registerSounds("ambient.azerioth_portal");
    }

    private static SoundEvent registerSounds(String name){
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent e = new SoundEvent(location);
        e.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(e);
        return e;
    }
}
