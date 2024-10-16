package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.loot.InheritHiveCombFunction;
import com.accbdd.complicated_bees.loot.InheritHiveSpeciesFunction;
import com.accbdd.complicated_bees.recipe.CentrifugeRecipe;
import com.accbdd.complicated_bees.worldgen.ComplicatedBeenestDecorator;
import com.accbdd.complicated_bees.worldgen.ComplicatedHiveFeature;
import com.accbdd.complicated_bees.worldgen.ComplicatedHiveFeatureConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class EsotericRegistration {
    public static final Supplier<SimpleParticleType> BEE_PARTICLE = () -> Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MODID, "bee"),
            new SimpleParticleType(true));

    public static final Supplier<LootItemFunctionType> INHERIT_HIVE = () -> Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, new ResourceLocation(MODID, "inherit_hive_species"),
            new LootItemFunctionType(InheritHiveSpeciesFunction.Serializer.INSTANCE));
    public static final Supplier<LootItemFunctionType> INHERIT_COMB = () -> Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, new ResourceLocation(MODID, "inherit_hive_comb"),
            new LootItemFunctionType(InheritHiveCombFunction.Serializer.INSTANCE));

    public static final Supplier<TreeDecoratorType<ComplicatedBeenestDecorator>> COMPLICATED_BEENEST_DECORATOR = () -> Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, new ResourceLocation(MODID, "bee_nest_decorator"),
            new TreeDecoratorType<>(ComplicatedBeenestDecorator.CODEC));

    public static final Supplier<ComplicatedHiveFeature> COMPLICATED_HIVE_FEATURE = () -> Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(MODID, "complicated_bee_nest"),
            new ComplicatedHiveFeature(ComplicatedHiveFeatureConfiguration.CODEC));

    public static final Supplier<RecipeType<CentrifugeRecipe>> CENTRIFUGE_RECIPE = () -> RecipeType.register(new ResourceLocation(MODID, "centrifuge").toString());

    public static final Supplier<RecipeSerializer<CentrifugeRecipe>> CENTRIFUGE_RECIPE_SERIALIZER = () -> Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(MODID, "centrifuge"),
            CentrifugeRecipe.SERIALIZER);

    public static void register() {}
}
