package litewolf101.aztech.proxy;

import litewolf101.aztech.init.ModEntities;
import litewolf101.aztech.objects.blocks.render.RenderPortalConstruct;
import litewolf101.aztech.objects.blocks.render.RenderTempleRuneCore;
import litewolf101.aztech.objects.blocks.render.RenderTileGeoObelisk;
import litewolf101.aztech.tileentity.TEGeoObelisk;
import litewolf101.aztech.tileentity.TETempleRuneBlock;
import litewolf101.aztech.tileentity.masterPortalConstruct;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.utils.client.particle.AzTechParticleTypes;
import litewolf101.aztech.utils.client.particle.ParticleEyeMaster;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import litewolf101.aztech.world.worldgen.structures.WorldGenCustomStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by LiteWolf101 on 9/20/2018.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ModEntities.initModels();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        AzTechSoundHandler.init();
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

    @Override
    public void spawnParticle(World world, AzTechParticleTypes particletype, double x, double y, double z, double velX, double velY, double velZ) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();

        if (entity != null && mc.effectRenderer != null) {
            int i = mc.gameSettings.particleSetting;

            if (i == 1 && world.rand.nextInt(3) == 0) {
                i = 2;
            }

            double d0 = entity.posX - x;
            double d1 = entity.posY - y;
            double d2 = entity.posZ - z;

            if (d0 * d0 + d1 * d1 + d2 * d2 <= 1024D && i <= 1) {
                Particle particle = null;

                switch (particletype) {
                    case EYE_MASTER:
                        particle = new ParticleEyeMaster(world, x, y, z, velX, velY, velZ);
                        break;
                }

                if (particle != null) {
                    mc.effectRenderer.addEffect(particle);
                }
            }
        }
    }
}

