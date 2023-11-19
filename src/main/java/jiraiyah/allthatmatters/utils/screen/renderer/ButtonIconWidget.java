package jiraiyah.allthatmatters.utils.screen.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ButtonIconWidget extends ButtonWidget
{
    public static final Identifier WIDGETS_TEXTURE = new Identifier("textures/gui/widgets.png");

    private final Identifier icon;
    private final int ix;
    private final int iy;
    private final ITooltipRendererCallback cb;

    public ButtonIconWidget(int x, int y, Text hover, PressAction onPress, Identifier icon, int ix, int iy, ITooltipRendererCallback cb)
    {
        super(x, y, 20, 20, hover, onPress, DEFAULT_NARRATION_SUPPLIER);
        this.icon = icon;
        this.ix = ix;
        this.iy = iy;
        this.cb = cb;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderTexture(0, WIDGETS_TEXTURE);
        context.drawTexture(icon, this.getX(), this.getY(), 0, 46 + i * 20, this.width / 2, this.height);
        context.drawTexture(icon, this.getX() + this.width / 2, this.getY(), 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);

        RenderSystem.setShaderTexture(0, icon);
        context.drawTexture(icon, this.getX() + 2, this.getY() + 2, this.ix, this.iy, 16, 16);

        if (mouseX >= this.getX() && mouseX < this.getX() + this.width &&
            mouseY >= this.getY() && mouseY < this.getY() + this.height)
            this.cb.renderWithTooltip(context, this.getMessage(), mouseX, mouseY);
    }

    @FunctionalInterface
    public interface ITooltipRendererCallback
    {
        void renderWithTooltip(DrawContext context, Text text, int mouseX, int mouseY);
    }

    protected int getYImage(boolean hovered)
    {
        int i = 1;
        if (!this.active)
            i = 0;
        else if (hovered)
            i = 2;

        return i;
    }
}