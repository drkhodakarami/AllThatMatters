package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.utils.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg)
    {
        getOrCreateTagBuilder(ModTags.Items.FLUID_BUCKETS)
                .add(ModFluids.MOLTEN_ENDERITE_BUCKET)
                .add(Items.LAVA_BUCKET)
                .add(Items.WATER_BUCKET)
                .add(Items.MILK_BUCKET);

        getOrCreateTagBuilder(ModTags.Items.CASTS)
                .add(ModItems.CAST_AXE)
                .add(ModItems.CAST_BINDING)
                .add(ModItems.CAST_GEAR)
                .add(ModItems.CAST_GEM)
                .add(ModItems.CAST_HAMMER)
                .add(ModItems.CAST_HANDLE)
                .add(ModItems.CAST_HOE)
                .add(ModItems.CAST_INGOT)
                .add(ModItems.CAST_NUGGET)
                .add(ModItems.CAST_PICKAXE)
                .add(ModItems.CAST_PLATE)
                .add(ModItems.CAST_ROD)
                .add(ModItems.CAST_SHOVEL)
                .add(ModItems.CAST_SWORD)
                .add(ModItems.CAST_WIRE);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.TOOL_CITRINE_AXE)
                .add(ModItems.TOOL_COPPER_AXE)
                .add(ModItems.TOOL_ENDERITE_AXE)
                .add(ModItems.TOOL_RUBY_AXE)
                .add(ModItems.TOOL_SAPPHIRE_AXE);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.TOOL_CITRINE_HOE)
                .add(ModItems.TOOL_COPPER_HOE)
                .add(ModItems.TOOL_ENDERITE_HOE)
                .add(ModItems.TOOL_RUBY_HOE)
                .add(ModItems.TOOL_SAPPHIRE_HOE);

        getOrCreateTagBuilder(ModTags.Items.TOOL_HAMMER)
                .add(ModItems.TOOL_CITRINE_HAMMER)
                .add(ModItems.TOOL_COPPER_HAMMER)
                .add(ModItems.TOOL_DIAMOND_HAMMER)
                .add(ModItems.TOOL_ENDERITE_HAMMER)
                .add(ModItems.TOOL_GOLD_HAMMER)
                .add(ModItems.TOOL_IRON_HAMMER)
                .add(ModItems.TOOL_NETHERITE_HAMMER)
                .add(ModItems.TOOL_RUBY_HAMMER)
                .add(ModItems.TOOL_SAPPHIRE_HAMMER)
                .add(ModItems.TOOL_STONE_HAMMER)
                .add(ModItems.TOOL_WOOD_HAMMER);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.TOOL_CITRINE_PICKAXE)
                .add(ModItems.TOOL_COPPER_PICKAXE)
                .add(ModItems.TOOL_ENDERITE_PICKAXE)
                .add(ModItems.TOOL_RUBY_PICKAXE)
                .add(ModItems.TOOL_SAPPHIRE_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.TOOL_CITRINE_SHOVEL)
                .add(ModItems.TOOL_COPPER_SHOVEL)
                .add(ModItems.TOOL_ENDERITE_SHOVEL)
                .add(ModItems.TOOL_RUBY_SHOVEL)
                .add(ModItems.TOOL_SAPPHIRE_SHOVEL);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.TOOL_CITRINE_SWORD)
                .add(ModItems.TOOL_COPPER_SWORD)
                .add(ModItems.TOOL_ENDERITE_SWORD)
                .add(ModItems.TOOL_RUBY_SWORD)
                .add(ModItems.TOOL_SAPPHIRE_SWORD);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ARMOR_AMETHYST_HELMET)
                .add(ModItems.ARMOR_AMETHYST_CHESTPLATE)
                .add(ModItems.ARMOR_AMETHYST_LEGGINGS)
                .add(ModItems.ARMOR_AMETHYST_BOOTS)
                .add(ModItems.ARMOR_CITRINE_HELMET)
                .add(ModItems.ARMOR_CITRINE_CHESTPLATE)
                .add(ModItems.ARMOR_CITRINE_LEGGINGS)
                .add(ModItems.ARMOR_CITRINE_BOOTS)
                .add(ModItems.ARMOR_COPPER_HELMET)
                .add(ModItems.ARMOR_COPPER_CHESTPLATE)
                .add(ModItems.ARMOR_COPPER_LEGGINGS)
                .add(ModItems.ARMOR_COPPER_BOOTS)
                .add(ModItems.ARMOR_EMERALD_HELMET)
                .add(ModItems.ARMOR_EMERALD_CHESTPLATE)
                .add(ModItems.ARMOR_EMERALD_LEGGINGS)
                .add(ModItems.ARMOR_EMERALD_BOOTS)
                .add(ModItems.ARMOR_ENDERITE_HELMET)
                .add(ModItems.ARMOR_ENDERITE_CHESTPLATE)
                .add(ModItems.ARMOR_ENDERITE_LEGGINGS)
                .add(ModItems.ARMOR_ENDERITE_BOOTS)
                .add(ModItems.ARMOR_RUBY_HELMET)
                .add(ModItems.ARMOR_RUBY_CHESTPLATE)
                .add(ModItems.ARMOR_RUBY_LEGGINGS)
                .add(ModItems.ARMOR_RUBY_BOOTS)
                .add(ModItems.ARMOR_SAPPHIRE_HELMET)
                .add(ModItems.ARMOR_SAPPHIRE_CHESTPLATE)
                .add(ModItems.ARMOR_SAPPHIRE_LEGGINGS)
                .add(ModItems.ARMOR_SAPPHIRE_BOOTS);
    }
}