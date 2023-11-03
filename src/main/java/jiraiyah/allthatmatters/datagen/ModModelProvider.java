package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    /**
     *
     * The method to create BLOCK MODELS
     *
     * @param blockStateModelGenerator
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CITRINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDERITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_DEEP_CITRINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_DEEP_RUBY);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_DEEP_SAPPHIRE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_END_CITRINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_END_ENDERITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_END_RUBY);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_END_SAPPHIRE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_NETHER_CITRINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_NETHER_RUBY);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_NETHER_SAPPHIRE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_WORLD_CITRINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_WORLD_RUBY);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_WORLD_SAPPHIRE);

        blockStateModelGenerator.registerSingleton(ModBlocks.ORE_ENDERITE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(ModBlocks.CHUNK_LOADER, TexturedModel.CUBE_BOTTOM_TOP);

        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_NORMAL, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_BLACK, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_BLUE, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_BROWN, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_CYAN, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_GRAY, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_GREEN, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_LIGHT_BLUE, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_LIGHT_GRAY, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_LIME, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_MAGENTA, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_ORANGE, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_PINK, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_RED, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_WHITE, TexturedModel.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.SHULKER_YELLOW, TexturedModel.CUBE_COLUMN);
    }

    /**
     *
     * The method to create ITEM MODELS
     *
     * @param itemModelGenerator
     */
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(ModItems.CITRINE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CITRINE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_RUBY, Models.GENERATED);

        itemModelGenerator.register(ModItems.SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SAPPHIRE, Models.GENERATED);

        itemModelGenerator.register(ModItems.ENDERITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ENDERITE, Models.GENERATED);
    }
}