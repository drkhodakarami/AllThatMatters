package jiraiyah.allthatmatters.block.custom;


import jiraiyah.allthatmatters.utils.interfaces.UpgradedShulker;
import jiraiyah.allthatmatters.block.entity.custom.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.screen.handler.EnderiteShulkerScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderiteShulkerBoxBlock extends ShulkerBoxBlock implements UpgradedShulker
{
    public EnderiteShulkerBoxBlock(DyeColor color)
    {
        super(color, Settings.copy(Blocks.SHULKER_BOX).nonOpaque().strength(2.0f,1200f).dynamicBounds().nonOpaque());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new EnderiteShulkerBlockEntity(this.getColor(), pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return validateTicker(type, ModBlockEntities.ENDERITE_SHULKER_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.customTick(world1, pos, state1));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack)
    {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (itemStack.hasNbt()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
        }
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state)
    {
        ItemStack itemStack = super.getPickStack(world, pos, state);
        return itemStack;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (world.isClient)
        {
            return ActionResult.SUCCESS;
        }
        else if (player.isSpectator())
        {
            return ActionResult.CONSUME;
        }
        else
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EnderiteShulkerBlockEntity shulkerBoxBlockEntity)
            {
                boolean bl2;
                if (shulkerBoxBlockEntity.getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.CLOSED)
                {
                    Box box = ShulkerEntity.calculateBoundingBox(state.get(FACING), 0.0F, 0.5F).offset(pos).contract(1.0E-6D);
                    bl2 = world.isSpaceEmpty(box);
                }
                else
                {
                    bl2 = true;
                }

                if (bl2)
                {
                    player.openHandledScreen(EnderiteShulkerScreenHandler.createScreenHandlerFactory(shulkerBoxBlockEntity,
                            shulkerBoxBlockEntity.getDisplayName(), blockEntity));
                    player.incrementStat(Stats.OPEN_SHULKER_BOX);
                    PiglinBrain.onGuardedBlockInteracted(player, true);
                }

                return ActionResult.CONSUME;
            }
            else
            {
                return ActionResult.PASS;
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EnderiteShulkerBlockEntity shulkerBoxBlockEntity)
        {
            if (!world.isClient && player.isCreative() && !shulkerBoxBlockEntity.isEmpty())
            {
                ItemStack itemStack = new ItemStack(this);
                if (!shulkerBoxBlockEntity.isEmpty())
                {
                    blockEntity.setStackNbt(itemStack);
                }
                if (shulkerBoxBlockEntity.hasCustomName())
                {
                    itemStack.setCustomName(shulkerBoxBlockEntity.getCustomName());
                }
                /*if (shulkerBoxBlockEntity.hasUpgrades())
                    itemStack.setSubNbt(KEY, shulkerBoxBlockEntity.getUpgrades());*/
                ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
            else
            {
                shulkerBoxBlockEntity.checkLootInteraction(player);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos)
    {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EnderiteShulkerBlockEntity shulkerBoxBlockEntity)
        {
            return EnderiteShulkerScreenHandler.createScreenHandlerFactory(shulkerBoxBlockEntity, shulkerBoxBlockEntity.getDisplayName(), blockEntity);
        }
        return null;
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options)
    {
        super.appendTooltip(stack, world, tooltip, options);
        NbtCompound compoundTag = stack.getSubNbt("BlockEntityTag");
        if (compoundTag != null)
        {
            if (compoundTag.contains("LootTable", 8))
            {
                tooltip.add(Text.translatable("upgradedenderiteshulkers.tooltip.?"));
            }

            if (compoundTag.contains("Items", 9))
            {
                DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(this.getInventorySize(), ItemStack.EMPTY);
                Inventories.readNbt(compoundTag, defaultedList);
                int i = 0;
                int j = 0;

                for (ItemStack itemStack : defaultedList)
                {
                    if (!itemStack.isEmpty())
                    {
                        ++j;
                        if (i <= 4)
                        {
                            ++i;
                            MutableText mutableText = itemStack.getName().copy();
                            mutableText.append(" x").append(String.valueOf(itemStack.getCount()));
                            tooltip.add(mutableText);
                        }
                    }
                }

                if (j - i > 0)
                {
                    tooltip.add((Text.translatable("container.shulkerBox.more", j - i)).formatted(Formatting.ITALIC));
                }
            }
        }

    }

    @Override
    public boolean isUpgradedShulker() {
        return true;
    }

    public int getInventorySize()
    {
        return 108;
    }
}