package jiraiyah.allthatmatters.screen.custom;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.entity.GemCleanserBE;
import jiraiyah.allthatmatters.block.entity.SmelteryBE;
import jiraiyah.allthatmatters.screen.handler.SmelteryScreenHandler;
import jiraiyah.allthatmatters.utils.screen.MouseUtil;
import jiraiyah.fluidutils.FluidStack;
import jiraiyah.fluidutils.FluidStackRenderer;
import jiraiyah.fluidutils.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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
            context.drawTooltip(textRenderer, getTooltip(fluidStack, TooltipContext.Default.BASIC, AllThatMatters.ModID),
                    Optional.empty(), mouseX - x, mouseY - y);
    }

    private boolean isMouseAboveAre(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, FluidStackRenderer renderer)
    {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }

    private static final NumberFormat nf = NumberFormat.getIntegerInstance();

    public List<Text> getTooltip(FluidStack fluidStack, TooltipContext tooltipFlag, String modid)
    {
        List<Text> tooltip = new ArrayList<>();
        FluidVariant fluidType = fluidStack.getFluidVariant();
        if (fluidType == null)
            return tooltip;

        MutableText displayName = Text.translatable("block." + Registries.FLUID.getId(fluidStack.fluidVariant.getFluid()).toTranslationKey());
        tooltip.add(displayName);

        long amount = fluidStack.getAmount();

        var fl = FluidUtils.convertDropletsToMb(amount);
        var nl = nf.format(fl);
            MutableText amountString = Text.translatable(modid + ".tooltip.liquid.amount.with.capacity", nl, nf.format(FluidUtils.convertDropletsToMb(81000 * 20)));
            tooltip.add(amountString.fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));

        return tooltip;
    }
}