package com.legionmodding.openblocksrenewed.block;

import com.legionmodding.openblocksrenewed.colours.ColorMeta;
import com.legionmodding.openblocksrenewed.handler.registry.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;

import javax.annotation.Nullable;

public class BlockElevator extends Block
{
    public static final BooleanProperty DIRECTIONAL = BooleanProperty.create("directional");

    @Nullable
    public static Block colorToBlock(final ColorMeta color)
    {
        switch (color)
        {
            case BLACK:
                return BlockRegistry.BLACK_ELEVATOR.get();
            case BLUE:
                return BlockRegistry.BLUE_ELEVATOR.get();
            case BROWN:
                return BlockRegistry.BROWN_ELEVATOR.get();
            case CYAN:
                return BlockRegistry.CYAN_ELEVATOR.get();
            case GRAY:
                return BlockRegistry.GRAY_ELEVATOR.get();
            case GREEN:
                return BlockRegistry.GREEN_ELEVATOR.get();
            case LIGHT_BLUE:
                return BlockRegistry.LIGHT_BLUE_ELEVATOR.get();
            case LIGHT_GRAY:
                return BlockRegistry.LIGHT_GRAY_ELEVATOR.get();
            case LIME:
                return BlockRegistry.LIME_ELEVATOR.get();
            case MAGENTA:
                return BlockRegistry.MAGENTA_ELEVATOR.get();
            case ORANGE:
                return BlockRegistry.ORANGE_ELEVATOR.get();
            case PINK:
                return BlockRegistry.PINK_ELEVATOR.get();
            case PURPLE:
                return BlockRegistry.PURPLE_ELEVATOR.get();
            case RED:
                return BlockRegistry.RED_ELEVATOR.get();
            case WHITE:
                return BlockRegistry.WHITE_ELEVATOR.get();
            case YELLOW:
                return BlockRegistry.YELLOW_ELEVATOR.get();
            default:
                return null;
        }
    }

    private final ColorMeta color;

    public BlockElevator(Block.Properties properties, ColorMeta color)
    {
        super(properties);
        this.color = color;
    }

    public static BlockElevator create(final Material material, final ColorMeta color)
    {
        return new BlockElevator(Block.Properties.of(material, color.vanillaEnum), color);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(DIRECTIONAL);
    }
}
