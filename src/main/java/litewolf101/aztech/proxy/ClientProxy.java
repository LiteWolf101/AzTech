package litewolf101.aztech.proxy;

import litewolf101.aztech.init.ModEntities;
import litewolf101.aztech.objects.blocks.render.RenderMirror;
import litewolf101.aztech.objects.blocks.render.RenderPortalConstruct;
import litewolf101.aztech.objects.blocks.render.RenderTempleRuneCore;
import litewolf101.aztech.objects.blocks.render.RenderTileGeoObelisk;
import litewolf101.aztech.tileentity.TEGeoObelisk;
import litewolf101.aztech.tileentity.TETempleMirror;
import litewolf101.aztech.tileentity.TETempleRuneBlock;
import litewolf101.aztech.tileentity.masterPortalConstruct;
import litewolf101.aztech.utils.Reference;
import litewolf101.aztech.utils.client.particle.*;
import litewolf101.aztech.utils.handlers.AzTechSoundHandler;
import litewolf101.aztech.world.worldgen.structures.WorldGenCustomStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TETempleMirror.class, new RenderMirror());
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
                    case EYE_GUARDIAN:
                        particle = new ParticleEyeGuardian(world, x, y, z, velX, velY, velZ);
                        break;
                    case ENEMY_LINK:
                        particle = new ParticleEnemyLink(world, x, y, z, velX, velY, velZ);
                        break;
                    case PYRONANT:
                        particle = new ParticlePyronant(world, x, y, z, (float)velX, (float)velY, (float)velZ);
                        break;
                    case RED_SPARKLE:
                        particle = new ParticleRedSparkle(world, x, y, z, velX, velY, velZ);
                        break;
                    case YELLOW_SPARKLE:
                        particle = new ParticleYellowSparkle(world, x, y, z, velX, velY, velZ);
                        break;
                    case GREEN_SPARKLE:
                        particle = new ParticleGreenSparkle(world, x, y, z, velX, velY, velZ);
                        break;
                    case BLUE_SPARKLE:
                        particle = new ParticleBlueSparkle(world, x, y, z, velX, velY, velZ);
                        break;
                    case WHITE_SPARKLE:
                        particle = new ParticleWhiteSparkle(world, x, y, z, velX, velY, velZ);
                        break;
                    case BLACK_SPARKLE:
                        particle = new ParticleBlackSparkle(world, x, y, z, velX, velY, velZ);
                        break;
                }

                if (particle != null) {
                    mc.effectRenderer.addEffect(particle);
                }
            }
        }
    }
}

