package jiraiyah.allthatmatters.recipe;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.recipe.custom.InfusingStationCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes
{
    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Recipes for : " + AllThatMatters.ModID);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(AllThatMatters.ModID, InfusingStationCraftingRecipe.Serializer.ID),
                InfusingStationCraftingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(AllThatMatters.ModID, InfusingStationCraftingRecipe.Type.ID),
                InfusingStationCraftingRecipe.Type.INSTANCE);
    }
}