package jiraiyah.allthatmatters.screen.handler;

import jiraiyah.allthatmatters.item.custom.BackpackItem;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.utils.data.BackpackInventory;
import jiraiyah.allthatmatters.utils.slot.BackpackSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Identifier;

import java.util.UUID;

import static jiraiyah.allthatmatters.AllThatMatters.identifier;

public class BackpackScreenHandler extends ScreenHandler
{
    public static final Identifier IDENTIFIER = identifier("generic_container");
    private final BackpackInventory inv;
    private final PlayerInventory playerInv;
    private final int width;
    private final int height;
    private final UUID uuid;

    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf)
    {
        this(syncId, playerInventory, packetByteBuf.readInt(), packetByteBuf.readInt(),
                packetByteBuf.readUuid(), Items.AIR.getDefaultStack());
    }

    public BackpackScreenHandler(int syncId, PlayerInventory playerInv, int width, int height, UUID uuid, ItemStack stack)
    {
        super(ModScreenHandlers.BACKPACK_SCREEN_HANDLER, syncId);
        this.inv = new BackpackInventory(stack, width * height);
        this.playerInv = playerInv;
        this.width = width;
        this.height = height;
        this.uuid = uuid;
        // Backpack inventory
        for (int n = 0; n < height; ++n) {
            for (int m = 0; m < width; ++m) {
                this.addSlot(new BackpackSlot(inv, m + n * width, 8 + m * 18, 18 + n * 18));
            }
        }

        // Player inventory
        for (int n = 0; n < 3; ++n) {
            for (int m = 0; m < 9; ++m) {
                this.addSlot(new Slot(playerInv, m + n * 9 + 9,
                        8 + (width * 18 - 162) / 2 + m * 18,
                        31 + (height + n) * 18));
            }
        }

        // Player hotbar
        for (int n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInv, n,
                    8 + (width * 18 - 162) / 2 + n * 18,
                    89 + height * 18));
        }

        this.inv.onOpen(playerInv.player);
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        if (player.getWorld().isClient) return true;

        var stack = inv.getHolderStack();
        var uuidMatch = BackpackItem.isUUIDMatch(stack, this.uuid);

        return !stack.isEmpty() && stack.getItem() instanceof BackpackItem && uuidMatch;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index)
    {
        var stack = ItemStack.EMPTY;
        var slot = this.slots.get(index);

        if (slot != null && slot.hasStack())
        {
            final var stack2 = slot.getStack();
            stack = stack2.copy();

            if (index < this.inv.size())
            {
                if (!this.insertItem(stack2, this.inv.size(), this.slots.size(), true))
                {
                    //this.inv.onClose(player);
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(stack2, 0, this.inv.size(), false))
            {
                //this.inv.onClose(player);
                return ItemStack.EMPTY;
            }

            if (stack2.isEmpty())
                slot.setStack(ItemStack.EMPTY);
            else
                slot.markDirty();
        }
        //this.inv.onClose(player);
        return stack;
    }

    @Override
    public void onSlotClick(int slotIndex, int button, SlotActionType type, PlayerEntity player)
    {
        if (slotIndex >= 0 && player.getInventory().selectedSlot + 27 + this.inv.size() == slotIndex) {
            if (type != SlotActionType.CLONE)
            {
                //this.inv.markDirty();
                return;
            }
        }
        //this.inv.markDirty();
        super.onSlotClick(slotIndex, button, type, player);
    }

    @Override
    public void onClosed(PlayerEntity player)
    {
        this.inv.onClose(player);
        super.onClosed(player);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}