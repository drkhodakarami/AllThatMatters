package jiraiyah.allthatmatters.recipe;

import jiraiyah.allthatmatters.AllThatMatters;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes
{
    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Recipes for : " + AllThatMatters.ModID);

        /*Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(StripBlock.ModID, StripperBlockRecipe.Serializer.ID),
                StripperBlockRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(StripBlock.ModID, StripperBlockRecipe.Type.ID),
                StripperBlockRecipe.Type.INSTANCE);*/
    }
}