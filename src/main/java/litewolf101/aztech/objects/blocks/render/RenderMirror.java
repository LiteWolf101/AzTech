package litewolf101.aztech.objects.blocks.render;

import litewolf101.aztech.objects.blocks.model.ModelMirror;
import litewolf101.aztech.tileentity.TETempleMirror;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static litewolf101.aztech.objects.blocks.TempleMirror.AXIS;
import static litewolf101.aztech.objects.blocks.TempleMirror.FLIPPED;
import static litewolf101.aztech.objects.blocks.TempleMirror.INVERT_IO;

/**
 * Created by LiteWolf101 on 11/5/2018.
 */
public class RenderMirror extends TileEntitySpecialRenderer<TETempleMirror>{
    protected final ModelMirror modelMirror = new ModelMirror();
    private ResourceLocation texture = new ResourceLocation("aztech:textures/blocks/temple_mirror_inner.png");
    private ResourceLocation texture2 = new ResourceLocation("aztech:textures/blocks/temple_mirror_inner_2.png");

    @Override
    public void render(TETempleMirror te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        renderReflector(te, x, y, z);
        renderIO(te, x, y, z);
        renderIO2(te, x, y, z);
    }

    public void renderReflector (TETempleMirror te, double x, double y, double z){
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x+0.5f,(float)y+0.5f,(float)z+0.5f);
        GL11.glScalef(0.8f, 0.8f, 0.8f);
        if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.X) {
            GL11.glRotatef(90, 1, 0, 0);
        } else if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.Y) {
            GL11.glRotatef(90, 0, 1, 0);
        } else if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.Z) {
            GL11.glRotatef(90, 0, 0, 1);
        }
        if (te.getWorld().getBlockState(te.getPos()).getValue(FLIPPED) == false) {
            GL11.glRotatef(45, 0, 1, 0);
        } else if (te.getWorld().getBlockState(te.getPos()).getValue(FLIPPED) == true) {
            GL11.glRotatef(-45, 0, 1, 0);
        }
        bindTexture(this.texture);
        GL11.glPushMatrix();
        modelMirror.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public void renderIO (TETempleMirror te, double x, double y, double z) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GL11.glPushMatrix();
        if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.X) {
            if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == false) {
                GL11.glTranslatef((float)x,(float)y+0.5f,(float)z+0.5f);
                GL11.glRotatef(90, 0, 1, 0);
            } else if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == true) {
                GL11.glTranslatef((float)x+0.5f,(float)y+1,(float)z+0.5f);
                GL11.glRotatef(90, 1, 0, 0);
            }
        } else if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.Y) {
            if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == false) {
                GL11.glTranslatef((float)x+0.5f,(float)y+0.5f,(float)z);
                GL11.glRotatef(90, 0, 0, 1);
            } else if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == true) {
                GL11.glTranslatef((float)x,(float)y+0.5f,(float)z+0.5f);
                GL11.glRotatef(90, 0, 1, 0);
            }
        } else if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.Z) {
            if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == false) {
                GL11.glTranslatef((float)x+0.5f,(float)y+0.5f,(float)z);
                GL11.glRotatef(90, 0, 0, 1);
            } else if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == true) {
                GL11.glTranslatef((float)x+0.5f,(float)y,(float)z+0.5f);
                GL11.glRotatef(90, 1, 0, 0);
            }
        }
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        bindTexture(this.texture2);
        GL11.glPushMatrix();
        modelMirror.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public void renderIO2 (TETempleMirror te, double x, double y, double z) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GL11.glPushMatrix();
        if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.X) {
            if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == false) {
                GL11.glTranslatef((float)x+1,(float)y+0.5f,(float)z+0.5f);
                GL11.glRotatef(90, 0, 1, 0);
            } else if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == true) {
                GL11.glTranslatef((float)x+0.5f,(float)y,(float)z+0.5f);
                GL11.glRotatef(90, 1, 0, 0);
            }
        } else if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.Y) {
            if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == false) {
                GL11.glTranslatef((float)x+0.5f,(float)y+0.5f,(float)z+1);
                GL11.glRotatef(90, 0, 0, 1);
            } else if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == true) {
                GL11.glTranslatef((float)x+1,(float)y+0.5f,(float)z+0.5f);
                GL11.glRotatef(90, 0, 1, 0);
            }
        } else if (te.getWorld().getBlockState(te.getPos()).getValue(AXIS) == EnumFacing.Axis.Z) {
            if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == false) {
                GL11.glTranslatef((float)x+0.5f,(float)y+0.5f,(float)z+1);
                GL11.glRotatef(90, 0, 0, 1);
            } else if (te.getWorld().getBlockState(te.getPos()).getValue(INVERT_IO) == true) {
                GL11.glTranslatef((float)x+0.5f,(float)y+1,(float)z+0.5f);
                GL11.glRotatef(90, 1, 0, 0);
            }
        }
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        bindTexture(this.texture2);
        GL11.glPushMatrix();
        modelMirror.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }
}
