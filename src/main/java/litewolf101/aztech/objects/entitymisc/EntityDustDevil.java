package litewolf101.aztech.objects.entitymisc;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDustDevil extends Entity {

    public EntityDustDevil(World worldIn) {
        super(worldIn);
        this.setEntityInvulnerable(true);
        this.setSize(2, 3);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
