package jiraiyah.allthatmatters.utils;

import jiraiyah.allthatmatters.AllThatMatters;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> GEM_BLOCKS = createCommonTag("gem_blocks");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, AllThatMatters.identifier(name));
        }

        private static TagKey<Block> createCommonTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }

    public static class Items
    {
        public static final TagKey<Item> FLUID_BUCKETS = createCommonTag("fluid_buckets");
        public static final TagKey<Item> TOOL_HAMMER = createCommonTag("hammers");
        public static final TagKey<Item> CASTS = createCommonTag("casts");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, AllThatMatters.identifier(name));
        }

        private static TagKey<Item> createCommonTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }
}