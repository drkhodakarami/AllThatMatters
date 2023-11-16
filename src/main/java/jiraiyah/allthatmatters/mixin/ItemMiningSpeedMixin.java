package jiraiyah.allthatmatters.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import org.spongepowered.asm.mixin.Mixin;

//todo : return -1 for the items that has mending on themselves(check hammer item todo)
@Mixin({Item.class, MiningToolItem.class})
public class ItemMiningSpeedMixin
{

}