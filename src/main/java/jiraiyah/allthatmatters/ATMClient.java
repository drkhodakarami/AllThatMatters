package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import jiraiyah.allthatmatters.block.entity.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.block.entity.ModBlockEntities;
import jiraiyah.allthatmatters.client.EnderiteShulkerBoxRenderer;
import jiraiyah.allthatmatters.screen.EnderiteShulkerScreen;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
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
        //HandledScreens.register(ModScreenHandlers.STRIPPER_SCREEN_HANDLER, StripperBlockScreen::new);

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