package com.accbdd.complicated_bees.block.entity;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import team.reborn.energy.api.EnergyStorage;

public class AdaptedEnergyStorage implements EnergyStorage {
    private final EnergyStorage storage;

    public AdaptedEnergyStorage(EnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean supportsInsertion() {
        return storage.supportsInsertion();
    }

    @Override
    public boolean supportsExtraction() {
        return storage.supportsExtraction();
    }

    @Override
    public long insert(long l, TransactionContext transactionContext) {
        return storage.insert(l, transactionContext);
    }

    @Override
    public long extract(long l, TransactionContext transactionContext) {
        return storage.extract(l, transactionContext);
    }

    @Override
    public long getAmount() {
        return storage.getAmount();
    }

    @Override
    public long getCapacity() {
        return storage.getCapacity();
    }
}
