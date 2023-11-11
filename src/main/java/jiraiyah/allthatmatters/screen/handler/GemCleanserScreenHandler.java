package jiraiyah.allthatmatters.screen.handler;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.entity.GemCleanserBE;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import jiraiyah.allthatmatters.utils.fluid.FluidStackRenderer;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.registry.Registries;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GemCleanserScreenHandler extends SyncedGuiDescription
{
    private final ScreenHandlerContext context;

    public GemCleanserBE loaderEntity;
    public FluidStack fluidStack;

    public GemCleanserScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(ModScreenHandlers.GEM_CLEANSER_SCREEN_HANDLER, syncId, playerInventory, getBlockInventory(context, GemCleanserBE.TOTAL_SLOTS), getBlockPropertyDelegate(context, GemCleanserBE.DELEGATE_SIZE));

        this.context = context;

        this.context.run((world, pos) -> loaderEntity = (GemCleanserBE) world.getBlockEntity(pos));
        this.fluidStack = new FluidStack(loaderEntity.fluidStorage.variant, loaderEntity.fluidStorage.amount);

        this.setTitleVisible(false);

        WPlainPanel  root = new WPlainPanel ();
        setRootPanel(root);

        root.setSize(200, 168);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot base_input_slot = WItemSlot.of(blockInventory, GemCleanserBE.BASE_INPUT_SLOT);
        WItemSlot base_output_slot = WItemSlot.of(blockInventory, GemCleanserBE.BASE_OUTPUT_SLOT);
        WItemSlot fluid_input_slot = WItemSlot.of(blockInventory, GemCleanserBE.FLUID_INPUT_SLOT);
        WItemSlot fluid_output_slot = WItemSlot.of(blockInventory, GemCleanserBE.FLUID_OUTPUT_SLOT);

        WBar progressBar = new WBar(AllThatMatters.identifier("textures/gui/empty_vertical_progress.png"),
                AllThatMatters.identifier("textures/gui/full_vertical_progress.png"),
                0, 1, WBar.Direction.DOWN);

        WSprite fluidBackground = new WSprite(AllThatMatters.identifier("textures/gui/fluid_tank_background.png"));

        WSprite fluidTube = new WSprite(AllThatMatters.identifier("textures/gui/short_tube.png"));
        WSprite connectionTube = new WSprite(AllThatMatters.identifier("textures/gui/gem_cleanser_connection_tube.png"));
        WSprite littleArrowDown = new WSprite(AllThatMatters.identifier("textures/gui/little_arrow_down.png"));

        root.add(base_input_slot, 116, 14);
        root.add(base_output_slot, 116, 59);
        root.add(fluid_input_slot, 42, 14);
        root.add(fluid_output_slot, 42, 59);

        progressBar.setProperties(this.propertyDelegate);

        root.add(progressBar, 135, 33, 8, 26);
        root.add(littleArrowDown, 46, 42, 9, 8);
        root.add(fluidBackground, 85, 14, 18, 63);
        root.add(fluidTube, 59, 26, 27, 20);
        root.add(connectionTube, 102, 31, 25, 29);
        root.add(this.createPlayerInventoryPanel(), 7, 85);

        root.validate(this);
    }

    @Override
    public boolean canUse(PlayerEntity entity)
    {
        return canUse(this.context, entity, ModBlocks.GEM_CLEANSER);
    }

    public void setFluid(FluidStack stack)
    {
        fluidStack = stack;
    }
}