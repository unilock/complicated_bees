package com.accbdd.complicated_bees;

import com.accbdd.complicated_bees.block.BeeNestBlock;
import com.accbdd.complicated_bees.config.Config;
import com.accbdd.complicated_bees.datagen.condition.ItemEnabledCondition;
import com.accbdd.complicated_bees.genetics.Comb;
import com.accbdd.complicated_bees.genetics.GeneticHelper;
import com.accbdd.complicated_bees.genetics.Species;
import com.accbdd.complicated_bees.genetics.effect.IBeeEffect;
import com.accbdd.complicated_bees.genetics.gene.IGene;
import com.accbdd.complicated_bees.genetics.mutation.Mutation;
import com.accbdd.complicated_bees.genetics.mutation.condition.IMutationCondition;
import com.accbdd.complicated_bees.item.CombItem;
import com.accbdd.complicated_bees.registry.BeeEffectRegistration;
import com.accbdd.complicated_bees.registry.BlockEntitiesRegistration;
import com.accbdd.complicated_bees.registry.BlocksRegistration;
import com.accbdd.complicated_bees.registry.CombRegistration;
import com.accbdd.complicated_bees.registry.EntitiesRegistration;
import com.accbdd.complicated_bees.registry.EsotericRegistration;
import com.accbdd.complicated_bees.registry.FlowerRegistration;
import com.accbdd.complicated_bees.registry.GeneRegistration;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import com.accbdd.complicated_bees.registry.MenuRegistration;
import com.accbdd.complicated_bees.registry.MutationRegistration;
import com.accbdd.complicated_bees.registry.SpeciesRegistration;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.reborn.energy.api.EnergyStorage;

import java.util.Map;
import java.util.Set;

public class ComplicatedBees implements ModInitializer {
    public static final String MODID = "complicated_bees";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static final Registry<IGene<?>> GENE_REGISTRY = FabricRegistryBuilder.<IGene<?>>createSimple(ResourceKey.createRegistryKey(new ResourceLocation(MODID, "gene"))).attribute(RegistryAttribute.SYNCED).buildAndRegister();
    public static final Registry<IBeeEffect> BEE_EFFECT_REGISTRY = FabricRegistryBuilder.<IBeeEffect>createSimple(ResourceKey.createRegistryKey(new ResourceLocation(MODID, "bee_effect"))).attribute(RegistryAttribute.SYNCED).buildAndRegister();
    public static final Registry<IMutationCondition> MUTATION_CONDITION_REGISTRY = FabricRegistryBuilder.<IMutationCondition>createSimple(ResourceKey.createRegistryKey(new ResourceLocation(MODID, "mutation"))).attribute(RegistryAttribute.SYNCED).buildAndRegister();

