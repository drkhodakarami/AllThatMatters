package jiraiyah.allthatmatters.compat;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.recipe.custom.InfusingStationCraftingRecipe;
import jiraiyah.allthatmatters.screen.custom.InfusingStationScreen;
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
        registry.add(new InfusingStationCategory());
        registry.addWorkstations(InfusingStationCategory.ADVANCE_INFUSING, EntryStacks.of(ModBlocks.INFUSING_STATION));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(InfusingStationCraftingRecipe.class, ModRecipes.INFUSING_STATION_TYPE, InfusingStationDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry)
    {
        registry.registerClickArea(screen ->
                        new Rectangle(75, 30, 20, 30),
                        InfusingStationScreen.class, InfusingStationCategory.ADVANCE_INFUSING);
    }
}