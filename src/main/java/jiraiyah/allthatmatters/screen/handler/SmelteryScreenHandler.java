package jiraiyah.allthatmatters.screen.handler;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.entity.SmelteryBE;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.utils.ModTags;
import jiraiyah.fluidutils.FluidStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.ScreenHandlerContext;

public class SmelteryScreenHandler extends SyncedGuiDescription
{
    private final ScreenHandlerContext context;

    public SmelteryBE loaderEntity;
    public FluidStack fluidStack;

    public SmelteryScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(ModScreenHandlers.SMELTERY_SCREEN_HANDLER, syncId, playerInventory, getBlockInventory(context, SmelteryBE.TOTAL_SLOTS), getBlockPropertyDelegate(context, SmelteryBE.DELEGATE_SIZE));

        this.context = context;

        this.context.run((world, pos) -> loaderEntity = (SmelteryBE) world.getBlockEntity(pos));
        this.fluidStack = new FluidStack(loaderEntity.fluidStorage.variant, loaderEntity.fluidStorage.amount);

        this.setTitleVisible(false);

        WPlainPanel  root = new WPlainPanel ();
        setRootPanel(root);

        root.setSize(200, 221);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot base_input_slot = WItemSlot.of(blockInventory, SmelteryBE.BASE_INPUT_SLOT);
        WItemSlot base_output_slot = WItemSlot.of(blockInventory, SmelteryBE.BASE_OUTPUT_SLOT);
        WItemSlot fluid_input_slot = WItemSlot.of(blockInventory, SmelteryBE.FLUID_INPUT_SLOT);
        WItemSlot fluid_output_slot = WItemSlot.of(blockInventory, SmelteryBE.FLUID_OUTPUT_SLOT);
        WItemSlot cast_slot = WItemSlot.of(blockInventory, SmelteryBE.CAST_SLOT);

