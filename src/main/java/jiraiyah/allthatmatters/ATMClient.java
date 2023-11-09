package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.entity.custom.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.client.renderer.InfusingStationBlockEntityRenderer;
import jiraiyah.allthatmatters.client.renderer.EnderiteShulkerBoxRenderer;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.screen.custom.ChunkLoaderScreen;
import jiraiyah.allthatmatters.screen.custom.EnderiteShulkerScreen;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.screen.custom.InfusingStationScreen;
import jiraiyah.allthatmatters.screen.handler.ChunkLoaderScreenHandler;
import jiraiyah.allthatmatters.utils.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class ATMClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModMessages.registerS2CPackets();

        //region Screen Handling
        HandledScreens.register(ModScreenHandlers.INFUSING_POLISHING_SCREEN_HANDLER, InfusingStationScreen::new);

        HandledScreens.register(ModScreenHandlers.ENDERITE_SCREEN_HANDLER_TYPE, EnderiteShulkerScreen::new);
        registerShulkerWith(null);

        //endregion

        //region Block Entity Handling
        BlockEntityRendererFactories.register(ModBlockEntities.INFUSING_STATION_BLOCK_ENTITY, InfusingStationBlockEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.ENDERITE_SHULKER_ENTITY, EnderiteShulkerBoxRenderer::new);
        for (DyeColor color : DyeColor.values())
            registerShulkerWith(color);
        //endregion

        //region Fluid Handling
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MOLTEN_ENDERITE, ModFluids.FLOWING_MOLTEN_ENDERITE,
                new SimpleFluidRenderHandler(
                        AllThatMatters.identifier("block/liquid"),
                        AllThatMatters.identifier("block/liquid_flow"),
                        0xA10C5E7C));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_MOLTEN_ENDERITE, ModFluids.FLOWING_MOLTEN_ENDERITE);
        //endregion

        HandledScreens.<ChunkLoaderScreenHandler, ChunkLoaderScreen>register(ModScreenHandlers.CHUNK_LOADER_SCREEN_HANDLER, (gui, inventory, title) -> new ChunkLoaderScreen(gui, inventory.player, title));

        ModModelPredicateProvider.registerModels();
    }

    private void registerShulkerWith(DyeColor color)
    {
        BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.getShulkerBlock(color), (stack, mode, matrices, vertexConsumers, light, overlay) ->
        {
            EnderiteShulkerBlockEntity be = new EnderiteShulkerBlockEntity(color, BlockPos.ORIGIN, ModBlocks.getShulkerBlock(color).getDefaultState());
            MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(be, matrices, vertexConsumers, light, overlay);
        });
    }
}