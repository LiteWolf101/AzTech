package litewolf101.aztech.utils.multiblock;

import litewolf101.aztech.init.BlocksInit;
import static litewolf101.aztech.objects.blocks.GeoluminescentObelisk.HALF;
import litewolf101.aztech.tileentity.TEGeoObelisk;
import litewolf101.aztech.utils.handlers.EnumHalf;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class GeoObeliskUtil {

	public static boolean isMultiblockCorrect(BlockPos pos, World world, boolean build) {
		if(world.getBlockState(pos) == BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM)) {
			if(world.getBlockState(pos.up()) == BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP)) {
				TileEntity tile = world.getTileEntity(pos);
				boolean master = world.getBlockState(pos) == BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM);

				((TEGeoObelisk)tile).setMasterCoords(pos.getX(), pos.getY(), pos.getZ());
				((TEGeoObelisk)tile).setHasMaster(true);
				((TEGeoObelisk)tile).setIsMaster(master);
				return true;
			}
		}
		return false;
	}

	private static void setupStructure(BlockPos pos, World world, boolean areCoordsCorrect) {
		TileEntity tile = world.getTileEntity(pos);
		boolean master = world.getBlockState(pos) == BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM);

		((TEGeoObelisk)tile).setMasterCoords(pos.getX(), pos.getY(), pos.getZ());
		((TEGeoObelisk)tile).setHasMaster(true);
		((TEGeoObelisk)tile).setIsMaster(master);

		world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	public static void resetStructure(BlockPos pos, World world) {
		TileEntity tile = world.getTileEntity(pos);
		if(tile != null && (tile instanceof TEGeoObelisk)) {
			((TEGeoObelisk)tile).reset();
			world.removeTileEntity(pos);
			world.removeTileEntity(pos.up());
			world.setBlockState(pos, BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
			world.setBlockState(pos.up(), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
		}
	}

	public static void removeStructure(BlockPos pos, World world) {
		TileEntity tile = world.getTileEntity(pos);
		if(tile != null && (tile instanceof TEGeoObelisk)) {
			((TEGeoObelisk)tile).reset();
			world.removeTileEntity(pos);
			world.removeTileEntity(pos.up());
			world.setBlockToAir(pos);
			world.setBlockToAir(pos.up());
		}
	}

}
