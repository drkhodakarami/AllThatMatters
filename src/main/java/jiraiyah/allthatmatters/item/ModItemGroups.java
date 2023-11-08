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
import net.minecraft.util.Identifier;

public class ModItemGroups
{
    public static final ItemGroup ATM_GROUP = Registry.register(Registries.ITEM_GROUP,
            AllThatMatters.identifier("allthatmattersgroup"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.allthatmattersgroup"))
                    .icon(() -> new ItemStack(ModBlocks.CHUNK_LOADER)).entries((displayContext, entries) ->
                    {
                        entries.add(ModBlocks.CHUNK_LOADER);
                        entries.add(ModBlocks.INFUSING_STATION);

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

                    }).build());

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Item Groups");
    }
}