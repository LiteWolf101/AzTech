package litewolf101.aztech.event;

import litewolf101.aztech.utils.Reference;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.time.LocalDate;

/**
 * Created by LiteWolf101 on 11/4/2018.
 */
@Mod.EventBusSubscriber(modid = Reference.MODID, value = Side.CLIENT)
public class ClientLoginEvent {

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent evt) {
		EntityPlayer p = evt.player;
		if(!p.world.isRemote) {
			String current = LocalDate.now().toString().substring(5);
			//I love holidays :)
			String wuffysBirthday = "01-07";
			String aztechsBirthday = "10-01";
			String halloween = "10-31";
			String christmas = "12-25";
			String newYear = "01-01";

			if(current.equals(wuffysBirthday)) {
				ItemStack firework = new ItemStack(Items.FIREWORKS);
				firework.setTagCompound(new NBTTagCompound());
				NBTTagCompound expl = new NBTTagCompound();
				expl.setBoolean("Flicker", true);
				expl.setBoolean("Trail", true);

				int[] colors = new int[7];
				colors[0] = 25565;
				colors[1] = 245;
				colors[2] = 700;
				expl.setIntArray("Colors", colors);
				expl.setByte("Type", (byte)1);

				NBTTagList explosions = new NBTTagList();
				explosions.appendTag(expl);

				NBTTagCompound fireworkTag = new NBTTagCompound();
				fireworkTag.setTag("Explosions", explosions);
				fireworkTag.setByte("Flight", (byte)1);
				firework.setTagInfo("Fireworks", fireworkTag);

				double randx = p.world.rand.nextDouble() * 4;
				double randz = p.world.rand.nextDouble() * 4;
				EntityFireworkRocket rocket = new EntityFireworkRocket(p.world, p.posX + randx, p.posY, p.posZ + randz, firework);
				p.world.spawnEntity(rocket);
				p.sendMessage(new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.BLUE + "Happy Birthday Wufflez!"));
			}
			if(current.equals(aztechsBirthday)) {
				ItemStack firework = new ItemStack(Items.FIREWORKS);
				firework.setTagCompound(new NBTTagCompound());
				NBTTagCompound expl = new NBTTagCompound();
				expl.setBoolean("Flicker", true);
				expl.setBoolean("Trail", true);

				int[] colors = new int[7];
				colors[0] = 16763909;
				colors[1] = 16776709;
				colors[2] = 16755461;
				expl.setIntArray("Colors", colors);
				expl.setByte("Type", (byte)1);

				NBTTagList explosions = new NBTTagList();
				explosions.appendTag(expl);

				NBTTagCompound fireworkTag = new NBTTagCompound();
				fireworkTag.setTag("Explosions", explosions);
				fireworkTag.setByte("Flight", (byte)1);
				firework.setTagInfo("Fireworks", fireworkTag);

				double randx = p.world.rand.nextDouble() * 4;
				double randz = p.world.rand.nextDouble() * 4;
				EntityFireworkRocket rocket = new EntityFireworkRocket(p.world, p.posX + randx, p.posY, p.posZ + randz, firework);
				p.world.spawnEntity(rocket);
				p.sendMessage(new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.GOLD + "Happy Birthday to Project AzTech!"));
			}
			if(current.equals(halloween)) {
				p.world.playSound(null, p.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.MASTER, 2f, 1f);
				p.sendMessage(new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.GOLD + "Happy Halloween!"));
			}
			if(current.equals(christmas)) {
				p.world.playSound(null, p.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 2f, 1f);
				p.sendMessage(new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.RED + "Merry " + TextFormatting.BOLD + "" + TextFormatting.GREEN + "Christmas!"));
			}
			if(current.equals(newYear)) {
				ItemStack firework = new ItemStack(Items.FIREWORKS);
				firework.setTagCompound(new NBTTagCompound());
				NBTTagCompound expl = new NBTTagCompound();
				expl.setBoolean("Flicker", true);
				expl.setBoolean("Trail", true);

				int[] colors = new int[7];
				colors[0] = 364287;
				colors[1] = 16777215;
				expl.setIntArray("Colors", colors);
				expl.setByte("Type", (byte)1);

				NBTTagList explosions = new NBTTagList();
				explosions.appendTag(expl);

				NBTTagCompound fireworkTag = new NBTTagCompound();
				fireworkTag.setTag("Explosions", explosions);
				fireworkTag.setByte("Flight", (byte)1);
				firework.setTagInfo("Fireworks", fireworkTag);

				double randx = p.world.rand.nextDouble() * 4;
				double randz = p.world.rand.nextDouble() * 4;
				EntityFireworkRocket rocket = new EntityFireworkRocket(p.world, p.posX + randx, p.posY, p.posZ + randz, firework);
				p.world.spawnEntity(rocket);
				p.sendMessage(new TextComponentString(TextFormatting.BOLD + "Happy New Year!"));
			}
		}
	}

}
