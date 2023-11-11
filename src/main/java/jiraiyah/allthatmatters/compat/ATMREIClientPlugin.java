package jiraiyah.allthatmatters.compat;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.recipe.custom.GemCleanserRecipe;
import jiraiyah.allthatmatters.screen.custom.GemCleanserScreen;
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
        registry.addWorkstations(GemCleanserCategory.GEM_CLEANSER, EntryStacks.of(ModBlocks.GEM_CLEANSER));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(GemCleanserRecipe.class, ModRecipes.INFUSING_STATION_TYPE, GemCleanserDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry)
    {
        registry.registerClickArea(screen ->
                        new Rectangle(75, 30, 20, 30),
                GemCleanserScreen.class, GemCleanserCategory.GEM_CLEANSER);
    }
}