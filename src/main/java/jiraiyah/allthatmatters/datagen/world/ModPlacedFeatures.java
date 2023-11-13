package jiraiyah.allthatmatters.datagen.world;

import jiraiyah.allthatmatters.AllThatMatters;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures
{
    public static final RegistryKey<PlacedFeature> ORE_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_citrine_placed"));
    public static final RegistryKey<PlacedFeature> ORE_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_ruby_placed"));
    public static final RegistryKey<PlacedFeature> ORE_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_sapphire_placed"));

    public static final RegistryKey<PlacedFeature> ORE_NETHER_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_citrine_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_ruby_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_sapphire_placed"));

    public static final RegistryKey<PlacedFeature> ORE_NETHER_COAL_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_coal_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_COPPER_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_copper_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_DIAMOND_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_diamond_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_IRON_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_iron_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_LAPIS_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_lapis_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_REDSTONE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_nether_redstone_placed"));

    public static final RegistryKey<PlacedFeature> ORE_END_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_end_citrine_placed"));
    public static final RegistryKey<PlacedFeature> ORE_END_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_end_ruby_placed"));
    public static final RegistryKey<PlacedFeature> ORE_END_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_end_sapphire_placed"));
    public static final RegistryKey<PlacedFeature> ORE_END_ENDERITE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier("ore_end_enderite_placed"));

    public static void bootstrap(Registerable<PlacedFeature> context)
    {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        //region OVERWORLD
        register(context, ORE_CITRINE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_CITRINE_KEY),
                ModOrePlacement.modifiersWithCount(10, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(55))));

        register(context, ORE_RUBY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_RUBY_KEY),
                ModOrePlacement.modifiersWithCount(10, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(55))));

        register(context, ORE_SAPPHIRE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SAPPHIRE_KEY),
                ModOrePlacement.modifiersWithCount(10, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(55))));
        //endregion

        //region NETHER
        register(context, ORE_NETHER_CITRINE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_CITRINE_KEY),
                ModOrePlacement.modifiersWithCount(6, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.belowTop(5))));

        register(context, ORE_NETHER_RUBY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_RUBY_KEY),
                ModOrePlacement.modifiersWithCount(6, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(5))));

        register(context, ORE_NETHER_SAPPHIRE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_SAPPHIRE_KEY),
                ModOrePlacement.modifiersWithCount(6, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(5))));

        register(context, ORE_NETHER_COAL_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_COAL_KEY),
                ModOrePlacement.modifiersWithCount(16, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(5))));

        register(context, ORE_NETHER_COPPER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_COPPER_KEY),
                ModOrePlacement.modifiersWithCount(10, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(5))));

        register(context, ORE_NETHER_DIAMOND_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_DIAMOND_KEY),
                ModOrePlacement.modifiersWithCount(4, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.aboveBottom(42))));

        register(context, ORE_NETHER_IRON_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_IRON_KEY),
                ModOrePlacement.modifiersWithCount(14, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(5))));

        register(context, ORE_NETHER_LAPIS_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_LAPIS_KEY),
                ModOrePlacement.modifiersWithCount(10, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(5))));

        register(context, ORE_NETHER_REDSTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_REDSTONE_KEY),
                ModOrePlacement.modifiersWithCount(10, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(5))));
        //endregion

        //region END
        register(context, ORE_END_CITRINE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_CITRINE_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(50), YOffset.fixed(65))));

        register(context, ORE_END_RUBY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_RUBY_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(50), YOffset.fixed(65))));

        register(context, ORE_END_SAPPHIRE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_SAPPHIRE_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(50), YOffset.fixed(65))));

        register(context, ORE_END_ENDERITE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_ENDERITE_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(50), YOffset.fixed(65))));
        //endregion
    }

    public static RegistryKey<PlacedFeature> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, AllThatMatters.identifier(name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context,
                                                                                   RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers)
    {
        register(context, key, configuration, List.of(modifiers));
    }
}