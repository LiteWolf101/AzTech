package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.utils.CustomTeleporter;
import litewolf101.aztech.utils.handlers.EnumHalf;
import litewolf101.aztech.utils.handlers.EnumPortalPart;
import litewolf101.aztech.utils.handlers.EnumRuneColor;
import litewolf101.aztech.utils.handlers.EnumTempleStoneType;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static litewolf101.aztech.objects.blocks.AzTechPortal.HALF;
import static litewolf101.aztech.objects.blocks.BlockTempleStone.STONE_TYPE;
import static litewolf101.aztech.objects.blocks.PortalMultiblock.PART;
import static litewolf101.aztech.objects.blocks.TempleRuneBlock.RUNE_COLOR;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class masterPortalConstruct extends TileEntity implements ITickable{
    int update = 0;
    @Override
    public void update() {
        update++;
        if(update % 20 == 0){
            conditions(this.getWorld(), this.getPos());
            teleportEntity(this.getWorld(),this.getPos());
        }

    }

    private void conditions(World world, BlockPos pos){
        if (world.getBlockState(pos) == BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BOTTOM)){
            if(!checkFloor(world, pos)){
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1f, 0.5f);
                constructBasicPortal(this.getWorld(), this.getPos());
            }
            if(!checkObelisks(world, pos)){
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1f, 0.5f);
                constructBasicPortal(this.getWorld(), this.getPos());
            }
        }
    }

    private void constructBasicPortal(World world, BlockPos pos) {
        //world.removeTileEntity(pos);
        //world.removeTileEntity(pos.up());
        world.setBlockState(pos, BlocksInit.AZTECH_PORTAL.getDefaultState().withProperty(HALF, EnumHalf.EnumType.BOTTOM));
        world.setBlockState(pos.up(), BlocksInit.AZTECH_PORTAL.getDefaultState().withProperty(HALF, EnumHalf.EnumType.TOP));
        world.setBlockState(pos.up(2), BlocksInit.ANCIENT_CHISELED_BRICKS.getDefaultState());
        world.setBlockState(pos.up(3), BlocksInit.TEMPLE_RUNE_BLOCK.getDefaultState().withProperty(RUNE_COLOR, EnumRuneColor.EnumType.WHITE));
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

    public void teleportEntity(World world, BlockPos pos){
        int x = this.getPos().getX();
        int y = this.getPos().getY();
        int z = this.getPos().getZ();
        if(world.getBlockState(pos) == BlocksInit.PORTAL_CONSTRUCT.getDefaultState().withProperty(PART, EnumPortalPart.EnumType.BOTTOM)) {
            BlockPos portalpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            AxisAlignedBB bb = new AxisAlignedBB(portalpos.getX(), portalpos.getY(), portalpos.getZ(), portalpos.getX()+0.85, portalpos.getY()+2, portalpos.getZ()+0.85);
            List<EntityLivingBase> victims = world.getEntitiesWithinAABB(EntityLivingBase.class, bb);
            for (EntityLivingBase entities : victims) {
                if (entities instanceof EntityPlayerMP){
                    if (this.world.provider.getDimension() == 17 ){//TODO Adjust dimension number
                        CustomTeleporter.teleportToDimension((EntityPlayerMP) entities, 0, x, y, z + 2);
                    } else {
                        CustomTeleporter.teleportToDimension((EntityPlayerMP) entities, 17, x, y, z + 2);
                    }

                }

            }
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return INFINITE_EXTENT_AABB;
    }

}