    public static final CreativeModeTab BEES_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(MODID, "complicated_bees"), FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.complicated_bees"))
            .icon(() -> ItemsRegistration.DRONE.getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemsRegistration.WAX_BLOCK);
                output.accept(ItemsRegistration.WAX_BLOCK_STAIRS);
                output.accept(ItemsRegistration.WAX_BLOCK_SLAB);
                output.accept(ItemsRegistration.WAX_BLOCK_WALL);
                output.accept(ItemsRegistration.SMOOTH_WAX);
                output.accept(ItemsRegistration.SMOOTH_WAX_STAIRS);
                output.accept(ItemsRegistration.SMOOTH_WAX_SLAB);
                output.accept(ItemsRegistration.SMOOTH_WAX_WALL);
                output.accept(ItemsRegistration.WAX_BRICKS);
                output.accept(ItemsRegistration.WAX_BRICK_STAIRS);
                output.accept(ItemsRegistration.WAX_BRICK_SLAB);
                output.accept(ItemsRegistration.WAX_BRICK_WALL);
                output.accept(ItemsRegistration.CHISELED_WAX);
                output.accept(ItemsRegistration.HONEYED_PLANKS);
                output.accept(ItemsRegistration.HONEYED_STAIRS);
                output.accept(ItemsRegistration.HONEYED_SLAB);
                output.accept(ItemsRegistration.HONEYED_FENCE);
                output.accept(ItemsRegistration.HONEYED_FENCE_GATE);
                output.accept(ItemsRegistration.HONEYED_BUTTON);
                output.accept(ItemsRegistration.HONEYED_PRESSURE_PLATE);
                output.accept(ItemsRegistration.HONEYED_DOOR);
                output.accept(ItemsRegistration.HONEYED_TRAPDOOR);
                output.accept(ItemsRegistration.APIARY);
                output.accept(ItemsRegistration.CENTRIFUGE);
                output.accept(ItemsRegistration.HONEY_DROPLET);
                output.accept(ItemsRegistration.BEESWAX);
                output.accept(ItemsRegistration.PROPOLIS);
                output.accept(ItemsRegistration.ROYAL_JELLY);
                output.accept(ItemsRegistration.POLLEN);
                output.accept(ItemsRegistration.SCOOP);
                output.accept(ItemsRegistration.METER);
                output.accept(ItemsRegistration.ANALYZER);
                output.accept(ItemsRegistration.GENERATOR);
                output.accept(ItemsRegistration.FRAME);
                output.accept(ItemsRegistration.WAXED_FRAME);
                output.accept(ItemsRegistration.HONEYED_FRAME);
                output.accept(ItemsRegistration.TWISTING_FRAME);
                output.accept(ItemsRegistration.SOOTHING_FRAME);
                output.accept(ItemsRegistration.HOT_FRAME);
                output.accept(ItemsRegistration.COLD_FRAME);
                output.accept(ItemsRegistration.DRY_FRAME);
                output.accept(ItemsRegistration.WET_FRAME);
                output.accept(ItemsRegistration.DEADLY_FRAME);
                output.accept(ItemsRegistration.RESTRICTIVE_FRAME);
                output.accept(ItemsRegistration.PEARL_SHARD);
                output.accept(ItemsRegistration.WAXED_STICK);
                output.accept(ItemsRegistration.HONEYED_STICK);
                output.accept(ItemsRegistration.EXP_DROP);
                output.accept(ItemsRegistration.SILK_WISP);
                output.accept(ItemsRegistration.WOVEN_MESH);
                output.accept(ItemsRegistration.APIARIST_HELMET);
                output.accept(ItemsRegistration.APIARIST_CHESTPLATE);
                output.accept(ItemsRegistration.APIARIST_LEGGINGS);
                output.accept(ItemsRegistration.APIARIST_BOOTS);
                output.accept(ItemsRegistration.BEE_STAFF);
                output.accept(ItemsRegistration.HONEY_BREAD);
                output.accept(ItemsRegistration.HONEY_PORKCHOP);
                output.accept(ItemsRegistration.AMBROSIA);
                RegistryAccess access = GeneticHelper.getRegistryAccess();
                if (access != null) {
                    Set<Map.Entry<ResourceKey<Species>, Species>> speciesSet = access.registry(SpeciesRegistration.SPECIES_REGISTRY_KEY).get().entrySet();
                    for (Map.Entry<ResourceKey<Species>, Species> entry : speciesSet) {
                        output.accept(GeneticHelper.setBothGenome(ItemsRegistration.DRONE.getDefaultInstance(), entry.getValue().getDefaultChromosome()));
                        output.accept(GeneticHelper.setBothGenome(ItemsRegistration.PRINCESS.getDefaultInstance(), entry.getValue().getDefaultChromosome()));
                        output.accept(GeneticHelper.setBothGenome(ItemsRegistration.QUEEN.getDefaultInstance(), entry.getValue().getDefaultChromosome()));
                    }
                    for (ResourceLocation id : access.registry(CombRegistration.COMB_REGISTRY_KEY).get().keySet()) {
                        output.accept(CombItem.setComb(ItemsRegistration.COMB.getDefaultInstance(), id));
                    }
                    for (Map.Entry<ResourceKey<Species>, Species> entry : speciesSet) {
                        output.accept(BeeNestBlock.stackNest(ItemsRegistration.BEE_NEST.getDefaultInstance(), entry.getValue()));
                    }
                }
            }).build());

    public static MinecraftServer currentServer;

    @Override
    public void onInitialize() {
        this.registerSerializers();
        this.registerDatapackRegistries();
//        modEventBus.addListener(DataGenerators::generate);

        BlocksRegistration.register();
        ItemsRegistration.register();
        BlockEntitiesRegistration.register();
        MenuRegistration.register();
        GeneRegistration.register();
        BeeEffectRegistration.register();
        MutationRegistration.register();
        EntitiesRegistration.register();
        EsotericRegistration.register();

        ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.COMMON, Config.CONFIG_SPEC);

        ServerLifecycleEvents.SERVER_STARTED.register(this::serverStarted);
        ServerLifecycleEvents.SERVER_STOPPING.register(this::serverStopping);

        EnergyStorage.SIDED.registerForBlockEntity((centrifugeBlockEntity, direction) -> {
            return centrifugeBlockEntity.getEnergyHandler();
        }, BlockEntitiesRegistration.CENTRIFUGE_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((generatorBlockEntity, direction) -> {
            return generatorBlockEntity.getEnergyHandler();
        }, BlockEntitiesRegistration.GENERATOR_BLOCK_ENTITY);

		//noinspection UnstableApiUsage
		ItemStorage.SIDED.registerForBlockEntity((apiaryBlockEntity, direction) -> {
            if (direction == null) {
                return apiaryBlockEntity.getItemHandler();
            }
            if (direction == Direction.DOWN) {
                return apiaryBlockEntity.getOutputItemHandler();
            }
            return apiaryBlockEntity.getBeeItemHandler();
        }, BlockEntitiesRegistration.APIARY_ENTITY);
        //noinspection UnstableApiUsage
        ItemStorage.SIDED.registerForBlockEntity((centrifugeBlockEntity, direction) -> {
            if (direction == null) {
                return centrifugeBlockEntity.getItemHandler();
            }
            if (direction == Direction.DOWN) {
                return centrifugeBlockEntity.getOutputItemHandler();
            }
            return centrifugeBlockEntity.getInputItemHandler();
        }, BlockEntitiesRegistration.CENTRIFUGE_ENTITY);
        //noinspection UnstableApiUsage
        ItemStorage.SIDED.registerForBlockEntity((generatorBlockEntity, direction) -> {
            return generatorBlockEntity.getItemHandler();
        }, BlockEntitiesRegistration.GENERATOR_BLOCK_ENTITY);
    }

    public void registerDatapackRegistries() {
        DynamicRegistries.registerSynced(
                SpeciesRegistration.SPECIES_REGISTRY_KEY,
                Species.SPECIES_CODEC,
                Species.SPECIES_CODEC
        );

        DynamicRegistries.registerSynced(
                CombRegistration.COMB_REGISTRY_KEY,
                Comb.CODEC,
                Comb.CODEC
        );

        DynamicRegistries.registerSynced(
                MutationRegistration.MUTATION_REGISTRY_KEY,
                Mutation.MUTATION_CODEC,
                Mutation.MUTATION_CODEC
        );

        DynamicRegistries.registerSynced(
                FlowerRegistration.FLOWER_REGISTRY_KEY,
                FlowerRegistration.CODEC,
                FlowerRegistration.CODEC
        );
    }

    public void registerSerializers() {
        ResourceConditions.register(ItemEnabledCondition.ID, ItemEnabledCondition::test);
    }

    public void serverStarted(MinecraftServer server) {
        LOGGER.info("Registered {} species", server.registryAccess().registry(SpeciesRegistration.SPECIES_REGISTRY_KEY).get().size());
        LOGGER.info("Registered {} combs", server.registryAccess().registry(CombRegistration.COMB_REGISTRY_KEY).get().size());
        LOGGER.info("Registered {} mutations", server.registryAccess().registry(MutationRegistration.MUTATION_REGISTRY_KEY).get().size());
        LOGGER.info("Registered {} flowers", server.registryAccess().registry(FlowerRegistration.FLOWER_REGISTRY_KEY).get().size());
        currentServer = server;
    }

    public void serverStopping(MinecraftServer server) {
        currentServer = null;
    }
}
