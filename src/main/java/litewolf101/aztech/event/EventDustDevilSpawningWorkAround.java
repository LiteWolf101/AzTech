package litewolf101.aztech.event;

import litewolf101.aztech.objects.entitymisc.EntityDustDevil;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.world.biome.AztechBiomes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MODID)
//This is a very stupid method. Don't do this.
public class EventDustDevilSpawningWorkAround {

    @SubscribeEvent
    public static void annoyThePlayer(PlayerEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player != null) {
            Biome biome = player.world.getBiome(player.getPosition());
            if (biome == AztechBiomes.biomeAridLands) {
                EntityDustDevil dustDevil = new EntityDustDevil(player.world);
                Random rand = new Random();
                int randX = rand.nextInt(32) - 16;
                int randY = rand.nextInt(8) - 4;
                int randZ = rand.nextInt(32) - 16;
                BlockPos pos = new BlockPos.MutableBlockPos((int) player.posX + randX,(int) player.posY + randY,(int) player.posZ + randZ);
                if (rand.nextInt(1000) == 0) {
                    if (checkValidLocation(pos, player.world)) {
                        dustDevil.posX = pos.getX();
                        dustDevil.posY = pos.getY();
                        dustDevil.posZ = pos.getZ();
                        player.world.spawnEntity(dustDevil);
                    }
                }
            }
        }
    }

    static boolean checkValidLocation (BlockPos pos, World world) {
        int check = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (!world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z)).isFullBlock()) {
                        check++;
                    }
                }
            }
        }
        return check == 27;
    }
}
