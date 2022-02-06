package com.legionmodding.openblocksrenewed.entity;

import com.legionmodding.openblocksrenewed.handler.registry.EntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class EntityXPOrbNoFly extends ExperienceOrbEntity
{
    public EntityXPOrbNoFly(EntityType<Entity> entityEntityType, World world)
    {
        super((EntityType<? extends ExperienceOrbEntity>) EntityRegistry.XP_ORB.get(), world);
        this.setDeltaMovement(0, -0.1f * world.random.nextFloat(), 0);
    }

    public EntityXPOrbNoFly(World world, double x, double y, double z, int xp)
    {
        super(world, x, y, z, xp);
        //todo setPosition(x, y, z);
        this.setDeltaMovement(0, -0.1f * world.random.nextFloat(), 0);
    }

    @Override
    public void tick()
    {
        final AxisAlignedBB aabb = getBoundingBox();

        final double vx = getMotionDirection().getStepX();
        final double vy = getMotionDirection().getStepY();
        final double vz = getMotionDirection().getStepZ();

        super.tick();

        if (isAlive())
        {
            return;
        }

        setBoundingBox(aabb);
        resetPos();

        this.setDeltaMovement(vx, vy, vz);

        //todo below
        /*this.position()..prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.029999999329447746D;*/

        final BlockPos pos = new BlockPos(this.position().x, this.position().y, this.position().z);

        if (this.level.getBlockState(pos).getMaterial() == Material.LAVA)
        {
            this.setDeltaMovement(0.20000000298023224D, this.random.nextFloat() - this.random.nextFloat() * 0.2F, this.random.nextFloat() - this.random.nextFloat() * 0.2F);
            playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
        }

        push(this.position().x, (getBoundingBox().minY + getBoundingBox().maxY) / 2.0D, this.position().z);

        move(MoverType.SELF, new Vector3d(this.getMotionDirection().getStepX(), this.getMotionDirection().getStepY(), this.getMotionDirection().getStepZ()));
        final float f;

        if (this.onGround)
        {
            BlockPos underPos = new BlockPos(MathHelper.floor(this.position().x), MathHelper.floor(getBoundingBox().minY) - 1, MathHelper.floor(this.position().z));
            BlockState underState = this.level.getBlockState(underPos);
            f = underState.getBlock().getSlipperiness(underState, this.level, underPos, this) * 0.98F;
        }

        else
        {
            f = 0.98F;
        }

        this.setDeltaMovement(f, 0.98, f);

        if (this.onGround)
        {
            this.setDeltaMovement(f, -0.8999999761581421D, f);
        }
    }
}
