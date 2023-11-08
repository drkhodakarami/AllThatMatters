package jiraiyah.allthatmatters.block.entity.custom;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.block.custom.InfusingStationBlock;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.recipe.custom.InfusingStationCraftingRecipe;
import jiraiyah.allthatmatters.screen.handler.InfusingStationScreenHandler;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import jiraiyah.allthatmatters.utils.interfaces.ImplementedInventory;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Optional;


public class InfusingStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory
{
    public static int ENERGY_CAPACITY = 10000;
    public static long FLUID_CAPACITY = FluidStack.convertDropletsToMb(FluidConstants.BLOCK) * 20; // 20k mb
    public static final int ENERGY_PER_TICK = 10;
    public static final int FLUID_PER_CRAFT = 125; //mb amount

    public static final int DELEGATE_SIZE = 4;

    public static final int TOTAL_SLOTS = 9;

    public static final int BASE_INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int FLUID_INPUT_SLOT = 2;
    public static final int FLUID_OUTPUT_SLOT = 3;
    public static final int FLUID_UPGRADE_SLOT = 4;
    public static final int MAIN_TOOL_SLOT = 5;
    public static final int SECOND_TOOL_SLOT = 6;
    public static final int THIRD_TOOL_SLOT = 7;
    public static final int ENERGY_UPGRADE_SLOT = 8;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(TOTAL_SLOTS, ItemStack.EMPTY);

    // Coal -> 4000 E
    // Capacity = 4 stacks of coal
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(ENERGY_CAPACITY, 32, 32)
    {
        @Override
        protected void onFinalCommit()
        {
            markDirty();
            if(!world.isClient())
            {
                sendEnergyPacket();
            }
        }
    };

    //region FLUID STORAGE
    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<FluidVariant>()
    {
        @Override
        protected FluidVariant getBlankVariant()
        {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant)
        {
            return FLUID_CAPACITY;
        }

        @Override
        protected void onFinalCommit()
        {
            markDirty();

            if(!world.isClient())
            {
                sendFluidPacket();
            }
        }
    };
    //endregion

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int liquidProgress = 0;
    private int maxLiquidProgress = 144;

    public InfusingStationBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.INFUSING_STATION_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> InfusingStationBlockEntity.this.progress;
                    case 1 -> InfusingStationBlockEntity.this.maxProgress;
                    case 2 -> InfusingStationBlockEntity.this.liquidProgress;
                    case 3 -> InfusingStationBlockEntity.this.maxLiquidProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> InfusingStationBlockEntity.this.progress = value;
                    case 1 -> InfusingStationBlockEntity.this.maxProgress = value;
                    case 2 -> InfusingStationBlockEntity.this.liquidProgress = value;
                    case 3 -> InfusingStationBlockEntity.this.maxLiquidProgress = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    //region OVERRIDES
    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
    {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("allthatmatters.infusing_station");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        sendEnergyPacket();
        sendFluidPacket();
        return new InfusingStationScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(InfusingStationBlock.FACING);

        // TOP --> RAW SLOT

        if(side == Direction.UP)
            return slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) ;
        if(side == Direction.DOWN)
            return false;

        // RIGHT --> TOOL SLOTS
        // LEFT --> LIQUIDATION SLOT

