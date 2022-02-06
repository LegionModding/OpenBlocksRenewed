package com.legionmodding.openblocksrenewed.handler;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigHandler
{
    public static final String CATEGORY_FLUIDS = "fluids";
    public static final String CATEGORY_GUIDES = "guides";
    public static final String CATEGORY_ELEVATORS = "elevators";
    public static final String CATEGORY_TANKS = "tanks";
    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;

    public static ForgeConfigSpec.IntValue REDSTONE_SENSITIVITY;
    public static ForgeConfigSpec.ConfigValue RENDER_DISTANCE;
    public static ForgeConfigSpec.BooleanValue ADVANCED_RENDERER;
    public static ForgeConfigSpec.BooleanValue ELEVATOR_REQUIRES_XP;
    public static ForgeConfigSpec.BooleanValue TANKS_EMIT_LIGHT;
    public static ForgeConfigSpec.IntValue XP_TO_LIQUID_RATIO;

    static
    {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("Fluid Settings").push(CATEGORY_FLUIDS);
        SERVER_BUILDER.comment("Builders Guide Settings").push(CATEGORY_GUIDES);
        SERVER_BUILDER.comment("Elevator Settings").push(CATEGORY_ELEVATORS);
        SERVER_BUILDER.comment("Tank Settings").push(CATEGORY_TANKS);
        setup(SERVER_BUILDER);

        SERVER_CONFIG = SERVER_BUILDER.build();
    }


    public static void setup(ForgeConfigSpec.Builder SERVER_BUILDER)
    {
        XP_TO_LIQUID_RATIO = SERVER_BUILDER.comment("How many mb is needed to store a single XP point").defineInRange("xpRatio", 20, 0, Integer.MAX_VALUE);
        REDSTONE_SENSITIVITY = SERVER_BUILDER.comment("How the Builders Guide should react to redstone. 0 - not sensitive, 1 - powered == on, -1 - inverted").defineInRange("redstoneSensitivity", 1,-1, 1);
        RENDER_DISTANCE = SERVER_BUILDER.comment("Square of guide maximum render distance").define("renderDistance", 64*64);
        ADVANCED_RENDERER = SERVER_BUILDER.comment("Try to use advanced OpenGL for performance improvements o nthe Builders Guide").define("advancedRender", true);
        ELEVATOR_REQUIRES_XP = SERVER_BUILDER.comment("Should elevator teleporting require XP?").define("requiresXp", false);
        TANKS_EMIT_LIGHT = SERVER_BUILDER.comment("Should tanks emit light?").define("emitLight", true);

        SERVER_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event)
    {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading event)
    {

    }
}