        WItemSlot cast_inv_slot_1 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_1);
        WItemSlot cast_inv_slot_2 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_2);
        WItemSlot cast_inv_slot_3 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_3);
        WItemSlot cast_inv_slot_4 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_4);
        WItemSlot cast_inv_slot_5 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_5);
        WItemSlot cast_inv_slot_6 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_6);
        WItemSlot cast_inv_slot_7 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_7);
        WItemSlot cast_inv_slot_8 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_8);
        WItemSlot cast_inv_slot_9 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_9);
        WItemSlot cast_inv_slot_10 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_10);
        WItemSlot cast_inv_slot_11 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_11);
        WItemSlot cast_inv_slot_12 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_12);
        WItemSlot cast_inv_slot_13 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_13);
        WItemSlot cast_inv_slot_14 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_14);
        WItemSlot cast_inv_slot_15 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_15);
        WItemSlot cast_inv_slot_16 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_16);
        WItemSlot cast_inv_slot_17 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_17);
        WItemSlot cast_inv_slot_18 = WItemSlot.of(blockInventory, SmelteryBE.CAST_INV_SLOT_18);

        fluid_input_slot.setInputFilter(itemStack -> itemStack.isOf(Items.BUCKET) || itemStack.isOf(Items.LAVA_BUCKET));

        cast_slot.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));

        cast_inv_slot_1.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_2.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_3.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_4.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_5.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_6.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_7.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_8.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_9.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_10.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_11.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_12.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_13.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_14.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_15.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_16.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_17.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));
        cast_inv_slot_18.setInputFilter(itemStack -> itemStack.isIn(ModTags.Items.CASTS) || itemStack.isIn(ModTags.Items.WOOD_CASTS));

        base_input_slot.setInputFilter(stack -> stack.isIn(ItemTags.COAL_ORES) ||
                                                stack.isIn(ItemTags.COPPER_ORES) ||
                                                stack.isIn(ItemTags.DIAMOND_ORES) ||
                                                stack.isIn(ItemTags.GOLD_ORES) ||
                                                stack.isIn(ItemTags.IRON_ORES) ||
                                                stack.isIn(ItemTags.EMERALD_ORES) ||
                                                stack.isIn(ItemTags.LAPIS_ORES) ||
                                                stack.isIn(ItemTags.REDSTONE_ORES) ||
                                                stack.isOf(Items.NETHER_QUARTZ_ORE) ||
                                                stack.isOf(Items.STRING) ||
                                                stack.isIn(ModTags.Items.SMELTERY)||
                                                stack.isIn(ItemTags.WOOL) ||
                                                stack.isIn(ItemTags.PLANKS) ||
                                                stack.isIn(ModTags.Items.PLATE) ||
                                                stack.isIn(ItemTags.STONE_CRAFTING_MATERIALS) ||
                                                stack.isIn(ItemTags.STONE_TOOL_MATERIALS));

        base_output_slot.setInsertingAllowed(false);
        fluid_output_slot.setInsertingAllowed(false);

        WBar progressBar = new WBar(AllThatMatters.identifier("textures/gui/empty_vertical_progress.png"),
                AllThatMatters.identifier("textures/gui/full_vertical_progress.png"),
                0, 1, WBar.Direction.DOWN);

        WSprite fluidBackground = new WSprite(AllThatMatters.identifier("textures/gui/fluid_tank_background.png"));
        WSprite bucketBackground = new WSprite(AllThatMatters.identifier("textures/gui/slot_bucket.png"));
        WSprite castBackground = new WSprite(AllThatMatters.identifier("textures/gui/container_edit_mode_fill.png"));

        WSprite fluidTube = new WSprite(AllThatMatters.identifier("textures/gui/short_tube.png"));
        WSprite connectionTube = new WSprite(AllThatMatters.identifier("textures/gui/gem_cleanser_connection_tube.png"));
        WSprite littleArrowDown = new WSprite(AllThatMatters.identifier("textures/gui/little_arrow_down.png"));

        root.add(base_input_slot, 99, 14);
        root.add(base_output_slot, 99, 59);
        root.add(connectionTube, 85, 31, 25, 29);

        root.add(fluid_input_slot, 25, 14);
        root.add(fluid_output_slot, 25, 59);

        progressBar.setProperties(this.propertyDelegate);

        root.add(progressBar, 121, 33, 8, 26);

        root.add(cast_slot, 133, 36);

        root.add(cast_inv_slot_1, 7, 93);
        root.add(cast_inv_slot_2, 25, 93);
        root.add(cast_inv_slot_3, 43, 93);
        root.add(cast_inv_slot_4, 61, 93);
        root.add(cast_inv_slot_5, 79, 93);
        root.add(cast_inv_slot_6, 97, 93);
        root.add(cast_inv_slot_7, 115, 93);
        root.add(cast_inv_slot_8, 133, 93);
        root.add(cast_inv_slot_9, 151, 93);
        root.add(cast_inv_slot_10, 7, 111);
        root.add(cast_inv_slot_11, 25, 111);
        root.add(cast_inv_slot_12, 43, 111);
        root.add(cast_inv_slot_13, 61, 111);
        root.add(cast_inv_slot_14, 79, 111);
        root.add(cast_inv_slot_15, 97, 111);
        root.add(cast_inv_slot_16, 115, 111);
        root.add(cast_inv_slot_17, 133, 111);
        root.add(cast_inv_slot_18, 151, 111);

        root.add(littleArrowDown, 29, 42, 9, 8);
        root.add(bucketBackground, 28, 16, 12, 14);
        root.add(castBackground, 138, 40, 8, 9);

        root.add(fluidBackground, 68, 14, 18, 63);
        root.add(fluidTube, 42, 26, 27, 20);

        root.add(this.createPlayerInventoryPanel(), 7, 138);

        root.validate(this);
    }

    @Override
    public boolean canUse(PlayerEntity entity)
    {
        return canUse(this.context, entity, ModBlocks.SMELTERY);
    }

    public void setFluid(FluidStack stack)
    {
        fluidStack = stack;
    }
}