package litewolf101.aztech.objects.items;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.handlers.EnumHalf;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static litewolf101.aztech.objects.blocks.GeoluminescentObelisk.HALF;

/**
 * Created by LiteWolf101 on 10/18/2018.
 */
public class GeoluminescentObeliskItem extends Item implements IHasModel{

    private final Block block;

    public GeoluminescentObeliskItem(String name, Block block) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AzTech.CREATIVE_TAB);
        this.block = BlocksInit.GEOLUMINESCENT_OBELISK;

        ItemsInit.ITEMS.add(this);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing != EnumFacing.UP)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (!block.isReplaceable(worldIn, pos))
            {
                pos = pos.offset(facing);
            }

            ItemStack itemstack = player.getHeldItem(hand);

            if (player.canPlayerEdit(pos, facing, itemstack) && BlocksInit.GEOLUMINESCENT_OBELISK.canPlaceBlockAt(worldIn, pos))
            {
                placeBlock(worldIn, pos);
                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
    }

    public static void placeBlock(World worldIn, BlockPos pos) {
        Block blockUnder = worldIn.getBlockState(pos.down()).getBlock();
        Boolean canBePlaced = blockUnder.isNormalCube(worldIn.getBlockState(pos));
        Boolean isAirAbove = worldIn.isAirBlock(pos.up());
        //if (isAirAbove){
        //    if (canBePlaced){
        //        worldIn.setBlockState(pos, BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        //        worldIn.setBlockState(pos.up(), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        //    }
        //}
        worldIn.setBlockState(pos, BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        worldIn.setBlockState(pos.up(), BlocksInit.GEOLUMINESCENT_OBELISK.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
