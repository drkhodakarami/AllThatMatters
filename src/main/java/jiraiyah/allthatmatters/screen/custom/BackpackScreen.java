package jiraiyah.allthatmatters.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import jiraiyah.allthatmatters.screen.handler.BackpackScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BackpackScreen extends HandledScreen<BackpackScreenHandler>
{
    public static final Identifier GENERIC_54 = new Identifier("textures/gui/container/generic_54.png");

    public BackpackScreen(BackpackScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
        this.backgroundWidth = handler.getWidth() * 18 + 17;
        this.backgroundHeight = (handler.getHeight() + 4) * 18 + 41;
        this.playerInventoryTitleY = handler.getHeight() * 18 + 20;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GENERIC_54);
        context.getMatrices().push();
        context.getMatrices().translate(this.x, this.y, 0.0D);

        // Upper-left corner
        context.drawTexture(GENERIC_54, 0, 0, 0, 0, 7, 17);
        // Top
        context.drawTexture(GENERIC_54, 7, 0, handler.getWidth() * 18, 17, 7, 0, 1, 17, 256, 256);
        // Upper-right corner
        context.drawTexture(GENERIC_54, 7 + handler.getWidth() * 18, 0, 169, 0, 7, 17);
        // Left
        context.drawTexture(GENERIC_54, 0, 17, 7, (handler.getHeight() + 4) * 18 + 22, 0, 17, 7, 1, 256, 256);
        // Lower-left corner
        context.drawTexture(GENERIC_54, 0, (handler.getHeight() + 4) * 18 + 34, 0, 215, 7, 7);
        // Lower
        context.drawTexture(GENERIC_54, 7, (handler.getHeight() + 4) * 18 + 34, handler.getWidth() * 18, 7, 7, 215, 1, 7, 256, 256);
        // Lower-right corner
        context.drawTexture(GENERIC_54, (handler.getWidth() * 18 + 7), (handler.getHeight() + 4) * 18 + 34, 169, 215, 7, 7);
        // Right
        context.drawTexture(GENERIC_54, (handler.getWidth() * 18 + 7), 17, 7, (handler.getHeight() + 4) * 18 + 17, 169, 17, 7, 1, 256, 256);
        // Background fill
        context.fill(7, 17, this.backgroundWidth - 10, this.backgroundHeight - 7, 0xFFC6C6C6);

        this.handler.slots.forEach(s ->
        {
            context.drawTexture(GENERIC_54, s.x - 1, s.y - 1, 7, 17, 18, 18);
        });

        context.getMatrices().pop();
    }
}