package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ItemRegistry
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    private static final RegistryObject<Item> BLACK_ELEVATOR = ITEMS.register("black_elevator", () -> new BlockItem(BlockRegistry.BLACK_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> BLUE_ELEVATOR = ITEMS.register("blue_elevator", () -> new BlockItem(BlockRegistry.BLUE_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> BOTTLER = ITEMS.register("bottler", () -> new BlockItem(BlockRegistry.BOTTLER.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> BROWN_ELEVATOR = ITEMS.register("brown_elevator", () -> new BlockItem(BlockRegistry.BROWN_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> CYAN_ELEVATOR = ITEMS.register("cyan_elevator", () -> new BlockItem(BlockRegistry.CYAN_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> DRAIN = ITEMS.register("drain", () -> new BlockItem(BlockRegistry.DRAIN.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> GRAY_ELEVATOR = ITEMS.register("gray_elevator", () -> new BlockItem(BlockRegistry.GRAY_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> GREEN_ELEVATOR = ITEMS.register("green_elevator", () -> new BlockItem(BlockRegistry.GREEN_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> HEALER = ITEMS.register("healer", () -> new BlockItem(BlockRegistry.HEALER.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> LIGHT_BLUE_ELEVATOR = ITEMS.register("light_blue_elevator", () -> new BlockItem(BlockRegistry.LIGHT_BLUE_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> LIGHT_GRAY_ELEVATOR = ITEMS.register("light_gray_elevator", () -> new BlockItem(BlockRegistry.LIGHT_GRAY_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> LIME_ELEVATOR = ITEMS.register("lime_elevator", () -> new BlockItem(BlockRegistry.LIME_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> MAGENTA_ELEVATOR = ITEMS.register("magenta_elevator", () -> new BlockItem(BlockRegistry.MAGENTA_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> ORANGE_ELEVATOR = ITEMS.register("orange_elevator", () -> new BlockItem(BlockRegistry.ORANGE_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> PINK_ELEVATOR = ITEMS.register("pink_elevator", () -> new BlockItem(BlockRegistry.PINK_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> PURPLE_ELEVATOR = ITEMS.register("purple_elevator", () -> new BlockItem(BlockRegistry.PURPLE_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> RED_ELEVATOR = ITEMS.register("red_elevator", () -> new BlockItem(BlockRegistry.RED_ELEVATOR.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    private static final RegistryObject<Item> WHITE_ELEVATOR = ITEMS.register("white_elevator", () -> new BlockItem(BlockRegistry.WHITE_ELEVATOR.get(),new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));
    public static final RegistryObject<Item> XP_BUCKET = ITEMS.register("xp_bucket", () -> new BucketItem(() -> FluidRegistry.XP_FLUID.get(), new Item.Properties().tab(Reference.CREATIVE_TAB_ITEMS).stacksTo(1)));
    private static final RegistryObject<Item> YELLOW_ELEVATOR = ITEMS.register("yellow_elevator", () -> new BlockItem(BlockRegistry.YELLOW_ELEVATOR.get(),new Item.Properties().tab(Reference.CREATIVE_TAB_BLOCKS)));

    public static void registerItems()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
