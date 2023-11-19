package jiraiyah.allthatmatters.item.custom;

import jiraiyah.allthatmatters.screen.custom.BackpackRenameScreen;
import jiraiyah.allthatmatters.screen.handler.BackpackScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

import static jiraiyah.allthatmatters.AllThatMatters.translate;

public class BackpackItem extends Item
{
    public static final String UUID_KEY = "AllThatMattersItemUID";

    private final int width = 9;
    private final int height = 6;

    public BackpackItem(Settings settings)
    {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        final ItemStack stack = user.getStackInHand(hand);

        if (!user.isSneaking())
        {
            if (!world.isClient())
                openScreen(user, user.getStackInHand(hand));
            else
                user.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 1.0F);

            return TypedActionResult.consume(stack);
        }
        else if (world.isClient())
        {
            openRenameScreen(hand, stack.getName());
            return TypedActionResult.pass(stack);
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
    {
        tooltip.add(translate("tooltip.size", this.width, this.height, this.width * this.height).formatted(Formatting.WHITE));
        tooltip.add(translate("tooltip.how.to.rename").formatted(Formatting.GRAY, Formatting.ITALIC));

        if (context.isAdvanced()) {
            var uuid = getUUID(stack);
            var display = uuid != null ? uuid.toString() : "<null>";
            tooltip.add(MutableText.of(new LiteralTextContent("UUID: " + display)).formatted(Formatting.RED));
        }
    }

    public static UUID getUUID(ItemStack stack)
    {
        try
        {
            var uuid = stack.getOrCreateNbt().getUuid(UUID_KEY);
            return uuid;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static UUID getOrBindUUID(ItemStack stack)
    {
        var foundUid = getUUID(stack);

        if (foundUid == null)
            return bindUid(stack);

        return foundUid;
    }

    public static UUID bindUid(ItemStack stack)
    {
        var uuid = UUID.randomUUID();
        stack.getOrCreateNbt().putUuid(UUID_KEY, uuid);

        return uuid;
    }

    public static boolean isUUIDMatch(ItemStack stack, UUID uid)
    {
        var uuid = getUUID(stack);
        return uuid != null && uuid.equals(uid);
    }

    public static int getBPWidth(ItemStack stack)
    {
        return ((BackpackItem) stack.getItem()).width;
    }

    public static int getBPHeight(ItemStack stack)
    {
        return ((BackpackItem) stack.getItem()).height;
    }

    public static int getBPInvSize(ItemStack stack)
    {
        return getBPHeight(stack) * getBPWidth(stack);
    }

    public static void openScreen(PlayerEntity user, ItemStack stack) {
        final var bp = (BackpackItem) stack.getItem();
        var uuid = getOrBindUUID(stack);

        user.openHandledScreen(new ExtendedScreenHandlerFactory() {
            @Override
            public Text getDisplayName() {
                return stack.getName();
            }

            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                return new BackpackScreenHandler(syncId, inv, bp.width, bp.height, uuid, stack);
            }

            @Override
            public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf buf) {
                buf.writeInt(bp.width);
                buf.writeInt(bp.height);
                buf.writeUuid(uuid);
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void openRenameScreen(Hand hand, Text name)
    {
        MinecraftClient.getInstance().setScreen(new BackpackRenameScreen(hand, name));
    }
}