package com.litewolf101.aztech.utils;

import com.litewolf101.aztech.client.gui.AzotomeScreen;
import com.litewolf101.aztech.client.gui.buttons.ButtonAzotomeIcon;
import com.litewolf101.aztech.client.gui.info.AzotomeIcon;
import com.litewolf101.aztech.client.gui.info.pages.InfoPage;
import com.litewolf101.aztech.init.ModBlocks;
import com.litewolf101.aztech.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class AzotomeRegistry {
    public static ArrayList<ButtonAzotomeIcon> AZOTOME_ENTRIES = new ArrayList<>();

    public static ButtonAzotomeIcon TEST;
    public static ButtonAzotomeIcon TEST2;

    public static void registerAzotomeEntries() {
        TEST = createAzotomeEntry("Test", "this is a test.", new ItemStack(ModBlocks.ANCIENT_BEDROCK), 30, 60, new TranslationTextComponent("azotome.page.test"), null, AzotomeScreen.CategoryType.BLOCKS);
        TEST2 = createAzotomeEntry("Test2", "This is also a test", new ItemStack(ModItems.BLACK_RUNE), 75, 25, new TranslationTextComponent("azotome.page.test"), TEST, AzotomeScreen.CategoryType.ITEMS);
    }

    public static ButtonAzotomeIcon createAzotomeEntry(String title, String description, ItemStack iconItem, int xPos, int yPos, ITextComponent lore, ButtonAzotomeIcon parent, AzotomeScreen.CategoryType category) {
        ButtonAzotomeIcon theIcon = new ButtonAzotomeIcon(xPos, yPos, 32, 32, title, (button) -> {
            Minecraft.getInstance().displayGuiScreen(new InfoPage(new StringTextComponent(title), lore));
        }, ButtonAzotomeIcon.getScreen(), title, description, parent, category, iconItem);
        AZOTOME_ENTRIES.add(theIcon);
        return theIcon;
    }
}
