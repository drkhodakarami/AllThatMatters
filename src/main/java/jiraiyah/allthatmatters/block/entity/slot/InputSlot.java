package jiraiyah.allthatmatters.block.entity.slot;

import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class InputSlot extends Slot
{
    public InputSlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        //return stack.isOf(Items.BAMBOO_BLOCK);
		
		return true;
    }
}