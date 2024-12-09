package com.accbdd.complicated_bees.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Predicate;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BiomeModificationRegistration {
	private static final BiomeModification MODIFIER = BiomeModifications.create(new ResourceLocation(MODID, "modifier"));

	public static void register() {
		// Technically, these shouldn't be using tags, but...
		add(ctx -> selector(ctx, Biomes.CRIMSON_FOREST), "crimson_bee_nest");
		add(ctx -> selector(ctx, ConventionalBiomeTags.DESERT), "desert_bee_nest");
		add(ctx -> selector(ctx, ConventionalBiomeTags.END_ISLANDS), "ender_bee_nest");
		add(ctx -> selector(ctx, ConventionalBiomeTags.FOREST), "birch_forest_bees");
		add(ctx -> selector(ctx, ConventionalBiomeTags.JUNGLE), "jungle_bee_nest");
		add(ctx -> selector(ctx, ConventionalBiomeTags.PLAINS) || selector(ctx, Biomes.MEADOW), "plains_bee_nest");
		add(ctx -> selector(ctx, Biomes.LUSH_CAVES), "rocky_bee_nest");
		add(ctx -> selector(ctx, Biomes.WARPED_FOREST), "warped_bee_nest");

		if (FabricLoader.getInstance().isModLoaded("biomesoplenty")) {
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:aspen_glade")), "biomesoplenty/aspen_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:bayou")), "biomesoplenty/bayou_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:forested_field")), "biomesoplenty/tall_spruce_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:jacaranda_glade")), "biomesoplenty/big_jacaranda_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:maple_woods")), "biomesoplenty/tall_spruce_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:mystic_grove")), "biomesoplenty/magic_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:old_growth_woodland")), "biomesoplenty/big_oak_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:orchard")), "biomesoplenty/flowering_oak_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:field")) || selector(ctx, biomeKey("biomesoplenty:grassland")) || selector(ctx, biomeKey("biomesoplenty:highland")) || selector(ctx, biomeKey("biomesoplenty:lavender_field")) || selector(ctx, biomeKey("biomesoplenty:moor")), "biomesoplenty/plains_bee_nest");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:rainforest")) || selector(ctx, biomeKey("biomesoplenty:rocky_rainforest")), "biomesoplenty/mahogany_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:seasonal_forest")), "biomesoplenty/orange_maple_tree");
			add(ctx -> selector(ctx, biomeKey("biomesoplenty:tropics")), "biomesoplenty/palm_tree");
		}

		if (FabricLoader.getInstance().isModLoaded("terralith")) {
			add(ctx -> selector(ctx, biomeKey("terralith:amethyst_canyon")) || selector(ctx, biomeKey("terralith:amethyst_rainforest")), "terralith/amethyst_tree");
			add(ctx -> selector(ctx, biomeKey("terralith:mirage_isles")), "terralith/mirage_tree");
			add(ctx -> selector(ctx, biomeKey("terralith:moonlight_valley")), "terralith/moonlight_tree");
			add(ctx -> selector(ctx, biomeKey("terralith:brushland")) || selector(ctx, biomeKey("terralith:bryce_canyon")) || selector(ctx, biomeKey("terralith:desert_canyon")) || selector(ctx, biomeKey("terralith:desert_oasis")) || selector(ctx, biomeKey("terralith:desert_spires")) || selector(ctx, biomeKey("terralith:lush_desert")) || selector(ctx, biomeKey("terralith:warped_mesa")) || selector(ctx, biomeKey("terralith:red_oasis")) || selector(ctx, biomeKey("terralith:sandstone_valley")), "terralith/desert_bee_nest");
			add(ctx -> selector(ctx, biomeKey("terralith:blooming_valley")) || selector(ctx, biomeKey("terralith:lavender_forest")) || selector(ctx, biomeKey("terralith:lavender_valley")) || selector(ctx, biomeKey("terralith:cloud_forest")) || selector(ctx, biomeKey("terralith:moonlight_grove")) || selector(ctx, biomeKey("terralith:skylands_spring")), "terralith/birch_forest_bees");
			add(ctx -> selector(ctx, biomeKey("terralith:jungle_mountains")) || selector(ctx, biomeKey("terralith:rocky_jungle")) || selector(ctx, biomeKey("terralith:tropical_jungle")), "terralith/jungle_bee_nest");
			add(ctx -> selector(ctx, biomeKey("terralith:blooming_plateau")), "terralith/plains_bee_nest");
			add(ctx -> selector(ctx, biomeKey("terralith:skylands_autumn")), "terralith/big_birch");
			add(ctx -> selector(ctx, biomeKey("terralith:yosemite_lowlands")), "terralith/birch");
		}
	}

	private static void add(Predicate<BiomeSelectionContext> predicate, String path) {
		MODIFIER.add(ModificationPhase.ADDITIONS, predicate, (ignored, ctx) -> modifier(ctx, path));
	}

	private static boolean selector(BiomeSelectionContext ctx, ResourceKey<Biome> biome) {
		return ctx.getBiomeRegistryEntry().is(biome);
	}

	private static boolean selector(BiomeSelectionContext ctx, TagKey<Biome> tag) {
		return ctx.getBiomeRegistryEntry().is(tag);
	}

	private static void modifier(BiomeModificationContext ctx, String path) {
		ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatureKey(path));
	}

	private static ResourceKey<Biome> biomeKey(String id) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(id));
	}

	private static ResourceKey<PlacedFeature> placedFeatureKey(String path) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MODID, path));
	}
}
