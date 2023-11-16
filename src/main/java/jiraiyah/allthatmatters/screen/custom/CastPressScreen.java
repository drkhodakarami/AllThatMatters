package jiraiyah.allthatmatters.screen.custom;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import jiraiyah.allthatmatters.screen.handler.CastPressScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.Optional;

public class CastPressScreen extends CottonInventoryScreen<CastPressScreenHandler>
{
    public CastPressScreen(CastPressScreenHandler container, PlayerEntity player, Text title)
    {
        super(container, player, title);
    }
}