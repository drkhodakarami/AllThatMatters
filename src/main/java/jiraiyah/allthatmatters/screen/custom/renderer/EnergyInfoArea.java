package jiraiyah.allthatmatters.screen.custom.renderer;

import jiraiyah.allthatmatters.utils.screen.InfoArea;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.text.Text;
import team.reborn.energy.api.EnergyStorage;

import java.util.List;

public class EnergyInfoArea extends InfoArea
{
    private final EnergyStorage energy;

    public EnergyInfoArea(MinecraftClient client, VertexConsumerProvider.Immediate vertexConsumers, int xMin, int yMin)
    {
        this(client, vertexConsumers, xMin, yMin, null, 8, 64);
    }

    public EnergyInfoArea(MinecraftClient client, VertexConsumerProvider.Immediate vertexConsumers, int xMin, int yMin, EnergyStorage energy)
    {
        this(client, vertexConsumers, xMin, yMin, energy, 8, 64);
    }

    public EnergyInfoArea(MinecraftClient client, VertexConsumerProvider.Immediate vertexConsumers, int xMin, int yMin, EnergyStorage energy, int width, int height)
    {
        super(client, vertexConsumers, new Rect2i(xMin, yMin, width, height));
        this.energy = energy;
    }

    public List<Text> getTooltips()
    {
        return List.of(Text.literal(energy.getAmount() + " / " + energy.getCapacity() + " E"));
    }

    @Override
    public void draw(DrawContext context)
    {
        final int height = area.getHeight();
        int stored = (int) (height * (energy.getAmount() / (float) energy.getCapacity()));
        context.fillGradient(
                area.getX(), area.getY() + (height - stored),
                area.getX() + area.getWidth(), area.getY() + area.getHeight(),
                0Xffb51500, 0xff600b00);
    }
}