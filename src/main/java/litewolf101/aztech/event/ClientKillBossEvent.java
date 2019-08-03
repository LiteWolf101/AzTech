package litewolf101.aztech.event;

import litewolf101.aztech.objects.mobs.BossUltimateEye;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by LiteWolf101 on Jan /11/2019
 */
@Mod.EventBusSubscriber(modid = Reference.MODID, value = Side.CLIENT)
public class ClientKillBossEvent {

	@SubscribeEvent
	public static void killedBoss(LivingDeathEvent event) {
		if(!event.getEntity().world.isRemote) {
			if(event.getEntity() instanceof BossUltimateEye) {
				if(wasEntityKilledByPlayer(event.getSource())) {
					event.getEntity().world.playSound(null, event.getEntity().getPosition(), AzTechSoundHandler.FANFARE, SoundCategory.MASTER, 1f, 1f);
				}
			}
		}
	}

	public static boolean wasEntityKilledByPlayer(DamageSource source) {
		return source.getTrueSource() instanceof EntityPlayerMP;
	}

}
