package com.litewolf101.aztech.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.gui.ScrollPanel;
import net.minecraftforge.common.ForgeHooks;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockGuiModList extends Screen {
    private MockGuiModList.InfoPanel modInfo;

    public MockGuiModList() {
        super(new TranslationTextComponent("fml.menu.mods.title"));
    }

    class InfoPanel extends ScrollPanel {
        private Minecraft client;
        private List<ITextComponent> lines = Collections.emptyList();
        private final int barWidth = 6;
        private final int barLeft;

        InfoPanel(Minecraft mcIn, int widthIn, int heightIn, int topIn) {
            super(mcIn, widthIn, heightIn, topIn, 92);
            this.client = mcIn;
            this.barLeft = this.left + this.width - barWidth;
        }

        void setInfo(List<String> lines) {
            this.lines = resizeContent(lines);
        }

        private List<ITextComponent> resizeContent(List<String> lines) {
            List<ITextComponent> ret = new ArrayList<ITextComponent>();
            for (String line : lines) {
                if (line == null) {
                    ret.add(null);
                    continue;
                }

                ITextComponent chat = ForgeHooks.newChatWithLinks(line, false);
                int maxTextLength = this.width - 12;
                if (maxTextLength >= 0) {
                    ret.addAll(RenderComponentsUtil.splitText(chat, maxTextLength, MockGuiModList.this.font, false, true));
                }
            }
            return ret;
        }

        @Override
        public int getContentHeight() {
            int height = 50;
            height += (lines.size() * font.FONT_HEIGHT);
            if (height < this.bottom - this.top - 8)
                height = this.bottom - this.top - 8;
            return height;
        }

        @Override
        protected int getScrollAmount() {
            return font.FONT_HEIGHT * 3;
        }

        @Override
        public void render(int mouseX, int mouseY, float partialTicks) {

            Tessellator tess = Tessellator.getInstance();
            BufferBuilder worldr = tess.getBuffer();

            double scale = client.mainWindow.getGuiScaleFactor();
            GL11.glEnable(GL11.GL_SCISSOR_TEST);
            GL11.glScissor((int) (left * scale), (int) (client.mainWindow.getFramebufferHeight() - (bottom * scale)), (int) (width * scale), (int) (height * scale));

            if (this.client.world != null) {
                this.drawGradientRect(this.left, this.top, this.right, this.bottom, 0, 0);
            }

            int baseY = this.top + border - (int) this.scrollDistance;
            this.drawPanel(right, baseY, tess, mouseX, mouseY);

            GlStateManager.disableDepthTest();

            int extraHeight = (this.getContentHeight() + border) - height;
            if (extraHeight > 0) {
                int barHeight = getBarHeight();

                int barTop = (int) this.scrollDistance * (height - barHeight) / extraHeight + this.top;
                if (barTop < this.top) {
                    barTop = this.top;
                }

                GlStateManager.disableTexture();
                worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldr.pos(barLeft, this.bottom, 0.0D).tex(0.0D, 1.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                worldr.pos(barLeft + barWidth, this.bottom, 0.0D).tex(1.0D, 1.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                worldr.pos(barLeft + barWidth, this.top, 0.0D).tex(1.0D, 0.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                worldr.pos(barLeft, this.top, 0.0D).tex(0.0D, 0.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                tess.draw();
                worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldr.pos(barLeft, barTop + barHeight, 0.0D).tex(0.0D, 1.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                worldr.pos(barLeft + barWidth, barTop + barHeight, 0.0D).tex(1.0D, 1.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                worldr.pos(barLeft + barWidth, barTop, 0.0D).tex(1.0D, 0.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                worldr.pos(barLeft, barTop, 0.0D).tex(0.0D, 0.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                tess.draw();
                worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldr.pos(barLeft, barTop + barHeight - 1, 0.0D).tex(0.0D, 1.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
                worldr.pos(barLeft + barWidth - 1, barTop + barHeight - 1, 0.0D).tex(1.0D, 1.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
                worldr.pos(barLeft + barWidth - 1, barTop, 0.0D).tex(1.0D, 0.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
                worldr.pos(barLeft, barTop, 0.0D).tex(0.0D, 0.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
                tess.draw();
            }

            GlStateManager.enableTexture();
            GlStateManager.shadeModel(GL11.GL_FLAT);
            GlStateManager.enableAlphaTest();
            GlStateManager.disableBlend();
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
        }

        private int getBarHeight() {
            int barHeight = (height * height) / this.getContentHeight();

            if (barHeight < 32) barHeight = 32;

            if (barHeight > height - border * 2)
                barHeight = height - border * 2;

            return barHeight;
        }

        @Override
        protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {

            for (ITextComponent line : this.lines) {
                if (line != null) {
                    GlStateManager.enableBlend();
                    MockGuiModList.this.font.drawString(line.getFormattedText(), left + 4, relativeY, 0);
                    GlStateManager.disableAlphaTest();
                    GlStateManager.disableBlend();
                }
                relativeY += font.FONT_HEIGHT;
            }
        }

        private ITextComponent findTextLine(final int mouseX, final int mouseY) {
            double offset = (mouseY - top) + border + scrollDistance + 1;
            if (offset <= 0)
                return null;

            int lineIdx = (int) (offset / font.FONT_HEIGHT);
            if (lineIdx >= lines.size() || lineIdx < 1)
                return null;

            ITextComponent line = lines.get(lineIdx - 1);
            if (line != null) {
                int k = left + border;
                for (ITextComponent part : line) {
                    if (!(part instanceof StringTextComponent))
                        continue;
                    k += MockGuiModList.this.font.getStringWidth(((StringTextComponent) part).getText());
                    if (k >= mouseX) {
                        return part;
                    }
                }
            }
            return null;
        }

        @Override
        public boolean mouseClicked(final double mouseX, final double mouseY, final int button) {
            final ITextComponent component = findTextLine((int) mouseX, (int) mouseY);
            if (component != null) {
                MockGuiModList.this.handleComponentClicked(component);
                return true;
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void init() {
        this.modInfo = new MockGuiModList.InfoPanel(this.minecraft, 180, this.height - 60, 31);
        modInfo.setInfo(this.font.listFormattedStringToWidth(new TranslationTextComponent("azotome.page.test").getFormattedText(), 175));
        children.add(modInfo);

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        int i = (this.width - 256) / 2;
        int j = (this.height - 192) / 2;
        this.renderWindow(i, j);
        this.modInfo.render(mouseX, mouseY, partialTicks);
        super.render(mouseX, mouseY, partialTicks);
    }

    private static final ResourceLocation BG_TEXTURE = new ResourceLocation("aztech:textures/gui/azotome_window_filled.png");


    public void renderWindow(int winx, int winz) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        RenderHelper.disableStandardItemLighting();
        Minecraft.getInstance().getTextureManager().bindTexture(BG_TEXTURE);
        this.blit(winx, winz, 0, 0, 256, 192);
        this.font.drawString(I18n.format("Azotome"), (float) (winx + 78), (float) (winz - 9), 15914104);
        this.font.drawString(I18n.format("aztech.azotome.bookmarks"), (float) (winx + 194), (float) (winz - 9), 15914104);
    }
}
