package com.litewolf101.aztech.client.gui.info;

import com.litewolf101.aztech.client.gui.info.pages.InfoPage;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class AzotomeIcon extends Screen {
    private ITextComponent title;
    private ITextComponent description;
    private ItemStack iconItem;
    private float x, y;
    private AzotomeIcon parent;


    protected AzotomeIcon(ITextComponent title, ITextComponent lockedDesc, ItemStack icon, float x, float y, AzotomeIcon parent) {
        super(title);
        this.title = title;
        this.description = lockedDesc;
        this.iconItem = icon;
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        this.getMinecraft().displayGuiScreen(new InfoPage(title, width, "This is some interesting lore, I might say!"));
    }
}
