package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry
{
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

    public static final RegistryObject<SoundEvent> ELEVATOR_TELEPORT = SOUNDS.register("elevator_teleport", () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, "elevator_teleport")));

    public static void registerSounds()
    {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
