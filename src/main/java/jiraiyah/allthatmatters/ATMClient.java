package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import jiraiyah.allthatmatters.block.entity.custom.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.client.EnderiteShulkerBoxRenderer;
import jiraiyah.allthatmatters.screen.custom.EnderiteShulkerScreen;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.screen.custom.InfusingStationScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class ATMClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        HandledScreens.register(ModScreenHandlers.INFUSING_POLISHING_SCREEN_HANDLER, InfusingStationScreen::new);

        HandledScreens.register(ModScreenHandlers.ENDERITE_SCREEN_HANDLER_TYPE, EnderiteShulkerScreen::new);
        registerShulkerWith(null);
        BlockEntityRendererFactories.register(ModBlockEntities.ENDERITE_SHULKER_ENTITY, EnderiteShulkerBoxRenderer::new);
        for (DyeColor color : DyeColor.values())
            registerShulkerWith(color);
    }

    private void registerShulkerWith(DyeColor color)
    {
        BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.getShulkerBlock(color), (stack, mode, matrices, vertexConsumers, light, overlay) ->
        {
            EnderiteShulkerBlockEntity be = new EnderiteShulkerBlockEntity(color, BlockPos.ORIGIN, ModBlocks.getShulkerBlock(color).getDefaultState());
            NbtCompound tag = stack.getSubNbt(EnderiteShulkerBoxBlock.KEY);
            if (tag != null)
                be.appendUpgrades(tag);
            MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(be, matrices, vertexConsumers, light, overlay);
        });
    }
}