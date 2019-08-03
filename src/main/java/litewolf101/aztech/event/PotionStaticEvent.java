package litewolf101.aztech.event;

import static litewolf101.aztech.init.PotionsInit.STATIC;
import litewolf101.aztech.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class PotionStaticEvent {

	static int tick;

	@SubscribeEvent
	public static void shake(TickEvent.PlayerTickEvent event) {
		tick++;
		boolean isActive = false;
		if(event.player.isPotionActive(STATIC)) {
			isActive = true;
		}
		if(isActive) {
			if(tick % 2 == 0) {
				event.player.rotationYaw = event.player.prevRotationYaw + 2;
			}
			if(tick % 2 == 1) {
				event.player.rotationYaw = event.player.prevRotationYaw - 2;
			}
		}
	}

}
