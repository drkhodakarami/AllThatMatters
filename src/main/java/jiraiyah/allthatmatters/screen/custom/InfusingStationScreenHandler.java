package jiraiyah.allthatmatters.screen.custom;

import jiraiyah.allthatmatters.block.entity.custom.InfusingStationBlockEntity;
import jiraiyah.allthatmatters.block.entity.slot.InfusingRawInputSlot;
import jiraiyah.allthatmatters.block.entity.slot.OutputSlot;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class InfusingStationScreenHandler extends ScreenHandler
{
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final InfusingStationBlockEntity blockEntity;

    public InfusingStationScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf)
    {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(InfusingStationBlockEntity.DELEGATRE_SIZE));
    }

    public InfusingStationScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.INFUSING_POLISHING_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), InfusingStationBlockEntity.TOTAL_SLOTS);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((InfusingStationBlockEntity) blockEntity);

        this.addSlot(new InfusingRawInputSlot(inventory, InfusingStationBlockEntity.RAW_INPUT_SLOT, 86, 15));
        this.addSlot(new OutputSlot(inventory, InfusingStationBlockEntity.OUTPUT_SLOT, 86, 60));
        this.addSlot(new OutputSlot(inventory, InfusingStationBlockEntity.LIQUID_INPUT_SLOT, 12, 15));
        this.addSlot(new OutputSlot(inventory, InfusingStationBlockEntity.UPGRADE_INPUT_SLOT, 12, 60));
        this.addSlot(new OutputSlot(inventory, InfusingStationBlockEntity.MAIN_TOOL_SLOT, 125, 15));
        this.addSlot(new OutputSlot(inventory, InfusingStationBlockEntity.SECOND_TOOL_SLOT, 125, 37));
        this.addSlot(new OutputSlot(inventory, InfusingStationBlockEntity.THIRD_TOOL_SLOT, 125, 60));


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot)
    {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory)
    {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory)
    {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    public boolean isCrafting()
    {
        return propertyDelegate.get(0) > 0;
    }

    public boolean isMeltingLiquid()
    {
        return propertyDelegate.get(2) > 0;
    }

    public int getScaledProgress()
    {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getLiquidScaledProgress()
    {
        int progress = this.propertyDelegate.get(2);
        int maxProgress = this.propertyDelegate.get(3);  // Max Progress
        int progressArrowSize = 20; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
}