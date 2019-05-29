package litewolf101.aztech.objects.potions;

import litewolf101.aztech.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class CustomPotion extends Potion {
    public CustomPotion(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName("effect." + name);
        setIconIndex(iconIndexX, iconIndexY);
        setRegistryName(new ResourceLocation(Reference.MODID + ":" + name));
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/potion_effects.png"));
        return true;
    }
}
