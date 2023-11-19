package jiraiyah.allthatmatters.networking.packet;

import jiraiyah.allthatmatters.block.entity.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.screen.handler.EnderiteShulkerScreenHandler;
import jiraiyah.fluidutils.FluidStack;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class ShulkerFluidSyncS2CPacket
{
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender)
    {
        FluidVariant leftVariant = FluidVariant.fromPacket(buf);
        FluidVariant rightVariant = FluidVariant.fromPacket(buf);
        long leftLevel = buf.readLong();
        long rightLevel = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if (client.world.getBlockEntity(position) instanceof EnderiteShulkerBlockEntity blockEntity)
        {
            blockEntity.setFluidLevel(leftVariant, leftLevel, rightVariant, rightLevel);

            if (client.player.currentScreenHandler instanceof EnderiteShulkerScreenHandler screenHandler &&
                    screenHandler.loaderEntity.getPos().equals(position))
            {
                blockEntity.setFluidLevel(leftVariant, leftLevel, rightVariant, rightLevel);
                screenHandler.setFluid(new FluidStack(leftVariant, leftLevel), new FluidStack(rightVariant, rightLevel));
            }
        }
    }
}