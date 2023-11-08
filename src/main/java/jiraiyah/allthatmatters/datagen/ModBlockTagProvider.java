package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.utils.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg)
    {
        getOrCreateTagBuilder(ModTags.Blocks.GEM_BLOCKS)
                .add(ModBlocks.RUBY)
                .add(ModBlocks.SAPPHIRE)
                .add(ModBlocks.CITRINE)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.CITRINE)
                .add(ModBlocks.ENDERITE)
                .add(ModBlocks.RUBY)
                .add(ModBlocks.SAPPHIRE)
                .add(ModBlocks.ORE_DEEP_CITRINE)
                .add(ModBlocks.ORE_DEEP_RUBY)
                .add(ModBlocks.ORE_DEEP_SAPPHIRE)
                .add(ModBlocks.ORE_END_CITRINE)
                .add(ModBlocks.ORE_END_ENDERITE)
                .add(ModBlocks.ORE_END_RUBY)
                .add(ModBlocks.ORE_END_SAPPHIRE)
                .add(ModBlocks.ORE_NETHER_CITRINE)
                .add(ModBlocks.ORE_NETHER_RUBY)
                .add(ModBlocks.ORE_NETHER_SAPPHIRE)
                .add(ModBlocks.ORE_WORLD_CITRINE)
                .add(ModBlocks.ORE_WORLD_RUBY)
                .add(ModBlocks.ORE_WORLD_SAPPHIRE)
                .add(ModBlocks.ORE_ENDERITE)
                .add(ModBlocks.SHULKER_NORMAL)
                .add(ModBlocks.SHULKER_BLACK)
                .add(ModBlocks.SHULKER_BLUE)
                .add(ModBlocks.SHULKER_BROWN)
                .add(ModBlocks.SHULKER_CYAN)
                .add(ModBlocks.SHULKER_GRAY)
                .add(ModBlocks.SHULKER_GREEN)
                .add(ModBlocks.SHULKER_LIGHT_BLUE)
                .add(ModBlocks.SHULKER_LIGHT_GRAY)
                .add(ModBlocks.SHULKER_LIME)
                .add(ModBlocks.SHULKER_MAGENTA)
                .add(ModBlocks.SHULKER_ORANGE)
                .add(ModBlocks.SHULKER_PINK)
                .add(ModBlocks.SHULKER_PURPLE)
                .add(ModBlocks.SHULKER_RED)
                .add(ModBlocks.SHULKER_WHITE)
                .add(ModBlocks.SHULKER_YELLOW)
                .add(ModBlocks.INFUSING_STATION);

        /*getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);*/

        /*getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);*/

        /*getOrCreateTagBuilder(BlockTags.HOE_MINEABLE);*/

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ORE_DEEP_CITRINE)
                .add(ModBlocks.ORE_DEEP_RUBY)
                .add(ModBlocks.ORE_DEEP_SAPPHIRE)
                .add(ModBlocks.ORE_END_CITRINE)
                .add(ModBlocks.ORE_END_RUBY)
                .add(ModBlocks.ORE_END_SAPPHIRE)
                .add(ModBlocks.ORE_NETHER_CITRINE)
                .add(ModBlocks.ORE_NETHER_RUBY)
                .add(ModBlocks.ORE_NETHER_SAPPHIRE)
                .add(ModBlocks.ORE_WORLD_CITRINE)
                .add(ModBlocks.ORE_WORLD_RUBY)
                .add(ModBlocks.ORE_WORLD_SAPPHIRE)
                .add(ModBlocks.INFUSING_STATION);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CITRINE)
                .add(ModBlocks.RUBY)
                .add(ModBlocks.SAPPHIRE)
                .add(ModBlocks.ENDERITE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ORE_ENDERITE);

        /*getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")));*/
    }
}