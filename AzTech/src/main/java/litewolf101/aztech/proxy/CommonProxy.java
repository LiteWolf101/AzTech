package litewolf101.aztech.proxy;

import litewolf101.aztech.tileentity.TESlaughtiveRune;
import litewolf101.aztech.tileentity.TETempleRuneBlock;
import litewolf101.aztech.utils.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
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

    public void registerTileEntities(){
        GameRegistry.registerTileEntity(TETempleRuneBlock.class, Reference.MODID + ":te_temple_rune_block");
        GameRegistry.registerTileEntity(TESlaughtiveRune.class, Reference.MODID + ":te_slaughtive_rune");
    }
}
