package jiraiyah.allthatmatters.mixin;

import jiraiyah.allthatmatters.utils.ModTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler
{
    @Shadow @Final private Property levelCost;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    public void allowOnlyMending(CallbackInfo ci)
    {
        if(this.input.getStack(0).isIn(ModTags.Items.NO_ENCHANT) &&
            !this.input.getStack(1).isEmpty() &&
            !EnchantmentHelper.get(this.input.getStack(1)).containsKey(Enchantments.MENDING))
        {
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            ci.cancel();
        }
    }
}