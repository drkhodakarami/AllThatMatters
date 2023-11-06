package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import jiraiyah.allthatmatters.block.entity.custom.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.client.renderer.InfusingStationBlockEntityRenderer;
import jiraiyah.allthatmatters.client.renderer.EnderiteShulkerBoxRenderer;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.screen.custom.EnderiteShulkerScreen;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.screen.custom.InfusingStationScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ATMClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModMessages.registerS2CPackets();

        HandledScreens.register(ModScreenHandlers.INFUSING_POLISHING_SCREEN_HANDLER, InfusingStationScreen::new);

        HandledScreens.register(ModScreenHandlers.ENDERITE_SCREEN_HANDLER_TYPE, EnderiteShulkerScreen::new);
        registerShulkerWith(null);
        BlockEntityRendererFactories.register(ModBlockEntities.ENDERITE_SHULKER_ENTITY, EnderiteShulkerBoxRenderer::new);
        for (DyeColor color : DyeColor.values())
            registerShulkerWith(color);

        BlockEntityRendererFactories.register(ModBlockEntities.INFUSING_STATION_BLOCK_ENTITY, InfusingStationBlockEntityRenderer::new);

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MOLTEN_ENDERITE, ModFluids.FLOWING_MOLTEN_ENDERITE,
                new SimpleFluidRenderHandler(
                        new Identifier(AllThatMatters.ModID, "block/liquid"),
                        new Identifier(AllThatMatters.ModID, "block/liquid_flow"),
                        0xA10C5E7C));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_MOLTEN_ENDERITE, ModFluids.FLOWING_MOLTEN_ENDERITE);
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