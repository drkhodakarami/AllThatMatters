package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.entity.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.client.renderer.EnderiteShulkerBoxRenderer;
import jiraiyah.allthatmatters.client.renderer.GemCleanserBERenderer;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.screen.custom.*;
import jiraiyah.allthatmatters.screen.handler.CastPressScreenHandler;
import jiraiyah.allthatmatters.screen.handler.ChunkLoaderScreenHandler;
import jiraiyah.allthatmatters.screen.handler.GemCleanserScreenHandler;
import jiraiyah.allthatmatters.screen.handler.SmelteryScreenHandler;
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

        HandledScreens.register(ModScreenHandlers.ENDERITE_SCREEN_HANDLER_TYPE, EnderiteShulkerScreen::new);
        registerShulkerWith(null);

        //noinspection RedundantTypeArguments
        HandledScreens.<ChunkLoaderScreenHandler, ChunkLoaderScreen>register(ModScreenHandlers.CHUNK_LOADER_SCREEN_HANDLER,
                (gui, inventory, title) -> new ChunkLoaderScreen(gui, inventory.player, title));

        //noinspection RedundantTypeArguments
        HandledScreens.<GemCleanserScreenHandler, GemCleanserScreen>register(ModScreenHandlers.GEM_CLEANSER_SCREEN_HANDLER,
                (gui, inventory, title) -> new GemCleanserScreen(gui, inventory.player, title));

        //noinspection RedundantTypeArguments
        HandledScreens.<CastPressScreenHandler, CastPressScreen>register(ModScreenHandlers.CAST_PRESS_SCREEN_HANDLER,
                (gui, inventory, title) -> new CastPressScreen(gui, inventory.player, title));

        //noinspection RedundantTypeArguments
        HandledScreens.<SmelteryScreenHandler, SmelteryScreen>register(ModScreenHandlers.SMELTERY_SCREEN_HANDLER,
                (gui, inventory, title) -> new SmelteryScreen(gui, inventory.player, title));

        HandledScreens.register(ModScreenHandlers.BACKPACK_SCREEN_HANDLER, BackpackScreen::new);

        //region Block Entity Handling
        BlockEntityRendererFactories.register(ModBlockEntities.GEM_CLEANSER, GemCleanserBERenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.ENDERITE_SHULKER, EnderiteShulkerBoxRenderer::new);
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