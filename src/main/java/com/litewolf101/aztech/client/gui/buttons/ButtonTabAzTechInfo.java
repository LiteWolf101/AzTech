package com.litewolf101.aztech.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class ButtonTabAzTechInfo extends Button {
    private static final ResourceLocation TABS = new ResourceLocation("aztech:textures/gui/azotome_tabs.png");

    public ButtonTabAzTechInfo(int xPos, int yPos, int width, int height, String title, IPressable pressable) {
        super(xPos, yPos, width, height, title, pressable);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(TABS);
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int k = this.getYImage(this.isHovered);
            this.blit(this.x, this.y, 0, k * 27, 63, 27);
            this.renderBg(mc, mouseX, mouseY);
            int color = 0;

            if (packedFGColor != 0) {
                color = packedFGColor;
            } else if (!this.active) {
                color = 10526880;
            }

            String buttonText = this.getMessage();
            int strWidth = mc.fontRenderer.getStringWidth(buttonText);
            int ellipsisWidth = mc.fontRenderer.getStringWidth("...");

            if (strWidth > width - 6 && strWidth > ellipsisWidth)
                buttonText = mc.fontRenderer.trimStringToWidth(buttonText, width - 6 - ellipsisWidth).trim() + "...";

            this.drawCenteredString(mc.fontRenderer, buttonText, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
        }

    }

    public void drawCenteredString(FontRenderer renderer, String string, int xPos, int zPos, int color) {
        renderer.drawString(string, (float) (xPos - renderer.getStringWidth(string) / 2), (float) zPos, color);
    }
}
