package jiraiyah.allthatmatters.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemUseOnBlockMixin
        implements FabricItemStack
{
    /**
     * @author jiraiyah, special thanks to Linguardiuum for help and LlamaLad7 for his nice mixin extra library
     **/

    @Shadow public abstract int getMaxDamage();

    @Shadow public abstract int getDamage();

    @ModifyReturnValue(method = "getMiningSpeedMultiplier", at = @At("RETURN"))
    public float itemWithMendingSpeed(float original, BlockState state)
    {
        if(EnchantmentHelper.getLevel(Enchantments.MENDING, (ItemStack)(Object) this) == 0)
            return original;
        if(getMaxDamage() - getDamage() <= 1)
            return -1f;
        return original;
    }

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void itemShouldNotWorkOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir)
    {
        ItemStack stack = context.getStack();
        if(EnchantmentHelper.getLevel(Enchantments.MENDING, stack) != 0)
        {
            if (stack.isIn(ItemTags.AXES) || stack.isIn(ItemTags.SHOVELS) || stack.isIn(ItemTags.HOES))
            {
                if (stack.getMaxDamage() - stack.getDamage() <= 1)
                    cir.setReturnValue(ActionResult.FAIL);
            }
        }
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "isDamageable")
    public boolean itemWithMendingShouldNotDamage(boolean original)
    {
        if(original && EnchantmentHelper.getLevel(Enchantments.MENDING, (ItemStack)(Object) this) != 0)
            return getMaxDamage() != getDamage();
        return original;
    }
}