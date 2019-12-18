package litewolf101.aztech.objects.blocks.render;

import litewolf101.aztech.tileentity.TEPlantVase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import static litewolf101.aztech.objects.blocks.InsertiveRune.FACING;

public class RenderPlantVaseTop extends TileEntitySpecialRenderer<TEPlantVase> {
    private float lbx = 0f;
    private float lby = 0f;

    @Override
    public void render(TEPlantVase te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack stack = te.getFlowerItemStack();

        EntityItem entity = new EntityItem(te.getWorld());
        entity.setItem(stack);
        entity.setNoDespawn();

        if (stack.getItem() != null) {
            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            lbx = OpenGlHelper.lastBrightnessX;
            lby = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 220f, 220f);
            GlStateManager.enableRescaleNormal();
            GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
            GlStateManager.scale(1, 1, 1);
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
