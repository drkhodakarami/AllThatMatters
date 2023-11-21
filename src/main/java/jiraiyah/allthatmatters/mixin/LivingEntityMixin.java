package jiraiyah.allthatmatters.mixin;

import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.utils.ModTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity
{
    public LivingEntityMixin(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;drop(Lnet/minecraft/entity/damage/DamageSource;)V"))
    public void shouldPutAnimalOnFire(DamageSource damageSource, CallbackInfo ci)
    {
        if(damageSource.getAttacker() == null)
            return;
        if (damageSource.getAttacker().isPlayer())
        {
            if (((PlayerEntity) damageSource.getAttacker()).getStackInHand(Hand.MAIN_HAND).isOf(ModItems.TOOL_RUBY_SWORD))
            {
                if (this.getType().isIn(ModTags.Entities.RUBY_SWORD_IGNITES))
                {
                    this.setOnFire(true);
                    this.setFireTicks(1);
                }
            }
        }
    }
}