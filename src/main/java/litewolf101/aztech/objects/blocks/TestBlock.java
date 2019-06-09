package litewolf101.aztech.objects.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.IRunePowerSource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

import static litewolf101.aztech.objects.blocks.R2RTranslator.ACTIVATED;

public class TestBlock extends Block implements IHasModel, IMetaName {
    public TestBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setDefaultState(blockState.getBaseState().withProperty(ACTIVATED, false));
        setCreativeTab(AzTech.CREATIVE_TAB);
        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }
    private final Set<BlockPos> blocksNeedingUpdate = Sets.<BlockPos>newHashSet();

    /*private IBlockState updateSurroundingRunes(World worldIn, BlockPos pos, IBlockState state)
    {
        state = this.calculateCurrentChanges(worldIn, pos, pos, state);
        List<BlockPos> list = Lists.newArrayList(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();

        for (BlockPos blockpos : list)
        {
            //worldIn.notifyNeighborsOfStateChange(blockpos, this, false);
        }

        return state;
    }

    private IBlockState calculateCurrentChanges(World worldIn, BlockPos pos1, BlockPos pos2, IBlockState state)
    {
        //if (worldIn.getBlockState(pos1.north()) instanceof IRunePowerSource) {
        //    worldIn.setBlockState(pos1, state.withProperty(ACTIVATED, true));
        //    System.out.println("GAH");
        //}
        for (EnumFacing enumfacing1 : EnumFacing.values())
        {

            if (worldIn.getBlockState(pos1.offset(enumfacing1)) == state) {
                this.blocksNeedingUpdate.add(pos1.offset(enumfacing1));
                System.out.println(enumfacing1);
            }
        }

        return state;
    }*/

    public void updateRunes (World world, BlockPos pos, IBlockState state) {

    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            //this.updateSurroundingRunes(worldIn, pos, state);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            //this.updateSurroundingRunes(worldIn, pos, state);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            //this.updateSurroundingRunes(worldIn, pos, state);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        boolean activated = meta != 0;
        return this.getDefaultState().withProperty(ACTIVATED, activated);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return this.getDefaultState().getValue(ACTIVATED) ? 1 : 0;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {ACTIVATED});
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        String name = null;
        switch (stack.getItemDamage()){
            case 1:
                name = "on";

                break;
            default:
                name = "off";
        }
        return name;
    }
}
