package jiraiyah.allthatmatters.screen;

import jiraiyah.allthatmatters.AllThatMatters;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.util.Identifier;

public class ModScreenHandlers
{
    /*public static final ScreenHandlerType<StripperBlockScreenHandler> STRIPPER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(StripBlock.ModID, "stripper"),
                    new ExtendedScreenHandlerType<>(StripperBlockScreenHandler::new));*/

    public static ScreenHandlerType<EnderiteShulkerScreenHandler> ENDERITE_SCREEN_HANDLER_TYPE =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(AllThatMatters.ModID, "enderiteshulker"),
                    new ExtendedScreenHandlerType<>(EnderiteShulkerScreenHandler::new));

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Screen Handlers for : " + AllThatMatters.ModID);
    }
}