package jiraiyah.allthatmatters.screen.custom;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WPanel;
import jiraiyah.allthatmatters.block.entity.GemCleanserBE;
import jiraiyah.allthatmatters.screen.handler.GemCleanserScreenHandler;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import jiraiyah.allthatmatters.utils.fluid.FluidStackRenderer;
import jiraiyah.allthatmatters.utils.screen.MouseUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

import java.util.Optional;

public class GemCleanserScreen extends CottonInventoryScreen<GemCleanserScreenHandler>
{
    private FluidStackRenderer fluidStackRenderer;
    private GemCleanserScreenHandler container;

    public GemCleanserScreen(GemCleanserScreenHandler container, PlayerEntity player, Text title)
    {
        super(container, player, title);
        this.container = container;
        this.fluidStackRenderer = new FluidStackRenderer(GemCleanserBE.FLUID_CAPACITY, true, 15, 61);
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
        fluidStackRenderer.drawFluid(context, container.fluidStack, x + 10, y + 0, 100, 200, GemCleanserBE.FLUID_CAPACITY);
        super.drawBackground(context, partialTicks, mouseX, mouseY);
        fluidStackRenderer.drawFluid(context, container.fluidStack, x + 10, y + 0, 100, 200, GemCleanserBE.FLUID_CAPACITY);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTicks)
    {
        fluidStackRenderer.drawFluid(context, container.fluidStack, x + 10, y + 0, 100, 200, GemCleanserBE.FLUID_CAPACITY);
        super.render(context, mouseX, mouseY, partialTicks);
        fluidStackRenderer.drawFluid(context, container.fluidStack, x + 10, y + 0, 100, 200, GemCleanserBE.FLUID_CAPACITY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        super.drawForeground(context, mouseX, mouseY);
        renderFLuidTooltip(context, mouseX, mouseY, x, y, container.fluidStack, 93, 21, fluidStackRenderer);
    }

    private void renderFLuidTooltip(DrawContext context, int mouseX, int mouseY, int x, int y, FluidStack fluidStack, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        if(isMouseAboveAre(mouseX, mouseY, x, y, offsetX, offsetY, renderer))
            context.drawTooltip(textRenderer, renderer.getTooltip(fluidStack, TooltipContext.Default.BASIC),
                    Optional.empty(), mouseX - x, mouseY - y);
    }

    private boolean isMouseAboveAre(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}