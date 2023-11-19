package jiraiyah.allthatmatters.utils.block.entity;

import jiraiyah.fluidutils.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class BEWithInventory extends BlockEntity implements NamedScreenHandlerFactory, ExtendedScreenHandlerFactory, ImplementedInventory, InventoryProvider
{
    protected final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(totalSlots(), ItemStack.EMPTY);

    protected int progress = 0;
    protected int maxProgress = 72;

    public BEWithInventory(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public int getProgress()
    {
        return this.progress;
    }

    public int getMaxProgress()
    {
        return this.maxProgress;
    }

    protected abstract int totalSlots();

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
    {
        buf.writeBlockPos(pos);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public abstract ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player);

    @Override
    public void markDirty()
    {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt()
    {
        return createNbt();
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos)
    {
        return this;
    }

    public void tick(World world, BlockPos pos, BlockState state)
    {
        if (world.isClient())
            return;

        handleFluidTick(world, pos, state);
        handleEnergyCreationTick(world, pos, state);
        handleItemCraftingTick(world, pos, state);
    }

    public abstract void setFluidLevel(FluidVariant fluidVariant, long fluidLevel);

    public SimpleInventory getSimpleInventory()
    {
        var inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++)
            inv.setStack(i, this.getStack(i));
        return inv;
    }

    protected abstract void handleFluidTick(World world, BlockPos pos, BlockState state);
    protected abstract void handleEnergyCreationTick(World world, BlockPos pos, BlockState state);
    protected abstract void handleItemCraftingTick(World world, BlockPos pos, BlockState state);

    protected <C extends Inventory, T extends Recipe<C>> boolean hasRecipe(RecipeType<T> type, int outputSlot)
    {
        var recipe = getCurrentRecipe(type);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null), outputSlot)
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem(), outputSlot);
    }

    protected boolean shouldUseFluid()
    {
        return false;
    }

    protected boolean hasEnoughFluid(SingleVariantStorage<FluidVariant> tank, long amount)
    {
        return tank.amount >= amount;
    }

    protected boolean fluidIsAcceptable(SingleVariantStorage<FluidVariant> tank, FlowableFluid fluid)
    {
        return tank.variant.isOf(fluid);
    }

    protected void increaseCraftProgress()
    {
        this.progress++;
    }

    protected boolean hasCraftingFinished()
    {
        return this.progress >= this.maxProgress;
    }

    protected void resetProgress()
    {
        this.progress = 0;
    }

    protected void useFluid(SingleVariantStorage<FluidVariant> tank, long amount)
    {
        try (Transaction transaction = Transaction.openOuter())
        {
            tank.extract(FluidVariant.of(Fluids.WATER), amount, transaction);
            transaction.commit();
        }
    }

    protected <C extends Inventory, T extends Recipe<C>> void craftItem(RecipeType<T> type, int inputSlot, int outputSlot)
    {
        var recipe = getCurrentRecipe(type);

        this.removeStack(inputSlot, 1);

        this.setStack(outputSlot, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(outputSlot).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private boolean canInsertItemIntoOutputSlot(Item item, int slotIndex)
    {
        return this.getStack(slotIndex).getItem() == item || this.getStack(slotIndex).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result, int slotIndex)
    {
        return this.getStack(slotIndex).getCount() + result.getCount() <= getStack(slotIndex).getMaxCount();
    }

    private <C extends Inventory, T extends Recipe<C>> Optional<RecipeEntry<T>> getCurrentRecipe(RecipeType<T> type)
    {
        var inv = getSimpleInventory();

        return getWorld().getRecipeManager().getFirstMatch(type, (C)inv, getWorld());
    }
}