package jiraiyah.allthatmatters.block.entity;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.screen.handler.ChunkLoaderScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ChunkLoaderBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ExtendedScreenHandlerFactory
{

    public ChunkLoaderBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CHUNK_LOADER, pos, state);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player)
    {
        return new ChunkLoaderScreenHandler(syncId, inv, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf)
    {
        packetByteBuf.writeBlockPos(pos);
    }
}