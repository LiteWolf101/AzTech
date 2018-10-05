package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by LiteWolf101 on 9/27/2018.
 */
public class EnemyEmitterRune extends Block implements IHasModel{
    public EnemyEmitterRune(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 1);
        setHardness(2f);
        setTickRandomly(true);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        AzTech.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public int tickRate(World worldIn) {
        return 200;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.scheduleUpdate(pos, this, 200);
        spawnEntity(worldIn, pos);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {

        worldIn.scheduleUpdate(pos, this, 200);
        spawnEntity(worldIn, pos);
    }

    //TODO Replace with custom entity
    private void spawnEntity(World worldIn, BlockPos pos) {
        Random random = new Random();
        int randx = random.nextInt(8) - 4;
        int randz = random.nextInt(8) - 4;
        EntityZombie zombie = new EntityZombie(worldIn);
        zombie.setLocationAndAngles(pos.getX() + (double)randx, pos.getY() + 1, pos.getZ() + (double)randz, 0, 0);
        worldIn.spawnEntity(zombie);
        worldIn.playSound(null, zombie.posX, zombie.posY, zombie.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1f, 1f);
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.5;
        double z = pos.getZ() + 0.5;
        double random = rand.nextDouble()/4;
        double random1 = rand.nextDouble()/4;
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x  + random , y, z + random1 , 0, 0.1D, 0);
    }
}
