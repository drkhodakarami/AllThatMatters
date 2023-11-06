package jiraiyah.allthatmatters.utils.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class InfusingToolSlot extends Slot
{
    public InfusingToolSlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return stack.isOf(Items.GOLDEN_PICKAXE);
    }
}