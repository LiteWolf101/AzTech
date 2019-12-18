package litewolf101.aztech.world.mapgen;

import litewolf101.aztech.world.biome.AztechBiomes;
import litewolf101.aztech.world.mapgen.dungeon.eye_dungeon.EyeDungeon;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapGenEyeDungeon extends MapGenStructure {
    static {
        MapGenStructureIO.registerStructure(MapGenEyeDungeon.Start.class, "AzTech_EyeDungeon");

        MapGenStructureIO.registerStructureComponent(EyeDungeon.Entrance.class, "EDEntrance");
        MapGenStructureIO.registerStructureComponent(EyeDungeon.CenterRoom.class, "EDCenterRoom");

        MapGenStructureIO.registerStructureComponent(EyeDungeon.LeftTurn.class, "EDLeftTurn");
        MapGenStructureIO.registerStructureComponent(EyeDungeon.RightTurn.class, "EDRightTurn");
        MapGenStructureIO.registerStructureComponent(EyeDungeon.Straight.class, "EDStraight");

        MapGenStructureIO.registerStructureComponent(EyeDungeon.CapNormal.class, "EDCapNormal");
        MapGenStructureIO.registerStructureComponent(EyeDungeon.CapMined.class, "EDCapMined");
        MapGenStructureIO.registerStructureComponent(EyeDungeon.CapSpawner.class, "EDCapSpawner");
        MapGenStructureIO.registerStructureComponent(EyeDungeon.CapGarden.class, "EDCapGarden");
        MapGenStructureIO.registerStructureComponent(EyeDungeon.CapPortal.class, "EDCapPortal");

        MapGenStructureIO.registerStructureComponent(EyeDungeon.Crossing.class, "EDCrossing");
    }

    private static final List<Biome> BIOMELIST = Arrays.<Biome>asList(AztechBiomes.biomeAncientJungle, AztechBiomes.biomeMurkySwamp, AztechBiomes.biomeAncientForest);
    private int maxDistanceBetweenScatteredFeatures;
    private final int minDistanceBetweenScatteredFeatures;

    public MapGenEyeDungeon()
    {
        this.maxDistanceBetweenScatteredFeatures = 16;
        this.minDistanceBetweenScatteredFeatures = 8;
    }

    @Override
    public String getStructureName() {
        return "AzTech_EyeDungeon";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        /*long dungeonPos = getEyeDungeonPosForCoords(this.world, chunkX, chunkZ, ((AztechWorldProvider) this.world.provider).getEyeDungeonSpacing());
        int i = (int) (dungeonPos >> 32);
        int j = (int) dungeonPos;  //Java automatically gives the 32 least significant bits
        return i == chunkX && j == chunkZ;*/
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int k = chunkX / this.maxDistanceBetweenScatteredFeatures;
        int l = chunkZ / this.maxDistanceBetweenScatteredFeatures;
        Random random = this.world.setRandomSeed(k, l, 14357617);
        k = k * this.maxDistanceBetweenScatteredFeatures;
        l = l * this.maxDistanceBetweenScatteredFeatures;
        k = k + random.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);
        l = l + random.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);

        if (i == k && j == l)
        {
            Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(i * 16 + 8, 0, j * 16 + 8));

            if (biome == null)
            {
                return false;
            }

            for (Biome biome1 : BIOMELIST)
            {
                if (biome == biome1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /*public static long getEyeDungeonPosForCoords(World world, int chunkX, int chunkZ, int spacing)
    {
        final int numChunks = spacing / 16;
        if (chunkX < 0)
        {
            chunkX -= numChunks - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= numChunks - 1;
        }

        int k = chunkX / numChunks;
        int l = chunkZ / numChunks;
        long seed = (long)k * 341873128712L + (long)l * 132897987541L + world.getWorldInfo().getSeed() + (long)(10387340 + world.provider.getDimension());
        Random random = new Random();
        random.setSeed(seed);
        k = k * numChunks + random.nextInt(numChunks);
        l = l * numChunks + random.nextInt(numChunks);
        return (((long) k) << 32) + l;
    }*/

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.maxDistanceBetweenScatteredFeatures, 8, 14357617, false, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new MapGenEyeDungeon.Start(this.world, this.rand, chunkX, chunkZ);
    }

    public class Start extends StructureStart {
        public Start(){}

        public Start(World worldIn, Random random, int chunkX, int chunkZ)
        {
            this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16, 0, chunkZ * 16)));
        }

        public Start (World world, Random rand, int chunkX, int chunkZ, Biome biome) {
            super(chunkX, chunkZ);
            int x = chunkX * 16;
            int z = chunkZ * 16;

            BlockPos.MutableBlockPos newPos = new BlockPos.MutableBlockPos(x, 0, z);

            /*for (int y = 22; y <= 32; y++){
                if (world.getBlockState(new BlockPos(x, y, z)) == Blocks.AIR) {
                    newPos.setY(y);
                    break;
                }
                newPos.setY(32);
            }*/

            EyeDungeon.Entrance entrance = new EyeDungeon.Entrance(x, 32, z, rand);
            this.components.add(entrance);
            entrance.buildComponent(entrance, this.components, rand);
            this.updateBoundingBox();
        }
    }
}
