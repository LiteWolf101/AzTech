package litewolf101.aztech.objects.blocks.render;

import litewolf101.aztech.init.BlocksInit;
import static litewolf101.aztech.objects.blocks.GeoluminescentObelisk.HALF;
import litewolf101.aztech.objects.blocks.model.BaseFlatTile;
import litewolf101.aztech.objects.blocks.model.ModelGeoObelisk;
import litewolf101.aztech.tileentity.TEGeoObelisk;
import litewolf101.aztech.utils.handlers.EnumHalf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class RenderTileGeoObelisk extends TileEntitySpecialRenderer<TEGeoObelisk> {

	protected final ModelGeoObelisk modelGeoObelisk = new ModelGeoObelisk();
	protected final BaseFlatTile baseFlatTile = new BaseFlatTile();
	private ResourceLocation textureOb = new ResourceLocation("aztech:textures/blocks/te_geo_obelisk.png");
	private ResourceLocation textureFlat = new ResourceLocation("aztech:textures/blocks/flat_temple_tile.png");

	@Override
	public void render(TEGeoObelisk te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(te.getWorld().getBlockState(te.getPos()) == BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP)) {
			return;
		}
		long height = (System.currentTimeMillis() / 50) % 210;

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.translate((float)x + 0.3, (float)y + 0.3 + (0.15f * MathHelper.sin(0.3f - height * 0.03f)), (float)z + 0.3);
		bindTexture(this.textureOb);
		//GlStateManager.translate(-1, -1, -1);
		GlStateManager.scale(0.8f, 0.8f, 0.8f);
		GlStateManager.pushMatrix();

		modelGeoObelisk.renderModel(0.0625f);
		GlStateManager.popMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(1, 1, 1);
		bindTexture(textureFlat);
		GlStateManager.pushMatrix();
		baseFlatTile.renderModel(0.0625f);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

}
