package litewolf101.aztech.world.worldgen.structures;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.AztechStructureHandler;
import litewolf101.aztech.world.biome.AztechBiomes;
import litewolf101.aztech.world.biome.BiomeAncientForest;
import litewolf101.aztech.world.worldgen.ores.WorldGenAzTechOres;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by LiteWolf101 on 10/23/2018.
 */
public class WorldGenCustomStructures implements IWorldGenerator {
    public static final AztechStructureHandler AZTECH_PORTAL = new AztechStructureHandler("aztech_portal");
    public static final WorldGenAzTechOres oreGen = new WorldGenAzTechOres();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()){
            case 0:
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 500, Blocks.GRASS, Biomes.FOREST.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 500, Blocks.GRASS, Biomes.ICE_PLAINS.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 500, Blocks.GRASS, Biomes.TAIGA.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 500, Blocks.GRASS, Biomes.PLAINS.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 500, Blocks.GRASS, Biomes.SAVANNA.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 500, Blocks.GRASS, Biomes.DESERT.getBiomeClass());
                //oreGen.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                break;
            case 1:
                break;
            case -1:
                break;
            case 17: //TODO Adjust Dimension number
                oreGen.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                break;
        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes){
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

        int x = chunkX * 16;
        int z = chunkZ * 16;
        int y = calculateGenerationHeight(world, x, z, topBlock);
        BlockPos pos = new BlockPos(x,y,z);

        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

        if(world.getWorldType() != WorldType.FLAT){
            if (classesList.contains(biome)){
                if(random.nextInt(chance) == 0){
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock){
        int y = world.getHeight();
        boolean foundGround = false;

        while(!foundGround && y-- >= 15){
            Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = block == topBlock;
        }
        return y;
    }
}
