package jiraiyah.allthatmatters.mixin;

import jiraiyah.allthatmatters.utils.data.PersistentChunks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin
{

    public MinecraftServerMixin()
    {
    }

    @Inject(at = @At("HEAD"), method = "shutdown()V")
    private void shutdown(CallbackInfo info)
    {
        PersistentChunks.save();
    }

    @Inject(at = @At("HEAD"), method = "prepareStartRegion(Lnet/minecraft/server/WorldGenerationProgressListener;)V")
    private void prepareStartRegion(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo info)
    {
        PersistentChunks.initialize(((MinecraftServer) (Object) this));
    }
}