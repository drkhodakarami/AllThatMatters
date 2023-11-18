package jiraiyah.allthatmatters.effect;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.effect.custom.FlightEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEffects
{
    public static StatusEffect FLIGHT_EFFECT = Registry.register(Registries.STATUS_EFFECT, AllThatMatters.identifier("flight"),
            new FlightEffect(StatusEffectCategory.BENEFICIAL, 0xFFFFFF));

    public ModEffects()
    {
        throw new AssertionError();
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Effects");
    }
}