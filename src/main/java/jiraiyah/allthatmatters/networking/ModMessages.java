package jiraiyah.allthatmatters.networking;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.networking.packet.InfusingStationEnergySyncS2CPacket;
import jiraiyah.allthatmatters.networking.packet.InfusingStationFluidSyncS2CPacket;
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
    public static final Identifier INFUSING_STATION_ENERGY_SYNC = new Identifier(AllThatMatters.ModID, "infusing_station_energy_sync");
    public static final Identifier INFUSING_STATION_FLUID_SYNC = new Identifier(AllThatMatters.ModID, "infusing_station_fluid_sync");

    public static void registerC2SPackets()
    {

    }

    public static void registerS2CPackets()
    {
        ClientPlayNetworking.registerGlobalReceiver(INFUSING_STATION_ENERGY_SYNC, InfusingStationEnergySyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(INFUSING_STATION_FLUID_SYNC, InfusingStationFluidSyncS2CPacket::receive);
    }

    public static void sendToServerPlayerEntities(World world, BlockPos pos, Identifier message, PacketByteBuf data)
    {
        for(ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, pos))
            ServerPlayNetworking.send(player, message, data);
    }
}