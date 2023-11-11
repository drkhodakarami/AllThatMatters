package jiraiyah.allthatmatters.block.entity;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

//TODO : Add fluid tank support
public class EnderiteShulkerBlockEntity extends ShulkerBoxBlockEntity
{
    private final int[] AVAILABLE_SLOTS;

    public static int INVENTORY_SLOT_SIZE = 108;

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
    }

    @Override
    public void writeNbt(NbtCompound tag)
    {
        super.writeNbt(tag);
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
    }
}