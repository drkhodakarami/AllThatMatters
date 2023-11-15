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
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider
{
    /*private static final List<ItemConvertible> CITRINE_SMELTABLES = List.of(
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
    );*/

    public ModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter)
    {
        /*offerSmelting(exporter, CITRINE_SMELTABLES, RecipeCategory.MISC, ModItems.CITRINE, 0.7f, 200, "citrine");
        offerBlasting(exporter, CITRINE_SMELTABLES, RecipeCategory.MISC, ModItems.CITRINE, 0.7f, 100, "citrine");
        offerSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.7f, 200, "ruby");
        offerBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.7f, 100, "ruby");
        offerSmelting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.7f, 200, "sapphire");
        offerBlasting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.7f, 100, "sapphire");*/

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

        //region MACHINES
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GEM_CLEANSER, 1)
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
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GEM_CLEANSER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CHUNK_LOADER, 1)
                .pattern("#/#")
                .pattern("#E#")
                .pattern("QQQ")
                .input('#', Items.ENDER_PEARL)
                .input('/', Items.ENDER_EYE)
                .input('E', Items.END_ROD)
                .input('Q', ModItems.ENDERITE)
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                .criterion(hasItem(Items.END_ROD), conditionsFromItem(Items.END_ROD))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CHUNK_LOADER)));
        //endregion

        //region CITRINE TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_AXE, 1)
                .pattern("CC")
                .pattern("CS")
                .pattern(" S")
                .input('C', ModItems.CITRINE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_HOE, 1)
                .pattern("CC")
                .pattern(" S")
                .pattern(" S")
                .input('C', ModItems.CITRINE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', ModItems.CITRINE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_PICKAXE, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.CITRINE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_SHOVEL, 1)
                .pattern("C")
                .pattern("S")
                .pattern("S")
                .input('C', ModItems.CITRINE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_CITRINE_SWORD, 1)
                .pattern("C")
                .pattern("C")
                .pattern("S")
                .input('C', ModItems.CITRINE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_SWORD)));
        //endregion

        //region COPPER TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_AXE, 1)
                .pattern("CC")
                .pattern("CS")
                .pattern(" S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_HOE, 1)
                .pattern("CC")
                .pattern(" S")
                .pattern(" S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_PICKAXE, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_SHOVEL, 1)
                .pattern("C")
                .pattern("S")
                .pattern("S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_COPPER_SWORD, 1)
                .pattern("C")
                .pattern("C")
                .pattern("S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_SWORD)));
        //endregion

        //region VANILLA HAMMERS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_DIAMOND_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', Items.DIAMOND)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_DIAMOND_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_GOLD_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', Items.GOLD_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_GOLD_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_IRON_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', Items.IRON_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_IRON_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_NETHERITE_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', Items.NETHERITE_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_NETHERITE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_STONE_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', ItemTags.STONE_TOOL_MATERIALS)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion("has_cobblestone", conditionsFromTag(ItemTags.STONE_TOOL_MATERIALS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_STONE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_WOOD_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', ItemTags.PLANKS)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion("has_planks", conditionsFromTag(ItemTags.PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_WOOD_HAMMER)));
        //endregion

        //region ENDERITE TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_AXE, 1)
                .pattern("CC")
                .pattern("CS")
                .pattern(" S")
                .input('C', ModItems.ENDERITE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_HOE, 1)
                .pattern("CC")
                .pattern(" S")
                .pattern(" S")
                .input('C', ModItems.ENDERITE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', ModItems.ENDERITE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_PICKAXE, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.ENDERITE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_SHOVEL, 1)
                .pattern("C")
                .pattern("S")
                .pattern("S")
                .input('C', ModItems.ENDERITE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_ENDERITE_SWORD, 1)
                .pattern("C")
                .pattern("C")
                .pattern("S")
                .input('C', ModItems.ENDERITE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_SWORD)));
        //endregion

        //region RUBY TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_AXE, 1)
                .pattern("CC")
                .pattern("CS")
                .pattern(" S")
                .input('C', ModItems.RUBY)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_HOE, 1)
                .pattern("CC")
                .pattern(" S")
                .pattern(" S")
                .input('C', ModItems.RUBY)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', ModItems.RUBY)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_PICKAXE, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.RUBY)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_SHOVEL, 1)
                .pattern("C")
                .pattern("S")
                .pattern("S")
                .input('C', ModItems.RUBY)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_RUBY_SWORD, 1)
                .pattern("C")
                .pattern("C")
                .pattern("S")
                .input('C', ModItems.RUBY)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_SWORD)));
        //endregion

        //region SAPPHIRE TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_AXE, 1)
                .pattern("CC")
                .pattern("CS")
                .pattern(" S")
                .input('C', ModItems.SAPPHIRE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_HOE, 1)
                .pattern("CC")
                .pattern(" S")
                .pattern(" S")
                .input('C', ModItems.SAPPHIRE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_HAMMER, 1)
                .pattern("CSC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', ModItems.SAPPHIRE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_PICKAXE, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.SAPPHIRE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_SHOVEL, 1)
                .pattern("C")
                .pattern("S")
                .pattern("S")
                .input('C', ModItems.SAPPHIRE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_SAPPHIRE_SWORD, 1)
                .pattern("C")
                .pattern("C")
                .pattern("S")
                .input('C', ModItems.SAPPHIRE)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_SWORD)));
        //endregion

        //region AMETHYST ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_HELMET, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .input('I', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_BOOTS)));
        //endregion

        //region CITRINE ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_HELMET, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .input('I', ModItems.CITRINE)
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.CITRINE)
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.CITRINE)
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.CITRINE)
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_BOOTS)));
        //endregion

        //region COPPER ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_HELMET, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .input('I', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_BOOTS)));
        //endregion

        // region COPPER ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_HELMET, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .input('I', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_BOOTS)));
        //endregion

        // region ENDERITE ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_HELMET, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .input('I', ModItems.ENDERITE)
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.ENDERITE)
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.ENDERITE)
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.ENDERITE)
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_BOOTS)));
        //endregion

        // region RUBY ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_HELMET, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .input('I', ModItems.RUBY)
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.RUBY)
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.RUBY)
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.RUBY)
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_BOOTS)));
        //endregion

        // region SAPPHIRE ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_HELMET, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("   ")
                .input('I', ModItems.SAPPHIRE)
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.SAPPHIRE)
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.SAPPHIRE)
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.SAPPHIRE)
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_BOOTS)));
        //endregion

        //region GEM BOWS
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOOL_ENDERITE_BOW, 1)
                .pattern(" IS")
                .pattern("G S")
                .pattern(" IS")
                .input('S', Items.STRING)
                .input('I', Items.STICK)
                .input('G', ModItems.ENDERITE)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_BOW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOOL_RUBY_BOW, 1)
                .pattern(" IS")
                .pattern("G S")
                .pattern(" IS")
                .input('S', Items.STRING)
                .input('I', Items.STICK)
                .input('G', ModItems.RUBY)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_BOW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOOL_SAPPHIRE_BOW, 1)
                .pattern(" IS")
                .pattern("G S")
                .pattern(" IS")
                .input('S', Items.STRING)
                .input('I', Items.STICK)
                .input('G', ModItems.SAPPHIRE)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_BOW)));
        //endregion
    }
}