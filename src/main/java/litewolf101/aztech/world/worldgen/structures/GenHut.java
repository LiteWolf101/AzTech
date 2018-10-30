package litewolf101.aztech.world.worldgen.structures;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumDirectional;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

import static litewolf101.aztech.objects.blocks.Lantern.FACING;
import static net.minecraft.block.BlockFlowerPot.CONTENTS;
import static net.minecraft.block.BlockFlowerPot.LEGACY_DATA;
import static net.minecraft.block.BlockStairs.HALF;
import static net.minecraft.block.BlockStairs.SHAPE;

/**
 * Created by LiteWolf101 on 10/25/2018.
 */
public class GenHut extends WorldGenerator {
    IBlockState flowerPot = Blocks.FLOWER_POT.getDefaultState().withProperty(CONTENTS, BlockFlowerPot.EnumFlowerType.DEAD_BUSH);
    //TopDown view facing north
    IBlockState bottomLeftCorner = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.EAST).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
    IBlockState bottomRightCorner = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
    IBlockState topRightCorner = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.WEST).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
    IBlockState topLeftCorner = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);

    IBlockState bottom = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
    IBlockState right = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.WEST).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
    IBlockState top = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
    IBlockState left = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.EAST).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        Material materialBelow = worldIn.getBlockState(position.down()).getMaterial();
        Material materialAbove = worldIn.getBlockState(position.up()).getMaterial();
        if (position.getY() <= worldIn.getSeaLevel() && position.getY() + 7 >= 90 || materialBelow != Material.GRASS && materialBelow != Material.GROUND || worldIn.provider.getDimension() != 17 || materialAbove == Material.WATER) {
            return false;
        }

        generateHut(worldIn, rand, position);

        return true;
    }

    private void generateHut(World worldIn, Random rand, BlockPos position) {
        //generate ground
        for (int x = -2; x <= 2; x++){
            for (int z = -2; z <= 2; z++){
                setBlockAndNotifyAdequately(worldIn, position.add(x, -1, z), BlocksInit.ANCIENT_DRY_MUD.getDefaultState());
            }
        }

        //generate muddy brick boxes
        for (int x = -2; x <= 2; x++){
            for (int z = -2; z <= 2; z++){
                for (int y = 0; y <= 3; y++){
                    setBlockAndNotifyAdequately(worldIn, position.add(x, y, z), BlocksInit.ANCIENT_MUDDY_BRICKS.getDefaultState());
                }
            }
        }
        for (int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                for (int y = 4; y <= 5; y++){
                    setBlockAndNotifyAdequately(worldIn, position.add(x, y, z), BlocksInit.ANCIENT_MUDDY_BRICKS.getDefaultState());
                }
            }
        }

        //Fill the boxes with air
        for (int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                for (int y = 0; y <= 2; y++){
                    worldIn.setBlockToAir(position.add(x, y, z));
                }
            }
        }
        worldIn.setBlockToAir(position.south(2));
        worldIn.setBlockToAir(position.add(0, 1, 2));

        //Add internal decor
        setBlockAndNotifyAdequately(worldIn, position.add(2, 1, 0), flowerPot);
        setBlockAndNotifyAdequately(worldIn, position.add(0, 1, -2), flowerPot);
        setBlockAndNotifyAdequately(worldIn, position.add(-2, 1, 0), flowerPot);
        setBlockAndNotifyAdequately(worldIn,position.up(3), BlocksInit.LANTERN.getDefaultState().withProperty(FACING, EnumDirectional.EnumFacing.DOWN));

        //Add roof (ugh...)
        //layer 1
        setBlockAndNotifyAdequately(worldIn, position.add(-3, 3, 3), bottomLeftCorner);
        for (int x = -2; x <= 2; x++){
            setBlockAndNotifyAdequately(worldIn, position.add(x, 3, 3), bottom);
        }
        setBlockAndNotifyAdequately(worldIn, position.add(3, 3, 3), bottomRightCorner);
        for (int z = 2; z >= -2; z--){
            setBlockAndNotifyAdequately(worldIn, position.add(3, 3, z), right);
        }
        setBlockAndNotifyAdequately(worldIn, position.add(3, 3, -3), topRightCorner);
        for (int x = 2; x >= -2; x--){
            setBlockAndNotifyAdequately(worldIn, position.add(x, 3, -3), top);
        }
        setBlockAndNotifyAdequately(worldIn, position.add(-3, 3, -3), topLeftCorner);
        for (int z = -2; z <= 2; z++){
            setBlockAndNotifyAdequately(worldIn, position.add(-3, 3, z), left);
        }
        //layer 2
        setBlockAndNotifyAdequately(worldIn, position.add(-2, 4, 2), bottomLeftCorner);
        for (int x = -1; x <= 1; x++){
            setBlockAndNotifyAdequately(worldIn, position.add(x, 4, 2), bottom);
        }
        setBlockAndNotifyAdequately(worldIn, position.add(2, 4, 2), bottomRightCorner);
        for (int z = 1; z >= -1; z--){
            setBlockAndNotifyAdequately(worldIn, position.add(2, 4, z), right);
        }
        setBlockAndNotifyAdequately(worldIn, position.add(2, 4, -2), topRightCorner);
        for (int x = 1; x >= -1; x--){
            setBlockAndNotifyAdequately(worldIn, position.add(x, 4, -2), top);
        }
        setBlockAndNotifyAdequately(worldIn, position.add(-2, 4, -2), topLeftCorner);
        for (int z = -1; z <= 1; z++){
            setBlockAndNotifyAdequately(worldIn, position.add(-2, 4, z), left);
        }
        //layer 3
        setBlockAndNotifyAdequately(worldIn, position.add(-1, 5, 1), bottomLeftCorner);
        setBlockAndNotifyAdequately(worldIn, position.add(0, 5, 1), bottom);
        setBlockAndNotifyAdequately(worldIn, position.add(1, 5, 1), bottomRightCorner);
        setBlockAndNotifyAdequately(worldIn, position.add(1, 5, 0), right);
        setBlockAndNotifyAdequately(worldIn, position.add(1, 5, -1), topRightCorner);
        setBlockAndNotifyAdequately(worldIn, position.add(0, 5, -1), top);
        setBlockAndNotifyAdequately(worldIn, position.add(-1, 5, -1), topLeftCorner);
        setBlockAndNotifyAdequately(worldIn, position.add(-1, 5, 0), left);

        //Gen loot
        worldIn.setBlockState(position.north(), Blocks.CHEST.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH));
        TileEntity tileEntity = worldIn.getTileEntity(position.north());

        if (tileEntity instanceof TileEntityChest)
        {
            ((TileEntityChest)tileEntity).setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, rand.nextLong());
        }

    }
}
