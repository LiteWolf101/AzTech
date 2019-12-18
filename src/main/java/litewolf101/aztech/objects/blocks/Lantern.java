package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumDirectional;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by LiteWolf101 on 9/27/2018.
 */
public class Lantern extends Block implements IHasModel, IMetaName {

    public static final PropertyEnum<EnumDirectional.EnumFacing> FACING = PropertyEnum.create("facing", EnumDirectional.EnumFacing.class);

    public Lantern(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumDirectional.EnumFacing.UP));
        setLightLevel(1F);
        setHardness(0.2f);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {

        return this.getDefaultState().withProperty(FACING, EnumDirectional.EnumFacing.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        return state.getValue(FACING).getMeta();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = pos.getX();
        double d1 = pos.getY();
        double d2 = pos.getZ();
        Random randomx = new Random();
        Random randomy = new Random();
        Random randomz = new Random();
        double randx = randomx.nextDouble();
        double randy = randomy.nextDouble();
        double randz = randomz.nextDouble();
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + randx, d1, d2 + randz, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + randx, d1 + randy, d2 + randz, 0.0D, 0.0D, 0.0D);
        if (rand.nextInt(20) == 0) {
            worldIn.playSound(d0 + 0.5D, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
        }
    }

    @Override
    public int quantityDropped(Random rand) {
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(IBlockState p_damageDropped_1_) {
        return 0;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, translateUtilToDirection(pos, placer)));
    }

    public static EnumDirectional.EnumFacing translateUtilToDirection(BlockPos pos, EntityLivingBase entity) {
        EnumFacing enumFacing = getFacingFromEntity(pos, entity);
        if (enumFacing == EnumFacing.NORTH) {
            return EnumDirectional.EnumFacing.NORTH;
        } else if (enumFacing == EnumFacing.EAST) {
            return EnumDirectional.EnumFacing.EAST;
        } else if (enumFacing == EnumFacing.SOUTH) {
            return EnumDirectional.EnumFacing.SOUTH;
        } else if (enumFacing == EnumFacing.WEST) {
            return EnumDirectional.EnumFacing.WEST;
        } else if (enumFacing == EnumFacing.UP) {
            return EnumDirectional.EnumFacing.UP;
        }
        if (enumFacing == EnumFacing.DOWN) {
            return EnumDirectional.EnumFacing.DOWN;
        } else {
            return EnumDirectional.EnumFacing.UP;
        }
    }

    public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase entity) {
        if (MathHelper.abs((float) entity.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) entity.posZ - (float) pos.getZ()) < 2.0F) {
            double d0 = entity.posY + (double) entity.getEyeHeight();
            if (d0 - (double) pos.getY() > 2.0D) {
                return EnumFacing.UP;
            }

            if ((double) pos.getY() - d0 > 0.0D) {
                return EnumFacing.DOWN;
            }
        }
        return entity.getHorizontalFacing().getOpposite();
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, FACING);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumDirectional.EnumFacing.values()[stack.getItemDamage()].getName();
    }

}
