package jiraiyah.allthatmatters.item;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.fluid.ModFluids;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModItemGroups
{
    public static final ItemGroup ATM_GROUP = Registry.register(Registries.ITEM_GROUP,
            AllThatMatters.identifier("allthatmattersgroup"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.allthatmattersgroup"))
                    .icon(() -> new ItemStack(ModBlocks.CHUNK_LOADER)).entries((displayContext, entries) ->
                    {
                        entries.add(ModBlocks.CHUNK_LOADER);
                        entries.add(ModBlocks.GEM_CLEANSER);

                        entries.add(ModItems.CITRINE);
                        entries.add(ModItems.RAW_CITRINE);
                        entries.add(ModBlocks.CITRINE);
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModBlocks.RUBY);
                        entries.add(ModItems.SAPPHIRE);
                        entries.add(ModItems.RAW_SAPPHIRE);
                        entries.add(ModBlocks.SAPPHIRE);

                        entries.add(ModItems.ENDERITE);
                        entries.add(ModItems.RAW_ENDERITE);
                        entries.add(ModBlocks.ENDERITE);

                        entries.add(ModFluids.MOLTEN_ENDERITE_BUCKET);

                        entries.add(ModItems.CAST_AXE);
                        entries.add(ModItems.CAST_BINDING);
                        entries.add(ModItems.CAST_GEAR);
                        entries.add(ModItems.CAST_GEM);
                        entries.add(ModItems.CAST_HAMMER);
                        entries.add(ModItems.CAST_HANDLE);
                        entries.add(ModItems.CAST_HOE);
                        entries.add(ModItems.CAST_INGOT);
                        entries.add(ModItems.CAST_NUGGET);
                        entries.add(ModItems.CAST_PICKAXE);
                        entries.add(ModItems.CAST_PLATE);
                        entries.add(ModItems.CAST_ROD);
                        entries.add(ModItems.CAST_SHOVEL);
                        entries.add(ModItems.CAST_SWORD);
                        entries.add(ModItems.CAST_WIRE);

                        entries.add(ModBlocks.SHULKER_NORMAL);
                        entries.add(ModBlocks.SHULKER_BLACK);
                        entries.add(ModBlocks.SHULKER_BLUE);
                        entries.add(ModBlocks.SHULKER_BROWN);
                        entries.add(ModBlocks.SHULKER_CYAN);
                        entries.add(ModBlocks.SHULKER_GRAY);
                        entries.add(ModBlocks.SHULKER_GREEN);
                        entries.add(ModBlocks.SHULKER_LIGHT_BLUE);
                        entries.add(ModBlocks.SHULKER_LIGHT_GRAY);
                        entries.add(ModBlocks.SHULKER_LIME);
                        entries.add(ModBlocks.SHULKER_MAGENTA);
                        entries.add(ModBlocks.SHULKER_ORANGE);
                        entries.add(ModBlocks.SHULKER_PINK);
                        entries.add(ModBlocks.SHULKER_PURPLE);
                        entries.add(ModBlocks.SHULKER_RED);
                        entries.add(ModBlocks.SHULKER_WHITE);
                        entries.add(ModBlocks.SHULKER_YELLOW);

                        entries.add(ModItems.HEAD_CITRINE_AXE);
                        entries.add(ModItems.HEAD_CITRINE_HAMMER);
                        entries.add(ModItems.HEAD_CITRINE_HOE);
                        entries.add(ModItems.HEAD_CITRINE_PICKAXE);
                        entries.add(ModItems.HEAD_CITRINE_SHOVEL);
                        entries.add(ModItems.HEAD_CITRINE_SWORD);

                        entries.add(ModItems.HEAD_COPPER_AXE);
                        entries.add(ModItems.HEAD_COPPER_HAMMER);
                        entries.add(ModItems.HEAD_COPPER_HOE);
                        entries.add(ModItems.HEAD_COPPER_PICKAXE);
                        entries.add(ModItems.HEAD_COPPER_SHOVEL);
                        entries.add(ModItems.HEAD_COPPER_SWORD);

                        entries.add(ModItems.HEAD_DIAMOND_AXE);
                        entries.add(ModItems.HEAD_DIAMOND_HAMMER);
                        entries.add(ModItems.HEAD_DIAMOND_HOE);
                        entries.add(ModItems.HEAD_DIAMOND_PICKAXE);
                        entries.add(ModItems.HEAD_DIAMOND_SHOVEL);
                        entries.add(ModItems.HEAD_DIAMOND_SWORD);

                        entries.add(ModItems.HEAD_ENDERITE_AXE);
                        entries.add(ModItems.HEAD_ENDERITE_HAMMER);
                        entries.add(ModItems.HEAD_ENDERITE_HOE);
                        entries.add(ModItems.HEAD_ENDERITE_PICKAXE);
                        entries.add(ModItems.HEAD_ENDERITE_SHOVEL);
                        entries.add(ModItems.HEAD_ENDERITE_SWORD);

                        entries.add(ModItems.HEAD_GOLD_AXE);
                        entries.add(ModItems.HEAD_GOLD_HAMMER);
                        entries.add(ModItems.HEAD_GOLD_HOE);
                        entries.add(ModItems.HEAD_GOLD_PICKAXE);
                        entries.add(ModItems.HEAD_GOLD_SHOVEL);
                        entries.add(ModItems.HEAD_GOLD_SWORD);

                        entries.add(ModItems.HEAD_IRON_AXE);
                        entries.add(ModItems.HEAD_IRON_HAMMER);
                        entries.add(ModItems.HEAD_IRON_HOE);
                        entries.add(ModItems.HEAD_IRON_PICKAXE);
                        entries.add(ModItems.HEAD_IRON_SHOVEL);
                        entries.add(ModItems.HEAD_IRON_SWORD);

                        entries.add(ModItems.HEAD_NETHERITE_AXE);
                        entries.add(ModItems.HEAD_NETHERITE_HAMMER);
                        entries.add(ModItems.HEAD_NETHERITE_HOE);
                        entries.add(ModItems.HEAD_NETHERITE_PICKAXE);
                        entries.add(ModItems.HEAD_NETHERITE_SHOVEL);
                        entries.add(ModItems.HEAD_NETHERITE_SWORD);

                        entries.add(ModItems.HEAD_RUBY_AXE);
                        entries.add(ModItems.HEAD_RUBY_HAMMER);
                        entries.add(ModItems.HEAD_RUBY_HOE);
                        entries.add(ModItems.HEAD_RUBY_PICKAXE);
                        entries.add(ModItems.HEAD_RUBY_SHOVEL);
                        entries.add(ModItems.HEAD_RUBY_SWORD);

                        entries.add(ModItems.HEAD_SAPPHIRE_AXE);
                        entries.add(ModItems.HEAD_SAPPHIRE_HAMMER);
                        entries.add(ModItems.HEAD_SAPPHIRE_HOE);
                        entries.add(ModItems.HEAD_SAPPHIRE_PICKAXE);
                        entries.add(ModItems.HEAD_SAPPHIRE_SHOVEL);
                        entries.add(ModItems.HEAD_SAPPHIRE_SWORD);

                        entries.add(ModItems.HEAD_STONE_HAMMER);
                        entries.add(ModItems.HEAD_WOOD_HAMMER);

                        entries.add(ModItems.TOOL_CITRINE_AXE);
                        entries.add(ModItems.TOOL_CITRINE_HAMMER);
                        entries.add(ModItems.TOOL_CITRINE_HOE);
                        entries.add(ModItems.TOOL_CITRINE_PICKAXE);
                        entries.add(ModItems.TOOL_CITRINE_SHOVEL);
                        entries.add(ModItems.TOOL_CITRINE_SWORD);

                        entries.add(ModItems.TOOL_COPPER_AXE);
                        entries.add(ModItems.TOOL_COPPER_HAMMER);
                        entries.add(ModItems.TOOL_COPPER_HOE);
                        entries.add(ModItems.TOOL_COPPER_PICKAXE);
                        entries.add(ModItems.TOOL_COPPER_SHOVEL);
                        entries.add(ModItems.TOOL_COPPER_SWORD);

                        entries.add(ModItems.TOOL_DIAMOND_HAMMER);

                        entries.add(ModItems.TOOL_ENDERITE_AXE);
                        entries.add(ModItems.TOOL_ENDERITE_BOW);
                        entries.add(ModItems.TOOL_ENDERITE_HAMMER);
                        entries.add(ModItems.TOOL_ENDERITE_HOE);
                        entries.add(ModItems.TOOL_ENDERITE_PICKAXE);
                        entries.add(ModItems.TOOL_ENDERITE_SHOVEL);
                        entries.add(ModItems.TOOL_ENDERITE_SWORD);

                        entries.add(ModItems.TOOL_GOLD_HAMMER);
                        entries.add(ModItems.TOOL_IRON_HAMMER);
                        entries.add(ModItems.TOOL_NETHERITE_HAMMER);

                        entries.add(ModItems.TOOL_RUBY_AXE);
                        entries.add(ModItems.TOOL_RUBY_BOW);
                        entries.add(ModItems.TOOL_RUBY_HAMMER);
                        entries.add(ModItems.TOOL_RUBY_HOE);
                        entries.add(ModItems.TOOL_RUBY_PICKAXE);
                        entries.add(ModItems.TOOL_RUBY_SHOVEL);
                        entries.add(ModItems.TOOL_RUBY_SWORD);

                        entries.add(ModItems.TOOL_SAPPHIRE_AXE);
                        entries.add(ModItems.TOOL_SAPPHIRE_BOW);
                        entries.add(ModItems.TOOL_SAPPHIRE_HAMMER);
                        entries.add(ModItems.TOOL_SAPPHIRE_HOE);
                        entries.add(ModItems.TOOL_SAPPHIRE_PICKAXE);
                        entries.add(ModItems.TOOL_SAPPHIRE_SHOVEL);
                        entries.add(ModItems.TOOL_SAPPHIRE_SWORD);

                        entries.add(ModItems.TOOL_STONE_HAMMER);
                        entries.add(ModItems.TOOL_WOOD_HAMMER);

                        entries.add(ModItems.ARMOR_AMETHYST_HELMET);
                        entries.add(ModItems.ARMOR_CITRINE_HELMET);
                        entries.add(ModItems.ARMOR_COPPER_HELMET);
                        entries.add(ModItems.ARMOR_EMERALD_HELMET);
                        entries.add(ModItems.ARMOR_ENDERITE_HELMET);
                        entries.add(ModItems.ARMOR_RUBY_HELMET);
                        entries.add(ModItems.ARMOR_SAPPHIRE_HELMET);

                        entries.add(ModItems.ARMOR_AMETHYST_CHESTPLATE);
                        entries.add(ModItems.ARMOR_CITRINE_CHESTPLATE);
                        entries.add(ModItems.ARMOR_COPPER_CHESTPLATE);
                        entries.add(ModItems.ARMOR_EMERALD_CHESTPLATE);
                        entries.add(ModItems.ARMOR_ENDERITE_CHESTPLATE);
                        entries.add(ModItems.ARMOR_RUBY_CHESTPLATE);
                        entries.add(ModItems.ARMOR_SAPPHIRE_CHESTPLATE);

                        entries.add(ModItems.ARMOR_AMETHYST_LEGGINGS);
                        entries.add(ModItems.ARMOR_CITRINE_LEGGINGS);
                        entries.add(ModItems.ARMOR_COPPER_LEGGINGS);
                        entries.add(ModItems.ARMOR_EMERALD_LEGGINGS);
                        entries.add(ModItems.ARMOR_ENDERITE_LEGGINGS);
                        entries.add(ModItems.ARMOR_RUBY_LEGGINGS);
                        entries.add(ModItems.ARMOR_SAPPHIRE_LEGGINGS);

                        entries.add(ModItems.ARMOR_AMETHYST_BOOTS);
                        entries.add(ModItems.ARMOR_CITRINE_BOOTS);
                        entries.add(ModItems.ARMOR_COPPER_BOOTS);
                        entries.add(ModItems.ARMOR_EMERALD_BOOTS);
                        entries.add(ModItems.ARMOR_ENDERITE_BOOTS);
                        entries.add(ModItems.ARMOR_RUBY_BOOTS);
                        entries.add(ModItems.ARMOR_SAPPHIRE_BOOTS);

                        entries.add(ModBlocks.ORE_DEEP_CITRINE);
                        entries.add(ModBlocks.ORE_DEEP_RUBY);
                        entries.add(ModBlocks.ORE_DEEP_SAPPHIRE);
                        entries.add(ModBlocks.ORE_END_CITRINE);
                        entries.add(ModBlocks.ORE_END_ENDERITE);
                        entries.add(ModBlocks.ORE_END_RUBY);
                        entries.add(ModBlocks.ORE_END_SAPPHIRE);
                        entries.add(ModBlocks.ORE_NETHER_CITRINE);
                        entries.add(ModBlocks.ORE_NETHER_RUBY);
                        entries.add(ModBlocks.ORE_NETHER_SAPPHIRE);
                        entries.add(ModBlocks.ORE_WORLD_CITRINE);
                        entries.add(ModBlocks.ORE_WORLD_RUBY);
                        entries.add(ModBlocks.ORE_WORLD_SAPPHIRE);
                        entries.add(ModBlocks.ORE_ENDERITE);


                    }).build());

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Item Groups");
    }
}