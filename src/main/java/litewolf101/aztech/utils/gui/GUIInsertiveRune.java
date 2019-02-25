package litewolf101.aztech.utils.gui;

import litewolf101.aztech.tileentity.TileEntityInsertiveRune;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.utils.container.ContainerInsertiveRune;
import litewolf101.aztech.utils.gui.button.ButtonLock;
import litewolf101.aztech.utils.gui.button.ButtonUnLock;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Created by LiteWolf101 on Feb
 * /15/2019
 */
public class GUIInsertiveRune extends GuiContainer{
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/gui/insertive_rune.png");
    private final InventoryPlayer playerInventory;
    private final TileEntityInsertiveRune te;

    public GUIInsertiveRune(InventoryPlayer playerInventory, TileEntityInsertiveRune chestInventory, EntityPlayer player) {
        super(new ContainerInsertiveRune(playerInventory, chestInventory, player));
        this.playerInventory = playerInventory;
        this.te = chestInventory;

        this.ySize = 168;
        this.xSize = 176;
    }

    @Override
    public void initGui() {
        this.buttonList.add(new ButtonUnLock(0, 265, 68));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 100, 2, 12434877);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 102, 12434877);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1,1,1,1);
        this.mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        this.buttonList.clear();
        if(button.id == 0){
            this.buttonList.add(new ButtonLock(1, 265, 68));
        }
        if(button.id == 1){
            this.buttonList.add(new ButtonUnLock(0, 265, 68));
        }
    }
}
