package jiraiyah.allthatmatters.networking.packet;

import jiraiyah.allthatmatters.block.entity.custom.InfusingStationBlockEntity;
import jiraiyah.allthatmatters.screen.custom.InfusingStationScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class InfusingStationEnergySyncS2CPacket
{
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender)
    {
        long energy = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if(client.world.getBlockEntity(position) instanceof InfusingStationBlockEntity blockEntity)
        {
            blockEntity.setEnergyLevel(energy);

            if(client.player.currentScreenHandler instanceof InfusingStationScreenHandler screenHandler &&
            screenHandler.blockEntity.getPos().equals(position))
                blockEntity.setEnergyLevel(energy);
        }
    }


}