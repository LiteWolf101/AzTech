package litewolf101.aztech.objects.blocks;

import litewolf101.aztech.AzTech;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.init.ItemsInit;
import litewolf101.aztech.objects.blocks.item.ItemBlockVariants;
import litewolf101.aztech.utils.IHasModel;
import litewolf101.aztech.utils.IMetaName;
import litewolf101.aztech.utils.handlers.EnumRuneColor;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LiteWolf101 on 9/21/2018.
 */
public class BlockSlaughtiveRune extends Block implements IHasModel, IMetaName{
    public static final PropertyEnum<EnumStage.EnumType> STAGE = PropertyEnum.<EnumStage.EnumType>create("stage", EnumStage.EnumType.class);
    public BlockSlaughtiveRune(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setCreativeTab(AzTech.CREATIVE_TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(STAGE, EnumStage.EnumType.STAGE_0));
        setHarvestLevel("pickaxe", 1);
        setHardness(2f);
        setTickRandomly(true);

        BlocksInit.BLOCKS.add(this);
        ItemsInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int tickRate(World worldIn) {
        return 20;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        this.randomDisplayTick(state,worldIn, pos, rand);
        worldIn.scheduleUpdate(pos, this, 20);
        changeStateFromCustomCondition(worldIn, state, pos);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleUpdate(pos, this, 20);
        changeStateFromCustomCondition(worldIn, state, pos);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        //this is done on purpose because only the first stage should show in the creative tab yet all the meta still exists
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {

        return this.getDefaultState().withProperty(STAGE, EnumStage.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        return ((EnumStage.EnumType)state.getValue(STAGE)).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, new IProperty[] {STAGE});
    }
    private final List<EntitySlime> dedslime = new ArrayList<EntitySlime>();

    public void changeStateFromCustomCondition(World world, IBlockState state, BlockPos pos){
        int newState = this.turnStateToInt(world, pos);
        boolean hasBeenAttacked = false;
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();


        AxisAlignedBB detectbb = new AxisAlignedBB(x - 5, y, z - 5, x + 5, y + 2, z + 5);
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, detectbb);
        List<EntitySlime> aboutToBeDedStuff = world.getEntitiesWithinAABB(EntitySlime.class, detectbb);
        for (EntitySlime slime : aboutToBeDedStuff) {
            if (slime.deathTime > 0) {
                dedslime.add(slime);
            }
        }
        System.out.println(dedslime.size());

        int power = Math.min(6, dedslime.size());
        world.setBlockState(pos, state.withProperty(STAGE, EnumStage.EnumType.byMetadata(power)));
        for (EntityLivingBase entity: entities){
            if (entity instanceof EntityPlayer){
                if (power == 6) {
                    world.playSound(null, x, y, z, SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 0f, 0.5f);
                }
            }
        }
    }

    public IBlockState getStateFromInt(World world, BlockPos pos, int state){
        state = this.turnStateToInt(world, pos);
        return getStateFromMeta(state);
    }

    public int turnStateToInt(World world, BlockPos pos){
        int currentState = 0;
        IBlockState thisBlock = world.getBlockState(pos);
        if (thisBlock.getValue(STAGE) == EnumStage.EnumType.STAGE_1) {
            currentState = 1;
        } else if (thisBlock.getValue(STAGE) == EnumStage.EnumType.STAGE_2) {
            currentState = 2;
        } else if (thisBlock.getValue(STAGE) == EnumStage.EnumType.STAGE_3) {
            currentState = 3;
        } else if (thisBlock.getValue(STAGE) == EnumStage.EnumType.STAGE_4) {
            currentState = 4;
        } else if (thisBlock.getValue(STAGE) == EnumStage.EnumType.STAGE_5) {
            currentState = 5;
        } else if (thisBlock.getValue(STAGE) == EnumStage.EnumType.STAGE_6) {
            currentState = 6;
        } else return 0;
        return currentState;
    }
    //TODO make this a tile entity

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.5;
        double z = pos.getZ() + 0.5;
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x + 5, y, z + 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z + 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x - 5, y, z + 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x - 5, y, z, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x - 5, y, z - 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z - 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x + 5, y, z - 5, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x + 5, y, z, 0, 0, 0);
    }

    @Override
    public void registerModels() {
        for(int i = 0; i < EnumStage.EnumType.values().length; i++)
        {
            AzTech.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "slaughtive_rune_" + EnumStage.EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumStage.EnumType.values()[stack.getItemDamage()].getName();
    }
}
