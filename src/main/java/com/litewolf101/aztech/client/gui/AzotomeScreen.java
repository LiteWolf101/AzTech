package com.litewolf101.aztech.client.gui;

import com.google.common.collect.Lists;
import com.litewolf101.aztech.client.gui.buttons.ButtonAzotomeIcon;
import com.litewolf101.aztech.client.gui.buttons.ButtonTabAzTechInfo;
import com.litewolf101.aztech.utils.AzotomeRegistry;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AzotomeScreen extends Screen {
    private static final ResourceLocation BG_WINDOW = new ResourceLocation("aztech:textures/gui/azotome_window.png");
    private AzotomeScreen.CategoryType selectedCategory;
    private double scrollX;
    private double scrollY;
    private boolean isScrolling;
    protected final List<Widget> infoButtons = Lists.newArrayList();

    public AzotomeScreen() {
        super(new TranslationTextComponent("Azotome"));
    }

    @Override
    protected void init() {
        System.out.println("INIT");
        int i = (this.width - 256) / 2;
        int j = (this.height - 192) / 2;
        addButton(new ButtonTabAzTechInfo(i - 63, j + 5, 63, 27, "Blocks", button -> {
            syncButtons(CategoryType.BLOCKS);
            AzotomeRegistry.TEST.x = (int)scrollX;
            AzotomeRegistry.TEST.y = (int)scrollY;
            infoButtons.add(AzotomeRegistry.TEST);
            addButton(AzotomeRegistry.TEST);
        }));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 32, 63, 27, "Items", button -> {
            syncButtons(CategoryType.ITEMS);
            infoButtons.add(AzotomeRegistry.TEST2);
            addButton(AzotomeRegistry.TEST2);
        }));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 59, 63, 27, "Entities", button -> {
            syncButtons(CategoryType.ENTITIES);
        }));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 86, 63, 27, "World", button -> {
            syncButtons(CategoryType.WORLD);
        }));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 113, 63, 27, "Machines", button -> {
            syncButtons(CategoryType.MACHINES);
        }));
        addButton(new ButtonTabAzTechInfo(i - 63, j + 140, 63, 27, "Lore", button -> {
            syncButtons(CategoryType.LORE);
        }));
        //this.renderIcons(selectedCategory);

        /*if (this.selectedCategory == CategoryType.BLOCKS) {
            this.infoButtons.add(new ButtonAzotomeIcon(i, j, 32, 32, "test", button -> {
                Minecraft.getInstance().displayGuiScreen(new InfoPage(new StringTextComponent("Test"), new TranslationTextComponent("azotome.page.test")));
            }, this, "Test", "Description"));
        }*/
    }

    @Override
    public void render(int mousex, int mousey, float partialTicks) {
        int i = (this.width - 256) / 2;
        int j = (this.height - 192) / 2;
        this.renderBackground();
        if (selectedCategory != null) {
            renderInternalScreen(selectedCategory, i, j, 200, 200, partialTicks);
        }
        super.render(mousex, mousey, partialTicks);
        this.renderWindow(i, j);
        //this.renderToolTips(mousex, mousey, i, j);
    }

    public void renderWindow(int winx, int winz) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        RenderHelper.disableStandardItemLighting();
        Minecraft.getInstance().getTextureManager().bindTexture(BG_WINDOW);
        this.blit(winx, winz, 0, 0, 256, 192);
        this.font.drawString(I18n.format("Azotome"), (float) (winx + 78), (float) (winz - 9), 15914104);
        this.font.drawString(I18n.format("aztech.azotome.bookmarks"), (float) (winx + 194), (float) (winz - 9), 15914104);
    }

    private void renderInternalScreen(CategoryType type, int x, int y, int width, int height, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(x + 5, y + 5, -400F);
        GlStateManager.enableDepthTest();
        if (type == CategoryType.BLOCKS) {
            this.drawContents(x, y, type, partialTicks, new ResourceLocation("aztech:textures/gui/azotome_blocks.png"));
        } else if (type == CategoryType.ITEMS) {
            this.drawContents(x, y, type, partialTicks, new ResourceLocation("aztech:textures/gui/azotome_items.png"));
        } else if (type == CategoryType.ENTITIES) {
            this.drawContents(x, y, type, partialTicks, new ResourceLocation("aztech:textures/gui/azotome_entities.png"));
        } else if (type == CategoryType.WORLD) {
            this.drawContents(x, y, type, partialTicks, new ResourceLocation("aztech:textures/gui/azotome_world.png"));
        } else if (type == CategoryType.MACHINES) {
            this.drawContents(x, y, type, partialTicks, new ResourceLocation("aztech:textures/gui/azotome_machines.png"));
        } else if (type == CategoryType.LORE) {
            this.drawContents(x, y, type, partialTicks, new ResourceLocation("aztech:textures/gui/azotome_lore.png"));
        }
        GlStateManager.popMatrix();
        GlStateManager.depthFunc(515);
        GlStateManager.disableDepthTest();
    }

    public void drawContents(int x, int y, CategoryType type, float partialTicks, ResourceLocation location) {

        GlStateManager.depthFunc(518);
        fill(0, 0, 184, 184, -16777216);
        GlStateManager.depthFunc(515);
        Minecraft.getInstance().getTextureManager().bindTexture(location);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int i = MathHelper.floor(this.scrollX);
        int j = MathHelper.floor(this.scrollY);
        int k = i % 16;
        int l = j % 16;

        for(int i1 = -1; i1 <= 12; ++i1) {
            for(int j1 = -1; j1 <= 12; ++j1) {
                blit(k + 16 * i1, l + 16 * j1, 0.0F, 0.0F, 16, 16, 16, 16);
            }
        }

        for (int ib = 0; ib < this.infoButtons.size(); ++ib) {
            renderTooltip("§b" + ((ButtonAzotomeIcon)this.infoButtons.get(ib)).getTitle(), ((ButtonAzotomeIcon)this.infoButtons.get(ib)).x, ((ButtonAzotomeIcon)this.infoButtons.get(ib)).y);
            renderTooltip(((ButtonAzotomeIcon)this.infoButtons.get(ib)).getDesc(), x, y + 16);
            this.itemRenderer.renderItemAndEffectIntoGUI((null), ((ButtonAzotomeIcon)this.infoButtons.get(ib)).getIconStack(), x, y);
        }


        /*this.root.drawConnectivity(i, j, true);
        this.root.drawConnectivity(i, j, false);
        this.root.draw(i, j);*/
    }

    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }

    public boolean mouseDragged(double mousex, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        boolean flag = mousex > ((this.width - 256) / 2) && mousex < ((this.width - 256) / 2) + 190 && p_mouseDragged_3_ > ((this.height - 192) / 2) && p_mouseDragged_3_ < ((this.height - 192) / 2) + 190;
        if (p_mouseDragged_5_ != 0 ) {
            this.isScrolling = false;
            return false;
        } else {
            if (!this.isScrolling) {
                this.isScrolling = true;
            } else if (this.selectedCategory != null && flag) {
                this.func_195626_a(p_mouseDragged_6_, p_mouseDragged_8_);
            }

            return true;
        }
    }

    public void func_195626_a(double p_195626_1_, double p_195626_3_) {
        this.scrollX = MathHelper.clamp(this.scrollX + p_195626_1_, -1000, 0.0D);
        this.scrollY = MathHelper.clamp(this.scrollY + p_195626_3_, -1000, 0.0D);
    }

    private void renderToolTips(int p_191937_1_, int p_191937_2_, int p_191937_3_, int p_191937_4_) {
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    //debug
    public String whatCatAmILookingAt(CategoryType type) {
        return type.name;
    }

    private void renderIcons(CategoryType type) {
    }

    public void syncButtons(CategoryType type) {
        this.selectedCategory = type;
        for (int i = 0; i < this.infoButtons.size(); ++i) {
            this.buttons.remove(this.infoButtons.get(i));
            this.children.remove(this.infoButtons.get(i));
        }
        this.infoButtons.clear();
        this.scrollX = 0;
        this.scrollY = 0;
    }

    public enum CategoryType {
        BLOCKS("blocks"),
        ITEMS("items"),
        ENTITIES("entities"),
        WORLD("world"),
        MACHINES("machines"),
        LORE("lore");

        String name;

        CategoryType(String name) {
            this.name = name;
        }

        String getName(CategoryType type) {
            return type.name;
        }
    }
}
