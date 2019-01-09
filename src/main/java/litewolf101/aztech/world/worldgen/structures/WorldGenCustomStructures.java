package litewolf101.aztech.world.worldgen.structures;

import litewolf101.aztech.dimension.AztechDimension;
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

    public static final AztechStructureHandler BASIC_DUNGEON_ENTRANCE = new AztechStructureHandler("aztech_basic_dungeon_enter_north");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_MIDDLE_BOTTOM = new AztechStructureHandler("aztech_basic_dungeon_enter2_north");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_RIGHT_BOTTOM = new AztechStructureHandler("basic_dungeon_f1_br");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_RIGHT_MIDDLE = new AztechStructureHandler("basic_dungeon_f1_mr");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_RIGHT_TOP = new AztechStructureHandler("basic_dungeon_f1_tr");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_MIDDLE_TOP = new AztechStructureHandler("basic_dungeon_f1_tm");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_LEFT_TOP = new AztechStructureHandler("basic_dungeon_f1_tl");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_LEFT_MIDDLE = new AztechStructureHandler("basic_dungeon_f1_ml");
    public static final AztechStructureHandler BASIC_DUNGEON_F1_LEFT_BOTTOM = new AztechStructureHandler("basic_dungeon_f1_bl");

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
                break;
            case 1:
                break;
            case -1:
                break;
            case 17: //TODO Adjust Dimension number
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 10, BlocksInit.ANCIENT_GRASS, AztechBiomes.biomeAncientForest.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 10, Blocks.GRASS, AztechBiomes.biomeAncientOcean.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 10, Blocks.SAND, AztechBiomes.biomeAridLands.getBiomeClass());
                generateStructure(AZTECH_PORTAL, world, random, chunkX, chunkZ, 10, Blocks.GRASS, AztechBiomes.biomeMurkySwamp.getBiomeClass());
                generateBasicDungeon(BASIC_DUNGEON_ENTRANCE, world, random, chunkX, chunkZ, 50, Blocks.GRASS, AztechBiomes.biomeMurkySwamp.getBiomeClass());
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

    private void generateBasicDungeon(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes){
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
                    BASIC_DUNGEON_F1_MIDDLE_BOTTOM.generate(world, random, pos.down(16));
                    BASIC_DUNGEON_F1_RIGHT_BOTTOM.generate(world, random, pos.add(16, -16, 0));
                    BASIC_DUNGEON_F1_RIGHT_MIDDLE.generate(world, random, pos.add(16, -16, -16));
                    BASIC_DUNGEON_F1_RIGHT_TOP.generate(world, random, pos.add(16, -16, -32));
                    BASIC_DUNGEON_F1_MIDDLE_TOP.generate(world, random, pos.add(0, -16, -32));
                    BASIC_DUNGEON_F1_LEFT_TOP.generate(world, random, pos.add(-16, -16, -32));
                    BASIC_DUNGEON_F1_LEFT_MIDDLE.generate(world, random, pos.add(-16, -16, -16));
                    BASIC_DUNGEON_F1_LEFT_BOTTOM.generate(world, random, pos.add(-16, -16, 0));
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
