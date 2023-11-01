package jiraiyah.allthatmatters;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ATMClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        //HandledScreens.register(ModScreenHandlers.STRIPPER_SCREEN_HANDLER, StripperBlockScreen::new);
    }
}