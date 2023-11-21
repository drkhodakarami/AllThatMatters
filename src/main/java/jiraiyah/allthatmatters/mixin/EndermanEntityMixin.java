package jiraiyah.allthatmatters.mixin;

import jiraiyah.allthatmatters.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin extends Entity
{

    public EndermanEntityMixin(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), cancellable = true, method = "isPlayerStaring")
    private void dropItem(PlayerEntity player, CallbackInfoReturnable<Boolean> info)
    {
        if (player.hasStatusEffect(StatusEffects.INVISIBILITY))
            info.setReturnValue(false);
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void enderiteBowHarmEnderman(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if(source.getAttacker() == null)
            return;
        if(source.getAttacker().isPlayer() && ((PlayerEntity)source.getAttacker()).getStackInHand(Hand.MAIN_HAND).isOf(ModItems.TOOL_ENDERITE_BOW))
        {
            if(!source.getAttacker().getWorld().isClient)
            {
                cir.setReturnValue(super.damage(source,amount));
            }
        }
    }
}