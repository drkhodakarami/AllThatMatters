package jiraiyah.allthatmatters.mixin;

import net.minecraft.server.world.ChunkTicket;
import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.util.collection.SortedArraySet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkTicketManager.class)
public interface ChunkTicketManagerInvoker {
    @Invoker("getLevel")
    static int invokeGetLevel(SortedArraySet<ChunkTicket<?>> arg) {
        throw new AssertionError();
    }

    @Invoker("getTicketSet")
    SortedArraySet<ChunkTicket<?>> invokeGetTicketSet(long position);
}