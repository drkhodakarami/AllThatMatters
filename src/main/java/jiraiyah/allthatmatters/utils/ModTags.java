package jiraiyah.allthatmatters.utils;

import jiraiyah.allthatmatters.AllThatMatters;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> GEM_BLOCKS = createCommonTag("gem_blocks");
        public static final TagKey<Block> HAMMER_NO_SMASHY = createTag("hammer_no_smashy");

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
        public static final TagKey<Item> WOOD_CASTS = createCommonTag("wood_casts");
        public static final TagKey<Item> GEAR = createCommonTag("gears");
        public static final TagKey<Item> PLATE = createCommonTag("plates");
        public static final TagKey<Item> REINFORCED = createCommonTag("reinforced_plates");
        public static final TagKey<Item> ROD = createCommonTag("rods");
        public static final TagKey<Item> SMELTERY = createTag("smeltery");
        public static final TagKey<Item> NO_ENCHANT = createTag("only_mending_allowed");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, AllThatMatters.identifier(name));
        }

        private static TagKey<Item> createCommonTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }

    public static class Entities
    {
        public static final TagKey<EntityType<?>> RUBY_SWORD_IGNITES = createTag("ruby_sword_ignites");

        private static TagKey<EntityType<?>> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, AllThatMatters.identifier(name));
        }
    }
}