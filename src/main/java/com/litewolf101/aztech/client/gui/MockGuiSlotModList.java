package com.litewolf101.aztech.client.gui;

public class MockGuiSlotModList /*extends ExtendedList<MockGuiSlotModList.ModEntry>*/ {
    /*private static String stripControlCodes(String value) { return net.minecraft.util.StringUtils.stripControlCodes(value); }
    private static final ResourceLocation VERSION_CHECK_ICONS = new ResourceLocation(ForgeVersion.MOD_ID, "textures/gui/version_check_icons.png");
    private final int listWidth;

    private MockGuiModList parent;

    public MockGuiSlotModList(MockGuiModList parent, int listWidth)
    {
        super(parent.getMinecraftInstance(), listWidth, parent.height, 32, parent.height - 91 + 4, parent.getFontRenderer().FONT_HEIGHT * 2 + 8);
        this.parent = parent;
        this.listWidth = listWidth;
        this.refreshList();
    }

    @Override
    protected int getScrollbarPosition()
    {
        return this.listWidth;
    }

    @Override
    public int getRowWidth()
    {
        return this.listWidth;
    }

    void refreshList() {
        this.clearEntries();
        parent.buildModList(this::addEntry, mod->new MockGuiSlotModList.ModEntry(mod, this.parent));
    }

    @Override
    protected void renderBackground()
    {
        this.parent.renderBackground();
    }

    class ModEntry extends ExtendedList.AbstractListEntry<MockGuiSlotModList.ModEntry> {
        private final ModInfo modInfo;
        private final MockGuiModList parent;

        ModEntry(ModInfo info, MockGuiModList parent) {
            this.modInfo = info;
            this.parent = parent;
        }

        @Override
        public void render(int entryIdx, int top, int left, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTicks)
        {
            String name = stripControlCodes(modInfo.getDisplayName());
            String version = stripControlCodes(MavenVersionStringHelper.artifactVersionToString(modInfo.getVersion()));
            VersionChecker.CheckResult vercheck = VersionChecker.getResult(modInfo);
            FontRenderer font = this.parent.getFontRenderer();
            font.drawString(font.trimStringToWidth(name, listWidth),left + 3, top + 2, 0xFFFFFF);
            font.drawString(font.trimStringToWidth(version, listWidth), left + 3 , top + 2 + font.FONT_HEIGHT, 0xCCCCCC);
            if (vercheck.status.shouldDraw())
            {

                Minecraft.getInstance().getTextureManager().bindTexture(VERSION_CHECK_ICONS);
                GlStateManager.color4f(1, 1, 1, 1);
                GlStateManager.pushMatrix();
                AbstractGui.blit(getRight() - (height / 2 + 4), MockGuiSlotModList.this.getTop() + (height / 2 - 4), vercheck.status.getSheetOffset() * 8, (vercheck.status.isAnimated() && ((System.currentTimeMillis() / 800 & 1)) == 1) ? 8 : 0, 8, 8, 64, 16);
                GlStateManager.popMatrix();
            }
        }

        @Override
        public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_)
        {
            parent.setSelected(this);
            MockGuiSlotModList.this.setSelected(this);
            return false;
        }

        public ModInfo getInfo()
        {
            return modInfo;
        }
    }*/
}
