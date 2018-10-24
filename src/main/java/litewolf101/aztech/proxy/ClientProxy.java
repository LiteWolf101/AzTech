package litewolf101.aztech.proxy;

import litewolf101.aztech.objects.blocks.render.RenderPortalConstruct;
import litewolf101.aztech.objects.blocks.render.RenderTempleRuneCore;
import litewolf101.aztech.objects.blocks.render.RenderTileGeoObelisk;
import litewolf101.aztech.tileentity.TEGeoObelisk;
import litewolf101.aztech.tileentity.TETempleRuneBlock;
import litewolf101.aztech.tileentity.masterPortalConstruct;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.world.worldgen.structures.WorldGenCustomStructures;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
    }

    @Override
    public void registerCustomStructures() {
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
    }
    @Override
    public void RegisterTileEntityRender() {
        ClientRegistry.bindTileEntitySpecialRenderer(TETempleRuneBlock.class, new RenderTempleRuneCore());
        ClientRegistry.bindTileEntitySpecialRenderer(TEGeoObelisk.class, new RenderTileGeoObelisk());
        ClientRegistry.bindTileEntitySpecialRenderer(masterPortalConstruct.class, new RenderPortalConstruct());
    }
}
