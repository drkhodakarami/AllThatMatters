package jiraiyah.allthatmatters.item.custom;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static jiraiyah.allthatmatters.AllThatMatters.translate;

public class TeleportItem extends Item
{
    public TeleportItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        if(user.isSneaking() && user.getStackInHand(hand).hasNbt())
            user.getStackInHand(hand).setNbt(new NbtCompound());
        if(!user.isSneaking() && user.getStackInHand(hand).hasNbt())
        {
            if (!user.getWorld().isClient)
            {
                ItemStack stack = user.getStackInHand(hand);
                var pos = NbtHelper.toBlockPos(stack.getNbt().getCompound("allthatmatters.teleporter.pos"));
                var dimension = stack.getNbt().getString("allthatmatters.teleporter.dimension");
                MinecraftServer server = world.getServer();
                RegistryKey<World> storedKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier(dimension));
                if(storedKey == null)
                    return super.use(world, user, hand);
                TeleportTarget target = new TeleportTarget(new Vec3d(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F), new Vec3d(0, 0, 0), user.getYaw(), user.getPitch());
                if(user.getWorld().getRegistryKey().equals(storedKey))
                    ((ServerPlayerEntity)user).networkHandler.requestTeleport(target.position.getX(),
                                                                                target.position.getY(),
                                                                                target.position.getZ(),
                                                                                target.yaw, target.pitch);
                else
                    FabricDimensions.teleport(user, server.getWorld(storedKey), target);
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        if(context.getStack().hasNbt())
            return super.useOnBlock(context);
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        NbtCompound data = new NbtCompound();
        if(!player.getWorld().isClient)
        {
            data.put("allthatmatters.teleporter.pos", NbtHelper.fromBlockPos(pos));
            data.putString("allthatmatters.teleporter.dimension", player.getWorld().getRegistryKey().getValue().toString());
            context.getStack().setNbt(data);
        }
        if(context.getWorld().isClient && !player.isSneaking())
        {
            var dimensionName = player.getWorld().getRegistryKey().getValue().toString();
            dimensionName = dimensionName.substring(dimensionName.indexOf(':') + 1).replace('_', ' ');
            outputCoordinatesToChat(pos, dimensionName, player);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return stack.hasNbt();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
    {
        if(stack.hasNbt())
        {
            var dimensionName = stack.getNbt().getString("allthatmatters.teleporter.dimension");
            dimensionName = dimensionName.substring(dimensionName.indexOf(':') + 1).replace('_', ' ');
            var pos = NbtHelper.toBlockPos(stack.getNbt().getCompound("allthatmatters.teleporter.pos"));
            tooltip.add(translate("teleporter.tooltip", pos.getX(), pos.getY(), pos.getZ(), dimensionName));
        }
    }

    private void outputCoordinatesToChat(BlockPos pos, String dimensionName, PlayerEntity player)
    {
        player.sendMessage(translate("teleporter.tooltip", pos.getX(), pos.getY(), pos.getZ(), dimensionName), false);
    }
}