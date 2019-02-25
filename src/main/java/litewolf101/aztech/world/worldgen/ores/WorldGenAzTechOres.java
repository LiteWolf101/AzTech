package litewolf101.aztech.world.worldgen.ores;

import litewolf101.aztech.config.AzTechConfig;
import litewolf101.aztech.dimension.AztechDimension;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumRuneColor;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static litewolf101.aztech.objects.blocks.BlockRuneOre.RUNE_COLOR;

/**
 * Created by LiteWolf101 on 10/22/2018.
 */
public class WorldGenAzTechOres implements IWorldGenerator {
    private WorldGenerator red_rune_ore, yellow_rune_ore, green_rune_ore, blue_rune_ore, white_rune_ore, black_rune_ore;
    private WorldGenerator ancient_coal_ore, ancient_iron_ore, ancient_redstone_ore, ancient_gold_ore, ancient_diamond_ore, ancient_emerald_ore, ancient_lapis_ore, ancient_quartz_ore;
    private WorldGenerator ancient_red_rune_ore, ancient_yellow_rune_ore, ancient_green_rune_ore, ancient_blue_rune_ore, ancient_white_rune_ore, ancient_black_rune_ore;

    public WorldGenAzTechOres() {
        Random random = new Random();
        int weight = random.nextInt(3);
        red_rune_ore = new WorldGenMinable(BlocksInit.RUNE_ORE.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.RED), 5 + weight, BlockMatcher.forBlock(Blocks.STONE));
        yellow_rune_ore = new WorldGenMinable(BlocksInit.RUNE_ORE.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.YELLOW), 5 + weight, BlockMatcher.forBlock(Blocks.STONE));
        green_rune_ore = new WorldGenMinable(BlocksInit.RUNE_ORE.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.GREEN), 5 + weight, BlockMatcher.forBlock(Blocks.STONE));
        blue_rune_ore = new WorldGenMinable(BlocksInit.RUNE_ORE.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.BLUE), 5 + weight, BlockMatcher.forBlock(Blocks.STONE));
        white_rune_ore = new WorldGenMinable(BlocksInit.RUNE_ORE.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.WHITE), 5 + weight, BlockMatcher.forBlock(Blocks.STONE));
        black_rune_ore = new WorldGenMinable(BlocksInit.RUNE_ORE.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.BLACK), 5 + weight, BlockMatcher.forBlock(Blocks.STONE));

        ancient_coal_ore = new WorldGenMinable(BlocksInit.COAL_ORE.getDefaultState(), 7 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_iron_ore = new WorldGenMinable(BlocksInit.IRON_ORE.getDefaultState(), 4 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_redstone_ore = new WorldGenMinable(BlocksInit.REDSTONE_ORE.getDefaultState(), 4 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_gold_ore = new WorldGenMinable(BlocksInit.GOLD_ORE.getDefaultState(), 2 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_diamond_ore = new WorldGenMinable(BlocksInit.DIAMOND_ORE.getDefaultState(), 1 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_emerald_ore = new WorldGenMinable(BlocksInit.EMERALD_ORE.getDefaultState(), 1 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_quartz_ore = new WorldGenMinable(BlocksInit.QUARTZ_ORE.getDefaultState(), 3 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_lapis_ore = new WorldGenMinable(BlocksInit.LAPIS_ORE.getDefaultState(), 4 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));

        ancient_red_rune_ore = new WorldGenMinable(BlocksInit.NEW_RED_RUNE_ORE.getDefaultState(), 3 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_yellow_rune_ore = new WorldGenMinable(BlocksInit.NEW_YELLOW_RUNE_ORE.getDefaultState(), 3 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_green_rune_ore = new WorldGenMinable(BlocksInit.NEW_GREEN_RUNE_ORE.getDefaultState(), 3 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_blue_rune_ore = new WorldGenMinable(BlocksInit.NEW_BLUE_RUNE_ORE.getDefaultState(), 3 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_white_rune_ore = new WorldGenMinable(BlocksInit.NEW_WHITE_RUNE_ORE.getDefaultState(), 3 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));
        ancient_black_rune_ore = new WorldGenMinable(BlocksInit.NEW_BLACK_RUNE_ORE.getDefaultState(), 3 + weight, BlockMatcher.forBlock(BlocksInit.ANCIENT_STONE));

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()){
            case -1:
                break;
            case 0:
                runGenerator(red_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.overworld_ore_frequency, 12, 45);
                runGenerator(yellow_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.overworld_ore_frequency, 12, 45);
                runGenerator(green_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.overworld_ore_frequency, 12, 45);
                runGenerator(blue_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.overworld_ore_frequency, 12, 45);
                runGenerator(white_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.overworld_ore_frequency, 12, 45);
                runGenerator(black_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.overworld_ore_frequency, 12, 45);
                break;
            case 1:
                break;
        }
        if (world.provider.getDimensionType() == AztechDimension.aztech) {
            runGenerator(ancient_coal_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_iron_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_redstone_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_gold_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_lapis_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_diamond_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_emerald_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_quartz_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_red_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_yellow_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_green_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_blue_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_white_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
            runGenerator(ancient_black_rune_ore, world, random, chunkX, chunkZ, AzTechConfig.aztech_ore_frequency, 5, 160);
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight){
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256){
            throw new IllegalArgumentException("Ore generated out of bounds! (An ore tried to escape the minecraft world!)");
        }
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chance; i++){
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
