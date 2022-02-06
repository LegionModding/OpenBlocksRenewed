package com.legionmodding.openblocksrenewed.handler;

import com.legionmodding.openblocksrenewed.block.BlockElevator;
import com.legionmodding.openblocksrenewed.handler.registry.SoundRegistry;
import com.legionmodding.openblocksrenewed.util.Reference;
import com.legionmodding.openblocksrenewed.util.RequestUtil;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MOD_ID)
public class ElevatorHandler
{
    private static boolean lastSneaking;
    private static boolean lastJumping;

    @SubscribeEvent
    public static void onInput(InputEvent event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;

        if (player == null || player.isSpectator() || !player.isAlive())
        {
            return;
        }

        boolean sneaking = player.input.shiftKeyDown;

        if (lastSneaking != sneaking)
        {
            lastSneaking = sneaking;

            if (sneaking)
            {
                tryTeleport(player, Direction.DOWN);
            }
        }

        boolean jumping = player.input.jumping;

        if (lastJumping != jumping)
        {
            lastJumping = jumping;

            if (jumping)
            {
                tryTeleport(player, Direction.UP);
            }
        }
    }

    private static void tryTeleport(ClientPlayerEntity player, Direction facing)
    {
        IBlockReader world = player.getCommandSenderWorld();

        BlockPos fromPos = getOriginElevator(player);

        if (fromPos == null)
        {
            return;
        }

        BlockPos.Mutable toPos = new BlockPos.Mutable(fromPos.getX(), fromPos.getY(), fromPos.getZ());
        BlockState toState;

        BlockElevator fromElevator;
        BlockElevator toElevator;

        fromElevator = (BlockElevator) world.getBlockState(fromPos).getBlock();

        while (true)
        {
            toPos.setY(toPos.getY() + facing.getStepY());

            if (Math.abs(toPos.getY() - fromPos.getY()) > 8)
            {
                break;
            }

            toState = world.getBlockState(toPos);

            if (TeleportHandler.isElevator(toState) && TeleportHandler.validateTarget(world, toPos))
            {
                if(ConfigHandler.ELEVATOR_REQUIRES_XP.get())
                {
                    //todo config option to choose how much xp is required
                    if(player.experienceLevel > 0)
                    {
                        player.giveExperienceLevels(-1);
                        toElevator = (BlockElevator) toState.getBlock();
                        player.getCommandSenderWorld().playSound(player, player.position().x, player.position().y, player.position().z(), SoundRegistry.ELEVATOR_TELEPORT.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                        NetworkHandler.INSTANCE.sendToServer(new RequestUtil(fromPos, toPos));
                    }

                    else
                    {
                        player.sendMessage(new TranslationTextComponent("openblocksrenewed.messages.experience"), null);
                    }
                }

                toElevator = (BlockElevator) toState.getBlock();
                player.getCommandSenderWorld().playSound(player, player.position().x, player.position().y, player.position().z, SoundRegistry.ELEVATOR_TELEPORT.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                NetworkHandler.INSTANCE.sendToServer(new RequestUtil(fromPos, toPos));
            }
        }
    }

    private static BlockPos getOriginElevator(ClientPlayerEntity player)
    {
        World world = player.getCommandSenderWorld();
        BlockPos pos = new BlockPos(player.position().x, player.position().y, player.position().z);

        for (int i = 0; i < 3; i++)
        {
            if (TeleportHandler.isElevator(world.getBlockState(pos)) && TeleportHandler.validateTarget(world, pos))
            {
                return pos;
            }

            pos = pos.below();
        }

        return null;
    }
}
