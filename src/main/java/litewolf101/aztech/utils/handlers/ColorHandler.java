package litewolf101.aztech.utils.handlers;

import litewolf101.aztech.init.BlocksInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on Feb
 * /06/2019
 */
public class ColorHandler implements IBlockColor {
    public static final IBlockColor COLOR = new ColorHandler();
    public static void registerExtraBlockColors(){
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(COLOR, BlocksInit.ANCIENT_GRASS);
    }
    @SuppressWarnings("deprecation")
    public static void init(){

        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
            if (tintIndex > 15) return 0xFFFFFF;

            if (worldIn == null || pos == null) {
                return ColorizerFoliage.getFoliageColorBasic();
            }

            int red = 0;
            int grn = 0;
            int blu = 0;

            for (int dz = -1; dz <= 1; ++dz) {
                for (int dx = -1; dx <= 1; ++dx) {
                    int i2 = worldIn.getBiome(pos.add(dx, 0, dz)).getFoliageColorAtPos(pos.add(dx, 0, dz));
                    red += (i2 & 16711680) >> 16;
                    grn += (i2 & 65280) >> 8;
                    blu += i2 & 255;
                }
            }

            return (red / 9 & 255) << 16 | (grn / 9 & 255) << 8 | blu / 9 & 255;
        }, BlocksInit.ANCIENT_GRASS);
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler((stack, tintIndex) -> blockColors.colorMultiplier(((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                BlocksInit.ANCIENT_GRASS
        );
    }
    private ColorHandler() {
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }
}
