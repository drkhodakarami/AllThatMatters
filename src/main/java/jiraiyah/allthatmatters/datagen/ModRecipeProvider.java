package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider
{
    private static final List<ItemConvertible> CITRINE_SMELTABLES = List.of(
            ModItems.RAW_CITRINE,
            ModBlocks.ORE_WORLD_CITRINE,
            ModBlocks.ORE_DEEP_CITRINE,
            ModBlocks.ORE_END_CITRINE,
            ModBlocks.ORE_NETHER_CITRINE
    );

    private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(
            ModItems.RAW_RUBY,
            ModBlocks.ORE_WORLD_RUBY,
            ModBlocks.ORE_DEEP_RUBY,
            ModBlocks.ORE_END_RUBY,
            ModBlocks.ORE_NETHER_RUBY
    );

    private static final List<ItemConvertible> SAPPHIRE_SMELTABLES = List.of(
            ModItems.RAW_SAPPHIRE,
            ModBlocks.ORE_WORLD_SAPPHIRE,
            ModBlocks.ORE_DEEP_SAPPHIRE,
            ModBlocks.ORE_END_SAPPHIRE,
            ModBlocks.ORE_NETHER_SAPPHIRE
    );

    public ModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter)
    {
        offerSmelting(exporter, CITRINE_SMELTABLES, RecipeCategory.MISC, ModItems.CITRINE, 0.7f, 200, "citrine");
        offerBlasting(exporter, CITRINE_SMELTABLES, RecipeCategory.MISC, ModItems.CITRINE, 0.7f, 100, "citrine");
        offerSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.7f, 200, "ruby");
        offerBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.7f, 100, "ruby");
        offerSmelting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.7f, 200, "sapphire");
        offerBlasting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.7f, 100, "sapphire");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CITRINE, RecipeCategory.DECORATIONS, ModBlocks.CITRINE);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.DECORATIONS, ModBlocks.RUBY);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.DECORATIONS, ModBlocks.SAPPHIRE);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ENDERITE, RecipeCategory.DECORATIONS, ModBlocks.ENDERITE);

        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 400, Items.ROTTEN_FLESH, Items.LEATHER, 0.1F);
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, 800, Items.ROTTEN_FLESH, Items.LEATHER, 0.1F);

        //region Shulker Box Shaped Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_NORMAL, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.SHULKER_BOX)
                .criterion(hasItem(Items.SHULKER_BOX), conditionsFromItem(Items.SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_NORMAL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_BLACK, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.BLACK_SHULKER_BOX)
                .criterion(hasItem(Items.BLACK_SHULKER_BOX), conditionsFromItem(Items.BLACK_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_BLACK) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_BLUE, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.BLUE_SHULKER_BOX)
                .criterion(hasItem(Items.BLUE_SHULKER_BOX), conditionsFromItem(Items.BLUE_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_BLUE) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_BROWN, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.BROWN_SHULKER_BOX)
                .criterion(hasItem(Items.BROWN_SHULKER_BOX), conditionsFromItem(Items.BROWN_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_BROWN) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_CYAN, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.CYAN_SHULKER_BOX)
                .criterion(hasItem(Items.CYAN_SHULKER_BOX), conditionsFromItem(Items.CYAN_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_CYAN) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_GRAY, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.GRAY_SHULKER_BOX)
                .criterion(hasItem(Items.GRAY_SHULKER_BOX), conditionsFromItem(Items.GRAY_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_GRAY) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_GREEN, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.GREEN_SHULKER_BOX)
                .criterion(hasItem(Items.GREEN_SHULKER_BOX), conditionsFromItem(Items.GREEN_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_GREEN) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_LIGHT_BLUE, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.LIGHT_BLUE_SHULKER_BOX)
                .criterion(hasItem(Items.LIGHT_BLUE_SHULKER_BOX), conditionsFromItem(Items.LIGHT_BLUE_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_LIGHT_BLUE) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_LIGHT_GRAY, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.LIGHT_GRAY_SHULKER_BOX)
                .criterion(hasItem(Items.LIGHT_GRAY_SHULKER_BOX), conditionsFromItem(Items.LIGHT_GRAY_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_LIGHT_GRAY) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_LIME, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.LIME_SHULKER_BOX)
                .criterion(hasItem(Items.LIME_SHULKER_BOX), conditionsFromItem(Items.LIME_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_LIME) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_MAGENTA, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.MAGENTA_SHULKER_BOX)
                .criterion(hasItem(Items.MAGENTA_SHULKER_BOX), conditionsFromItem(Items.MAGENTA_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_MAGENTA) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_ORANGE, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.ORANGE_SHULKER_BOX)
                .criterion(hasItem(Items.ORANGE_SHULKER_BOX), conditionsFromItem(Items.ORANGE_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_ORANGE) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_PINK, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.PINK_SHULKER_BOX)
                .criterion(hasItem(Items.PINK_SHULKER_BOX), conditionsFromItem(Items.PINK_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_PINK) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_PURPLE, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.PURPLE_SHULKER_BOX)
                .criterion(hasItem(Items.PURPLE_SHULKER_BOX), conditionsFromItem(Items.PURPLE_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_PURPLE) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_RED, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.RED_SHULKER_BOX)
                .criterion(hasItem(Items.RED_SHULKER_BOX), conditionsFromItem(Items.RED_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_RED) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_WHITE, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.WHITE_SHULKER_BOX)
                .criterion(hasItem(Items.WHITE_SHULKER_BOX), conditionsFromItem(Items.WHITE_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_WHITE) + "shaped"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_YELLOW, 1)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .input('E', ModItems.ENDERITE)
                .input('S', Items.YELLOW_SHULKER_BOX)
                .criterion(hasItem(Items.YELLOW_SHULKER_BOX), conditionsFromItem(Items.YELLOW_SHULKER_BOX))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_YELLOW) + "shaped"));
        //endregion

        //region Shulker Box Shapeless Recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_BLACK, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.BLACK_DYE)
                .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_BLACK) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_BLUE, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.BLUE_DYE)
                .criterion(hasItem(Items.BLUE_DYE), conditionsFromItem(Items.BLUE_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_BLUE) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_BROWN, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.BROWN_DYE)
                .criterion(hasItem(Items.BROWN_DYE), conditionsFromItem(Items.BROWN_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_BROWN) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_CYAN, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.CYAN_DYE)
                .criterion(hasItem(Items.CYAN_DYE), conditionsFromItem(Items.CYAN_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_CYAN) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_GRAY, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.GRAY_DYE)
                .criterion(hasItem(Items.GRAY_DYE), conditionsFromItem(Items.GRAY_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_GRAY) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_GREEN, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.GREEN_DYE)
                .criterion(hasItem(Items.GREEN_DYE), conditionsFromItem(Items.GREEN_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_GREEN) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_LIGHT_BLUE, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.LIGHT_BLUE_DYE)
                .criterion(hasItem(Items.LIGHT_BLUE_DYE), conditionsFromItem(Items.LIGHT_BLUE_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_LIGHT_BLUE) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_LIGHT_GRAY, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.LIGHT_GRAY_DYE)
                .criterion(hasItem(Items.LIGHT_GRAY_DYE), conditionsFromItem(Items.LIGHT_GRAY_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_LIGHT_GRAY) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_LIME, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.LIME_DYE)
                .criterion(hasItem(Items.LIME_DYE), conditionsFromItem(Items.LIME_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_LIME) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_MAGENTA, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.MAGENTA_DYE)
                .criterion(hasItem(Items.MAGENTA_DYE), conditionsFromItem(Items.MAGENTA_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_MAGENTA) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_ORANGE, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.ORANGE_DYE)
                .criterion(hasItem(Items.ORANGE_DYE), conditionsFromItem(Items.ORANGE_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_ORANGE) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_PINK, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.PINK_DYE)
                .criterion(hasItem(Items.PINK_DYE), conditionsFromItem(Items.PINK_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_PINK) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_PURPLE, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.PURPLE_DYE)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_PURPLE) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_RED, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.RED_DYE)
                .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_RED) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_WHITE, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.WHITE_DYE)
                .criterion(hasItem(Items.WHITE_DYE), conditionsFromItem(Items.WHITE_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_WHITE) + "shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHULKER_YELLOW, 1)
                .input(ModBlocks.SHULKER_NORMAL)
                .input(Items.YELLOW_DYE)
                .criterion(hasItem(Items.YELLOW_DYE), conditionsFromItem(Items.YELLOW_DYE))
                .criterion(hasItem(ModBlocks.SHULKER_NORMAL), conditionsFromItem(ModBlocks.SHULKER_NORMAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SHULKER_YELLOW) + "shapeless"));
        //endregion

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.INFUSING_STATION, 1)
                .pattern("RIR")
                .pattern("IBI")
                .pattern("RFR")
                .input('R', Items.REDSTONE)
                .input('B', Items.FURNACE)
                .input('F', Items.BLAST_FURNACE)
                .input('I', Items.IRON_BARS)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.FURNACE), conditionsFromItem(Items.FURNACE))
                .criterion(hasItem(Items.IRON_BARS), conditionsFromItem(Items.IRON_BARS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INFUSING_STATION)));

        /*ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_RUBY, 1)
                .pattern("sss")
                .pattern("srs")
                .pattern("sss")
                .input('s', Items.STONE)
                .input('r', ModItems.RUBY)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RAW_RUBY)));*/
    }
}