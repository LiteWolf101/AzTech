package litewolf101.aztech.objects.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class RuneLineDirectional extends RuneLine {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public RuneLineDirectional() {
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        boolean activated = meta > 3;
        EnumFacing direction = EnumFacing.NORTH;
        if (meta == 0 || meta == 4) {
            direction = EnumFacing.SOUTH;
        }
        if (meta == 1 || meta == 5) {
            direction = EnumFacing.WEST;
        }
        if (meta == 2 || meta == 6) {
            direction = EnumFacing.NORTH;
        }
        if (meta == 3 || meta == 7) {
            direction = EnumFacing.EAST;
        }
        return getDefaultState().withProperty(FACING, direction).withProperty(ACTIVATED, activated);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing direction = state.getValue(FACING);
        Boolean active = state.getValue(ACTIVATED);
        return direction.getHorizontalIndex() + (active ? 4 : 0);
    }

    @Override
    public int damageDropped(IBlockState state) {
        //replace with basic rune nine
        return 0;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(ACTIVATED, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, FACING, ACTIVATED);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

}
