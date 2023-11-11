package jiraiyah.allthatmatters.networking.packet;

import jiraiyah.allthatmatters.block.entity.GemCleanserBE;
import jiraiyah.allthatmatters.screen.handler.GemCleanserScreenHandler;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class GemCleanserFluidSyncS2CPacket
{
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender)
    {
        FluidVariant variant = FluidVariant.fromPacket(buf);
        long fluidLevel = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if (client.world.getBlockEntity(position) instanceof GemCleanserBE blockEntity)
        {
            blockEntity.setFluidLevel(variant, fluidLevel);

            if (client.player.currentScreenHandler instanceof GemCleanserScreenHandler screenHandler &&
                    screenHandler.loaderEntity.getPos().equals(position))
            {
                blockEntity.setFluidLevel(variant, fluidLevel);
                screenHandler.setFluid(new FluidStack(variant, fluidLevel));
            }
        }
    }
}