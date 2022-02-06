package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.block.BlockElevator;
import com.legionmodding.openblocksrenewed.colours.ColorMeta;
import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public static final RegistryObject<Block> BLACK_ELEVATOR = BLOCKS.register("black_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.BLACK));
    public static final RegistryObject<Block> BLUE_ELEVATOR = BLOCKS.register("blue_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.BLUE));
    public static final RegistryObject<Block> BROWN_ELEVATOR = BLOCKS.register("brown_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.BROWN));
    public static final RegistryObject<Block> CYAN_ELEVATOR = BLOCKS.register("cyan_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.CYAN));
    public static final RegistryObject<Block> GRAY_ELEVATOR = BLOCKS.register("gray_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.GRAY));
    public static final RegistryObject<Block> GREEN_ELEVATOR = BLOCKS.register("green_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.GREEN));
    public static final RegistryObject<Block> LIGHT_BLUE_ELEVATOR = BLOCKS.register("light_blue_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.LIGHT_BLUE));
    public static final RegistryObject<Block> LIGHT_GRAY_ELEVATOR = BLOCKS.register("light_gray_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.LIGHT_GRAY));
    public static final RegistryObject<Block> LIME_ELEVATOR = BLOCKS.register("lime_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.LIME));
    public static final RegistryObject<Block> MAGENTA_ELEVATOR = BLOCKS.register("magenta_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.MAGENTA));
    public static final RegistryObject<Block> ORANGE_ELEVATOR = BLOCKS.register("orange_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.ORANGE));
    public static final RegistryObject<Block> PINK_ELEVATOR = BLOCKS.register("pink_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.PINK));
    public static final RegistryObject<Block> PURPLE_ELEVATOR = BLOCKS.register("purple_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.PURPLE));
    public static final RegistryObject<Block> RED_ELEVATOR = BLOCKS.register("red_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.RED));
    public static final RegistryObject<Block> WHITE_ELEVATOR = BLOCKS.register("white_elevator", () -> BlockElevator.create(Material.STONE,ColorMeta.WHITE ));
    public static final RegistryObject<Block> YELLOW_ELEVATOR = BLOCKS.register("yellow_elevator", () -> BlockElevator.create(Material.STONE, ColorMeta.YELLOW));

    public static void registerBlocks()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
