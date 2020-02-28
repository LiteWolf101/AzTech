package com.litewolf101.aztech.client.gui.buttons;

import com.litewolf101.aztech.client.gui.AzotomeScreen;
import com.litewolf101.aztech.client.gui.info.pages.InfoPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ButtonAzotomeIcon extends Button {
    private static final ResourceLocation ICON = new ResourceLocation("aztech:textures/gui/azotome_icon.png");
    private static Screen screen;
    private String title;
    private String desc;
    private ButtonAzotomeIcon parent;
    private AzotomeScreen.CategoryType type;
    private ItemStack iconStack;

    public ButtonAzotomeIcon(int xPos, int yPos, int width, int height, String name, IPressable pressable, Screen screen, String title, String desc, ButtonAzotomeIcon parent, AzotomeScreen.CategoryType category, ItemStack iconItem) {
        super(xPos, yPos, width, height, name, pressable);
        ButtonAzotomeIcon.screen = screen;
        this.title = title;
        this.desc = desc;
        this.parent = parent;
        this.type = category;
        this.iconStack = iconItem;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(ICON);
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int k = this.getYImage(this.isHovered);
            this.blit(this.x, this.y, 0, (k - 1) * 32, 32, 32);
            this.renderBg(mc, mouseX, mouseY);
            //this.renderToolTip(mouseX, mouseY);
        }
    }

    @Override
    public void onPress() {
        super.onPress();
        Minecraft.getInstance().displayGuiScreen(new InfoPage(new StringTextComponent("Test"), new TranslationTextComponent("azotome.page.test")));
    }

    public static Screen getScreen() {
        return ButtonAzotomeIcon.screen;
    }

    public ButtonAzotomeIcon getParent() {
        if (this.parent != null) {
            if (this.parent.getCategory() != this.getCategory()) {
                throw new IllegalArgumentException("Cannot parent icons between categories! {" + this.title + ":" + this.getCategory() + "->" + this.parent.title + ":" + this.parent.getCategory() + "}");
            }
        }
        return this.parent;
    }

    public AzotomeScreen.CategoryType getCategory() {
        return this.type;
    }

    public ItemStack getIconStack() {
        return this.iconStack;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
