package jiraiyah.allthatmatters.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.utils.screen.renderer.ButtonIconWidget;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import static jiraiyah.allthatmatters.AllThatMatters.identifier;
import static jiraiyah.allthatmatters.AllThatMatters.translate;

public class BackpackRenameScreen extends Screen
{
    private static final Identifier ICONS = identifier("textures/gui/icons.png");
    private static final Identifier BACKGROUND = new Identifier("textures/gui/demo_background.png");

    private static final int NAME_MAX_CHARS = 32;

    private final Hand hand;
    private final Text placeHolder;
    private final OrderedText orderedTextTitle;
    private final Text maxCharsMsg;
    private TextFieldWidget textField;

    private int x;
    private int y;

    public BackpackRenameScreen(Hand hand, Text placeHolder)
    {
        super(translate("screen.title.rename"));
        this.hand = hand;
        this.orderedTextTitle = this.getTitle().asOrderedText();
        this.placeHolder = placeHolder;
        this.maxCharsMsg = translate("screen.rename.max.length", NAME_MAX_CHARS);
    }

    @Override
    protected void init()
    {
        super.init();

        this.x = (this.width - 248) / 2;
        this.y = (this.height - 120) / 2;

        this.textField = new TextFieldWidget(this.textRenderer, this.x + ((248 - 195) / 2), this.y + 50, 195, 20, this.placeHolder);
        this.textField.setMaxLength(NAME_MAX_CHARS);
        this.addDrawableChild(this.textField);
        this.textField.setText(this.placeHolder.getString());

        // Reset to default name button
        this.addDrawableChild(new ButtonIconWidget(this.x + (248 - 50) / 2, this.y + 120 - 26,
                translate("screen.button.default.name"),
                b -> this.sendChange(true), ICONS, 32, 0, this::renderTooltip));
        // Rename button
        this.addDrawableChild(new ButtonIconWidget(30 + this.x + (248 - 50) / 2, this.y + 120 - 26,
                translate("screen.button.rename"),
                b -> this.sendChange(false), ICONS, 0, 0, this::renderTooltip));
        // Close button
        this.addDrawableChild(new ButtonIconWidget(this.x + 222, this.y + 6,
                translate("screen.button.close"),
                b -> this.close(), ICONS, 16, 0, this::renderTooltip));

        this.setInitialFocus(this.textField);
    }

    private void renderTooltip(DrawContext context, Text text, int x, int y)
    {
        context.drawTooltip(this.textRenderer, text, x, y);
    }

    public void sendChange(boolean def)
    {
        final PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBoolean(def);
        buf.writeEnumConstant(hand);

        if (!def) {
            buf.writeString(this.textField.getText().trim());
        }

        ClientPlayNetworking.send(AllThatMatters.PACKET_RENAME_BACKPACK, buf);
        this.close();
    }

    @Override
    public void tick()
    {
        super.tick();
        //this.textField.tick();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == GLFW.GLFW_KEY_ENTER)
            this.sendChange(false);

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        this.renderBackground(context, mouseX, mouseY, delta);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        context.drawTexture(BACKGROUND, this.x, this.y, 0, 0, 248, 116);
        context.drawTexture(BACKGROUND, this.x, this.y + 116, 0, 162, 248, 4);
        this.textField.render(context, mouseX, mouseY, delta);
        this.textField.render(context, mouseX, mouseY, delta);

        context.drawText(this.textRenderer, this.orderedTextTitle,
                (this.width - this.textRenderer.getWidth(this.orderedTextTitle)) / 2,
                this.y + 8, 0x404040, false);

        context.drawText(this.textRenderer, this.maxCharsMsg,
                this.textField.getX(), this.textField.getY() - this.textRenderer.fontHeight - 5,
                0xFFFF5252, true);

        super.render(context, mouseX, mouseY, delta);
    }
}