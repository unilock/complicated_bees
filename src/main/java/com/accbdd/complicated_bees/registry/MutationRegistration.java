package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.genetics.gene.enums.EnumHumidity;
import com.accbdd.complicated_bees.genetics.gene.enums.EnumTemperature;
import com.accbdd.complicated_bees.genetics.mutation.Mutation;
import com.accbdd.complicated_bees.genetics.mutation.condition.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;
import static com.accbdd.complicated_bees.ComplicatedBees.MUTATION_CONDITION_REGISTRY;

public class MutationRegistration {
    public static final ResourceKey<Registry<Mutation>> MUTATION_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(MODID, "mutation"));

    public static final Supplier<IMutationCondition> BLOCK_UNDER = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, BlockUnderCondition.ID), new BlockUnderCondition(Blocks.AIR));
    public static final Supplier<IMutationCondition> ECSTATIC = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, EcstaticCondition.ID), new EcstaticCondition());
    public static final Supplier<IMutationCondition> DAYTIME = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, DaytimeCondition.ID), new DaytimeCondition());
    public static final Supplier<IMutationCondition> NIGHTTIME = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, NighttimeCondition.ID), new NighttimeCondition());
    public static final Supplier<IMutationCondition> DOWNFALL = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, DownfallCondition.ID), new DownfallCondition());
    public static final Supplier<IMutationCondition> HUMIDITY = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, HumidityCondition.ID), new HumidityCondition(EnumHumidity.NORMAL, EnumHumidity.NORMAL));
    public static final Supplier<IMutationCondition> TEMPERATURE = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, TemperatureCondition.ID), new TemperatureCondition(EnumTemperature.NORMAL, EnumTemperature.NORMAL));
    public static final Supplier<IMutationCondition> DIMENSION = () -> Registry.register(MUTATION_CONDITION_REGISTRY.get(), new ResourceLocation(MODID, DimensionCondition.ID), new DimensionCondition(new ResourceLocation("minecraft:overworld")));

    public static void register() {}
}
