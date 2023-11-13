package jiraiyah.allthatmatters.mixin;

import jiraiyah.allthatmatters.item.custom.HammerItem;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerGameModeMixin
{
    @Shadow @Final protected ServerPlayerEntity player;

    @Shadow protected ServerWorld world;

    @Inject(method = "tryBreakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;isCreative()Z", ordinal = 0, shift = At.Shift.BEFORE))
    public void onCreativeRejectOfMineFollowThrough(BlockPos blockPos, CallbackInfoReturnable<Boolean> cir)
    {
        ItemStack itemStack = this.player.getActiveItem();
        if (itemStack.getItem() instanceof HammerItem)
        {
            BlockState blockState = this.world.getBlockState(blockPos);
            itemStack.postMine(this.world, blockState, blockPos, this.player);
        }
    }
}