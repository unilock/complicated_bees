package com.accbdd.complicated_bees.block.entity;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerSlot;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AdaptedItemHandler extends ItemStackHandler {

    private final ItemStackHandler handler;

    public AdaptedItemHandler(ItemStackHandler handler) {
        this.handler = handler;
    }

	@Override
	public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return handler.insert(resource, maxAmount, transaction);
	}

	@Override
	public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return handler.extract(resource, maxAmount, transaction);
	}

	@Override
	public StorageView<ItemVariant> exactView(ItemVariant resource) {
		return handler.exactView(resource);
	}

	@Override
	public Iterable<StorageView<ItemVariant>> nonEmptyViews() {
		return handler.nonEmptyViews();
	}

	@Override
	public Iterator<StorageView<ItemVariant>> nonEmptyIterator() {
		return handler.nonEmptyIterator();
	}

	@Override
	public int getSlotCount() {
		return handler.getSlotCount();
	}

	@Override
	public ItemStackHandlerSlot getSlot(int slot) {
		return handler.getSlot(slot);
	}

	@Override
	public List<SingleSlotStorage<ItemVariant>> getSlots() {
		return handler.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return handler.getStackInSlot(slot);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		handler.setStackInSlot(slot, stack);
	}

	@Override
	public ItemVariant getVariantInSlot(int slot) {
		return handler.getVariantInSlot(slot);
	}

	@Override
	public int getSlotLimit(int slot) {
		return handler.getSlotLimit(slot);
	}

//	@Override
//	protected int getStackLimit(int slot, ItemVariant resource) {
//		return handler.getStackLimit(slot, resource);
//	}

//	@Override
//	protected void onContentsChanged(int slot) {
//		handler.onContentsChanged(slot);
//	}

	@Override
	public SortedSet<ItemStackHandlerSlot> getSlotsContaining(Item item) {
		return handler.getSlotsContaining(item);
	}

//	@Override
//	protected void onLoad() {
//		handler.onLoad();
//	}

	@Override
	public boolean empty() {
		return handler.empty();
	}

	@Override
	public void setSize(int size) {
		handler.setSize(size);
	}

//	@Override
//	protected ItemStackHandlerSlot makeSlot(int index, ItemStack stack) {
//		return handler.makeSlot(index, stack);
//	}

	@Override
	public CompoundTag serializeNBT() {
		return handler.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		handler.deserializeNBT(nbt);
	}

	@Override
	public String toString() {
		return handler.toString();
	}

	@Override
	public boolean isItemValid(int slot, ItemVariant resource) {
		return handler.isItemValid(slot, resource);
	}

	@Override
	public boolean isItemValid(int slot, ItemVariant resource, int count) {
		return handler.isItemValid(slot, resource, count);
	}

	@Override
	public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return handler.insertSlot(slot, resource, maxAmount, transaction);
	}

	@Override
	public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
		return handler.extractSlot(slot, resource, maxAmount, transaction);
	}

	@Override
	public Iterator<StorageView<ItemVariant>> iterator() {
		return handler.iterator();
	}

	@Override
	public boolean supportsInsertion() {
		return handler.supportsInsertion();
	}

	@Override
	public boolean supportsExtraction() {
		return handler.supportsExtraction();
	}

	@Override
	public long getVersion() {
		return handler.getVersion();
	}

	@Override
	public long simulateInsert(ItemVariant resource, long maxAmount, @Nullable TransactionContext transaction) {
		return handler.simulateInsert(resource, maxAmount, transaction);
	}

	@Override
	public long simulateExtract(ItemVariant resource, long maxAmount, @Nullable TransactionContext transaction) {
		return handler.simulateExtract(resource, maxAmount, transaction);
	}

	@Override
	public void forEach(Consumer<? super StorageView<ItemVariant>> action) {
		handler.forEach(action);
	}

	@Override
	public Spliterator<StorageView<ItemVariant>> spliterator() {
		return handler.spliterator();
	}
}
