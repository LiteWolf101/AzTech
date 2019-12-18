package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.EnemyEmitterBaseLogic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TEEnemyEmitterRune extends TileEntityMobSpawner {
    private final EnemyEmitterBaseLogic spawnerLogic = new EnemyEmitterBaseLogic()
    {
        public void broadcastEvent(int id)
        {
            TEEnemyEmitterRune.this.world.addBlockEvent(TEEnemyEmitterRune.this.pos, BlocksInit.ENEMY_EMITTER_RUNE, id, 0);
        }
        public World getSpawnerWorld()
        {
            return TEEnemyEmitterRune.this.world;
        }
        public BlockPos getSpawnerPosition()
        {
            return TEEnemyEmitterRune.this.pos;
        }
        public void setNextSpawnData(WeightedSpawnerEntity p_184993_1_)
        {
            super.setNextSpawnData(p_184993_1_);

            if (this.getSpawnerWorld() != null)
            {
                IBlockState iblockstate = this.getSpawnerWorld().getBlockState(this.getSpawnerPosition());
                this.getSpawnerWorld().notifyBlockUpdate(TEEnemyEmitterRune.this.pos, iblockstate, iblockstate, 4);
            }
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.spawnerLogic.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        this.spawnerLogic.writeToNBT(compound);
        return compound;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update()
    {
        this.spawnerLogic.updateSpawner();
    }

}
