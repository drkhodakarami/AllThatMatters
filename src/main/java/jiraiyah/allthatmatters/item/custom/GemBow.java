package jiraiyah.allthatmatters.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class GemBow extends RangedWeaponItem implements Vanishable
{
    public static final float SPEED = 5.0f;
    public static final int RANGE = 15;

    public static final float ANIMATION_DURATION_DIVIDER = 5f;

    private final boolean isInfinit;

    public GemBow(Settings settings, boolean isInfinit)
    {
        super(settings);
        this.isInfinit = isInfinit;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks)
    {
        if (user instanceof PlayerEntity playerEntity)
        {
            boolean bl = this.isInfinit || playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (!itemStack.isEmpty() || bl)
            {
                if (itemStack.isEmpty())
                    itemStack = new ItemStack(Items.ARROW);

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!((double)f < 0.1))
                {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient)
                    {
                        ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * SPEED, 1.0F);
                        if (f == 1.0F)
                            persistentProjectileEntity.setCritical(true);

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (j > 0)
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)j * 0.5 + 0.5);

                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (k > 0)
                            persistentProjectileEntity.setPunch(k);

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0)
                            persistentProjectileEntity.setOnFireFor(100);

                        stack.damage(1, playerEntity, (p) ->
                        {
                            p.sendToolBreakStatus(playerEntity.getActiveHand());
                        });
                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW)))
                            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

                        world.spawnEntity(persistentProjectileEntity);
                    }

                    world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!bl2 && !playerEntity.getAbilities().creativeMode && !this.isInfinit)
                    {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty())
                            playerEntity.getInventory().removeOne(itemStack);
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    public static float getPullProgress(int useTicks)
    {
        float f = (float)useTicks / ANIMATION_DURATION_DIVIDER;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public int getMaxUseTime(ItemStack stack)
    {
        return 200;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean bl = !user.getProjectileType(itemStack).isEmpty();
        if (!user.getAbilities().creativeMode && !bl && !this.isInfinit)
        {
            return TypedActionResult.fail(itemStack);
        }
        else
        {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    public int getRange() {
        return RANGE;
    }
}