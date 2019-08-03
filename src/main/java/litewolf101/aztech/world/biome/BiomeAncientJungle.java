package litewolf101.aztech.world.biome;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.mobs.MobAncientSquid;
import litewolf101.aztech.utils.handlers.EnumAzTechPlantType;
import litewolf101.aztech.world.worldgen.feature.WorldGenAztechLiquidBase;
import litewolf101.aztech.world.worldgen.feature.WorldGenCropBlotch;
import litewolf101.aztech.world.worldgen.feature.WorldGenMelonsInAzTech;
import litewolf101.aztech.world.worldgen.feature.WorldGenShortGrass;
import litewolf101.aztech.world.worldgen.trees.WorldGenAztechOakTree;
import static net.minecraft.block.BlockOldLog.VARIANT;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

/**
 * Created by LiteWolf101 on Feb /25/2019
 */
public class BiomeAncientJungle extends Biome {

	private static BiomeProperties properties = new Biome.BiomeProperties("Ancient Jungle");

	public BiomeAncientJungle() {
		super(properties);
		this.topBlock = BlocksInit.ANCIENT_GRASS.getDefaultState();
		this.fillerBlock = BlocksInit.ANCIENT_DIRT.getDefaultState();
		properties.setTemperature(1.7F);
		properties.setHeightVariation(0.5F);
		properties.setRainDisabled();

		spawnableCaveCreatureList.clear();

		spawnableWaterCreatureList.clear();

		spawnableMonsterList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 3, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 4, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 1, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 2, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 3));
		spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 4, 2, 3));
		//spawnableMonsterList.add(new SpawnListEntry(SoulStealer.class, 7, 1, 1));
		//spawnableMonsterList.add(new SpawnListEntry(Ex.class, 7, 1, 1));
		//spawnableMonsterList.add(new SpawnListEntry(AoAo.class, 7, 1, 1));

		spawnableCreatureList.clear();
		spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 3, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 5, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 6, 2, 2));
		spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 6, 2, 2));

		spawnableWaterCreatureList.clear();
		spawnableWaterCreatureList.add(new SpawnListEntry(MobAncientSquid.class, 10, 4, 4));
	}

	@Override
	public float getSpawningChance() {
		return 0.025F;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);

		WorldGenMegaJungle genMegaJungle = new WorldGenMegaJungle(false, 10, 6, Blocks.LOG.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.JUNGLE), Blocks.LEAVES.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.JUNGLE));
		WorldGenShrub genShrub = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.JUNGLE), Blocks.LEAVES.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.JUNGLE));
		//WorldGenTallGrass tallGrass = new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
		WorldGenShortGrass shortgrass1 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.NORMAL);
		WorldGenShortGrass shortgrass2 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.STALKY);
		WorldGenShortGrass shortgrass3 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.FLAT);
		WorldGenShortGrass shortgrass4 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.THICC);
		WorldGenAztechLiquidBase genLakes = new WorldGenAztechLiquidBase(Blocks.WATER);
		WorldGenCropBlotch cropBlotch = new WorldGenCropBlotch();
		WorldGenAztechOakTree genAztechOakTree = new WorldGenAztechOakTree(false, 5);
		WorldGenMelonsInAzTech genMelon = new WorldGenMelonsInAzTech();

		BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos(0, 0, 0);
		for(int i = 0; i < 380; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 15 + rand.nextInt(100) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genMegaJungle.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 60; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 15 + rand.nextInt(100) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genAztechOakTree.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 100; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 15 + rand.nextInt(90) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genShrub.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 15; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 5 + rand.nextInt(90) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			//tallGrass.generate(worldIn, rand, mutPos);
			genMelon.generate(worldIn, rand, mutPos);
		}
		for(int i = 0; i < 1; i++) {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int ry = 2 + rand.nextInt(67) + 4;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			mutPos.setPos(rx, ry, rz);
			genLakes.generate(worldIn, rand, mutPos);
			cropBlotch.generate(worldIn, rand, mutPos);
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
		this.generateAncientForestTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 2585617;
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 2585617;
	}

	@Override
	public int getWaterColorMultiplier() {
		return 9493052;
	}

	protected void generateAncientForestTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
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
							topBlock = BlocksInit.ANCIENT_GRASS.getDefaultState();
							fillerBlock = BlocksInit.ANCIENT_DIRT.getDefaultState();
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