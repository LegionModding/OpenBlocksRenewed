package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.tileentity.*;
import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry
{
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);

    public static final RegistryObject<TileEntityType<TileEntityBottler>> BOTTLER = TILE_ENTITIES.register("bottler", () -> TileEntityType.Builder.of(TileEntityBottler::new, BlockRegistry.BOTTLER.get()).build(null));
    public static final RegistryObject<TileEntityType<TileEntityDrain>> DRAIN = TILE_ENTITIES.register("drain", () -> TileEntityType.Builder.of(TileEntityDrain::new, BlockRegistry.DRAIN.get()).build(null));

    public static void registerTileEntities()
    {
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
