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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;

/**
 * Created by LiteWolf101 on Feb /15/2019
 */
public class GUIInsertiveRune extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/gui/insertive_rune.png");
	public static boolean locked;
	public static int sync = 0;
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
		if(locked) {
			this.buttonList.add(new ButtonUnLock(0, 265, 68));
		}
		else if(!locked) {
			this.buttonList.add(new ButtonLock(0, 265, 68));
		}
		System.out.println("GUI State:" + locked);
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		if(mouseX >= buttonList.get(0).x && mouseX <= buttonList.get(0).x + buttonList.get(0).width && mouseY >= buttonList.get(0).y && mouseY <= buttonList.get(0).y + buttonList.get(0).height) {
			drawHoveringText("Lock/Unlock", buttonList.get(0).x, buttonList.get(0).y);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 100, 2, 12434877);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 102, 12434877);

		sync++;

		if(sync % 10 == 0) {
			//PacketHandler.INSTANCE.sendToServer(new PacketSendLockedState(te.getPos(), te.isLocked(), "litewolf101.aztech.utils.gui.GUIInsertiveRune", "locked"));
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		this.mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(playerInventory.player.isCreative()) {
			this.buttonList.clear();
			if(button.id == 0) {
				this.buttonList.add(new ButtonLock(1, 265, 68));
				playerInventory.player.sendMessage(new TextComponentString("Locked"));
				//PacketSendLockedState packet = new PacketSendLockedState(te.getPos(), true);
				//PacketHandler.INSTANCE.sendToServer(packet);
			}
			if(button.id == 1) {
				this.buttonList.add(new ButtonUnLock(0, 265, 68));
				playerInventory.player.sendMessage(new TextComponentString("Unlocked"));
				//PacketSendLockedState packet = new PacketSendLockedState(te.getPos(), false);
				//PacketHandler.INSTANCE.sendToServer(packet);
			}
		}
		else if(!playerInventory.player.isSpectator()) {
			if(button.id == 0 || button.id == 1) {
				playerInventory.player.sendMessage(new TextComponentString(TextFormatting.RED + "Cannot lock or unlock in survival mode"));
			}
		}
	}

}
