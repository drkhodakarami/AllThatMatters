package jiraiyah.allthatmatters.block;

import dev.architectury.registry.registries.RegistrySupplier;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.custom.EnderiteOre;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModBlocks
{
    public static final Block CITRINE = registerBlock("block_citrine",
            new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));

    public static final Block ENDERITE = registerBlock("block_enderite",
            new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK)));

    public static final Block RUBY = registerBlock("block_ruby",
            new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));

    public static final Block SAPPHIRE = registerBlock("block_sapphire",
            new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));

    public static final Block ORE_DEEP_CITRINE = registerBlock("ore_deep_citrine",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)));
    public static final Block ORE_DEEP_RUBY = registerBlock("ore_deep_ruby",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)));
    public static final Block ORE_DEEP_SAPPHIRE = registerBlock("ore_deep_sapphire",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)));
    public static final Block ORE_END_CITRINE = registerBlock("ore_end_citrine",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
    public static final Block ORE_END_ENDERITE = registerBlock("ore_end_enderite",
            new EnderiteOre(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE)));
    public static final Block ORE_END_RUBY = registerBlock("ore_end_ruby",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
    public static final Block ORE_END_SAPPHIRE = registerBlock("ore_end_sapphire",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
    public static final Block ORE_NETHER_CITRINE = registerBlock("ore_nether_citrine",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE)));
    public static final Block ORE_NETHER_RUBY = registerBlock("ore_nether_ruby",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE)));
    public static final Block ORE_NETHER_SAPPHIRE = registerBlock("ore_nether_sapphire",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE)));
    public static final Block ORE_WORLD_CITRINE = registerBlock("ore_world_citrine",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block ORE_WORLD_RUBY = registerBlock("ore_world_ruby",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block ORE_WORLD_SAPPHIRE = registerBlock("ore_world_sapphire",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));

    public static final Block ORE_ENDERITE = registerBlock("ore_enderite",
            new Block(FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS)));

    public static final Block CHUNK_LOADER = registerBlock("chunk_loader",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block SHULKER_NORMAL = registerShulkerBlock("enderite_shulker_", "normal",
            new EnderiteShulkerBoxBlock(null));
    public static final Block SHULKER_BLACK = registerShulkerBlock("enderite_shulker_", "black",
            new EnderiteShulkerBoxBlock(DyeColor.BLACK));
    public static final Block SHULKER_BLUE = registerShulkerBlock("enderite_shulker_", "blue",
            new EnderiteShulkerBoxBlock(DyeColor.BLUE));
    public static final Block SHULKER_BROWN = registerShulkerBlock("enderite_shulker_", "brown",
            new EnderiteShulkerBoxBlock(DyeColor.BROWN));
    public static final Block SHULKER_CYAN = registerShulkerBlock("enderite_shulker_", "cyan",
            new EnderiteShulkerBoxBlock(DyeColor.CYAN));
    public static final Block SHULKER_GRAY = registerShulkerBlock("enderite_shulker_", "gray",
            new EnderiteShulkerBoxBlock(DyeColor.GRAY));
    public static final Block SHULKER_GREEN = registerShulkerBlock("enderite_shulker_", "green",
            new EnderiteShulkerBoxBlock(DyeColor.GREEN));
    public static final Block SHULKER_LIGHT_BLUE = registerShulkerBlock("enderite_shulker_", "light_blue",
            new EnderiteShulkerBoxBlock(DyeColor.LIGHT_BLUE));
    public static final Block SHULKER_LIGHT_GRAY = registerShulkerBlock("enderite_shulker_", "light_gray",
            new EnderiteShulkerBoxBlock(DyeColor.LIGHT_GRAY));
    public static final Block SHULKER_LIME = registerShulkerBlock("enderite_shulker_", "lime",
            new EnderiteShulkerBoxBlock(DyeColor.LIME));
    public static final Block SHULKER_MAGENTA = registerShulkerBlock("enderite_shulker_", "magenta",
            new EnderiteShulkerBoxBlock(DyeColor.MAGENTA));
    public static final Block SHULKER_ORANGE = registerShulkerBlock("enderite_shulker_", "orange",
            new EnderiteShulkerBoxBlock(DyeColor.ORANGE));
    public static final Block SHULKER_PINK = registerShulkerBlock("enderite_shulker_", "pink",
            new EnderiteShulkerBoxBlock(DyeColor.PINK));
    public static final Block SHULKER_RED = registerShulkerBlock("enderite_shulker_", "red",
            new EnderiteShulkerBoxBlock(DyeColor.RED));
    public static final Block SHULKER_WHITE = registerShulkerBlock("enderite_shulker_", "white",
            new EnderiteShulkerBoxBlock(DyeColor.WHITE));
    public static final Block SHULKER_YELLOW = registerShulkerBlock("enderite_shulker_", "yellow",
            new EnderiteShulkerBoxBlock(DyeColor.YELLOW));

    //region Helper Methods
    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(AllThatMatters.ModID, name), block);
    }

    private static Block registerShulkerBlock(String name, String color, Block block)
    {
        Registry.register(Registries.ITEM, new Identifier(AllThatMatters.ModID, name + color),
                new BlockItem(block, new FabricItemSettings().fireproof()));
        return Registry.register(Registries.BLOCK, new Identifier(AllThatMatters.ModID, name + color), block);
    }

    private static Item registerBlockItem(String name, Block block)
    {
        return Registry.register(Registries.ITEM, new Identifier(AllThatMatters.ModID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Blocks");
    }
    //endregion
}