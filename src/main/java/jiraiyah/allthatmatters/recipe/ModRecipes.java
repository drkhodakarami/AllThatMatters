package jiraiyah.allthatmatters.recipe;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.recipe.custom.InfusingStationCraftingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes
{
    public static final RecipeType<InfusingStationCraftingRecipe> INFUSING_STATION_TYPE = new RecipeType<>() {};
    public static final RecipeSerializer<InfusingStationCraftingRecipe> INFUSING_STATION_SERIALIZER = new InfusingStationCraftingRecipe.Serializer();

    private ModRecipes()
    {
        throw new AssertionError();
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Recipes for : " + AllThatMatters.ModID);

        register("advance_infusing", INFUSING_STATION_TYPE);
        register("advance_infusing", INFUSING_STATION_SERIALIZER);
    }

    private static void register(String name, RecipeSerializer<?> serializer) {
        Registry.register(Registries.RECIPE_SERIALIZER, AllThatMatters.identifier(name), serializer);
    }

    private static void register(String name, RecipeType<?> serializer)
    {
        Registry.register(Registries.RECIPE_TYPE, AllThatMatters.identifier(name), serializer);
    }
}