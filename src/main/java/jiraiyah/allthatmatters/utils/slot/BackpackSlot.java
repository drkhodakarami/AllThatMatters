package jiraiyah.allthatmatters.utils.slot;

import jiraiyah.allthatmatters.item.custom.BackpackItem;
import jiraiyah.allthatmatters.item.custom.EnderBackpackItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BackpackSlot extends Slot
{
    public BackpackSlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return !(stack.getItem() instanceof BackpackItem) &&
               !(stack.getItem() instanceof EnderBackpackItem);
    }
}