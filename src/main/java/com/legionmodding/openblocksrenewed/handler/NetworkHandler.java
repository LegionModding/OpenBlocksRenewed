package com.legionmodding.openblocksrenewed.handler;

import com.legionmodding.openblocksrenewed.util.Reference;
import com.legionmodding.openblocksrenewed.util.RequestUtil;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler
{
    public static final String PROTOCOL_VERSION = "1";

    public static SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Reference.MOD_ID, "openblocks_renewed"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void init()
    {
        int i = 0;
        INSTANCE.registerMessage(i++, RequestUtil.class, RequestUtil::encode, RequestUtil::decode, TeleportHandler::handle);
    }
}
