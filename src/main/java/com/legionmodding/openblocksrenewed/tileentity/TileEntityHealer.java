package com.legionmodding.openblocksrenewed.tileentity;

import com.legionmodding.openblocksrenewed.handler.registry.TileEntityRegistry;
import com.legionmodding.yalm.util.BlockUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public class TileEntityHealer extends TileEntity implements ITickableTileEntity
{
    public TileEntityHealer()
    {
        super(TileEntityRegistry.HEALER.get());
    }

    @Override
    public void tick()
    {

        if(level.isClientSide)
        {
            return;
        }

        long time = level.getGameTime();

        if(time % 20 == 0)
        {
            //todo config range for healer
            List<PlayerEntity> playersOnTop = level.getEntitiesOfClass(PlayerEntity.class, BlockUtil.expandAround(getBlockPos(), 1, 2, 1));

            for(PlayerEntity player : playersOnTop)
            {
                if(!player.isCreative() && player.isAlive())
                {
                    player.addEffect(new EffectInstance(Effects.REGENERATION, 100, 10));
                    player.addEffect(new EffectInstance(Effects.SATURATION, 100, 10));
                }
            }
        }
    }
}
