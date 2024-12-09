package com.accbdd.complicated_bees.block.entity;

import com.accbdd.complicated_bees.config.Config;
import com.accbdd.complicated_bees.genetics.Product;
import com.accbdd.complicated_bees.recipe.CentrifugeRecipe;
import com.accbdd.complicated_bees.registry.BlockEntitiesRegistration;
import com.accbdd.complicated_bees.registry.EsotericRegistration;
import com.accbdd.complicated_bees.util.TransferUtilExtras;
import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.RecipeWrapper;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedSlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@SuppressWarnings("UnstableApiUsage")
public class CentrifugeBlockEntity extends BlockEntity {
    public static final int INPUT_SLOT = 0;
    public static final int INPUT_SLOT_COUNT = 1;
    public static final String ITEMS_INPUT_TAG = "input_items";

    public static final int OUTPUT_SLOT = 0;
    public static final int OUTPUT_SLOT_COUNT = 9;
    public static final String ITEMS_OUTPUT_TAG = "output_items";

    public static final int SLOT_COUNT = INPUT_SLOT_COUNT + OUTPUT_SLOT_COUNT;

    public final Stack<ItemStack> outputBuffer = new Stack<>();
    public static final String OUTPUT_BUFFER_TAG = "output_buffer";

    public static final String ENERGY_TAG = "energy";
    public static final int CAPACITY = 100000;
    public static final int MAXTRANSFER = 5000;
    public static final int USAGE = Config.CONFIG.centrifugeEnergy.get();

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;
    private final RecipeManager.CachedCheck<Container, CentrifugeRecipe> quickCheck;

    private final ItemStackHandler inputItems = createItemHandler(INPUT_SLOT_COUNT);
    private final ItemStackHandler outputItems = createItemHandler(OUTPUT_SLOT_COUNT);
    private final CombinedSlottedStorage<ItemVariant, ItemStackHandler> itemHandler = new CombinedSlottedStorage<>(List.of(inputItems, outputItems));
    private final ItemStackHandler inputItemHandler = new AdaptedItemHandler(inputItems) {
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
    private final ItemStackHandler outputItemHandler = new AdaptedItemHandler(outputItems) {
        @Override
        public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
            return 0L;
        }

        @Override
        public long insertSlot(int slot, ItemVariant resource, long maxAmount, TransactionContext transaction) {
            return 0L;
        }

        @Override
        public boolean isItemValid(int slot, ItemVariant resource, int count) {
            return false;
        }

        @Override
        public boolean supportsInsertion() {
            return false;
        }
    };

//    @Override
//    public void invalidateCaps() {
//        super.invalidateCaps();
//        itemHandler.invalidate();
//        inputItemHandler.invalidate();
//        outputItemHandler.invalidate();
//        energyHandler.invalidate();
//    }
//
//    @Override
//    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
//        if (cap == ForgeCapabilities.ITEM_HANDLER) {
//            if (side == null)
//                return this.getItemHandler().cast();
//            if (side == Direction.DOWN)
//                return this.getOutputItemHandler().cast();
//            return this.getInputItemHandler().cast();
//        }
//        if (cap == ForgeCapabilities.ENERGY)
//            return this.getEnergyHandler().cast();
//        return super.getCapability(cap, side);
//    }

