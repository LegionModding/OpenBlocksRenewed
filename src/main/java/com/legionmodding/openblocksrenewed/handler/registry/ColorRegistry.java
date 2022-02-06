package com.legionmodding.openblocksrenewed.handler.registry;

import com.legionmodding.openblocksrenewed.block.BlockElevator;
import com.legionmodding.openblocksrenewed.colours.ColorMeta;
import com.legionmodding.openblocksrenewed.handler.BlockFixedColorHandler;
import com.legionmodding.openblocksrenewed.handler.ItemFixedColorHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;

import java.util.function.Function;

public class ColorRegistry
{
    public static void registerItemColorHandlers(ColorHandlerEvent.Item evt)
    {
        final ItemColors itemColors = evt.getItemColors();
        final BlockColors blockColors = evt.getBlockColors();

        registerFixedColorHandlers(BlockElevator::colorToBlock, itemColors, blockColors);
    }

    private static void registerFixedColorHandlers(final Function<ColorMeta, Block> blockSupplier, final ItemColors itemColors, final BlockColors blockColors)
    {
        ColorMeta.getAllColors().forEach(color ->
        {
            final Block block = blockSupplier.apply(color);

            if (block != null)
            {
                blockColors.register(new BlockFixedColorHandler(color), block);
                itemColors.register(new ItemFixedColorHandler(color), block);
            }
        });
    }
}
