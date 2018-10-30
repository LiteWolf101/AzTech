package litewolf101.aztech.utils;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumHalf;
import litewolf101.aztech.utils.handlers.EnumPortalPart;
import litewolf101.aztech.utils.handlers.EnumTempleStoneType;
import litewolf101.aztech.world.worldgen.structures.GenAztechPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

import static litewolf101.aztech.objects.blocks.BlockTempleStone.STONE_TYPE;
import static litewolf101.aztech.objects.blocks.GeoluminescentObelisk.HALF;
import static litewolf101.aztech.objects.blocks.PortalMultiblock.PART;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class CustomTeleporter extends Teleporter{
    public CustomTeleporter(WorldServer world, double x, double y, double z) {
        super(world);
        this.worldServer = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private final WorldServer worldServer;
    private double x;
    private double y;
    private double z;

    @Override
    public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
        // The main purpose of this function is to *not* create a nether portal
        this.worldServer.getBlockState(new BlockPos((int) this.x, (int) this.y, (int) this.z));

        entity.setPosition(this.x, this.y, this.z);
        entity.motionX = 0.0f;
        entity.motionY = 0.0f;
        entity.motionZ = 0.0f;
    }


    public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
        int oldDimension = player.getEntityWorld().provider.getDimension();
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
        MinecraftServer server = player.getEntityWorld().getMinecraftServer();
        WorldServer worldServer = server.getWorld(dimension);
        player.addExperienceLevel(0);

        if (worldServer == null || worldServer.getMinecraftServer() == null){ //Dimension doesn't exist
            throw new IllegalArgumentException("Dimension: "+dimension+" doesn't exist!");
        }

        worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new CustomTeleporter(worldServer, x, y, z));
        player.setPositionAndUpdate(x, y, z);
        BlockPos pos = player.getPosition();
        generatePortal(worldServer, pos.add(0, -1, -2));

        if (oldDimension == 1) {
            // For some reason teleporting out of the end does weird things. Compensate for that
            player.setPositionAndUpdate(x, y, z);
            worldServer.spawnEntity(player);
            worldServer.updateEntityWithOptionalForce(player, false);
        }
    }

    private static void generatePortal(World world, BlockPos pos) {
        IBlockState stone = BlocksInit.TEMPLE_STONE.getDefaultState();
        IBlockState stone2 = BlocksInit.TEMPLE_STONE.getDefaultState().withProperty(STONE_TYPE, EnumTempleStoneType.EnumType.BRICKS);

        for (int x = -3; x <= 3; x++){
            for (int z = -3; z <= 3; z++){
                for (int y = 0; y <= 4; y++){
                    world.setBlockToAir(pos.add(x, y, z));
                }

            }
        }
        for (int x = -3; x <= 3; x++){
            for (int z = -3; z <= 3; z++){
                world.setBlockState( pos.add(x, 0, z), stone);
            }
        }
        for (int x = -2; x <= 2; x++){
            for (int z = -2; z <= 2; z++){
                world.setBlockState( pos.add(x, 0, z), stone2);
            }
        }
        for (int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                world.setBlockState( pos.add(x, 0, z), stone);
            }
        }
        world.setBlockState(pos.add(3, 1, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        world.setBlockState(pos.add(3, 1, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        world.setBlockState(pos.add(-3, 1, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        world.setBlockState(pos.add(-3, 1, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));

        world.setBlockState(pos.add(3, 2, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        world.setBlockState(pos.add(3, 2, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        world.setBlockState(pos.add(-3, 2, -3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        world.setBlockState(pos.add(-3, 2, 3), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));

        world.setBlockState( pos.up(), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BOTTOM));
        world.setBlockState( pos.up(2), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.MIDDLE));
        world.setBlockState( pos.up(3), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.TOP));
        world.setBlockState( pos.up(4), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BRACE));
    }
}
