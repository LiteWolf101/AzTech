package litewolf101.aztech.utils.gui;

import litewolf101.aztech.tileentity.TileEntityInsertiveRune;
import litewolf101.aztech.utils.container.ContainerInsertiveRune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by LiteWolf101 on Feb /15/2019
 */
public class GUIHandler implements IGuiHandler {

	public static final int BLOCK_INSERTIVE_RUNE = 0;

	@Nullable
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == BLOCK_INSERTIVE_RUNE) {
			return new ContainerInsertiveRune(player.inventory, (TileEntityInsertiveRune)world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		return null;
	}

	@Nullable
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == BLOCK_INSERTIVE_RUNE) {
			return new GUIInsertiveRune(player.inventory, (TileEntityInsertiveRune)world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		return null;
	}

}
