package jiraiyah.allthatmatters.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
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
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class SmelteryRecipeBuilder implements CraftingRecipeJsonBuilder
{
    private final Item result;
    private final Ingredient ingredient;
    private final Ingredient cast;
    private final int count;
    private final int craftingTime;
    private final int fluidAmount;
    private final int ingredientCount;
    private final Advancement.Builder advancement = Advancement.Builder.create();
    private final boolean usesTags;
    private final TagKey<Item> tagKey;
    private final String additionalName;

    public SmelteryRecipeBuilder(ItemConvertible ingredient, int ingredientCount, ItemConvertible cast, ItemConvertible result,
                                 int resultCount, int craftingTime, int fluidAmount)
    {
        this(ingredient, ingredientCount, cast, result, resultCount, craftingTime, fluidAmount, "");
    }

    public SmelteryRecipeBuilder(TagKey<Item> tagKey, int ingredientCount, ItemConvertible cast, ItemConvertible result, int resultCount, int craftingTime, int fluidAmount)
    {
        this(tagKey, ingredientCount, cast, result, resultCount, craftingTime, fluidAmount, "");
    }

    public SmelteryRecipeBuilder(ItemConvertible ingredient, int ingredientCount, ItemConvertible cast, ItemConvertible result,
                                 int resultCount, int craftingTime, int fluidAmount, String additionalName)
    {
        this.ingredient = Ingredient.ofItems(ingredient);
        this.cast = Ingredient.ofItems(cast);
        this.result = result.asItem();
        this.count = resultCount;
        this.craftingTime = craftingTime;
        this.fluidAmount = fluidAmount;
        this.ingredientCount = ingredientCount;
        this.usesTags = false;
        this.tagKey = null;
        this.additionalName = additionalName;
    }

    public SmelteryRecipeBuilder(TagKey<Item> tagKey, int ingredientCount, ItemConvertible cast, ItemConvertible result, int resultCount,
                                 int craftingTime, int fluidAmount, String additionalName)
    {
        this.ingredient = null;
        this.cast = Ingredient.ofItems(cast);
        this.result = result.asItem();
        this.count = resultCount;
        this.craftingTime = craftingTime;
        this.fluidAmount = fluidAmount;
        this.ingredientCount = ingredientCount;
        this.usesTags = true;
        this.tagKey = tagKey;
        this.additionalName = additionalName;
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
        exporter.accept(new JsonBuilder(recipeId, this.result, this.count, this.ingredient, this.tagKey, this.usesTags, this.cast,
                this.advancement, new Identifier(recipeId.getNamespace(), "recipes/"
                + recipeId.getPath()), this.craftingTime, this.fluidAmount, this.ingredientCount, additionalName));
    }

    public static class JsonBuilder implements RecipeJsonProvider
    {
        private final Identifier id;
        private final Item result;
        private final Ingredient ingredient;
        private final Ingredient cast;
        private final int count;
        private final int craftingTime;
        private final int fluidAmount;
        private final int ingredientCount;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;
        private final boolean usesTags;
        private final TagKey<Item> tagKey;
        private final String additionalName;

        private final String idSuffix = "_from_smeltery";

        public JsonBuilder(Identifier id, Item result, int count, Ingredient ingredient, TagKey<Item> tagKey, boolean usesTags,
                           Ingredient cast, Advancement.Builder advancement, Identifier advancementId,
                           int craftingTime, int fluidAmount, int ingredientCount)
        {
            this(id, result, count, ingredient, tagKey, usesTags, cast, advancement, advancementId, craftingTime, fluidAmount, ingredientCount, "");
        }

        public JsonBuilder(Identifier id, Item result, int count, Ingredient ingredient, TagKey<Item> tagKey, boolean usesTags,
                           Ingredient cast, Advancement.Builder advancement, Identifier advancementId,
                           int craftingTime, int fluidAmount, int ingredientCount, String additionalName)
        {
            this.id = id;
            this.result = result;
            this.count = count;
            this.ingredient = ingredient;
            this.cast = cast;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.craftingTime = craftingTime;
            this.fluidAmount = fluidAmount;
            this.ingredientCount = ingredientCount;
            this.usesTags = usesTags;
            this.tagKey = tagKey;
            this.additionalName = additionalName;
        }

        @Override
        public void serialize(JsonObject json)
        {
            JsonArray jsonArray = new JsonArray();
            JsonObject jsonObject = new JsonObject();

            if(!usesTags)
                jsonArray.add(ingredient.toJson(true));
            else
            {
                String value = TagKey.codec(RegistryKeys.ITEM).encodeStart(JsonOps.INSTANCE,tagKey).result().get().toString();
                value = value.replace("#","").replace("\"", "");
                jsonObject.addProperty("tag", value);
                jsonArray.add(jsonObject);
            }

            jsonArray.add(cast.toJson(true));

            json.add("ingredients", jsonArray);

            jsonObject = new JsonObject();

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
            String name = Registries.ITEM.getId(this.result).getPath();
            if(additionalName.isBlank() || additionalName.isEmpty())
                name += idSuffix;
            else
                name += "_" + additionalName + idSuffix;

            return new Identifier(AllThatMatters.ModID, name);
        }

        @Override
        public RecipeSerializer<?> serializer()
        {
            return ModRecipes.SMELTERY_SERIALIZER;
        }

        @Nullable
        @Override
        public AdvancementEntry advancement()
        {
            return new AdvancementEntry(id(), advancement.build(id()).value());
        }
    }
}