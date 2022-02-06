package com.legionmodding.openblocksrenewed.tileentity;

import com.legionmodding.openblocksrenewed.handler.registry.TileEntityRegistry;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBottler extends TileEntity implements ITickableTileEntity
{
    public TileEntityBottler()
    {
        super(TileEntityRegistry.BOTTLER.get());
    }

    @Override
    public void tick()
    {

    }
}
