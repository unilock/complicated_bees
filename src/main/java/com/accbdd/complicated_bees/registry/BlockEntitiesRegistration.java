package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.block.entity.ApiaryBlockEntity;
import com.accbdd.complicated_bees.block.entity.BeeNestBlockEntity;
import com.accbdd.complicated_bees.block.entity.CentrifugeBlockEntity;
import com.accbdd.complicated_bees.block.entity.GeneratorBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BlockEntitiesRegistration {
    public static final Supplier<BlockEntityType<ApiaryBlockEntity>> APIARY_ENTITY = () -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MODID, "apiary"),
            BlockEntityType.Builder.of(ApiaryBlockEntity::new, BlocksRegistration.APIARY.get()).build(null));
    public static final Supplier<BlockEntityType<CentrifugeBlockEntity>> CENTRIFUGE_ENTITY = () -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MODID, "centrifuge"),
            BlockEntityType.Builder.of(CentrifugeBlockEntity::new, BlocksRegistration.CENTRIFUGE.get()).build(null));
    public static final Supplier<BlockEntityType<BeeNestBlockEntity>> BEE_NEST_ENTITY = () -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MODID, "bee_nest"),
            BlockEntityType.Builder.of(BeeNestBlockEntity::new, BlocksRegistration.BEE_NEST.get()).build(null));
    public static final Supplier<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY = () -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MODID, "generator"),
            BlockEntityType.Builder.of(GeneratorBlockEntity::new, BlocksRegistration.GENERATOR.get()).build(null));

    public static void register() {}

}
