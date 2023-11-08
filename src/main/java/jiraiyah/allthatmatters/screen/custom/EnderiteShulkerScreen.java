package jiraiyah.allthatmatters.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.screen.handler.EnderiteShulkerScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EnderiteShulkerScreen extends HandledScreen<EnderiteShulkerScreenHandler>
{
    private final Identifier selectedTexture = AllThatMatters.identifier("textures/gui/container/enderite_shulker_box.png");
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
        context.drawTexture(this.selectedTexture,x,y,0,0, backgroundWidth - 2, backgroundHeight, 256, Math.max(256, backgroundHeight));
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
    }
}