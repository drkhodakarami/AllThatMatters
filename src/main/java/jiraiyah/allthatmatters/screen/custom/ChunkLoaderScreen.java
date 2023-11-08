package jiraiyah.allthatmatters.screen.custom;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import jiraiyah.allthatmatters.screen.handler.ChunkLoaderGUIHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ChunkLoaderScreen extends CottonInventoryScreen<ChunkLoaderGUIHandler>
{
    public ChunkLoaderScreen(ChunkLoaderGUIHandler container, PlayerEntity player, Text title) {
        super(container, player, title);
    }
}