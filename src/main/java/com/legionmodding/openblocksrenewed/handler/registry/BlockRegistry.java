package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.block.BlockElevator;
import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

       public static final RegistryObject<Block> WHITE_ELEVATOR = BLOCKS.register("white_elevator", BlockElevator::new);

    public static void registerBlocks()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
