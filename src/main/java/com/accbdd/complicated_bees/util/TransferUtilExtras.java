package com.accbdd.complicated_bees.util;

import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.world.item.ItemStack;
import team.reborn.energy.api.EnergyStorage;

@SuppressWarnings("UnstableApiUsage")
public class TransferUtilExtras {
	public static long simulateInsertSlot(SlottedStorage<ItemVariant> storage, int slot, ItemStack stack, Transaction tx) {
		if (!storage.supportsInsertion()) {
			return 0L;
		} else {
			int max = (int) Math.min(2147483647L, stack.getCount());
			return StorageUtil.simulateInsert(storage.getSlot(slot), ItemVariant.of(stack), max, tx);
		}
	}

	public static long simulateInsertSlot(SlottedStorage<ItemVariant> storage, int slot, ItemStack stack) {
		try (Transaction tx = TransferUtil.getTransaction()) {
			long inserted = simulateInsertSlot(storage, slot, stack, tx);
			tx.commit();
			return inserted;
		}
	}

	public static ItemStack extractAnySlot(SlottedStorage<ItemVariant> storage, int slot, long maxAmount, Transaction tx) {
		if (!storage.supportsExtraction()) {
			return ItemStack.EMPTY;
		} else {
			int max = (int) Math.min(2147483647L, maxAmount);
			ResourceAmount<ItemVariant> extracted = StorageUtil.extractAny(storage.getSlot(slot), max, tx);
			return extracted == null ? ItemStack.EMPTY : extracted.resource().toStack((int) extracted.amount());
		}
	}

	public static ItemStack extractAnySlot(SlottedStorage<ItemVariant> storage, int slot, long maxAmount) {
		try (Transaction tx = TransferUtil.getTransaction()) {
			ItemStack stack = extractAnySlot(storage, slot, maxAmount, tx);
			tx.commit();
			return stack;
		}
	}

	public static long receiveEnergy(EnergyStorage storage, long maxAmount, Transaction tx) {
		if (!storage.supportsInsertion()) {
			return 0L;
		} else {
			int max = (int) Math.min(2147483647L, maxAmount);
			return storage.insert(max, tx);
		}
	}

	public static long receiveEnergy(EnergyStorage storage, long maxAmount) {
		try (Transaction tx = TransferUtil.getTransaction()) {
			long received = receiveEnergy(storage, maxAmount, tx);
			tx.commit();
			return received;
		}
	}

	public static long extractEnergy(EnergyStorage storage, long maxAmount, Transaction tx) {
		if (!storage.supportsExtraction()) {
			return 0L;
		} else {
			int max = (int) Math.min(2147483647L, maxAmount);
			return storage.extract(max, tx);
		}
	}

	public static long extractEnergy(EnergyStorage storage, long maxAmount) {
		try (Transaction tx = TransferUtil.getTransaction()) {
			long extracted = extractEnergy(storage, maxAmount, tx);
			tx.commit();
			return extracted;
		}
	}
}
