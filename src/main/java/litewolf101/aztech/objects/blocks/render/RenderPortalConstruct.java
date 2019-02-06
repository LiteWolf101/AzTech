package litewolf101.aztech.objects.blocks.render;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.blocks.model.ModelPortalConstruct;
import litewolf101.aztech.objects.blocks.model.ModelRuneCore;
import litewolf101.aztech.tileentity.masterPortalConstruct;
import litewolf101.aztech.utils.handlers.EnumPortalPart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

import static litewolf101.aztech.objects.blocks.PortalMultiblock.PART;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class RenderPortalConstruct extends TileEntitySpecialRenderer<masterPortalConstruct> {
    protected final ModelPortalConstruct modelPortalConstruct= new ModelPortalConstruct();
    protected final ModelRuneCore modelRuneCore= new ModelRuneCore();
    private ResourceLocation textureConstruct = new ResourceLocation("aztech:textures/blocks/portal_construct_full.png");
    private ResourceLocation textureCore = new ResourceLocation("aztech:textures/blocks/white_rune_block.png");

    @Override
    public void render(masterPortalConstruct te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if(te.getWorld().getBlockState(te.getPos()) != BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BOTTOM)){
            return;
        }
        long angle = (System.currentTimeMillis() / 20) % 360;
        long size = ((System.currentTimeMillis() / 3)% 300);

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.5, (float) y, (float) z + 0.5);
        bindTexture(this.textureConstruct);
        GlStateManager.scale(1f, 1f, 1f);
        GlStateManager.pushMatrix();
        modelPortalConstruct.renderModel(0.0625f);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.15, (float) y + 3.2, (float) z + 0.15);
        bindTexture(this.textureCore);
        GlStateManager.scale(0.7f, 0.7f, 0.7f);
        GlStateManager.rotate(angle, 1f, 1f, 1f);
        GlStateManager.pushMatrix();
        modelRuneCore.renderModel(0.0625f);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.15 - (((float) size/1000)/2), (float) y + 3.2 - (((float) size/1000)/2), (float) z + 0.15 - (((float) size/1000)/2));
        bindTexture(this.textureCore);
        GlStateManager.scale(0.7f + ((float) size/1000), 0.7f + ((float) size/1000), 0.7f + ((float) size/1000));
        GlStateManager.rotate(angle, 1f, 1f, 1f);
        GlStateManager.enableBlend();
        GlStateManager.color(1f, 1f, 1f, 1f - (((float) size/200)));
        GlStateManager.pushMatrix();
        modelRuneCore.renderModel(0.0625f);
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}
