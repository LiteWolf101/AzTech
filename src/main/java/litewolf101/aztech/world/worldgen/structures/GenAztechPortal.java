package litewolf101.aztech.world.worldgen.structures;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.objects.blocks.BlockTempleStone;
import litewolf101.aztech.utils.handlers.EnumHalf;
import litewolf101.aztech.utils.handlers.EnumPortalPart;
import litewolf101.aztech.utils.handlers.EnumTempleStoneType;
import litewolf101.aztech.world.biome.AztechBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static litewolf101.aztech.objects.blocks.BlockTempleStone.STONE_TYPE;
import static litewolf101.aztech.objects.blocks.GeoluminescentObelisk.HALF;
import static litewolf101.aztech.objects.blocks.PortalMultiblock.PART;

/**
 * Created by LiteWolf101 on 10/23/2018.
 */
public class GenAztechPortal extends WorldGenerator {
    protected IBlockState stone = BlocksInit.TEMPLE_STONE.getDefaultState();
    protected IBlockState stone2 = BlocksInit.TEMPLE_STONE.getDefaultState().withProperty(STONE_TYPE, EnumTempleStoneType.EnumType.BRICKS);

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        Material materialBelow = worldIn.getBlockState(position.down()).getMaterial();
        Material materialAbove = worldIn.getBlockState(position.up()).getMaterial();
        if (position.getY() <= 13 && position.getY() + 7 >= 128 || materialBelow != Material.GRASS && materialBelow != Material.GROUND || worldIn.provider.getDimension() != 17 || materialAbove == Material.WATER) {
            return false;
        }

        generatePortal(worldIn, rand, position);

        return true;
    }

    protected void generatePortal(World world, Random random, BlockPos pos) {
        for (int x = -3; x <= 3; x++){
            for (int z = -3; z <= 3; z++){
                for (int y = 0; y <= 4; y++){
                    world.setBlockToAir(pos.add(x, y, z));
                }

            }
        }
        for (int x = -3; x <= 3; x++){
            for (int z = -3; z <= 3; z++){
                setBlockAndNotifyAdequately(world, pos.add(x, 0, z), stone);
            }
        }
        for (int x = -2; x <= 2; x++){
            for (int z = -2; z <= 2; z++){
                setBlockAndNotifyAdequately(world, pos.add(x, 0, z), stone2);
            }
        }
        for (int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                setBlockAndNotifyAdequately(world, pos.add(x, 0, z), stone);
            }
        }
        setBlockAndNotifyAdequately(world ,pos.add(3, 1, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        setBlockAndNotifyAdequately(world ,pos.add(3, 1, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        setBlockAndNotifyAdequately(world ,pos.add(-3, 1, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        setBlockAndNotifyAdequately(world ,pos.add(-3, 1, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));

        setBlockAndNotifyAdequately(world ,pos.add(3, 2, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        setBlockAndNotifyAdequately(world ,pos.add(3, 2, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        setBlockAndNotifyAdequately(world ,pos.add(-3, 2, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        setBlockAndNotifyAdequately(world ,pos.add(-3, 2, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));

        setBlockAndNotifyAdequately(world, pos.up(), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BOTTOM));
        setBlockAndNotifyAdequately(world, pos.up(2), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.MIDDLE));
        setBlockAndNotifyAdequately(world, pos.up(3), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.TOP));
        setBlockAndNotifyAdequately(world, pos.up(4), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BRACE));
    }
}
