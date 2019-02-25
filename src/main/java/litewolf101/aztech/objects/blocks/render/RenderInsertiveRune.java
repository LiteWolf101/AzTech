package litewolf101.aztech.objects.blocks.render;

import litewolf101.aztech.tileentity.TileEntityInsertiveRune;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import static litewolf101.aztech.objects.blocks.InsertiveRune.FACING;

/**
 * Created by LiteWolf101 on Feb
 * /25/2019
 */
public class RenderInsertiveRune extends TileEntitySpecialRenderer<TileEntityInsertiveRune> {
    private float lbx = 0f;
    private float lby = 0f;
    @Override
    public void render(TileEntityInsertiveRune te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack stack = te.getStackInSlot(0);
        EntityItem entity = new EntityItem(te.getWorld());
        entity.setItem(stack);
        entity.setNoDespawn();
        IBlockState state = te.getWorld().getBlockState(te.getPos());

        if (stack.getItem() != null){
            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            lbx = OpenGlHelper.lastBrightnessX;
            lby = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 220f, 220f);
            GlStateManager.enableRescaleNormal();
            GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
            if (state.getValue(FACING) == EnumFacing.NORTH) {
                GlStateManager.rotate(0, 0, 1, 0);
            }
            if (state.getValue(FACING) == EnumFacing.EAST) {
                GlStateManager.rotate(90, 0, 1, 0);
            }
            if (state.getValue(FACING) == EnumFacing.SOUTH) {
                GlStateManager.rotate(180, 0, 1, 0);
            }
            if (state.getValue(FACING) == EnumFacing.WEST) {
                GlStateManager.rotate(270, 0, 1, 0);
            }
            GlStateManager.scale(0.5,0.5,0.5);
            model = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.FIXED, false);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
            GlStateManager.disableRescaleNormal();
            OpenGlHelper.lastBrightnessX = lbx;
            OpenGlHelper.lastBrightnessY = lby;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY);
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
