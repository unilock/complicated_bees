package com.accbdd.complicated_bees.datagen.loot;

import com.accbdd.complicated_bees.loot.InheritHiveCombFunction;
import com.accbdd.complicated_bees.loot.InheritHiveSpeciesFunction;
import com.accbdd.complicated_bees.registry.BlocksRegistration;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class BlockLootTables extends FabricBlockLootTableProvider {
    public BlockLootTables(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        dropSelf(BlocksRegistration.APIARY);
        dropSelf(BlocksRegistration.CENTRIFUGE);
        dropSelf(BlocksRegistration.GENERATOR);
        this.add(BlocksRegistration.BEE_NEST, nestLootTable(BlocksRegistration.BEE_NEST));
        dropSelf(BlocksRegistration.WAX_BLOCK);
        dropSelf(BlocksRegistration.WAX_BLOCK_STAIRS);
        this.add(BlocksRegistration.WAX_BLOCK_SLAB, createSlabItemTable(BlocksRegistration.WAX_BLOCK_SLAB));
        dropSelf(BlocksRegistration.WAX_BLOCK_WALL);
        dropSelf(BlocksRegistration.SMOOTH_WAX);
        dropSelf(BlocksRegistration.SMOOTH_WAX_STAIRS);
        this.add(BlocksRegistration.SMOOTH_WAX_SLAB, createSlabItemTable(BlocksRegistration.SMOOTH_WAX_SLAB));
        dropSelf(BlocksRegistration.SMOOTH_WAX_WALL);
        dropSelf(BlocksRegistration.WAX_BRICKS);
        dropSelf(BlocksRegistration.WAX_BRICK_STAIRS);
        this.add(BlocksRegistration.WAX_BRICK_SLAB, createSlabItemTable(BlocksRegistration.WAX_BRICK_SLAB));
        dropSelf(BlocksRegistration.WAX_BRICK_WALL);
        dropSelf(BlocksRegistration.CHISELED_WAX);
        dropSelf(BlocksRegistration.HONEYED_PLANKS);
        dropSelf(BlocksRegistration.HONEYED_STAIRS);
        this.add(BlocksRegistration.HONEYED_SLAB, createSlabItemTable(BlocksRegistration.HONEYED_SLAB));
        dropSelf(BlocksRegistration.HONEYED_FENCE);
        dropSelf(BlocksRegistration.HONEYED_FENCE_GATE);
        dropSelf(BlocksRegistration.HONEYED_BUTTON);
        dropSelf(BlocksRegistration.HONEYED_PRESSURE_PLATE);
        this.add(BlocksRegistration.HONEYED_DOOR, createDoorTable(BlocksRegistration.HONEYED_DOOR));
        dropSelf(BlocksRegistration.HONEYED_TRAPDOOR);
    }

//    @Override
//    protected Iterable<Block> getKnownBlocks() {
//        return BlocksRegistration.BLOCKS.getEntries()
//                .stream()
//                .map(RegistryObject::get)
//                .toList();
//    }

    public LootTable.Builder nestLootTable(Block beenest) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(HAS_SILK_TOUCH)
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                                LootItem.lootTableItem(beenest).apply(CopyNbtFunction
                                        .copyData(ContextNbtProvider.BLOCK_ENTITY)
                                        .copy("species", "BlockEntityTag.species", CopyNbtFunction.MergeStrategy.REPLACE)
                                )
                        ))
                .withPool(LootPool.lootPool()
                        .when(HAS_NO_SILK_TOUCH)
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                                LootItem.lootTableItem(ItemsRegistration.PRINCESS).apply(InheritHiveSpeciesFunction.set())
                        ))
                .withPool(LootPool.lootPool()
                        .when(HAS_NO_SILK_TOUCH)
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                                LootItem.lootTableItem(ItemsRegistration.DRONE)
                                        .apply(InheritHiveSpeciesFunction.set())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1))
                        ))
                .withPool(LootPool.lootPool()
                        .when(HAS_NO_SILK_TOUCH)
                        .setRolls(BinomialDistributionGenerator.binomial(1, 0.35f))
                        .add(
                                LootItem.lootTableItem(ItemsRegistration.COMB)
                                        .apply(InheritHiveCombFunction.set())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1))
                        )
                );
    }
}
