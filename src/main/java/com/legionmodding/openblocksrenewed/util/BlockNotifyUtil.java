package com.legionmodding.openblocksrenewed.util;

public class BlockNotifyUtil
{
    public static final int NONE = 0;

    /**
     * Cause a block update
     */
    public static final int BLOCK_UPDATE = 1;

    /**
     * Send the change to clients
     */
    public static final int SEND_TO_CLIENTS = 2;

    public static final int ALL = BLOCK_UPDATE | SEND_TO_CLIENTS;

    /**
     * prevents the block from being re-rendered, if this is a client world
     */
    public static final int NO_RENDER = 4;
}
