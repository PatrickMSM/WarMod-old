package tk.patsite.warmod.common.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceThresholdFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import tk.patsite.warmod.common.Warmod;

import java.util.Arrays;

public class WarmodWorldgen {
    private static final ConfiguredFeature<?, ?> URANIUM_ORE_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(
            OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
            WarmodBlocks.URANIUM_ORE_BLOCK.getDefaultState(),
            8 // vein size
    ));
    public static final PlacedFeature URANIUM_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(URANIUM_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(1), // veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-32)) // height
            )
    );

    private static final ConfiguredFeature<?, ?> SILICON_ORE_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(
            OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
            WarmodBlocks.SILICON_ORE_BLOCK.getDefaultState(),
            8 // vein size
    ));
    public static final PlacedFeature SILICON_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(SILICON_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(6), // veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0)) // height
            )
    );

    private static final ConfiguredFeature<?, ?> CRUDE_OIL_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.LAKE, new LakeFeature.Config(
            new SimpleBlockStateProvider(WarmodBlocks.CRUDE_OIL.getDefaultState()){},
            new SimpleBlockStateProvider(Blocks.STONE.getDefaultState()){}
    ));
    public static final PlacedFeature CRUDE_OIL_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(CRUDE_OIL_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(2), // veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, -1, 2) // heightmap + min = min height, heightmap + max = max height
            )
    );
    
    public static void register() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Warmod.id("uranium_ore"), URANIUM_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, Warmod.id("uranium_ore"), URANIUM_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, Warmod.id("uranium_ore")));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Warmod.id("silicon_ore"), SILICON_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, Warmod.id("silicon_ore"), SILICON_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, Warmod.id("silicon_ore")));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Warmod.id("crude_oil_lake"), CRUDE_OIL_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, Warmod.id("crude_oil_lake"), CRUDE_OIL_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.LAKES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, Warmod.id("crude_oil_lake")));
    }
}
