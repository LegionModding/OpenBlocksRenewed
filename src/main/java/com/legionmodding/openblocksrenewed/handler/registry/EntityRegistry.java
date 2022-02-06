package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.entity.EntityXPOrbNoFly;
import com.legionmodding.openblocksrenewed.util.Reference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegistry
{
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);

    public static final RegistryObject<EntityType<?>> XP_ORB = ENTITIES.register("xp_orb", () ->
            EntityType.Builder.of(EntityXPOrbNoFly::new,
                    EntityClassification.AMBIENT)
                    .sized(0.25f, 0.25f)
                    .build(new ResourceLocation(Reference.MOD_ID, "xp_orb").toString()));

    public static void registerEntities()
    {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
