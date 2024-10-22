package jiraiyah.allthatmatters.utils.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;

public class BackpackInventory extends SimpleInventory
{
    protected final ItemStack itemStack;
    protected final int SIZE;

    public BackpackInventory(ItemStack stack, int SIZE)
    {
        super(getStacks(stack, SIZE).toArray(new ItemStack[SIZE]));
        itemStack = stack;
        this.SIZE = SIZE;
    }

    @Override
    public void markDirty()
    {
        super.markDirty();
        NbtCompound blockEntityTag = itemStack.getSubNbt(getNBTTag());
        if (blockEntityTag == null)
            blockEntityTag = itemStack.getOrCreateSubNbt(getNBTTag());

        if (isEmpty())
        {
            if (blockEntityTag.contains("Items"))
                blockEntityTag.remove("Items");
        }
        else
        {
            DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
            for (int i = 0; i < size(); i++)
                itemStacks.set(i, getStack(i));
            Inventories.writeNbt(blockEntityTag, itemStacks);
        }

        if (shouldDeleteNBT(blockEntityTag))
            itemStack.removeSubNbt(getNBTTag());
    }

    @Override
    public void onClose(PlayerEntity player)
    {
        if (itemStack.getCount() > 1)
        {
            int count = itemStack.getCount();
            itemStack.setCount(1);
            player.giveItemStack(new ItemStack(itemStack.getItem(), count - 1));
        }
        markDirty();
    }

    public ItemStack getHolderStack()
    {
        return itemStack;
    }

    public static String getNBTTag() {
        return "BackpackContent";
    }

    public static DefaultedList<ItemStack> getStacks(ItemStack usedStack, int SIZE)
    {
        NbtCompound compoundTag = usedStack.getSubNbt(getNBTTag());
        DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
        if (compoundTag != null && compoundTag.contains("Items", 9))
            Inventories.readNbt(compoundTag, itemStacks);
        return itemStacks;
    }

    public boolean shouldDeleteNBT(NbtCompound blockEntityTag)
    {
        if (!blockEntityTag.contains("Items"))
            return blockEntityTag.getKeys().size() == 0;
        return isEmpty();
    }
}