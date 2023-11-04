package jiraiyah.allthatmatters.block.entity.custom;

import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
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
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

//TODO : Add fluid tank support
public class EnderiteShulkerBlockEntity extends ShulkerBoxBlockEntity
{
    private int[] AVAILABLE_SLOTS;
    private NbtCompound upgrades;

    public EnderiteShulkerBlockEntity(@Nullable DyeColor color, BlockPos pos, BlockState state)
    {
        super(color, pos, state);
        this.AVAILABLE_SLOTS = IntStream.range(0, 108).toArray();
        this.setInvStackList(DefaultedList.ofSize(108, ItemStack.EMPTY));
    }

    public EnderiteShulkerBlockEntity(BlockPos pos, BlockState state)
    {
        this(null, pos, state);
    }

    @Override
    public BlockEntityType<?> getType()
    {
        return ModBlockEntities.ENDERITE_SHULKER_ENTITY;
    }

    @Override
    public void readNbt(NbtCompound tag)
    {
        if (tag.contains(EnderiteShulkerBoxBlock.KEY))
            upgrades = (NbtCompound) tag.get(EnderiteShulkerBoxBlock.KEY);
        super.readNbt(tag);
    }

    @Override
    public void writeNbt(NbtCompound tag)
    {
        if (hasUpgrades())
            tag.put(EnderiteShulkerBoxBlock.KEY, upgrades);
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
}