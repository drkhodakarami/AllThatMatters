package jiraiyah.allthatmatters.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SapphireSwordItem extends SwordItem
{
    public SapphireSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 3), attacker);
        var knockbackVector = target.getPos().subtract(attacker.getPos()).normalize().multiply(5,1,5);
        target.setVelocity(knockbackVector);
        return super.postHit(stack, target, attacker);
    }
}