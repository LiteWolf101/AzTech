package litewolf101.aztech.utils;

import litewolf101.aztech.config.AzTechConfig;
import litewolf101.aztech.dimension.AztechDimension;
import litewolf101.aztech.dimension.chunk.AztechChunkGenerator;
import litewolf101.aztech.world.biome.AztechBiomeProvider;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class AztechWorldProvider extends WorldProvider {

	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for(int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float)i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
		}
	}

	@Override
	protected void init() {
		this.biomeProvider = new AztechBiomeProvider(world);
		this.nether = false;
		this.hasSkyLight = false;

	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new AztechChunkGenerator(world, world.getSeed());
	}

	public boolean canCoordinateBeSpawn(int x, int z) {
		return false;
	}

	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.5F;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Nullable
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return new Vec3d(0.30000000298023224D, 0.2000000329447746D, 0.029999999329447746D);
	}

	public boolean canRespawnHere() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return false;
	}

	@Override
	public int getAverageGroundLevel() {
		return world.getSeaLevel();
	}

	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z) {
		return AzTechConfig.toogle_dimension_fog;
	}

	@Nullable
	@Override
	public String getSaveFolder() {
		return "AzTech";
	}

	@Override
	public int getHeight() {
		return 128;
	}

	@Override
	public int getActualHeight() {
		return 128;
	}

	@Override
	public double getHorizon() {
		return world.getSeaLevel();
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;
	}

	@Override
	public DimensionType getDimensionType() {
		return AztechDimension.aztech;
	}

}
