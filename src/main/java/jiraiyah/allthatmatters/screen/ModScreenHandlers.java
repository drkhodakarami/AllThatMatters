package jiraiyah.allthatmatters.screen;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.custom.CastPressBlock;
import jiraiyah.allthatmatters.block.custom.ChunkLoaderBlock;
import jiraiyah.allthatmatters.block.custom.GemCleanserBlock;
import jiraiyah.allthatmatters.block.custom.SmelteryBlock;
import jiraiyah.allthatmatters.screen.handler.*;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers
{
    public static ScreenHandlerType<EnderiteShulkerScreenHandler> ENDERITE_SCREEN_HANDLER_TYPE =
            Registry.register(Registries.SCREEN_HANDLER, AllThatMatters.identifier("enderiteshulker"),
                    new ExtendedScreenHandlerType<>(EnderiteShulkerScreenHandler::new));

    public final static ScreenHandlerType<BackpackScreenHandler> BACKPACK_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, BackpackScreenHandler.IDENTIFIER,
                    new ExtendedScreenHandlerType<>(BackpackScreenHandler::new));

    public static ScreenHandlerType CHUNK_LOADER_SCREEN_HANDLER;
    public static ScreenHandlerType GEM_CLEANSER_SCREEN_HANDLER;
    public static ScreenHandlerType SMELTERY_SCREEN_HANDLER;
    public static ScreenHandlerType CAST_PRESS_SCREEN_HANDLER;

    public ModScreenHandlers()
    {
        throw new AssertionError();
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Screen Handlers");

        CHUNK_LOADER_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(ChunkLoaderBlock.ID,
                (syncId, inventory, buf) -> new ChunkLoaderScreenHandler(syncId, inventory,
                        ScreenHandlerContext.create(inventory.player.getWorld(), buf.readBlockPos())));

        GEM_CLEANSER_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(GemCleanserBlock.ID,
                (syncId, inventory, buf) -> new GemCleanserScreenHandler(syncId, inventory,
                        ScreenHandlerContext.create(inventory.player.getWorld(), buf.readBlockPos())));

        CAST_PRESS_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(CastPressBlock.ID,
                (syncId, inventory, buf) -> new CastPressScreenHandler(syncId, inventory,
                        ScreenHandlerContext.create(inventory.player.getWorld(), buf.readBlockPos())));

        SMELTERY_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(SmelteryBlock.ID,
                (syncId, inventory, buf) -> new SmelteryScreenHandler(syncId, inventory,
                        ScreenHandlerContext.create(inventory.player.getWorld(), buf.readBlockPos())));
    }
}