package jiraiyah.allthatmatters.screen.custom;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WPanel;
import jiraiyah.allthatmatters.screen.handler.GemCleanserScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class GemCleanserScreen extends CottonInventoryScreen<GemCleanserScreenHandler>
{
    public GemCleanserScreen(GemCleanserScreenHandler container, PlayerEntity player, Text title)
    {
        super(container, player, title);
    }

    @Override
    protected void handledScreenTick() {
        super.handledScreenTick();
        if (description!=null) {
            WPanel root = description.getRootPanel();
            if (root!=null) {
                root.tick();
            }
        }
    }

    @Override
    protected void drawBackground(DrawContext context, float partialTicks, int mouseX, int mouseY)
    {
        super.drawBackground(context, partialTicks, mouseX, mouseY);

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTicks)
    {
        super.render(context, mouseX, mouseY, partialTicks);
    }
}