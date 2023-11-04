package jiraiyah.allthatmatters.datagen;

import jiraiyah.allthatmatters.AllThatMatters;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldgen
{
    public static final RegistryKey<PlacedFeature> ORE_DEEP_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_deep_citrine"));
    public static final RegistryKey<PlacedFeature> ORE_DEEP_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_deep_ruby"));
    public static final RegistryKey<PlacedFeature> ORE_DEEP_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_deep_sapphire"));

    public static final RegistryKey<PlacedFeature> ORE_WORLD_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_world_citrine"));
    public static final RegistryKey<PlacedFeature> ORE_WORLD_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_world_ruby"));
    public static final RegistryKey<PlacedFeature> ORE_WORLD_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_world_sapphire"));

    public static final RegistryKey<PlacedFeature> ORE_NETHER_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_nether_citrine"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_nether_ruby"));
    public static final RegistryKey<PlacedFeature> ORE_NETHER_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_nether_sapphire"));

    public static final RegistryKey<PlacedFeature> ORE_END_CITRINE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_citrine"));
    public static final RegistryKey<PlacedFeature> ORE_END_RUBY_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_ruby"));
    public static final RegistryKey<PlacedFeature> ORE_END_SAPPHIRE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_sapphire"));
    public static final RegistryKey<PlacedFeature> ORE_END_ENDERITE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(AllThatMatters.ModID,"ore_end_enderite"));

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Worldgen");

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_DEEP_CITRINE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_DEEP_RUBY_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_DEEP_SAPPHIRE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_WORLD_CITRINE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_WORLD_RUBY_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_WORLD_SAPPHIRE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_NETHER_CITRINE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_NETHER_RUBY_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_NETHER_SAPPHIRE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_END_CITRINE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_END_RUBY_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_END_SAPPHIRE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_END_ENDERITE_PLACED_KEY);
    }
}