package jiraiyah.allthatmatters.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import jiraiyah.allthatmatters.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(AbstractBlock.class)
public class AbstractBlockMixin
{
    /**
     * @author zOnlyKrocks, dicedpixels, and specially LlamaLad7 for his nice mixin extra library
     **/

    private final float effectiveHardness = 36.0F;

    @ModifyReturnValue(method = "calcBlockBreakingDelta", at = @At("RETURN"))
    public float enderitePickaxeBreakingSpeed(float original, BlockState state, PlayerEntity player)
    {
        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
        if ((state.getBlock() == Blocks.BEDROCK && (stack.getItem() == ModItems.TOOL_ENDERITE_PICKAXE)) ||
            (state.getBlock() == Blocks.END_PORTAL_FRAME  && (stack.getItem() == ModItems.TOOL_ENDERITE_PICKAXE)))
                return player.getBlockBreakingSpeed(state) / effectiveHardness;
        return original;
    }
}