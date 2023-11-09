package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
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

        itemModelGenerator.register(ModItems.TOOL_CITRINE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_CITRINE_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_CITRINE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_CITRINE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_CITRINE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_CITRINE_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.TOOL_COPPER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_COPPER_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_COPPER_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_COPPER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_COPPER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_COPPER_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.TOOL_DIAMOND_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_GOLD_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_IRON_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_NETHERITE_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_STONE_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_WOOD_HAMMER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.TOOL_ENDERITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_ENDERITE_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_ENDERITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_ENDERITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_ENDERITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_ENDERITE_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.TOOL_RUBY_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_RUBY_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_RUBY_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_RUBY_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_RUBY_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_RUBY_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.TOOL_SAPPHIRE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_SAPPHIRE_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_SAPPHIRE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_SAPPHIRE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_SAPPHIRE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TOOL_SAPPHIRE_SWORD, Models.HANDHELD);

        //itemModelGenerator.register(ModItems.TOOL_RUBY_BOW, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_AMETHYST_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_AMETHYST_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_AMETHYST_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_AMETHYST_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_CITRINE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_CITRINE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_CITRINE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_CITRINE_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_COPPER_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_COPPER_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_COPPER_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_COPPER_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_EMERALD_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_EMERALD_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_EMERALD_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_EMERALD_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_ENDERITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_ENDERITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_ENDERITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_ENDERITE_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_RUBY_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_RUBY_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_RUBY_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_RUBY_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_SAPPHIRE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_SAPPHIRE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_SAPPHIRE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARMOR_SAPPHIRE_BOOTS));
    }
}