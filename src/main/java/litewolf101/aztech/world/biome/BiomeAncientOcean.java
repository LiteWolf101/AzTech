package litewolf101.aztech.world.biome;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.mobs.MobAncientSquid;
import litewolf101.aztech.utils.handlers.EnumAzTechPlantType;
import litewolf101.aztech.world.worldgen.feature.WorldGenAztechLiquidBase;
import litewolf101.aztech.world.worldgen.feature.WorldGenCropBlotch;
import litewolf101.aztech.world.worldgen.feature.WorldGenMelonsInAzTech;
import litewolf101.aztech.world.worldgen.feature.WorldGenShortGrass;
import litewolf101.aztech.world.worldgen.trees.WorldGenAztechOakTree;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

import static net.minecraft.block.BlockOldLog.VARIANT;

/**
 * Created by LiteWolf101 on 11/2/2018.
 */
public class BiomeAncientOcean extends Biome {
    private static BiomeProperties properties = new Biome.BiomeProperties("Ancient Ocean");
    public BiomeAncientOcean() {
        super(properties);
        this.topBlock = BlocksInit.ANCIENT_GRASS.getDefaultState();
        this.fillerBlock = BlocksInit.ANCIENT_DIRT.getDefaultState();
        properties.setTemperature(1.7F);
        properties.setHeightVariation(0.0F);
        properties.setRainDisabled();
        properties.setBaseHeight(-6.7f);

        spawnableMonsterList.clear();
        spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 2, 1, 1));
        spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 2, 1, 1));
        spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 2, 1, 1));
        spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 2, 1, 1));

        spawnableCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 4, 1, 3));
        spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 3, 1, 2));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 1, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(MobAncientSquid.class, 10, 4, 4));

        spawnableWaterCreatureList.clear();
        spawnableWaterCreatureList.add(new SpawnListEntry(MobAncientSquid.class, 10, 4, 4));


    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        //WorldGenTallGrass tallGrass = new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
        WorldGenShortGrass shortgrass1 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.NORMAL);
        WorldGenShortGrass shortgrass4 = new WorldGenShortGrass(EnumAzTechPlantType.EnumType.THICC);

        BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos(0, 0, 0);
        for (int i = 0; i < 1; i++) {
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int ry = 2 + rand.nextInt(124);
            int rz = pos.getZ() + rand.nextInt(16) + 8;
            mutPos.setPos(rx, ry, rz);
            shortgrass1.generate(worldIn, rand, mutPos);
            shortgrass4.generate(worldIn, rand, mutPos);
        }
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.generateAncientOceanTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    protected void generateAncientOceanTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
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
