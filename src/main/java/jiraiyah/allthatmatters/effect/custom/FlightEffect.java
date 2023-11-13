package jiraiyah.allthatmatters.effect.custom;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import jiraiyah.allthatmatters.AllThatMatters;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;

public class FlightEffect extends StatusEffect
{
    public static final AbilitySource FLIGHT_POTION = Pal.getAbilitySource(AllThatMatters.identifier("potion_flight"));

    private ServerPlayerEntity entity;

    public FlightEffect(StatusEffectCategory category, int color)
    {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier)
    {
        super.onApplied(entity, amplifier);
        if(entity instanceof ServerPlayerEntity sp)
        {
            Pal.grantAbility(sp, VanillaAbilities.ALLOW_FLYING, FLIGHT_POTION);
            Pal.grantAbility(sp, VanillaAbilities.FLYING, FLIGHT_POTION);
            this.entity = sp;
        }
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer)
    {
        super.onRemoved(attributeContainer);
        if(this.entity != null)
        {
            Pal.revokeAbility(this.entity, VanillaAbilities.ALLOW_FLYING, FLIGHT_POTION);
            Pal.revokeAbility(this.entity, VanillaAbilities.FLYING, FLIGHT_POTION);
        }
    }
}