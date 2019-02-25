package litewolf101.aztech.world.worldgen.trees;

import litewolf101.aztech.config.AzTechConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

/**
 * Created by LiteWolf101 on 10/23/2018.
 */
public class WorldGenAztechOakTree2 extends WorldGenAbstractTree {
    private int minTrunkHeight;

    protected IBlockState log = Blocks.LOG.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
    protected IBlockState leaves = Blocks.LEAVES.getDefaultState();

    public WorldGenAztechOakTree2(boolean notify) {
        this(notify, 3);
    }

    public WorldGenAztechOakTree2(boolean notify, int minTrunkHeight) {
        super(notify);
        this.minTrunkHeight = minTrunkHeight;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        Material materialBelow = worldIn.getBlockState(position.down()).getMaterial();

        //Let's make sure we're at the right height and in the right dimension
        if (position.getY() <= 13 && position.getY() + minTrunkHeight + 1 >= 128 || worldIn.getBlockState(position.up()).getMaterial() == Material.WATER || materialBelow != Material.GRASS && materialBelow != Material.GROUND || worldIn.provider.getDimension() != AzTechConfig.dimension_ID) {
            return false;
        } else {
            checkIsAirAndBuild(worldIn, position, rand);
        }
        return true;
    }

    public void checkIsAirAndBuild(World world, BlockPos position, Random rand){
        int trunkHeight = rand.nextInt(5) + minTrunkHeight;
        int treeHeight = trunkHeight + 6;
        int check = 0;
        for (int x = -3; x <= 3; x++){
            for (int y = trunkHeight - 1; y <= treeHeight; y++){
                for (int z = -3; z <= 3; z++){
                    BlockPos checkingpos = new BlockPos(position.getX() + x, position.getY() + y, position.getZ() + z);
                    if (world.getBlockState(checkingpos).getMaterial() == Material.GROUND || world.getBlockState(checkingpos).getMaterial() == Material.ROCK) {
                        check++;
                    }
                }
            }
        }
        if (check == 0) {
            buildLeaves(world, position, trunkHeight + 1);
            buildTrunk(world, position, trunkHeight);
            buildSupport(world, position, trunkHeight + 1);
        }
    }

    private void buildLeaves(World worldIn, BlockPos position, int trunkHeight) {
        //layer1
        for(int x = -1; x <= 1; x++){
            for (int z = -3; z <= 3; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight, z), leaves);
            }
        }
        for(int x = -3; x <= 3; x++){
            for (int z = -1; z <= 1; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight, z), leaves);
            }
        }
        for(int x = -2; x <= 2; x++){
            for (int z = -2; z <= 2; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight, z), leaves);
            }
        }
        //layer2
        for(int x = -2; x <= 2; x++){
            for (int z = -1; z <= 1; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight + 1, z), leaves);
            }
        }
        for(int x = -1; x <= 1; x++){
            for (int z = -2; z <= 2; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight + 1, z), leaves);
            }
        }
        //layer3
        setBlockAndNotifyAdequately(worldIn, position.add(1, trunkHeight + 2, 0), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(-1, trunkHeight + 2, 0), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2, 1), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2, -1), leaves);
        //layer4
        for(int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight + 3, z), leaves);
            }
            setBlockAndNotifyAdequately(worldIn, position.add(2, trunkHeight + 3, 0), leaves);
            setBlockAndNotifyAdequately(worldIn, position.add(-2, trunkHeight + 3, 0), leaves);
            setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, 2), leaves);
            setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, -2), leaves);
        }
        //layer5
        setBlockAndNotifyAdequately(worldIn, position.add(1, trunkHeight + 4, 0), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(-1, trunkHeight + 4, 0), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 4, 1), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 4, -1), leaves);
        setBlockAndNotifyAdequately(worldIn, position.up(trunkHeight + 5), leaves);

    }

    @Override
    protected boolean canGrowInto(Block blockType) {
        return blockType.getDefaultState().getMaterial() != Material.ROCK || blockType.getDefaultState().getMaterial() != Material.WATER;
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos) {
        return super.isReplaceable(world, pos);
    }

    private void buildSupport(World worldIn, BlockPos position, int trunkHeight) {
        for (int xz = 1; xz <= 2; xz++){
            setBlockAndNotifyAdequately(worldIn, position.add(xz, trunkHeight, 0), log);
            setBlockAndNotifyAdequately(worldIn, position.add(-xz, trunkHeight, 0), log);
            setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight, xz), log);
            setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight, -xz), log);
        }
        setBlockAndNotifyAdequately(worldIn, position.add(1, trunkHeight + 3, 0), log);
        setBlockAndNotifyAdequately(worldIn, position.add(-1, trunkHeight + 3, 0), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, 1), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, -1), log);
        for (int y = trunkHeight; y <= trunkHeight + 4; y++){
            setBlockAndNotifyAdequately(worldIn, position.up(y), log);
        }

    }

    private void buildTrunk(World worldIn, BlockPos position, int trunkHeight) {
        for (int y = 0; y <= trunkHeight; y++){
            setBlockAndNotifyAdequately(worldIn, position.up(y), log);
        }
    }
}
