package jiraiyah.allthatmatters.utils.slot;

import jiraiyah.allthatmatters.utils.ModTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class FluidInputSlot extends Slot
{
    public FluidInputSlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return stack.isIn(ModTags.Items.FLUID_BUCKETS);
    }
}