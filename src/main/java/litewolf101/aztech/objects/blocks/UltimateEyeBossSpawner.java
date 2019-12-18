package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.objects.BlockMobSpawnable;
import litewolf101.aztech.objects.mobs.BossUltimateEye;
import net.minecraft.block.material.Material;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class UltimateEyeBossSpawner extends BlockMobSpawnable {

    public UltimateEyeBossSpawner(String name, Material material) {
        super(name, material);
    }

    @Override
    public void spawnMob(World world, BlockPos pos) {
        AxisAlignedBB bb = new AxisAlignedBB(pos).grow(10);
        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, bb);
        List<EntityPlayer> noncreativePlayers = new ArrayList<EntityPlayer>();
        if (!players.isEmpty()) {
            for(EntityPlayer player : players) {
                if (!player.isCreative() && !player.isSpectator()) {
                    noncreativePlayers.add(player);
                }
            }
            if (!noncreativePlayers.isEmpty()) {
                BossUltimateEye ultimateEye = new BossUltimateEye(world);
                ultimateEye.enablePersistence();
                ultimateEye.moveToBlockPosAndAngles(pos, 0, 0);
                ultimateEye.onInitialSpawn(world.getDifficultyForLocation(pos), (IEntityLivingData)null);
                world.spawnEntity(ultimateEye);
                world.createExplosion(ultimateEye, pos.getX(), pos.getY(), pos.getZ(), 2, false);
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }
}
