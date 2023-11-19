package jiraiyah.allthatmatters.screen.custom;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.entity.GemCleanserBE;
import jiraiyah.allthatmatters.block.entity.SmelteryBE;
import jiraiyah.allthatmatters.screen.handler.SmelteryScreenHandler;
import jiraiyah.allthatmatters.utils.screen.MouseUtil;
import jiraiyah.fluidutils.FluidStack;
import jiraiyah.fluidutils.FluidStackRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.Optional;

public class SmelteryScreen extends CottonInventoryScreen<SmelteryScreenHandler>
{
    private FluidStackRenderer fluidStackRenderer;
    private SmelteryScreenHandler container;

    public SmelteryScreen(SmelteryScreenHandler container, PlayerEntity player, Text title)
    {
        super(container, player, title);
        this.container = container;
        this.fluidStackRenderer = new FluidStackRenderer(SmelteryBE.FLUID_CAPACITY, true, 15, 61);
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

        fluidStackRenderer.drawFluid(context, container.fluidStack, x + 76, y + 22, 16, 61, GemCleanserBE.FLUID_CAPACITY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        super.drawForeground(context, mouseX, mouseY);
        renderFLuidTooltip(context, mouseX, mouseY, x, y, container.fluidStack, 76, 22, fluidStackRenderer);
    }

    private void renderFLuidTooltip(DrawContext context, int mouseX, int mouseY, int x, int y, FluidStack fluidStack, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        if(isMouseAboveAre(mouseX, mouseY, x, y, offsetX, offsetY, renderer))
            context.drawTooltip(textRenderer, renderer.getTooltip(fluidStack, TooltipContext.Default.BASIC, AllThatMatters.ModID),
                    Optional.empty(), mouseX - x, mouseY - y);
    }

    private boolean isMouseAboveAre(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}