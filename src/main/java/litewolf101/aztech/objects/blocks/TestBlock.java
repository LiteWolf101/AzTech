package litewolf101.aztech.objects.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.MiscHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

import static litewolf101.aztech.objects.blocks.R2RTranslator.ACTIVATED;

public class TestBlock extends Block implements IHasModel, IMetaName {

    private final Set<BlockPos> blocksNeedingUpdate = Sets.newHashSet();

    public TestBlock(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setDefaultState(blockState.getBaseState().withProperty(ACTIVATED, false));
        setCreativeTab(AzTech.CREATIVE_TAB);
        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
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
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            updateSurroundingRunes(worldIn, pos, state);
            //this.updateSurroundingRunes(worldIn, pos, state);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            updateSurroundingRunes(worldIn, pos, state);
            //this.updateSurroundingRunes(worldIn, pos, state);
        }
    }

    private IBlockState updateSurroundingRunes(World worldIn, BlockPos pos, IBlockState state) {
        updateRunes(worldIn, pos, state);
        List<BlockPos> list = Lists.newArrayList(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();

        for (BlockPos blockpos : list) {
            if (worldIn.getBlockState(blockpos) != state) {
                worldIn.setBlockState(blockpos, state);
            }
        }

        return state;
    }

    public void updateRunes(World world, BlockPos pos, IBlockState state) {
        BlockPos updatePos = pos;
        EnumFacing[] offset = EnumFacing.values();
        System.out.println("---");
        for (EnumFacing facing : offset) {
            if (MiscHandler.SOURCES.contains(world.getBlockState(pos.offset(facing)))) {
                this.blocksNeedingUpdate.add(pos);
                System.out.println("Power source at [" + facing + "]");
            }
            if (world.getBlockState(updatePos.offset(facing)).getBlock() == BlocksInit.TEST_BLOCK && !this.blocksNeedingUpdate.contains(updatePos.offset(facing))) {
                System.out.println("Test block at [" + facing + "]");
                this.blocksNeedingUpdate.add(updatePos.offset(facing));
                updatePos = updatePos.offset(facing);
            }
        }
        System.out.println("Blocks that need power:");
        System.out.println(this.blocksNeedingUpdate);
        System.out.println("---");
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        if (!worldIn.isRemote) {
            updateSurroundingRunes(worldIn, pos, state);
            //this.updateSurroundingRunes(worldIn, pos, state);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ACTIVATED);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        String name = null;
        switch (stack.getItemDamage()) {
            case 1:
                name = "on";

                break;
            default:
                name = "off";
        }
        return name;
    }

    enum EnumAttachPosition implements IStringSerializable {
        DIRECTION("direction"),
        NONE("none");

        private final String name;

        EnumAttachPosition(String name) {
            this.name = name;
        }

        public String toString() {
            return this.getName();
        }

        public String getName() {
            return this.name;
        }
    }

}
