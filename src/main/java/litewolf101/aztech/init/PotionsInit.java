package litewolf101.aztech.init;

import litewolf101.aztech.objects.potions.CustomPotion;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionsInit {
    //potions
    public static final Potion STATIC = new CustomPotion("static", true, 14930175, 0, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), -200D, 1);

    //potion types
    public static final PotionType STATIC_I = new PotionType("static", new PotionEffect[] {new PotionEffect(STATIC, 50)}).setRegistryName("static");

    public static void registerPotions(){
        registerPotion(STATIC_I, STATIC);
    }

    private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }

    private static void registerPotion(PotionType defaultPotion, Potion effect){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
    }
}
