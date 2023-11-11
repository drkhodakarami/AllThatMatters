package jiraiyah.allthatmatters.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.mojang.authlib.GameProfile;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.item.custom.GemBow;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity
{
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile)
    {
        super(world, pos, yaw, gameProfile);
    }

    @WrapOperation(
            method = "getFovMultiplier",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private boolean allowFowChangeForCustomBow(ItemStack instance, Item item, Operation<Boolean> original, @Share("isCustomBow") LocalBooleanRef isCustomBow)
    {
        var flag = instance.getItem() instanceof GemBow;
        isCustomBow.set(flag);
        return original.call(instance, item) || flag;
    }

    @ModifyExpressionValue(
            method = "getFovMultiplier",
            at = @At(value = "CONSTANT", args = "floatValue=20.0f")
    )
    private float customBowFovDividingFactor(float original, @Share("isCustomBow") LocalBooleanRef isCustomBow)
    {
        return isCustomBow.get() ? GemBow.ANIMATION_DURATION_DIVIDER : original;
    }

    /*@ModifyArg(method = "getFovMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F"), index = 2)
    public float onGetFovMultiplier(float f)
    {
        ItemStack itemStack = this.getActiveItem();
        if (this.isUsingItem())
        {
            if (itemStack.isOf(ModItems.TOOL_RUBY_BOW))
            {
                int i = this.getItemUseTime();
                float g = (float) i / GemBow.ANIMATION_DURATION_DIVIDER;
                if (g > 1.0F)
                    g = 1.0F;
                else
                    g *= g;

                f *= 1.0F - g * 0.15F;
            }
        }
        return f;
    }*/
}