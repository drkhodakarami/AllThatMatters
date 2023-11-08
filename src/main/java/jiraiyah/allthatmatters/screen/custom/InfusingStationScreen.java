package jiraiyah.allthatmatters.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.entity.custom.InfusingStationBlockEntity;
import jiraiyah.allthatmatters.screen.custom.renderer.EnergyInfoArea;
import jiraiyah.allthatmatters.screen.handler.InfusingStationScreenHandler;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import jiraiyah.allthatmatters.utils.fluid.FluidStackRenderer;
import jiraiyah.allthatmatters.utils.screen.MouseUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class InfusingStationScreen extends HandledScreen<InfusingStationScreenHandler>
{
    private static final Identifier TEXTURE = AllThatMatters.identifier("textures/gui/container/infusing_station.png");

    private EnergyInfoArea energyInfoArea;
    private FluidStackRenderer fluidStackRenderer;

    public InfusingStationScreen(InfusingStationScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        super.init();
        titleY = 2000;
        playerInventoryTitleY = 2000;
        assignEnergyInfoAre();
        assignFluidStackRenderer();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
        renderLiquidProgress(context, x, y);
        energyInfoArea.draw(context);
        fluidStackRenderer.drawFluid(context, handler.fluidStack, x + 55, y + 15, 16, 61, InfusingStationBlockEntity.FLUID_CAPACITY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        renderEnergyAreaTooltips(context, mouseX, mouseY, x, y);
        renderFluidAreaTooltips(context, mouseX, mouseY, x, y, handler.fluidStack, 55, 15, fluidStackRenderer);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void renderProgressArrow(DrawContext context, int x, int y)
    {
        if(handler.isCrafting())
            context.drawTexture(TEXTURE, x + 105, y + 33, 176, 0, 8, handler.getScaledProgress());
    }

    private void renderLiquidProgress(DrawContext context, int x, int y)
    {
        if(handler.isMeltingLiquid())
            context.drawTexture(TEXTURE, x + 29, y + 26, 184, 0, 25, handler.getLiquidScaledProgress());
    }

    //region ENERGY RENDERING
    private void assignEnergyInfoAre()
    {
        energyInfoArea = new EnergyInfoArea(MinecraftClient.getInstance(), null,
                ((width - backgroundWidth) / 2) + 156,
                ((height - backgroundHeight) / 2) + 13,
                handler.blockEntity.energyStorage);
    }

    private void renderEnergyAreaTooltips(DrawContext context, int mouseX, int mouseY, int x, int y)
    {
        if(isMouseAboveArea(mouseX, mouseY, x, y, 156, 13, 8, 64))
            context.drawTooltip(textRenderer, energyInfoArea.getTooltips(), Optional.empty(), mouseX - x, mouseY - y);
    }

    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, int width, int height)
    {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, width, height);
    }
    //endregion

    //region FLUID RENDERING
    private void assignFluidStackRenderer()
    {
        fluidStackRenderer = new FluidStackRenderer(InfusingStationBlockEntity.FLUID_CAPACITY,
                true, 15, 61);
    }

    private void renderFluidAreaTooltips(DrawContext context, int mouseX, int mouseY, int x, int y, FluidStack fluidStack, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        if(isMouseAboveArea(mouseX, mouseY, x, y, offsetX, offsetY, renderer))
            context.drawTooltip(textRenderer, renderer.getTooltip(fluidStack, TooltipContext.Default.BASIC), Optional.empty(), mouseX - x, mouseY - y);
    }

    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
    //endregion
}