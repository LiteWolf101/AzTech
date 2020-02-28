package com.litewolf101.aztech;

import com.litewolf101.aztech.blocks.tileEntity.TESlaughterhouseBlock;
import com.litewolf101.aztech.client.gui.AzotomeScreen;
import com.litewolf101.aztech.client.gui.info.AzotomeIcon;
import com.litewolf101.aztech.init.ModBlocks;
import com.litewolf101.aztech.init.ModItems;
import com.litewolf101.aztech.proxy.ClientProxy;
import com.litewolf101.aztech.proxy.IProxy;
import com.litewolf101.aztech.proxy.ServerProxy;
import com.litewolf101.aztech.utils.AzotomeRegistry;
import com.litewolf101.aztech.utils.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.ImmutableMap.Builder;

import java.util.Map;
import java.util.function.Supplier;


@Mod(com.litewolf101.aztech.AzTech.MODID)
public final class AzTech {
    public static final String MODID = "aztech";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static final ItemGroup AT_ITEMGROUP = new com.litewolf101.aztech.init.AzTechItemGroup();

    public AzTech() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postLoading);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        OBJLoader.INSTANCE.addDomain(MODID);
    }

    //Called post registration
    private void postLoading(final FMLLoadCompleteEvent event) {
        proxy.initBlockColors();
        LOGGER.info("Made things look pretty.");

        HoeItem.HOE_LOOKUP.put(ModBlocks.ANCIENT_GRASS, ModBlocks.ANCIENT_FARMLAND.getDefaultState());
        HoeItem.HOE_LOOKUP.put(ModBlocks.ANCIENT_DIRT, ModBlocks.ANCIENT_FARMLAND.getDefaultState());
        LOGGER.info("Added vanilla capabilities.");

        AzotomeRegistry.registerAzotomeEntries();
    }


}
