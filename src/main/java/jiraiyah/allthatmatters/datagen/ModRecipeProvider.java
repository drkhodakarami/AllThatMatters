package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.datagen.recipe.GemCleanserRecipeBuilder;
import jiraiyah.allthatmatters.datagen.recipe.SmelteryRecipeBuilder;
import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter)
    {
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
                .pattern("RRR")
                .pattern("SBS")
                .pattern("SFS")
                .input('R', Items.IRON_INGOT)
                .input('F', Items.FURNACE)
                .input('B', Items.BUCKET)
                .input('S', ModItems.PLATE_IRON)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.FURNACE), conditionsFromItem(Items.FURNACE))
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CAST_PRESS, 1)
                .pattern("W#W")
                .pattern("#S#")
                .pattern("SFS")
                .input('#', Items.IRON_INGOT)
                .input('F', Items.FURNACE)
                .input('W', ItemTags.PLANKS)
                .input('S', Items.SMOOTH_STONE)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CAST_PRESS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SMELTERY, 1)
                .pattern("###")
                .pattern("#B#")
                .pattern("#F#")
                .input('#', Items.IRON_INGOT)
                .input('F', Items.FURNACE)
                .input('B', Items.BUCKET)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SMELTERY)));
        //endregion

        //region CITRINE TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_AXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_CITRINE_AXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_HOE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_CITRINE_HOE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_CITRINE_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_PICKAXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_CITRINE_PICKAXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_CITRINE_SHOVEL, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_CITRINE_SHOVEL)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_CITRINE_SWORD, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_CITRINE_SWORD)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.CITRINE), conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_CITRINE_SWORD)));
        //endregion

        //region COPPER TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_AXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_COPPER_AXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_HOE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_COPPER_HOE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_COPPER_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_PICKAXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_COPPER_PICKAXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_COPPER_SHOVEL, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_COPPER_SHOVEL)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_COPPER_SWORD, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_COPPER_SWORD)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_COPPER_SWORD)));
        //endregion

        //region VANILLA HAMMERS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_DIAMOND_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_DIAMOND_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_DIAMOND_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_GOLD_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_GOLD_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_GOLD_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_IRON_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_IRON_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_IRON_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_NETHERITE_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_NETHERITE_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_NETHERITE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_STONE_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_STONE_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion("has_cobblestone", conditionsFromTag(ItemTags.STONE_TOOL_MATERIALS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_STONE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_WOOD_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_WOOD_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion("has_planks", conditionsFromTag(ItemTags.PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_WOOD_HAMMER)));
        //endregion

        //region ENDERITE TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_AXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_ENDERITE_AXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_HOE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_ENDERITE_HOE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_ENDERITE_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_PICKAXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_ENDERITE_PICKAXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_ENDERITE_SHOVEL, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_ENDERITE_SHOVEL)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_ENDERITE_SWORD, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_ENDERITE_SWORD)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_SWORD)));
        //endregion

        //region RUBY TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_AXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_RUBY_AXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_HOE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_RUBY_HOE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_RUBY_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_PICKAXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_RUBY_PICKAXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_RUBY_SHOVEL, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_RUBY_SHOVEL)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_RUBY_SWORD, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_RUBY_SWORD)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_SWORD)));
        //endregion

        //region SAPPHIRE TOOLS
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_AXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_SAPPHIRE_AXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_HOE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_SAPPHIRE_HOE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_HAMMER, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_SAPPHIRE_HAMMER)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_PICKAXE, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_SAPPHIRE_PICKAXE)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.TOOL_SAPPHIRE_SHOVEL, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_SAPPHIRE_SHOVEL)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOOL_SAPPHIRE_SWORD, 1)
                .pattern("H")
                .pattern("B")
                .pattern("S")
                .input('H', ModItems.HEAD_SAPPHIRE_SWORD)
                .input('B', ModItems.BINDING)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_SWORD)));
        //endregion

        //region AMETHYST ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_HELMET, 1)
                .pattern("III")
                .pattern("IGI")
                .pattern("   ")
                .input('I', ModItems.REINFORCED_PLATE_AMETHYST)
                .input('G', ModItems.GEAR_DIAMOND)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_AMETHYST), conditionsFromItem(ModItems.REINFORCED_PLATE_AMETHYST))
                .criterion(hasItem(ModItems.GEAR_DIAMOND), conditionsFromItem(ModItems.GEAR_DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_CHESTPLATE, 1)
                .pattern("IGI")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.REINFORCED_PLATE_AMETHYST)
                .input('G', ModItems.GEAR_DIAMOND)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_AMETHYST), conditionsFromItem(ModItems.REINFORCED_PLATE_AMETHYST))
                .criterion(hasItem(ModItems.GEAR_DIAMOND), conditionsFromItem(ModItems.GEAR_DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_LEGGINGS, 1)
                .pattern("IGI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_AMETHYST)
                .input('G', ModItems.GEAR_DIAMOND)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_AMETHYST), conditionsFromItem(ModItems.REINFORCED_PLATE_AMETHYST))
                .criterion(hasItem(ModItems.GEAR_DIAMOND), conditionsFromItem(ModItems.GEAR_DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_AMETHYST_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_AMETHYST)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_AMETHYST), conditionsFromItem(ModItems.REINFORCED_PLATE_AMETHYST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_AMETHYST_BOOTS)));
        //endregion

        //region CITRINE ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_HELMET, 1)
                .pattern("III")
                .pattern("IGI")
                .pattern("   ")
                .input('I', ModItems.REINFORCED_PLATE_CITRINE)
                .input('G', ModItems.GEAR_GOLD)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_CITRINE), conditionsFromItem(ModItems.REINFORCED_PLATE_CITRINE))
                .criterion(hasItem(ModItems.GEAR_GOLD), conditionsFromItem(ModItems.GEAR_GOLD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_CHESTPLATE, 1)
                .pattern("IGI")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.REINFORCED_PLATE_CITRINE)
                .input('G', ModItems.GEAR_GOLD)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_CITRINE), conditionsFromItem(ModItems.REINFORCED_PLATE_CITRINE))
                .criterion(hasItem(ModItems.GEAR_GOLD), conditionsFromItem(ModItems.GEAR_GOLD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_LEGGINGS, 1)
                .pattern("IGI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_CITRINE)
                .input('G', ModItems.GEAR_GOLD)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_CITRINE), conditionsFromItem(ModItems.REINFORCED_PLATE_CITRINE))
                .criterion(hasItem(ModItems.GEAR_GOLD), conditionsFromItem(ModItems.GEAR_GOLD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_CITRINE_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_CITRINE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_CITRINE), conditionsFromItem(ModItems.REINFORCED_PLATE_CITRINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_CITRINE_BOOTS)));
        //endregion

        //region COPPER ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_HELMET, 1)
                .pattern("III")
                .pattern("IGI")
                .pattern("   ")
                .input('I', ModItems.REINFORCED_PLATE_COPPER)
                .input('G', ModItems.GEAR_COPPER)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_COPPER), conditionsFromItem(ModItems.REINFORCED_PLATE_COPPER))
                .criterion(hasItem(ModItems.GEAR_COPPER), conditionsFromItem(ModItems.GEAR_COPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_CHESTPLATE, 1)
                .pattern("IGI")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.REINFORCED_PLATE_COPPER)
                .input('G', ModItems.GEAR_COPPER)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_COPPER), conditionsFromItem(ModItems.REINFORCED_PLATE_COPPER))
                .criterion(hasItem(ModItems.GEAR_COPPER), conditionsFromItem(ModItems.GEAR_COPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_LEGGINGS, 1)
                .pattern("IGI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_COPPER)
                .input('G', ModItems.GEAR_COPPER)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_COPPER), conditionsFromItem(ModItems.REINFORCED_PLATE_COPPER))
                .criterion(hasItem(ModItems.GEAR_COPPER), conditionsFromItem(ModItems.GEAR_COPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_COPPER_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_COPPER)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_COPPER), conditionsFromItem(ModItems.REINFORCED_PLATE_COPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_COPPER_BOOTS)));
        //endregion

        // region EMERALD ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_HELMET, 1)
                .pattern("III")
                .pattern("IGI")
                .pattern("   ")
                .input('I', ModItems.REINFORCED_PLATE_EMERALD)
                .input('G', ModItems.GEAR_EMERALD)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_EMERALD), conditionsFromItem(ModItems.REINFORCED_PLATE_EMERALD))
                .criterion(hasItem(ModItems.GEAR_EMERALD), conditionsFromItem(ModItems.GEAR_EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_CHESTPLATE, 1)
                .pattern("IGI")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.REINFORCED_PLATE_EMERALD)
                .input('G', ModItems.GEAR_EMERALD)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_EMERALD), conditionsFromItem(ModItems.REINFORCED_PLATE_EMERALD))
                .criterion(hasItem(ModItems.GEAR_EMERALD), conditionsFromItem(ModItems.GEAR_EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_LEGGINGS, 1)
                .pattern("IGI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_EMERALD)
                .input('G', ModItems.GEAR_EMERALD)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_EMERALD), conditionsFromItem(ModItems.REINFORCED_PLATE_EMERALD))
                .criterion(hasItem(ModItems.GEAR_EMERALD), conditionsFromItem(ModItems.GEAR_EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_EMERALD_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_EMERALD)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_EMERALD), conditionsFromItem(ModItems.REINFORCED_PLATE_EMERALD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_EMERALD_BOOTS)));
        //endregion

        // region ENDERITE ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_HELMET, 1)
                .pattern("III")
                .pattern("IGI")
                .pattern("   ")
                .input('I', ModItems.REINFORCED_PLATE_ENDERITE)
                .input('G', ModItems.GEAR_ENDERITE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_ENDERITE), conditionsFromItem(ModItems.REINFORCED_PLATE_ENDERITE))
                .criterion(hasItem(ModItems.GEAR_ENDERITE), conditionsFromItem(ModItems.GEAR_ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_CHESTPLATE, 1)
                .pattern("IGI")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.REINFORCED_PLATE_ENDERITE)
                .input('G', ModItems.GEAR_ENDERITE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_ENDERITE), conditionsFromItem(ModItems.REINFORCED_PLATE_ENDERITE))
                .criterion(hasItem(ModItems.GEAR_ENDERITE), conditionsFromItem(ModItems.GEAR_ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_LEGGINGS, 1)
                .pattern("IGI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_ENDERITE)
                .input('G', ModItems.GEAR_ENDERITE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_ENDERITE), conditionsFromItem(ModItems.REINFORCED_PLATE_ENDERITE))
                .criterion(hasItem(ModItems.GEAR_ENDERITE), conditionsFromItem(ModItems.GEAR_ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_ENDERITE_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_ENDERITE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_ENDERITE), conditionsFromItem(ModItems.REINFORCED_PLATE_ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_ENDERITE_BOOTS)));
        //endregion

        // region RUBY ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_HELMET, 1)
                .pattern("III")
                .pattern("IGI")
                .pattern("   ")
                .input('I', ModItems.REINFORCED_PLATE_RUBY)
                .input('G', ModItems.GEAR_RUBY)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_RUBY), conditionsFromItem(ModItems.REINFORCED_PLATE_RUBY))
                .criterion(hasItem(ModItems.GEAR_RUBY), conditionsFromItem(ModItems.GEAR_RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_CHESTPLATE, 1)
                .pattern("IGI")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.REINFORCED_PLATE_RUBY)
                .input('G', ModItems.GEAR_RUBY)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_RUBY), conditionsFromItem(ModItems.REINFORCED_PLATE_RUBY))
                .criterion(hasItem(ModItems.GEAR_RUBY), conditionsFromItem(ModItems.GEAR_RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_LEGGINGS, 1)
                .pattern("IGI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_RUBY)
                .input('G', ModItems.GEAR_RUBY)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_RUBY), conditionsFromItem(ModItems.REINFORCED_PLATE_RUBY))
                .criterion(hasItem(ModItems.GEAR_RUBY), conditionsFromItem(ModItems.GEAR_RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_RUBY_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_RUBY)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_RUBY), conditionsFromItem(ModItems.REINFORCED_PLATE_RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_RUBY_BOOTS)));
        //endregion

        // region SAPPHIRE ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_HELMET, 1)
                .pattern("III")
                .pattern("IGI")
                .pattern("   ")
                .input('I', ModItems.REINFORCED_PLATE_SAPPHIRE)
                .input('G', ModItems.GEAR_SAPPHIRE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_SAPPHIRE), conditionsFromItem(ModItems.REINFORCED_PLATE_SAPPHIRE))
                .criterion(hasItem(ModItems.GEAR_SAPPHIRE), conditionsFromItem(ModItems.GEAR_SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_CHESTPLATE, 1)
                .pattern("IGI")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.REINFORCED_PLATE_SAPPHIRE)
                .input('G', ModItems.GEAR_SAPPHIRE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_SAPPHIRE), conditionsFromItem(ModItems.REINFORCED_PLATE_SAPPHIRE))
                .criterion(hasItem(ModItems.GEAR_SAPPHIRE), conditionsFromItem(ModItems.GEAR_SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_LEGGINGS, 1)
                .pattern("IGI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_SAPPHIRE)
                .input('G', ModItems.GEAR_SAPPHIRE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_SAPPHIRE), conditionsFromItem(ModItems.REINFORCED_PLATE_SAPPHIRE))
                .criterion(hasItem(ModItems.GEAR_SAPPHIRE), conditionsFromItem(ModItems.GEAR_SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARMOR_SAPPHIRE_BOOTS, 1)
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.REINFORCED_PLATE_SAPPHIRE)
                .criterion(hasItem(ModItems.REINFORCED_PLATE_SAPPHIRE), conditionsFromItem(ModItems.REINFORCED_PLATE_SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMOR_SAPPHIRE_BOOTS)));
        //endregion

        // region NETHERITE ARMOR
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.NETHERITE_HELMET, 1)
                .pattern("IRI")
                .pattern("I I")
                .pattern("   ")
                .input('I', ModItems.PLATE_NETHERITE)
                .input('R', ModItems.REINFORCED_PLATE_NETHERITE)
                .criterion(hasItem(ModItems.PLATE_NETHERITE), conditionsFromItem(ModItems.PLATE_NETHERITE))
                .offerTo(exporter, new Identifier(getRecipeName(Items.NETHERITE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.NETHERITE_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("IRI")
                .pattern("IRI")
                .input('I', ModItems.PLATE_NETHERITE)
                .input('R', ModItems.REINFORCED_PLATE_NETHERITE)
                .criterion(hasItem(ModItems.PLATE_NETHERITE), conditionsFromItem(ModItems.PLATE_NETHERITE))
                .offerTo(exporter, new Identifier(getRecipeName(Items.NETHERITE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.NETHERITE_LEGGINGS, 1)
                .pattern("IRI")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.PLATE_NETHERITE)
                .input('R', ModItems.REINFORCED_PLATE_NETHERITE)
                .criterion(hasItem(ModItems.PLATE_NETHERITE), conditionsFromItem(ModItems.PLATE_NETHERITE))
                .offerTo(exporter, new Identifier(getRecipeName(Items.NETHERITE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.NETHERITE_BOOTS, 1)
                .pattern("I I")
                .pattern("R R")
                .input('I', ModItems.PLATE_NETHERITE)
                .input('R', ModItems.REINFORCED_PLATE_NETHERITE)
                .criterion(hasItem(ModItems.PLATE_NETHERITE), conditionsFromItem(ModItems.PLATE_NETHERITE))
                .offerTo(exporter, new Identifier(getRecipeName(Items.NETHERITE_BOOTS)));
        //endregion

        //region GEM BOWS
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOOL_ENDERITE_BOW, 1)
                .pattern(" GB")
                .pattern("O S")
                .pattern(" GB")
                .input('S', Items.STRING)
                .input('G', ModItems.GEAR_ENDERITE)
                .input('O', ModItems.ROD_OBSIDIAN)
                .input('B', ModItems.BINDING)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_ENDERITE_BOW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOOL_RUBY_BOW, 1)
                .pattern(" GB")
                .pattern("O S")
                .pattern(" GB")
                .input('S', Items.STRING)
                .input('G', ModItems.GEAR_RUBY)
                .input('O', ModItems.ROD_OBSIDIAN)
                .input('B', ModItems.BINDING)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_RUBY_BOW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOOL_SAPPHIRE_BOW, 1)
                .pattern(" GB")
                .pattern("O S")
                .pattern(" GB")
                .input('S', Items.STRING)
                .input('G', ModItems.GEAR_SAPPHIRE)
                .input('O', ModItems.ROD_OBSIDIAN)
                .input('B', ModItems.BINDING)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOOL_SAPPHIRE_BOW)));
        //endregion

        //region BACKPACK
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BACKPACK, 1)
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .input('#', Items.LEATHER)
                .input('C', Items.CHEST)
                .criterion(hasItem(Items.CHEST), conditionsFromItem(Items.CHEST))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BACKPACK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDER_BACKPACK, 1)
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .input('#', Items.LEATHER)
                .input('C', Items.ENDER_CHEST)
                .criterion(hasItem(Items.ENDER_CHEST), conditionsFromItem(Items.ENDER_CHEST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDER_BACKPACK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDER_BACKPACK, 1)
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .input('#', ModItems.ENDERITE)
                .input('C', ModItems.BACKPACK)
                .criterion(hasItem(ModItems.ENDERITE), conditionsFromItem(ModItems.ENDERITE))
                .criterion(hasItem(ModItems.BACKPACK), conditionsFromItem(ModItems.BACKPACK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDER_BACKPACK) + ".enderite"));
        //endregion

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PLAYER_TELEPORT, 1)
                .pattern("PEP")
                .pattern("DCD")
                .pattern("GGG")
                .input('G', ModItems.REINFORCED_PLATE_GOLD)
                .input('D', ModItems.REINFORCED_PLATE_DIAMOND)
                .input('C', Items.COMPASS)
                .input('P', Items.ENDER_PEARL)
                .input('E', Items.ENDER_EYE)
                .criterion(hasItem(Items.COMPASS), conditionsFromItem(Items.COMPASS))
                .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                .criterion(hasItem(ModItems.REINFORCED_PLATE_GOLD), conditionsFromItem(ModItems.REINFORCED_PLATE_GOLD))
                .criterion(hasItem(ModItems.REINFORCED_PLATE_DIAMOND), conditionsFromItem(ModItems.REINFORCED_PLATE_DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PLAYER_TELEPORT)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NAME_TAG, 1)
                .input(Items.PAPER)
                .input(ModItems.ROD_IRON)
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(Items.NAME_TAG) + "shapeless"));

        //region GEM CLEANSER
        new GemCleanserRecipeBuilder(ModItems.RAW_CITRINE, 1, ModItems.CITRINE, 2,144,200)
                .criterion(hasItem(ModItems.RAW_CITRINE), conditionsFromItem(ModItems.RAW_CITRINE))
                .offerTo(exporter);

        new GemCleanserRecipeBuilder(ModItems.RAW_ENDERITE, 1, ModItems.ENDERITE, 1, 576, 500)
                .criterion(hasItem(ModItems.RAW_ENDERITE), conditionsFromItem(ModItems.RAW_ENDERITE))
                .offerTo(exporter);

        new GemCleanserRecipeBuilder(ModItems.RAW_RUBY, 1, ModItems.RUBY, 2, 144, 200)
                .criterion(hasItem(ModItems.RAW_RUBY), conditionsFromItem(ModItems.RAW_RUBY))
                .offerTo(exporter);

        new GemCleanserRecipeBuilder(ModItems.RAW_SAPPHIRE, 1, ModItems.SAPPHIRE, 2,144,200)
                .criterion(hasItem(ModItems.RAW_SAPPHIRE), conditionsFromItem(ModItems.RAW_SAPPHIRE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - BINDING
        new SmelteryRecipeBuilder(Items.STRING, 2, ModItems.CAST_BINDING ,
                ModItems.BINDING,1,18,10, "string")
                .criterion(hasItem(Items.STRING),conditionsFromItem(Items.STRING))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.WOOL,1, ModItems.CAST_BINDING ,
                ModItems.BINDING,4,36,50, "wool")
                .criterion("has_wool",conditionsFromTag(ItemTags.WOOL))
                .offerTo(exporter);
        //endregion

        //region SMELTER - WIRE
        new SmelteryRecipeBuilder(ItemTags.WOOL,1, ModItems.CAST_WIRE ,
                Items.STRING,8,36,50)
                .criterion("has_wool",conditionsFromTag(ItemTags.WOOL))
                .offerTo(exporter);
        //endregion

        //region SMELTER - CHARCOAL
        new SmelteryRecipeBuilder(ItemTags.PLANKS,1, ModItems.CAST_GEM ,
                Items.CHARCOAL,4, 144,125, "planks")
                .criterion("has_planks",conditionsFromTag(ItemTags.PLANKS))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.LOGS,1, ModItems.CAST_GEM ,
                Items.CHARCOAL,16, 576,500, "logs")
                .criterion("has_logs",conditionsFromTag(ItemTags.LOGS))
                .offerTo(exporter);
        //endregion

        //region SMELTER - AXE HEAD
        new SmelteryRecipeBuilder(ModItems.CITRINE,1, ModItems.CAST_AXE ,
                ModItems.HEAD_CITRINE_AXE,1, 144,200)
                .criterion(hasItem(ModItems.CITRINE),conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_AXE ,
                ModItems.HEAD_COPPER_AXE,1, 144,200)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,1, ModItems.CAST_AXE ,
                ModItems.HEAD_DIAMOND_AXE,1, 144,200)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_AXE ,
                ModItems.HEAD_ENDERITE_AXE,1, 144,200)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_AXE ,
                ModItems.HEAD_GOLD_AXE,1, 144,200)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_AXE ,
                ModItems.HEAD_IRON_AXE,1, 144,200)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,1, ModItems.CAST_AXE ,
                ModItems.HEAD_NETHERITE_AXE,1, 144,200)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,1, ModItems.CAST_AXE ,
                ModItems.HEAD_RUBY_AXE,1, 144,200)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,1, ModItems.CAST_AXE ,
                ModItems.HEAD_SAPPHIRE_AXE,1, 144,200)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - HAMMER HEAD
        new SmelteryRecipeBuilder(ModItems.CITRINE,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_CITRINE_HAMMER,1, 144,250)
                .criterion(hasItem(ModItems.CITRINE),conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_COPPER_HAMMER,1, 144,250)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_DIAMOND_HAMMER,1, 144,250)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_ENDERITE_HAMMER,1, 144,250)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_GOLD_HAMMER,1, 144,250)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_IRON_HAMMER,1, 144,250)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_NETHERITE_HAMMER,1, 144,250)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_RUBY_HAMMER,1, 144,250)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_SAPPHIRE_HAMMER,1, 144,250)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.STONE_TOOL_MATERIALS,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_STONE_HAMMER,1, 144,250)
                .criterion("has_cobblestone",conditionsFromTag(ItemTags.STONE_TOOL_MATERIALS))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.PLANKS,1, ModItems.CAST_HAMMER ,
                ModItems.HEAD_WOOD_HAMMER,1, 144,250)
                .criterion("has_planks",conditionsFromTag(ItemTags.PLANKS))
                .offerTo(exporter);
        //endregion

        //region SMELTER - HOE HEAD
        new SmelteryRecipeBuilder(ModItems.CITRINE,1, ModItems.CAST_HOE ,
                ModItems.HEAD_CITRINE_HOE,1, 144,100)
                .criterion(hasItem(ModItems.CITRINE),conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_HOE ,
                ModItems.HEAD_COPPER_HOE,1, 144,100)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,1, ModItems.CAST_HOE ,
                ModItems.HEAD_DIAMOND_HOE,1, 144,100)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_HOE ,
                ModItems.HEAD_ENDERITE_HOE,1, 144,100)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_HOE ,
                ModItems.HEAD_GOLD_HOE,1, 144,100)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_HOE ,
                ModItems.HEAD_IRON_HOE,1, 144,100)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,1, ModItems.CAST_HOE ,
                ModItems.HEAD_NETHERITE_HOE,1, 144,100)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,1, ModItems.CAST_HOE ,
                ModItems.HEAD_RUBY_HOE,1, 144,100)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,1, ModItems.CAST_HOE ,
                ModItems.HEAD_SAPPHIRE_HOE,1, 144,100)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - PICKAXE HEAD
        new SmelteryRecipeBuilder(ModItems.CITRINE,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_CITRINE_PICKAXE,1, 144,200)
                .criterion(hasItem(ModItems.CITRINE),conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_COPPER_PICKAXE,1, 144,200)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_DIAMOND_PICKAXE,1, 144,200)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_ENDERITE_PICKAXE,1, 144,200)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_GOLD_PICKAXE,1, 144,200)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_IRON_PICKAXE,1, 144,200)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_WOOD_PICKAXE ,
                ModItems.HEAD_IRON_PICKAXE,1, 144,200, "wood")
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_NETHERITE_PICKAXE,1, 144,200)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_RUBY_PICKAXE,1, 144,200)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,1, ModItems.CAST_PICKAXE ,
                ModItems.HEAD_SAPPHIRE_PICKAXE,1, 144,200)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - SHOVEL HEAD
        new SmelteryRecipeBuilder(ModItems.CITRINE,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_CITRINE_SHOVEL,1, 144,200)
                .criterion(hasItem(ModItems.CITRINE),conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_COPPER_SHOVEL,1, 144,200)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_DIAMOND_SHOVEL,1, 144,200)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_ENDERITE_SHOVEL,1, 144,200)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_GOLD_SHOVEL,1, 144,200)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_IRON_SHOVEL,1, 144,200)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_NETHERITE_SHOVEL,1, 144,200)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_RUBY_SHOVEL,1, 144,200)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,1, ModItems.CAST_SHOVEL ,
                ModItems.HEAD_SAPPHIRE_SHOVEL,1, 144,200)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - SWORD HEAD
        new SmelteryRecipeBuilder(ModItems.CITRINE,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_CITRINE_SWORD,1, 144,250)
                .criterion(hasItem(ModItems.CITRINE),conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_COPPER_SWORD,1, 144,250)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_DIAMOND_SWORD,1, 144,250)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_ENDERITE_SWORD,1, 144,250)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_GOLD_SWORD,1, 144,250)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_IRON_SWORD,1, 144,250)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_NETHERITE_SWORD,1, 144,250)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_RUBY_SWORD,1, 144,250)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,1, ModItems.CAST_SWORD ,
                ModItems.HEAD_SAPPHIRE_SWORD,1, 144,250)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - PLATE
        new SmelteryRecipeBuilder(Items.AMETHYST_SHARD,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_AMETHYST,1,576,250)
                .criterion(hasItem(Items.AMETHYST_SHARD),conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.CITRINE,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_CITRINE,1, 576,250)
                .criterion(hasItem(ModItems.CITRINE),conditionsFromItem(ModItems.CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.COPPER_INGOT,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_COPPER,1, 576,250)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_DIAMOND,1, 576,250)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.EMERALD,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_EMERALD,1, 576,250)
                .criterion(hasItem(Items.EMERALD),conditionsFromItem(Items.EMERALD))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_ENDERITE,1, 576,250)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_GOLD,1, 576,250)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_IRON,1, 576,250)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_NETHERITE,1, 576,250)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_RUBY,1, 576,250)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_SAPPHIRE,1, 576,250)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.STONE_CRAFTING_MATERIALS,4, ModItems.CAST_PLATE ,
                ModItems.PLATE_STONE,1, 576,250)
                .criterion("has_cobblestone",conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                .offerTo(exporter);
        //endregion

        //region SMELTER - REINFORCED PLATE
        new SmelteryRecipeBuilder(ModItems.PLATE_AMETHYST,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_AMETHYST,1,2304,1000)
                .criterion(hasItem(ModItems.PLATE_AMETHYST),conditionsFromItem(ModItems.PLATE_AMETHYST))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_CITRINE,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_CITRINE,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_CITRINE),conditionsFromItem(ModItems.PLATE_CITRINE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_COPPER,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_COPPER,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_COPPER),conditionsFromItem(ModItems.PLATE_COPPER))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_DIAMOND,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_DIAMOND,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_DIAMOND),conditionsFromItem(ModItems.PLATE_DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_EMERALD,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_EMERALD,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_EMERALD),conditionsFromItem(ModItems.PLATE_EMERALD))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_ENDERITE,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_ENDERITE,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_ENDERITE),conditionsFromItem(ModItems.PLATE_ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_GOLD,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_GOLD,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_GOLD),conditionsFromItem(ModItems.PLATE_GOLD))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_IRON,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_IRON,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_IRON),conditionsFromItem(ModItems.PLATE_IRON))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_NETHERITE,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_NETHERITE,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_NETHERITE),conditionsFromItem(ModItems.PLATE_NETHERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_RUBY,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_RUBY,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_RUBY),conditionsFromItem(ModItems.PLATE_RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.PLATE_SAPPHIRE,4, ModItems.CAST_PLATE ,
                ModItems.REINFORCED_PLATE_SAPPHIRE,1, 2304,1000)
                .criterion(hasItem(ModItems.PLATE_SAPPHIRE),conditionsFromItem(ModItems.PLATE_SAPPHIRE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - GEAR
        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_COPPER,1,288,250)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.DIAMOND,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_DIAMOND,1,288,250)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.EMERALD,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_EMERALD,1,288,250)
                .criterion(hasItem(Items.EMERALD),conditionsFromItem(Items.EMERALD))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_ENDERITE,1,288,250)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_GOLD,1,288,250)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_IRON,1,288,250)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.LAPIS_LAZULI,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_LAPIS,1,288,250)
                .criterion(hasItem(Items.LAPIS_LAZULI),conditionsFromItem(Items.LAPIS_LAZULI))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHERITE_INGOT,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_NETHERITE,1,288,250)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.OBSIDIAN,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_OBSIDIAN,1,288,250)
                .criterion(hasItem(Items.OBSIDIAN),conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.PRISMARINE_SHARD,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_PRISMARINE,1,288,250)
                .criterion(hasItem(Items.PRISMARINE_SHARD),conditionsFromItem(Items.PRISMARINE_SHARD))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.QUARTZ,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_QUARTZ,1,288,250)
                .criterion(hasItem(Items.QUARTZ),conditionsFromItem(Items.QUARTZ))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.RUBY,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_RUBY,1,288,250)
                .criterion(hasItem(ModItems.RUBY),conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.SAPPHIRE,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_SAPPHIRE,1,288,250)
                .criterion(hasItem(ModItems.SAPPHIRE),conditionsFromItem(ModItems.SAPPHIRE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.STONE_CRAFTING_MATERIALS,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_STONE,1,288,250)
                .criterion("has_cobblestone",conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.PLANKS,1, ModItems.CAST_GEAR ,
                ModItems.GEAR_WOOD,1,288,250)
                .criterion("has_planks",conditionsFromTag(ItemTags.PLANKS))
                .offerTo(exporter);
        //endregion

        //region SMELTER - ROD
        new SmelteryRecipeBuilder(Items.COPPER_INGOT,1, ModItems.CAST_ROD ,
                ModItems.ROD_COPPER,1,288,150)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ModItems.ENDERITE,1, ModItems.CAST_ROD ,
                ModItems.ROD_ENDERITE,1,288,150)
                .criterion(hasItem(ModItems.ENDERITE),conditionsFromItem(ModItems.ENDERITE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GLOWSTONE_DUST,2, ModItems.CAST_ROD ,
                ModItems.ROD_GLOWSTONE,1,288,150)
                .criterion(hasItem(Items.GLOWSTONE_DUST),conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.GOLD_INGOT,1, ModItems.CAST_ROD ,
                ModItems.ROD_GOLD,1,288,150)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.IRON_INGOT,1, ModItems.CAST_ROD ,
                ModItems.ROD_IRON,1,288,150)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.OBSIDIAN,1, ModItems.CAST_ROD ,
                ModItems.ROD_OBSIDIAN,1,288,150)
                .criterion(hasItem(Items.OBSIDIAN),conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter);
        //endregion

        //region SMELTER - ORE
        new SmelteryRecipeBuilder(ItemTags.COAL_ORES,1, ModItems.CAST_GEM ,
                Items.COAL,2, 288,250)
                .criterion("has_coal",conditionsFromTag(ItemTags.COAL_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.COPPER_ORES,1, ModItems.CAST_INGOT ,
                Items.COPPER_INGOT,4, 288,250, "tag")
                .criterion("has_copper",conditionsFromTag(ItemTags.COPPER_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.DIAMOND_ORES,1, ModItems.CAST_GEM ,
                Items.DIAMOND,2, 288,250)
                .criterion("has_diamond",conditionsFromTag(ItemTags.DIAMOND_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.EMERALD_ORES,1, ModItems.CAST_GEM ,
                Items.EMERALD,2, 288,250)
                .criterion("has_emerald",conditionsFromTag(ItemTags.EMERALD_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.GOLD_ORES,1, ModItems.CAST_INGOT ,
                Items.GOLD_INGOT,4, 288,250, "tag")
                .criterion("has_gold",conditionsFromTag(ItemTags.GOLD_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.IRON_ORES,1, ModItems.CAST_INGOT ,
                Items.IRON_INGOT,4, 288,250, "tag")
                .criterion("has_iron",conditionsFromTag(ItemTags.IRON_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.IRON_ORES,1, ModItems.CAST_WOOD_INGOT ,
                Items.IRON_INGOT,4, 288,250, "wood")
                .criterion("has_iron",conditionsFromTag(ItemTags.IRON_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.LAPIS_ORES,1, ModItems.CAST_GEM ,
                Items.LAPIS_LAZULI,18, 288,250)
                .criterion("has_lapis_lazuli",conditionsFromTag(ItemTags.LAPIS_ORES))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.NETHER_QUARTZ_ORE,1, ModItems.CAST_GEM ,
                Items.QUARTZ,2, 288,250)
                .criterion(hasItem(Items.NETHER_QUARTZ_ORE),conditionsFromItem(Items.NETHER_QUARTZ_ORE))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.RAW_COPPER_BLOCK,1, ModItems.CAST_INGOT ,
                Items.COPPER_INGOT,18, 3000,2250, "raw_block")
                .criterion(hasItem(Items.RAW_COPPER_BLOCK),conditionsFromItem(Items.RAW_COPPER_BLOCK))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.RAW_GOLD_BLOCK,1, ModItems.CAST_INGOT ,
                Items.GOLD_INGOT,18, 3000,2250, "raw_block")
                .criterion(hasItem(Items.RAW_GOLD_BLOCK),conditionsFromItem(Items.RAW_GOLD_BLOCK))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.RAW_IRON_BLOCK,1, ModItems.CAST_INGOT ,
                Items.IRON_INGOT,18, 3000,2250, "raw_block")
                .criterion(hasItem(Items.RAW_IRON_BLOCK),conditionsFromItem(Items.RAW_IRON_BLOCK))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.RAW_COPPER,1, ModItems.CAST_INGOT ,
                Items.COPPER_INGOT,2, 288,250, "raw")
                .criterion(hasItem(Items.RAW_COPPER),conditionsFromItem(Items.RAW_COPPER))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.RAW_GOLD,1, ModItems.CAST_INGOT ,
                Items.GOLD_INGOT,2, 288,250, "raw")
                .criterion(hasItem(Items.RAW_GOLD),conditionsFromItem(Items.RAW_GOLD))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.RAW_IRON,1, ModItems.CAST_INGOT ,
                Items.IRON_INGOT,2, 288,250, "raw")
                .criterion(hasItem(Items.RAW_IRON),conditionsFromItem(Items.RAW_IRON))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.REDSTONE_ORE,1, ModItems.CAST_GEM ,
                Items.REDSTONE,10, 288,250)
                .criterion(hasItem(Items.REDSTONE_ORE),conditionsFromItem(Items.REDSTONE_ORE))
                .offerTo(exporter);
        //endregion

        //region SMELTER - STICK
        new SmelteryRecipeBuilder(ItemTags.PLANKS,1, ModItems.CAST_HANDLE ,
                Items.STICK,8, 36,50, "planks")
                .criterion("has_planks",conditionsFromTag(ItemTags.PLANKS))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(ItemTags.LOGS,1, ModItems.CAST_HANDLE ,
                Items.STICK,32, 144,200, "logs")
                .criterion("has_logs",conditionsFromTag(ItemTags.LOGS))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.BAMBOO,1, ModItems.CAST_HANDLE ,
                Items.STICK,4, 36,50, "bamboo")
                .criterion(hasItem(Items.BAMBOO),conditionsFromItem(Items.BAMBOO))
                .offerTo(exporter);

        new SmelteryRecipeBuilder(Items.BAMBOO_BLOCK,1, ModItems.CAST_HANDLE ,
                Items.STICK,32, 288,400, "bamboo_block")
                .criterion(hasItem(Items.BAMBOO_BLOCK),conditionsFromItem(Items.BAMBOO_BLOCK))
                .offerTo(exporter);
        //endregion
    }
}