package litewolf101.aztech.world.biome;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.mobs.MobAncientSquid;
import litewolf101.aztech.utils.handlers.EnumAzTechPlantType;
import litewolf101.aztech.world.worldgen.AztechBiomeDecor;
import litewolf101.aztech.world.worldgen.feature.*;
import litewolf101.aztech.world.worldgen.trees.WorldGenAztechOakTree;
import litewolf101.aztech.world.worldgen.trees.WorldGenSmolTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenWaterlily;

import java.util.Random;

/**
 * Created by LiteWolf101 on 10/24/2018.
 */
public class BiomeMurkySwamp extends Biome {

	private static BiomeProperties properties = new Biome.BiomeProperties("Murky Swamp");

	public BiomeMurkySwamp() {
		super(properties);
		this.topBlock = BlocksInit.ANCIENT_GRASS.getDefaultState();
		this.fillerBlock = BlocksInit.ANCIENT_DIRT.getDefaultState();
		properties.setTemperature(1.4F);
		properties.setHeightVariation(0.2F);
		properties.setBaseHeight(0f);
		properties.setRainDisabled();

		getAztechBiomeDecor().setAztechOakTreesPerChunk(4);
		getAztechBiomeDecor().setSmolTreesPerChunk(3);

		spawnableMonsterList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 7, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 7, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 7, 1, 1));

		spawnableCreatureList.clear();
		spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 3, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 5, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 6, 2, 2));

		spawnableWaterCreatureList.clear();
		spawnableWaterCreatureList.add(new SpawnListEntry(MobAncientSquid.class, 10, 2, 5));

		spawnableCaveCreatureList.clear();
	}

	protected AztechBiomeDecor getAztechBiomeDecor() {
		return (AztechBiomeDecor)this.decorator;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new AztechBiomeDecor();
	}

	@Override
	public float getSpawningChance() {
		return 0.07F;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);

		WorldGenAztechOakTree aztechOakTree = new WorldGenAztechOakTree(true);
		//WorldGenTallGrass tallGrass = new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
		WorldGenShortGrass shortgrass1 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.NORMAL);
		WorldGenShortGrass shortgrass2 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.STALKY);
		WorldGenShortGrass shortgrass3 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.FLAT);
		WorldGenShortGrass shortgrass4 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.THICC);
		WorldGenAztechLiquidBase genLakes = new WorldGenAztechLiquidBase(Blocks.WATER);
		WorldGenAztechLiquidBase genLavaLakes = new WorldGenAztechLiquidBase(Blocks.LAVA);
		WorldGenFlowers genFlowers = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlower.EnumFlowerType.DANDELION);
		WorldGenFlowers genFlowers2 = new WorldGenFlowers(Blocks.RED_FLOWER, BlockFlower.EnumFlowerType.ALLIUM);
		WorldGenWaterlily genWaterlily = new WorldGenWaterlily();
		WorldGenSmolTree genSmolTree = new WorldGenSmolTree(true);
		WorldGenAztechClay genAztechClay = new WorldGenAztechClay();
		WorldGenAztechSand genAztechSand = new WorldGenAztechSand();
		WorldGenAztechDirt genAztechDirt = new WorldGenAztechDirt();
		WorldGenCropBlotch cropBlotch = new WorldGenCropBlotch();

		BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos(0, 0, 0);
		for(int i = 0; i < 75; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 15 + rand.nextInt(60) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			aztechOakTree.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 75; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 15 + rand.nextInt(60) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genSmolTree.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 15; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 5 + rand.nextInt(90) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			//tallGrass.generate(worldIn, rand, mutPos);
			genFlowers.generate(worldIn, rand, mutPos);
			genFlowers2.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 90; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 5 + rand.nextInt(90) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genWaterlily.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 1; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 15 + rand.nextInt(60) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			if(rand.nextInt(10) == 0) {
				genLavaLakes.generate(worldIn, rand, mutPos);
			}
			genLakes.generate(worldIn, rand, mutPos);
			cropBlotch.generate(worldIn, rand, mutPos);
		}

		for(int i = 0; i < 120; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = worldIn.getSeaLevel() - rand.nextInt(6);
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genAztechClay.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 120; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = worldIn.getSeaLevel() - rand.nextInt(6);
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genAztechSand.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 120; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = worldIn.getSeaLevel() - rand.nextInt(6);
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genAztechDirt.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 4; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 2 + rand.nextInt(124);
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			shortgrass1.generate(worldIn, rand, mutPos);
			shortgrass2.generate(worldIn, rand, mutPos);
			shortgrass3.generate(worldIn, rand, mutPos);
			shortgrass4.generate(worldIn, rand, mutPos);
		}
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateMurkySwampTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 587547;
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return getGrassColorAtPos(pos);
	}

	@Override
	public int getWaterColorMultiplier() {
		return 10875392;
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return 587547;
	}

	protected void generateMurkySwampTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int seaLevel = worldIn.getSeaLevel();
		IBlockState topBlock = this.topBlock;
		IBlockState fillerBlock = this.fillerBlock;
		int j = -1;
		int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int chunkX = x & 15;
		int chunkZ = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int currentY = 255; currentY >= 0; --currentY) {
			if(currentY <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, BlocksInit.ANCIENT_BEDROCK.getDefaultState());
			}
			else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(chunkZ, currentY, chunkX);

				if(iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				}
				else if(iblockstate2.getBlock() == Blocks.STONE) {
					if(j == -1) {
						if(k <= 0) {
							topBlock = AIR;
							fillerBlock = STONE;
						}
						else if(currentY >= seaLevel - 4 && currentY <= seaLevel + 1) {
							topBlock = this.topBlock;
							fillerBlock = this.fillerBlock;
						}

						if(currentY < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR)) {
							if(this.getTemperature(blockpos$mutableblockpos.setPos(x, currentY, z)) < 0.15F) {
								topBlock = ICE;
							}
							else {
								topBlock = WATER;
							}
						}

						j = k;

						if(currentY >= seaLevel - 1) {
							chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, topBlock);
						}
						else if(currentY < seaLevel - 7 - k) {
							topBlock = AIR;
							fillerBlock = STONE;
							chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, GRAVEL);
						}
						else {
							chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, fillerBlock);
						}
					}
					else if(j > 0) {
						--j;
						chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, fillerBlock);

						if(j == 0 && fillerBlock.getBlock() == Blocks.SAND && k > 1) {
							j = rand.nextInt(4) + Math.max(0, currentY - 63);
							fillerBlock = fillerBlock.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
						}
					}
				}
			}
		}
	}

}
