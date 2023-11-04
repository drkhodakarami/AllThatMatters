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
    public static final RegistryKey<PlacedFeature> ORE_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_citrine_placed"));
    public static final RegistryKey<PlacedFeature> ORE_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_ruby_placed"));
    public static final RegistryKey<PlacedFeature> ORE_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_sapphire_placed"));

    public static final RegistryKey<PlacedFeature> ORE_NETHER_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_nether_citrine_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_nether_ruby_placed"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_nether_sapphire_placed"));

    public static final RegistryKey<PlacedFeature> ORE_END_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_citrine_placed"));
    public static final RegistryKey<PlacedFeature> ORE_END_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_ruby_placed"));
    public static final RegistryKey<PlacedFeature> ORE_END_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_sapphire_placed"));
    public static final RegistryKey<PlacedFeature> ORE_END_ENDERITE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_enderite_placed"));

    public static void bootstrap(Registerable<PlacedFeature> context)
    {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        //region OVERWORLD
        register(context, ORE_CITRINE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_CITRINE_KEY),
                ModOrePlacement.modifiersWithCount(8, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(20), YOffset.aboveBottom(110))));

        register(context, ORE_RUBY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_RUBY_KEY),
                ModOrePlacement.modifiersWithCount(8, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(20), YOffset.aboveBottom(110))));

        register(context, ORE_SAPPHIRE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SAPPHIRE_KEY),
                ModOrePlacement.modifiersWithCount(8, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(20), YOffset.aboveBottom(110))));
        //endregion

        //region NETHER
        register(context, ORE_NETHER_CITRINE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_CITRINE_KEY),
                ModOrePlacement.modifiersWithCount(6, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(10), YOffset.belowTop(10))));

        register(context, ORE_NETHER_RUBY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_RUBY_KEY),
                ModOrePlacement.modifiersWithCount(6, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(10), YOffset.belowTop(10))));

        register(context, ORE_NETHER_SAPPHIRE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_SAPPHIRE_KEY),
                ModOrePlacement.modifiersWithCount(6, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(10), YOffset.belowTop(10))));
        //endregion

        //region END
        register(context, ORE_END_CITRINE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_CITRINE_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(20), YOffset.aboveBottom(80))));

        register(context, ORE_END_RUBY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_RUBY_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(20), YOffset.aboveBottom(80))));

        register(context, ORE_END_SAPPHIRE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_SAPPHIRE_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(20), YOffset.aboveBottom(80))));

        register(context, ORE_END_ENDERITE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_END_ENDERITE_KEY),
                ModOrePlacement.modifiersWithCount(3, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(20), YOffset.aboveBottom(80))));
        //endregion
    }

    public static RegistryKey<PlacedFeature> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID, name));
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