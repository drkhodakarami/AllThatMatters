package jiraiyah.allthatmatters.screen.handler;

import jiraiyah.allthatmatters.block.entity.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.utils.slot.FluidInputSlot;
import jiraiyah.allthatmatters.utils.slot.FluidOutputSlot;
import jiraiyah.fluidutils.FluidStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class EnderiteShulkerScreenHandler extends ScreenHandler
{
    private final Inventory inventory;
    private final int rows, columns;

    public EnderiteShulkerBlockEntity loaderEntity;
    public FluidStack leftFluidStack;
    public FluidStack rightFluidStack;

    public EnderiteShulkerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, BlockEntity blockEntity)
    {
        this(ModScreenHandlers.ENDERITE_SCREEN_HANDLER_TYPE, syncId, playerInventory, inventory, blockEntity);
    }

    public EnderiteShulkerScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf)
    {
        this(syncId, playerInventory, new SimpleInventory(buf.readInt()), playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()));
    }

    protected EnderiteShulkerScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, BlockEntity blockEntity)
    {
        super(type, syncId);

        loaderEntity = (EnderiteShulkerBlockEntity) blockEntity;
        this.leftFluidStack = new FluidStack(loaderEntity.leftFluidStorage.variant,
                loaderEntity.leftFluidStorage.amount);
        this.rightFluidStack = new FluidStack(loaderEntity.rightFluidStorage.variant,
                loaderEntity.rightFluidStorage.amount);

        if (inventory.size() - 4 <= 81)
            columns = 9;
        else
            columns = 12;
        rows = (inventory.size() - 4) / columns;
        checkSize(inventory, rows * 9);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        int offsetX = 8;
        for (int row = 0; row < this.rows; ++row)
        {
            for (int collumn = 0; collumn < columns; ++collumn)
            {
                this.addSlot(new ShulkerBoxSlot(inventory, collumn + (row * columns), offsetX + collumn * 18, 18 + (row * 18)));
            }
        }

        int largeInvXOffset = 0;
        if (columns == 12)
            largeInvXOffset = 1;

        int offsetY = 18 + (rows * 18) + 14;
        if (columns > 9)
            offsetX = 34;
        for (int row = 0; row < 3; ++row)
            for (int collumn = 0; collumn < 9; ++collumn)
                this.addSlot(new Slot(playerInventory, collumn + row * 9 + 9, largeInvXOffset + offsetX + collumn * 18, offsetY + (row * 18)));

        offsetY += (3 * 18) + 4;
        for (int row = 0; row < 9; ++row)
            this.addSlot(new Slot(playerInventory, row, largeInvXOffset + offsetX + row * 18, offsetY));

        this.addSlot(new FluidInputSlot(inventory, EnderiteShulkerBlockEntity.LEFT_FLUID_INPUT_SLOT, 16, 194));
        this.addSlot(new FluidInputSlot(inventory, EnderiteShulkerBlockEntity.RIGHT_FLUID_INPUT_SLOT, 198, 194));

        this.addSlot(new FluidOutputSlot(inventory, EnderiteShulkerBlockEntity.LEFT_FLUID_OUTPUT_SLOT, 16, 230));
        this.addSlot(new FluidOutputSlot(inventory, EnderiteShulkerBlockEntity.RIGHT_FLUID_OUTPUT_SLOT, 198, 230));
    }

    public static ExtendedScreenHandlerFactory createScreenHandlerFactory(Inventory inventory, Text text, BlockEntity blockEntity)
    {
        return new ExtendedScreenHandlerFactory()
        {
            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
            {
                ((EnderiteShulkerBlockEntity)blockEntity).sendFluidPacket();
                return new EnderiteShulkerScreenHandler(syncId, playerInventory, inventory, blockEntity);
            }

            @Override
            public Text getDisplayName()
            {
                return text;
            }

            @Override
            public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
            {
                buf.writeInt(inventory.size());
                buf.writeBlockPos(blockEntity.getPos());
            }
        };
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack())
        {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index < this.rows * this.columns)
            {
                if (!this.insertItem(itemStack2, this.rows * this.columns, this.slots.size(), true))
                    return ItemStack.EMPTY;
            }
            else if (!this.insertItem(itemStack2, 0, this.rows * columns, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty())
                slot.setStack(ItemStack.EMPTY);
            else
                slot.markDirty();
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void onClosed(PlayerEntity player)
    {
        super.onClosed(player);
        this.inventory.onClose(player);
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    @Override
    public ScreenHandlerType<?> getType()
    {
        return ModScreenHandlers.ENDERITE_SCREEN_HANDLER_TYPE;
    }

    @Environment(EnvType.CLIENT)
    public int getRows()
    {
        return this.rows;
    }

    @Environment(EnvType.CLIENT)
    public int getColumns()
    {
        return this.columns;
    }

    public void setFluid(FluidStack left, FluidStack right)
    {
        this.leftFluidStack = left;
        this.rightFluidStack = right;
    }
}