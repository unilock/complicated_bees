package com.accbdd.complicated_bees.datagen;

import com.accbdd.complicated_bees.datagen.loot.BlockLootTables;
import com.accbdd.complicated_bees.registry.BlocksRegistration;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.BlockFamily;

public class DataGenerators implements DataGeneratorEntrypoint {
    public static final BlockFamily HONEYED_PLANK_FAMILY = new BlockFamily.Builder(BlocksRegistration.HONEYED_PLANKS)
            .stairs(BlocksRegistration.HONEYED_STAIRS)
            .slab(BlocksRegistration.HONEYED_SLAB)
            .fence(BlocksRegistration.HONEYED_FENCE)
            .fenceGate(BlocksRegistration.HONEYED_FENCE_GATE)
            .button(BlocksRegistration.HONEYED_BUTTON)
            .pressurePlate(BlocksRegistration.HONEYED_PRESSURE_PLATE)
            .door(BlocksRegistration.HONEYED_DOOR)
            .trapdoor(BlocksRegistration.HONEYED_TRAPDOOR)
            .getFamily();

    public static final BlockFamily WAX_BLOCK_FAMILY = new BlockFamily.Builder(BlocksRegistration.WAX_BLOCK)
            .stairs(BlocksRegistration.WAX_BLOCK_STAIRS)
            .slab(BlocksRegistration.WAX_BLOCK_SLAB)
            .wall(BlocksRegistration.WAX_BLOCK_WALL)
            .getFamily();

    public static final BlockFamily WAX_BRICK_FAMILY = new BlockFamily.Builder(BlocksRegistration.WAX_BRICKS)
            .stairs(BlocksRegistration.WAX_BRICK_STAIRS)
            .slab(BlocksRegistration.WAX_BRICK_SLAB)
            .wall(BlocksRegistration.WAX_BRICK_WALL)
            .getFamily();

    public static final BlockFamily SMOOTH_WAX_FAMILY = new BlockFamily.Builder(BlocksRegistration.SMOOTH_WAX)
            .stairs(BlocksRegistration.SMOOTH_WAX_STAIRS)
            .slab(BlocksRegistration.SMOOTH_WAX_SLAB)
            .wall(BlocksRegistration.SMOOTH_WAX_WALL)
            .chiseled(BlocksRegistration.CHISELED_WAX)
            .getFamily();

//    public static void generate(GatherDataEvent event) {
//        DataGenerator generator = event.getGenerator();
//        PackOutput packOutput = generator.getPackOutput();
//        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
//        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//
//        generator.addProvider(event.includeClient(), new BlockStateGenerator(packOutput, existingFileHelper));
//        generator.addProvider(event.includeClient(), new ItemModelGenerator(packOutput, existingFileHelper));
//        generator.addProvider(event.includeClient(), new BeeModelGenerator(packOutput, existingFileHelper));
//        generator.addProvider(event.includeClient(), new ParticleDescriptionGenerator(packOutput, existingFileHelper));
//
//        BlockTagGenerator blockTagGenerator = new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
//        generator.addProvider(event.includeServer(), blockTagGenerator);
//        generator.addProvider(event.includeServer(), new ItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
//        generator.addProvider(event.includeServer(), (DataProvider.Factory<LootTableGenerator>) pOutput -> new LootTableGenerator(packOutput));
//        generator.addProvider(event.includeServer(), new RecipeGenerator(packOutput));
//    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        BlockTagGenerator blockTagGenerator = pack.addProvider(BlockTagGenerator::new);
        pack.addProvider((fabricDataOutput, completableFuture) -> new ItemTagGenerator(fabricDataOutput, completableFuture, blockTagGenerator));
        pack.addProvider(BlockLootTables::new);
        pack.addProvider(RecipeGenerator::new);
    }
}
