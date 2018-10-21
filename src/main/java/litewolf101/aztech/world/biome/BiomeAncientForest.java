package litewolf101.aztech.world.biome;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.Reference;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by LiteWolf101 on 10/20/2018.
 */
public class BiomeAncientForest extends Biome{
    private static BiomeProperties properties = new Biome.BiomeProperties("Ancient Forest");
    public BiomeAncientForest() {
        super(properties);
        this.setRegistryName(new ResourceLocation(Reference.MODID, "biome_ancient_forest"));
        this.decorator.flowersPerChunk = 0;
        this.decorator.treesPerChunk = 4;
        this.topBlock = BlocksInit.ANCIENT_GRASS.getDefaultState();
        this.fillerBlock = BlocksInit.ANCIENT_DIRT.getDefaultState();
        properties.setTemperature(1.7F);
        properties.setHeightVariation(0.5F);
        properties.setRainDisabled();

        spawnableMonsterList.clear();
        spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 7, 1, 1));

        spawnableCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 3, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 5, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 6, 2, 2));
    }



    @Override
    public float getSpawningChance() {
        return 0.07F;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        if (rand.nextInt(4) == 0) {
            return new WorldGenTallGrass(BlockTallGrass.EnumType.FERN);
        } else {
            return new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
        }
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        return 9493052;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random random) {
        if (random.nextInt(10) == 0) {
            return BIG_TREE_FEATURE;
        } else {
            return TREE_FEATURE;
        }
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos) {
        return 9493052;
    }

    @Override
    public int getWaterColorMultiplier() {
        return 9493052;
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.generateAncientForestTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    protected void generateAncientForestTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int seaLevel = worldIn.getSeaLevel();
        IBlockState topBlock = this.topBlock;
        IBlockState fillerBlock = this.fillerBlock;
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int chunkX = x & 15;
        int chunkZ = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int currentY = 255; currentY >= 0; --currentY) {
            if (currentY <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, BlocksInit.ANCIENT_BEDROCK.getDefaultState());
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(chunkZ, currentY, chunkX);

                if (iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (iblockstate2.getBlock() == Blocks.STONE) {
                    if (j == -1) {
                        if (k <= 0) {
                            topBlock = AIR;
                            fillerBlock = STONE;
                        } else if (currentY >= seaLevel - 4 && currentY <= seaLevel + 1) {
                            topBlock = BlocksInit.ANCIENT_GRASS.getDefaultState();
                            fillerBlock = BlocksInit.ANCIENT_DIRT.getDefaultState();
                        }

                        if (currentY < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, currentY, z)) < 0.15F) {
                                topBlock = ICE;
                            } else {
                                topBlock = WATER;
                            }
                        }

                        j = k;

                        if (currentY >= seaLevel - 1) {
                            chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, topBlock);
                        } else if (currentY < seaLevel - 7 - k) {
                            topBlock = AIR;
                            fillerBlock = STONE;
                            chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, GRAVEL);
                        } else {
                            chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, fillerBlock);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, fillerBlock);

                        if (j == 0 && fillerBlock.getBlock() == Blocks.SAND && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, currentY - 63);
                            fillerBlock = fillerBlock.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
                        }
                    }
                }
            }
        }
    }
}
