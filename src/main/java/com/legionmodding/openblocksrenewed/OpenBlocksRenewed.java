package com.legionmodding.openblocksrenewed;

import com.legionmodding.openblocksrenewed.handler.NetworkHandler;
import com.legionmodding.openblocksrenewed.handler.registry.*;
import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class OpenBlocksRenewed
{
    public static final Logger LOGGER = LogManager.getLogger();

    public OpenBlocksRenewed()
    {
        BlockRegistry.registerBlocks();
        EntityRegistry.registerEntities();
        FluidRegistry.registerFluids();
        ItemRegistry.registerItems();
        SoundRegistry.registerSounds();
        TileEntityRegistry.registerTileEntities();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ColorRegistry::registerItemColorHandlers);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {

    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        NetworkHandler.init();
    }
}
