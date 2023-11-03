package jiraiyah.allthatmatters.block.entity;

import net.minecraft.block.BlockState;
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
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class EnderiteShulkerBlockEntity extends ShulkerBoxBlockEntity
{
    private int[] AVAILABLE_SLOTS;
    private NbtCompound upgrades;

    public EnderiteShulkerBlockEntity(@Nullable DyeColor color, BlockPos pos, BlockState state)
    {
        super(color, pos, state);
        //this.type = ShulkersRegistry.UPGRADEDSHULKERENTITYTYPE;
        this.AVAILABLE_SLOTS = IntStream.range(0, 108).toArray();
        this.setInvStackList(DefaultedList.ofSize(108, ItemStack.EMPTY));
    }

    public EnderiteShulkerBlockEntity(BlockPos pos, BlockState state)
    {
        this(null, pos, state);
    }

    public void readNbt(NbtCompound tag)
    {
        super.readNbt(tag);
    }

    public void writeNbt(NbtCompound tag)
    {
        super.writeNbt(tag);
    }

    public int[] getAvailableSlots(Direction side)
    {
        return this.AVAILABLE_SLOTS;
    }

    public void appendUpgrades(NbtCompound tag)
    {
        this.upgrades = tag;
    }

    public boolean hasUpgrades()
    {
        return upgrades != null;
    }

    public NbtCompound getUpgrades()
    {
        return this.upgrades;
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
}