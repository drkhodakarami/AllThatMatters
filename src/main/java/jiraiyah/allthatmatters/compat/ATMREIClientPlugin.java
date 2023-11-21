package jiraiyah.allthatmatters.compat;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.recipe.custom.GemCleanserRecipe;
import jiraiyah.allthatmatters.recipe.custom.SmelteryRecipe;
import jiraiyah.allthatmatters.screen.custom.GemCleanserScreen;
import jiraiyah.allthatmatters.screen.custom.SmelteryScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class ATMREIClientPlugin implements REIClientPlugin
{
    @Override
    public void registerCategories(CategoryRegistry registry)
    {
        registry.add(new GemCleanserCategory());
        registry.add(new SmelteryCategory());
        registry.addWorkstations(GemCleanserCategory.GEM_CLEANSER, EntryStacks.of(ModBlocks.GEM_CLEANSER));
        registry.addWorkstations(SmelteryCategory.SMELTERY, EntryStacks.of(ModBlocks.SMELTERY));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(GemCleanserRecipe.class, ModRecipes.GEM_CLEANSE_TYPE, GemCleanserDisplay::new);
        registry.registerRecipeFiller(SmelteryRecipe.class, ModRecipes.SMELTERY_TYPE, SmelteryDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry)
    {
        registry.registerClickArea(screen ->
                        new Rectangle(75, 30, 20, 30),
                GemCleanserScreen.class, GemCleanserCategory.GEM_CLEANSER);
        registry.registerClickArea(screen ->
                        new Rectangle(75, 30, 20, 30),
                SmelteryScreen.class, SmelteryCategory.SMELTERY);
    }
}