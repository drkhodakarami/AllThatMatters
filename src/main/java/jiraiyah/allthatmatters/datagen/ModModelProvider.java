package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    /**
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
    }

    /**
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

        itemModelGenerator.register(ModFluids.MOLTEN_ENDERITE_BUCKET, Models.GENERATED);
    }
}