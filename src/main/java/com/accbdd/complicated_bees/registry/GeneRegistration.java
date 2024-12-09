package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.genetics.gene.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import static com.accbdd.complicated_bees.ComplicatedBees.GENE_REGISTRY;
import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class GeneRegistration {
    //every registered gene should be registered as a 'default' value
    public static final GeneSpecies SPECIES = Registry.register(GENE_REGISTRY, GeneSpecies.ID, new GeneSpecies());
    public static final GeneLifespan LIFESPAN = Registry.register(GENE_REGISTRY, GeneLifespan.ID, new GeneLifespan());
    public static final GeneTemperature TEMPERATURE = Registry.register(GENE_REGISTRY, GeneTemperature.ID, new GeneTemperature());
    public static final GeneHumidity HUMIDITY = Registry.register(GENE_REGISTRY, GeneHumidity.ID, new GeneHumidity());
    public static final GeneFlower FLOWER = Registry.register(GENE_REGISTRY, GeneFlower.ID, new GeneFlower());
    public static final GeneFertility FERTILITY = Registry.register(GENE_REGISTRY, GeneFertility.ID, new GeneFertility());
    public static final GeneProductivity PRODUCTIVITY = Registry.register(GENE_REGISTRY, GeneProductivity.ID, new GeneProductivity());
    public static final GeneTerritory TERRITORY = Registry.register(GENE_REGISTRY, GeneTerritory.ID, new GeneTerritory());
    public static final GeneEffect EFFECT = Registry.register(GENE_REGISTRY, GeneEffect.ID, new GeneEffect());
    public static final GeneActiveTime ACTIVE_TIME = Registry.register(GENE_REGISTRY, GeneActiveTime.ID, new GeneActiveTime());

    public static final GeneBoolean CAVE_DWELLING = Registry.register(GENE_REGISTRY, new ResourceLocation(MODID, "cave_dwelling"), new GeneBoolean(false, true));
    public static final GeneBoolean WEATHERPROOF = Registry.register(GENE_REGISTRY, new ResourceLocation(MODID, "weatherproof"), new GeneBoolean(false, true));

    public static void register() {}
}
