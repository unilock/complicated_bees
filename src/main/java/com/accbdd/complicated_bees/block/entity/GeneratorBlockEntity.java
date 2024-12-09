package com.accbdd.complicated_bees.block.entity;

import com.accbdd.complicated_bees.config.Config;
import com.accbdd.complicated_bees.registry.BlockEntitiesRegistration;
import com.accbdd.complicated_bees.util.TransferUtilExtras;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class GeneratorBlockEntity extends BlockEntity {
    public static final String ITEMS_TAG = "items";
    public static final String ENERGY_TAG = "energy";
    public static final String BURN_TIME_TAG = "burn_time";

    public static final int GENERATE = Config.CONFIG.generatorEnergy.get();
    public static final int MAXTRANSFER = 1000;
    public static final int CAPACITY = 100000;

    public static final int SLOT_COUNT = 1;
    public static final int SLOT = 0;

    private final ItemStackHandler items = createItemHandler();
    private final ItemStackHandler itemHandler = new AdaptedItemHandler(items) {
        @Override
        public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
            return 0L;
        }

        @Override
        public long extractSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
            return 0L;
        }

        @Override
        public boolean supportsExtraction() {
            return false;
        }
    };

    private final SimpleEnergyStorage energy = createEnergyStorage();
    private final EnergyStorage energyHandler = new AdaptedEnergyStorage(energy) {
        @Override
        public long insert(long l, TransactionContext transactionContext) {
            return 0L;
        }

        @Override
        public long extract(long l, TransactionContext transactionContext) {
            return 0L;
        }

        @Override
        public boolean supportsExtraction() {
            return false;
        }

        @Override
        public boolean supportsInsertion() {
            return false;
        }
    };

    private int burnTime;
    private int maxBurnTime;

//    @Override
//    public void invalidateCaps() {
//        super.invalidateCaps();
//        itemHandler.invalidate();
//        energyHandler.invalidate();
//    }
//
//    @Override
//    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
//        if (cap == ForgeCapabilities.ITEM_HANDLER)
//            return getItemHandler().cast();
//        if (cap == ForgeCapabilities.ENERGY)
//            return getEnergyHandler().cast();
//
//        return super.getCapability(cap, side);
//    }

    public GeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesRegistration.GENERATOR_BLOCK_ENTITY.get(), pos, state);
    }

    public void tickServer() {
        generateEnergy();
        distributeEnergy();
    }

    private void generateEnergy() {
        if (energy.getAmount() < energy.getCapacity()) {
            if (burnTime <= 0) {
                ItemStack fuel = items.getStackInSlot(SLOT);
                if (fuel.isEmpty()) {
                    return;
                }
                int burnTime = FuelRegistry.INSTANCE.get(fuel.getItem());
                maxBurnTime = burnTime;
                setBurnTime(burnTime);
                if (burnTime <= 0) {
                    return;
                }
                TransferUtilExtras.extractAnySlot(items, SLOT, 1);
            } else {
                setBurnTime(burnTime - 1);
                TransferUtilExtras.receiveEnergy(energy, GENERATE);
            }
            setChanged();
        }
    }

    private void setBurnTime(int bt) {
        if (bt == burnTime) {
            return;
        }
        burnTime = bt;
        if (getBlockState().getValue(BlockStateProperties.POWERED) != burnTime > 0) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.POWERED, burnTime > 0));
        }
        setChanged();
    }

    private void distributeEnergy() {
        // Check all sides of the block and send energy if that block supports the energy capability
        for (Direction direction : Direction.values()) {
            if (energy.getAmount() <= 0L) {
                return;
            }
            var be = getLevel().getBlockEntity(getBlockPos().relative(direction));
            if (be != null) {
                // TODO: verify that this direction stuff is correct
                EnergyStorage energy = EnergyStorage.SIDED.find(getLevel(), getBlockPos().relative(direction), direction.getOpposite());
                if (energy != null) {
                    if (energy.supportsInsertion()) {
                        long received = TransferUtilExtras.receiveEnergy(energy, Math.min(this.energy.getAmount(), MAXTRANSFER));
                        TransferUtilExtras.extractEnergy(this.energy, received);
                        setChanged();
                    }
                }
            }
        }
    }

    public int getBurnTime() {
        return this.burnTime;
    }

    public ItemStackHandler getItems() {
        return items;
    }

    public int getStoredPower() {
        return (int) energy.getAmount();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ITEMS_TAG, items.serializeNBT());
        tag.put(ENERGY_TAG, energy.serializeNBT());
        tag.put(BURN_TIME_TAG, IntTag.valueOf(burnTime));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(ITEMS_TAG)) {
            items.deserializeNBT(tag.getCompound(ITEMS_TAG));
        }
        if (tag.contains(ENERGY_TAG)) {
            energy.deserializeNBT(tag.get(ENERGY_TAG));
        }
        if (tag.contains(BURN_TIME_TAG)) {
            burnTime = tag.getInt(BURN_TIME_TAG);
            maxBurnTime = burnTime;
        }
    }

    @NotNull
    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(SLOT_COUNT) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @NotNull
    private SimpleEnergyStorage createEnergyStorage() {
        return new SimpleEnergyStorage(CAPACITY, MAXTRANSFER, MAXTRANSFER);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public EnergyStorage getEnergyHandler() {
        return energyHandler;
    }

    public int getMaxBurnTime() {
        return maxBurnTime;
    }
}
