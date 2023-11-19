package jiraiyah.allthatmatters.item.custom;

import jiraiyah.allthatmatters.AllThatMatters;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EnderBackpackItem extends Item
{
    public static final Text CONTAINER_NAME = Text.translatable(AllThatMatters.ModID + ".container.enderchest");

    public EnderBackpackItem(Settings settings)
    {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        if(!world.isClient)
        {
            if(user.getEnderChestInventory() != null)
                openScreen(user, user.getStackInHand(hand));
        }
        else
            user.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0f, 1.0f);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    public static void openScreen(PlayerEntity user, ItemStack stack)
    {
        user.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInv, player) -> {
            return GenericContainerScreenHandler.createGeneric9x3(i, playerInv, user.getEnderChestInventory());
        }, CONTAINER_NAME));
    }
}