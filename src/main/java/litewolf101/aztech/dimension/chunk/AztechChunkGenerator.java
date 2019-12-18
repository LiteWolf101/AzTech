package litewolf101.aztech.dimension.chunk;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.world.mapgen.MapGenAztechCaves;
import litewolf101.aztech.world.mapgen.MapGenAztechRavine;
import litewolf101.aztech.world.mapgen.MapGenEyeDungeon;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.List;
import java.util.Random;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class AztechChunkGenerator implements IChunkGenerator, IChunkProvider {

	private final World worldObj;
	private final Random rand;

	private final NoiseGeneratorOctaves noiseGen1;
	private final NoiseGeneratorOctaves noiseGen2;
	private final NoiseGeneratorOctaves noiseGen3;
	private final NoiseGeneratorOctaves noiseGen4;
	private final NoiseGeneratorOctaves noiseGen5;
	private final NoiseGeneratorOctaves noiseGen6;
	private final MapGenBase caveGenerator;
	private final MapGenBase ravineGenerator;
	private double[] noiseArray;
	private double[] stoneNoise;
	private double[] noiseData1;
	private double[] noiseData2;
	private double[] noiseData3;
	private double[] noiseData4;
	private double[] noiseData5;
	private NoiseGeneratorPerlin perlinAdditional1;
	private NoiseGeneratorPerlin perlinAdditional2;
	private Biome[] biomesForGeneration;
	private MapGenEyeDungeon eyeDungeon = new MapGenEyeDungeon();

	public AztechChunkGenerator(World world, long seed) {
		worldObj = world;
		rand = new Random(seed + 1);
		world.setSeaLevel(32);

		noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
		noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
		noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
		noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
		perlinAdditional1 = new NoiseGeneratorPerlin(rand, 4);
		perlinAdditional2 = new NoiseGeneratorPerlin(rand, 4);
		stoneNoise = new double[256];

		caveGenerator = new MapGenAztechCaves();
		ravineGenerator = new MapGenAztechRavine();
		eyeDungeon = (MapGenEyeDungeon) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(eyeDungeon, InitMapGenEvent.EventType.CUSTOM);
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();

		biomesForGeneration = worldObj.getBiomeProvider().getBiomes(biomesForGeneration, x * 16, z * 16, 16, 16);
		generateTerrain(x, z, chunkprimer);
		replaceBlocksForBiome(x, z, biomesForGeneration, chunkprimer);

		caveGenerator.generate(worldObj, x, z, chunkprimer);
		ravineGenerator.generate(worldObj, x, z, chunkprimer);
		eyeDungeon.generate(this.worldObj, x, z, chunkprimer);

		Chunk chunk = new Chunk(worldObj, chunkprimer, x, z);
		byte[] biomeArrayReference = chunk.getBiomeArray();

		for(int a = 0; a < biomeArrayReference.length; ++a) {
			biomeArrayReference[a] = (byte)Biome.getIdForBiome(biomesForGeneration[a]);
		}

		//chunk.generateSkylightMap();
		chunk.resetRelightChecks();
		return chunk;
	}

	public void generateTerrain(int x, int z, ChunkPrimer chunkPrimer) {
		int i = 4;
		int k = i + 1;
		int l = 17;
		int i1 = i + 1;
		int j = this.worldObj.getSeaLevel();
		noiseArray = initializeNoiseField(noiseArray, x * i, 0, z * i, k, l, i1);

		for(int j1 = 0; j1 < i; ++j1) {
			for(int k1 = 0; k1 < i; ++k1) {
				for(int l1 = 0; l1 < 16; ++l1) {
					double d0 = 0.125D;
					double d1 = noiseArray[((j1 + 0) * i1 + k1 + 0) * l + l1 + 0];
					double d2 = noiseArray[((j1 + 0) * i1 + k1 + 1) * l + l1 + 0];
					double d3 = noiseArray[((j1 + 1) * i1 + k1 + 0) * l + l1 + 0];
					double d4 = noiseArray[((j1 + 1) * i1 + k1 + 1) * l + l1 + 0];
					double d5 = (noiseArray[((j1 + 0) * i1 + k1 + 0) * l + l1 + 1] - d1) * d0;
					double d6 = (noiseArray[((j1 + 0) * i1 + k1 + 1) * l + l1 + 1] - d2) * d0;
					double d7 = (noiseArray[((j1 + 1) * i1 + k1 + 0) * l + l1 + 1] - d3) * d0;
					double d8 = (noiseArray[((j1 + 1) * i1 + k1 + 1) * l + l1 + 1] - d4) * d0;

					for(int i2 = 0; i2 < 8; ++i2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int j2 = 0; j2 < 4; ++j2) {
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;

							for(int k2 = 0; k2 < 4; ++k2) {
								IBlockState iblockstate = null;
								if(l1 * 8 + i2 < j) {
									iblockstate = Blocks.WATER.getDefaultState();
								}

								if(d15 > 0.0D) {
									iblockstate = BlocksInit.ANCIENT_STONE.getDefaultState();
								}

								int l2 = j2 + j1 * 4;
								int i3 = i2 + l1 * 8;
								int j3 = k2 + k1 * 4;
								chunkPrimer.setBlockState(l2, i3, j3, iblockstate);
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int x, int z, Biome[] biomes, ChunkPrimer primer) {
		ChunkGeneratorEvent.ReplaceBiomeBlocks event = new ChunkGeneratorEvent.ReplaceBiomeBlocks(this, x, z, primer, worldObj);
		MinecraftForge.EVENT_BUS.post(event);
		if(event.getResult() == Event.Result.DENY) {
			return;
		}

		byte var5 = 0;
		stoneNoise = noiseGen4.generateNoiseOctaves(stoneNoise, x * 16, 0, z * 16, 16, 16, 1, 0.0625D, 0.0625D, 0.0625D);
		double d0 = 0.03125D;
		double[] additionalNoise1 = new double[256];
		double[] additionalNoise2 = new double[256];
		additionalNoise1 = perlinAdditional1.getRegion(additionalNoise1, x * 16, z * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
		additionalNoise2 = perlinAdditional2.getRegion(additionalNoise2, x * 16, z * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

		for(int zInChunk = 0; zInChunk < 16; ++zInChunk) {
			for(int xInChunk = 0; xInChunk < 16; ++xInChunk) {
				int horIndex = xInChunk + zInChunk * 16;
				Biome biome = biomes[horIndex]; //change to modded biomes next time
				int var12 = (int)(stoneNoise[xInChunk + zInChunk * 16] / 3D + 3D + rand.nextDouble() * 0.25D);
				int var13 = -1;

				IBlockState topBlock = biome.topBlock;
				IBlockState fillerBlock = biome.fillerBlock;

				int preHeightIndex = (xInChunk * 16 + zInChunk) * 127;

				for(int yInChunk = 127; yInChunk >= 0; --yInChunk) {
					int index = (xInChunk * 16 + zInChunk) * 127 + yInChunk;
					if(yInChunk == 0 || yInChunk == 127) {
						primer.setBlockState(xInChunk, yInChunk, zInChunk, BlocksInit.ANCIENT_BEDROCK.getDefaultState());
					}
					else {
						IBlockState iblockstate2 = primer.getBlockState(xInChunk, yInChunk, zInChunk);
						if(iblockstate2.getMaterial() == Material.AIR) {
							var13 = -1;
						}
						else if(iblockstate2.getBlock().getDefaultState() == BlocksInit.ANCIENT_STONE.getDefaultState()) {
							if(var13 == -1) {
								if(var12 <= 0) {
									topBlock = Blocks.AIR.getDefaultState();
									fillerBlock = BlocksInit.ANCIENT_STONE.getDefaultState();
								}
								else if(yInChunk >= var5 + 4 && yInChunk <= var5 + 120) {
									if(yInChunk == worldObj.getSeaLevel() - 3) {
										topBlock = biome.fillerBlock;
									} else if(yInChunk < worldObj.getSeaLevel() - 3) {
										topBlock = Blocks.GRAVEL.getDefaultState();
									}
									else {
										topBlock = biome.topBlock;
									}
									fillerBlock = biome.fillerBlock;
								}
								if(yInChunk < worldObj.getSeaLevel() && (topBlock == null || topBlock.getMaterial() == Material.AIR || fillerBlock.getMaterial() == Material.AIR)) {
									topBlock = Blocks.WATER.getDefaultState();
								}
								if(yInChunk < var5 && topBlock.getMaterial() == Material.AIR) {
									if(biome.getDefaultTemperature() < 0.15F) {
										topBlock = Blocks.ICE.getDefaultState();
									}
									else {
										topBlock = Blocks.WATER.getDefaultState();
									}
								}
								primer.setBlockState(xInChunk, yInChunk, zInChunk, fillerBlock);
								primer.setBlockState(xInChunk, yInChunk + 1, zInChunk, topBlock);

								var13 = var12;
							}
						}
					}
				}
				primer.setBlockState(xInChunk, 127, zInChunk, BlocksInit.ANCIENT_BEDROCK.getDefaultState());
				primer.setBlockState(xInChunk, 126, zInChunk, BlocksInit.ANCIENT_STONE.getDefaultState());
			}
		}
	}

	private double[] initializeNoiseField(double[] noise, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
		if(noise == null) {
			noise = new double[sizeX * sizeY * sizeZ];
		}

		double d = 684.412D;
		double d1 = 2053.236D;
		noiseData4 = noiseGen5.generateNoiseOctaves(noiseData4, x, y, z, sizeX, 1, sizeZ, 1D, 0D, 1D);
		noiseData5 = noiseGen6.generateNoiseOctaves(noiseData5, x, y, z, sizeX, 1, sizeZ, 100D, 0D, 100D);
		noiseData1 = noiseGen3.generateNoiseOctaves(noiseData1, x, y, z, sizeX, sizeY, sizeZ, d * 0.0125D, d1 / 60D, d * 0.0125D);
		noiseData2 = noiseGen1.generateNoiseOctaves(noiseData2, x, y, z, sizeX, sizeY, sizeZ, d, d1, d);
		noiseData3 = noiseGen2.generateNoiseOctaves(noiseData3, x, y, z, sizeX, sizeY, sizeZ, d, d1, d);
		int index = 0;
		int j = 0;
		double[] ad = new double[sizeY];
		double oneOver512 = 1D / 512D;
		double groundNoiseMp = 1D / 2048D;

		for(int k = 0; k < sizeY; ++k) {
			ad[k] = Math.cos(k * Math.PI * 6D / sizeY) * 2D;
			double d2 = k;

			if(k > sizeY / 2) {
				d2 = sizeY - 1 - k;
			}

			if(d2 < 4D) {
				d2 = 4D - d2;
				ad[k] -= d2 * d2 * d2 * 10D;
			}
		}

		for(int xx = 0; xx < sizeX; ++xx) {
			for(int zz = 0; zz < sizeZ; ++zz) {
				double d3 = (noiseData4[j] + 256D) * oneOver512;

				if(d3 > 1.0D) {
					d3 = 1.0D;
				}

				double d4 = 0.0D;
				double d5 = noiseData5[j] * 0.000125D;

				if(d5 < 0.0D) {
					d5 = -d5;
				}

				d5 = d5 * 3D - 3D;

				if(d5 < 0.0D) {
					d5 /= 2D;

					if(d5 < -1D) {
						d5 = -1D;
					}

					d5 /= 1.4D;
					d5 *= 0.5D;
					d3 = 0.0D;
				}
				else {
					if(d5 > 1.0D) {
						d5 = 1.0D;
					}

					d5 /= 6D;
				}

				d3 += 0.5D;
				d5 = d5 * sizeY * 0.0625D;
				j++;

				for(int yy = 0; yy < sizeY; ++yy) {
					double d6 = 0.0D;
					double d7 = ad[yy];
					double d8 = noiseData2[index] * groundNoiseMp;
					double d9 = noiseData3[index] * groundNoiseMp;
					double d10 = (noiseData1[index] * 0.1D + 1.0D) * 0.5D;

					if(d10 < 0.0D) {
						d6 = d8;
					}
					else if(d10 > 1.0D) {
						d6 = d9;
					}
					else {
						d6 = d8 + (d9 - d8) * d10;
					}

					d6 -= d7;

					if(yy > sizeY - 4) {
						double d11 = (yy - (sizeY - 4)) / 3F;
						d6 = d6 * (1.0D - d11) + -10D * d11;
					}

					if(yy < d4) {
						double d12 = (d4 - yy) * 0.25D;
						if(d12 < 0.0D) {
							d12 = 0.0D;
						}
						if(d12 > 1.0D) {
							d12 = 1.0D;
						}

						d6 = d6 * (1.0D - d12) + -10D * d12;
					}

					noise[index] = d6;
					++index;
				}
			}
		}

		return noise;
	}

	@Override
	public void populate(int x, int z) {

		BlockFalling.fallInstantly = true;
		int i = x * 16;
		int j = z * 16;
		ChunkPos chunkpos = new ChunkPos(x, z);
		BlockPos blockCoord = new BlockPos(i, 0, j);
		Biome biomeBase = this.worldObj.getBiome(blockCoord.add(16, 0, 16));
		BlockPos blockpos = new BlockPos(i, 0, j);
		if(biomeBase instanceof Biome) {//modded biome base
			Biome biome = biomeBase;///modded biome base
			rand.setSeed(worldObj.getSeed());
			rand.setSeed(x * (rand.nextLong() / 2L * 2L + 1L) + z * (rand.nextLong() / 2L * 2L + 1L) ^ worldObj.getSeed());
			biome.decorate(this.worldObj, this.rand, blockCoord);
			eyeDungeon.generateStructure(this.worldObj, this.rand, chunkpos);
		}
		BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return true;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.worldObj.getBiome(pos);
		return biome == null ? null : biome.getSpawnableList(creatureType);
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return "AzTech_EyeDungeon".equals(structureName) && this.eyeDungeon != null ? this.eyeDungeon.isInsideStructure(pos) : false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return "AzTech_EyeDungeon".equals(structureName) && this.eyeDungeon != null ? this.eyeDungeon.getNearestStructurePos(worldIn, position, findUnexplored) : null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		eyeDungeon.generate(this.worldObj, x, z, (ChunkPrimer)null);
	}

	private int getLowestAirBlock(ChunkPrimer primer, int xInChunk, int zInChunk, int preHeightIndex, int minH, int maxH) {
		for(int h = Math.min(minH, maxH); h <= Math.max(minH, maxH); h++) {
			IBlockState iblockstate = primer.getBlockState(xInChunk, h, zInChunk);
			if(primer.getBlockState(xInChunk, h, zInChunk).getMaterial() == Material.AIR) {
				return h;
			}
		}
		return -1;
	}

	@Override
	public Chunk getLoadedChunk(int x, int z) {
		return null;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		return null;
	}

	@Override
	public boolean tick() {
		return false;
	}

	@Override
	public String makeString() {
		return "AzTech";
	}

	@Override
	public boolean isChunkGeneratedAt(int x, int z) {
		return false;
	}

}