    public CentrifugeBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntitiesRegistration.CENTRIFUGE_ENTITY.get(), pos, blockState);
        this.quickCheck = RecipeManager.createCheck(EsotericRegistration.CENTRIFUGE_RECIPE.get());
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CentrifugeBlockEntity.this.progress;
                    case 1 -> CentrifugeBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CentrifugeBlockEntity.this.progress = value;
                    case 1 -> CentrifugeBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ItemStackHandler getInputItems() {
        return inputItems;
    }

    public ItemStackHandler getOutputItems() {
        return outputItems;
    }

    public CombinedSlottedStorage<ItemVariant, ItemStackHandler> getItemHandler() {
        return itemHandler;
    }

    public ItemStackHandler getInputItemHandler() {
        return inputItemHandler;
    }

    public ItemStackHandler getOutputItemHandler() {
        return outputItemHandler;
    }

    private final SimpleEnergyStorage energy = createEnergyStorage();
    private final EnergyStorage energyHandler = new AdaptedEnergyStorage(energy) {
        @Override
        public long extract(long l, TransactionContext transactionContext) {
            return 0L;
        }

        @Override
        public long insert(long l, TransactionContext transactionContext) {
            setChanged();
            return super.insert(l, transactionContext);
        }

        @Override
        public boolean supportsExtraction() {
            return false;
        }

        @Override
        public boolean supportsInsertion() {
            return true;
        }
    };

    private ItemStackHandler createItemHandler(int slots) {
        return new ItemStackHandler(slots) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    private SimpleEnergyStorage createEnergyStorage() {
        return new SimpleEnergyStorage(CAPACITY, MAXTRANSFER, MAXTRANSFER);
    }

    public EnergyStorage getEnergyHandler() {
        return energyHandler;
    }

    public int getStoredPower() {
        return (int) energy.getAmount();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ITEMS_INPUT_TAG, inputItems.serializeNBT());
        tag.put(ITEMS_OUTPUT_TAG, outputItems.serializeNBT());
        tag.put(ENERGY_TAG, energy.serializeNBT());
        ListTag bufferTag = new ListTag();
        for (ItemStack stack : outputBuffer) {
            bufferTag.add(stack.save(new CompoundTag()));
        }
        tag.put(OUTPUT_BUFFER_TAG, bufferTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(ITEMS_INPUT_TAG)) {
            inputItems.deserializeNBT(tag.getCompound(ITEMS_INPUT_TAG));
        }
        if (tag.contains(ITEMS_OUTPUT_TAG)) {
            outputItems.deserializeNBT(tag.getCompound(ITEMS_OUTPUT_TAG));
        }
        if (tag.contains(OUTPUT_BUFFER_TAG)) {
            for (Tag itemCompound : tag.getList(OUTPUT_BUFFER_TAG, Tag.TAG_COMPOUND)) {
                outputBuffer.add(ItemStack.of((CompoundTag) itemCompound));
            }
        }
        if (tag.contains(ENERGY_TAG)) {
            energy.deserializeNBT(tag.get(ENERGY_TAG));
        }
    }

    public void tickServer() {
        ItemStack stack = this.inputItems.getStackInSlot(INPUT_SLOT);

        if (!outputBuffer.empty()) {
            tryEmptyBuffer();
        }

        if (hasRecipe(stack) && energy.getAmount() > 0L && outputBuffer.empty()) {
            if (!getBlockState().getValue(BlockStateProperties.POWERED)) {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.POWERED, true));
            }
            increaseCraftingProgress();
            setChanged();
            if (hasFinished()) {
                craftItem(stack);
                resetProgress();
            }
        } else {
            if (getBlockState().getValue(BlockStateProperties.POWERED)) {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.POWERED, false));
            }
            lowerProgress();
        }
    }

    private void lowerProgress() {
        if (progress > 0) {
            progress--;
        }
    }

    private void tryEmptyBuffer() {
        while (!outputBuffer.empty()) {
            ItemStack next = outputBuffer.pop();
            long inserted = TransferUtil.insertItem(outputItems, next);
            if (inserted > 0L) {
                setChanged();
            } else {
                outputBuffer.push(next);
                break;
            }
        }
    }

    private void increaseCraftingProgress() {
        TransferUtilExtras.extractEnergy(energy, USAGE);
        progress++;
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasRecipe(ItemStack stack) {
        Optional<CentrifugeRecipe> recipeCheck = quickCheck.getRecipeFor(getWrapper(), getLevel());
        if (recipeCheck.isPresent()) {
            ItemStack primary = ItemStack.EMPTY;
            CentrifugeRecipe recipe = recipeCheck.get();
            if (!recipe.getOutputs().isEmpty()) {
                primary = recipe.getOutputs().get(0).getStack();
            }
            return canInsertIntoOutput(primary);
        }
        return false;
    }

    private boolean hasFinished() {
        return progress >= maxProgress;
    }

    private void craftItem(ItemStack stack) {
        List<Product> products = quickCheck.getRecipeFor(getWrapper(), getLevel()).get().getOutputs();
        TransferUtilExtras.extractAnySlot(inputItems, INPUT_SLOT, 1);

        for (Product product : products) {
            outputBuffer.push(product.getStackResult());
        }
    }

    private boolean canInsertIntoOutput(ItemStack stack) {
        boolean canInsert = false;
        int stackCount = stack.getCount();
        for (int i = 0; i < OUTPUT_SLOT_COUNT; i++) {
            long inserted = TransferUtilExtras.simulateInsertSlot(outputItems, i, stack);
            canInsert = canInsert || (inserted < stackCount);
        }
        return canInsert;
    }

    public ContainerData getData() {
        return data;
    }

    public RecipeWrapper getWrapper() {
        return new RecipeWrapper(inputItems);
    }
}
