package litewolf101.aztech.utils;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.config.AzTechConfig;
import litewolf101.aztech.dimension.AztechDimension;
import litewolf101.aztech.dimension.chunk.AztechChunkGenerator;
import litewolf101.aztech.world.biome.AztechBiomeProvider;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class AztechWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return AztechDimension.aztech;
    }

    @Nullable
    @Override
    public String getSaveFolder() {
        return "AzTech";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new AztechChunkGenerator(world, world.getSeed());
    }

    @Override
    protected void init() {
        this.biomeProvider = new AztechBiomeProvider(world);
        this.nether = false;
        this.hasSkyLight = false;

    }

    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0.30000000298023224D, 0.2000000329447746D, 0.029999999329447746D);
    }
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
        }
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }

    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.5F;
    }
    @Nullable
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }

    public boolean canRespawnHere()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return AzTechConfig.toogle_dimension_fog;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }
}