        return switch (localDir)
        {
            default ->
                    side.getOpposite() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                            side.getOpposite() == Direction.EAST && slot == MAIN_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.getOpposite() == Direction.EAST && slot == SECOND_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.getOpposite() == Direction.EAST && slot == THIRD_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.getOpposite() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot) ; // LEFT
            case EAST ->
                    side.rotateYClockwise() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot)  || // TOP
                            side.rotateYClockwise() == Direction.EAST && slot == MAIN_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.rotateYClockwise() == Direction.EAST && slot == SECOND_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.rotateYClockwise() == Direction.EAST && slot == THIRD_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.rotateYClockwise() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot) ; // LEFT
            case SOUTH ->
                    side == Direction.NORTH &&  slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot)  || // TOP
                            side == Direction.EAST &&  slot == MAIN_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side == Direction.EAST &&  slot == SECOND_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side == Direction.EAST &&  slot == THIRD_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot) ; // LEFT
            case WEST ->
                    side.rotateYCounterclockwise() == Direction.NORTH &&  slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot)  || // TOP
                            side.rotateYCounterclockwise() == Direction.EAST &&  slot == MAIN_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.rotateYCounterclockwise() == Direction.EAST &&  slot == SECOND_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.rotateYCounterclockwise() == Direction.EAST &&  slot == THIRD_TOOL_SLOT && StackAcceptableInSlot(stack, slot)  || //RIGHT
                            side.rotateYCounterclockwise() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot) ; // LEFT
        };

    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(InfusingStationBlock.FACING);
        if(side == Direction.DOWN)
            return slot == OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT;

        return switch (localDir)
        {
            default -> side.getOpposite() == Direction.SOUTH && (slot == OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
            case EAST -> side.rotateYClockwise() == Direction.SOUTH && (slot == OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
            case SOUTH -> side == Direction.SOUTH && (slot == OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
            case WEST -> side.rotateYCounterclockwise() == Direction.SOUTH && (slot == OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
        };
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("atm.infusing_station.progress", progress);
        nbt.putInt("atm.infusing_station.liquid_progress", liquidProgress);
        nbt.putLong("atm.infusing_station.energy", energyStorage.amount);
        nbt.put("atm.infusing_station.fluid_variant", fluidStorage.variant.toNbt());
        nbt.putLong("atm.infusing_station.fluid_level", fluidStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("atm.infusing_station.progress");
        liquidProgress = nbt.getInt("atm.infusing_station.liquid_progress");
        energyStorage.amount = nbt.getLong("atm.infusing_station.energy");
        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("atm.infusing_station.fluid_variant"));
        fluidStorage.amount = nbt.getLong("atm.infusing_station.fluid_level");
    }

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
    //endregion

    public void tick(World world, BlockPos pos, BlockState state)
    {
        if(world.isClient())
            return;

        handleFluidTick(world, pos, state);
        handleEnergyCreationTick(world, pos, state);
        handleItemCraftingTick(world, pos, state);
    }

    //region TICK HANDLIGN METHODS
    private void handleFluidTick(World world, BlockPos pos, BlockState state)
    {
        if(!this.isTankEmpty() && isLiquidOutputReceivable())
        {
            if(this.isItemStackEmptyBucket())
            {
                this.transferFluidFromFluidTank();
                this.resetLiquidProgress();
                markDirty(world, pos, state);
            }
        }
        if((this.isTankEmpty() || this.isTankReceivable()) && isLiquidOutputReceivable())
        {
            if(this.hasFluidSourceInSlot())
            {
                if(this.isItemStackCompatibleWithTank())
                {
                    if(this.isItemStackLiquidBucket())
                    {
                        this.transferFluidToFluidTank();
                        this.resetLiquidProgress();
                        markDirty(world, pos, state);
                    }
                    else
                    {
                        this.increaseFluidProgress();
                        if (isLiquidationFinished())
                        {
                            this.transferFluidToFluidTank();
                            this.resetLiquidProgress();
                        }
                        markDirty(world, pos, state);
                    }
                }
            }
        }
        else
        {
            if(this.liquidProgress != 0)
            {
                this.resetLiquidProgress();
                markDirty(world, pos, state);
            }
        }
    }

    private void handleEnergyCreationTick(World world, BlockPos pos, BlockState state)
    {
        /*if((this.isCapacitorEmpty() || this.isCapacitorReceivable()) &&
                isEnergyOutputReceivable())
        {
            if(this.hasEnergySourceInSlot())
            {
                // check if fuel source is battery
                // check if batter still has charge
                // reduce energy from battery and add to capacitor
                // if battery is empty, put to output slot

                // if fuel is not battery
                // burn the fuel
                // start fueling timer
                // check if timer is greater than zero
                // give energy to capacitor
                markDirty(world, pos, state);
            }
            if(!this.isCapacitorEmpty() && isEnergyChargingOutputReceivable())
            {
                // check if input is battery and can charge
                // charge the battery
                // check if battery if full
                // if full, put to output slot
                markDirty(world, pos, state);
            }
        }*/
    }

    private void handleItemCraftingTick(World world, BlockPos pos, BlockState state)
    {
        if(this.isOutputSlotEmptyOrReceivable())
        {
            if(this.hasRecipe())
            {
                if(this.shouldUseEnergy() && this.hasEnoughEnergy() &&
                        this.shouldUseFluid() && this.hasEnoughFluid() && this.fluidIsAcceptable())
                {
                    this.increaseCraftProgress();
                    this.extractEnergy();
                    if (hasCraftingFinished())
                    {
                        this.useFluid();
                        this.craftItem();
                        this.resetProgress();
                    }
                    markDirty(world, pos, state);
                }
                else if(!this.shouldUseEnergy() && this.shouldUseFluid() && this.hasEnoughFluid() && this.fluidIsAcceptable())
                {
                    this.increaseCraftProgress();
                    if (hasCraftingFinished())
                    {
                        this.useFluid();
                        this.craftItem();
                        this.resetProgress();
                    }
                    markDirty(world, pos, state);
                }
                else if (!this.shouldUseFluid() && this.shouldUseEnergy() && this.hasEnoughEnergy())
                {
                    this.increaseCraftProgress();
                    this.extractEnergy();
                    if (hasCraftingFinished())
                    {
                        this.craftItem();
                        this.resetProgress();
                    }
                    markDirty(world, pos, state);
                }
                else if (!this.shouldUseFluid() && !this.shouldUseEnergy())
                {
                    this.increaseCraftProgress();
                    if (hasCraftingFinished())
                    {
                        this.craftItem();
                        this.resetProgress();
                    }
                    markDirty(world, pos, state);
                }
            }
            else
            {
                this.resetProgress();
            }
        }
        else
        {
            if(this.progress != 0)
            {
                this.resetProgress();
                markDirty(world, pos, state);
            }
        }
    }
    //endregion

    //region ITEM INFUSING / CRAFTING
    private boolean isOutputSlotEmptyOrReceivable()
    {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    private void resetProgress()
    {
        this.progress = 0;
    }

    private boolean hasCraftingFinished()
    {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress()
    {
        progress++;
    }

    private boolean hasRecipe()
    {
        var recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private void craftItem()
    {
        var recipe = getCurrentRecipe();

        this.removeStack(BASE_INPUT_SLOT, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    // private Optional<? extends RecipeEntry<? extends Recipe<?>>> getCurrentRecipe()
    // private Optional<?> getCurrentRecipe()
    // Both forces you to cast later at usage
    private Optional<RecipeEntry<InfusingStationCraftingRecipe>> getCurrentRecipe()
    {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++)
        {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(ModRecipes.INFUSING_STATION_TYPE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item)
    {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result)
    {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }
    //endregion

    //region ENERGY HANDLING
    private boolean hasEnergyItem()
    {
        return getStack(FLUID_INPUT_SLOT).isOf(Items.COAL);
    }

    private boolean shouldUseEnergy()
    {
        // TODO : Handle energy creation and transfer
        return true;
    }

    private boolean hasEnoughEnergy()
    {
        return energyStorage.amount >= ENERGY_PER_TICK;
    }

    private void extractEnergy()
    {
        try(Transaction transaction = Transaction.openOuter())
        {
            energyStorage.extract(ENERGY_PER_TICK, transaction);
            transaction.commit();
        }
    }

    public void setEnergyLevel(long amount)
    {
        this.energyStorage.amount = amount;
    }

    private void sendEnergyPacket()
    {
        PacketByteBuf data = PacketByteBufs.create();
        data.writeLong(energyStorage.amount);
        data.writeBlockPos(getPos());
        ModMessages.sendToServerPlayerEntities(world, getPos(), ModMessages.INFUSING_STATION_ENERGY_SYNC, data);
    }
    //endregion

    //region FLUID HANDLING
    public void setFluidLevel(FluidVariant fluidVariant, long fluidLevel)
    {
        this.fluidStorage.variant = fluidVariant;
        this.fluidStorage.amount = fluidLevel;
    }

    private boolean shouldUseFluid()
    {
        // TODO : Take note maybe some recipe won't use fluid in future
        return true;
    }

    private boolean hasEnoughFluid()
    {
        return this.fluidStorage.amount >= FLUID_PER_CRAFT;
    }

    private boolean fluidIsAcceptable()
    {
        if(getCurrentRecipe().get().value().getType() == ModRecipes.INFUSING_STATION_TYPE)
            return fluidStorage.variant.isOf(Fluids.WATER);

        return false;
    }

    private boolean isTankReceivable()
    {
        return this.fluidStorage.amount <= this.fluidStorage.getCapacity() - 1000;
    }

    private boolean isLiquidOutputReceivable()
    {
        return getStack(FLUID_INPUT_SLOT).isOf(ModItems.ENDERITE) ||
                getStack(FLUID_OUTPUT_SLOT).isEmpty() ||
                getStack(FLUID_OUTPUT_SLOT).getCount() < getStack(FLUID_OUTPUT_SLOT).getMaxCount();
    }

    private boolean isTankEmpty()
    {
        return this.fluidStorage.amount == 0;
    }

    private boolean hasFluidSourceInSlot()
    {
        return getStack(FLUID_INPUT_SLOT).isOf(ModItems.ENDERITE) ||
                getStack(FLUID_INPUT_SLOT).isOf(ModFluids.MOLTEN_ENDERITE_BUCKET) ||
                getStack(FLUID_INPUT_SLOT).isOf(Items.WATER_BUCKET) ||
                getStack(FLUID_INPUT_SLOT).isOf(Items.LAVA_BUCKET);
    }

    private boolean isItemStackLiquidBucket()
    {
        return getStack(FLUID_INPUT_SLOT).isOf(ModFluids.MOLTEN_ENDERITE_BUCKET) ||
                getStack(FLUID_INPUT_SLOT).isOf(Items.WATER_BUCKET) ||
                getStack(FLUID_INPUT_SLOT).isOf(Items.LAVA_BUCKET);
    }

    private boolean isItemStackEmptyBucket()
    {
        return getStack(FLUID_INPUT_SLOT).isOf(Items.BUCKET);
    }

    private boolean isItemStackCompatibleWithTank()
    {
        return (getStack(FLUID_INPUT_SLOT).isOf(ModItems.ENDERITE) &&
                (this.fluidStorage.variant.isOf(ModFluids.STILL_MOLTEN_ENDERITE) ||
                        this.fluidStorage.amount == 0))||
                (getStack(FLUID_INPUT_SLOT).isOf(ModFluids.MOLTEN_ENDERITE_BUCKET) &&
                        (this.fluidStorage.variant.isOf(ModFluids.STILL_MOLTEN_ENDERITE) ||
                                this.fluidStorage.amount == 0))||
                (getStack(FLUID_INPUT_SLOT).isOf(Items.WATER_BUCKET) &&
                        (this.fluidStorage.variant.isOf(Fluids.WATER) ||
                                this.fluidStorage.amount == 0))||
                (getStack(FLUID_INPUT_SLOT).isOf(Items.LAVA_BUCKET) &&
                        (this.fluidStorage.variant.isOf(Fluids.LAVA) ||
                                this.fluidStorage.amount == 0));
    }

    private void transferFluidToFluidTank()
    {
        if(getStack(FLUID_INPUT_SLOT).isOf(ModFluids.MOLTEN_ENDERITE_BUCKET))
        {
            insertFluid(ModFluids.STILL_MOLTEN_ENDERITE, true);
            return;
        }
        if(getStack(FLUID_INPUT_SLOT).isOf(Items.WATER_BUCKET))
        {
            insertFluid(Fluids.WATER, true);
            return;
        }
        if(getStack(FLUID_INPUT_SLOT).isOf(Items.LAVA_BUCKET))
        {
            insertFluid(Fluids.LAVA, true);
            return;
        }
        if(getStack(FLUID_INPUT_SLOT).isOf(ModItems.ENDERITE))
        {
            insertFluid(ModFluids.STILL_MOLTEN_ENDERITE, false);
        }
    }

    private void transferFluidFromFluidTank()
    {
        if(fluidStorage.variant.isOf(ModFluids.STILL_MOLTEN_ENDERITE))
        {
            extractFluid(ModFluids.STILL_MOLTEN_ENDERITE, ModFluids.MOLTEN_ENDERITE_BUCKET);
            return;
        }
        if(fluidStorage.variant.isOf(Fluids.WATER))
        {
            extractFluid(Fluids.WATER, Items.WATER_BUCKET);
            return;
        }
        if(fluidStorage.variant.isOf(Fluids.LAVA))
        {
            extractFluid(Fluids.LAVA, Items.LAVA_BUCKET);
        }
    }

    private void insertFluid(FlowableFluid fluid, boolean giveBucket)
    {
        try (Transaction transaction = Transaction.openOuter())
        {

            this.fluidStorage.insert(FluidVariant.of(fluid),
                    FluidStack.convertDropletsToMb(FluidConstants.BLOCK), transaction);
            transaction.commit();

            this.removeStack(FLUID_INPUT_SLOT, 1);
            if(giveBucket)
                this.setStack(FLUID_OUTPUT_SLOT, new ItemStack(Items.BUCKET, getStack(FLUID_OUTPUT_SLOT).getCount() + 1));
        }
    }

    private void extractFluid(FlowableFluid fluid, Item item)
    {
        try (Transaction transaction = Transaction.openOuter())
        {
            this.fluidStorage.extract(FluidVariant.of(fluid),
                    FluidStack.convertDropletsToMb(FluidConstants.BLOCK), transaction);
            transaction.commit();

            this.removeStack(FLUID_INPUT_SLOT, 1);

            this.setStack(FLUID_OUTPUT_SLOT, new ItemStack(item, getStack(FLUID_OUTPUT_SLOT).getCount() + 1));
        }
    }

    private void useFluid()
    {
        if(getCurrentRecipe().get().value().getType() == ModRecipes.INFUSING_STATION_TYPE)
        {
            try (Transaction transaction = Transaction.openOuter())
            {
                this.fluidStorage.extract(FluidVariant.of(Fluids.WATER),
                        FLUID_PER_CRAFT, transaction);
                transaction.commit();
            }
        }
    }

    private void increaseFluidProgress()
    {
        liquidProgress++;
    }

    private boolean isLiquidationFinished()
    {
        return liquidProgress >= maxLiquidProgress;
    }

    private void resetLiquidProgress()
    {
        liquidProgress = 0;
    }

    private void sendFluidPacket()
    {
        PacketByteBuf data = PacketByteBufs.create();
        fluidStorage.variant.toPacket(data);
        data.writeLong(fluidStorage.amount);
        data.writeBlockPos(getPos());
        ModMessages.sendToServerPlayerEntities(world, getPos(), ModMessages.INFUSING_STATION_FLUID_SYNC, data);
    }
    //endregion

    //region HELPER METHODS
    public ItemStack getRenderStack()
    {
        if(this.getStack(OUTPUT_SLOT).isEmpty())
            return this.getStack(BASE_INPUT_SLOT);
        return this.getStack(OUTPUT_SLOT);
    }

    private boolean StackAcceptableInSlot(ItemStack stack, int slot)
    {
        if(slot == BASE_INPUT_SLOT)
            return StackIsRawGem(stack);
        if(slot == MAIN_TOOL_SLOT || slot == SECOND_TOOL_SLOT || slot == THIRD_TOOL_SLOT)
            return StackIsTool(stack);
        if(slot == FLUID_INPUT_SLOT)
            return StackIsLiquidable(stack);
        return false;
    }

    private boolean StackIsLiquidable(ItemStack stack)
    {
        return stack.isOf(ModItems.ENDERITE) ||
                stack.isOf(Items.LAVA_BUCKET) ||
                stack.isOf(Items.WATER_BUCKET) ||
                stack.isOf(ModFluids.MOLTEN_ENDERITE_BUCKET);
    }

    private boolean StackIsTool(ItemStack stack)
    {
        // TODO : Add your item
        return stack.isOf(Items.GOLDEN_PICKAXE);
    }

    private boolean StackIsRawGem(ItemStack stack)
    {
        return stack.isOf(ModItems.RAW_SAPPHIRE) ||
                stack.isOf(ModItems.RAW_RUBY) ||
                stack.isOf(ModItems.RAW_CITRINE) ||
                stack.isOf(ModItems.RAW_ENDERITE);
    }
    //endregion
}