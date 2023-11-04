package jiraiyah.allthatmatters.block.entity.slot;

import jiraiyah.allthatmatters.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class InfusingRawInputSlot extends Slot
{
    public InfusingRawInputSlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return stack.isOf(ModItems.RAW_ENDERITE) ||
                stack.isOf(ModItems.RAW_CITRINE) ||
                stack.isOf(ModItems.RAW_RUBY) ||
                stack.isOf(ModItems.RAW_SAPPHIRE);
    }
}