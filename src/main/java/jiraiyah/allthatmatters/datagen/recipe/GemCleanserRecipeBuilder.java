package jiraiyah.allthatmatters.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class GemCleanserRecipeBuilder implements CraftingRecipeJsonBuilder
{
    private final Item result;
    private final Ingredient ingredient;
    private final int count;
    private final int craftingTime;
    private final int fluidAmount;
    private final int ingredientCount;
    private final Advancement.Builder advancement = Advancement.Builder.create();

    public GemCleanserRecipeBuilder(ItemConvertible ingredient, int ingredientCount, ItemConvertible result,
                                    int resultCount, int craftingTime, int fluidAmount)
    {
        this.ingredient = Ingredient.ofItems(ingredient);
        this.result = result.asItem();
        this.count = resultCount;
        this.craftingTime = craftingTime;
        this.fluidAmount = fluidAmount;
        this.ingredientCount = ingredientCount;
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion)
    {
        this.advancement.criterion(name, criterion);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group)
    {
        return this;
    }

    @Override
    public Item getOutputItem()
    {
        return result;
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId)
    {
        exporter.accept(new JsonBuilder(recipeId, this.result, this.count, this.ingredient,
                this.advancement, new Identifier(recipeId.getNamespace(), "recipes/"
                + recipeId.getPath()), this.craftingTime, this.fluidAmount, this.ingredientCount));
    }

    public static class JsonBuilder implements RecipeJsonProvider
    {
        private final Identifier id;
        private final Item result;
        private final Ingredient ingredient;
        private final int count;
        private final int craftingTime;
        private final int fluidAmount;
        private final int ingredientCount;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;

        public JsonBuilder(Identifier id, Item result, int count, Ingredient ingredient,
                           Advancement.Builder advancement, Identifier advancementId,
                           int craftingTime, int fluidAmount, int ingredientCount)
        {
            this.id = id;
            this.result = result;
            this.count = count;
            this.ingredient = ingredient;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.craftingTime = craftingTime;
            this.fluidAmount = fluidAmount;
            this.ingredientCount = ingredientCount;
        }

        @Override
        public void serialize(JsonObject json)
        {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(ingredient.toJson(true));

            json.add("ingredients", jsonArray);

            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("item", Registries.ITEM.getId(this.result).toString());
            if(this.count > 1)
                jsonObject.addProperty("count",this.count);

            json.addProperty("craftingTime", this.craftingTime);
            json.addProperty("fluidAmount", this.fluidAmount);
            json.addProperty("ingredientCount", this.ingredientCount);

            json.add("output", jsonObject);
        }

        @Override
        public Identifier id()
        {
            return new Identifier(AllThatMatters.ModID,
                    Registries.ITEM.getId(this.result).getPath()
                            + "_from_gem_cleanser");
        }

        @Override
        public RecipeSerializer<?> serializer()
        {
            return ModRecipes.GEM_CLEANSER_SERIALIZER;
        }

        @Nullable
        @Override
        public AdvancementEntry advancement()
        {
            return new AdvancementEntry(id(), advancement.build(id()).value());
        }
    }
}