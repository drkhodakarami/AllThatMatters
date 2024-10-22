package jiraiyah.allthatmatters.block.entity;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.block.custom.GemCleanserBlock;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.recipe.custom.SmelteryRecipe;
import jiraiyah.allthatmatters.screen.handler.SmelteryScreenHandler;
import jiraiyah.allthatmatters.utils.ModTags;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SmelteryBE extends BEWithInventory implements PropertyDelegateHolder
{
    public static final int TOTAL_SLOTS = 23;

    public static final int BASE_INPUT_SLOT = 0;
    public static final int BASE_OUTPUT_SLOT = 1;
    public static final int FLUID_INPUT_SLOT = 2;
    public static final int FLUID_OUTPUT_SLOT = 3;
    public static final int CAST_SLOT = 4;

    public static final int CAST_INV_SLOT_1 = 5;
    public static final int CAST_INV_SLOT_2 = 6;
    public static final int CAST_INV_SLOT_3 = 7;
    public static final int CAST_INV_SLOT_4 = 8;
    public static final int CAST_INV_SLOT_5 = 9;
    public static final int CAST_INV_SLOT_6 = 10;
    public static final int CAST_INV_SLOT_7 = 11;
    public static final int CAST_INV_SLOT_8 = 12;
    public static final int CAST_INV_SLOT_9 = 13;
    public static final int CAST_INV_SLOT_10 = 14;
    public static final int CAST_INV_SLOT_11 = 15;
    public static final int CAST_INV_SLOT_12 = 16;
    public static final int CAST_INV_SLOT_13 = 17;
    public static final int CAST_INV_SLOT_14 = 18;
    public static final int CAST_INV_SLOT_15 = 19;
    public static final int CAST_INV_SLOT_16 = 20;
    public static final int CAST_INV_SLOT_17 = 21;
    public static final int CAST_INV_SLOT_18 = 22;

    public static final int DELEGATE_SIZE = 2;

    public static long FLUID_CAPACITY = FluidConstants.BUCKET * 20; // 20k mb

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

    public SmelteryBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.SMELTERY, pos, state);
        this.propertyDelegate = new PropertyDelegate()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> SmelteryBE.this.progress;
                    case 1 -> SmelteryBE.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> SmelteryBE.this.progress = value;
                    case 1 -> SmelteryBE.this.maxProgress = value;
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

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        sendFluidPacket();
        return new SmelteryScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos));
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

        // RIGHT --> TOOL SLOTS
        // LEFT --> LIQUIDATION SLOT

        return switch (localDir)
        {
            default -> side.getOpposite() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side.getOpposite() == Direction.EAST && slot == CAST_SLOT && StackAcceptableInSlot(stack, slot) || //RIGHT
                    side.getOpposite() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot); // LEFT
            case EAST -> side.rotateYClockwise() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side.rotateYClockwise() == Direction.EAST && slot == CAST_SLOT && StackAcceptableInSlot(stack, slot) || //RIGHT
                    side.rotateYClockwise() == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot); // LEFT
            case SOUTH -> side == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side == Direction.EAST && slot == CAST_SLOT && StackAcceptableInSlot(stack, slot) || //RIGHT
                    side == Direction.WEST && slot == FLUID_INPUT_SLOT && StackAcceptableInSlot(stack, slot); // LEFT
            case WEST -> side.rotateYCounterclockwise() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot) || // TOP
                    side.rotateYCounterclockwise() == Direction.EAST && slot == CAST_SLOT && StackAcceptableInSlot(stack, slot) || //RIGHT
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
        nbt.putInt("atm.smeltery.progress", progress);
        nbt.put("atm.smeltery.fluid_variant", fluidStorage.variant.toNbt());
        nbt.putLong("atm.smeltery.fluid_level", fluidStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("atm.smeltery.progress");
        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("atm.smeltery.fluid_variant"));
        fluidStorage.amount = nbt.getLong("atm.smeltery.fluid_level");
    }

    @Override
    protected int totalSlots()
    {
        return TOTAL_SLOTS;
    }

    @Override
    protected void handleFluidTick(World world, BlockPos pos, BlockState state)
    {
        FluidUtils.handleTankTransfer(world, pos, this, this.fluidStorage, FLUID_INPUT_SLOT, FLUID_OUTPUT_SLOT);
    }

    @Override
    protected void handleItemCraftingTick(World world, BlockPos pos, BlockState state)
    {
        if (FluidUtils.isOutputReceivable(this, BASE_OUTPUT_SLOT))
        {
            if (this.hasRecipe())
            {
                if(this.hasEnoughIngredient(BASE_INPUT_SLOT))
                {
                    var recipe = getCurrentRecipe().get().value();
                    this.maxProgress = recipe.getCraftTime();

                    if (this.shouldUseFluid() &&
                            this.hasEnoughFluid(this.fluidStorage, recipe.getFluidAmount()) &&
                            this.fluidIsAcceptable(this.fluidStorage, Fluids.LAVA))
                    {
                        this.increaseCraftProgress();
                        if (hasCraftingFinished())
                        {
                            this.useFluid(this.fluidStorage, Fluids.LAVA, recipe.getFluidAmount());
                            this.craftItem();
                            this.resetProgress();
                        }
                        markDirty(world, pos, state);
                    }
                }
            }
            else
                this.resetProgress();
        }
        else
        {
            if (this.progress != 0)
            {
                this.resetProgress();
                markDirty(world, pos, state);
            }
        }
    }

    private boolean hasEnoughIngredient(int baseInputSlot)
    {
        if(getStack(CAST_SLOT).isOf(ModItems.CAST_PLATE))
            return getStack(baseInputSlot).getCount() >= 1; // TODO
        return getStack(baseInputSlot).getCount() >= 1;
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

    @Override
    protected boolean shouldUseFluid()
    {
        return true;
    }

    //region PRIVATE METHODS
    private boolean StackAcceptableInSlot(ItemStack stack, int slot)
    {
        if (slot == BASE_INPUT_SLOT)
            return StackIsRawOre(stack);
        if (slot == FLUID_INPUT_SLOT)
            return StackIsLiquidable(stack);
        return false;
    }

    private boolean StackIsLiquidable(ItemStack stack)
    {
        return stack.isOf(Items.LAVA_BUCKET);
    }

    private boolean StackIsRawOre(ItemStack stack)
    {
        return stack.isIn(ItemTags.COAL_ORES) ||
                stack.isIn(ItemTags.COPPER_ORES) ||
                stack.isIn(ItemTags.DIAMOND_ORES) ||
                stack.isIn(ItemTags.GOLD_ORES) ||
                stack.isIn(ItemTags.IRON_ORES) ||
                stack.isIn(ItemTags.EMERALD_ORES) ||
                stack.isIn(ItemTags.LAPIS_ORES) ||
                stack.isIn(ItemTags.REDSTONE_ORES) ||
                stack.isOf(Items.NETHER_QUARTZ_ORE) ||
                stack.isIn(ModTags.Items.SMELTERY)||
                stack.isIn(ItemTags.WOOL) ||
                stack.isIn(ItemTags.PLANKS) ||
                stack.isIn(ModTags.Items.PLATE) ||
                stack.isIn(ItemTags.STONE_CRAFTING_MATERIALS) ||
                stack.isIn(ItemTags.STONE_TOOL_MATERIALS);
    }

    private void sendFluidPacket()
    {
        PacketByteBuf data = PacketByteBufs.create();
        fluidStorage.variant.toPacket(data);
        data.writeLong(fluidStorage.amount);
        data.writeBlockPos(getPos());
        ModMessages.sendToClientPlayerEntities(world, getPos(), ModMessages.SMELTERY_FLUID_SYNC, data);
    }

    private boolean hasRecipe()
    {
        var recipe = getCurrentRecipe();

        if (recipe.isEmpty())
            return false;

        var recipePresent = recipe.isPresent();

        if(!recipePresent)
            return false;

        var amountAcceptable = canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null), BASE_OUTPUT_SLOT);
        var itemAcceptable = canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem(), BASE_OUTPUT_SLOT);

        return amountAcceptable && itemAcceptable;
    }

    private Optional<RecipeEntry<SmelteryRecipe>> getCurrentRecipe()
    {
        return getWorld().getRecipeManager().getFirstMatch(ModRecipes.SMELTERY_TYPE, getSimpleInventory(), getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item, int slotIndex)
    {
        return this.getStack(slotIndex).getItem() == item || this.getStack(slotIndex).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result, int slotIndex)
    {
        return this.getStack(slotIndex).getCount() + result.getCount() <= getStack(slotIndex).getMaxCount();
    }

    private void craftItem()
    {
        var recipe = getCurrentRecipe().get().value();

        this.removeStack(BASE_INPUT_SLOT, recipe.getIngredientCount());

        if(getStack(CAST_SLOT).isIn(ModTags.Items.WOOD_CASTS))
            this.removeStack(CAST_SLOT, 1);

        this.setStack(BASE_OUTPUT_SLOT, new ItemStack(recipe.getResult(null).getItem(),
                getStack(BASE_OUTPUT_SLOT).getCount() + recipe.getResult(null).getCount()));
    }
    //endregion
}