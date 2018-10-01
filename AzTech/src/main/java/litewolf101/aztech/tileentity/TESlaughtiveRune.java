package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;

import static litewolf101.aztech.objects.blocks.BlockSlaughtiveRune.STAGE;

/**
 * Created by LiteWolf101 on 9/30/2018.
 */
public class TESlaughtiveRune extends TileEntity implements ITickable{
    private final List<EntitySlime> dyingSlime = new ArrayList<EntitySlime>();
    @Override
    public void update() {
        AxisAlignedBB detectbb = new AxisAlignedBB(pos, pos.add(1, 1, 1)).grow(5D, 1D, 5D);
        List<EntitySlime> nearbySlimes = world.getEntitiesWithinAABB(EntitySlime.class, detectbb);

        for (EntitySlime slime : nearbySlimes){
            if(slime.deathTime > 0){
                if (!dyingSlime.contains(slime)) {
                    dyingSlime.add(slime);
                }
            }
        }
        int power = Math.min(6, dyingSlime.size());
        //System.out.println(dyingSlime);
        world.setBlockState(pos, BlocksInit.SLAUGHTIVE_RUNE.getDefaultState().withProperty(STAGE, EnumStage.EnumType.byMetadata(power)));
    }
}