package jiraiyah.allthatmatters.utils;

import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.item.custom.GemBow;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelPredicateProvider
{
    public static void registerModels()
    {
        registerBow(ModItems.TOOL_ENDERITE_BOW);
        registerBow(ModItems.TOOL_RUBY_BOW);
        registerBow(ModItems.TOOL_SAPPHIRE_BOW);
    }

    private static void registerBow(Item bow)
    {
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) ->
                {
                    if (entity == null)
                        return 0.0f;
                    if (entity.getActiveItem() != stack)
                        return 0.0f;
                    return (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / GemBow.ANIMATION_DURATION_DIVIDER;
                });
        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null &&
                        entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f);
    }
}