package litewolf101.aztech.proxy;

import litewolf101.aztech.dimension.AztechDimension;
import litewolf101.aztech.init.BlocksInit;
import litewolf101.aztech.tileentity.*;
import litewolf101.aztech.utils.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        AztechDimension.init();
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
    }

    public void RegisterTileEntityRender(){}

    public void registerCustomStructures(){}

    @SuppressWarnings("deprecation")
    public void registerTileEntities(){
        GameRegistry.registerTileEntity(TETempleRuneBlock.class, Reference.MODID + ":te_temple_rune_block");
        GameRegistry.registerTileEntity(TESlaughtiveRune0.class, Reference.MODID + ":te_slaughtive_rune_0");
        GameRegistry.registerTileEntity(TESlaughtiveRune1.class, Reference.MODID + ":te_slaughtive_rune_1");
        GameRegistry.registerTileEntity(TESlaughtiveRune2.class, Reference.MODID + ":te_slaughtive_rune_2");
        GameRegistry.registerTileEntity(TESlaughtiveRune3.class, Reference.MODID + ":te_slaughtive_rune_3");
        GameRegistry.registerTileEntity(TESlaughtiveRune4.class, Reference.MODID + ":te_slaughtive_rune_4");
        GameRegistry.registerTileEntity(TESlaughtiveRune5.class, Reference.MODID + ":te_slaughtive_rune_5");
        GameRegistry.registerTileEntity(TESlaughtiveRune6.class, Reference.MODID + ":te_slaughtive_rune_6");
        GameRegistry.registerTileEntity(TEAncientLaser.class, Reference.MODID + ":te_ancient_laser");
        GameRegistry.registerTileEntity(TERuneLine.class, Reference.MODID + ":te_rune_line");
        GameRegistry.registerTileEntity(TEObjectorRune.class, Reference.MODID + ":te_objector_rune");
        GameRegistry.registerTileEntity(TEGeoObelisk.class, Reference.MODID + ":te_geo_obelisk");
        GameRegistry.registerTileEntity(TEPortalConstruct.class, Reference.MODID + ":te_portal_construct_basic");
        GameRegistry.registerTileEntity(masterPortalConstruct.class, Reference.MODID + ":te_master_portal_construct");
    }
}
