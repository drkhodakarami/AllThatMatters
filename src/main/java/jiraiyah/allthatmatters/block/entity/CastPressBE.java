package jiraiyah.allthatmatters.block.entity;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.block.custom.CastPressBlock;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.screen.handler.CastPressScreenHandler;
import jiraiyah.allthatmatters.utils.CastType;
import jiraiyah.allthatmatters.utils.interfaces.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public final class CastPressBE extends BlockEntity implements NamedScreenHandlerFactory, ExtendedScreenHandlerFactory, ImplementedInventory, InventoryProvider
{
    protected final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(TOTAL_SLOTS, ItemStack.EMPTY);

    public static final int TOTAL_SLOTS = 2;

    public static final int BASE_INPUT_SLOT = 0;
    public static final int BASE_OUTPUT_SLOT = 1;

    public CastPressBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CAST_PRESS, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state){}

    public void pressTheCast(CastType type)
    {
        if(stackIsCorrectMaterial(this.getStack(BASE_INPUT_SLOT)) && outputCanReceive(this.BASE_OUTPUT_SLOT))
        {
            Item output = null;
            if(this.getStack(BASE_INPUT_SLOT).isIn(ItemTags.PLANKS))
            {
                switch (type)
                {
                    case INGOT -> output = ModItems.CAST_WOOD_INGOT;
                    case PICKAXE -> output = ModItems.CAST_WOOD_PICKAXE;
                }
            }
            else
            {
                switch (type)
                {
                    case AXE -> output = ModItems.CAST_AXE;
                    case BINDING -> output = ModItems.CAST_BINDING;
                    case GEAR -> output = ModItems.CAST_GEAR;
                    case GEM -> output = ModItems.CAST_GEM;
                    case HAMMER -> output = ModItems.CAST_HAMMER;
                    case HANDLE -> output = ModItems.CAST_HANDLE;
                    case HOE -> output = ModItems.CAST_HOE;
                    case INGOT -> output = ModItems.CAST_INGOT;
                    case NUGGET -> output = ModItems.CAST_NUGGET;
                    case PICKAXE -> output = ModItems.CAST_PICKAXE;
                    case PLATE -> output = ModItems.CAST_PLATE;
                    case ROD -> output = ModItems.CAST_ROD;
                    case SHOVEL -> output = ModItems.CAST_SHOVEL;
                    case SWORD -> output = ModItems.CAST_SWORD;
                    case WIRE -> output = ModItems.CAST_WIRE;
                }
            }
            if(output != null)
            {
                this.removeStack(BASE_INPUT_SLOT, 1);
                this.setStack(BASE_OUTPUT_SLOT, new ItemStack(output, getStack(BASE_OUTPUT_SLOT).getCount() + 1));
                markDirty();
            }
        }
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new CastPressScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return inventory;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(CastPressBlock.FACING);

        // TOP --> RAW SLOT

        if (side == Direction.UP)
            return slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot);
        if (side == Direction.DOWN)
            return false;

        // RIGHT --> TOOL SLOTS
        // LEFT --> LIQUIDATION SLOT

        return switch (localDir)
        {
            default -> side.getOpposite() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot);
            case EAST -> side.rotateYClockwise() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot);
            case SOUTH -> side == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot);
            case WEST -> side.rotateYCounterclockwise() == Direction.NORTH && slot == BASE_INPUT_SLOT && StackAcceptableInSlot(stack, slot);
        };

    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side)
    {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(CastPressBlock.FACING);
        if (side == Direction.DOWN)
            return slot == BASE_OUTPUT_SLOT;

        return switch (localDir)
        {
            default -> side.getOpposite() == Direction.SOUTH && slot == BASE_OUTPUT_SLOT;
            case EAST -> side.rotateYClockwise() == Direction.SOUTH && slot == BASE_OUTPUT_SLOT;
            case SOUTH -> side == Direction.SOUTH && slot == BASE_OUTPUT_SLOT;
            case WEST -> side.rotateYCounterclockwise() == Direction.SOUTH && slot == BASE_OUTPUT_SLOT;
        };
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
    {
        buf.writeBlockPos(pos);
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos)
    {
        return this;
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    //region PRIVATE METHODS
    private boolean StackAcceptableInSlot(ItemStack stack, int slot)
    {
        if (slot == BASE_INPUT_SLOT)
            return stackIsCorrectMaterial(stack);
        return false;
    }

    private boolean stackIsCorrectMaterial(ItemStack stack)
    {
        return stack.isOf(Items.GOLD_INGOT) || stack.isIn(ItemTags.PLANKS);
    }

    private boolean slotIsEmpty(int slotIndex)
    {
        return this.getStack(slotIndex).isEmpty();
    }

    private boolean outputCanReceive(int slotIndex)
    {
        return this.slotIsEmpty(slotIndex) || this.getStack(slotIndex).getCount() + 1 <= this.getStack(slotIndex).getMaxCount();
    }
    //endregion
}