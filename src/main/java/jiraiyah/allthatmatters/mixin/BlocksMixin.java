package jiraiyah.allthatmatters.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Debug(export = true)
@Mixin(Blocks.class)
public abstract class BlocksMixin
{
    /**
     * @author LlamaLad7
     **/

    @Redirect(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=bedrock")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$Settings;dropsNothing()Lnet/minecraft/block/AbstractBlock$Settings;", ordinal = 0)
    )
    private static AbstractBlock.Settings bedrockDropsItself(AbstractBlock.Settings instance) {
        return instance;
    }

    @Redirect(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=end_portal_frame")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$Settings;dropsNothing()Lnet/minecraft/block/AbstractBlock$Settings;", ordinal = 0)
    )
    private static AbstractBlock.Settings endframeDropsItself(AbstractBlock.Settings instance) {
        return instance;
    }
}