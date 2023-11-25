package jiraiyah.allthatmatters.block.entity;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.block.custom.GemCleanserBlock;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.screen.handler.GemCleanserScreenHandler;
import jiraiyah.allthatmatters.utils.block.entity.BEWithInventory;
import jiraiyah.fluidutils.FluidUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GemCleanserBE extends BEWithInventory implements PropertyDelegateHolder
{
    public static final int TOTAL_SLOTS = 4;

    public static final int BASE_INPUT_SLOT = 0;
    public static final int BASE_OUTPUT_SLOT = 1;
    public static final int FLUID_INPUT_SLOT = 2;
    public static final int FLUID_OUTPUT_SLOT = 3;

    public static final int DELEGATE_SIZE = 2;

    public static long FLUID_CAPACITY = FluidConstants.BUCKET * 20; // 20k mb

    public static final long FLUID_PER_CRAFT = FluidUtils.MILLI_BUCKET * 125; //mb amount

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

            if (!world.isClient())
                sendFluidPacket();
        }
    };

    protected final PropertyDelegate propertyDelegate;

    public GemCleanserBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEM_CLEANSER, pos, state);
        this.propertyDelegate = new PropertyDelegate()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> GemCleanserBE.this.progress;
                    case 1 -> GemCleanserBE.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> GemCleanserBE.this.progress = value;
                    case 1 -> GemCleanserBE.this.maxProgress = value;
                }
            }

            @Override
            public int size()
            {
                return 2;
            }
        };
    }

    @Override
    public PropertyDelegate getPropertyDelegate()
    {
        return this.propertyDelegate;
    }

    public ItemStack getRenderStack()
    {
        if (this.getStack(BASE_OUTPUT_SLOT).isEmpty())
            return this.getStack(BASE_INPUT_SLOT);
        return this.getStack(BASE_OUTPUT_SLOT);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        sendFluidPacket();
        return new GemCleanserScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(GemCleanserBlock.FACING);

        // TOP --> RAW SLOT

        if (side == Direction.UP)
            return slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot);
        if (side == Direction.DOWN)
            return false;

        // LEFT --> LIQUIDATION SLOT

        return switch (localDir)
        {
            default -> side.getOpposite() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side.getOpposite() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot); // LEFT
            case EAST -> side.rotateYClockwise() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side.rotateYClockwise() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot); // LEFT
            case SOUTH -> side == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot); // LEFT
            case WEST -> side.rotateYCounterclockwise() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side.rotateYCounterclockwise() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot); // LEFT
        };

    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(GemCleanserBlock.FACING);
        if (side == Direction.DOWN)
            return slot == BASE_OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT;

        return switch (localDir)
        {
            default -> side.getOpposite() == Direction.SOUTH && (slot == BASE_OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
            case EAST -> side.rotateYClockwise() == Direction.SOUTH && (slot == BASE_OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
            case SOUTH -> side == Direction.SOUTH && (slot == BASE_OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
            case WEST -> side.rotateYCounterclockwise() == Direction.SOUTH && (slot == BASE_OUTPUT_SLOT || slot == FLUID_OUTPUT_SLOT);
        };
    }

    @Override
    public void setFluidLevel(FluidVariant fluidVariant, long fluidLevel)
    {
        this.fluidStorage.variant = fluidVariant;
        this.fluidStorage.amount = fluidLevel;
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("atm.infusing_station.progress", progress);
        nbt.put("atm.infusing_station.fluid_variant", fluidStorage.variant.toNbt());
        nbt.putLong("atm.infusing_station.fluid_level", fluidStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("atm.infusing_station.progress");
        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("atm.infusing_station.fluid_variant"));
        fluidStorage.amount = nbt.getLong("atm.infusing_station.fluid_level");
    }

    @Override
    protected int totalSlots()
    {
        return TOTAL_SLOTS;
    }

    @Override
    protected void handleFluidTick(World world, BlockPos pos, BlockState state)
    {
        FluidUtils.handleTankTransfer(world,pos,this,this.fluidStorage,FLUID_INPUT_SLOT,FLUID_OUTPUT_SLOT);
    }

    @Override
    protected void handleItemCraftingTick(World world, BlockPos pos, BlockState state)
    {
        if (FluidUtils.isOutputReceivable(this, BASE_OUTPUT_SLOT))
            if (this.hasRecipe(ModRecipes.GEM_CLEANSE_TYPE, BASE_OUTPUT_SLOT))
            {
                if (this.shouldUseFluid() &&
                        this.hasEnoughFluid(this.fluidStorage, FLUID_PER_CRAFT) &&
                        this.fluidIsAcceptable(this.fluidStorage, Fluids.WATER))
                {
                    this.increaseCraftProgress();
                    if (hasCraftingFinished())
                    {
                        this.useFluid(this.fluidStorage, FLUID_PER_CRAFT);
                        this.craftItem(ModRecipes.GEM_CLEANSE_TYPE, BASE_INPUT_SLOT, BASE_OUTPUT_SLOT);
                        this.resetProgress();
                    }
                    markDirty(world, pos, state);
                }
            }
            else
                this.resetProgress();
        else
        {
            if (this.progress != 0)
            {
                this.resetProgress();
                markDirty(world, pos, state);
            }
        }
    }

    @Override
    protected <C extends Inventory, T extends Recipe<C>> void craftItem(RecipeType<T> type, int inputSlot, int outputSlot)
    {
        var recipe = getCurrentRecipe(type);

        this.removeStack(inputSlot, recipe.get().value().getIngredients().get(0).getMatchingStacks()[0].getCount());

        this.setStack(outputSlot, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(outputSlot).getCount() + recipe.get().value().getResult(null).getCount()));

        //super.craftItem(type, inputSlot, outputSlot);
    }

    @Override
    protected void handleEnergyCreationTick(World world, BlockPos pos, BlockState state)
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

    /*protected boolean hasFluidSourceInSlot(int slotIndex, Item item)
    {
        return getStack(slotIndex).isOf(item);
    }*/

    @Override
    protected boolean shouldUseFluid()
    {
        return true;
    }

    //region PRIVATE METHODS
    private boolean StackAcceptableInSlot(ItemStack stack, int slot)
    {
        if (slot == BASE_INPUT_SLOT)
            return StackIsRawGem(stack);
        if (slot == FLUID_INPUT_SLOT)
            return StackIsLiquidable(stack);
        return false;
    }

    private boolean StackIsLiquidable(ItemStack stack)
    {
        return stack.isOf(Items.WATER_BUCKET);
    }

    private boolean StackIsRawGem(ItemStack stack)
    {
        return stack.isOf(ModItems.RAW_SAPPHIRE) ||
                stack.isOf(ModItems.RAW_RUBY) ||
                stack.isOf(ModItems.RAW_CITRINE) ||
                stack.isOf(ModItems.RAW_ENDERITE);
    }

    private void sendFluidPacket()
    {
        PacketByteBuf data = PacketByteBufs.create();
        fluidStorage.variant.toPacket(data);
        data.writeLong(fluidStorage.amount);
        data.writeBlockPos(getPos());
        ModMessages.sendToClientPlayerEntities(world, getPos(), ModMessages.GEM_CLEANSER_FLUID_SYNC, data);
    }
    //endregion
}