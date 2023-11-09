package jiraiyah.allthatmatters.utils;

import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.item.custom.GemBow;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import javax.swing.text.html.parser.Entity;

public class ModModelPredicateProvider
{
    public static void registerModels()
    {
        registerBow(ModItems.TOOL_RUBY_BOW);
    }

    private static void registerBow(Item bow)
    {
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) ->{
                  if(entity == null)
                      return 0.0f;
                  if(entity.getActiveItem() != stack)
                      return 0.0f;
                  return (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / GemBow.ANIMATION_DURATION_DIVIDER;
                });
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null &&
                        entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f);

        //AbstractClientPlayerEntity.getFovMultiplier()
    }
}