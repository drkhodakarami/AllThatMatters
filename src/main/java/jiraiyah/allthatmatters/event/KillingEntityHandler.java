package jiraiyah.allthatmatters.event;

import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;

public class KillingEntityHandler implements ServerEntityCombatEvents.AfterKilledOtherEntity
{
    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity)
    {
        if(entity == null)
            return;
        if(!world.isClient)
        {
            if (killedEntity instanceof EndermanEntity &&
                    entity.isPlayer() &&
                    (((PlayerEntity) entity)
                            .getStackInHand(Hand.MAIN_HAND).isOf(ModItems.TOOL_ENDERITE_SWORD) ||
                    ((PlayerEntity) entity)
                            .getStackInHand(Hand.MAIN_HAND).isOf(ModItems.TOOL_ENDERITE_BOW)))
            {

                world.spawnEntity(new ItemEntity(world,
                        killedEntity.getPos().x,
                        killedEntity.getPos().y,
                        killedEntity.getPos().z,
                        new ItemStack(Items.ENDER_PEARL, Random.create().nextBetween(2, 4))));
                return;
            }
            if (killedEntity instanceof WitherSkeletonEntity &&
                    entity.isPlayer() &&
                    ((PlayerEntity) entity)
                            .getStackInHand(Hand.MAIN_HAND).isOf(ModItems.TOOL_CITRINE_SWORD))
            {

                world.spawnEntity(new ItemEntity(world,
                        killedEntity.getPos().x,
                        killedEntity.getPos().y,
                        killedEntity.getPos().z,
                        new ItemStack(Items.WITHER_SKELETON_SKULL, 1)));
                return;
            }
        }
    }
}