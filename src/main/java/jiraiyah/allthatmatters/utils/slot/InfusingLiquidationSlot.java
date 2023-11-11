package jiraiyah.allthatmatters.utils.slot;

import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class InfusingLiquidationSlot extends Slot
{
    public InfusingLiquidationSlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return stack.isOf(Items.WATER_BUCKET) || stack.isOf(Items.BUCKET);
    }
}