package com.accbdd.complicated_bees.datagen;

import com.accbdd.complicated_bees.registry.BlocksRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public static final TagKey<Block> SCOOPABLE = TagKey.create(Registries.BLOCK, new ResourceLocation(MODID, "mineable/scoop_tool"));

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        getOrCreateTagBuilder(SCOOPABLE).add(BlocksRegistration.BEE_NEST);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                BlocksRegistration.APIARY,
                BlocksRegistration.HONEYED_PLANKS,
                BlocksRegistration.HONEYED_STAIRS,
                BlocksRegistration.HONEYED_SLAB,
                BlocksRegistration.HONEYED_FENCE,
                BlocksRegistration.HONEYED_FENCE_GATE,
                BlocksRegistration.HONEYED_BUTTON,
                BlocksRegistration.HONEYED_PRESSURE_PLATE,
                BlocksRegistration.HONEYED_DOOR,
                BlocksRegistration.HONEYED_TRAPDOOR
        );
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlocksRegistration.CENTRIFUGE,
                BlocksRegistration.CHISELED_WAX,
                BlocksRegistration.WAX_BLOCK,
                BlocksRegistration.WAX_BLOCK_STAIRS,
                BlocksRegistration.WAX_BLOCK_SLAB,
                BlocksRegistration.WAX_BLOCK_WALL,
                BlocksRegistration.SMOOTH_WAX,
                BlocksRegistration.SMOOTH_WAX_STAIRS,
                BlocksRegistration.SMOOTH_WAX_SLAB,
                BlocksRegistration.SMOOTH_WAX_WALL,
                BlocksRegistration.WAX_BRICKS,
                BlocksRegistration.WAX_BRICK_STAIRS,
                BlocksRegistration.WAX_BRICK_SLAB,
                BlocksRegistration.WAX_BRICK_WALL,
                BlocksRegistration.CHISELED_WAX
        );
        getOrCreateTagBuilder(BlockTags.WALLS).add(
                BlocksRegistration.WAX_BLOCK_WALL,
                BlocksRegistration.SMOOTH_WAX_WALL,
                BlocksRegistration.WAX_BRICK_WALL
        );
        getOrCreateTagBuilder(BlockTags.FENCES).add(
                BlocksRegistration.HONEYED_FENCE
        );
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(
                BlocksRegistration.HONEYED_FENCE_GATE
        );
    }
}
