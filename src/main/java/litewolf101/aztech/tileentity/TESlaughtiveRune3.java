package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import static litewolf101.aztech.objects.blocks.BlockSlaughtiveRune.STAGE;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiteWolf101 on 9/30/2018.
 */
public class TESlaughtiveRune3 extends TileEntity implements ITickable {

	private final List<EntitySlime> dyingSlimes = new ArrayList<EntitySlime>();

	@Override
	public void update() {
		AxisAlignedBB aabb = new AxisAlignedBB(pos, pos.add(1, 1, 1)).grow(5D, 2D, 5D);
		List<EntitySlime> nearbySlimes = world.getEntitiesWithinAABB(EntitySlime.class, aabb);
		for(EntitySlime slime : nearbySlimes) {
			if(slime.deathTime > 0) {
				if(slime.deathTime == 1) {
					world.setBlockState(pos, BlocksInit.SLAUGHTIVE_RUNE.getDefaultState().withProperty(STAGE, EnumStage.EnumType.STAGE_4));
				}
			}
		}
	}

}