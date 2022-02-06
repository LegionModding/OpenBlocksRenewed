package com.legionmodding.openblocksrenewed.util;

import com.legionmodding.openblocksrenewed.handler.registry.BlockRegistry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Reference
{
    public static final String MOD_ID = "openblocksrenewed";

    public static final ItemGroup CREATIVE_TAB_BLOCKS = new ItemGroup("tab.blocks")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(BlockRegistry.WHITE_ELEVATOR.get());
        }
    };

    public static final String VERSION = "0.0.7";
}
