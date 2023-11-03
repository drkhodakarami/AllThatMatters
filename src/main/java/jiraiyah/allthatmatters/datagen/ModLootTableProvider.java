package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput)
    {
        super(dataOutput);
    }

    @Override
    public void generate()
    {
        addDrop(ModBlocks.CITRINE);
        addDrop(ModBlocks.ENDERITE);
        addDrop(ModBlocks.RUBY);
        addDrop(ModBlocks.SAPPHIRE);
        addDrop(ModBlocks.CHUNK_LOADER);
        addDrop(ModBlocks.SHULKER_NORMAL);
        addDrop(ModBlocks.SHULKER_BLACK);
        addDrop(ModBlocks.SHULKER_BLUE);
        addDrop(ModBlocks.SHULKER_BROWN);
        addDrop(ModBlocks.SHULKER_CYAN);
        addDrop(ModBlocks.SHULKER_GRAY);
        addDrop(ModBlocks.SHULKER_GREEN);
        addDrop(ModBlocks.SHULKER_LIGHT_BLUE);
        addDrop(ModBlocks.SHULKER_LIGHT_GRAY);
        addDrop(ModBlocks.SHULKER_LIME);
        addDrop(ModBlocks.SHULKER_MAGENTA);
        addDrop(ModBlocks.SHULKER_ORANGE);
        addDrop(ModBlocks.SHULKER_PINK);
        addDrop(ModBlocks.SHULKER_PURPLE);
        addDrop(ModBlocks.SHULKER_RED);
        addDrop(ModBlocks.SHULKER_WHITE);
        addDrop(ModBlocks.SHULKER_YELLOW);

        addDrop(ModBlocks.ORE_DEEP_CITRINE, customOreDrops(ModBlocks.ORE_DEEP_CITRINE, ModItems.RAW_CITRINE));
        addDrop(ModBlocks.ORE_END_CITRINE, customOreDrops(ModBlocks.ORE_END_CITRINE, ModItems.RAW_CITRINE));
        addDrop(ModBlocks.ORE_NETHER_CITRINE, customOreDrops(ModBlocks.ORE_NETHER_CITRINE, ModItems.RAW_CITRINE, 2.0f, 4.0f));
        addDrop(ModBlocks.ORE_WORLD_CITRINE, customOreDrops(ModBlocks.ORE_WORLD_CITRINE, ModItems.RAW_CITRINE, 2.0f, 3.0f));

        addDrop(ModBlocks.ORE_DEEP_RUBY, customOreDrops(ModBlocks.ORE_DEEP_RUBY, ModItems.RAW_RUBY));
        addDrop(ModBlocks.ORE_END_RUBY, customOreDrops(ModBlocks.ORE_END_RUBY, ModItems.RAW_RUBY));
        addDrop(ModBlocks.ORE_NETHER_RUBY, customOreDrops(ModBlocks.ORE_NETHER_RUBY, ModItems.RAW_RUBY, 2.0f, 4.0f));
        addDrop(ModBlocks.ORE_WORLD_RUBY, customOreDrops(ModBlocks.ORE_WORLD_RUBY, ModItems.RAW_RUBY, 2.0f, 3.0f));

        addDrop(ModBlocks.ORE_DEEP_SAPPHIRE, customOreDrops(ModBlocks.ORE_DEEP_SAPPHIRE, ModItems.RAW_SAPPHIRE));
        addDrop(ModBlocks.ORE_END_SAPPHIRE, customOreDrops(ModBlocks.ORE_END_SAPPHIRE, ModItems.RAW_SAPPHIRE));
        addDrop(ModBlocks.ORE_NETHER_SAPPHIRE, customOreDrops(ModBlocks.ORE_NETHER_SAPPHIRE, ModItems.RAW_SAPPHIRE, 2.0f, 4.0f));
        addDrop(ModBlocks.ORE_WORLD_SAPPHIRE, customOreDrops(ModBlocks.ORE_WORLD_SAPPHIRE, ModItems.RAW_SAPPHIRE, 2.0f, 3.0f));

        addDrop(ModBlocks.ORE_ENDERITE, customOreDrops(ModBlocks.ORE_ENDERITE, ModItems.RAW_ENDERITE, 1.0f, 2.0f));
    }

    public LootTable.Builder customOreDrops(Block drop, Item item, float min, float max)
    {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop,
                (LootPoolEntry.Builder)
                        this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(min, max))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }

    public LootTable.Builder customOreDrops(Block drop, Item item)
    {
        return customOreDrops(drop, item, 2.0f, 5.0f);
    }
}