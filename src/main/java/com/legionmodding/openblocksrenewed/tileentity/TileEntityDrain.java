package com.legionmodding.openblocksrenewed.tileentity;

import com.legionmodding.openblocksrenewed.entity.EntityXPOrbNoFly;
import com.legionmodding.openblocksrenewed.handler.registry.FluidRegistry;
import com.legionmodding.openblocksrenewed.handler.registry.TileEntityRegistry;
import com.legionmodding.openblocksrenewed.util.ExperienceUtil;
import com.legionmodding.yalm.util.BlockUtil;
import com.legionmodding.yalm.util.CompatibilityUtil;
import com.legionmodding.yalm.util.EnchantmentUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;

public class TileEntityDrain extends TileEntity implements ITickableTileEntity
{
    public TileEntityDrain()
    {
        super(TileEntityRegistry.DRAIN.get());
    }

    public void tick()
    {

        if (!level.isClientSide)
        {
            final List<EntityXPOrbNoFly> xpOrbsOnGrid = getXPOrbsOnGrid();
            final List<PlayerEntity> playersOnGrid = getPlayersOnGrid();

            if (!xpOrbsOnGrid.isEmpty() || !playersOnGrid.isEmpty())
            {
                final BlockPos down = getBlockPos().below();

                if (level.isLoaded(down))
                {
                    final TileEntity te = level.getBlockEntity(down);

                    if (te != null && !te.isRemoved())
                    {
                        final IFluidHandler maybeHandler = CompatibilityUtil.getFluidHandler(te, Direction.UP);

                        if (maybeHandler != null)
                        {
                            for (EntityXPOrbNoFly orb : xpOrbsOnGrid)
                            {
                                tryConsumeOrb(maybeHandler, orb);
                            }

                            for (PlayerEntity player : playersOnGrid)
                            {
                                tryDrainPlayer(maybeHandler, player);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void tryDrainPlayer(IFluidHandler tank, PlayerEntity player)
    {
        int playerXP = EnchantmentUtil.getPlayerXP(player);

        if (playerXP <= 0)
        {
            return;
        }

        int maxDrainedXp = Math.min(4, playerXP);

        int xpAmount = ExperienceUtil.xpConverter.experienceToFluid(maxDrainedXp);
        FluidStack xpStack = new FluidStack(FluidRegistry.XP_FLUID.get(), xpAmount);

        int maxAcceptedLiquid = tank.fill(xpStack, IFluidHandler.FluidAction.SIMULATE);

        // rounding down, so we only use as much as we can
        int acceptedXP = ExperienceUtil.xpConverter.fluidToExperience(maxAcceptedLiquid);
        int acceptedLiquid = ExperienceUtil.xpConverter.experienceToFluid(acceptedXP);

        xpStack.setAmount(acceptedLiquid);

        int finallyAcceptedLiquid = tank.fill(xpStack, IFluidHandler.FluidAction.EXECUTE);

        if (finallyAcceptedLiquid <= 0)
        {
            return;
        }

        if (level.getGameTime() % 4 == 0)
        {
            level.playSound(player, getBlockPos(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 0.1F, 0.5F * ((level.random.nextFloat() - level.random.nextFloat()) * 0.7F + 1.8F));
        }

        EnchantmentUtil.addPlayerXP(player, -acceptedXP);
    }

    protected void tryConsumeOrb(IFluidHandler tank, EntityXPOrbNoFly orb)
    {
        if (orb.isAlive())
        {
            int xpAmount = ExperienceUtil.xpConverter.experienceToFluid(orb.getValue());
            FluidStack xpStack = new FluidStack(FluidRegistry.XP_FLUID.get(), xpAmount);
            int filled = tank.fill(xpStack, IFluidHandler.FluidAction.SIMULATE);

            if (filled == xpStack.getAmount())
            {
                tank.fill(xpStack, IFluidHandler.FluidAction.EXECUTE);
                orb.remove();
            }
        }
    }

    protected List<PlayerEntity> getPlayersOnGrid()
    {
        return level.getEntitiesOfClass(PlayerEntity.class, BlockUtil.singleBlock(getBlockPos()));
    }

    protected List<EntityXPOrbNoFly> getXPOrbsOnGrid()
    {
        return level.getEntitiesOfClass(EntityXPOrbNoFly.class, BlockUtil.aabbOffset(getBlockPos(), 0, 0, 0, 1, 0.3, 1));
    }
}
