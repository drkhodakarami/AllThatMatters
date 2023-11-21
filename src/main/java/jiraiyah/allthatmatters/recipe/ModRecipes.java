package jiraiyah.allthatmatters.recipe;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.recipe.custom.GemCleanserRecipe;
import jiraiyah.allthatmatters.recipe.custom.SmelteryRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes
{
    public static final RecipeType<GemCleanserRecipe> GEM_CLEANSE_TYPE = new RecipeType<>(){};
    public static final RecipeSerializer<GemCleanserRecipe> GEM_CLEANSER_SERIALIZER = new GemCleanserRecipe.Serializer();

    public static final RecipeType<SmelteryRecipe> SMELTERY_TYPE = new RecipeType<>(){};
    public static final RecipeSerializer<SmelteryRecipe> SMELTERY_SERIALIZER = new SmelteryRecipe.Serializer();

    public static final String GEM_CLEANSER_ID = "gem_cleanser";
    public static final String SMELTERY_ID = "smeltery";

    private ModRecipes()
    {
        throw new AssertionError();
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Recipes");

        register(GEM_CLEANSER_ID, GEM_CLEANSE_TYPE);
        register(GEM_CLEANSER_ID, GEM_CLEANSER_SERIALIZER);

        register(SMELTERY_ID, SMELTERY_TYPE);
        register(SMELTERY_ID, SMELTERY_SERIALIZER);
    }

    private static void register(String name, RecipeSerializer<?> serializer)
    {
        Registry.register(Registries.RECIPE_SERIALIZER, AllThatMatters.identifier(name), serializer);
    }

    private static void register(String name, RecipeType<?> serializer)
    {
        Registry.register(Registries.RECIPE_TYPE, AllThatMatters.identifier(name), serializer);
    }
}