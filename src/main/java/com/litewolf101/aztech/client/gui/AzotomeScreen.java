package com.litewolf101.aztech.client.gui;

import com.litewolf101.aztech.client.gui.buttons.ButtonTabAzTechInfo;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class AzotomeScreen extends Screen {
    private static final ResourceLocation BG_WINDOW = new ResourceLocation("aztech:textures/gui/azotome_window.png");
    //private final Map<AzotomeIcon, AzTechInfoTabGui> tabs = Maps.newLinkedHashMap();
    //private AzTechInfoTabGui selectedTab;
    //private boolean isScrolling;

    public AzotomeScreen() {
        super(new TranslationTextComponent("Azotome"));
    }

    @Override
    protected void init() {
        //this.tabs.clear();
        //this.selectedTab = null;
        int i = (this.width - 256) / 2;
        int j = (this.height - 192) / 2;
        addButton(new ButtonTabAzTechInfo(i - 63, j + 5, 63, 27, "Blocks", button -> {}));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 32, 63, 27, "Items", button -> {}));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 59, 63, 27, "Entities", button -> {}));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 86, 63, 27, "World", button -> {}));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 113, 63, 27, "Machines", button -> {}));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 140, 63, 27, "Lore", button -> {}));
        super.init();
    }

    @Override
    public void render(int mousex, int mousey, float partialTicks) {
        int i = (this.width - 256) / 2;
        int j = (this.height - 192) / 2;
        this.renderBackground();
        this.renderWindow(i, j);
        //this.renderInside(mousex, mousey, i, j);
        //this.renderToolTips(mousex, mousey, i, j);
        super.render(mousex, mousey, partialTicks);
    }

    public void renderWindow(int winx, int winz) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        RenderHelper.disableStandardItemLighting();
        this.minecraft.getTextureManager().bindTexture(BG_WINDOW);
        this.blit(winx, winz, 0, 0, 256, 192);
        this.font.drawString(I18n.format("Azotome"), (float)(winx + 78), (float)(winz - 9), 15914104);
        this.font.drawString(I18n.format("aztech.azotome.bookmarks"), (float)(winx + 194), (float)(winz - 9), 15914104);
    }

    private void renderInside(int p_191936_1_, int p_191936_2_, int p_191936_3_, int p_191936_4_) {
    }

    private void renderToolTips(int p_191937_1_, int p_191937_2_, int p_191937_3_, int p_191937_4_) {
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
