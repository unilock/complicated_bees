package com.accbdd.complicated_bees;

import com.accbdd.complicated_bees.block.BeeNestBlock;
import com.accbdd.complicated_bees.client.ColorHandlers;
import com.accbdd.complicated_bees.config.Config;
import com.accbdd.complicated_bees.datagen.DataGenerators;
import com.accbdd.complicated_bees.datagen.condition.ItemEnabledCondition;
import com.accbdd.complicated_bees.genetics.Comb;
import com.accbdd.complicated_bees.genetics.GeneticHelper;
import com.accbdd.complicated_bees.genetics.Species;
import com.accbdd.complicated_bees.genetics.effect.IBeeEffect;
import com.accbdd.complicated_bees.genetics.gene.IGene;
import com.accbdd.complicated_bees.genetics.mutation.Mutation;
import com.accbdd.complicated_bees.genetics.mutation.condition.IMutationCondition;
import com.accbdd.complicated_bees.item.CombItem;
import com.accbdd.complicated_bees.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class ComplicatedBees implements ModInitializer {
    public static final String MODID = "complicated_bees";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static final Supplier<Registry<IGene<?>>> GENE_REGISTRY = () -> FabricRegistryBuilder.<IGene<?>>createSimple(ResourceKey.createRegistryKey(new ResourceLocation(MODID, "gene"))).attribute(RegistryAttribute.SYNCED).buildAndRegister();
    public static final Supplier<Registry<IBeeEffect>> BEE_EFFECT_REGISTRY = () -> FabricRegistryBuilder.<IBeeEffect>createSimple(ResourceKey.createRegistryKey(new ResourceLocation(MODID, "bee_effect"))).attribute(RegistryAttribute.SYNCED).buildAndRegister();
    public static final Supplier<Registry<IMutationCondition>> MUTATION_CONDITION_REGISTRY = () -> FabricRegistryBuilder.<IMutationCondition>createSimple(ResourceKey.createRegistryKey(new ResourceLocation(MODID, "mutation"))).attribute(RegistryAttribute.SYNCED).buildAndRegister();

    public static final CreativeModeTab BEES_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(MODID, "complicated_bees"), FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.complicated_bees"))
            .icon(() -> ItemsRegistration.DRONE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemsRegistration.WAX_BLOCK.get());
                output.accept(ItemsRegistration.WAX_BLOCK_STAIRS.get());
                output.accept(ItemsRegistration.WAX_BLOCK_SLAB.get());
                output.accept(ItemsRegistration.WAX_BLOCK_WALL.get());
                output.accept(ItemsRegistration.SMOOTH_WAX.get());
                output.accept(ItemsRegistration.SMOOTH_WAX_STAIRS.get());
                output.accept(ItemsRegistration.SMOOTH_WAX_SLAB.get());
                output.accept(ItemsRegistration.SMOOTH_WAX_WALL.get());
                output.accept(ItemsRegistration.WAX_BRICKS.get());
                output.accept(ItemsRegistration.WAX_BRICK_STAIRS.get());
                output.accept(ItemsRegistration.WAX_BRICK_SLAB.get());
                output.accept(ItemsRegistration.WAX_BRICK_WALL.get());
                output.accept(ItemsRegistration.CHISELED_WAX.get());
                output.accept(ItemsRegistration.HONEYED_PLANKS.get());
                output.accept(ItemsRegistration.HONEYED_STAIRS.get());
                output.accept(ItemsRegistration.HONEYED_SLAB.get());
                output.accept(ItemsRegistration.HONEYED_FENCE.get());
                output.accept(ItemsRegistration.HONEYED_FENCE_GATE.get());
                output.accept(ItemsRegistration.HONEYED_BUTTON.get());
                output.accept(ItemsRegistration.HONEYED_PRESSURE_PLATE.get());
                output.accept(ItemsRegistration.HONEYED_DOOR.get());
                output.accept(ItemsRegistration.HONEYED_TRAPDOOR.get());
                output.accept(ItemsRegistration.APIARY.get());
                output.accept(ItemsRegistration.CENTRIFUGE.get());
                output.accept(ItemsRegistration.HONEY_DROPLET.get());
                output.accept(ItemsRegistration.BEESWAX.get());
                output.accept(ItemsRegistration.PROPOLIS.get());
                output.accept(ItemsRegistration.ROYAL_JELLY.get());
                output.accept(ItemsRegistration.POLLEN.get());
                output.accept(ItemsRegistration.SCOOP.get());
                output.accept(ItemsRegistration.METER.get());
                output.accept(ItemsRegistration.ANALYZER.get());
                output.accept(ItemsRegistration.GENERATOR.get());
                output.accept(ItemsRegistration.FRAME.get());
                output.accept(ItemsRegistration.WAXED_FRAME.get());
                output.accept(ItemsRegistration.HONEYED_FRAME.get());
                output.accept(ItemsRegistration.TWISTING_FRAME.get());
                output.accept(ItemsRegistration.SOOTHING_FRAME.get());
                output.accept(ItemsRegistration.HOT_FRAME.get());
                output.accept(ItemsRegistration.COLD_FRAME.get());
                output.accept(ItemsRegistration.DRY_FRAME.get());
                output.accept(ItemsRegistration.WET_FRAME.get());
                output.accept(ItemsRegistration.DEADLY_FRAME.get());
                output.accept(ItemsRegistration.RESTRICTIVE_FRAME.get());
                output.accept(ItemsRegistration.PEARL_SHARD.get());
                output.accept(ItemsRegistration.WAXED_STICK.get());
                output.accept(ItemsRegistration.HONEYED_STICK.get());
                output.accept(ItemsRegistration.EXP_DROP.get());
                output.accept(ItemsRegistration.SILK_WISP.get());
                output.accept(ItemsRegistration.WOVEN_MESH.get());
                output.accept(ItemsRegistration.APIARIST_HELMET.get());
                output.accept(ItemsRegistration.APIARIST_CHESTPLATE.get());
                output.accept(ItemsRegistration.APIARIST_LEGGINGS.get());
                output.accept(ItemsRegistration.APIARIST_BOOTS.get());
                output.accept(ItemsRegistration.BEE_STAFF.get());
                output.accept(ItemsRegistration.HONEY_BREAD.get());
                output.accept(ItemsRegistration.HONEY_PORKCHOP.get());
                output.accept(ItemsRegistration.AMBROSIA.get());
                Set<Map.Entry<ResourceKey<Species>, Species>> speciesSet = Objects.requireNonNull(Minecraft.getInstance().getConnection()).registryAccess().registry(SpeciesRegistration.SPECIES_REGISTRY_KEY).get().entrySet();
                for (Map.Entry<ResourceKey<Species>, Species> entry : speciesSet) {
                    output.accept(GeneticHelper.setBothGenome(ItemsRegistration.DRONE.get().getDefaultInstance(), entry.getValue().getDefaultChromosome()));
                    output.accept(GeneticHelper.setBothGenome(ItemsRegistration.PRINCESS.get().getDefaultInstance(), entry.getValue().getDefaultChromosome()));
                    output.accept(GeneticHelper.setBothGenome(ItemsRegistration.QUEEN.get().getDefaultInstance(), entry.getValue().getDefaultChromosome()));
                }
                for (ResourceLocation id : Minecraft.getInstance().getConnection().registryAccess().registry(CombRegistration.COMB_REGISTRY_KEY).get().keySet()) {
                    output.accept(CombItem.setComb(ItemsRegistration.COMB.get().getDefaultInstance(), id));
                }
                for (Map.Entry<ResourceKey<Species>, Species> entry : speciesSet) {
                    output.accept(BeeNestBlock.stackNest(ItemsRegistration.BEE_NEST.get().getDefaultInstance(), entry.getValue()));
                }
            }).build());

    @Override
    public void onInitialize() {
        this.registerSerializers();
        modEventBus.addListener(ColorHandlers::registerItemColorHandlers);
        modEventBus.addListener(ColorHandlers::registerBlockColorHandlers);
        this.registerDatapackRegistries();
//        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(DataGenerators::generate);

        BlocksRegistration.BLOCKS.register(modEventBus);
        ItemsRegistration.ITEMS.register(modEventBus);
        BlockEntitiesRegistration.BLOCK_ENTITIES.register(modEventBus);
        MenuRegistration.MENU_TYPES.register(modEventBus);
        GeneRegistration.register();
        BeeEffectRegistration.EFFECTS.register(modEventBus);
        MutationRegistration.MUTATION_CONDITIONS.register(modEventBus);
        EntitiesRegistration.ENTITY_TYPE.register(modEventBus);
        EsotericRegistration.LOOT_ITEM_FUNCTION_REGISTER.register(modEventBus);
        EsotericRegistration.TREE_DECORATOR_REGISTER.register(modEventBus);
        EsotericRegistration.FEATURE_REGISTER.register(modEventBus);
        EsotericRegistration.RECIPE_TYPE_REGISTER.register(modEventBus);
        EsotericRegistration.RECIPE_SERIALIZER_REGISTER.register(modEventBus);
        EsotericRegistration.PARTICLE_TYPE.register(modEventBus);

        context.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);

        CREATIVE_MODE_TABS.register(modEventBus);

        ServerLifecycleEvents.SERVER_STARTED.register(this::serverStarted);
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
    }
}
