package litewolf101.aztech.tileentity;

import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

/**
 * Created by LiteWolf101 on 9/30/2018.
 */
public class TESlaughtiveRune6 extends TileEntity implements ITickable {
    int timer;

    @Override
    public void update() {
        timer++;
        if (timer % 10 == 0){
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.BLOCKS, 0.5f, 2f);
        }
    }
}