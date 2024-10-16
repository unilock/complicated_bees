package com.accbdd.complicated_bees.datagen.condition;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class ItemEnabledCondition implements ConditionJsonProvider {
    public static final ResourceLocation ID = new ResourceLocation(MODID, "item_enabled");
    public static Codec<ItemEnabledCondition> CODEC = RecordCodecBuilder.create(
            builder -> builder
                    .group(
                            ResourceLocation.CODEC.fieldOf("item").forGetter(ItemEnabledCondition::getItem))
                    .apply(builder, ItemEnabledCondition::new));

    private final ResourceLocation item;

    public ItemEnabledCondition(String location) {
        this(new ResourceLocation(location));
    }

    public ItemEnabledCondition(String namespace, String path) {
        this(new ResourceLocation(namespace, path));
    }

    public ItemEnabledCondition(ResourceLocation item) {
        this.item = item;
    }

    @Override
    public ResourceLocation getConditionId() {
        return ID;
    }

    public ResourceLocation getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "item_enabled(\"" + item + "\")";
    }

    @Override
    public void writeParameters(JsonObject json) {
        json.add("type", new JsonPrimitive(getConditionId().toString()));
        json.add("item", new JsonPrimitive(getItem().toString()));
    }

    public static boolean test(JsonObject json) {
        var result = CODEC.decode(JsonOps.INSTANCE, json);
        var completedResult = result.getOrThrow(false, (string) -> {
            throw new RuntimeException("error reading ItemEnabledCondition: " + string);
        });
        return BuiltInRegistries.ITEM.get(completedResult.getFirst().getItem()).isEnabled(FeatureFlagSet.of());
    }
}
