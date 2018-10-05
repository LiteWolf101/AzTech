package litewolf101.aztech.commands;

import litewolf101.aztech.utils.Reference;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiteWolf101 on 10/5/2018.
 */
public class CommandAzTech extends CommandBase {
    private final String name = "aztech";
    private final List<String> commands = new ArrayList<String>();

    public CommandAzTech() {
        commands.add("data");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "aztech <command>\n"
                + "data: Displays The mod name, current version of mod and current version of minecraft.\n";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (!(iCommandSender instanceof EntityPlayerMP)) {
            throw new WrongUsageException("message.command.onlyInGame");
        }
        EntityPlayerMP player = (EntityPlayerMP) iCommandSender;
        WorldServer world = player.getServerWorld();
        if (strings.length != 1) {
            throw new WrongUsageException(getUsage(iCommandSender));
        }
        if (strings[0].equals("data")) {
            iCommandSender.sendMessage(new TextComponentString(TextFormatting.GOLD + "\u2605" + "MOD: " + TextFormatting.RESET + Reference.NAME + "\n" + TextFormatting.GOLD + "\u2605" + "VERSION: " + TextFormatting.RESET + Reference.VERSION + "\n" + TextFormatting.GOLD + "\u2605" + "MINECRAFT VERSION: " + TextFormatting.RESET + Reference.ACCEPTED_MINECRAFT_VERSIONS));
        }

    }
}
