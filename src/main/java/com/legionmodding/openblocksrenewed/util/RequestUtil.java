package com.legionmodding.openblocksrenewed.util;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class RequestUtil
{
    private final BlockPos from, to;

    public RequestUtil(BlockPos from, BlockPos to)
    {
        this.from = from;
        this.to = to;
    }

    public BlockPos getFrom()
    {
        return from;
    }

    public BlockPos getTo()
    {
        return to;
    }

    public static RequestUtil decode(PacketBuffer buf)
    {
        return new RequestUtil(buf.readBlockPos(), buf.readBlockPos());
    }

    public static void encode(RequestUtil msg, PacketBuffer buf)
    {
        buf.writeBlockPos(msg.from);
        buf.writeBlockPos(msg.to);
    }
}
