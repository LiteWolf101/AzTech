package litewolf101.aztech.objects;

import litewolf101.aztech.objects.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class BlockMobSpawnable extends BlockBase {

    public BlockMobSpawnable(String name, Material material) {
        super(name, material);
        setBlockUnbreakable();
        setTickRandomly(true);
    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleUpdate(pos, this, 20);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.scheduleUpdate(pos, this, 20);
        spawnMob(worldIn, pos);
    }

    //Use this method to spawn in mobs
    public abstract void spawnMob(World world, BlockPos pos);
}
