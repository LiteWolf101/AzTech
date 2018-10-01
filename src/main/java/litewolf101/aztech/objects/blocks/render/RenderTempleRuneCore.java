package litewolf101.aztech.objects.blocks.render;

import litewolf101.aztech.objects.blocks.TempleRuneBlock;
import litewolf101.aztech.objects.blocks.model.*;
import litewolf101.aztech.tileentity.TETempleRuneBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by LiteWolf101 on 9/29/2018.
 */
public class RenderTempleRuneCore extends TileEntitySpecialRenderer<TETempleRuneBlock> {
    private final ResourceLocation texture_red = new ResourceLocation("aztech:textures/blocks/red_rune_block.png");
    private final ResourceLocation texture_yellow = new ResourceLocation("aztech:textures/blocks/yellow_rune_block.png");
    private final ResourceLocation texture_green = new ResourceLocation("aztech:textures/blocks/green_rune_block.png");
    private final ResourceLocation texture_blue = new ResourceLocation("aztech:textures/blocks/blue_rune_block.png");
    private final ResourceLocation texture_white = new ResourceLocation("aztech:textures/blocks/white_rune_block.png");
    private final ResourceLocation texture_black = new ResourceLocation("aztech:textures/blocks/black_rune_block.png");

    private final ModelRuneCore runeCore = new ModelRuneCore();
    @Override
    public void render(TETempleRuneBlock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        long angle = (System.currentTimeMillis() / 50) % 360;
        IBlockState state = te.getWorld().getBlockState(te.getPos());

        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)x+0.25f,(float)y+0.25f,(float)z+0.25f);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glRotatef(angle, 1F, 1F, 1F);
        switch (state.getValue(TempleRuneBlock.RUNE_COLOR)){
            case YELLOW:
                this.bindTexture(texture_yellow);
                break;
            case GREEN:
                this.bindTexture(texture_green);
                break;
            case BLUE:
                this.bindTexture(texture_blue);
                break;
            case WHITE:
                this.bindTexture(texture_white);
                break;
            case BLACK:
                this.bindTexture(texture_black);
                break;
            default:
                this.bindTexture(texture_red);
        }
        GL11.glPushMatrix();
        runeCore.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
