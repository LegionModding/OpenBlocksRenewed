package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidRegistry 
{
    public static final ResourceLocation XP_STILL_RL = new ResourceLocation(Reference.MOD_ID, "block/xp_still");
    public static final ResourceLocation XP_FLOWING_RL = new ResourceLocation(Reference.MOD_ID,
            "block/xp_flowing");
    public static final ResourceLocation XP_OVERLAY_RL = new ResourceLocation(Reference.MOD_ID,
            "block/xp_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,
            Reference.MOD_ID);

    public static final RegistryObject<FlowingFluid> XP_FLUID = FLUIDS.register("xp_fluid",
            () -> new ForgeFlowingFluid.Source(FluidRegistry.XP_PROPERTIES));

    public static final RegistryObject<FlowingFluid> XP_FLOWING = FLUIDS.register("xp_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidRegistry.XP_PROPERTIES));

    public static final ForgeFlowingFluid.Properties XP_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> XP_FLUID.get(), () -> XP_FLOWING.get(),
            FluidAttributes.builder(XP_STILL_RL, XP_FLOWING_RL).density(5).luminosity(10).rarity(Rarity.RARE)
                    .sound(SoundEvents.PLAYER_LEVELUP).overlay(XP_OVERLAY_RL))
            .block(() -> FluidRegistry.XP_BLOCK.get()).bucket(() -> ItemRegistry.XP_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> XP_BLOCK = BlockRegistry.BLOCKS.register("xp",
            () -> new FlowingFluidBlock(() -> FluidRegistry.XP_FLUID.get(), Block.Properties.of(Material.WATER)
                    .strength(100.0f, 100.0f).noDrops()));

    public static void registerFluids()
    {
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
