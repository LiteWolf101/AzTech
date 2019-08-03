package litewolf101.aztech.utils;

import litewolf101.aztech.config.AzTechConfig;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by LiteWolf101 on 10/23/2018.
 */
public interface IStructure {

	WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(AzTechConfig.dimension_ID);
	PlacementSettings settings = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.NONE);

}
