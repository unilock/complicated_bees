package com.accbdd.complicated_bees.datagen;

import com.accbdd.complicated_bees.registry.ItemsRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> SCOOP_TOOL = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "scoop_tool"));
    public static final TagKey<Item> BEE = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "bee"));
    public static final TagKey<Item> ROYAL = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "royal"));
    public static final TagKey<Item> FRAME = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "frame"));
    public static final TagKey<Item> ANALYZER_FUEL = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "analyzer_fuel"));

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        getOrCreateTagBuilder(SCOOP_TOOL).add(ItemsRegistration.SCOOP.get());
        getOrCreateTagBuilder(ROYAL).add(ItemsRegistration.PRINCESS.get(), ItemsRegistration.QUEEN.get());
        getOrCreateTagBuilder(BEE).add(ItemsRegistration.PRINCESS.get(), ItemsRegistration.QUEEN.get(), ItemsRegistration.DRONE.get());
        getOrCreateTagBuilder(ANALYZER_FUEL).add(ItemsRegistration.HONEY_DROPLET.get(), ItemsRegistration.ROYAL_JELLY.get());
        getOrCreateTagBuilder(FRAME).add(
                ItemsRegistration.FRAME.get(),
                ItemsRegistration.DEADLY_FRAME.get(),
                ItemsRegistration.DRY_FRAME.get(),
                ItemsRegistration.WET_FRAME.get(),
                ItemsRegistration.COLD_FRAME.get(),
                ItemsRegistration.HOT_FRAME.get(),
                ItemsRegistration.RESTRICTIVE_FRAME.get(),
                ItemsRegistration.WAXED_FRAME.get(),
                ItemsRegistration.HONEYED_FRAME.get(),
                ItemsRegistration.TWISTING_FRAME.get(),
                ItemsRegistration.SOOTHING_FRAME.get()
        );
        getOrCreateTagBuilder(ItemTags.PLANKS).add(ItemsRegistration.HONEYED_PLANKS.get());
    }
}
