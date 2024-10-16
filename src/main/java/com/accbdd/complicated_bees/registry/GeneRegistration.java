package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.genetics.gene.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.GENE_REGISTRY;
import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class GeneRegistration {
    //every registered gene should be registered as a 'default' value
    public static final Supplier<GeneSpecies> SPECIES = () -> Registry.register(GENE_REGISTRY.get(), GeneSpecies.ID, new GeneSpecies());
    public static final Supplier<GeneLifespan> LIFESPAN = () -> Registry.register(GENE_REGISTRY.get(), GeneLifespan.ID, new GeneLifespan());
    public static final Supplier<GeneTemperature> TEMPERATURE = () -> Registry.register(GENE_REGISTRY.get(), GeneTemperature.ID, new GeneTemperature());
    public static final Supplier<GeneHumidity> HUMIDITY = () -> Registry.register(GENE_REGISTRY.get(), GeneHumidity.ID, new GeneHumidity());
    public static final Supplier<GeneFlower> FLOWER = () -> Registry.register(GENE_REGISTRY.get(), GeneFlower.ID, new GeneFlower());
    public static final Supplier<GeneFertility> FERTILITY = () -> Registry.register(GENE_REGISTRY.get(), GeneFertility.ID, new GeneFertility());
    public static final Supplier<GeneProductivity> PRODUCTIVITY = () -> Registry.register(GENE_REGISTRY.get(), GeneProductivity.ID, new GeneProductivity());
    public static final Supplier<GeneTerritory> TERRITORY = () -> Registry.register(GENE_REGISTRY.get(), GeneTerritory.ID, new GeneTerritory());
    public static final Supplier<GeneEffect> EFFECT = () -> Registry.register(GENE_REGISTRY.get(), GeneEffect.ID, new GeneEffect());
    public static final Supplier<GeneActiveTime> ACTIVE_TIME = () -> Registry.register(GENE_REGISTRY.get(), GeneActiveTime.ID, new GeneActiveTime());

    public static final Supplier<GeneBoolean> CAVE_DWELLING = () -> Registry.register(GENE_REGISTRY.get(), new ResourceLocation(MODID, "cave_dwelling"), new GeneBoolean(false, true));
    public static final Supplier<GeneBoolean> WEATHERPROOF = () -> Registry.register(GENE_REGISTRY.get(), new ResourceLocation(MODID, "weatherproof"), new GeneBoolean(false, true));

    public static void register() {}
}
