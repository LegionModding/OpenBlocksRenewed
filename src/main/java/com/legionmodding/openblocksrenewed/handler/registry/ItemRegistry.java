package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ItemRegistry
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    private static final RegistryObject<Item> WHITE_ELEVATOR = ITEMS.register("white_elevator", () -> new BlockItem(BlockRegistry.WHITE_ELEVATOR.get(),new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));

    public static void registerItems()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
