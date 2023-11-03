package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider
{
    private static final List<ItemConvertible> CITRINE_SMELTABLES = List.of(
            ModItems.RAW_CITRINE
            //ModBlocks.ORE_WORLD_RUBY,
            //ModBlocks.ORE_DEEP_RUBY,
            //ModBlocks.ORE_END_RUBY,
            //ModBlocks.ORE_NETHER_RUBY
    );

    private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(
            ModItems.RAW_RUBY
            //ModBlocks.ORE_WORLD_RUBY,
            //ModBlocks.ORE_DEEP_RUBY,
            //ModBlocks.ORE_END_RUBY,
            //ModBlocks.ORE_NETHER_RUBY
    );

    private static final List<ItemConvertible> SAPPHIRE_SMELTABLES = List.of(
            ModItems.RAW_SAPPHIRE
            //ModBlocks.ORE_WORLD_RUBY,
            //ModBlocks.ORE_DEEP_RUBY,
            //ModBlocks.ORE_END_RUBY,
            //ModBlocks.ORE_NETHER_RUBY
    );

    private static final List<ItemConvertible> ENDERITE_SMELTABLES = List.of(
            ModItems.RAW_ENDERITE
            //ModBlocks.ORE_WORLD_RUBY,
            //ModBlocks.ORE_DEEP_RUBY,
            //ModBlocks.ORE_END_RUBY,
            //ModBlocks.ORE_NETHER_RUBY
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

        offerSmelting(exporter, ENDERITE_SMELTABLES, RecipeCategory.MISC, ModItems.ENDERITE, 0.7f, 400, "enderite");
        offerBlasting(exporter, ENDERITE_SMELTABLES, RecipeCategory.MISC, ModItems.ENDERITE, 0.7f, 200, "enderite");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CITRINE, RecipeCategory.DECORATIONS, ModBlocks.CITRINE);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.DECORATIONS, ModBlocks.RUBY);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.DECORATIONS, ModBlocks.SAPPHIRE);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ENDERITE, RecipeCategory.DECORATIONS, ModBlocks.ENDERITE);

        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 400, Items.ROTTEN_FLESH, Items.LEATHER, 0.1F);
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, 800, Items.ROTTEN_FLESH, Items.LEATHER, 0.1F);

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