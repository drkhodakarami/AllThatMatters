package jiraiyah.allthatmatters.networking;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.networking.packet.ForcedChunksUpdatePacket;
import jiraiyah.allthatmatters.networking.packet.GemCleanserFluidSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModMessages
{
    public static final Identifier INFUSING_STATION_FLUID_SYNC = AllThatMatters.identifier("infusing_station_fluid_sync");

    private ModMessages()
    {
        throw new AssertionError();
    }

    public static void registerC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(ForcedChunksUpdatePacket.PACKET_ID, (server, servPlayer, handler, buf, sender) ->
                ForcedChunksUpdatePacket.read(buf).onServerReceive(server, servPlayer, handler, buf, sender));
    }

    public static void registerS2CPackets()
    {
        ClientPlayNetworking.registerGlobalReceiver(INFUSING_STATION_FLUID_SYNC, GemCleanserFluidSyncS2CPacket::receive);

        ClientPlayNetworking.registerGlobalReceiver(ForcedChunksUpdatePacket.PACKET_ID, (client, handler, buf, responseSender) -> ForcedChunksUpdatePacket.read(buf).onClientReceive(client, handler, buf, responseSender));
    }

    public static void sendToClientPlayerEntities(World world, BlockPos pos, Identifier message, PacketByteBuf data)
    {
        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, pos))
            ServerPlayNetworking.send(player, message, data);
    }

    public static void sendToServerPlayerEntities(Identifier message, PacketByteBuf data)
    {
        ClientPlayNetworking.send(message, data);
    }
}