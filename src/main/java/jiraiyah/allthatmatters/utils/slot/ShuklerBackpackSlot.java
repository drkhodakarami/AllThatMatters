package jiraiyah.allthatmatters.utils.slot;

import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class ShuklerBackpackSlot extends Slot
{
    public ShuklerBackpackSlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return (Block.getBlockFromItem(stack.getItem()) instanceof EnderiteShulkerBoxBlock) || stack.isOf(Items.SHULKER_BOX);
    }
}