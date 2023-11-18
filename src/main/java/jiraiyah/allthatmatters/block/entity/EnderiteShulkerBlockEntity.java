package jiraiyah.allthatmatters.block.entity;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import jiraiyah.allthatmatters.utils.ModTags;
import jiraiyah.allthatmatters.utils.fluid.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

import static jiraiyah.allthatmatters.AllThatMatters.LOGGER;

public class EnderiteShulkerBlockEntity extends ShulkerBoxBlockEntity
{
    private final int[] AVAILABLE_SLOTS;

    public static int INVENTORY_SLOT_SIZE = 112;

    public static int LEFT_FLUID_INPUT_SLOT = 108;
    public static int LEFT_FLUID_OUTPUT_SLOT = 109;
    public static int RIGHT_FLUID_INPUT_SLOT = 110;
    public static int RIGHT_FLUID_OUTPUT_SLOT = 111;

    // NEW SECTION
    public static long FLUID_CAPACITY = FluidStack.convertDropletsToMb(FluidConstants.BLOCK) * 100; // 20k mb

    public final SingleVariantStorage<FluidVariant> leftFluidStorage = new SingleVariantStorage<FluidVariant>()
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
            {
                //sendFluidPacket();
            }
        }
    };

    public final SingleVariantStorage<FluidVariant> rightFluidStorage = new SingleVariantStorage<FluidVariant>()
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
            {
                //sendFluidPacket();
            }
        }
    };










    //


    //region SHULKER
    public EnderiteShulkerBlockEntity(@Nullable DyeColor color, BlockPos pos, BlockState state)
    {
        super(color, pos, state);
        this.AVAILABLE_SLOTS = IntStream.range(0, INVENTORY_SLOT_SIZE).toArray();
        this.setInvStackList(DefaultedList.ofSize(INVENTORY_SLOT_SIZE, ItemStack.EMPTY));
    }

    public EnderiteShulkerBlockEntity(BlockPos pos, BlockState state)
    {
        this(null, pos, state);
    }

    @Override
    public BlockEntityType<?> getType()
    {
        return ModBlockEntities.ENDERITE_SHULKER;
    }

    @Override
    public void readNbt(NbtCompound tag)
    {
        super.readNbt(tag);
        leftFluidStorage.variant = FluidVariant.fromNbt((NbtCompound) tag.get("atm.shulker_box.left_fluid_variant"));
        leftFluidStorage.amount = tag.getLong("atm.shulker_box.left_fluid_level");
        rightFluidStorage.variant = FluidVariant.fromNbt((NbtCompound) tag.get("atm.shulker_box.right_fluid_variant"));
        rightFluidStorage.amount = tag.getLong("atm.shulker_box.right_fluid_level");
    }

    @Override
    public void writeNbt(NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.put("atm.shulker_box.left_fluid_variant", leftFluidStorage.variant.toNbt());
        tag.putLong("atm.shulker_box.left_fluid_level", leftFluidStorage.amount);
        tag.put("atm.shulker_box.right_fluid_variant", rightFluidStorage.variant.toNbt());
        tag.putLong("atm.shulker_box.right_fluid_level", rightFluidStorage.amount);
    }

    @Override
    public int[] getAvailableSlots(Direction side)
    {
        return this.AVAILABLE_SLOTS;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory)
    {
        return null;
    }

    @Override
    protected Text getContainerName()
    {
        return Text.translatable("block.enderiteshulkers.shulker");
    }

    public void customTick(World world, BlockPos pos, BlockState state)
    {
        ShulkerBoxBlockEntity.tick(world, pos, state, this);
        handleFluidTick(world, pos, state);
    }
    //endregion


    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(EnderiteShulkerBoxBlock.FACING);

        if (side == Direction.UP)
            return slot != LEFT_FLUID_INPUT_SLOT && slot != LEFT_FLUID_OUTPUT_SLOT &&
                    slot != RIGHT_FLUID_INPUT_SLOT && slot != RIGHT_FLUID_OUTPUT_SLOT;
        if (side == Direction.DOWN)
            return false;

        return switch (localDir)
        {
            default -> (side.getOpposite() == Direction.NORTH && slot != LEFT_FLUID_INPUT_SLOT && slot != LEFT_FLUID_OUTPUT_SLOT &&
                            slot != RIGHT_FLUID_INPUT_SLOT && slot != RIGHT_FLUID_OUTPUT_SLOT) || // TOP
                    (side.getOpposite() == Direction.EAST && slot == RIGHT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)) || //RIGHT
                    (side.getOpposite() == Direction.WEST && slot == LEFT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)); // LEFT

            case EAST -> (side.rotateYClockwise() == Direction.NORTH && slot != LEFT_FLUID_INPUT_SLOT && slot != LEFT_FLUID_OUTPUT_SLOT &&
                    slot != RIGHT_FLUID_INPUT_SLOT && slot != RIGHT_FLUID_OUTPUT_SLOT) || // TOP
                    (side.rotateYClockwise() == Direction.EAST && slot == RIGHT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)) || //RIGHT
                    (side.rotateYClockwise() == Direction.WEST && slot == LEFT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)); // LEFT

            case SOUTH -> (side == Direction.NORTH && slot != LEFT_FLUID_INPUT_SLOT && slot != LEFT_FLUID_OUTPUT_SLOT &&
                    slot != RIGHT_FLUID_INPUT_SLOT && slot != RIGHT_FLUID_OUTPUT_SLOT) || // TOP
                    (side == Direction.EAST && slot == RIGHT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)) || //RIGHT
                    (side == Direction.WEST && slot == LEFT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)); // LEFT

            case WEST -> (side.rotateYCounterclockwise() == Direction.NORTH && slot != LEFT_FLUID_INPUT_SLOT && slot != LEFT_FLUID_OUTPUT_SLOT &&
                    slot != RIGHT_FLUID_INPUT_SLOT && slot != RIGHT_FLUID_OUTPUT_SLOT) || // TOP
                    (side.rotateYCounterclockwise() == Direction.EAST && slot == RIGHT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)) || //RIGHT
                    (side.rotateYCounterclockwise() == Direction.WEST && slot == LEFT_FLUID_INPUT_SLOT && StackAcceptableInSlot(stack)); // LEFT
        };
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(EnderiteShulkerBoxBlock.FACING);
        if (side == Direction.DOWN)
            return slot != LEFT_FLUID_INPUT_SLOT || slot != RIGHT_FLUID_INPUT_SLOT;
        return switch (localDir)
        {
            default -> side.getOpposite() == Direction.SOUTH && (slot != LEFT_FLUID_INPUT_SLOT || slot != RIGHT_FLUID_INPUT_SLOT);
            case EAST -> side.rotateYClockwise() == Direction.SOUTH && (slot != LEFT_FLUID_INPUT_SLOT || slot != RIGHT_FLUID_INPUT_SLOT);
            case SOUTH -> side == Direction.SOUTH && (slot != LEFT_FLUID_INPUT_SLOT || slot != RIGHT_FLUID_INPUT_SLOT);
            case WEST -> side.rotateYCounterclockwise() == Direction.SOUTH && (slot != LEFT_FLUID_INPUT_SLOT || slot != RIGHT_FLUID_INPUT_SLOT);
        };
    }

    public void setFluidLevel(FluidVariant leftFluidVariant, long leftFluidLevel, FluidVariant rightFluidVariant, long rightFluidLevel)
    {
        this.leftFluidStorage.variant = leftFluidVariant;
        this.leftFluidStorage.amount = leftFluidLevel;
        this.rightFluidStorage.variant = rightFluidVariant;
        this.rightFluidStorage.amount = rightFluidLevel;
    }

    private boolean StackAcceptableInSlot(ItemStack stack)
    {
        return stack.isOf(Items.BUCKET) || stack.isIn(ModTags.Items.FLUID_BUCKETS);
    }

    private void handleFluidTick(World world, BlockPos pos, BlockState state)
    {
        handleTank(world, pos, state, this.leftFluidStorage, LEFT_FLUID_INPUT_SLOT, LEFT_FLUID_OUTPUT_SLOT);
        handleTank(world, pos, state, this.rightFluidStorage, RIGHT_FLUID_INPUT_SLOT, RIGHT_FLUID_OUTPUT_SLOT);
    }

    private void handleTank(World world, BlockPos pos, BlockState state, SingleVariantStorage<FluidVariant> tank, int inputSlot, int outputSlot)
    {
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(getStack(inputSlot)).find(FluidStorage.ITEM);
        if(slotStorage == null)
            return;
        Item slotItem = getStack(inputSlot).getItem();
        try
        {
            if(!moveWithSound(world, pos, tank, slotStorage, true, slotItem))
                moveWithSound(world, pos, slotStorage, tank, false, slotItem);
        }
        catch (Exception e)
        {
            LOGGER.info(e.getMessage());
        }
    }

    private static boolean moveWithSound(World world, BlockPos pos, Storage<FluidVariant> from, Storage<FluidVariant> to, boolean fill, Item handItem)
    {
        for (StorageView<FluidVariant> view : from)
        {
            if (view.isResourceBlank())
                continue;

            FluidVariant resource = view.getResource();
            long maxExtracted;

            // check how much can be extracted
            try (Transaction extractionTestTransaction = Transaction.openOuter())
            {
                maxExtracted = view.extract(resource, Long.MAX_VALUE, extractionTestTransaction);
                extractionTestTransaction.abort();
            }

            try (Transaction transferTransaction = Transaction.openOuter())
            {
                // check how much can be inserted
                long accepted = to.insert(resource, maxExtracted, transferTransaction);

                // extract it, or rollback if the amounts don't match
                if (accepted > 0 && view.extract(resource, accepted, transferTransaction) == accepted)
                {
                    transferTransaction.commit();

                    SoundEvent sound = fill ? FluidVariantAttributes.getFillSound(resource) : FluidVariantAttributes.getEmptySound(resource);

                    if (resource.isOf(Fluids.WATER))
                    {
                        if (fill && handItem == Items.GLASS_BOTTLE) sound = SoundEvents.ITEM_BOTTLE_FILL;
                        if (!fill && handItem == Items.POTION) sound = SoundEvents.ITEM_BOTTLE_EMPTY;
                    }

                    world.playSound(pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.BLOCKS, 1, 1, true);

                    return true;
                }
            }
        }

        return false;
    }
}