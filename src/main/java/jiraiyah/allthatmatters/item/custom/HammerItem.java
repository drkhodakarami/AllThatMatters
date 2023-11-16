package jiraiyah.allthatmatters.item.custom;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import jiraiyah.allthatmatters.item.ModToolMaterial;
import jiraiyah.allthatmatters.utils.ModTags;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HammerItem extends MiningToolItem
{
    private TagKey<Block> blockTags;
    private int depth;
    private int radius;

    public HammerItem(ToolMaterial material, int radius, int depth)
    {
        super(1, -2.8f, material, BlockTags.PICKAXE_MINEABLE,
                new FabricItemSettings().maxCount(1).maxDamage(computeDurability(material, material.getMiningLevel())));
        this.blockTags = BlockTags.PICKAXE_MINEABLE;
        this.depth = depth;
        this.radius = radius;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
    {
        tooltip.add(Text.translatable("allthatmatters.tooltip.hammer_size", this.radius, this.radius, this.depth).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
    }

    // TODO : make the mixin, prevent items from breaking when they have mending
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state)
    {
        if(EnchantmentHelper.getLevel(Enchantments.MENDING, stack) == 0)
            return super.getMiningSpeedMultiplier(stack, state);
        if(stack.getMaxDamage() - stack.getDamage() <= 1)
            return -1f;
        return super.getMiningSpeedMultiplier(stack, state);

    }

    @Override
    public boolean isSuitableFor(BlockState state)
    {
        return actualIsCorrectToolForDrops(state);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner)
    {
        if(world.isClient || state.getHardness(world, pos) == 0.0f)
            return true;

        HitResult pick = miner.raycast(20D, 1f, false);

        if(!(pick instanceof BlockHitResult))
            return super.postMine(stack, world, state, pos, miner);

        this.findAndBreakNearBlocks(pick, pos, stack, world, miner);

        return super.postMine(stack, world, state, pos, miner);
    }

    private static int computeDurability(ToolMaterial material, int level)
    {
        return ((material.getDurability() * 2) + (200 * level)) * level;
    }

    private boolean actualIsCorrectToolForDrops(BlockState state)
    {
        int i = this.getMaterial().getMiningLevel();
        if (i < 3 && state.isIn(BlockTags.NEEDS_DIAMOND_TOOL))
            return false;
        else if (i < 2 && state.isIn(BlockTags.NEEDS_IRON_TOOL))
            return false;
        else
            return (i >= 1 || !state.isIn(BlockTags.NEEDS_STONE_TOOL)) && state.isIn(this.blockTags);
    }

    private void findAndBreakNearBlocks(HitResult pick, BlockPos blockPos, ItemStack stack, World world, LivingEntity miner)
    {
        if (!(miner instanceof ServerPlayerEntity player)) return;

        var size = (radius / 2);
        var offset = size - 1;

        Direction direction = ((BlockHitResult) pick).getSide();
        BlockPos start = BlockPos.ORIGIN;
        BlockPos end = BlockPos.ORIGIN;
        switch (direction)
        {
            case DOWN, UP ->
            {
                start = new BlockPos(blockPos.getX() - size, blockPos.getY() - (direction == Direction.UP ? depth - 1 : 0), blockPos.getZ() - size);
                end = new BlockPos(blockPos.getX() + size, blockPos.getY() + (direction == Direction.DOWN ? depth - 1 : 0), blockPos.getZ() + size);
            }
            case NORTH, SOUTH ->
            {
                start = new BlockPos(blockPos.getX() - size, blockPos.getY() - size + offset, blockPos.getZ() - (direction == Direction.SOUTH ? depth - 1 : 0));
                end = new BlockPos(blockPos.getX() + size, blockPos.getY() + size + offset, blockPos.getZ() + (direction == Direction.NORTH ? depth - 1 : 0));
            }
            case WEST, EAST ->
            {
                start = new BlockPos(blockPos.getX() - (direction == Direction.EAST ? depth - 1 : 0), blockPos.getY() - size + offset, blockPos.getZ() - size);
                end = new BlockPos(blockPos.getX() + (direction == Direction.WEST ? depth - 1 : 0), blockPos.getY() + size + offset, blockPos.getZ() + size);
            }
        };

        int damage = 0;
        Iterator<BlockPos> iterator = BlockPos.stream(
                Math.min(start.getX(), end.getX()),
                Math.min(start.getY(), end.getY()),
                Math.min(start.getZ(), end.getZ()),
                Math.max(start.getX(), end.getX()),
                Math.max(start.getY(), end.getY()),
                Math.max(start.getZ(), end.getZ())).iterator();
        Set<BlockPos> removedPos = new HashSet<>();
        while (iterator.hasNext())
        {
            var pos = iterator.next();

            if (damage >= (stack.getMaxDamage() - stack.getDamage() - 1))
                break;

            BlockState targetState = world.getBlockState(pos);
            if (pos == blockPos || removedPos.contains(pos) || !canDestroy(targetState, world, pos))
                continue;

            // Throw event out there and let mods block us breaking this block
            EventResult eventResult = BlockEvent.BREAK.invoker().breakBlock(world, pos, targetState, (ServerPlayerEntity) miner, null);
            if (eventResult.isFalse())
                continue;

            removedPos.add(pos);
            world.breakBlock(pos, false, miner);
            if (!player.isCreative())
            {
                boolean correctToolForDrops = stack.isSuitableFor(targetState);
                if (correctToolForDrops)
                {
                    targetState.onStacksDropped((ServerWorld) world, pos, stack, true);
                    List<ItemStack> drops = Block.getDroppedStacks(targetState, (ServerWorld) world, pos, world.getBlockEntity(pos), miner, stack);
                    drops.forEach(e -> Block.dropStack(world, pos, ((BlockHitResult) pick).getSide(), e));
                }
            }
            damage ++;
        }

        if (damage != 0 && !player.isCreative())
            stack.damage(damage, miner, (livingEntityx) ->
            {
                livingEntityx.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
            });
    }

    private boolean canDestroy(BlockState targetState, World world, BlockPos pos)
    {
        if (targetState.getHardness(world, pos) <= 0)
            return false;

        if(!this.isSuitableFor(targetState))
            return false;

        /*if (toolMiningLevel < MiningLevelManager.getRequiredMiningLevel(state)) {
            info.setReturnValue(false);
        }*/

        if (targetState.isIn(ModTags.Blocks.HAMMER_NO_SMASHY))
            return false;

        return world.getBlockEntity(pos) == null;
    }
}