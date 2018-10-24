package litewolf101.aztech.world.worldgen.trees;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

/**
 * Created by LiteWolf101 on 10/23/2018.
 */
public class WorldGenAztechOakTree extends WorldGenAbstractTree {
    private int minTrunkHeight;
    protected IBlockState log = Blocks.LOG.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
    protected IBlockState leaves = Blocks.LEAVES.getDefaultState();

    public WorldGenAztechOakTree(boolean notify) {
        this(notify, 3);
    }

    public WorldGenAztechOakTree(boolean notify, int minTrunkHeight) {
        super(notify);
        this.minTrunkHeight = minTrunkHeight;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int trunkHeight = rand.nextInt(3) + minTrunkHeight;
        int treeHeight = trunkHeight + 5;
        Material materialBelow = worldIn.getBlockState(position.down()).getMaterial();

        //Let's make sure we're at the right height and in the right dimension
        //TODO Adjust dimension number
        if (position.getY() <= 13 && position.getY() + minTrunkHeight + 1 >= 128 || materialBelow != Material.GRASS && materialBelow != Material.GROUND || worldIn.provider.getDimension() != 17) {
            return false;
        }
        buildLeaves(worldIn, position, trunkHeight);
        buildTrunk(worldIn, position, trunkHeight);
        buildSupport(worldIn, position, trunkHeight);
        return true;
    }

    private void buildLeaves(World worldIn, BlockPos position, int trunkHeight) {
        Random rand = new Random();
        int leafDrop = rand.nextInt(4) + 1;
        int leafDrop2 = rand.nextInt(4) + 1;
        int leafDrop3 = rand.nextInt(4) + 1;
        int leafDrop4 = rand.nextInt(4) + 1;
        for(int x = -3; x <= 3; x++){
            for (int z = -3; z <= 3; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight + 2, z), leaves);
            }
        }
        worldIn.setBlockToAir(position.add(-3, trunkHeight + 2, -3));
        worldIn.setBlockToAir(position.add(-3, trunkHeight + 2, 3));
        worldIn.setBlockToAir(position.add(3, trunkHeight + 2, 3));
        worldIn.setBlockToAir(position.add(3, trunkHeight + 2, -3));
        for(int x = -2; x <= 2; x++){
            for (int z = -2; z <= 2; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight + 3, z), leaves);
            }
        }
        worldIn.setBlockToAir(position.add(-2, trunkHeight + 3, -2));
        worldIn.setBlockToAir(position.add(-2, trunkHeight + 3, 2));
        worldIn.setBlockToAir(position.add(2, trunkHeight + 3, 2));
        worldIn.setBlockToAir(position.add(2, trunkHeight + 3, -2));
        for(int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, trunkHeight + 4, z), leaves);
            }
        }
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, 3), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(3, trunkHeight + 3, 0), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, -3), leaves);
        setBlockAndNotifyAdequately(worldIn, position.add(-3, trunkHeight + 3, 0), leaves);
        for (int y = 0; y <= leafDrop; y++){
            setBlockAndNotifyAdequately(worldIn, position.add(-4, trunkHeight + 2 - y, 0), leaves);
        }
        for (int y = 0; y <= leafDrop2; y++){
            setBlockAndNotifyAdequately(worldIn, position.add(4, trunkHeight + 2 - y, 0), leaves);
        }
        for (int y = 0; y <= leafDrop3; y++){
            setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2 - y, -4), leaves);
        }
        for (int y = 0; y <= leafDrop4; y++){
            setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2 - y, 4), leaves);
        }
    }

    @Override
    protected boolean canGrowInto(Block blockType) {
        return super.canGrowInto(blockType);
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos) {
        return super.isReplaceable(world, pos);
    }

    private void buildSupport(World worldIn, BlockPos position, int trunkHeight) {
        setBlockAndNotifyAdequately(worldIn, position.up(trunkHeight + 1), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 1, -1), log);
        setBlockAndNotifyAdequately(worldIn, position.add(1, trunkHeight + 1, 0), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 1, 1), log);
        setBlockAndNotifyAdequately(worldIn, position.add(-1, trunkHeight + 1, 0), log);

        setBlockAndNotifyAdequately(worldIn, position.up(trunkHeight + 2), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2, -2), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2, -3), log);
        setBlockAndNotifyAdequately(worldIn, position.add(2, trunkHeight + 2, 0), log);
        setBlockAndNotifyAdequately(worldIn, position.add(3, trunkHeight + 2, 0), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2, 2), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 2, 3), log);
        setBlockAndNotifyAdequately(worldIn, position.add(-2, trunkHeight + 2, 0), log);
        setBlockAndNotifyAdequately(worldIn, position.add(-3, trunkHeight + 2, 0), log);

        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, -1), log);
        setBlockAndNotifyAdequately(worldIn, position.add(1, trunkHeight + 3, 0), log);
        setBlockAndNotifyAdequately(worldIn, position.add(0, trunkHeight + 3, 1), log);
        setBlockAndNotifyAdequately(worldIn, position.add(-1, trunkHeight + 3, 0), log);
    }

    private void buildTrunk(World worldIn, BlockPos position, int trunkHeight) {
        setBlockAndNotifyAdequately(worldIn, position.north(), log);
        setBlockAndNotifyAdequately(worldIn, position.east(), log);
        setBlockAndNotifyAdequately(worldIn, position.south(), log);
        setBlockAndNotifyAdequately(worldIn, position.west(), log);
        for (int y = 0; y <= trunkHeight; y++){
            setBlockAndNotifyAdequately(worldIn, position.up(y), log);
        }
    }
}
