package com.accbdd.complicated_bees.block.entity;

import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AdaptedItemHandler implements InventoryStorage {

    private final InventoryStorage handler;

    public AdaptedItemHandler(InventoryStorage handler) {
        this.handler = handler;
    }

    @Override
    public int getSlotCount() {
        return this.handler.getSlotCount();
    }

    @Override
    public SingleSlotStorage<ItemVariant> getSlot(int slot) {
        return this.handler.getSlot(slot);
    }

    @Override
    @UnmodifiableView
    public List<SingleSlotStorage<ItemVariant>> getSlots() {
        return this.handler.getSlots();
    }

    @Override
    public boolean supportsInsertion() {
        return this.handler.supportsInsertion();
    }

    @Override
    public boolean supportsExtraction() {
        return this.handler.supportsExtraction();
    }

    @Override
    public Iterator<StorageView<ItemVariant>> nonEmptyIterator() {
        return this.handler.nonEmptyIterator();
    }

    @Override
    public Iterable<StorageView<ItemVariant>> nonEmptyViews() {
        return this.handler.nonEmptyViews();
    }

    @Override
    public long getVersion() {
        return this.handler.getVersion();
    }

    @Override
    public long simulateInsert(ItemVariant resource, long maxAmount, @Nullable TransactionContext transaction) {
        return this.handler.simulateInsert(resource, maxAmount, transaction);
    }

    @Override
    public long simulateExtract(ItemVariant resource, long maxAmount, @Nullable TransactionContext transaction) {
        return this.handler.simulateExtract(resource, maxAmount, transaction);
    }

    @Override
    public @Nullable StorageView<ItemVariant> exactView(ItemVariant resource) {
        return this.handler.exactView(resource);
    }

    @Override
    public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        return this.handler.insert(resource, maxAmount, transaction);
    }

    @Override
    public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        return this.handler.extract(resource, maxAmount, transaction);
    }

    @Override
    public Iterator<StorageView<ItemVariant>> iterator() {
        return this.handler.iterator();
    }

    @Override
    public void forEach(Consumer<? super StorageView<ItemVariant>> action) {
        this.handler.forEach(action);
    }

    @Override
    public Spliterator<StorageView<ItemVariant>> spliterator() {
        return this.handler.spliterator();
    }
}
