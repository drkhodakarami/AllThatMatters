package jiraiyah.allthatmatters.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.entity.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.screen.handler.EnderiteShulkerScreenHandler;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import jiraiyah.allthatmatters.utils.fluid.FluidStackRenderer;
import jiraiyah.allthatmatters.utils.screen.MouseUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class EnderiteShulkerScreen extends HandledScreen<EnderiteShulkerScreenHandler>
{
    private final Identifier selectedTexture = AllThatMatters.identifier("textures/gui/enderite_shulker_box.png");

    private FluidStackRenderer fluidStackRenderer;

    public EnderiteShulkerScreen(EnderiteShulkerScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);

        int rows = handler.getRows();
        int collums = handler.getColumns();
        this.backgroundWidth = 16 + collums * 18;
        this.backgroundHeight = 114 + rows * 18;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void init()
    {
        super.init();
        fluidStackRenderer = new FluidStackRenderer(EnderiteShulkerBlockEntity.FLUID_CAPACITY, true, 8, 76);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, this.selectedTexture);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(this.selectedTexture, x, y, 0, 0, backgroundWidth - 2, backgroundHeight, 256, Math.max(256, backgroundHeight));
        fluidStackRenderer.drawFluid(context,handler.leftFluidStack,x + 5,y + 192,8,76,EnderiteShulkerBlockEntity.FLUID_CAPACITY);
        fluidStackRenderer.drawFluid(context,handler.rightFluidStack,x + 217,y + 192,8,76,EnderiteShulkerBlockEntity.FLUID_CAPACITY);
        context.drawTexture(selectedTexture,x + 4, y + 191,231,0,10,78,256, Math.max(256, backgroundHeight));
        context.drawTexture(selectedTexture,x + 216, y + 191,231,0,10,78,256, Math.max(256, backgroundHeight));
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        renderFluidTooltips(context, mouseX, mouseY, x, y, handler.leftFluidStack, 4, 191, fluidStackRenderer);
        renderFluidTooltips(context, mouseX, mouseY, x, y, handler.rightFluidStack, 216, 191, fluidStackRenderer);
    }

    private void renderFluidTooltips(DrawContext context, int mouseX, int mouseY, int x, int y, FluidStack fluidStack, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        if(isMouseAboveArea(mouseX, mouseY,x,y, offsetX, offsetY, renderer))
            context.drawTooltip(this.textRenderer,renderer.getTooltip(fluidStack, TooltipContext.BASIC),
                    Optional.empty(),mouseX - x, mouseY - y);
    }

    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        return MouseUtil.isMouseOver(mouseX,mouseY,x + offsetX,y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}