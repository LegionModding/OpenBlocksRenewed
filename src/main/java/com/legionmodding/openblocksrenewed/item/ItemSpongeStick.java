package com.legionmodding.openblocksrenewed.item;

import com.legionmodding.openblocksrenewed.util.BlockNotifyUtil;
import com.legionmodding.openblocksrenewed.util.Reference;

import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemSpongeStick extends Item
{
    private static boolean spongeStickBlockUpdate = true;

    public ItemSpongeStick()
    {
        super(new Item.Properties().stacksTo(1).tab(Reference.CREATIVE_TAB_ITEMS));
    }

    private static int getCleanupFlags()
    {
        return spongeStickBlockUpdate ? BlockNotifyUtil.ALL : BlockNotifyUtil.SEND_TO_CLIENTS;
    }

    @Override
    public int getEnchantmentValue()
    {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        final ItemStack stack = player.getItemInHand(hand);
        boolean result = soakUp(world, new BlockPos(player.position().x, player.position().y, player.position().z), player, stack);
        return ActionResult.pass(stack);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context)
    {
        final ItemStack stack = context.getPlayer().getItemInHand(context.getHand());
        return soakUp(context.getLevel(), context.getClickedPos(), context.getPlayer(), stack) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
    }

    private static boolean soakUp(World world, BlockPos pos, PlayerEntity player, @Nonnull ItemStack stack)
    {
        boolean absorbedAnything = false;
        boolean hitLava = false;
        int damage = stack.getMaxDamage();

        final int cleanupFlags = getCleanupFlags();

        for (int x = -10; x <= 10; x++)
        {
            for (int y = -10; y <= 10; y++)
            {
                for (int z = -10; z <= 10; z++)
                {
                    final BlockPos targetPos = pos.offset(x, y, z);

                    Material material = world.getBlockState(targetPos).getMaterial();

                    if (material.isLiquid())
                    {
                        absorbedAnything = true;
                        hitLava |= material == Material.LAVA;
                        world.setBlock(targetPos, Blocks.AIR.defaultBlockState(), cleanupFlags);
                    }
                }
            }
        }

        if (hitLava)
        {
            stack.setCount(0);
            player.setSecondsOnFire(6);
        }

        if (absorbedAnything)
        {
            stack.setDamageValue(1);

            return true;
        }

        return false;
    }
}
