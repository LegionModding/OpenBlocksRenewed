package com.legionmodding.openblocksrenewed.handler;

import com.legionmodding.openblocksrenewed.block.BlockElevator;
import com.legionmodding.openblocksrenewed.util.RequestUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TeleportHandler
{
    public static boolean handle(RequestUtil request, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ServerPlayerEntity player = ctx.get().getSender();

            if (player == null || !player.isAlive())
            {
                return;
            }

            ServerWorld world = player.getLevel();
            BlockPos from = request.getFrom(), to = request.getTo();

            final double distanceSq = player.distanceToSqr(new Vector3d(from.getX(), from.getY(), from.getZ()).add(0, 1, 0));

            if (distanceSq > 4D)
            {
                return;
            }

            if (from.getX() != to.getX() || from.getZ() != to.getZ())
            {
                return;
            }

            BlockState fromState = world.getBlockState(from);
            BlockState toState = world.getBlockState(to);

            if (!isElevator(fromState) || !isElevator(toState))
            {
                return;
            }

            if (!validateTarget(world, to))
            {
                return;
            }

            final float yaw, pitch;
            yaw = player.yRot;

            pitch = (toState.getValue(BlockElevator.DIRECTIONAL)) || (!toState.getValue(BlockElevator.DIRECTIONAL)) ? 0F : player.xRot;

            final double toX, toZ;

            toX = player.position().x;
            toZ = player.position().z;

            double blockYOffset = toState.getCollisionShape(world, to).max(Direction.Axis.Y);
            player.teleportTo(world, toX, to.getY() + (blockYOffset == Double.NEGATIVE_INFINITY ? 1 : blockYOffset), toZ, yaw, pitch);
            player.setDeltaMovement(player.getDeltaMovement().multiply(new Vector3d(1D, 0D, 1D)));
        });

        return true;
    }

    public static boolean validateTarget(IBlockReader world, BlockPos target)
    {
        return validateTarget(world.getBlockState(target.above(1))) && validateTarget(world.getBlockState(target.above(2)));
    }

    private static boolean validateTarget(BlockState blockState)
    {
        return !blockState.getMaterial().isSolid();
    }

    public static boolean isElevator(BlockState state)
    {
        if(state.getBlock() instanceof BlockElevator)
        {
            return true;
        }

        else
        {
            return false;
        }
    }
}
