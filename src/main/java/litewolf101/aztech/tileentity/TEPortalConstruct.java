package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.handlers.EnumPortalPart;
import litewolf101.aztech.utils.handlers.EnumRuneColor;
import litewolf101.aztech.utils.handlers.EnumTempleStoneType;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static litewolf101.aztech.objects.blocks.BlockTempleStone.STONE_TYPE;
import static litewolf101.aztech.objects.blocks.PortalMultiblock.PART;
import static litewolf101.aztech.objects.blocks.TempleRuneBlock.RUNE_COLOR;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class TEPortalConstruct extends TileEntity implements ITickable {
    int update = 0;
    @Override
    public void update() {
        update++;
        if(update % 20 == 0){
            isMultiblockValid(this.getWorld(), this.getPos());
        }

    }

    public boolean isMultiblockValid(World world, BlockPos pos){
        boolean valid = false;
        if (checkFloor(world, pos)){
            if (checkObelisks(world, pos)){
                if (world.getBlockState(pos.up(2)).getBlock() == BlocksInit.ANCIENT_CHISELED_BRICKS){
                    if (world.getBlockState(pos.up(3)) == BlocksInit.TEMPLE_RUNE_BLOCK.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.WHITE)){
                        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1f, 0.5f);
                        constructPortal(world, pos);
                        valid = true;
                    }
                }
            }
        }
        return valid;

    }

    private void constructPortal(World world, BlockPos pos) {
        //world.removeTileEntity(pos);
        //world.removeTileEntity(pos.up());
        world.setBlockState(pos, BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BOTTOM));
        world.setBlockState(pos.up(), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.MIDDLE));
        world.setBlockState(pos.up(2), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.TOP));
        world.setBlockState(pos.up(3), BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BRACE));
    }

    public boolean checkFloor(World world, BlockPos pos){
        IBlockState block = BlocksInit.TEMPLE_STONE.getDefaultState().withProperty(STONE_TYPE, EnumTempleStoneType.EnumType.NORMAL);
        boolean floor = false;
        if (world.getBlockState(pos.down()) == block){
            if (world.getBlockState(pos.add(-1, -1, -1)) == block){
                if (world.getBlockState(pos.add(0, -1, -1)) == block){
                    if (world.getBlockState(pos.add(1, -1, -1)) == block){
                        if (world.getBlockState(pos.add(-1, -1, 0)) == block){
                            if (world.getBlockState(pos.add(0, -1, 0)) == block){
                                if (world.getBlockState(pos.add(1, -1, 0)) == block){
                                    if (world.getBlockState(pos.add(-1, -1, 1)) == block){
                                        if (world.getBlockState(pos.add(0, -1, 1)) == block){
                                            if (world.getBlockState(pos.add(1, -1, 1)) == block){
                                                floor = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return floor;
    }

    public boolean checkObelisks(World world, BlockPos pos){
        boolean geo = false;
        Block block = BlocksInit.GEOLUMINESCENT_OBELISK;
        if (world.getBlockState(pos.add(3, 0, 3)).getBlock() == block){
            if (world.getBlockState(pos.add(-3, 0, -3)).getBlock() == block){
                if (world.getBlockState(pos.add(-3, 0, 3)).getBlock() == block){
                    if (world.getBlockState(pos.add(3, 0, -3)).getBlock() == block){
                        if (world.getBlockState(pos.add(3, 1, 3)).getBlock() == block){
                            if (world.getBlockState(pos.add(-3, 1, -3)).getBlock() == block){
                                if (world.getBlockState(pos.add(-3, 1, 3)).getBlock() == block){
                                    if (world.getBlockState(pos.add(3, 1, -3)).getBlock() == block){
                                        geo = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return geo;
    }
}
