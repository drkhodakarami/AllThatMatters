package jiraiyah.allthatmatters.screen.handler;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.entity.GemCleanserBE;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;

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

        root.setSize(200, 178);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot base_input_slot = WItemSlot.of(blockInventory, GemCleanserBE.BASE_INPUT_SLOT);
        WItemSlot base_output_slot = WItemSlot.of(blockInventory, GemCleanserBE.BASE_OUTPUT_SLOT);
        WItemSlot fluid_input_slot = WItemSlot.of(blockInventory, GemCleanserBE.FLUID_INPUT_SLOT);
        WItemSlot fluid_output_slot = WItemSlot.of(blockInventory, GemCleanserBE.FLUID_OUTPUT_SLOT);

        fluid_input_slot.setInputFilter(itemStack -> itemStack.isOf(Items.BUCKET) || itemStack.isOf(Items.WATER_BUCKET));
        base_input_slot.setInputFilter(itemStack -> itemStack.isOf(ModItems.RAW_ENDERITE) ||
                                                         itemStack.isOf(ModItems.RAW_CITRINE) ||
                                                         itemStack.isOf(ModItems.RAW_RUBY) ||
                                                         itemStack.isOf(ModItems.RAW_SAPPHIRE));
        base_output_slot.setInsertingAllowed(false);
        fluid_output_slot.setInsertingAllowed(false);

        WBar progressBar = new WBar(AllThatMatters.identifier("textures/gui/empty_vertical_progress.png"),
                AllThatMatters.identifier("textures/gui/full_vertical_progress.png"),
                0, 1, WBar.Direction.DOWN);

        WSprite fluidBackground = new WSprite(AllThatMatters.identifier("textures/gui/fluid_tank_background.png"));

        WSprite bucketBackground = new WSprite(AllThatMatters.identifier("textures/gui/slot_bucket.png"));

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
        root.add(bucketBackground, 45, 16, 12, 14);
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