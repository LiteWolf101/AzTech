package com.litewolf101.aztech.client.gui.info.pages;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

public class InfoPage extends Screen {
    private String lore;
    private int width;

    public InfoPage(ITextComponent text,  int width, String lore) {
        super(text);
        this.lore = lore;
        this.width = width;
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        String lore1 = this.font.wrapFormattedStringToWidth(this.lore, width);
        this.font.drawString(lore1, p_render_1_, p_render_2_, 0);
    }
}
