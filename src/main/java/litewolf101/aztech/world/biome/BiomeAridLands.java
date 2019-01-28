package litewolf101.aztech.world.biome;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.mobs.MobPyronant;
import litewolf101.aztech.world.worldgen.feature.WorldGenAztechLiquidBase;
import litewolf101.aztech.world.worldgen.feature.WorldGenDryMudReplaceable;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLakes;

import java.util.Random;

/**
 * Created by LiteWolf101 on 10/24/2018.
 */
public class BiomeAridLands extends Biome {
    private static BiomeProperties properties = new Biome.BiomeProperties("Arid Lands");
    public BiomeAridLands() {
        super(properties);
        this.fillerBlock = Blocks.SANDSTONE.getDefaultState();
        this.topBlock = Blocks.SAND.getDefaultState();
        properties.setTemperature(1.7F);
        properties.setHeightVariation(0.1F);
        properties.setRainDisabled();
        properties.setBaseHeight(0.2f);

        spawnableMonsterList.clear();
        spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 7, 1, 1));
        spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 7, 1, 1));
        spawnableMonsterList.add(new SpawnListEntry(EntityWitherSkeleton.class, 7, 1, 1));
        spawnableMonsterList.add(new SpawnListEntry(MobPyronant.class, 4, 1, 1));


        spawnableCreatureList.clear();

        spawnableCaveCreatureList.clear();

        spawnableWaterCreatureList.clear();
    }

    @Override
    public float getSpawningChance() {
        return 0.56F;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        return 13492102;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos) {
        return getGrassColorAtPos(pos);
    }

    @Override
    public int getWaterColorMultiplier() {
        return 7245492;
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        WorldGenCactus cactus = new WorldGenCactus();
        WorldGenDryMudReplaceable dryMudReplaceable = new WorldGenDryMudReplaceable();
        WorldGenAztechLiquidBase genLavaLakes = new WorldGenAztechLiquidBase(Blocks.LAVA);
        WorldGenAztechLiquidBase genDryMud = new WorldGenAztechLiquidBase(BlocksInit.ANCIENT_DRY_MUD);
        WorldGenDeadBush deadBush = new WorldGenDeadBush();

        BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos(0, 0, 0);
        for (int i = 0; i < 20; i++) {
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int ry = 15 + rand.nextInt(60) + 8;
            int rz = pos.getZ() + rand.nextInt(16) + 8;
            mutPos.setPos(rx, ry, rz);
            cactus.generate(worldIn, rand, mutPos);
            deadBush.generate(worldIn, rand, mutPos);
        }
        for (int i = 0; i < 1; i++) {
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int ry = 15 + rand.nextInt(60) + 4;
            int rz = pos.getZ() + rand.nextInt(16) + 8;
            mutPos.setPos(rx, ry, rz);
            dryMudReplaceable.generate(worldIn, rand, mutPos);
        }
        for (int i = 0; i < 1; i++) {
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int ry = 15 + rand.nextInt(60) + 4;
            int rz = pos.getZ() + rand.nextInt(16) + 8;
            mutPos.setPos(rx, ry, rz);
            if (rand.nextInt(10) == 0){
                genLavaLakes.generate(worldIn, rand, mutPos);
                genDryMud.generate(worldIn, rand, pos);
            }
        }
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.generateAridLandsTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    protected void generateAridLandsTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
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
                            topBlock = this.topBlock;
                            fillerBlock = this.fillerBlock;
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
